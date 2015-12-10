package com.example.mati.transicion;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ImageView imagen = new ImageView(this);

        setContentView(R.layout.activity_main);
        TransitionDrawable mi_transicion = (TransitionDrawable)
                getResources().getDrawable(R.drawable.transicion);
        ImageView imagen= (ImageView)findViewById(R.id.foto);
        imagen.setImageDrawable(mi_transicion);
        mi_transicion.startTransition(2000);
    }
}
