package org.model.myapplicationmodel.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.model.myapplicationmodel.R;
import org.model.myapplicationmodel.adapter.ModelAdapter;
import org.model.myapplicationmodel.dao.ModelRestClient;
import org.model.myapplicationmodel.domain.TabModel;
import org.model.myapplicationmodel.util.UtilApp;

import java.util.ArrayList;
import java.util.List;

public class ModelActivity extends AppCompatActivity {

    private EditText editTextColumnModel;
    private Button buttonSave, buttonReturn;
    private ListView listViewModel;

    private UtilApp utilApp;
    private ModelRestClient modelRestClient;

    private TextView textViewTitle;
    private TabModel tabModel;

    private AlertDialog alerta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        if (android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // initialize
        createFindByID();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // initialize
        createFindByID();
    }

    public void createFindByID(){

        editTextColumnModel  = (EditText) findViewById(R.id.txt_ColumnModel);
        editTextColumnModel.setImeOptions(EditorInfo.IME_ACTION_NONE);
        editTextColumnModel.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
        editTextColumnModel.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            //enter
                            save(editTextColumnModel, v);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        buttonSave = (Button) findViewById(R.id.btn_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save
                save(editTextColumnModel, view);
            }
        });

        buttonReturn = (Button) findViewById(R.id.btn_return);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabModel = null;
                createFindByID();
            }
        });

        //set title
        textViewTitle = (TextView) findViewById(R.id.lbl_title);
        if (tabModel != null){
            textViewTitle.setText("Update item select: " + tabModel.getId().toString());

            // visible button
            buttonReturn.setVisibility(View.VISIBLE);
            // set value select
            editTextColumnModel.setText(tabModel.getColumnmodel());
        }else{
            textViewTitle.setText("Add a new item | Press long clik to Edit");

            // gone button
            buttonReturn.setVisibility(View.GONE);
            // set value clear
            editTextColumnModel.setText("");
        }
        // list obj
        list();
    }

    /**
     *
     * @param editTextColumnModel
     * @param view
     */
    public void save(EditText editTextColumnModel, View view) {
        modelRestClient = new ModelRestClient();
        utilApp = new UtilApp();
        try {
            String value = editTextColumnModel.getText().toString();
            // validate and save value
            if (validateSave(value)) {

                // save
                TabModel t = new TabModel();
                if (tabModel != null){
                    // set id if select to edit item
                    t.setId(tabModel.getId());
                }
                t.setColumnmodel(value);
                modelRestClient.salvar(t);

                infMsg(view,"Sucessfull!",true);
            }else{
                infMsg(view,"Invalid value!",false);
            }
        }catch (Exception e){
            utilApp.msgToastLong("Script:" + e.getMessage(), view.getContext());
        }finally {
            tabModel = null;
            createFindByID();
        }
    }

    public void delete(View v){
        modelRestClient = new ModelRestClient();
        utilApp = new UtilApp();
        try {
            modelRestClient.remover(tabModel.getId().toString());
            tabModel = null;
            infMsg(v,"Sucessfull!",true);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            createFindByID();
        }
    }

    public void list(){
        /**
         * connn
         */
        modelRestClient = new ModelRestClient();
        utilApp = new UtilApp();
        try {

            listViewModel = (ListView) findViewById(R.id.list_ColumnModel);
            // fill list
            List<TabModel> modelList = modelRestClient.listAll();
            // set adapter
            if (modelList.size() > 0){
                ModelAdapter modelAdapter = new ModelAdapter(this, modelList);
                listViewModel.setAdapter(modelAdapter);

                // set item update
                listViewModel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position >= 0) {
                            tabModel = new TabModel();
                            tabModel = (TabModel) parent.getItemAtPosition(position);

                            // select option
                            SelectOption(view);
                        }
                        return false;
                    }
                });
            }else{
                // null
                listViewModel.setAdapter(null);
            }

        }catch (Exception e){
           e.getStackTrace();
        }
    }

    /**
     * validateSave
     * @param value
     * @return
     */
    public boolean validateSave(String value){
        if (value.length() == 0){
            return false;
        }
        return true;
    }

    public void SelectOption(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        //set icone
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ALERT!");
        builder.setMessage("Select the option...");

        // set edit item
        builder.setNegativeButton(" EDIT  ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // refresh
                createFindByID();
            }
        });

        // set delete item
        builder.setPositiveButton(" DELETE  ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                delete(view);
            }
        });

        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {}
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void infMsg(View view, String msg, boolean b){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        if (!b){
            //set icone
            builder.setIcon(R.drawable.warning);
            builder.setTitle("ALERT!");
            builder.setMessage(msg);
        }else if (b){
            //set icone
            builder.setIcon(R.drawable.inf_alert);
            builder.setTitle("INF!");
            builder.setMessage(msg);
        }

        builder.setNegativeButton(" EXIT  ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {}
        });
        alerta = builder.create();
        alerta.show();
    }

}
