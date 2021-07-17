package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Logica para agregar mascotas
 *
 */

public class mas_agregar_mascota extends AppCompatActivity {

    EditText inputNicknameMascotaAgregar, inputNombreMascotaAgregar, inputTipoMascotaAgregar, inputRasgosMascotaAgregar;
    Button btn_FechaNacimientoMascotaAgregar;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_agregar_mascota);
        //Mapear
        inputNicknameMascotaAgregar = (EditText) findViewById(R.id.inputNickNameMascotaAgregar);
        inputNombreMascotaAgregar = (EditText) findViewById(R.id.inputNombreMascotaAgregar);
        inputTipoMascotaAgregar = (EditText) findViewById(R.id.inputTipoMascotaAgregar);
        inputRasgosMascotaAgregar = (EditText) findViewById(R.id.inputTipoMascotaAgregar);

        btn_FechaNacimientoMascotaAgregar = (Button) findViewById(R.id.btn_FechaNacimientoMascotaAgregar);

        //Llamar Base Datos
        bdd = new BaseDatos(getApplicationContext());

    }
}