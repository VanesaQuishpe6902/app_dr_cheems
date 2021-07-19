package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion Cliente acceso a editar cliente e  infomacion mascotas
 *
 */
public class cli_ver_cliente extends AppCompatActivity {

    TextView  txtCedulaClienteVer, txtApellidoClienteVer, txtNombreClienteVer, txtWhastAppClienteVer,txtDireccionClienteVer;
    Button btn_EditarClienteVer, btn_MascotasClienteVer;

    BaseDatos bdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_ver_cliente);

        txtCedulaClienteVer = (TextView) findViewById(R.id.txtCedulaClienteVer);
        txtApellidoClienteVer = (TextView) findViewById(R.id.txtApellidoClienteVer);
        txtNombreClienteVer = (TextView) findViewById(R.id.txtNombreMascotaVer);
        txtWhastAppClienteVer= (TextView) findViewById(R.id.txtWhastAppClienteVer);
        txtDireccionClienteVer = (TextView) findViewById(R.id.txtDireccionClienteVer);

        // Mapear


    }
}