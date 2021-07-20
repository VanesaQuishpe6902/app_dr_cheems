package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion Cliente acceso a editar cliente e  infomacion mascotas
 *
 */
public class cli_ver_cliente extends AppCompatActivity {

    TextView txtCedulaClienteVer, txtApellidoClienteVer, txtNombreClienteVer, txtWhastAppClienteVer, txtDireccionClienteVer;

    BaseDatos bdd;
    String idCli;
    Cursor infoCli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_ver_cliente);

        txtCedulaClienteVer = (TextView) findViewById(R.id.txtCedulaClienteVer);
        txtApellidoClienteVer = (TextView) findViewById(R.id.txtApellidoClienteVer);
        txtNombreClienteVer = (TextView) findViewById(R.id.txtNombreClienteVer);
        txtWhastAppClienteVer = (TextView) findViewById(R.id.txtWhastAppClienteVer);
        txtDireccionClienteVer = (TextView) findViewById(R.id.txtDireccionClienteVer);

        // Mapear
        bdd = new BaseDatos(getApplicationContext());

        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCli = parametrosExtra.getString("idCli");
                // Colocar datos
                colocarDatos();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void colocarDatos() {
        try {
            infoCli = bdd.verClientes(idCli);

            txtCedulaClienteVer.setText(infoCli.getString(1).toString());
            txtApellidoClienteVer.setText(infoCli.getString(2).toString());
            txtNombreClienteVer.setText(infoCli.getString(3).toString());
            txtWhastAppClienteVer.setText(infoCli.getString(4).toString());
            txtDireccionClienteVer.setText(infoCli.getString(5).toString());
        } catch (Exception ex) {
            Toast.makeText(this, "No se encontro el registro: " + ex, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        colocarDatos();
    }

    public void volver(View vista) {
        finish();
    }

    public void editarCliente(View vista) {
        Intent editarCliente = new Intent(getApplicationContext(), cli_editar_cliente.class);
        editarCliente.putExtra("idCli", idCli);
        startActivity(editarCliente);
    }

    public void verMascotas(View vista) {
        Intent verMascotas = new Intent(getApplicationContext(), cli_ver_mascota.class);
        verMascotas.putExtra("idCli", idCli);
        startActivity(verMascotas);
    }
}


