package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: informacion a detalle de la cita Mascota
 *
 */
public class mas_ver_historial_mascota extends AppCompatActivity {
    TextView txtFechaCitaHistorialMas, txtServicioHistorialMas, txtSolucionHistorialMas, txtFechaCitaHistorialMasHora;
    BaseDatos bdd;
    String idCit;
    Cursor infoCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_ver_historial_mascota);
        //Mapeo
        txtFechaCitaHistorialMas = (TextView) findViewById(R.id.txtFechaCitaHistorialMas);
        txtFechaCitaHistorialMasHora = (TextView) findViewById(R.id.txtFechaCitaHistorialMasHora);
        txtServicioHistorialMas = (TextView) findViewById(R.id.txtServicioHistorialMas);
        txtSolucionHistorialMas = (TextView) findViewById(R.id.txtSolucionHistorialMas);
        bdd = new BaseDatos(getApplicationContext());
        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCit = parametrosExtra.getString("idCit");
                obtenerDatosCita();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void obtenerDatosCita() {
        infoCita = bdd.listarCitaId(idCit);
        if (infoCita != null) {
            String str_fecha = infoCita.getString(1).toString();
            String[] fecha = str_fecha.split(" ");
            txtFechaCitaHistorialMas.setText(fecha[0]);
            txtFechaCitaHistorialMasHora.setText(fecha[1]);
            txtServicioHistorialMas.setText(infoCita.getString(2));
            txtSolucionHistorialMas.setText(infoCita.getString(3));
        }
    }

    public void volver(View vista) {
        finish();
    }
}