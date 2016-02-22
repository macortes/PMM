package com.example.miguelangel.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Evento extends AppCompatActivity {
    private EditText evento;
    private EditText localizacion;
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        evento= (EditText)findViewById(R.id.editText10);
        localizacion=(EditText)findViewById(R.id.editText11);
        boton = (Button)findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPag = new Intent(getApplicationContext(), Fecha.class);
                Bundle b = getIntent().getExtras();
                Persona p = (Persona) b.getSerializable("objeto");

                //PASO POR PARAMETRO LOS DOS OBJETOS
                Bundle b2 = new Bundle();
                String event=evento.getText().toString();
                String localiza=localizacion.getText().toString();
                EventoClase ec=new EventoClase(event,localiza);
                b.putSerializable("objeto", p);
                b.putSerializable("evento",ec);
                //    b.putSerializable("ob",p);
                pasarPag.putExtras(b);
                startActivity(pasarPag);
            }
        });
    }

}
