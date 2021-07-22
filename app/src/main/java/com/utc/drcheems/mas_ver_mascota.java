package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion de la mascota por fecha de historial
 *
 */
public class mas_ver_mascota extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFechaNacimiento;
    TextView txtNickMascotaVer, txtNombreMascotaVer, txtTipoMascotaVer, txtFechaNacimientoMascotaVer, txtRasgosMascotaVer;
    BaseDatos bdd;
    String idMas;
    Button btn_SeleccionarFechaMascotaVer;
    Cursor datosMascota;
    Cursor datosCitas;
    ListView lstCitas;
    ArrayList<String> listaCitas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_ver_mascota);
        //Mapeo
        btn_SeleccionarFechaMascotaVer = (Button) findViewById(R.id.btn_SeleccionarFechaMascotaVer);
        txtNombreMascotaVer = (TextView) findViewById(R.id.txtNombreMascotaVer);
        txtNickMascotaVer = (TextView) findViewById(R.id.txtNickMascotaVer);
        txtTipoMascotaVer = (TextView) findViewById(R.id.txtTipoMascotaVer);
        txtFechaNacimientoMascotaVer = (TextView) findViewById(R.id.txtFechaNacimientoMascotaVer);
        txtRasgosMascotaVer = (TextView) findViewById(R.id.txtRasgosMascotaVer);
        lstCitas = (ListView) findViewById(R.id.lstCitas);
        bdd = new BaseDatos(getApplicationContext());
        // Calendario
        initDatePicker();
        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idMas = parametrosExtra.getString("idMas");
                obtenerDatosMascota();
//                Toast.makeText(this, "ID mas:" + idMas, Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }
        obtenerDatosCitas();
    }

    public void buscarCitasCliente(View vista) {
        String fechaCriterio = btn_SeleccionarFechaMascotaVer.getText().toString();
        if (!fechaCriterio.equals("aaaa-mm-dd") && !fechaCriterio.equals("01-01-2021")) {
            listaCitas.clear();
            try {
                datosCitas = bdd.listarCitaPorFechaMascota(idMas, fechaCriterio);
            } catch (Exception ex) {
                Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
            }
            if (datosCitas != null) {
                do {
                    String fecha_cita = datosCitas.getString(1).toString();
                    String[] vec_fecha = fecha_cita.split(" ");
                    listaCitas.add("Día: " + vec_fecha[0] + " Hora: " + vec_fecha[1]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                    lstCitas.setAdapter(adapter);
                } while (datosCitas.moveToNext());

                listaCitasEditable();
            } else {
                listaCitas.add("Sin resultados, toque para volver a listar todos.");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                lstCitas.setAdapter(adapter);
                lstCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        obtenerDatosCitas();
                    }
                });
                Toast.makeText(this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void obtenerDatosCitas() {
        listaCitas.clear();
        try {
            datosCitas = bdd.listarCitaMascota(idMas);
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
        }
        if (datosCitas != null) {
            do {
                String id = datosCitas.getString(0).toString(),
                        fecha_cita = datosCitas.getString(1).toString();
                String[] vec_fecha = fecha_cita.split(" ");
                listaCitas.add("Día: " + vec_fecha[0] + " Hora: " + vec_fecha[1]);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
                lstCitas.setAdapter(adapter);
            } while (datosCitas.moveToNext());

            listaCitasEditable();
        } else {
            Toast.makeText(this, "Sin registros aun", Toast.LENGTH_SHORT).show();
        }
    }

    public void listaCitasEditable() {
        lstCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                datosCitas.moveToPosition(position);
                Intent verCita = new Intent(getApplicationContext(), cit_menu.class);
                verCita.putExtra("idCit", datosCitas.getString(0).toString());
                startActivity(verCita);
//                Toast.makeText(cli_menu.this, "ID: " + datosClientes.getString(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerDatosMascota() {
        datosMascota = bdd.verMascota(idMas);
        txtNickMascotaVer.setText(datosMascota.getString(1));
        txtNombreMascotaVer.setText(datosMascota.getString(2));
        txtTipoMascotaVer.setText(datosMascota.getString(3));
        txtFechaNacimientoMascotaVer.setText(datosMascota.getString(4));
        txtRasgosMascotaVer.setText(datosMascota.getString(5));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        obtenerDatosMascota();

    }

    public void editarMascota(View vista) {
        Intent editarMascota = new Intent(getApplicationContext(), mas_editar_mascota.class);
        editarMascota.putExtra("idMas", idMas);
        startActivity(editarMascota);
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
                btn_SeleccionarFechaMascotaVer.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialogFechaNacimiento = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialogFechaNacimiento.getDatePicker().setMinDate(cal.getTimeInMillis());
    }

    public void OpenDatePickerBuscarCitaCliente(View vista) {
        datePickerDialogFechaNacimiento.show();
    }

    public void volver(View vista) {
        finish();
    }
}