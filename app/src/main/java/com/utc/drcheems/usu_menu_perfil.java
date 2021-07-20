package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Perfil del usuario
 *
 */
public class usu_menu_perfil extends AppCompatActivity {
    TextView txtApellidoPerfilUsuario, txtNombrePerfilUsuario, txtCorreoPerfilUsuario, txtClavePerfilUsuario, txtCelularPerfilUsuario;
    BaseDatos bdd;
    String idUsu;
    Cursor infoUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_menu_perfil);
        //Mapeo
        txtApellidoPerfilUsuario = (TextView) findViewById(R.id.txtApellidoPerfilUsuario);
        txtNombrePerfilUsuario = (TextView) findViewById(R.id.txtNombrePerfilUsuario);
        txtCorreoPerfilUsuario = (TextView) findViewById(R.id.txtCorreoPerfilUsuario);
        txtCelularPerfilUsuario = (TextView) findViewById(R.id.txtCelularPerfilUsuario);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");

        obtenerDatosUsuario();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        obtenerDatosUsuario();
    }

    public void obtenerDatosUsuario() {
        infoUsu = bdd.obtenerUsuario(idUsu);
        if (infoUsu != null) {
            txtApellidoPerfilUsuario.setText(infoUsu.getString(1).toString());
            txtNombrePerfilUsuario.setText(infoUsu.getString(2).toString());
            txtCorreoPerfilUsuario.setText(infoUsu.getString(3).toString());
            txtCelularPerfilUsuario.setText(infoUsu.getString(5).toString());
        } else {
            Toast.makeText(this, "Huston, tenemos un problema.", Toast.LENGTH_SHORT).show();
            volver(null);
        }

    }

    public void editarPerfil(View vista) {
        Intent editarPerfil = new Intent(getApplicationContext(), usu_editar_perfil.class);
        startActivity(editarPerfil);
    }

    public void volver(View vista) {
        finish();
    }
}