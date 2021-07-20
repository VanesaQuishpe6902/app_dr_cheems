package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Editar un cliente existente
 *
 */
public class cli_editar_cliente extends AppCompatActivity {
    //String idCliente, apellido, nombre, whastapp, direccion;
    //Cursor infoCliente;
    TextView txtIdClienteEditar;
    EditText inputCedulaClienteEditar, inputApellidoClienteEditar, inputNombreClienteEditar, inputWhastAppClienteEditar, inputDireccionClienteEditar;
    BaseDatos bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_editar_cliente);
        //Mapeo
        txtIdClienteEditar = (TextView) findViewById(R.id.txtIdClienteEditar);
        inputCedulaClienteEditar = (EditText) findViewById(R.id.inputCedulaClienteEditar);
        inputApellidoClienteEditar = (EditText) findViewById(R.id.inputApellidoClienteEditar);
        inputNombreClienteEditar = (EditText) findViewById(R.id.inputNombreClienteEditar);
        inputWhastAppClienteEditar = (EditText) findViewById(R.id.inputWhastAppClienteEditar);
        inputDireccionClienteEditar = (EditText) findViewById(R.id.inputDireccionClienteEditar);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

    }

/*    public void editarCliente(View vista) {
        String id = "",
                ci = inputCiRegistroCliente.getText().toString(),
                apellido = txtApellidoRegistrarProfesor.getText().toString(),
                nombre = txtNombreRegistrarProfesor.getText().toString(),
                wsp = txtClaveRegistrarProfesor.getText().toString(),
                direccion = txtClaveConfirmarRegistrarProfesor.getText().toString();
        int error = 0;
        // Validaciones
        if (ci.equals("") || ci.length() != 10) {
            error++;
            inputCiRegistroCliente.setError("CI inválida");
            inputCiRegistroCliente.requestFocus();
        }
        if (nombre.equals("") || !isWord(nombre)) {
            error++;
            txtNombreRegistrarProfesor.setError("Nombre inválido");
            txtNombreRegistrarProfesor.requestFocus();
        }
        if (apellido.equals("") || !isWord(apellido)) {
            error++;
            txtApellidoRegistrarProfesor.setError("Apellido inválido");
            txtApellidoRegistrarProfesor.requestFocus();
        }

        if (wsp.equals("") || !isNumberPhone(wsp)) {
            error++;
            txtTelefonoRegistrarProfesor.setError("Número de whatsapp inválido");
            txtTelefonoRegistrarProfesor.requestFocus();
        }
        if (direccion.equals("")) {
            error++;
            txtClaveConfirmarRegistrarProfesor.setError("Direccion obligatoria");
            txtClaveConfirmarRegistrarProfesor.requestFocus();
        }
        //
        if (error == 0) {
            // Almacenamos el registro
            try {
                bdd.actualizarCliente(id, ci, apellido, nombre, wsp, direccion);
                volverListarProfesores(null);
                Toast.makeText(this, "Cliente registrado con éxito!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Huston, tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    //Metodo para volver al la ventana ADMINISTRADOR:Listar profesore
    public void volverListarProfesores(View vista) {
        finish();//cerrando ventana de nueva venta
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