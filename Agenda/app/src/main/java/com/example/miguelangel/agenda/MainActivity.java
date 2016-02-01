package com.example.miguelangel.agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] provincias= {" ESP MADRID","ESP VALENCIA","GB LONDRES"};
    private Spinner spinner;
    private EditText nombre;
    private EditText apellidos;
    private EditText telefono;
    private RadioGroup sexoRadio;
    private String sexo;
    private EditText dni;
    private String provSeleccionada;
    private CheckBox seleccion;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creacion Spinner
        spinner= (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provSeleccionada = provincias[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Recoger Datos
        sexoRadio=(RadioGroup)findViewById(R.id.radioGroup);
        sexoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioHombre) {
                    sexo ="Hombre";
                } else {
                    sexo = "Mujer";
                }
            }
        });

        nombre= (EditText)findViewById(R.id.editText);
        apellidos = (EditText)findViewById(R.id.Apellidos);
        telefono = (EditText)findViewById(R.id.Telefono);
        dni = (EditText)findViewById(R.id.dni);
















        AdaptadorProvincia adaptador = new AdaptadorProvincia(this);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String seleccionado;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, spinner.getSelectedItem() + "", Toast.LENGTH_SHORT).show();
                seleccionado = spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "" + nombre, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button botonSiguiente=(Button)findViewById(R.id.button);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPag = new Intent(getApplicationContext(),Evento.class);
                startActivity(pasarPag);
            }
        });
        seleccion= (CheckBox)findViewById(R.id.checkBox);
        seleccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked== false){
                    botonSiguiente.setEnabled(false);
                }else{
                    botonSiguiente.setEnabled(true);
                }
            }
        });







































    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
























    class AdaptadorProvincia extends ArrayAdapter<String>{
        public Activity context;
        public AdaptadorProvincia(Activity context) {
            super(context, R.layout.spinner, provincias);
            this.context = context;
        }
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vista = getView(position,convertView,parent);
            return vista;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinner, null);
            TextView Provincia= (TextView)item.findViewById(R.id.provinciaSpinner);
            Provincia.setText(provincias[position]);
            return item;
        }


    }
}
