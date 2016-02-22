package com.example.miguelangel.agenda;

/**
 * Created by ma on 13/02/16.
 */
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class BasesDatos extends Activity
{

 /* private String Dni;
    private String Nombre;
    private String Apellidos;
    private String Telefono;
    private String Provincia;
    private String Sexo;

    private int id=1;
    private String descripcion;
    private String Localizacion;
    private int Fecha;
    private String id_usuari;*/

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bases_datos);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        AgendaSQLiteHelper usdbh =
                new AgendaSQLiteHelper(this, "DBAgenda", null, 1);
      //  Toast.makeText(BasesDatos.this, "BASE DAROS CREAD  CON EXITO" , Toast.LENGTH_SHORT).show();

        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null)
        {
        //Cerramos la base de datos
            db.close();
        }
        //Si hemos abierto correctamente la base de datos
        //Cerramos la base de datos
        db.close();

    }
}