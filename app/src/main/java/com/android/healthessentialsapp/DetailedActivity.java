package com.android.healthessentialsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.healthessentialsapp.adapter.TrendingNowAdapter;
import com.android.healthessentialsapp.model.TrendingInHandSanitizersModel;
import com.android.healthessentialsapp.model.TrendingInMasksModel;
import com.android.healthessentialsapp.model.TrendingInSpraysModel;
import com.android.healthessentialsapp.model.TrendingInWipesModel;
import com.android.healthessentialsapp.model.TrendingNowModel;
import com.android.healthessentialsapp.model.ViewAllModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    ImageView product_image, star_rating;
    TextView product_name, product_price, description, link, num_reviews;
    Button back_btn, buy_btn;
    RecyclerView recommended_recycler;
    FirebaseFirestore db;

    //reusing model and adapter but for recommended items recycler
    List<TrendingNowModel> trendingNowModelList;
    TrendingNowAdapter trendingNowAdapter;

    ViewAllModel viewAllModel = null;
    TrendingNowModel trendingNowModel = null;
    TrendingInMasksModel trendingInMasksModel = null;
    TrendingInHandSanitizersModel trendingInHandSanitizersModel = null;
    TrendingInWipesModel trendingInWipesModel = null;
    TrendingInSpraysModel trendingInSpraysModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        db = FirebaseFirestore.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel)
            viewAllModel = (ViewAllModel) object;
        else if(object instanceof TrendingNowModel)
            trendingNowModel = (TrendingNowModel) object;
        else if(object instanceof TrendingInMasksModel)
            trendingInMasksModel = (TrendingInMasksModel) object;
        else if(object instanceof TrendingInHandSanitizersModel)
            trendingInHandSanitizersModel = (TrendingInHandSanitizersModel) object;
        else if(object instanceof TrendingInWipesModel)
            trendingInWipesModel = (TrendingInWipesModel) object;
        else if(object instanceof TrendingInSpraysModel)
            trendingInSpraysModel = (TrendingInSpraysModel) object;

        product_image = findViewById(R.id.product_image);
        star_rating = findViewById(R.id.star_rating);
        product_name = findViewById(R.id.product_name);
        num_reviews = findViewById(R.id.num_reviews);
        product_price = findViewById(R.id.product_price);
        link = findViewById(R.id.amazon_link);
        description = findViewById(R.id.description);
        back_btn = findViewById(R.id.back_btn);
        buy_btn = findViewById(R.id.buy_btn);
        recommended_recycler = findViewById(R.id.recommended_recycler);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
                }
        });

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(viewAllModel.getRating()).into(star_rating);
            product_name.setText(viewAllModel.getName());
            product_price.setText("Price: " +viewAllModel.getPrice());
            description.setText(viewAllModel.getDescription());
            num_reviews.setText(viewAllModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(viewAllModel.getLink()));
                    startActivity(intent);
                }
            });
        }
        else if(trendingNowModel != null){
            Glide.with(getApplicationContext()).load(trendingNowModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(trendingNowModel.getRating()).into(star_rating);
            product_name.setText(trendingNowModel.getName());
            product_price.setText("Price: " +trendingNowModel.getPrice());
            description.setText(trendingNowModel.getDescription());
            num_reviews.setText(trendingNowModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trendingNowModel.getLink()));
                    startActivity(intent);
                }
            });
        }
        else if(trendingInMasksModel != null){
            Glide.with(getApplicationContext()).load(trendingInMasksModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(trendingInMasksModel.getRating()).into(star_rating);
            product_name.setText(trendingInMasksModel.getName());
            product_price.setText("Price: " +trendingInMasksModel.getPrice());
            description.setText(trendingInMasksModel.getDescription());
            num_reviews.setText(trendingInMasksModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trendingInMasksModel.getLink()));
                    startActivity(intent);
                }
            });
        }
        else if(trendingInHandSanitizersModel != null){
            Glide.with(getApplicationContext()).load(trendingInHandSanitizersModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(trendingInHandSanitizersModel.getRating()).into(star_rating);
            product_name.setText(trendingInHandSanitizersModel.getName());
            product_price.setText("Price: " +trendingInHandSanitizersModel.getPrice());
            description.setText(trendingInHandSanitizersModel.getDescription());
            num_reviews.setText(trendingInHandSanitizersModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trendingInHandSanitizersModel.getLink()));
                    startActivity(intent);
                }
            });
        }
        else if(trendingInWipesModel != null){
            Glide.with(getApplicationContext()).load(trendingInWipesModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(trendingInWipesModel.getRating()).into(star_rating);
            product_name.setText(trendingInWipesModel.getName());
            product_price.setText("Price: " +trendingInWipesModel.getPrice());
            description.setText(trendingInWipesModel.getDescription());
            num_reviews.setText(trendingInWipesModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trendingInWipesModel.getLink()));
                    startActivity(intent);
                }
            });
        }
        else if(trendingInSpraysModel != null){
            Glide.with(getApplicationContext()).load(trendingInSpraysModel.getImage()).into(product_image);
            Glide.with(getApplicationContext()).load(trendingInSpraysModel.getRating()).into(star_rating);
            product_name.setText(trendingInSpraysModel.getName());
            product_price.setText("Price: " +trendingInSpraysModel.getPrice());
            description.setText(trendingInSpraysModel.getDescription());
            num_reviews.setText(trendingInSpraysModel.getNum_reviews());
            buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trendingInSpraysModel.getLink()));
                    startActivity(intent);
                }
            });
        }

        //recommended items
        recommended_recycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false));
        trendingNowModelList = new ArrayList<>();
        trendingNowAdapter = new TrendingNowAdapter(this,trendingNowModelList);
        recommended_recycler.setAdapter(trendingNowAdapter);

        db.collection("recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                TrendingNowModel trendingNowModel = document.toObject(TrendingNowModel.class);
                                trendingNowModelList.add(trendingNowModel);
                                trendingNowAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.firstFragment:
                        Intent intent = new Intent(DetailedActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.secondFragment:
                        Intent intent2 = new Intent(DetailedActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}