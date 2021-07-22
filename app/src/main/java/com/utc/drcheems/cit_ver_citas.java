package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Ver informacion de cita
 *
 */
public class cit_ver_citas extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFecha;
    private TimePickerDialog timePickerDialogHora;
    TextView txtIdCitaVer, txtFechaCitaVer, txtServicioCitaVer, txtSolucionCitaVer;
    Button btnDiaCitaver, btnHoraCitaver;
    BaseDatos bdd;
    String idCit;
    Cursor infoCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cit_ver_citas);
        //Mapeo
        btnDiaCitaver = (Button) findViewById(R.id.btnDiaCitaver);
        btnHoraCitaver = (Button) findViewById(R.id.btnHoraCitaver);
        txtIdCitaVer = (TextView) findViewById(R.id.txtIdCitaVer);
//        txtFechaCitaVer = (TextView) findViewById(R.id.txtFechaCitaVer);
        txtServicioCitaVer = (TextView) findViewById(R.id.txtServicioCitaVer);
        txtSolucionCitaVer = (TextView) findViewById(R.id.txtSolucionCitaVer);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCit = parametrosExtra.getString("idCit");
                try {
                    infoCita = bdd.listarCitaId(idCit);
                    colocarDatosCita();
                } catch (Exception ex) {
                    Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }

        //Calendario
        initDatePicker();
        initTimePicker();


    }

    private void colocarDatosCita() {
        String[] vec_fecha = infoCita.getString(1).split(" ");

        btnDiaCitaver.setText(vec_fecha[0]);
        btnHoraCitaver.setText(vec_fecha[1]);
        txtIdCitaVer.setText(infoCita.getString(0));
        txtServicioCitaVer.setText(infoCita.getString(2));
        txtSolucionCitaVer.setText(infoCita.getString(3));
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
                btnDiaCitaver.setText(date);
            }
        };
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
        // Traer la fecha de la cita por partes
        String[] vec_fecha = infoCita.getString(1).split(" ");
        String[] vec_fecha_dia = vec_fecha[0].split("-");
        int year = Integer.parseInt(vec_fecha_dia[0]);
        int month = Integer.parseInt(vec_fecha_dia[1]) - 1;
        int day = Integer.parseInt(vec_fecha_dia[2]);

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
                btnHoraCitaver.setText(hour + ":" + str_min);
            }
        };
        String[] vec_fecha = infoCita.getString(1).split(" ");
        String[] vec_hora = vec_fecha[1].split(":");

        int hour = Integer.parseInt(vec_hora[0]);
        int min = Integer.parseInt(vec_hora[1]);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        timePickerDialogHora = new TimePickerDialog(this, style, timeSetListener, hour, min, true);
    }

    public void OpenDatePickerFecha(View vista) {
        datePickerDialogFecha.show();
    }

    public void OpenTimePickerHora(View vista) {
        timePickerDialogHora.show();
    }

    public void actualzarCita(View vista) {
        String dia = btnDiaCitaver.getText().toString(),
                hora = btnHoraCitaver.getText().toString(),
                servicio = txtServicioCitaVer.getText().toString(),
                solucion = txtSolucionCitaVer.getText().toString();
        int error = 0;
        if (servicio.equals("")) {
            error++;
            txtServicioCitaVer.setError("Campo obligatorio");
            txtServicioCitaVer.requestFocus();
        }

        if (error == 0) {
            // Construir la fecha
            String fecha_nueva = dia + " " + hora;
            try {
                bdd.actualizarCita(idCit, fecha_nueva, servicio, solucion);
                volver(null);
                Toast.makeText(this, "Cita actualizada!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void volver(View vista) {
        finish();
    }

}