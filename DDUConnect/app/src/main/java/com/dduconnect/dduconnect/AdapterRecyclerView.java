package com.dduconnect.dduconnect;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class AdapterRecyclerView extends RecyclerView.Adapter {

    private ArrayList<Model> dataset;
    private Context mContext;
    public AdapterRecyclerView(ArrayList<Model> mlist, Context context)
    {
        this.dataset=mlist;
        this.mContext=context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle,date,category;
        LinearLayout card;
        ImageView imageView;

        public ImageTypeViewHolder(View itemView){
            super(itemView);
           // this.date=itemView.findViewById(R.id.date);

         //   this.category=(TextView) itemView.findViewById(R.id.categories_name1);
            this.title=(TextView) itemView.findViewById(R.id.post_title);
          //  this.subtitle=(TextView) itemView.findViewById(R.id.subtitle);
           // this.card=(LinearLayout) itemView.findViewById(R.id.card2);
            //card.bringToFront();
            this.imageView=(ImageView) itemView.findViewById(R.id.post_img);
            this.category=itemView.findViewById(R.id.cat_view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_details,parent,false);
        return new ImageTypeViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Model object =dataset.get(position);
        //((ImageTypeViewHolder) holder).date.setText(object.date);
        ((ImageTypeViewHolder) holder).title.setText(object.title);
        final String cat=Constants.getCategory(object.getCategory());
        ((ImageTypeViewHolder) holder).category.setText(cat);
       // ((ImageTypeViewHolder) holder).subtitle.setText(object.subtitle);
       // ((ImageTypeViewHolder) holder).category.setText(object.categori);
        final String url=object.Image;
        final String link=object.link;

        Picasso.with(mContext).load(url).into(((ImageTypeViewHolder) holder).imageView);
        ((ImageTypeViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
//                    Intent intent = new Intent(mContext,PostContent.class);
//                    intent.putExtra("catid",cat);
//                    intent.putExtra("title", object.getTitle());
//                    intent.putExtra("id", object.getId().toString());
//                    intent.putExtra("date", object.getDate());
//                    intent.putExtra("img", url);
//                    mContext.startActivity(intent);
                    Intent intent=new Intent(mContext,PostWebView.class);
                    intent.putExtra("itemlink",object.link);
                    mContext.startActivity(intent);
                } else{
                    Toast.makeText(mContext, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((ImageTypeViewHolder)  holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
//                   Intent intent = new Intent(mContext,PostContent.class);
//                    intent.putExtra("catid",cat);
//                    intent.putExtra("title", object.getTitle());
//                    intent.putExtra("id", object.getId().toString());
//                    intent.putExtra("date", object.getDate());
//                    intent.putExtra("img", url);
//                    mContext.startActivity(intent);

                    Intent intent=new Intent(mContext,PostWebView.class);
                    intent.putExtra("itemlink",object.link);
                    mContext.startActivity(intent);
                } else{
                    Toast.makeText(mContext, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

//
//        ((ImageTypeViewHolder)  holder).subtitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isOnline()) {
//                    //Intent intent = new Intent(mContext, WPPostDetails.class);
//                    //intent.putExtra("itemlink", link);
//                    //mContext.startActivity(intent);
//                } else{
//                    Toast.makeText(mContext, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
