package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Editar Mascota
 *
 */

public class mas_editar_mascota extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFechaNacimiento;
    TextView txtIdMascotaEditar;
    EditText inputNombreMascotaEditar, inputTipoMascotaEditar, inputRasgosMascotaEditar;
    RadioButton chbxEditarTipoDog, chbxEditarTipoCat, chbxEditarTipoOther;
    Button btn_FechaNacimientoMascotaEditar;
    String idMas;
    Cursor infoMas;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_editar_mascota);
        //Mapear
        btn_FechaNacimientoMascotaEditar = (Button) findViewById(R.id.btn_FechaNacimientoMascotaEditar);
        txtIdMascotaEditar = (TextView) findViewById(R.id.txtIdMascotaEditar);
        inputNombreMascotaEditar = (EditText) findViewById(R.id.inputNombreMascotaEditar);
        inputRasgosMascotaEditar = (EditText) findViewById(R.id.inputRasgosMascotaEditar);
        //Calendario
        initDatePicker();
        bdd = new BaseDatos(getApplicationContext());

        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idMas = parametrosExtra.getString("idMas");
                infoMas = bdd.verMascota(idMas);

                txtIdMascotaEditar.setText(idMas);
                inputNombreMascotaEditar.setText(infoMas.getString(2));
                inputRasgosMascotaEditar.setText(infoMas.getString(5));
                btn_FechaNacimientoMascotaEditar.setText(infoMas.getString(4));
                chbxEditarTipoDog = (RadioButton) findViewById(R.id.chbxEditarTipoDog);
                chbxEditarTipoCat = (RadioButton) findViewById(R.id.chbxEditarTipoCat);
                chbxEditarTipoOther = (RadioButton) findViewById(R.id.chbxEditarTipoOther);

                if (infoMas.getString(3).toString().equals("canino")) {
                    chbxEditarTipoDog.setChecked(true);
                } else if (infoMas.getString(3).toString().equals("felino")) {
                    chbxEditarTipoCat.setChecked(true);
                } else {
                    chbxEditarTipoOther.setChecked(true);
                }

//                Toast.makeText(this, "ID: " + idMas, Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void actualizar(View vista) {
        String nick = "",
                nombre = inputNombreMascotaEditar.getText().toString(),
                tipo = "",
                fecha_nacimiento = btn_FechaNacimientoMascotaEditar.getText().toString(),
                rasgos = inputRasgosMascotaEditar.getText().toString();
        int error = 0;
        if (nombre.equals("") || !isWord(nombre)) {
            error++;
            inputNombreMascotaEditar.setError("Debe ingresar un nombre válido");
            inputNombreMascotaEditar.requestFocus();
        }
        if (fecha_nacimiento.equals("aaaa-mm-dd")) {
            error++;
            //            btn_FechaNacimientoMascotaAgregar.setError("Debes ingresar una fecha de nacimiento");
            Toast.makeText(this, "Debes ingresar una fecha de nacimiento", Toast.LENGTH_SHORT).show();
        }
        if (rasgos.equals("")) {
            error++;
            inputRasgosMascotaEditar.setError("Debe ingresar un rasgo característico");
            inputRasgosMascotaEditar.requestFocus();
        }
        if (error == 0) {
            String[] vec_nik = infoMas.getString(1).split("-");
            String str_nik = vec_nik[0];
            nick = str_nik + "-" + nombre;
            if (chbxEditarTipoDog.isChecked()) {
                tipo = "canino";
            } else if (chbxEditarTipoCat.isChecked()) {
                tipo = "felino";
            } else {
                tipo = "otro";
            }
            try {
                bdd.actualizarMascota(idMas, nick, nombre, tipo, fecha_nacimiento, rasgos);
                volver(null);
                Toast.makeText(this, "Información actualizada!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Huston, tenemos un problema... " + ex, Toast.LENGTH_LONG).show();
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
                btn_FechaNacimientoMascotaEditar.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialogFechaNacimiento = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialogFechaNacimiento.getDatePicker().setMaxDate(cal.getTimeInMillis());
    }

    public void OpenDatePickerFechaNacimiento(View vista) {
        datePickerDialogFechaNacimiento.show();
    }


    // Metodos de validacion
    private boolean isWord(String word) {
        return Pattern.matches(".*[ a-zA-Z-ñÑáéíóúÁÉÍÓÚ].*", word) && !Pattern.matches(".*[0-9].*", word);
    }

    public void selectDog(View vista) {
        chbxEditarTipoDog.setChecked(true);
        chbxEditarTipoCat.setChecked(false);
        chbxEditarTipoOther.setChecked(false);
    }

    public void selectCat(View vista) {
        chbxEditarTipoDog.setChecked(false);
        chbxEditarTipoCat.setChecked(true);
        chbxEditarTipoOther.setChecked(false);
    }

    public void selectOther(View vista) {
        chbxEditarTipoDog.setChecked(false);
        chbxEditarTipoCat.setChecked(false);
        chbxEditarTipoOther.setChecked(true);
    }

    public void volver(View vista) {
        finish();
    }
}

