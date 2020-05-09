package com.example.image.m_DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.image.R;
import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_UI.ImageMap;
import com.example.image.m_UI.PicassoClient;

import java.util.ArrayList;

public class Detail_shop extends AppCompatActivity {

    TextView nameTxt ,idTxt,foorTxt,websiteTxt ,opentimeTxt, closetimeTxt, descriptionTxt,telTxt;
    ImageView  picFloor,imageUrlLogo;



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


        //imageUrlLogo = (ImageView)findViewById(R.id.img_map);



        Intent i = this.getIntent();
        String id = i.getExtras().getString("ID_KEY");
        String name = i.getExtras().getString("NAME_KEY");
        String imageUrl = i.getExtras().getString("IMAGEURL_KEY");
        String floor_Shop = i.getExtras().getString("FLOORSHOP_KEY");
        String website = i.getExtras().getString("WEBSITE_KEY");
        String opentime = i.getExtras().getString("OPENTIME_KEY");
        String closetime = i.getExtras().getString("CLOSETIME_KEY");
        String description_Shop = i.getExtras().getString("DESCRIPTION_KEY");
        String tel = i.getExtras().getString("TEL_KEY");
        String picMap = i.getExtras().getString("PICMAP_KEY");


        idTxt .setText(id);
        nameTxt.setText(name);
        foorTxt.setText(floor_Shop);
        websiteTxt.setText(website);
        opentimeTxt.setText(opentime);
        closetimeTxt.setText(closetime);
        descriptionTxt.setText(description_Shop);
        telTxt.setText(tel);

        picFloor = (ImageView)findViewById(R.id.img_map);
        ImageMap.downloadImageMap(this,picMap,picFloor);

    }


}
