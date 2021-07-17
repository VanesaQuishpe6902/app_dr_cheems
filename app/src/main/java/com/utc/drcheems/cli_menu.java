package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción:
 *
 */
public class cli_menu extends AppCompatActivity {
    BaseDatos bdd;
    EditText txtBuscarProfesor;
    ListView lstProfesores;
    ArrayList<String> listaProfesores = new ArrayList<>();
    Cursor datosProfesores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_menu);

        bdd = new BaseDatos(getApplicationContext());
        txtBuscarProfesor = (EditText) findViewById(R.id.txtBuscarProfesor);
        lstProfesores = (ListView) findViewById(R.id.lstProfesores);
        obtenerDatosClientes();
    }

    public void obtenerDatosClientes() {
        listaProfesores.clear();
        datosProfesores = bdd.listarProfesores();
        if (datosProfesores != null) {
            do {
                String id = datosProfesores.getString(0),
                        nombre = datosProfesores.getString(2),
                        apellido = datosProfesores.getString(1);
                listaProfesores.add(apellido + " " + nombre);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProfesores);
                lstProfesores.setAdapter(adapter);
            } while (datosProfesores.moveToNext());
        } else {
            Toast.makeText(this, "Sin registros aun", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarCliente(View vista) {
        String buscador = txtBuscarProfesor.getText().toString();
        if (!buscador.equals("")) {
            listaProfesores.clear();
            datosProfesores = bdd.buscarProfesoresNombre(buscador);
            if (datosProfesores != null) {
                do {
                    String id = datosProfesores.getString(0),
                            nombre = datosProfesores.getString(2),
                            apellido = datosProfesores.getString(1);
                    listaProfesores.add(apellido + " " + nombre);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProfesores);
                    lstProfesores.setAdapter(adapter);
                } while (datosProfesores.moveToNext());
            } else {
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            }
        } else {
            obtenerDatosProfesor();
        }
    }
}