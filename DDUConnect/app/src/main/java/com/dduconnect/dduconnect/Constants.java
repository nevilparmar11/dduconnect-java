package com.dduconnect.dduconnect;

import java.util.HashMap;
import java.util.List;

public class Constants {
    public static final String BASE_URL="http:dduconnect.in";

    public static final String NEVIL_URL ="https://nevilparmar11.github.io";
    public static final String KIRAN_URL ="https://kiranbhanushali.github.io";
    public static final HashMap<String,String> CATEFORY_NAMES = new HashMap<>();
    static {
        CATEFORY_NAMES.put("79","'Tech' it easy");
        CATEFORY_NAMES.put("111","Dentistry arround the gloabe");
        CATEFORY_NAMES.put("129","Pharmacy: Then & Now");
        CATEFORY_NAMES.put("82","Connect-ions");
        CATEFORY_NAMES.put("83","Fiction");
        CATEFORY_NAMES.put("90","Open Letter");
        CATEFORY_NAMES.put("91","Verses");
        CATEFORY_NAMES.put("81","Writers' Lounge ");
        CATEFORY_NAMES.put("133","Alumni Speaks");
        CATEFORY_NAMES.put("134","DDU Speaks");
        CATEFORY_NAMES.put("135","Hall of Fame");
        CATEFORY_NAMES.put("132","Interview");
        CATEFORY_NAMES.put("121","More Content");
        CATEFORY_NAMES.put("84","Non-Tech");
        CATEFORY_NAMES.put("80","Tech");
    }
    public static String getCategory(List<Integer> cat){
        String catname=CATEFORY_NAMES.get(cat.get(0).toString());
        for (int i=1;i<cat.size();i++){
            catname+="-"+CATEFORY_NAMES.get(cat.get(i).toString());
        }
        return catname;
    }
    public static String getDateFormat(String date){
        String newdate[]=date.split(date);
        return newdate[1]+" "+newdate[2];
    }
}
