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

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción:
 *
 */
public class cli_menu extends AppCompatActivity {

    BaseDatos bdd;
    EditText inputBuscarCliente;
    ListView lstClientes;
    ArrayList<String> listaClientes = new ArrayList<>();
    Cursor datosClientes;
    String id_usu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_menu);

        inputBuscarCliente = (EditText) findViewById(R.id.inputBuscarCliente);
        lstClientes = (ListView) findViewById(R.id.lstClientes);

        bdd = new BaseDatos(getApplicationContext());
        // Traer informacion del usuario
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        id_usu = prefs.getString("idUsu", "");

        obtenerDatosClientes();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        obtenerDatosClientes();
    }

    public void obtenerDatosClientes() {
        listaClientes.clear();
        try {
            datosClientes = bdd.listarClientes(id_usu);
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
        if (datosClientes != null) {
            do {
                String id = datosClientes.getString(0),
                        nombre = datosClientes.getString(3),
                        apellido = datosClientes.getString(2);
                listaClientes.add(apellido + " " + nombre);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
                lstClientes.setAdapter(adapter);
            } while (datosClientes.moveToNext());

            listaEditable();
        } else {
            Toast.makeText(this, "Sin registros aun", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarCliente(View vista) {
        String buscador = inputBuscarCliente.getText().toString();
        if (!buscador.equals("")) {
            listaClientes.clear();
            datosClientes = bdd.buscarCliente(id_usu, buscador);
            if (datosClientes != null) {
                do {
                    String id = datosClientes.getString(0),
                            nombre = datosClientes.getString(3),
                            apellido = datosClientes.getString(2);
                    listaClientes.add(apellido + " " + nombre);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
                    lstClientes.setAdapter(adapter);
                } while (datosClientes.moveToNext());

                listaEditable();

            } else {
                listaClientes.clear();
                listaClientes.add("Sin resultados.");

                lstClientes.setOnItemClickListener(null);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
                lstClientes.setAdapter(adapter);

                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe ingresar un citerio de busqueda", Toast.LENGTH_SHORT).show();
            obtenerDatosClientes();
        }
    }

    public void listaEditable() {
        lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosClientes.moveToPosition(position);
                Intent editarCursoProfesor = new Intent(getApplicationContext(), cli_ver_cliente.class);
                editarCursoProfesor.putExtra("idCli", datosClientes.getString(0).toString());
                startActivity(editarCursoProfesor);
//                Toast.makeText(cli_menu.this, "ID: " + datosClientes.getString(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void nuevoCliente(View vista) {
        Intent ventanaNuevoCliente = new Intent(getApplicationContext(), cli_agregar_cliente.class);
        startActivity(ventanaNuevoCliente);
    }

    public void volver(View vista) {
        finish();
    }
}