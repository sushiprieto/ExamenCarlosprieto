package com.iesnervion.cprieto.examencarlosprieto.Models;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.iesnervion.cprieto.examencarlosprieto.Controllers.ImageAdapter;
import com.iesnervion.cprieto.examencarlosprieto.MyApplication;
import com.iesnervion.cprieto.examencarlosprieto.R;

import java.util.Vector;

/**
 * Created by cprieto on 14/12/16.
 */

public class SeleccionaImagen extends AppCompatActivity implements GridView.OnItemClickListener{

    private int idSeleccionado;
    private ImageAdapter adaptador;

    //Aquí guardaremos TODAS las imagenes
    private Vector<Integer> imagenesOriginal = new Vector<>(25, 0);
    //Aquí solo guardaremos las imagenes que vamos a mostrar.
    private Vector<Integer> imagenesMostrar = new Vector<>(25, 0);
    private Vector<Integer> imagenesEliminar = new Vector<>(25, 0);

    private Vector<Jugador> vectorJugadores=new Vector<>(10,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        recuperaImagenes();
        adaptador=new ImageAdapter(this, imagenesMostrar);
        gridview.setAdapter(adaptador);

        gridview.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent returnIntent = new Intent();
        idSeleccionado = adaptador.getItem(position);
        returnIntent.putExtra("imagen", idSeleccionado);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    /**
     * Método para facilitar la legibilidad, el código de este método puede ir en el onCreate (originalmente iba en el
     * ImageAdapter, pero para hacer esa clase más versatil, lo cambié al onCreate, y por último creé este método)
     */
    private void recuperaImagenes(){

        //Recuperamos todas las imagenes
        for (int i = 0; i < 25; i++) {

            if (i < 10) {

                imagenesOriginal.add(getResources().getIdentifier("jugador0" + i, "drawable", getPackageName()));

            } else {

                imagenesOriginal.add(getResources().getIdentifier("jugador" + i, "drawable", getPackageName()));

            }
        }

        //Recuperamos las imagenes que no vamos a mostrar porque ya las tenemos cogidas
        vectorJugadores=((MyApplication) getApplication()).getVectorJugadores();

        for(int j = 0; j < vectorJugadores.size(); j++){

            imagenesEliminar.add(vectorJugadores.elementAt(j).getImagen());

        }

        //Por último, quitamos de las imagenes a mostrar, las que no queremos mostrar
        imagenesMostrar=imagenesOriginal;

        for(int k = 0; k < imagenesEliminar.size(); k++){

            imagenesMostrar.remove(imagenesEliminar.elementAt(k));

        }
    }


}
