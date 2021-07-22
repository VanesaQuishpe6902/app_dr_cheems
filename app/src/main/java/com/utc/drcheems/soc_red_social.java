package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 17/07/2021
 * Descripción: Opciones de Gestion Comentarios para  Red Social
 *
 */
public class soc_red_social extends AppCompatActivity {
    Button btn_RespondidoRedSocial, btn_SinResponderRedSocial, btn_TodosRedSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soc_red_social);
    }

    public void verComentario(View vista) {
        Intent irComentario = new Intent(getApplicationContext(), soc_responder_comentario.class);
        startActivity(irComentario);
    }

    public void volver(View vista) {
        finish();
    }
}