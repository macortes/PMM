package com.example.miguelangel.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ListaEventosUsuario extends AppCompatActivity {
    private TextView txtResultado;

    private Button eliminar;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos_usuario);
        txtResultado = (TextView) findViewById(R.id.textView9);
        AgendaSQLiteHelper usdbh = new AgendaSQLiteHelper(this, "DBAgenda", null, 1);
        Bundle b = getIntent().getExtras();
        Persona p = (Persona) b.getSerializable("persona");
        db = usdbh.getWritableDatabase();
       // Toast.makeText(ListaEventosUsuario.this, ""+p.getId(), Toast.LENGTH_SHORT).show();
        Cursor c = db.rawQuery("SELECT * FROM Eventos where Id_Usuario= '" + p.getDni()+"'", null);
        txtResultado.setText("");
        eliminar=(Button)findViewById(R.id.button4);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getIntent().getExtras();
                Persona p = (Persona) b.getSerializable("persona");
                try {
                    db.execSQL("Delete from Usuarios where Dni=" + p.getDni());
                //    Toast.makeText(ListaEventosUsuario.this, ""+p.getNombre()+" Eliminado correctamente", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }catch(Exception e){
                    Toast.makeText(ListaEventosUsuario.this, "error al eliminar: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                final String evento = c.getString(c.getColumnIndex("Descripcion"));
                final String localizacion = c.getString(c.getColumnIndex("Localizacion"));
                final String fecha=c.getString(c.getColumnIndex("Fecha"));

                txtResultado.append("Nombre  "+p.getNombre()+ "\n");
                txtResultado.append("Evento  "+evento+"\n");
                txtResultado.append("Fecha del Evento :"+fecha+ "\n");
                txtResultado.append("Lugar   "+ localizacion + "\n");

            } while (c.moveToNext());


        }




    }
}
