package com.example.miguelangel.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecuperacionDatos extends Activity {
    ListView li;
    ArrayList<Persona> lista = new ArrayList<Persona>();
    Persona p;
    TextView res;
    private SQLiteDatabase db;
    AgendaSQLiteHelper usdbh =
            new AgendaSQLiteHelper(this, "DBAgenda", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_datos);
        LinearLayout ventana = (LinearLayout) findViewById(R.id.layout_lista);
       // ventana.setBackgroundColor(Color.BLACK);

        li = (ListView) findViewById(R.id.lista_usuarios);

        //Obtenemos las referencias a los controles
/*
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);*/

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
     //   final AgendaSQLiteHelper usdbh = new AgendaSQLiteHelper(this, "DBAgenda", null, 1);
        db = usdbh.getWritableDatabase();


        // btnConsultar.setOnClickListener(new OnClickListener() {

        // public void onClick(View v) {

        //Alternativa 1: método rawQuery()
        Cursor c = db.rawQuery("SELECT * FROM Usuarios GROUP BY Dni", null);
        //txtResultado.setText("");
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                //  id = c.getString(0);
                p = new Persona(
                        c.getInt(c.getColumnIndex("Id")),
                        c.getString(c.getColumnIndex("Nombre")),
                        c.getString(c.getColumnIndex("Apellidos")),
                        c.getString(c.getColumnIndex("Telefono")),
                        c.getString(c.getColumnIndex("Sexo")),
                        c.getString(c.getColumnIndex("Dni")),
                        c.getString(c.getColumnIndex("Provincia"))
                        );
                      /*  final String nombre = c.getString(1);
                        final String name = c.getString(2);
                        final String tel = c.getString(3);*/
                //Toast.makeText(RecuperacionDatos.this, ""+id, Toast.LENGTH_SHORT).show();
                lista.add(p);

                       /* txtResultado.append(" " + id + " - " + nombre + " - " + name + "\n");*/


                //

            } while (c.moveToNext());

        }
       // ArrayAdapter<Persona> adap = new ArrayAdapter<Persona>(getApplicationContext(),R.layout.listview, lista);
        AdaptadorPersona adap = new AdaptadorPersona(this);
        li.setAdapter(adap);

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(RecuperacionDatos.this, ListaEventosUsuario.class);
                Persona p = (Persona)li.getItemAtPosition(position);
                Toast.makeText(RecuperacionDatos.this, ""+p.getSexo()+" - "+p.getNombre(), Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putSerializable("persona", p);
                Toast.makeText(RecuperacionDatos.this, "" + p.getId(), Toast.LENGTH_SHORT).show();
                i.putExtras(b);
                startActivity(i);
            }
        });
        // Toast.makeText(RecuperacionDatos.this, ""+p.getNombre(), Toast.LENGTH_SHORT).show();

        //startActivity(i); */


    }
    class AdaptadorPersona extends ArrayAdapter<Persona>{

        public Activity context;

        public AdaptadorPersona(Activity context) {
            super(context, R.layout.listview, lista);
            this.context = context;
        }
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vista = getView(position,convertView,parent);
            return vista;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listview, null);

            TextView res= (TextView)item.findViewById(R.id.resultadoX);

            Persona p = lista.get(position);

            res.setText(p.getId() + " - " + p.getNombre() + " - " + p.getApellidos());
           // showToast(lista.get(position).getNombre());

            return (item);
        }
    }

    public void showToast(String texto){
        Toast.makeText(RecuperacionDatos.this, texto, Toast.LENGTH_SHORT).show();
    }
}
/*
       // });
   //     btn=(Button)findViewById(R.id.btnCon);
      /*  btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecuperacionDatos.this, ListaEventosUsuario.class);
                Toast.makeText(RecuperacionDatos.this, "holi" + txtResultado.getId(), Toast.LENGTH_SHORT).show();
                EditText id=(EditText)findViewById(R.id.text);

                String eo=id.getText().toString();
                int idInt = Integer.parseInt(eo);
                Persona p = usdbh.getUsuario(idInt);
                Bundle b = new Bundle();
                b.putSerializable("persona", p);
                Toast.makeText(RecuperacionDatos.this, "" + p.getId(), Toast.LENGTH_SHORT).show();
                i.putExtras(b);
                 startActivity(i);

            }
        });
   // }





//}
*/