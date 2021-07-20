package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Descripción: agregar nuevos clientes
 *
 */
public class cli_agregar_cliente extends AppCompatActivity {
    EditText inputCedulaClienteAgregar,
            inputApellidoClienteAgregar,
            inputNombreClienteAgregar,
            inputWhastAppClienteAgregar,
            inputDireccionClienteAgregar;

    String id_usu;

    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_agregar_cliente);

        // Traer informacion del usuario
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        id_usu = prefs.getString("idUsu", "");

        inputCedulaClienteAgregar = (EditText) findViewById(R.id.inputCedulaClienteAgregar);
        inputApellidoClienteAgregar = (EditText) findViewById(R.id.inputApellidoClienteAgregar);
        inputNombreClienteAgregar = (EditText) findViewById(R.id.inputNombreClienteAgregar);
        inputWhastAppClienteAgregar = (EditText) findViewById(R.id.inputWhastAppClienteAgregar);
        inputDireccionClienteAgregar = (EditText) findViewById(R.id.inputDireccionClienteAgregar);

        bdd = new BaseDatos(getApplicationContext());

    }

    public void agregarCliente(View vista) {
        String ci = inputCedulaClienteAgregar.getText().toString();
        String apellido = inputApellidoClienteAgregar.getText().toString(),
                nombre = inputNombreClienteAgregar.getText().toString(),
                wsp = inputWhastAppClienteAgregar.getText().toString(),
                direccion = inputDireccionClienteAgregar.getText().toString();

        int error = 0;
        // Validaciones
        if (ci.equals("") || ci.length() != 10 || !containNumber(ci)) {
            error++;
            inputCedulaClienteAgregar.setError("CI inválida");
            inputCedulaClienteAgregar.requestFocus();
        }
        if (apellido.equals("") || containNumber(apellido)) {
            error++;
            inputApellidoClienteAgregar.setError("Apellido inválido");
            inputApellidoClienteAgregar.requestFocus();
        }
        if (nombre.equals("") || containNumber(nombre)) {
            error++;
            inputNombreClienteAgregar.setError("Nombre inválido");
            inputNombreClienteAgregar.requestFocus();
        }

        if (wsp.equals("") || !isNumberPhone(wsp) || wsp.length() != 10) {
            error++;
            inputWhastAppClienteAgregar.setError("Número de whatsapp inválido");
            inputWhastAppClienteAgregar.requestFocus();
        }
        if (direccion.equals("")) {
            error++;
            inputDireccionClienteAgregar.setError("Direccion obligatoria");
            inputDireccionClienteAgregar.requestFocus();
        }
        //
        if (error == 0) {
            // Almacenamos el registro
            try {
                bdd.agregarCliente(ci, apellido, nombre, wsp, direccion, id_usu);
                Toast.makeText(this, "Cliente registrado con éxito!", Toast.LENGTH_SHORT).show();
                volverListarClientes(null);
            } catch (Exception ex) {
                Toast.makeText(this, "Huston, tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Metodo para volver al la ventana ADMINISTRADOR:Listar profesore
    public void volverListarClientes(View vista) {
        finish();
    }

    // Metodos de validacion
    private boolean isWord(String word) {
        return Pattern.matches(".*[ a-zA-Z-ñÑáéíóúÁÉÍÓÚ].*", word);
    }

    private boolean containNumber(String word) {
        return Pattern.matches(".*[0-9].*", word);
    }

    // Validar que solo contenga texto y espacios
    private boolean isNumberPhone(String number) {
        return Pattern.matches("^09.*[0-9]$", number);
    }


}