package com.example.image.m_mySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void, Void,String > {


    Context c;
    String urlAddress;
    GridView gv;
    SearchView searchView;
    Spinner spinner_floor;
    Spinner spinner_group;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, GridView gv, SearchView searchView,Spinner spinner_floor,Spinner spinner_group) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.gv = gv;
        this.searchView = searchView;
        this.spinner_floor =spinner_floor;
        this.spinner_group = spinner_group;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        pd.dismiss();
        Log.i("TAG","jsonData-->> " +jsonData);

        if (jsonData == null)
        {
            Toast.makeText(c,"Unsuccessful, No Data Retrieved",Toast.LENGTH_SHORT).show();
        }else {
            //PARSE
            Log.i("Show","jsonData-->> " +jsonData);
            DataParser parser = new DataParser(c,jsonData,gv,searchView,spinner_floor,spinner_group);
            parser.execute();
        }
    }

    private String downloadData()
    {

        HttpURLConnection connection = NetConnector.connect(urlAddress);
        if ( connection == null)
        {
            return null;
        }
        try
        {
            InputStream inputStream = null;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                    "iso-8859-1"),8);

            StringBuilder jsonData = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                jsonData.append(line + "\n");
            }
            inputStream.close();

            Log.d("JSON Result" , jsonData.toString());


            return jsonData.toString();

        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
