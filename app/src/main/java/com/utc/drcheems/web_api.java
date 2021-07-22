package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 17/07/2021
 * Editado: 19/07/2021
 * Descripción: Logica para navegar en internet a traves de un webView
 *
 */

public class web_api extends AppCompatActivity {
    //ENTRADA
    EditText txtUrl; //Definicion de variable para capturar la URL inngresada por el usuario
    WebView webView; //Definicion de variable para arrastrar las paginas web solicitadas por el usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_api);
        //MAPEO DE ELEMENTOS GRAFICOS
        webView = (WebView) findViewById(R.id.webView); //Relacionar el elemento webview1
        buscarPaginaWeb();
    }
//PROCESO 2

    public void buscarPaginaWeb() {
        String urlingresada = getString(R.string.url_site_web_comments) + ""; //Capturar el valor ingresado por el usuario en el txturl y lo almacenamoms en una variable urlingresada
        webView.setWebViewClient(new WebViewClient()); //creando un navegador nuevo cada vez que el usuario cambie de url
        webView.getSettings().setJavaScriptEnabled(true); //Habilitando javascript dentro de las paginas web que se este buscando
        webView.getSettings().setDomStorageEnabled(true); // habilitando todos los elementos del mon de la pagina web solicitada
        webView.loadUrl(urlingresada); //Cargando en el navegador la url ingresada por el usuario
//        Toast.makeText(getApplicationContext(), "Cargando página, por favor espere ...", Toast.LENGTH_LONG).show(); //Mostrando en pantalla un mensaje informativo, salida 2
    }

    public void volver(View vista) {
        finish();
    }
}