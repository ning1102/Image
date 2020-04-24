package com.example.image.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.image.R;
import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_DetailActivity.Detail_shop;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(c).inflate(R.layout.model,parent,false);

            //convertView = inflater.inflate(R.layout.model,parent,false);

        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);
        ImageView img = (ImageView) convertView.findViewById(R.id.logoImage);





        final Spacecraft s = (Spacecraft) this.getItem(position);

        //BIND DATA'
        //Spacecraft spacecraft = spacecrafts.get(position);
        nameTxt.setText(s.getName());

        //IMG
        PicassoClient.downloadImage(c,s.getImageUrl(),img);



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(s.getId(),s.getName(),s.getImageUrl(),s.getFloor_Shop(),s.getWebsite(),
                        s.getOpentime(),s.getClosetime(),s.getDescription_Shop(),s.getTel(),s.getPicMap());
            }
        });

        return convertView;
    }

    private void openDetailActivity(String id, String name, String imageUrl,String floor_Shop,
                                    String website,String opentime, String  closetime, String  description_Shop,
                                    String  tel ,String picMap)
    {
        Intent i = new Intent(c, Detail_shop.class);

        //PACK DATA
        i.putExtra("ID_KEY",id);
        i.putExtra("NAME_KEY",name);
        i.putExtra("IMAGEURL_KEY",imageUrl);
        i.putExtra("FLOORSHOP_KEY",floor_Shop);
        i.putExtra("WEBSITE_KEY",website);
        i.putExtra("OPENTIME_KEY",opentime);
        i.putExtra("CLOSETIME_KEY",closetime);
        i.putExtra("DESCRIPTION_KEY",description_Shop);
        i.putExtra("TEL_KEY",tel);
        i.putExtra("PICMAP_KEY",picMap);

      //  i.putExtra("PICFLOOR_KEY",Pic_floor);

        c.startActivity(i);
    }
}
