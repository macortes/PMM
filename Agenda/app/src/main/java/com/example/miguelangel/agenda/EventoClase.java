package com.example.miguelangel.agenda;

import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by ma on 13/02/16.
 */
public class EventoClase implements Serializable{
    private String evento;
    private String localizacion;
    public EventoClase(String evento,String localizacion) {
        this.evento = evento;
        this.localizacion = localizacion;
    }
    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }
    public String getLocalizacion() {
        return localizacion;
    }
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }


}
