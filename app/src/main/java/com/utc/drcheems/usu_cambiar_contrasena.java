package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Logica para Cambiar Contraeña de Usuarios
 */
public class usu_cambiar_contrasena extends AppCompatActivity {
    EditText inputClaveAnterior, inputClaveNueva, inputConfirmarClaveNueva;
    BaseDatos bdd;
    Cursor infoUsu;
    String idUsu;

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
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");
    }

    public void cambiarContrasena(View vista) {
        infoUsu = bdd.obtenerUsuario(idUsu);
        String claveAntiguaStr = infoUsu.getString(4).toString();
        String claveAnterior = inputClaveAnterior.getText().toString(),
                clave = inputClaveNueva.getText().toString(),
                claveRepite = inputConfirmarClaveNueva.getText().toString();
        int error = 0;
        if (!claveAnterior.equals(claveAntiguaStr)) {
            error++;
            inputClaveAnterior.setText("");
            inputClaveNueva.setText("");
            inputConfirmarClaveNueva.setText("");
            inputClaveAnterior.setError("No es tu clave anterior.");
            inputClaveAnterior.requestFocus();
        }
        if (clave.isEmpty() || !isPassLetterNumber(clave) || clave.length() < 8) {
            error++;
            inputClaveNueva.setError("Su contraseña debe tener mínimo 8 caracteres entre letras y números");
            inputClaveNueva.requestFocus();
        }
        if (!claveRepite.equals(clave)) {
            error++;
            inputConfirmarClaveNueva.setText("");
            inputConfirmarClaveNueva.setError("Las contraseñas no coinciden.");
            inputConfirmarClaveNueva.requestFocus();
        }
        // Si no hay errores
        if (error == 0) {
            try {
                bdd.actualizarClaveUsuario(idUsu, clave);
            } catch (Exception ex) {
                Toast.makeText(this, "Houston tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Listo! Datos actualizados.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Upss.. No se ha actualizado tus datos", Toast.LENGTH_SHORT).show();
        }
    }

    // validar que la contraseña tenga letras y numeros
    private boolean isPassLetterNumber(String pass) {
        return Pattern.matches(".*[a-zA-Z]+.*", pass) && Pattern.matches(".*[0-9]+.*", pass);

    }

    public void volver(View vista) {
        finish();
    }
}