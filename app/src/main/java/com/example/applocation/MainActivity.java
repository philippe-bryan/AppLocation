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

    EditText txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtID = (EditText)findViewById(R.id.txtID);
    }

    public void btnPesqOnClick(View v){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null){
            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        EditText txtID = (EditText) findViewById(R. id.txtID);

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
}