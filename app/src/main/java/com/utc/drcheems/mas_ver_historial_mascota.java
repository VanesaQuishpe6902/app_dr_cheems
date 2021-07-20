package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: informacion a detalle de la cita Mascota
 *
 */
public class mas_ver_historial_mascota extends AppCompatActivity {
    TextView txtFechaCitaHistorialMas, txtServicioHistorialMas, txtSolucionHistorialMas;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_ver_historial_mascota);
        //Mapeo
        txtFechaCitaHistorialMas = (TextView) findViewById(R.id.txtFechaCitaHistorialMas);
        txtServicioHistorialMas = (TextView) findViewById(R.id.txtServicioHistorialMas);
        txtSolucionHistorialMas = (TextView) findViewById(R.id.txtSolucionHistorialMas);

    }
}