package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InfoHiro extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DataBaseHelper mydb ;
    TextView nome, codinome, cidade;
    Herois herois;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hiro);
        nome = findViewById(R.id.txtNome);
        codinome = findViewById(R.id.txtCodinome);
        cidade = findViewById(R.id.txtCidade);

        mydb = new DataBaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int Value = extras.getInt("idHiro");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                herois = new Herois();
                herois.setName(rs.getString(rs.getColumnIndex(DataBaseHelper.HIRO_COLUMN_NAME)));
                herois.setFullName(rs.getString(rs.getColumnIndex(DataBaseHelper.HIRO_COLUMN_FULLNAME)));
                herois.setPlaceOfBirth(rs.getString(rs.getColumnIndex(DataBaseHelper.HIRO_COLUMN_BIRTH)));

                if (!rs.isClosed())  {
                    rs.close();
                }

                nome.setText(herois.getFullName());
                nome.setFocusable(true);
                nome.setClickable(false);

                codinome.setText(herois.getName());
                codinome.setFocusable(false);
                codinome.setClickable(false);

                cidade.setText(herois.getPlaceOfBirth());
                cidade.setFocusable(false);
                cidade.setClickable(false);
            }
        }
    }
}