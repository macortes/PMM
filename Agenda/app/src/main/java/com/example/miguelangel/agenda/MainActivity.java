package com.example.miguelangel.agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
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
    //Spinner
    private Spinner spinner;
    //nombre
    private EditText nombre;
    //apellidos
    private EditText apellidos;
    //telefono
    private EditText telefono;
    //sexo
    private RadioGroup sexoRadio;
    //dni y clave principal
    private EditText dni;
    //provincia
    public String provSeleccionada;
    //Sexo seleccionado
    public String sexo;
    //Confirmacion
    private CheckBox seleccion;
    //boton siguiente
    private Button boton;
    private String seleccionado;
    //ver eventos
    private Button evento;

    private TextInputLayout til1;
    private TextInputLayout til2;
    TextView tv;

    //Comienza
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        registerForContextMenu(toolbar);
        setSupportActionBar(toolbar);
        til1=(TextInputLayout)findViewById(R.id.til1);
        til2=(TextInputLayout)findViewById(R.id.til2);
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
        //Spinner
        AdaptadorProvincia adaptador = new AdaptadorProvincia(this);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(MainActivity.this, spinner.getSelectedItem() + "", Toast.LENGTH_SHORT).show();
                seleccionado = spinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, "" + nombre, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Pasar pag y objeto
        final Button botonSiguiente=(Button)findViewById(R.id.button);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPag = new Intent(getApplicationContext(), Evento.class);
                Bundle b = new Bundle();
                //PARAMETROS A  PASAR
                String name = nombre.getText().toString();

                String surname = apellidos.getText().toString();
                String phone = telefono.getText().toString();
                String card = dni.getText().toString();
                    //validacion de campos vacios
                if (name.equals("") && surname.equals("")&& card.equals("")&& phone.equals("")) {
                    Toast.makeText(MainActivity.this, "Debes rellernar todos los campos", Toast.LENGTH_SHORT).show();
            /*    Toast.makeText(MainActivity.this, ""+name, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+surname, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+phone, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+card, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+seleccionado, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+sexo, Toast.LENGTH_SHORT).show();*/
                } else {
                    Persona p = new Persona(name, surname, phone, sexo, card, provSeleccionada);


                    b.putSerializable("objeto", p);
                    pasarPag.putExtras(b);
                    startActivity(pasarPag);

                }
            }
        });
        seleccion= (CheckBox)findViewById(R.id.checkBox);
        seleccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == false) {
                    botonSiguiente.setEnabled(false);
                } else {
                    botonSiguiente.setEnabled(true);
                }
            }
        });
        evento =(Button)findViewById(R.id.eventos);
        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VerBaseDatos = new Intent(getApplicationContext(),RecuperacionDatos.class);


                startActivity(VerBaseDatos);
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
    //Menu contextual
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cxt_etiqueta, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        // Previamente creamos el objeto TextView y lo inicializamos para poder
        // asignarle aquí el texto en función de la opción seleccionada.
        switch (item.getItemId()) {
            case R.id.canvas:
                Intent i =new Intent(this,Dibujo.class);
                startActivity(i);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    //spinner
    class AdaptadorProvincia extends ArrayAdapter<String> {
        public Activity context;

        public AdaptadorProvincia(Activity context) {
            super(context, R.layout.spinner, provincias);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View vista = getView(position, convertView, parent);
            return vista;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinner, null);
            TextView Provincia = (TextView) item.findViewById(R.id.provinciaSpinner);
            Provincia.setText(provincias[position]);
            return item;
        }
    }
}
