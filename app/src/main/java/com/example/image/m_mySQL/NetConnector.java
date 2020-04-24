package com.example.image.m_mySQL;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class  NetConnector {
    public static HttpURLConnection connect (String urlAddress)  {
        String responce = null;
        try {
            //Log.i("TAG", "urlAddress --> " +urlAddress);
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            connection.setAllowUserInteraction(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(200000);
            connection.setReadTimeout(200000);
            connection.setDoInput(true);

            /*InputStream inputStream = null;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                    "iso-8859-1"),8);

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            inputStream.close();
            Log.d("JSON Result" , stringBuilder.toString());

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray exArray = jsonObject.getJSONArray("shopdetail");

            for ( int i = 0; i < exArray.length(); i++ ){
                JSONObject jsonObj = exArray.getJSONObject(i);
                //  ยังไม่จบนะ

            }*/
            return connection;

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
