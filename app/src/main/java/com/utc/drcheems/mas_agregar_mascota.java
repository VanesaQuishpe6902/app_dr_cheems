package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Logica para agregar mascotas
 *
 */

public class mas_agregar_mascota extends AppCompatActivity {
    private DatePickerDialog datePickerDialogFechaNacimiento;
    EditText inputNombreMascotaAgregar, inputRasgosMascotaAgregar;
    Button btn_FechaNacimientoMascotaAgregar;
    BaseDatos bdd;
    String idCli;
    RadioButton chbxIsDog, chbxIsCat, chbxIsOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_agregar_mascota);
        //Mapear
        inputNombreMascotaAgregar = (EditText) findViewById(R.id.inputNombreMascotaAgregar);
        inputRasgosMascotaAgregar = (EditText) findViewById(R.id.inputRasgosMascotaAgregar);
        btn_FechaNacimientoMascotaAgregar = (Button) findViewById(R.id.btn_FechaNacimientoMascotaAgregar);
        chbxIsDog = (RadioButton) findViewById(R.id.chbxIsDog);
        chbxIsCat = (RadioButton) findViewById(R.id.chbxIsCat);
        chbxIsOther = (RadioButton) findViewById(R.id.chbxIsOther);
        chbxIsOther.setChecked(true);

        //Calendario
        initDatePicker();
        //Llamar Base Datos
        bdd = new BaseDatos(getApplicationContext());

        Bundle parametrosExtra = getIntent().getExtras();
        if (parametrosExtra != null) {
            try {
                idCli = parametrosExtra.getString("idCli");
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Sin datos de la anterior acticidad. " + ex.toString(), Toast.LENGTH_LONG).show();

            }
        }

    }

    public void selectDog(View vista) {
        chbxIsDog.setChecked(true);
        chbxIsCat.setChecked(false);
        chbxIsOther.setChecked(false);
    }

    public void selectCat(View vista) {
        chbxIsDog.setChecked(false);
        chbxIsCat.setChecked(true);
        chbxIsOther.setChecked(false);
    }

    public void selectOther(View vista) {
        chbxIsDog.setChecked(false);
        chbxIsCat.setChecked(false);
        chbxIsOther.setChecked(true);
    }

    public void registrarMascota(View vista) {
        String nick = "",
                nombre = inputNombreMascotaAgregar.getText().toString(),
                tipo = "",
                fecha_nacimiento = btn_FechaNacimientoMascotaAgregar.getText().toString(),
                rasgos = inputRasgosMascotaAgregar.getText().toString();
        int error = 0;
        if (nombre.equals("") || !isWord(nombre)) {
            error++;
            inputNombreMascotaAgregar.setError("Debe ingresar un nombre válido");
            inputNombreMascotaAgregar.requestFocus();
        }
        if (fecha_nacimiento.equals("aaaa-mm-dd")) {
            error++;
            //            btn_FechaNacimientoMascotaAgregar.setError("Debes ingresar una fecha de nacimiento");
            Toast.makeText(this, "Debes ingresar una fecha de nacimiento", Toast.LENGTH_SHORT).show();
        }
        if (rasgos.equals("")) {
            error++;
            inputRasgosMascotaAgregar.setError("Debe ingresar un rasgo característico");
            inputRasgosMascotaAgregar.requestFocus();
        }
        if (error == 0) {
            // Obtener informacion del cliente
            Cursor clienteInfo = bdd.verClientes(idCli);
            String last_ci = clienteInfo.getString(1).toString().substring(6, 10);
            nick = last_ci + "-" + nombre;
            if (chbxIsDog.isChecked()) {
                tipo = "canino";
            } else if (chbxIsCat.isChecked()) {
                tipo = "felino";
            } else {
                tipo = "otro";
            }
            try {
                bdd.agregarMascota(nick, nombre, tipo, fecha_nacimiento, rasgos, idCli);
                volver(null);
                Toast.makeText(this, "Mascota registrada!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Huston, tenemos un problema... " + ex, Toast.LENGTH_SHORT).show();
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
                btn_FechaNacimientoMascotaAgregar.setText(date);
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

    // Validar que solo contenga texto y espacios
    private boolean isNumberPhone(String number) {
        return Pattern.matches("^09.*[0-9]$", number);
    }

    public void volver(View vista) {
        finish();
    }
}