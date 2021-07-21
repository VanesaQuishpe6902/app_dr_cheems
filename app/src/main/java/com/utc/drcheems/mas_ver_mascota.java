package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion de la mascota por fecha de historial
 *
 */
public class mas_ver_mascota extends AppCompatActivity {
    TextView txtNickMascotaVer, txtNombreMascotaVer, txtTipoMascotaVer, txtFechaNacimientoMascotaVer, txtRasgosMascotaVer;

    BaseDatos bdd;
    String idMas;
    Cursor datosMascota;
    Cursor datosCitas;
    ListView lstCitas;
    ArrayList<String> listaCitas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_ver_mascota);
        //Mapeo
        txtNombreMascotaVer = (TextView) findViewById(R.id.txtNombreMascotaVer);
        txtNickMascotaVer = (TextView) findViewById(R.id.txtNickMascotaVer);
        txtTipoMascotaVer = (TextView) findViewById(R.id.txtTipoMascotaVer);
        txtFechaNacimientoMascotaVer = (TextView) findViewById(R.id.txtFechaNacimientoMascotaVer);
        txtRasgosMascotaVer = (TextView) findViewById(R.id.txtRasgosMascotaVer);

        bdd = new BaseDatos(getApplicationContext());
        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idMas = parametrosExtra.getString("idMas");
                datosMascota = bdd.verMascota(idMas);
                txtNickMascotaVer.setText(datosMascota.getString(1));
                txtNombreMascotaVer.setText(datosMascota.getString(2));
                txtTipoMascotaVer.setText(datosMascota.getString(3));
                txtFechaNacimientoMascotaVer.setText(datosMascota.getString(4));
                txtRasgosMascotaVer.setText(datosMascota.getString(5));
//                Toast.makeText(this, "ID mas:" + idMas, Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void editarMascota(View vista) {
        Intent editarMascota = new Intent(getApplicationContext(), mas_editar_mascota.class);
        editarMascota.putExtra("idMas", idMas);
        startActivity(editarMascota);
    }

    public void volver(View vista) {
        finish();
    }
}