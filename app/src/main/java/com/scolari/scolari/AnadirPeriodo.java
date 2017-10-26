package com.scolari.scolari;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by luisangelnanez on 23/10/17.
 */

public class AnadirPeriodo extends AppCompatActivity implements View.OnClickListener{

    Button b_fechaIni,b_fechaFin;
    EditText e_fechaIni,e_fechaFin;
    private int dia,mes,anio,hora,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_periodo);


        b_fechaIni = (Button) findViewById(R.id.b_fechaIni);
        b_fechaFin = (Button) findViewById(R.id.b_fechaFin);
        e_fechaIni = (EditText) findViewById(R.id.e_fechaIni);
        e_fechaFin = (EditText) findViewById(R.id.e_fechaFin);
        b_fechaIni.setOnClickListener(this);
        b_fechaFin.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==b_fechaIni) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    e_fechaIni.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,anio);
            datePickerDialog.show();
        }
        if(v==b_fechaFin) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    e_fechaFin.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,anio);
            datePickerDialog.show();
        }
    }
}
