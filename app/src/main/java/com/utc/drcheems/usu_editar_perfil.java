package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

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
            inputCelularUsuarioEditar.setText(infoUsu.getString(5).toString());
        } else {
            Toast.makeText(this, "Huston, tenemos un problema.", Toast.LENGTH_SHORT).show();
            volver(null);
        }

    }

    public void cambiarClave(View vista) {
        Intent irCambiarClave = new Intent(getApplicationContext(), usu_cambiar_contrasena.class);
        volver(null);
        startActivity(irCambiarClave);
    }


    public void actualizarUsuario(View vista) {
        String apellido = inputApellidoUsuarioEditar.getText().toString(),
                nombre = inputNombreUsuarioEditar.getText().toString(),
                email = inputCorreoUsuarioEditar.getText().toString(),
                numTelefono = inputCelularUsuarioEditar.getText().toString();
        int error = 0;
        // Validar datos
        if (apellido.isEmpty() || !isWord(apellido)) {
            error++;
            inputApellidoUsuarioEditar.setError("Debes ingresar un apellido válido");
            inputApellidoUsuarioEditar.requestFocus();
        }
        if (nombre.isEmpty() || !isWord(nombre)) {
            error++;
            inputNombreUsuarioEditar.setError("Debes ingresar un nombre válido");
            inputNombreUsuarioEditar.requestFocus();
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            error++;
            inputCorreoUsuarioEditar.setError("Debes ingresar un email válido");
            inputCorreoUsuarioEditar.requestFocus();
        }
        if (numTelefono.isEmpty() || !isNumberPhone(numTelefono) || numTelefono.length() != 10) {
            error++;
            inputCelularUsuarioEditar.setError("Debe ingresar un número de telefono válido");
            inputCelularUsuarioEditar.requestFocus();
        }

        // Si no hay errores
        if (error == 0) {
            try {
                bdd.actualizarUsuario(idUsu, apellido, nombre, email, numTelefono);
                volver(null);
            } catch (Exception ex) {
                Toast.makeText(this, "Houston tenemos un problema...", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Listo! Datos actualizados.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Upss.. No se ha actualizado tus datos", Toast.LENGTH_SHORT).show();
        }

    }

    // Metodos de validacion
    private boolean isWord(String word) {
        return Pattern.matches(".*[ a-zA-Z-ñÑáéíóúÁÉÍÓÚ].*", word);
    }

    public boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    // Validar que solo contenga texto y espacios
    private boolean isNumberPhone(String number) {
        return Pattern.matches("^09.*[0-9]$", number);
    }

    // validar que la contraseña tenga letras y numeros
    private boolean isPassLetterNumber(String pass) {
        return Pattern.matches(".*[a-zA-Z]+.*", pass) && Pattern.matches(".*[0-9]+.*", pass);

    }

    public void volver(View vista) {
        finish();
    }
}