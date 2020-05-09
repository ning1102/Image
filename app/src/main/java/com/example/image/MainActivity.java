package com.example.image;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_UI.CustomAdapter;
import com.example.image.m_mySQL.Downloader;
import com.example.image.m_mySQL.NetConnector;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

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
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  static final String  locallhost = "http://192.168.43.79/" ;
    private static final String urlAddress = "http://192.168.43.79/testdb/detail.php";
    GridView gv;
    SearchView searchView ;
    Spinner spinner_floor;
    Spinner spinner_group;
    ArrayList<String> FloorList;
    ArrayList<String> GroupList;



    //defind array adapter



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner
        FloorList = new ArrayList<>();
        FloorList.add(0,"ทั้งหมด");
        GroupList = new ArrayList<>();
        GroupList.add(0,"ทั้งหมด");
        spinner_floor = findViewById(R.id.spinfloor);
        spinner_group = findViewById(R.id.spingroup);
        loadSpinnerData();

        gv = (GridView)findViewById(R.id.json_gridview);
        //search view
        searchView = (SearchView)findViewById(R.id.searchShop);

        //spinner






        new Downloader(MainActivity.this,urlAddress,gv,searchView,spinner_floor,spinner_group).execute();



    }




    //เชื่อมฐานข้อมูลด้วย Ion และแสดงข้อมูล
    private void loadSpinnerData() {

        Ion.with(getApplicationContext())
                .load(locallhost +"testdb/spinfloor.php")
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        String aa = result.getResult();

                        try{
                            JSONObject jsonfloor = new JSONObject(aa);
                            JSONArray jsonArrayfloor = jsonfloor.getJSONArray("Android");
                            for (int i = 0 ; i < jsonArrayfloor.length(); i++){
                                JSONObject jsonObjectfloor = jsonArrayfloor.getJSONObject(i);
                                String floor_shop =  jsonObjectfloor.getString("floor_shop");

                                Log.i("LG",floor_shop);

                                FloorList.add(floor_shop);

                            }

                            spinner_floor.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, FloorList));



                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                    }
                });


        Ion.with(getApplicationContext())
                .load(locallhost + "testdb/spingroup.php")
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        String typeShop = result.getResult();

                        try{
                            JSONObject jsonType = new JSONObject(typeShop);
                            JSONArray jsonArrayType = jsonType.getJSONArray("Android");
                            for (int i = 0 ; i < jsonArrayType.length(); i++){
                                JSONObject jsonObjectType = jsonArrayType.getJSONObject(i);
                                String shopTypefood =  jsonObjectType.getString("group_name");

                                Log.i("zz",shopTypefood);

                                GroupList.add(shopTypefood);

                            }
                            spinner_group.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, GroupList));


                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
    }
    //จบเชื่อมฐานข้อมูลด้วย Ion และแสดงข้อมูล


}
