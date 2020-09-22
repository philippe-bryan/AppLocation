package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class PesquisaHero extends AppCompatActivity {

    EditText txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_hero);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtID = (EditText)findViewById(R.id.txtID);
        }

    public void btnPesqOnClick(View v){
            String intentID = txtID.getText().toString();
            Intent intent = new Intent(this, RecebeDados.class);
            Bundle bundleID = new Bundle();
            Toast.makeText(this, "Carregando...", Toast.LENGTH_LONG).show();
            bundleID.putString("key_ID", String.valueOf(intentID));
            intent.putExtras(bundleID);
            startActivity(intent);
        }

    public void PesqIdOnClick(View mais){
        Uri webpage = Uri.parse("https://superheroapi.com/ids.html");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        startActivity(webIntent);
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, MainActivity.class);
        startActivity(intentReturn);
    }
}