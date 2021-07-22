package com.utc.drcheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class web_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_menu);
    }

    public void comWeb(View vista) {
        Intent verWeb = new Intent(getApplicationContext(), web_api.class);
        startActivity(verWeb);
    }

    public void comAPI(View vista) {
        Intent verAPI = new Intent(getApplicationContext(), soc_red_social.class);
        startActivity(verAPI);
    }

    public void volver(View vista) {
        finish();
    }
}