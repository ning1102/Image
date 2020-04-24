package com.example.image.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.example.image.R;
import com.squareup.picasso.Picasso;

public class ImageMap {

    public static void downloadImage(Context c, String picMap, ImageView picturemap)
    {
        if (picMap != null && picMap.length() >0)
        {
            Picasso.with(c).load(picMap).placeholder(R.drawable.placeholder).into(picturemap);
        }else{
            Picasso.with(c).load(R.drawable.placeholder).into(picturemap);
        }
    }
}
