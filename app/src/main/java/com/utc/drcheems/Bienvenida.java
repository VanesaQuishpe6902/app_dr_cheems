package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Bienvenida
 *
 */
public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
                String estadoSesion = prefs.getString("estadoSesion", "").toString();
                Intent ventantaInicio;
                if (estadoSesion.equals("activo")) {
                    ventantaInicio = new Intent(getApplicationContext(), Dashboard.class);
                } else {
                    ventantaInicio = new Intent(getApplicationContext(), Login.class);
                }
                finish();
                startActivity(ventantaInicio);
            }
        }, 4000);
    }
}