package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class LocalEncontrado extends AppCompatActivity {

    TextView lblLocal;
    String recebidos1, recebidos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_encontrado);

        Intent intentRecebe1 = getIntent();
        Intent intentRecebe2 = getIntent();
        Bundle bundleLat = intentRecebe1.getExtras();
        Bundle bundleLong = intentRecebe2.getExtras();
        recebidos1 = bundleLat.getString("key_lat");
        recebidos2 = bundleLong.getString("key_long");

        lblLocal = (TextView) findViewById(R.id.lblLocal);
        lblLocal.setText(recebidos1+" e "+recebidos2);
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, GeoLocation.class);
        startActivity(intentReturn);
    }

}