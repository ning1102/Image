package com.example.image.m_DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.image.R;
import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_UI.ImageMap;
import com.example.image.m_UI.PicassoClient;

import java.util.ArrayList;

public class Detail_shop extends AppCompatActivity {

    TextView nameTxt ,idTxt,foorTxt,websiteTxt ,opentimeTxt, closetimeTxt, descriptionTxt,telTxt;
    ImageView imageUrlLogo , picFloor;


    Context c;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        nameTxt = (TextView) findViewById(R.id.Shop_name);
        idTxt = (TextView) findViewById(R.id.Shop_id);
        foorTxt = (TextView) findViewById(R.id.Shop_floor);
        websiteTxt = (TextView) findViewById(R.id.website);
        opentimeTxt = (TextView) findViewById(R.id.opentime);
        closetimeTxt = (TextView) findViewById(R.id.closetime);
        descriptionTxt = (TextView) findViewById(R.id.description);
        telTxt = (TextView) findViewById(R.id.tel);

       // picFloor = (ImageView)findViewById(R.id.img_map);
        imageUrlLogo = (ImageView)findViewById(R.id.img_map);


        Intent i = this.getIntent();
        String name = i.getExtras().getString("ID_KEY");
        String imageUrl = i.getExtras().getString("IMAGEURL_KEY");
        String floor_Shop = i.getExtras().getString("FLOORSHOP_KEY");
        String website = i.getExtras().getString("WEBSITE_KEY");
        String opentime = i.getExtras().getString("OPENTIME_KEY");
        String closetime = i.getExtras().getString("CLOSETIME_KEY");
        String description_Shop = i.getExtras().getString("DESCRIPTION_KEY");
        String tel = i.getExtras().getString("TEL_KEY");
        String picMap = i.getExtras().getString("PICMAP_KEY");

        nameTxt.setText(name);
        idTxt .setText(floor_Shop);
        foorTxt.setText(website);
        websiteTxt.setText(opentime);
        opentimeTxt.setText(closetime);
        closetimeTxt.setText(closetime);
        descriptionTxt.setText(description_Shop);
        telTxt.setText(tel);



        PicassoClient.downloadImage(this,imageUrl,imageUrlLogo);
    }
}
