package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_menu_perfil);
        //Mapeo
        txtApellidoPerfilUsuario = (TextView) findViewById(R.id.txtApellidoPerfilUsuario);
        txtNombrePerfilUsuario = (TextView) findViewById(R.id.txtNombrePerfilUsuario);
        txtCorreoPerfilUsuario = (TextView) findViewById(R.id.txtCorreoPerfilUsuario);
        txtClavePerfilUsuario= (TextView) findViewById(R.id.txtClavePerfilUsuario);
        txtCelularPerfilUsuario = (TextView) findViewById(R.id.txtCelularPerfilUsuario);


        //Base de datos
        bdd = new BaseDatos(getApplicationContext());
    }
}