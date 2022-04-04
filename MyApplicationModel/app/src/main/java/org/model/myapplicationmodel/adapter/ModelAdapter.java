package org.model.myapplicationmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.model.myapplicationmodel.R;
import org.model.myapplicationmodel.domain.TabModel;

import java.util.List;

public class ModelAdapter extends ArrayAdapter<TabModel> {
    private final Context context;
    private final List<TabModel> lista;

    public ModelAdapter(Context context, List<TabModel> lista) {
        super(context,0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int larguraMax = 30;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_model_column, null);

        TextView textViewID = (TextView) convertView.findViewById(R.id.txt_id_row);
        textViewID.setText(lista.get(position).getId().toString());

        TextView textViewColumn = (TextView) convertView.findViewById(R.id.txt_column_row);
        textViewColumn.setText(lista.get(position).getColumnmodel());

        return convertView;
    }

}