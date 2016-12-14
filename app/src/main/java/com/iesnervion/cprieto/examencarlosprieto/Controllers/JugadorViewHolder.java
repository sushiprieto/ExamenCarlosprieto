package com.iesnervion.cprieto.examencarlosprieto.Controllers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cprieto on 7/12/16.
 */

public class JugadorViewHolder {

    ImageView foto;
    TextView nombre, info;

    public JugadorViewHolder(View row, int fotoid, int nombreid, int infoid){

        this.foto = (ImageView)row.findViewById(fotoid);
        this.nombre = (TextView)row.findViewById(nombreid);
        this.info = (TextView)row.findViewById(infoid);

    }

    public ImageView getFoto() {
        return foto;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getInfo() {
        return info;
    }
}
