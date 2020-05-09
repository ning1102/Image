package com.example.image.m_UI;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.image.R;
import com.squareup.picasso.Picasso;

public class ImageMap {

    public static void downloadImageMap(Context c, String picMap, ImageView picFloor)

    {

        Log.i("TAG","picMap -->" +picMap);
        if (picMap != null && picMap.length() >0)
        {
            Picasso.with(c).load(picMap).placeholder(R.drawable.placeholder01).into(picFloor);
        }else{
            Picasso.with(c).load(R.drawable.placeholder01).into(picFloor);
        }
    }
}
