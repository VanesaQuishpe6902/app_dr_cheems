package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción:
 *
 */
public class Dashboard extends AppCompatActivity {
    TextView txtNombreDashboard;
    BaseDatos bdd;
    Cursor usuario;
    String idUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Mapear
        txtNombreDashboard = (TextView) findViewById(R.id.txtNombreDashboard);
        // Base de datos
        bdd = new BaseDatos(getApplicationContext());
        // Obtener datos del Share
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");

        obtenerDatosUsuario();
    }

    public void obtenerDatosUsuario() {
        usuario = bdd.obtenerUsuario(idUsu);
        txtNombreDashboard.setText(usuario.getString(2).toString());
    }

    // Navegacion
    public void irVentanaClientes(View vista) {
        Intent ventanaClientes = new Intent(getApplicationContext(), cli_menu.class);
        startActivity(ventanaClientes);
    }

    public void irVentanaCitas(View vista) {
        Intent ventanaCitas = new Intent(getApplicationContext(), cit_menu.class);
        startActivity(ventanaCitas);
    }

    public void irVentanaComentarios(View vista) {
        Intent ventanaComentarios = new Intent(getApplicationContext(), soc_red_social.class);
        startActivity(ventanaComentarios);
    }

    public void irVentanaPerfil(View vista) {
        Intent ventanaPerfil = new Intent(getApplicationContext(), usu_menu_perfil.class);
        startActivity(ventanaPerfil);
    }

}