package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GeoLocation extends AppCompatActivity {

    Double latude, longitude;
    TextView lblInforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_location);
    }

    public void LocalizaOnClick(View v) {
        lblInforma = (TextView) findViewById(R.id.lblinforma);
        lblInforma.setText("Carregando...");
        pedirPermissoes();
    }

    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
            configurarServico();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurarServico();
                } else {
                    Toast.makeText(this, "Permita que o aplicativo use sua localização para ele funcionar corretamente.", Toast.LENGTH_LONG).show();
                    lblInforma = (TextView) findViewById(R.id.lblinforma);
                    lblInforma.setText("Localização não encontrada.");
                }
                return;
            }
        }
    }

    public void configurarServico(){
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    atualizar(location);
                }
                //public void onStatusChanged(String provider, int status, Bundle extras) { }
                //public void onProviderEnabled(String provider) { }
                //public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void atualizar(Location location)
    {
        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        latude = latPoint;
        longitude = lngPoint;

        lblInforma = (TextView) findViewById(R.id.lblinforma);
        lblInforma.setText("Sua localização está sendo monitorada.");
    }

    public void VerOnClick(View a){

        if(latude == null && longitude == null){
            Toast.makeText(this, "Erro, precisa da localização!", Toast.LENGTH_LONG).show();
        }
        else {
            String intentLat = latude.toString();
            String intentLong = longitude.toString();
            Intent intent = new Intent(this, LocalEncontrado.class);
            Bundle bundleLat = new Bundle();
            Bundle bundleLong = new Bundle();
            bundleLat.putString("key_lat", String.valueOf(intentLat));
            bundleLong.putString("key_long", String.valueOf(intentLong));
            intent.putExtras(bundleLat);
            intent.putExtras(bundleLong);
            startActivity(intent);
        }
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, MainActivity.class);
        startActivity(intentReturn);
    }
}