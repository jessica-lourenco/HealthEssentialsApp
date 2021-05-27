package com.android.healthessentialsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.healthessentialsapp.adapter.ViewAllAdapter;
import com.android.healthessentialsapp.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllMasks extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    TextView category_name;
    List<ViewAllModel> viewAllMasksModelList;
    ViewAllAdapter viewAllMasksAdapter;
    Button btn;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.view_all_recycler);
        category_name = findViewById(R.id.category_name);
        btn = findViewById(R.id.back_btn);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        viewAllMasksModelList = new ArrayList<>();
        viewAllMasksAdapter = new ViewAllAdapter(this,viewAllMasksModelList);
        recyclerView.setAdapter(viewAllMasksAdapter);

        category_name.setText("MASKS");

        db.collection("masks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ViewAllModel viewAllMasksModel = document.toObject(ViewAllModel.class);
                                viewAllMasksModelList.add(viewAllMasksModel);
                                viewAllMasksAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllMasks.this, MainActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.firstFragment:
                        Intent intent = new Intent(ViewAllMasks.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.secondFragment:
                        Intent intent2 = new Intent(ViewAllMasks.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

}