package com.iesnervion.cprieto.examencarlosprieto.Models;

/**
 * Created by cprieto on 7/12/16.
 */
import android.graphics.Region;
import android.os.Parcel;
import android.os.Parcelable;

import com.iesnervion.cprieto.examencarlosprieto.R;


public class Jugador implements Parcelable {

    //ToDo cambiar este método por uno que recupere el id de un archivo o BBDD
    private static int idStatico = 0;
    private int id;
    private String nombre;
    private int imagen;
    private String posicion;
    private int altura;
    private int peso;

    public Jugador(){

        id = asignaId();
        nombre = "Defecto";
        imagen = R.drawable.silueta;
        posicion = "Base";
        altura = 200;
        peso = 100;
    }


    public Jugador(String nombre, int imagen, String posicion, int altura, int peso) {

        id = asignaId();
        this.nombre = nombre;
        this.imagen = imagen;
        this.posicion = posicion;
        this.altura = altura;
        this.peso = peso;

    }

    protected Jugador(Parcel in) {

        id = in.readInt();
        nombre = in.readString();
        imagen = in.readInt();
        posicion = in.readString();
        altura = in.readInt();
        peso = in.readInt();

    }

    public int getId(){return id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString(){
        //ToDo Modificar para que use cadenas de @string
        String devolver="Altura: "+this.altura+" Peso: "+this.peso+" Posición: "+this.posicion;
        return  devolver;
    }

    @Override
    public boolean equals(Object o){
        boolean resultado=false;
        if(o instanceof Jugador){
            Jugador j=(Jugador) o;
            resultado= (j.getId()==this.id);

        }
        return resultado;
    }

    private int asignaId() {

        idStatico++;
        return idStatico;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeInt(imagen);
        dest.writeString(posicion);
        dest.writeInt(altura);
        dest.writeInt(peso);

    }


    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}