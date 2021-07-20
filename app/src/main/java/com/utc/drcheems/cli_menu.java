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

        bdd = new BaseDatos(getApplicationContext());
        inputBuscarCliente = (EditText) findViewById(R.id.inputBuscarCliente);
        lstClientes = (ListView) findViewById(R.id.lstClientes);
        // Traer informacion del usuario
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        id_usu = prefs.getString("idUsu", "");

        obtenerDatosClientes();
        listaEditable();
    }

    public void obtenerDatosClientes() {
        listaClientes.clear();
        datosClientes = bdd.listarClientes(id_usu);
        if (datosClientes != null) {
            do {
                String id = datosClientes.getString(0),
                        nombre = datosClientes.getString(2),
                        apellido = datosClientes.getString(1);
                listaClientes.add(apellido + " " + nombre);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
                lstClientes.setAdapter(adapter);
            } while (datosClientes.moveToNext());
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
                            nombre = datosClientes.getString(2),
                            apellido = datosClientes.getString(1);
                    listaClientes.add(apellido + " " + nombre);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
                    lstClientes.setAdapter(adapter);
                } while (datosClientes.moveToNext());
            } else {
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            }
        } else {
            obtenerDatosClientes();
        }
    }

    public void listaEditable() {
        lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosClientes.moveToPosition(position);
//                Intent editarCursoProfesor = new Intent(getApplicationContext(), EP_GestionCurso_Listar_MostrarInformacion.class);
//                editarCursoProfesor.putExtra("idCurso", datosClientes.getString(0).toString());
//                finish();
//                startActivity(editarCursoProfesor);
//                Toast.makeText(EP_GestionCurso.this, "ID: " + misCursosProfesor.getString(0), Toast.LENGTH_SHORT).show();
            }
        });
    }
}