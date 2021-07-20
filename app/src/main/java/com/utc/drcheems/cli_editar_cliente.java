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

    String idCli;
    Cursor infoCli;

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
        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCli = parametrosExtra.getString("idCli");
                colocarDatos();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }

    }

    public void colocarDatos() {
        try {
            infoCli = bdd.verClientes(idCli);

            txtIdClienteEditar.setText(infoCli.getString(0));
            inputCedulaClienteEditar.setText(infoCli.getString(1));
            inputApellidoClienteEditar.setText(infoCli.getString(2));
            inputNombreClienteEditar.setText(infoCli.getString(3));
            inputWhastAppClienteEditar.setText(infoCli.getString(4));
            inputDireccionClienteEditar.setText(infoCli.getString(5));

        } catch (Exception ex) {
            Toast.makeText(this, "No se pudo colocar los datos. " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    public void editarCliente(View vista) {
        String ci = inputCedulaClienteEditar.getText().toString(),
                apellido = inputApellidoClienteEditar.getText().toString(),
                nombre = inputNombreClienteEditar.getText().toString(),
                wsp = inputWhastAppClienteEditar.getText().toString(),
                direccion = inputDireccionClienteEditar.getText().toString();
        int error = 0;
        // Validaciones
        if (ci.equals("") || ci.length() != 10) {
            error++;
            inputCedulaClienteEditar.setError("CI inválida");
            inputCedulaClienteEditar.requestFocus();
        }
        if (nombre.equals("") || !isWord(nombre)) {
            error++;
            inputNombreClienteEditar.setError("Nombre inválido");
            inputNombreClienteEditar.requestFocus();
        }
        if (apellido.equals("") || !isWord(apellido)) {
            error++;
            inputApellidoClienteEditar.setError("Apellido inválido");
            inputApellidoClienteEditar.requestFocus();
        }

        if (wsp.equals("") || !isNumberPhone(wsp) || wsp.length() != 10) {
            error++;
            inputWhastAppClienteEditar.setError("Número de whatsapp inválido");
            inputWhastAppClienteEditar.requestFocus();
        }
        if (direccion.equals("")) {
            error++;
            inputDireccionClienteEditar.setError("Direccion obligatoria");
            inputDireccionClienteEditar.requestFocus();
        }
        //
        if (error == 0) {
            // Almacenamos el registro
            try {
                bdd.actualizarCliente(idCli, ci, apellido, nombre, wsp, direccion);
                volver(null);
                Toast.makeText(this, "Información actualizada con éxito!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Huston, tenemos un problema...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Metodo para volver al la ventana ADMINISTRADOR:Listar profesore
    public void volver(View vista) {
        finish();//cerrando ventana de nueva venta
    }

    // Metodos de validacion
    private boolean isWord(String word) {
        return Pattern.matches(".*[ a-zA-Z-ñÑáéíóúÁÉÍÓÚ].*", word) && !Pattern.matches(".*[0-9].*", word);
    }

    // Validar que solo contenga texto y espacios
    private boolean isNumberPhone(String number) {
        return Pattern.matches("^09.*[0-9]$", number);
    }


}