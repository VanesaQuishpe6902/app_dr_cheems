package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Ver mascotas de cliente o agregar
 */
public class cli_ver_mascota extends AppCompatActivity {
    BaseDatos bdd;
    String idCli;

    Cursor datosMascotas;
    ListView lstMascotas;
    ArrayList<String> listaMascotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_ver_mascota);
        //
        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCli = parametrosExtra.getString("idCli");
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }

        lstMascotas = (ListView) findViewById(R.id.lstMascotasCliente);
        bdd = new BaseDatos(getApplicationContext());

        obtenerMascotas();
    }

    public void obtenerMascotas() {
        listaMascotas.clear();
        datosMascotas = bdd.listarMascotas(idCli);
    }
}