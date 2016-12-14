package com.iesnervion.cprieto.examencarlosprieto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.iesnervion.cprieto.examencarlosprieto.Controllers.SpinnerArrayAdapter;
import com.iesnervion.cprieto.examencarlosprieto.Models.Jugador;
import com.iesnervion.cprieto.examencarlosprieto.Models.SeleccionaImagen;

import java.util.Vector;

/**
 * Created by cprieto on 14/12/16.
 */

public class DetallesJugador extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener {

    private static final int PICK_CONTACT_REQUEST = 0 ;

    //Si queremos editar un jugador, lo recogemos en el bundle
    private Bundle datos;
    private Jugador jugadorEditar, jugadorInsertar;
    private String nombreJugador, posicion;
    private int pesoJugador, alturaJugador, imagenJugador, posicionEditar;

    //Aquí guardamos si lo que vamos a hacer es editar un jugador, o a añadir uno nuevo
    private boolean editar;

    //Aqui guardamos si todos los campos son correctos
    private boolean imagenSeleccionada = false;
    private boolean alturaSeleccionada = false;
    private boolean pesoSeleccionado = false;
    private boolean posicionSeleccionada = false;

    //Objetos del xml
    private EditText nombre;
    private ImageView imagenView;
    private RadioGroup rg;
    private RadioButton rbBase, rbEscolta, rbAlero, rbAla, rbPivot;
    private Spinner spAltura, spPeso;

    //Array compartido
    private Vector<Jugador> vectorJugadores;

    private String[] arrayAltura = new String[101];
    private String[] arrayPeso = new String[206];

    //Número de jugadores de cada posición
    private int alero, ala, pivot, base, escolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        /**
         * Interesante saber que este vectorjugadores hace referencia al vectorJugadores original
         * por lo que cualquier modificación en este vector, supone una modificación en el vector original
         * y viceversa.
         */
        vectorJugadores = ((MyApplication)getApplication()).getVectorJugadores();

        //Contamos las diferentes posiciones para deshabilitar dónde ya haya 2 jugadores
        cuentaPosiciones();

        //Creamos los arrays de Altura y peso y los arrayadapter
        for(Integer i=150;i<=250;i++){
            arrayAltura[i-150]=i.toString();
        }
        for(Integer i=45;i<=250;i++){
            arrayPeso[i-45]=i.toString();
        }

        SpinnerArrayAdapter adapAltura = new SpinnerArrayAdapter(this, R.layout.fila_spinner, R.id.txvNumero, arrayAltura);
        SpinnerArrayAdapter c = new SpinnerArrayAdapter(this, R.layout.fila_spinner, R.id.txvNumero, arrayPeso);

        //Asignamos lso objetos
        nombre = (EditText) findViewById(R.id.txtNombre);

        imagenView = (ImageView) findViewById(R.id.imgbImagen);

        rg = (RadioGroup) findViewById(R.id.rdgRadios);
        rg.setOnCheckedChangeListener(this);

        rbBase = (RadioButton)findViewById(R.id.rbBase);
        rbEscolta = (RadioButton)findViewById(R.id.rbEscolta);
        rbAlero = (RadioButton)findViewById(R.id.rbAlero);
        rbAla = (RadioButton)findViewById(R.id.rbAla);
        rbPivot = (RadioButton)findViewById(R.id.rbPivot);

        spAltura = (Spinner)findViewById(R.id.spAltura);
        spAltura.setAdapter(adapAltura);
        spAltura.setOnItemSelectedListener(this);

        spPeso = (Spinner)findViewById(R.id.spPeso);
        spPeso.setAdapter(adapAltura);
        spPeso.setOnItemSelectedListener(this);

        //recogemos extra si hay
        if(getIntent().hasExtra("jugador")){

            datos = getIntent().getExtras();

            jugadorEditar = datos.getParcelable("jugador");
            posicionEditar = vectorJugadores.indexOf(jugadorEditar);

            editar = true;

            //Le damos los valores.
            nombre.setText(jugadorEditar.getNombre());
            imagenView.setImageResource(jugadorEditar.getImagen());
            imagenSeleccionada = true;
            imagenJugador = jugadorEditar.getImagen();
            posicionSeleccionada = true;

            switch (jugadorEditar.getPosicion()) {

                case "Base":
                    rbBase.setChecked(true);
                    break;

                case "Escolta":
                    rbEscolta.setChecked(true);
                    break;

                case "Alero":
                    rbAlero.setChecked(true);
                    break;

                case "Ala":
                    rbAla.setChecked(true);
                    break;

                case "Pivot":
                    rbPivot.setChecked(true);
                    break;

            }

            //Pongo -150 porque setSelection me pide la posición, no el valor
            spAltura.setSelection(jugadorEditar.getAltura() - 150);
            alturaSeleccionada = true;

            //Pongo -45 porque setSelection me pide la posición, no el valor
            spPeso.setSelection(jugadorEditar.getPeso() - 45);
            pesoSeleccionado = true;

        }else{

            editar = false;

        }

