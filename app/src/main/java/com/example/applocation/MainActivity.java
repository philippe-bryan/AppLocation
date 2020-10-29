package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PesqHeroOnClick(View pesq){
        Intent intentHero = new Intent(this, PesquisaHero.class);
        startActivity(intentHero);
    }

    public void SalvosOnClick(View salvos){
        Intent intentHero = new Intent(this, MostraHeroi.class);
        startActivity(intentHero);
    }

    public void AbrirGeoOnClick(View abrir){
        Intent intentGeo = new Intent(this, GeoLocation.class);
        startActivity(intentGeo);
    }
}