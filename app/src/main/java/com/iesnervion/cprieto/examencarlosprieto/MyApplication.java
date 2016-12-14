package com.iesnervion.cprieto.examencarlosprieto;

import android.app.Application;

import com.iesnervion.cprieto.examencarlosprieto.Models.Jugador;

import java.util.Vector;

/**
 * Created by cprieto on 14/12/16.
 */


//ToDo Guardar en fichero,no en memoria
public class MyApplication extends Application {

    Vector<Jugador> vectorJugadores;

    public MyApplication(){

        vectorJugadores = new Vector<>(10,0);
    }

    public MyApplication(Vector<Jugador> arrayJugadores) {

        this.vectorJugadores = arrayJugadores;

    }

    public Vector<Jugador> getVectorJugadores() {

        return vectorJugadores;

    }

    public void setVectorJugadores(Vector<Jugador> arrayJugadores) {

        if(arrayJugadores.size()<11){

            this.vectorJugadores = arrayJugadores;

        }
    }

    public Jugador getJugador(int posicion){

        return vectorJugadores.elementAt(posicion);

    }

    public void setJugador(Jugador jugador){

        if(this.vectorJugadores.size()<10){

            vectorJugadores.add(jugador);

        }
    }

    /**
     * Método que sobreescribe un jugador en una posición dada
     * @param j
     * @param posicion
     */
    public void sobreEscribeJugador(Jugador j,int posicion){

        vectorJugadores.setElementAt(j,posicion);

    }

    public void eliminaJugador(int posicion){

        vectorJugadores.remove(posicion);

    }

}
