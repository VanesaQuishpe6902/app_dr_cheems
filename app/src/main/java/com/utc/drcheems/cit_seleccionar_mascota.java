package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class cit_seleccionar_mascota extends AppCompatActivity {
    EditText inputNombreMascotaBuscar;
    ListView lstMascotasNuevaCitas;
    ArrayList<String> listaMascotas = new ArrayList<>();
    Cursor datosMascotas;
    BaseDatos bdd;
    String idUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_seleccionar_mascota);
        //
        inputNombreMascotaBuscar = (EditText) findViewById(R.id.inputNombreMascotaBuscar);
        lstMascotasNuevaCitas = (ListView) findViewById(R.id.lstMascotasNuevaCitas);

        bdd = new BaseDatos(getApplicationContext());

        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");

        obtenerMascotas();
    }

    private void obtenerMascotas() {
        listaMascotas.clear();
        datosMascotas = bdd.listarMascotasUsuario(idUsu);
        if (datosMascotas != null) {
            do {
                String nombreMas = datosMascotas.getString(1).toString();
                listaMascotas.add(nombreMas);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
                lstMascotasNuevaCitas.setAdapter(adapter);
            } while (datosMascotas.moveToNext());
            listaSeleccionable();
        }
    }

    public void obtenerMascotasBuscar(View vista) {
        String criterio = inputNombreMascotaBuscar.getText().toString();
        listaMascotas.clear();
        datosMascotas = bdd.listarMascotasUsuarioBuscar(idUsu, criterio);
        if (datosMascotas != null) {
            do {
                String nombreMas = datosMascotas.getString(1).toString();
                listaMascotas.add(nombreMas);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
                lstMascotasNuevaCitas.setAdapter(adapter);
            } while (datosMascotas.moveToNext());
            listaSeleccionable();
        } else {
            listaMascotas.add("No se encontro resultados. Pulsa para volver a listar");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMascotas);
            lstMascotasNuevaCitas.setAdapter(adapter);
            lstMascotasNuevaCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    obtenerMascotas();
                }
            });
        }
    }

    public void listaSeleccionable() {
        lstMascotasNuevaCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosMascotas.moveToPosition(position);
                Intent irAgregarDatosCita = new Intent(getApplicationContext(), cit_agregar_citas.class);
                irAgregarDatosCita.putExtra("idMas", datosMascotas.getString(0));
                startActivity(irAgregarDatosCita);
//                Toast.makeText(cit_seleccionar_mascota.this, "Nombre mas: " + datosMascotas.getString(1).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agredarDatosCita(View vista) {
        Intent irAgregarDatosCita = new Intent(getApplicationContext(), cit_agregar_citas.class);
        startActivity(irAgregarDatosCita);
    }

    public void volver(View vista) {
        finish();
    }
}