package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion de cita
 *
 */
public class cit_ver_citas extends AppCompatActivity {
    TextView txtFechaCitaVer, txtServicioCitaVer, txtSolucionCitaVer;
    BaseDatos bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_ver_citas);
        //Mapeo
//        txtFechaCitaVer = (TextView) findViewById(R.id.txtFechaCitaVer);
        txtServicioCitaVer = (TextView) findViewById(R.id.txtServicioCitaVer);
        txtSolucionCitaVer = (TextView) findViewById(R.id.txtSolucionCitaVer);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());


    }
}