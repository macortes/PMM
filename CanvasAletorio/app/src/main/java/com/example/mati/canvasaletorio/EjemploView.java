package com.example.mati.canvasaletorio;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class EjemploView extends View {
    private  BitmapDrawable miImagen;
                         //  private  BitmapDrawable miImagen2;
    public EjemploView(Context contexto,AttributeSet attr){
        super(contexto,attr);
        Resources res = contexto.getResources();
        miImagen = (BitmapDrawable)res.getDrawable(R.drawable.logo_cefire);
        miImagen.setBounds(new Rect(30, 30, 200, 200));


   //     miImagen2 = (BitmapDrawable)res.getDrawable(R.drawable.ic_launcher);
     //   miImagen2.setBounds(new Rect(80,80,700,700));


    }
    protected void onDraw(Canvas canvas){
        miImagen.draw(canvas);
        //  miImagen2.draw(canvas);
    }


}

