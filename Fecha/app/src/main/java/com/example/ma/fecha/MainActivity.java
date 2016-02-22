package com.example.ma.fecha;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RadioGroup groupBtn;

    DatePicker pickerDate;
    TextView fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = (TextView) findViewById(R.id.fecha);


        pickerDate = (DatePicker) findViewById(R.id.pickerdate);
        Calendar hoy = Calendar.getInstance();
        pickerDate.init(hoy.get(Calendar.YEAR), hoy.get(Calendar.MONTH), hoy.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
                // ADVERTENCIA ... los meses van de 0 a 11 ... hay que sumar 1
                fecha.setText("Año: " + arg1 + "\n" + "Mes: " + (arg2 + 1) + "\n" + "Día: " + arg3);
            }
        });
    }

            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                    pickerDate.setCalendarViewShown(true);
                    pickerDate.setSpinnersShown(true);
                }

        }
