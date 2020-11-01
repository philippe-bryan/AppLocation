package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class RecebeDados extends AppCompatActivity{
    int from_Where_I_Am_Coming = 0;
    private DataBaseHelper mydb ;

    String queryString;
    TextView lblName, lblFullName, lblPlaceOfBirth;
    String name, fullName, placeOfBirth;
    Herois herois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_dados);

        Intent intent2 = getIntent();
        Bundle bundleID = intent2.getExtras();
        queryString = bundleID.getString("key_ID");

        lblName = (TextView)findViewById(R.id.lblName);
        lblFullName = (TextView)findViewById(R.id.lblFull);
        lblPlaceOfBirth = (TextView)findViewById(R.id.lblCityNasc);

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

        mydb = new DataBaseHelper(this);
    }

    public void Carregar(String id) throws JSONException, IOException {
        CarregaDados carregaDados = NetworkUtils.localizar(queryString);
        lblName.setText("Codnome: " + carregaDados.getName());
        lblFullName.setText("Nome Real: " + carregaDados.getFullName());
        lblPlaceOfBirth.setText("Local de Nascimento: " + carregaDados.getPlaceOfBirth());

        name = carregaDados.getName();
        fullName = carregaDados.getFullName();
        placeOfBirth = carregaDados.getPlaceOfBirth();
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

    public void SalvarDadosOnClick(View salvar){
        if(mydb.insertHiro(new Herois(name, fullName, placeOfBirth))){
            Toast.makeText(getApplicationContext(), "Salvo com sucesso",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(), "falha",
                    Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getApplicationContext(),PesquisaHero.class);
        startActivity(intent);
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, PesquisaHero.class);
        startActivity(intentReturn);
    }
}