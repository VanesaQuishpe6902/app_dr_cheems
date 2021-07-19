package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Editar Usuario
 *
 */
public class usu_editar_perfil extends AppCompatActivity {
    TextView txtIdUsuarioEditar;
    EditText inputApellidoUsuarioEditar, inputNombreUsuarioEditar, inputCorreoUsuarioEditar, inputCelularUsuarioEditar;
    Button btn_CambiarClaveUsuarioEditar;
    BaseDatos bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_editar_perfil);
        //Mapeo
        txtIdUsuarioEditar = (TextView) findViewById(R.id.txtIdUsuarioEditar);
        inputApellidoUsuarioEditar = (EditText) findViewById(R.id.inputApellidoUsuarioEditar);
        inputNombreUsuarioEditar = (EditText) findViewById(R.id.inputNombreUsuarioEditar);
        inputCorreoUsuarioEditar = (EditText) findViewById(R.id.inputCorreoUsuarioEditar);
        inputCelularUsuarioEditar = (EditText) findViewById(R.id.inputCelularUsuarioEditar);
        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

    }
}