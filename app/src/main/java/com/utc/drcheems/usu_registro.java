package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    EditText inputApellidoRegistro, inputNombreRegistro, inputCorreoRegistro, inputClaveRegistro, inputClaveRepiteRegistro, inputCelularRegistro;
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
        inputClaveRepiteRegistro = (EditText) findViewById(R.id.inputClaveRepiteRegistro);
        inputCelularRegistro = (EditText) findViewById(R.id.inputCelularRegistro);
        //Llamar base de datos
        bdd = new BaseDatos(getApplicationContext());

    }

    public void registrarUsuario(View vista) {
        String apellido = inputApellidoRegistro.getText().toString(),
                nombre = inputNombreRegistro.getText().toString(),
                email = inputCorreoRegistro.getText().toString(),
                clave = inputClaveRegistro.getText().toString(),
                claveRepite = inputClaveRepiteRegistro.getText().toString(),
                numTelefono = inputCelularRegistro.getText().toString();
        int error = 0;
        // Validar datos
        if (apellido.isEmpty() || containNumber(apellido)) {
            error++;
            inputApellidoRegistro.setError("Debes ingresar un apellido válido");
            inputApellidoRegistro.requestFocus();
        }
        if (nombre.isEmpty() || containNumber(nombre)) {
            error++;
            inputNombreRegistro.setError("Debes ingresar un nombre válido");
            inputNombreRegistro.requestFocus();
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            error++;
            inputCorreoRegistro.setError("Debes ingresar un email válido");
            inputCorreoRegistro.requestFocus();
        }
        if (clave.isEmpty() || !isPassLetterNumber(clave) || clave.length() < 8) {
            error++;
            inputClaveRegistro.setError("Su contraseña debe tener mínimo 8 caracteres entre letras y números");
            inputClaveRegistro.requestFocus();
        }
        if (!claveRepite.equals(clave)) {
            error++;
            inputClaveRepiteRegistro.setText("");
            inputClaveRepiteRegistro.setError("Las contraseñas no coinciden.");
            inputClaveRepiteRegistro.requestFocus();
        }
        if (numTelefono.isEmpty() || !isNumberPhone(numTelefono) || numTelefono.length() != 10) {
            error++;
            inputCelularRegistro.setError("Debe ingresar un número de telefono válido");
            inputCelularRegistro.requestFocus();
        }

        // Si no hay errores
        if (error == 0) {
            try {
                bdd.agregarUsuario(apellido, nombre, email, clave, numTelefono);
            } catch (Exception ex) {
                Toast.makeText(this, "Houston tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
            this.volverVentanaLogin(null);
            Toast.makeText(this, "Listo! Estas registrado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Upss.. No se ha registrado tus datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void volverVentanaLogin(View vista) {
        finish();
    }

    // Metodos de validacion
    private boolean isWord(String word) {
        return Pattern.matches(".*[ a-zA-Z-ñÑáéíóúÁÉÍÓÚ].*", word);
    }

    private boolean containNumber(String word) {
        return Pattern.matches(".*[0-9].*", word);
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