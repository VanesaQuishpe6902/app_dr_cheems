package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 16/07/2021
 * Editado: 16/07/2021
 * Descripción: Creacion de Login
 *
 */

public class Login extends AppCompatActivity {
    EditText inputCorreoLogin, inputClaveLogin;
    CheckBox chbxSesion;
    Cursor infoUser;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Mapeo
        inputCorreoLogin = (EditText) findViewById(R.id.inputCorreoLogin);
        inputClaveLogin = (EditText) findViewById(R.id.inputClaveLogin);
        chbxSesion = (CheckBox) findViewById(R.id.chbxSesion);

        // Llamar a la Base datos
        bdd = new BaseDatos(getApplicationContext());

    }

    public void iniciarSesion(View vista) {
        String correo = inputCorreoLogin.getText().toString(),
                clave = inputClaveLogin.getText().toString();
        if (!correo.isEmpty() && !clave.isEmpty()) {
            infoUser = bdd.iniciarSesionUsuario(correo, clave);
            if (infoUser != null) {
                // Obtener el ID del usuario
                String idUsu = infoUser.getString(0).toString();
                // Guardar en un sharePreference
                //instancia de clase y objeto por medio del metodo, nombre del archivo de preferencia
                SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
                //A traves del la clase SharedPreferences y del metodo de clase editor se instancia un objeto editor donde se editan las preferencias
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("idUsu", idUsu);

                // Si se desea mantener la sesion abierta
                if (chbxSesion.isChecked()) {
                    //A traves del objeto editor establece la clave llamada estado sesion con un valor de 1
                    editor.putString("estadoSesion", "activo");
                } else {
                    editor.putString("estadoSesion", "inactivo");
                }
                // Almacenar datos
                editor.commit(); //Guardando el SharedPreferences
                // Enviar al dashboard
                Intent abrirDashboard = new Intent(getApplicationContext(), Dashboard.class);
                finish();
                startActivity(abrirDashboard);
            } else {
                inputClaveLogin.setText("");
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes ingresar tu correo y contraseña!", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarse(View vista) {
        Intent abrirRegistro = new Intent(getApplicationContext(), usu_registro.class);
        startActivity(abrirRegistro);
    }
}