        habilitaPosiciones(editar);

    }


    /**
     * Método que maneja cuando clickemos en la imagen
     * @param v
     */
    public void onClickImagen(View v){

        Intent seleccionaImagen = new Intent(this, SeleccionaImagen.class);
        startActivityForResult(seleccionaImagen, PICK_CONTACT_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_CONTACT_REQUEST) {

            if (resultCode == RESULT_OK) {

                imagenSeleccionada = true;
                imagenJugador = data.getIntExtra("imagen", R.drawable.silueta);
                imagenView.setImageResource(imagenJugador);

            }

        }

    }

    /**
     * Método que controla cuando cambiamos la posición de un jugador.
     * @param group
     * @param checkedId
     */

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){

            case R.id.rbAla:
                posicion = "Ala";
                break;

            case R.id.rbAlero:
                posicion = "Alero";
                break;

            case R.id.rbBase:
                posicion = "Base";
                break;

            case R.id.rbEscolta:
                posicion = "Escolta";
                break;

            case R.id.rbPivot:
                posicion = "Pivot";
                break;

        }

        posicionSeleccionada=true;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //No usar view, porque devuelve la fila pulsada, no el spinner!!
        switch (parent.getId()) {

            case R.id.spPeso:
                pesoSeleccionado = true;
                pesoJugador = position + 45;
                break;

            case R.id.spAltura:
                alturaSeleccionada = true;
                alturaJugador = position + 150;
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        switch (parent.getId()) {

            case R.id.spPeso:
                pesoSeleccionado = false;
                break;

            case R.id.spAltura:
                alturaSeleccionada = false;
                break;

        }

    }


    /**
     * Método que maneja el botón flotante
     * @param v
     */
    @Override
    public void onClick(View v) {

        if(datosCorrectos()){

            nombreJugador = nombre.getText().toString();

            jugadorInsertar = new Jugador(nombreJugador, imagenJugador, posicion, alturaJugador, pesoJugador);

            //Si es un jugador el que vamos a editar, lo que hacemos es sustituir el antiguo por el nuevo
            if(editar){

                vectorJugadores.setElementAt(jugadorInsertar,posicionEditar);

            }else{

                vectorJugadores.add(jugadorInsertar);

            }
        }else{

            Toast toast = Toast.makeText(getApplicationContext(), "Falta algún dato. Jugador no creado", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }

        finish();

    }

    /**
     * Método que devuelve si los datos introducidos son correctos
     * @return
     */
    private boolean datosCorrectos(){

        boolean resultado = false;

        if(!nombre.getText().toString().equals("") && posicionSeleccionada && imagenSeleccionada &&
                alturaSeleccionada && pesoSeleccionado){

            resultado = true;

        }

        return resultado;

    }

    /**
     * Cuenta cuantos jugadores hay de cada posición
     */
    private void cuentaPosiciones(){

        //To malote XD
        //alero=ala=pivot=escolta=base=0;

        alero = 0;
        ala = 0;
        pivot = 0;
        escolta = 0;
        base = 0;

        for(int i=0; i < vectorJugadores.size(); i++){

            switch (vectorJugadores.get(i).getPosicion()){

                case "Ala":
                    ala++;
                    break;

                case "Alero":
                    alero++;
                    break;

                case "Base":
                    base++;
                    break;

                case "Escolta":
                    escolta++;
                    break;
                case "Pivot":
                    pivot++;
                    break;

            }

        }

    }

    /**
     * Método que se encarga de habilitar o deshabilitar los radiobutton de las posiciones
     * @param editar, nos indica si estamos editando el jugador o creandolo
     */
    private void habilitaPosiciones(boolean editar){

        if(ala >= 2){

            rbAla.setEnabled(false);

        }

        if(alero >= 2){

            rbAlero.setEnabled(false);

        }

        if(base >= 2){

            rbBase.setEnabled(false);

        }

        if(escolta >= 2){

            rbEscolta.setEnabled(false);

        }

        if(pivot >= 2){

            rbPivot.setEnabled(false);

        }

        /* Si estamos editando un jugador, no podemos deshabilitar la posición en la que juega
        aunque ya hayan dos jugadores
         */
        if(editar){

            switch (jugadorEditar.getPosicion()) {

                case "Base":
                    rbBase.setEnabled(true);
                    break;

                case "Escolta":
                    rbEscolta.setEnabled(true);
                    break;

                case "Alero":
                    rbAlero.setEnabled(true);
                    break;

                case "Ala":
                    rbAla.setEnabled(true);
                    break;

                case "Pivot":
                    rbPivot.setEnabled(true);
                    break;

            }

        }

    }
}
