package com.example.mati.trestiposbotones;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context ctx=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button botonNormal = (Button) findViewById(R.id.boton1);
        ctx=this;
        botonNormal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(ctx, "Pulsado boton uno", Toast.LENGTH_SHORT).show();
            }
        });
    }
   public void cmdDos_click(View v){
        Toast.makeText(this, "Pulsado boton Dos",
        Toast.LENGTH_SHORT).show();
    }

}
