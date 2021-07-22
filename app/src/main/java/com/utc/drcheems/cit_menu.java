package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción:Menu citas
 *
 */
public class cit_menu extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFechaCita;
    Button btn_NuevaCita, btn_SeleccionarFechaCita;
    BaseDatos bdd;
    String idUsu;
    Cursor datosCitas;
    ListView lstCitas;
    ArrayList<String> listaCitas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_menu);

        btn_SeleccionarFechaCita = (Button) findViewById(R.id.btn_SeleccionarFechaCita);
        lstCitas = (ListView) findViewById(R.id.lstCitas);
        initDatePicker();
        bdd = new BaseDatos(getApplicationContext());
        SharedPreferences prefs = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        idUsu = prefs.getString("idUsu", "");
        obtenerCitasHoy();
    }

    public void nuevaCita(View vista) {
        Intent nuevaCita = new Intent(getApplicationContext(), cit_seleccionar_mascota.class);
        startActivity(nuevaCita);
    }
    //

    private void obtenerCitasHoy() {
        listaCitas.clear();
        String fechaHoy = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        fechaHoy = formatter.format(date);
//        Toast.makeText(this, "Hoy es: " + fechaHoy, Toast.LENGTH_SHORT).show();
        datosCitas = bdd.listarCitaHoy(idUsu, fechaHoy);
        if (datosCitas != null) {
            do {
                String str_fecha = datosCitas.getString(1);
                String[] vec_fecha = str_fecha.split(" ");
                listaCitas.add("Fecha: " + vec_fecha[0] + " Hora: " + vec_fecha[1]);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                lstCitas.setAdapter(adapter);
            } while (datosCitas.moveToNext());

            listaEditable();
        } else {
            listaCitas.add("No tienes citas para hoy");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
            lstCitas.setAdapter(adapter);
            lstCitas.setOnItemClickListener(null);
//            Toast.makeText(this, "No tienes citas para hoy", Toast.LENGTH_LONG).show();
        }

    }

    public void listaEditable() {
        lstCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosCitas.moveToPosition(position);
//                Intent verCita = new Intent(getApplicationContext(), cli_ver_cliente.class);
//                verCita.putExtra("idCit", datosCitas.getString(0).toString());
//                startActivity(verCita);
                Toast.makeText(cit_menu.this, "ID CIT: " + datosCitas.getString(0), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Buscador de citas por fecha

    public void buscarCitasPorFecha(View vista) {
        String fecha = btn_SeleccionarFechaCita.getText().toString();
        if (!fecha.equals("aaaa-mm-dd")) {
            listaCitas.clear();
//        Toast.makeText(this, "Hoy es: " + fechaHoy, Toast.LENGTH_SHORT).show();
            datosCitas = bdd.listarCitaHoy(idUsu, fecha);
            if (datosCitas != null) {
                do {
                    String str_fecha = datosCitas.getString(1);
                    String[] vec_fecha = str_fecha.split(" ");
                    listaCitas.add("Fecha: " + vec_fecha[0] + " Hora: " + vec_fecha[1]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                    lstCitas.setAdapter(adapter);
                } while (datosCitas.moveToNext());

                listaEditable();
            } else {
                listaCitas.add("Sin registros, toca para volver a listar las citas de hoy");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                lstCitas.setAdapter(adapter);
                lstCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        obtenerCitasHoy();
                    }
                });
                Toast.makeText(this, "No hay citas para esa fecha.", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Hacer de la fecha una cadena y presentarla
    private String makeDateString(int day, int month, int year) {
        String str_month = "" + month;
        String str_day = "" + day;
        if (month < 10) {
            str_month = "0" + month;
        }
        if (day < 10) {
            str_day = "0" + day;
        }
        return year + "-" + str_month + "-" + str_day;
    }

    //Funcion: Selector de fecha de inicio
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                btn_SeleccionarFechaCita.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialogFechaCita = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialogFechaNacimiento.getDatePicker().setMinDate(cal.getTimeInMillis());
    }

    public void OpenDatePickerFecha(View vista) {
        datePickerDialogFechaCita.show();
    }

    public void volver(View vista) {
        finish();
    }
}