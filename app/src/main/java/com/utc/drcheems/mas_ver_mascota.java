package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion de la mascota por fecha de historial
 *
 */
public class mas_ver_mascota extends AppCompatActivity {
    TextView  txtNicknameMascotaVer, txtNombreMascotaVer, txtTipoMascotaVer, txtFechaNacimientoMascotaVer, txtRasgosMascotaVer;

    BaseDatos bdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mapeo
        txtNicknameMascotaVer = (TextView) findViewById(R.id.txtNicknameMascotaVer);
        txtNombreMascotaVer = (TextView) findViewById(R.id.txtNombreMascotaVer);
        txtTipoMascotaVer = (TextView) findViewById(R.id.txtTipoMascotaVer);
        txtFechaNacimientoMascotaVer = (TextView) findViewById(R.id.txtFechaNacimientoMascotaVer);
        txtRasgosMascotaVer = (TextView) findViewById(R.id.txtRasgosMascotaVer);

        bdd = new BaseDatos(getApplicationContext());
    }
}