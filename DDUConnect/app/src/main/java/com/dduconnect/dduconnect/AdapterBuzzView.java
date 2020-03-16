package com.dduconnect.dduconnect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dduconnect.dduconnect.interfaces.WPbuzzContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBuzzView extends RecyclerView.Adapter  {

    private ArrayList<Model> dataset;
    private Context mContext;
    public AdapterBuzzView(ArrayList<Model> mlist, Context context)
    {
        this.dataset=mlist;
        this.mContext=context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle,date,read_more;
        ImageView imageView;

        public ImageTypeViewHolder(View itemView){
            super(itemView);
            this.date=itemView.findViewById(R.id.date);
            this.title=(TextView) itemView.findViewById(R.id.title);
            this.subtitle=(TextView) itemView.findViewById(R.id.subtitle);
            this.imageView=(ImageView) itemView.findViewById(R.id.icon);
            this.read_more=itemView.findViewById(R.id.read_more_buzz);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_buzz,parent,false);
        return new AdapterBuzzView.ImageTypeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Model object =dataset.get(position);
        ((ImageTypeViewHolder) holder).date.setText(object.date);
        ((ImageTypeViewHolder) holder).title.setText(object.title);
        ((ImageTypeViewHolder) holder).subtitle.setText(object.content.substring(0,250)+"....");
        final String url=object.getImage();
        final String link=object.link;

        Picasso.with(mContext).load(url).into(((ImageTypeViewHolder) holder).imageView);
        ((ImageTypeViewHolder) holder).read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                    Intent intent = new Intent(mContext,BuzzContent.class);
                    intent.putExtra("catid", object.categori);
                    intent.putExtra("title", object.getTitle());
                    intent.putExtra("id", object.getId().toString());
                    intent.putExtra("date", object.getDate());
                    intent.putExtra("img", url);
                    intent.putExtra("content", object.content);
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
                    Intent intent = new Intent(mContext,BuzzContent.class);
                    intent.putExtra("catid", object.categori);
                    intent.putExtra("title", object.getTitle());
                    intent.putExtra("id", object.getId().toString());
                    intent.putExtra("date", object.getDate());
                    intent.putExtra("img", url);
                    intent.putExtra("content", object.content);
                    mContext.startActivity(intent);
                } else{
                    Toast.makeText(mContext, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ((ImageTypeViewHolder)  holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    Intent intent = new Intent(mContext,BuzzContent.class);
                    intent.putExtra("catid", object.categori);
                    intent.putExtra("title", object.getTitle());
                    intent.putExtra("id", object.getId().toString());
                    intent.putExtra("date", object.getDate());
                    intent.putExtra("img", url);
                    intent.putExtra("content", object.content);
                    mContext.startActivity(intent);
                } else{
                    Toast.makeText(mContext, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
