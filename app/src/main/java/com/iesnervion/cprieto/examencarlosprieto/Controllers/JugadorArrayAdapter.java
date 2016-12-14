package com.iesnervion.cprieto.examencarlosprieto.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iesnervion.cprieto.examencarlosprieto.Models.Jugador;
import com.iesnervion.cprieto.examencarlosprieto.R;

/**
 * Created by cprieto on 7/12/16.
 */

public class JugadorArrayAdapter extends ArrayAdapter {

    public JugadorArrayAdapter(Context context, int resource, int textViewResourceID, Object[] objects) {
        super(context, resource, textViewResourceID, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;
        JugadorViewHolder holder;
        Jugador jugador = (Jugador) getItem(position);

        if (v == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.fila_lista, parent, false);
            holder = new JugadorViewHolder(v, R.id.txvTexto, R.id.info,R.id.icon);
            v.setTag(holder);

        } else {

            holder = (JugadorViewHolder) v.getTag();

        }

        holder.getNombre().setText(jugador.getNombre());
        holder.getFoto().setImageResource(jugador.getImagen());
        holder.getInfo().setText(jugador.toString());

        return v;
    }
}
