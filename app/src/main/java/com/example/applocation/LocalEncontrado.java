package com.example.applocation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocalEncontrado extends AppCompatActivity {

    TextView lblLat, lblLong;
    String recebidos1, recebidos2;
    double latitude, longitude;

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

        latitude = Double.parseDouble(recebidos1);
        longitude = Double.parseDouble(recebidos2);

        lblLat = (TextView) findViewById(R.id.lblLat);
        lblLong = (TextView) findViewById(R.id.lblLong);
        lblLat.setText("Latitude: "+latitude);
        lblLong.setText("Longitude: "+longitude);
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, GeoLocation.class);
        startActivity(intentReturn);
    }

    public void MapaOnClick(View mapa){
        Uri mappage = Uri.parse("https://www.google.com/maps/search/?api=1&query="+latitude+"%2C"+longitude+"&hl=pt-br");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mappage);
        startActivity(mapIntent);
    }
}