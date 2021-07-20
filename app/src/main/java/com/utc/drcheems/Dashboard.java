package com.utc.drcheems;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onRestart() {
        super.onRestart();
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

    public void verVersionApp(View vista) {
        Toast.makeText(this, "Version App Dr.Cheems 1.0.0", Toast.LENGTH_SHORT).show();
    }

    public void cerrarSesion(View vista) {
        Intent irLogin = new Intent(getApplicationContext(), Login.class);
        // Borrar datos del sharepreferences
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("estadoSesion", "");
        editor.putString("idUsu", "");
        editor.commit();

        finish();
        startActivity(irLogin);
    }

    public void verSitioWeb(View vista) {
        String urlSitio = getString(R.string.url_site_web).toString();
        Uri uri = Uri.parse(urlSitio);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}