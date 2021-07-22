package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cit_seleccionar_mascota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_seleccionar_mascota);
    }

    public void agredarDatosCita(View vista) {
        Intent irAgregarDatosCita = new Intent(getApplicationContext(), cit_agregar_citas.class);
        startActivity(irAgregarDatosCita);
    }

    public void volver(View vista) {
        finish();
    }
}