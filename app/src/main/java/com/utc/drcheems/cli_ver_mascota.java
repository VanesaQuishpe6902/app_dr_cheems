package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    EditText inputBuscarMascota;
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
        inputBuscarMascota = (EditText) findViewById(R.id.inputBuscarMascota);
        bdd = new BaseDatos(getApplicationContext());

        obtenerMascotas();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        obtenerMascotas();
    }

    public void obtenerMascotas() {
        listaMascotas.clear();
        datosMascotas = bdd.listarMascotas(idCli);
        if (datosMascotas != null) {
            do {
                String nombreMas = datosMascotas.getString(1).toString();
                listaMascotas.add(nombreMas);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
                lstMascotas.setAdapter(adapter);
            } while (datosMascotas.moveToNext());
            listaSeleccionable();
        }
    }

    public void listaSeleccionable() {
        lstMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosMascotas.moveToPosition(position);
                Intent verMascota = new Intent(getApplicationContext(), mas_ver_mascota.class);
                verMascota.putExtra("idMas", datosMascotas.getString(0));
                startActivity(verMascota);
//                Toast.makeText(cli_ver_mascota.this, "Nombre mas: " + datosMascotas.getString(1).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buscarMascota(View vista) {
        listaMascotas.clear();
        String criterio = inputBuscarMascota.getText().toString();
        if (!criterio.equals("")) {
            datosMascotas = bdd.buscarMascota(idCli, criterio);
            if (datosMascotas != null) {
                do {
                    String nombreMas = datosMascotas.getString(1).toString();
                    listaMascotas.add(nombreMas);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
                    lstMascotas.setAdapter(adapter);
                } while (datosMascotas.moveToNext());
                listaSeleccionable();
            } else {
                Toast.makeText(this, "Debes ingresar un criterio.", Toast.LENGTH_SHORT).show();
                listaMascotas.add("Sin resultados.");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
                lstMascotas.setAdapter(adapter);
                lstMascotas.setOnItemClickListener(null);
            }
        } else {
            Toast.makeText(this, "Debes ingresar un criterio.", Toast.LENGTH_SHORT).show();
            obtenerMascotas();
        }
    }

    public void volver(View vista) {
        finish();
    }

    public void agregarMascota(View vista) {
        Intent agregarMascota = new Intent(getApplicationContext(), mas_agregar_mascota.class);
        agregarMascota.putExtra("idCli", idCli);
        startActivity(agregarMascota);
    }
}