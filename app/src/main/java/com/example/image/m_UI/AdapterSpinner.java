package com.example.image.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.image.R;
import com.example.image.m_DataObject.Spacecraft;
import com.example.image.m_DetailActivity.Detail_shop;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    ArrayList<Spacecraft> filterList;
    CustomFilter filter;


    LayoutInflater inflater;

    public AdapterSpinner(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        this.filterList = spacecrafts;

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
        return spacecrafts.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
            //convertView = inflater.inflate(R.layout.model,parent,false);
        }
        //SET DATA
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
                        s.getOpentime(),s.getClosetime(),s.getDescription_Shop(),s.getTel(),s.getPicMap(),s.getTypeShopName(),s.getGroupName());

            }
        });


        return convertView;
    }

    private void openDetailActivity(String id, String name, String imageUrl, String floor_shop, String website, String opentime, String closetime,
                                            String description_shop, String tel, String picMap, String typeShopName, String groupName) {

        Intent i = new Intent(c, Detail_shop.class);

        //PACK DATA
        i.putExtra("ID_KEY",id);
        i.putExtra("NAME_KEY",name);
        i.putExtra("IMAGEURL_KEY",imageUrl);
        i.putExtra("FLOORSHOP_KEY",floor_shop);
        i.putExtra("WEBSITE_KEY",website);
        i.putExtra("OPENTIME_KEY",opentime);
        i.putExtra("CLOSETIME_KEY",closetime);
        i.putExtra("DESCRIPTION_KEY",description_shop);
        i.putExtra("TEL_KEY",tel);
        i.putExtra("PICMAP_KEY",picMap);
        i.putExtra("TYPESHOPNAME_KEY",typeShopName);
        i.putExtra("GROUPNAME_KEY",groupName);



        c.startActivity(i);
    }


    @Override
    public Filter getFilter() {

        if (filter == null)
        {
            filter  = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() >0)
            {
                //Constraint To Upper
                constraint = constraint.toString().toUpperCase();

                ArrayList<Spacecraft> filters = new ArrayList<Spacecraft>();

                //Filtering
                for (int i =0 ; i < filterList.size();i++)
                {
                    if (filterList.get(i).getFloor_Shop().toUpperCase().contains(constraint) && filterList.get(i).getGroupName().toUpperCase().contains(constraint))
                    {
                        Spacecraft ss = new Spacecraft(filterList.get(i).getId(),filterList.get(i).getName(),filterList.get(i).getImageUrl(),filterList.get(i).getFloor_Shop(),filterList.get(i).getWebsite(),
                                filterList.get(i).getOpentime(),filterList.get(i).getClosetime(),filterList.get(i).getDescription_Shop(),filterList.get(i).getTel(),filterList.get(i).getPicMap(),filterList.get(i).getTypeShopName(),
                                filterList.get(i).getGroupName());
                        filters.add(ss);

                    }
                    results.count = filters.size();
                    results.values = filters;
                }
            }else
            {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            spacecrafts = (ArrayList<Spacecraft>) results.values;
            notifyDataSetChanged();

        }


    }



}
