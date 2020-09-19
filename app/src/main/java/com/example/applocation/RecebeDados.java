package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class RecebeDados extends AppCompatActivity {

    String queryString;
    TextView lblName, lblFull, lblCityNasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_dados);

        Intent intent2 = getIntent();
        Bundle bundleID = intent2.getExtras();
        queryString = bundleID.getString("key_ID");

        lblName = (TextView)findViewById(R.id.lblName);
        lblFull = (TextView)findViewById(R.id.lblFull);
        lblCityNasc = (TextView)findViewById(R.id.lblCityNasc);

        String id = queryString;
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            else {
                Carregar(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show();
        }
    }

    public void Carregar(String id) throws JSONException, IOException {
        CarregaDados carregaDados = NetworkUtils.localizar(queryString);
        lblName.setText("Codnome: " + carregaDados.getName());
        lblFull.setText("Nome Real: " + carregaDados.getFullName());
        lblCityNasc.setText("Local de Nascimento: " + carregaDados.getPlaceOfBirth());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EditText txtID = (EditText) findViewById(R. id.txtID);
                    String id = txtID.getText().toString();
                    try {
                        Carregar(id);
                    } catch (Exception e) {
                        Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, PesquisaHero.class);
        startActivity(intentReturn);
    }
}