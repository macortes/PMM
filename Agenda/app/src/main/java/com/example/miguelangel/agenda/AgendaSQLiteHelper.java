package com.example.miguelangel.agenda;

/**
 * Created by ma on 13/02/16.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaSQLiteHelper  extends SQLiteOpenHelper{
    //Crear la tabla Usuarios
    String t_Usuarios = "CREATE TABLE IF NOT EXISTS Usuarios(Id INTEGER PRIMARY KEY, Dni TEXT,Nombre TEXT,Apellidos TEXT,Sexo TEXT,Telefono TEXT," +
            "Provincia TEXT)";
    //Crear la Tabla Eventos
    String t_Eventos= "CREATE TABLE IF NOT EXISTS Eventos(Id INTEGER PRIMARY KEY ," +
            "Localizacion INTEGER," +
            "Descripcion TEXT," +
            "Fecha TEXT," +
         //   "Hora NUMERIC," +
            "Id_Usuario TEXT, FOREIGN KEY(Id_Usuario) REFERENCES Usuarios(Dni))";

    public AgendaSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(t_Usuarios);
        db.execSQL(t_Eventos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Eventos");
        //Se crea la nueva versión de la tabla
        db.execSQL(t_Usuarios);

        //Se crea la nueva versión de la tabla
        db.execSQL(t_Eventos);
    }
    public Persona getUsuario(int id){
        String query = "SELECT * FROM Usuarios WHERE id = "+id;
        SQLiteDatabase sqLiteHelper = this.getReadableDatabase();
        Cursor c = sqLiteHelper.rawQuery(query, null);
        Persona p = new Persona();
        if (c.moveToFirst()){
            do {
                p.setId(c.getInt(c.getColumnIndex("Id")));
                p.setNombre(c.getString(c.getColumnIndex("Nombre")));
                p.setApellidos(c.getString(c.getColumnIndex("Apellidos")));
                p.setTelefono(c.getString(c.getColumnIndex("Telefono")));
                p.setDni(c.getString(c.getColumnIndex("Dni")));
                p.setSexo(c.getString(c.getColumnIndex("Sexo")));
                p.setProvSeleccionada(c.getString(c.getColumnIndex("Provincia")));
            }while(c.moveToNext());
        }
        return p;
    }
}
