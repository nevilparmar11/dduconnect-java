package com.dduconnect.dduconnect;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.dduconnect.dduconnect.FavouriteHandler.isfav;

class PostByCategoryAdapter extends RecyclerView.Adapter {

    private ArrayList<Model> dataset;
    private Context mContext;
    public PostByCategoryAdapter(ArrayList<Model> mlist, Context context)
    {
        this.dataset=mlist;
        this.mContext=context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView title,subtitle,date,category;
        LinearLayout card;
        ImageView imageView;
        Button fav,read_more;
        public ImageTypeViewHolder(View itemView){
            super(itemView);
            this.read_more=itemView.findViewById(R.id.read_more_btn);
            this.date=itemView.findViewById(R.id.date);
            this.fav=itemView.findViewById(R.id.fav_btn);
            this.category=(TextView) itemView.findViewById(R.id.categories_name1);
            this.title=(TextView) itemView.findViewById(R.id.title);
            this.subtitle=(TextView) itemView.findViewById(R.id.subtitle);
            this.card=(LinearLayout) itemView.findViewById(R.id.card2);
             card.bringToFront();
            this.imageView=(ImageView) itemView.findViewById(R.id.icon);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wp_post_details,parent,false);
        return new ImageTypeViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Model object =dataset.get(position);
        ((ImageTypeViewHolder) holder).date.setText(object.date);
        ((ImageTypeViewHolder) holder).title.setText(object.title);
        ((ImageTypeViewHolder) holder).subtitle.setText(object.subtitle);
        final String cat=Constants.getCategory(object.getCategory());
        ((ImageTypeViewHolder) holder).category.setText(cat);
        final String url=object.Image;
        final String link=object.link;
        if(FavouriteHandler.favouriteList!=null)
        {
            if((isfav(object))!=-1)
            {
                ((ImageTypeViewHolder) holder).fav.setBackground(mContext.getDrawable(R.drawable.ic_favorite_yellow_24dp));
            }else {
                ((ImageTypeViewHolder) holder).fav.setBackground(mContext.getDrawable(R.drawable.ic_favorite_border_black_24dp));
            }
        }
        ((ImageTypeViewHolder) holder).read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
//                    Intent intent = new Intent(mContext,PostContent.class);
//                    intent.putExtra("catid", cat);
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
        ((ImageTypeViewHolder) holder).fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FavouriteHandler.favouriteList!=null){
                    int x=(isfav(object));
                    if(x==-1)
                    {
                        FavouriteHandler.favouriteList.add(object);
                        ((ImageTypeViewHolder) holder).fav.setBackground(mContext.getDrawable(R.drawable.ic_favorite_yellow_24dp));
                        FavouriteHandler.saveFavouriteList(mContext);
                    }
                    else
                    {
                        FavouriteHandler.favouriteList.remove(x);
                        ((ImageTypeViewHolder) holder).fav.setBackground(mContext.getDrawable(R.drawable.ic_favorite_border_black_24dp));
                        FavouriteHandler.saveFavouriteList(mContext);
                    }
                }
                else
                {
                    FavouriteHandler.favouriteList = new ArrayList<Model>();
                    FavouriteHandler.favouriteList.add(object);
                    ((ImageTypeViewHolder) holder).fav.setBackground(mContext.getDrawable(R.drawable.ic_favorite_yellow_24dp));
                    FavouriteHandler.saveFavouriteList(mContext);
                }

            }
        });
        Picasso.with(mContext).load(url).into(((ImageTypeViewHolder) holder).imageView);

        ((ImageTypeViewHolder)  holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
//                    Intent intent = new Intent(mContext,PostContent.class);
//                    intent.putExtra("catid", cat);
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


        ((ImageTypeViewHolder)  holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
//                    Intent intent = new Intent(mContext,PostContent.class);
//                    intent.putExtra("catid", cat);
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
