package com.iesnervion.cprieto.examencarlosprieto.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iesnervion.cprieto.examencarlosprieto.R;

/**
 * Created by cprieto on 14/12/16.
 */

public class SpinnerArrayAdapter extends ArrayAdapter {

    public SpinnerArrayAdapter(Context context, int resource, int textViewResourceID, Object[] objects) {

        super(context, resource, textViewResourceID, objects);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        SpinnerViewHolder holder;

        //Aqui guardaremos o bien el peso, o bien la altura en formato string, nada de int
        String cadena = (String) getItem(position);

        if (v == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.fila_spinner, parent, false);
            holder = new SpinnerViewHolder(v, R.id.txvNumero);
            v.setTag(holder);

        } else {

            holder = (SpinnerViewHolder) v.getTag();

        }

        holder.getNumero().setText(cadena);

        return v;
    }
}
