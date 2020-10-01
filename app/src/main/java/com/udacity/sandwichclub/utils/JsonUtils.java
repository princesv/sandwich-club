package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwitchData=new JSONObject(json);
            JSONObject name=sandwitchData.getJSONObject("name");
            String mainName=name.getString("mainName");
            JSONArray tempAlsoKnownAs=name.getJSONArray("alsoKnownAs");
            int length=tempAlsoKnownAs.length();

                List<String> alsoKnownAs=new ArrayList<>();
                for(int i=0;i<length;i++){
                    alsoKnownAs.add(tempAlsoKnownAs.getString(i));
                }

            String placeOfOrigin=sandwitchData.getString("placeOfOrigin");
            String description=sandwitchData.getString("description");
            String image=sandwitchData.getString("image");
            List<String> ingredients=new ArrayList<>();
            JSONArray tempIngredients=sandwitchData.getJSONArray("ingredients");
            int length2=tempIngredients.length();
            for(int i=0;i<length2;i++){
                ingredients.add(tempIngredients.getString(i));
            }
            Sandwich sandwitch=new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
            return sandwitch;

        }catch (Exception e){e.getStackTrace();}
       return null;
    }
}
