package com.dduconnect.dduconnect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeetOurTeamAdapter extends RecyclerView.Adapter<MeetOurTeamAdapter.MyViewHolder> {
    private ArrayList<AllTeamModel> allTeamModelList;
    Context contex;
    private int index;

    public MeetOurTeamAdapter(ArrayList<AllTeamModel> allTeamModelList1, Context contex, int index) {
        this.allTeamModelList = allTeamModelList1;
        this.contex = contex;
        this.index = index;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view1 = (View) layoutInflater.inflate(R.layout.profile_card,parent,false);
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(allTeamModelList.get(index).getMembers().get(position).getName());
        holder.post.setText(allTeamModelList.get(index).getMembers().get(position).getPost());
        Glide.with(contex)
                .load(allTeamModelList.get(index).getMembers().get(position).getImage().toString())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return Integer.parseInt(allTeamModelList.get(index).getMembersCount());
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private View view;
        private TextView name,post;
        private CircleImageView image;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.profile_name);
            post = view.findViewById(R.id.profile_post);
            image = view.findViewById(R.id.profile_image);
        }
    }
}
