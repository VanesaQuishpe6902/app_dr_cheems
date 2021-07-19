package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Editar Mascota
 *
 */

public class mas_editar_mascota extends AppCompatActivity {

    TextView txtIdMascotaEditar;
    EditText inputNicknameMascotaEditar, inputNombreMascotaEditar, inputTipoMascotaEditar, inputRasgosMascotaEditar;

    Button btn_FechaNacimientoMascotaEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_editar_mascota);
        //Mapear
        txtIdMascotaEditar = (TextView) findViewById(R.id.txtIdMascotaEditar);


    }
}