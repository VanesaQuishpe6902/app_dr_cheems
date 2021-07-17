package com.utc.drcheems;
/* Cabecera */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class Login extends AppCompatActivity {
    // Componentes de la vista
    /*
     * TextView
     * EditText
     * ListView
     * */
// Componentes AUX
    /*
     * ArrayList
     * Cursor ;
     * */
// La base de datos
    /*
     * Llamar la bdd
     * */
// Strings [OPC]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Mapeo
        // Componentes de la vista
        // Base datos
        // Opc AUX [Traer datos de la anterior vista, GetSharePrerefences]

    }

/*    public void iniciarSesion(View vista) {
        String correo = txteEmailLogin.getText().toString(),
                clave = txtPasswordLogin.getText().toString();
        int error = 0;
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
                if (estadoSesion.isChecked()) {
                    //A traves del objeto editor establece la clave llamada estado sesion con un valor de 1
                    editor.putString("estadoSesion", "activo");
                } else {
                    editor.putString("estadoSesion", "inactivo");
                }
                // Almacenar datos
                editor.commit(); //Guardando el SharedPreferences
            } else {
                txtPasswordLogin.setText("");
                Toast.makeText(this, "Correo o contraseña erroneos", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
