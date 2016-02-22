package com.example.miguelangel.agenda;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Fecha extends AppCompatActivity {
    DatePicker pickerDate;
    TextView fecha;
    Button save;
    int mes;
    private SQLiteDatabase db;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AgendaSQLiteHelper usdbh =
                new AgendaSQLiteHelper(this, "DBAgenda", null, 1);
        db=usdbh.getWritableDatabase();

        fecha = (TextView) findViewById(R.id.fecha);
        pickerDate = (DatePicker) findViewById(R.id.datePicker);
        Calendar hoy = Calendar.getInstance();
        pickerDate.init(hoy.get(Calendar.YEAR), hoy.get(Calendar.MONTH), hoy.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
                fecha.setText("Año: " + arg1 + "\n" + "Mes: " + (arg2 + 1) + "\n" + "Día: " + arg3);
                data = arg3 + "-" + arg2 + "-" + arg1;

                //   db.insert("Eventos", null, insertaEvento);


            }
        });

        save = (Button)findViewById(R.id.button3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insertar tabla Usuarios
                Bundle b = getIntent().getExtras();
                Persona p = (Persona) b.getSerializable("objeto");
                String nombre = p.getNombre();
                String apellidos = p.getApellidos();
                String dni = p.getDni();
                String telefono = p.getTelefono();
                String provincia = p.getProvSeleccionada();
                String sexo = p.getSexo();

                ContentValues inserta =new ContentValues();
                inserta.put("Dni", dni);
                inserta.put("Nombre", nombre);
                inserta.put("Apellidos", apellidos);
                inserta.put("Sexo",sexo);
                inserta.put("Telefono", telefono);
                inserta.put("Provincia", provincia);
                db.insert("Usuarios", null, inserta);


                Bundle b2=getIntent().getExtras();
                EventoClase ec=(EventoClase)b2.getSerializable("evento");
                String evento=ec.getEvento();
                String localizacion=ec.getLocalizacion();

                final ContentValues insertaEvento =new ContentValues();
                insertaEvento.put("Descripcion",evento);
                insertaEvento.put("Localizacion", localizacion);
                insertaEvento.put("Id_Usuario",p.getDni());
                //db.insert("Eventos",null,insertaEvento);



                insertaEvento.put("Fecha", data);
                db.insert("Eventos", null, insertaEvento);
                Toast.makeText(Fecha.this, "Usuario y evento creado correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Fecha.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        pickerDate.setCalendarViewShown(true);
        pickerDate.setSpinnersShown(true);
    }
}





