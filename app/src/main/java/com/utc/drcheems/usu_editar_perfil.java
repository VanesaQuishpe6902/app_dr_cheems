package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    String idUsu;
    Cursor infoUsu;

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

        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");

        obtenerDatosUsuario();
    }

    public void obtenerDatosUsuario() {
        infoUsu = bdd.obtenerUsuario(idUsu);
        if (infoUsu != null) {
            txtIdUsuarioEditar.setText(infoUsu.getString(0).toString());
            inputApellidoUsuarioEditar.setText(infoUsu.getString(1).toString());
            inputNombreUsuarioEditar.setText(infoUsu.getString(2).toString());
            inputCorreoUsuarioEditar.setText(infoUsu.getString(3).toString());
            inputCelularUsuarioEditar.setText(infoUsu.getString(4).toString());
        } else {
            Toast.makeText(this, "Huston, tenemos un problema.", Toast.LENGTH_SHORT).show();
            volver(null);
        }

    }

    public void volver(View vista) {
        finish();
    }
}