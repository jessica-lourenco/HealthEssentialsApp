package com.android.healthessentialsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.healthessentialsapp.adapter.TrendingNowAdapter;
import com.android.healthessentialsapp.model.TrendingNowModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public TextView search_field;
    public Button search_btn;
    public RecyclerView resultsRecycler;

    FirebaseFirestore db;

    List<TrendingNowModel> trendingNowModelList;
    TrendingNowAdapter trendingNowAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = FirebaseFirestore.getInstance();

        search_field = findViewById(R.id.search);
        search_btn = findViewById(R.id.search_btn);
        resultsRecycler = findViewById(R.id.results_rec);

        resultsRecycler.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
        trendingNowModelList = new ArrayList<>();
        trendingNowAdapter = new TrendingNowAdapter(SearchActivity.this,trendingNowModelList);
        resultsRecycler.setAdapter(trendingNowAdapter);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trendingNowModelList.clear();

                Query query = db.collection("all_products").orderBy("name").startAt(search_field.getText().toString()).endAt(search_field.getText().toString()+ "\uf8ff");

                query.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        TrendingNowModel trendingNowModel = document.toObject(TrendingNowModel.class);
                                        trendingNowModelList.add(trendingNowModel);
                                        trendingNowAdapter.notifyDataSetChanged();
                                    }
                                } else {

                                    Toast.makeText(SearchActivity.this,"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.firstFragment:
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.secondFragment:
                        Intent intent2 = new Intent(SearchActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}

