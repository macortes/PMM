package com.example.mati.dibujarl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
    }

    class MiDibujo extends View {
        public MiDibujo(Context c){

            super(c);
        }

        protected void onDraw(Canvas lienzo){


            Paint miPincel= new Paint();

            miPincel.setColor(Color.BLUE);
           miPincel.setStyle(Paint.Style.STROKE);
            for (int i=0; i<200;i+=20){
                lienzo.drawCircle(200,500,i,miPincel);
            }

            RectF rec= new RectF(100,150,270,280);
            miPincel.setColor(Color.RED);
            miPincel.setStrokeWidth(5);
            lienzo.drawRect(rec, miPincel);



            lienzo.drawLine(10, 100, 200, 400, miPincel);

            Path paz =new Path();
            paz.moveTo(180, 680);
            paz.lineTo(200, 1000);

            paz.moveTo(200, 680);
            paz.lineTo(300, 1000);

            lienzo.drawPath(paz, miPincel);



            miPincel.setTextSize(60);
            lienzo.drawText("TEXTO ",500,700,miPincel);



        }
    }

}