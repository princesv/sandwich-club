package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView name;
    TextView alsoKnownAs;
    TextView placeOfOrigin;
    TextView ingredients;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        alsoKnownAs=findViewById(R.id.alsoKnownAs_tv);
        placeOfOrigin=findViewById(R.id.origin_tv);
        ingredients=findViewById(R.id.ingredients_tv);
        description=findViewById(R.id.description_tv);
        name=findViewById(R.id.name_tv);


        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        name.setText(sandwich.getMainName());
        List<String> lAlsoKnownAs=new ArrayList<>();
        lAlsoKnownAs=sandwich.getAlsoKnownAs();
        int p=0;
        for(String string:lAlsoKnownAs) {
            if(p==1){
                alsoKnownAs.append(", ");
            }
            if(p==0){p=1;}
            alsoKnownAs.append(string);


        }
        List<String> lingredients=new ArrayList<>();
        lingredients=sandwich.getIngredients();
        p=0;
        for(String string: lingredients){
            if(p==1){
                ingredients.append(", ");
            }
            if(p==0){p=1;}
            ingredients.append(string);
        }
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        description.setText(sandwich.getDescription());


    }
}
