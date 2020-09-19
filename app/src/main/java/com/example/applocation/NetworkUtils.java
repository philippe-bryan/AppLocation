package com.example.applocation;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl) throws IOException {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }
        finally {
            urlConnection.disconnect();
        }
    }

    public static CarregaDados localizar(String id) throws JSONException, IOException {
        String resposta = request("https://superheroapi.com/api/1022707078160850/"+id+"/biography");
        JSONObject obj = new JSONObject(resposta);
        String name = obj.get("name").toString();
        String fullName = obj.get("full-name").toString();
        String placeOfBirth = obj.get("place-of-birth").toString();
        return new CarregaDados(name, fullName, placeOfBirth);
    }
}
