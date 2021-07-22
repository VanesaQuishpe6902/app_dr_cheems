package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción:
 *
 */
public class cit_agregar_citas extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFecha;
    private TimePickerDialog timePickerDialogHora;
    EditText inputServicioAgregarCita;
    Button btn_SeleccionarFechaCitaAgregar, btn_SeleccionarHoraCitaAgregar;

    BaseDatos bdd;
    String idMas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_agregar_citas);
        btn_SeleccionarFechaCitaAgregar = (Button) findViewById(R.id.btn_SeleccionarFechaCitaAgregar);
        btn_SeleccionarHoraCitaAgregar = (Button) findViewById(R.id.btn_SeleccionarHoraCitaAgregar);
        inputServicioAgregarCita = (EditText) findViewById(R.id.inputServicioAgregarCita);
        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idMas = parametrosExtra.getString("idMas");
                Toast.makeText(this, "ID MAS: " + idMas, Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
        //Calendar Timer
        initDatePicker();
        initTimePicker();
    }

    public void opRapBtn1(View vista) {
        inputServicioAgregarCita.setText("Vacuna Mensual");
    }

    public void opRapBtn2(View vista) {
        inputServicioAgregarCita.setText("Desparacitación");
    }

    public void opRapBtn3(View vista) {
        inputServicioAgregarCita.setText("Tratamiento vitamínico");
    }

    public void opRapBtn4(View vista) {
        inputServicioAgregarCita.setText("Revisión Odontológica");
    }

    public void opRapBtn5(View vista) {
        inputServicioAgregarCita.setText("Revisión Post-Operatoria");
    }

    public void opRapBtn6(View vista) {
        inputServicioAgregarCita.setText("Revisión Post-Esterilización");
    }

    public void registrarCita(View vista) {
        String fecha = btn_SeleccionarFechaCitaAgregar.getText().toString(),
                hora = btn_SeleccionarHoraCitaAgregar.getText().toString(),
                servicio = inputServicioAgregarCita.getText().toString();
        int error = 0;
        if (fecha.equals("aaaa-mm-dd")) {
            error++;
            Toast.makeText(this, "Debe ingresar una fecha para la cita", Toast.LENGTH_SHORT).show();
        }
        if (hora.equals("HH:mm")) {
            error++;
            Toast.makeText(this, "Debe ingresar una hora para la cita", Toast.LENGTH_SHORT).show();
        }
        if (servicio.equals("")) {
            error++;
            inputServicioAgregarCita.setError("Campo obligatorio");
            inputServicioAgregarCita.requestFocus();
        }
        if (error == 0) {
            String fecha_cita = fecha + " " + hora;
            try {
                bdd.agregarCita(fecha_cita, servicio, idMas);
                Intent volverMenuCita = new Intent(getApplicationContext(), cit_menu.class);
                volverMenuCita.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(volverMenuCita);
                Toast.makeText(this, "Cita registrada!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
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
                btn_SeleccionarFechaCitaAgregar.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialogFecha = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialogFechaNacimiento.getDatePicker().setMaxDate(cal.getTimeInMillis());

    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // Dar formato a los minutos
                String str_min = "" + minute;
                if (minute < 10) {
                    str_min = "0" + minute;
                }
                btn_SeleccionarHoraCitaAgregar.setText(hour + ":" + str_min);
            }
        };


        int hour = 12;
        int min = 0;

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        timePickerDialogHora = new TimePickerDialog(this, style, timeSetListener, hour, min, true);
    }

    public void OpenDatePickerFecha(View vista) {
        datePickerDialogFecha.show();
    }

    public void OpenTimePickerHora(View vista) {
        timePickerDialogHora.show();
    }

    public void volver(View vista) {
        finish();
    }
}