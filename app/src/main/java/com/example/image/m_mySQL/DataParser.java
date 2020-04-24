package com.example.image.m_mySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Boolean> {

   Context c;
    String jsonData;
    GridView gv;

    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    public DataParser(Context c, String jsonData, GridView gv) {
        this.c = c;
        this.jsonData = jsonData;
        this.gv = gv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parse...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        pd.dismiss();

        if (parsed)
        {
            //BIND DATA TO LISTVIEW
            CustomAdapter adaper= new CustomAdapter(c,spacecrafts);
            gv.setAdapter(adaper);
            Log.i("Show","อ่านค่า Result  =  " +parsed);
        }else{

            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData()
    {
        try
        {

            JSONObject jsonObject  = new JSONObject(jsonData);
            JSONArray exArray = jsonObject.getJSONArray("shopdetail");

            spacecrafts.clear();
            Spacecraft spacecraft;



            for ( int i =0; i < exArray.length(); i++ ) {
                JSONObject jo = exArray.getJSONObject(i);

                String id = jo.getString("Shop_id");
                String name = jo.getString("Shop_name");
                String imageUrl = jo.getString("PictureShop");
                String floor_Shop = jo.getString("Floor_Shop");
                String website = jo.getString("Website");
                String opentime = jo.getString("Opentime");
                String closetime = jo.getString("Closetime");
                String description_Shop = jo.getString("Description_Shop");
                String tel = jo.getString("Tel");
                String picMap = jo.getString("Pic_floor");


                spacecraft = new Spacecraft();

               spacecraft.setId(id);
                spacecraft.setName(name);
                spacecraft.setFloor_Shop(floor_Shop);
                spacecraft.setWebsite(website);
                spacecraft.setOpentime(opentime);
                spacecraft.setClosetime(closetime);
                spacecraft.setDescription_Shop(description_Shop);
                spacecraft.setTel(tel);
                spacecraft.setImageUrl(imageUrl);
                spacecraft.setPicMap(picMap);

                spacecrafts.add(spacecraft);
                // }
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

}