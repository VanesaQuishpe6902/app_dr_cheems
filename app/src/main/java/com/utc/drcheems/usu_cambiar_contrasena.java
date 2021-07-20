package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Logica para Cambiar Contraeña de Usuarios
 */
public class usu_cambiar_contrasena extends AppCompatActivity {
    EditText inputClaveAnterior, inputClaveNueva, inputConfirmarClaveNueva;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_cambiar_contrasena);
        //Mapeo
        inputClaveAnterior = (EditText) findViewById(R.id.inputClaveAnterior);
        inputClaveNueva = (EditText) findViewById(R.id.inputClaveNueva);
        inputConfirmarClaveNueva = (EditText) findViewById(R.id.inputConfirmarClaveNueva);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());
    }
}