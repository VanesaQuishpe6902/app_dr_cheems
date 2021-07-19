package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Logica de Registro de Usuarios
 *
 */
public class usu_registro extends AppCompatActivity {
    EditText inputApellidoRegistro, inputNombreRegistro, inputCorreoRegistro, inputClaveRegistro, inputCelularRegistro;
    BaseDatos bdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_registro);
        //Mapear
        inputApellidoRegistro = (EditText) findViewById(R.id.inputApellidoRegistro);
        inputNombreRegistro = (EditText) findViewById(R.id.inputNombreRegistro);
        inputCorreoRegistro = (EditText) findViewById(R.id.inputCorreoRegistro);
        inputClaveRegistro = (EditText) findViewById(R.id.inputClaveRegistro);
        inputCelularRegistro = (EditText) findViewById(R.id.inputCelularRegistro);
        //Llamar base de datos
        bdd = new BaseDatos(getApplicationContext());

    }

    public void registrarUsuario(View vista) {
/*        String nombre = txtNombreRegistro.getText().toString(),
                apellido = txtApellidoRegistro.getText().toString(),
                email = txtEmailRegistro.getText().toString(),
                clave = txtClaveRegistro.getText().toString(),
                numTelefono = txtTelefonoRegistro.getText().toString();
        int error = 0;
        // Validar datos
        if (nombre.isEmpty() || !isWord(nombre)) {
            error++;
            txtNombreRegistro.setError("Debes ingresar un nombre válido");
            txtNombreRegistro.requestFocus();
        }
        if (apellido.isEmpty() || !isWord(apellido)) {
            error++;
            txtApellidoRegistro.setError("Debes ingresar un apellido válido");
            txtApellidoRegistro.requestFocus();
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            error++;
            txtEmailRegistro.setError("Debes ingresar un email válido");
            txtEmailRegistro.requestFocus();
        }
        if (clave.isEmpty() || !isPassLetterNumber(clave) || clave.length() < 8) {
            error++;
            txtClaveRegistro.setError("Su contraseña debe tener mínimo 8 caracteres entre letras y números");
            txtClaveRegistro.requestFocus();
        }
        if (numTelefono.isEmpty() || !isNumberPhone(numTelefono) || numTelefono.length() != 10) {
            error++;
            txtTelefonoRegistro.setError("Debe ingresar un número de telefono válido");
            txtTelefonoRegistro.requestFocus();
        }

        // Si no hay errores
        if (error == 0) {
            try {
                bdd.agregarUsuario(apellido, nombre, email, clave, numTelefono);
            } catch (Exception ex) {
                Toast.makeText(this, "Houston tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
            this.volverVentanaLogin(vista);
            Toast.makeText(this, "Listo! Estas registrado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Upss.. No se ha registrado tus datos", Toast.LENGTH_SHORT).show();
        }*/

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
}