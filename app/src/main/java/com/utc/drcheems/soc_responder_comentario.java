package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Responder Comentarios
 *
 */
public class soc_responder_comentario extends AppCompatActivity {
    TextView txtFechaResponder, txtNombreResponder, txtWhatsAppResponder, txtComentarioObtenidoWeb;
    EditText inputResponderComentario;
    BaseDatos bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soc_responder_comentario);
        //Mapeo
        txtFechaResponder = (TextView) findViewById(R.id.txtFechaResponder);
        txtNombreResponder = (TextView) findViewById(R.id.txtNombreResponder);
        txtWhatsAppResponder = (TextView) findViewById(R.id.txtWhatsAppResponder);
        txtComentarioObtenidoWeb = (TextView) findViewById(R.id.txtComentarioObtenidoWeb);
        inputResponderComentario = (EditText) findViewById(R.id.inputResponderComentario);

        //Base de datos
        bdd = new BaseDatos(getApplicationContext());

    }

    public void volver(View vista) {
        finish();
    }
}