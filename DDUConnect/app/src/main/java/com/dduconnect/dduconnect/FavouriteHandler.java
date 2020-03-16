package com.dduconnect.dduconnect;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavouriteHandler {
    public static ArrayList<Model> favouriteList;

    public static void saveFavouriteList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favouriteList);
        editor.putString("fav list", json);
        editor.apply();
    }

    public static void loadFavouriteList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("fav list", null);
        Type type = new TypeToken<ArrayList<Model>>() {}.getType();
        favouriteList = gson.fromJson(json, type);
        if (favouriteList == null) {
            favouriteList = new ArrayList<>();
        }
    }

    public static int isfav(Model model){
        for (int i=0;i<favouriteList.size();i++){
            if(favouriteList.get(i).title.equals(model.title))
            {
                return i;
            }

        }
        return -1;
    }
}
