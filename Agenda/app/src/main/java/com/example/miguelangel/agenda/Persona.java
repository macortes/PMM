package com.example.miguelangel.agenda;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.Serializable;

/**
 * Created by ma on 10/02/16.
 */
public class Persona implements Serializable {


    private String nombre;
    private String apellidos;
    private String telefono;
    private String sexo;
    private String dni;
    private String provSeleccionada;
    private int id;


    public Persona(String nombre, String apellidos, String telefono, String sexo, String dni, String provSeleccionada) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sexo = sexo;
        this.dni = dni;
        this.provSeleccionada = provSeleccionada;
    }

    public Persona(int id, String nombre, String apellidos, String telefono, String sexo, String dni, String provSeleccionada) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.sexo = sexo;
        this.dni = dni;
        this.provSeleccionada = provSeleccionada;
    }

    public Persona() {
    }

    public String getProvSeleccionada() {
        return provSeleccionada;
    }

    public void setProvSeleccionada(String provSeleccionada) {
        this.provSeleccionada = provSeleccionada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return this.nombre;
    }
}
