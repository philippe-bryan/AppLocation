package com.example.applocation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostraHeroi extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    DataBaseHelper mydb;
    private TextView text_empty;
    private ListView obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_heroi);
        text_empty = findViewById(R.id.lblSemRegistros);
        obj = findViewById(R.id.lvListaHiro);
        mydb = new DataBaseHelper(this);
        atualizaLista();
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    public void atualizaLista() {

        ArrayList array_list = mydb.getAllHiroes();

        if (array_list.isEmpty()) {
            text_empty.setVisibility(View.VISIBLE);
            obj.setVisibility(View.GONE);

        } else {
            text_empty.setVisibility(View.GONE);

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);


            obj.setAdapter(arrayAdapter);
            obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    int id_To_Search = arg2 + 1;

                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("idHiro", id_To_Search);

                    Intent intent = new Intent(getApplicationContext(), InfoHiro.class);

                    intent.putExtras(dataBundle);
                    startActivity(intent);
                }
            });
            obj.setVisibility(View.VISIBLE);
        }
    }

    public void DeleteAllOnClick(View voltar){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteAll)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mydb.deleteAll();
                        Toast.makeText(getApplicationContext(), R.string.delete_ok,
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        AlertDialog d = builder.create();
        d.setTitle(R.string.deleteHeroi);
        d.show();
        atualizaLista();
    }

    public void VoltarOnClick(View voltar){
        Intent intentReturn = new Intent(this, MainActivity.class);
        startActivity(intentReturn);
    }
}