package com.example.applocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void SalvarOnClick(View salve){

        Local local = new Local();
        local.setLatitude(lblLat.getText().toString());
        local.setLongitude(lblLong.getText().toString());
        SharedPreferences prefs = getSharedPreferences("Localização", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("latitude", local.getLatitude());
        ed.putString("longitude", local.getLongitude());
        ed.apply();
        Toast.makeText(getBaseContext(),"Salvo com sucesso", Toast.LENGTH_SHORT).show();
    }

    public void RecuperarOnClick(View Rec){
        SharedPreferences prefs = getSharedPreferences("Localização", Context.MODE_PRIVATE);
        lblLat.setText(prefs.getString("latitude", "não encontrado"));
        lblLong.setText(prefs.getString("longitude", "não encontrado"));
    }
}