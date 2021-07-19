package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción:
 *
 */
public class cit_agregar_citas extends AppCompatActivity {
    EditText inputServicioAgregarCita;
    Button btn_SeleccionarFechaCitaAgregar, btn_AgregarCita;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_agregar_citas);

        inputServicioAgregarCita = (EditText) findViewById(R.id.inputServicioAgregarCita);
        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

    }
}