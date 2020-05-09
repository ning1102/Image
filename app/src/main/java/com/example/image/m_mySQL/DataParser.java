package com.example.image.m_mySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_UI.AdapterSpinner;
import com.example.image.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Boolean> {

   Context c;
    String jsonData;
    GridView gv;
    SearchView searchView;
    Spinner spinner_floor;
    Spinner spinner_group;


    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();


    public DataParser(Context c, String jsonData, GridView gv, SearchView searchView,Spinner spinner_floor,Spinner spinner_group) {
        this.c = c;
        this.jsonData = jsonData;
        this.gv = gv;
        this.searchView = searchView;
        this.spinner_floor = spinner_floor;
        this.spinner_group = spinner_group;
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
            //BIND DATA TO GRIDVIEW
            final CustomAdapter adaper= new CustomAdapter(c,spacecrafts);
            gv.setAdapter(adaper);
            final AdapterSpinner adapterSpinnerer= new AdapterSpinner(c,spacecrafts);
            //BIND DATA TO SearchView
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    adaper.getFilter().filter(newText);
                    return false;
                }
            });

          spinner_floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object item = parent.getItemAtPosition(position);
                    if (position == 0 ){
                        String all = item.toString();
                        Toast toast = Toast.makeText ( c, "เลือก " + all, Toast.LENGTH_LONG );
                        toast.show();
                        adaper.getFilter().filter(null);
                    } else if(position != 0){
                        String ii = item.toString();
                        Toast toast = Toast.makeText ( c, "เลือก " + ii, Toast.LENGTH_LONG );
                        toast.show();
                        final ArrayList<String> listfloor = new ArrayList<>();
                       // listfloor.add(ii);
                        adaper.getFilter().filter(ii);

                    }

                    view.clearFocus();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

           spinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   Object item = parent.getItemAtPosition(position);
                                if (position == 0){
                                    String all = item.toString();
                                    Toast toast = Toast.makeText ( c, "เลือก " + all, Toast.LENGTH_LONG );
                                    toast.show ();
                                    adaper.getFilter().filter(null);

                                }else if(position != 0){
                                    String aa = item.toString();
                                    Toast toast = Toast.makeText ( c, "เลือก "+ aa, Toast.LENGTH_LONG );
                                    toast.show ( );
                                  //  listfloor.add(aa);
                                   // Log.d("TAG" ," array --> "+ listfloor );
                                    adaper.getFilter().filter(aa);
                                }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

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
                String typeShopName = jo.getString("TypeShop_Name");
                String groupName = jo.getString("Group_Name");




                spacecraft = new Spacecraft(id,name,imageUrl,floor_Shop,website,opentime,closetime,description_Shop,tel,picMap,typeShopName,groupName);

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
                spacecraft.setTypeShopName(typeShopName);
                spacecraft.setGroupName(groupName);

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