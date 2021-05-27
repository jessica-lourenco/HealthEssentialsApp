package com.android.healthessentialsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.healthessentialsapp.adapter.TrendingInHandSanitizersAdapter;
import com.android.healthessentialsapp.adapter.TrendingInMasksAdapter;
import com.android.healthessentialsapp.adapter.TrendingInSpraysAdapter;
import com.android.healthessentialsapp.adapter.TrendingInWipesAdapter;
import com.android.healthessentialsapp.adapter.TrendingNowAdapter;
import com.android.healthessentialsapp.model.TrendingInHandSanitizersModel;
import com.android.healthessentialsapp.model.TrendingInMasksModel;
import com.android.healthessentialsapp.model.TrendingInSpraysModel;
import com.android.healthessentialsapp.model.TrendingInWipesModel;
import com.android.healthessentialsapp.model.TrendingNowModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView trendingNowRecycler, trendingInMasksRecycler, trendingInWipesRecycler, trendingInSpraysRecycler, trendingInHandSanitizersRecycler;
    Button btn1, btn2, btn3, btn4;
    FirebaseFirestore db;

    //trending now items
    List<TrendingNowModel> trendingNowModelList;
    TrendingNowAdapter trendingNowAdapter;

    //trending in masks items
    List<TrendingInMasksModel> trendingInMasksModelList;
    TrendingInMasksAdapter trendingInMasksAdapter;

    //trending in hand sanitizers items
    List<TrendingInHandSanitizersModel> trendingInHandSanitizersModelList;
    TrendingInHandSanitizersAdapter trendingInHandSanitizersAdapter;

    //trending in wipes items
    List<TrendingInWipesModel> trendingInWipesModelList;
    TrendingInWipesAdapter trendingInWipesAdapter;

    //trending in sprays items
    List<TrendingInSpraysModel> trendingInSpraysModelList;
    TrendingInSpraysAdapter trendingInSpraysAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();

        trendingNowRecycler = view.findViewById(R.id.trending_now_recycler);
        trendingInMasksRecycler = view.findViewById(R.id.trending_now_recycler2);
        trendingInHandSanitizersRecycler = view.findViewById(R.id.trending_now_recycler3);
        trendingInWipesRecycler = view.findViewById(R.id.trending_now_recycler4);
        trendingInSpraysRecycler = view.findViewById(R.id.trending_now_recycler5);

        btn1 = view.findViewById(R.id.masks);
        btn2 = view.findViewById(R.id.hand_sanitizers);
        btn3 = view.findViewById(R.id.wipes);
        btn4 = view.findViewById(R.id.disinfectant_sprays);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllMasks.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllHandSanitizers.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllWipes.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllSprays.class);
                startActivity(intent);
            }
        });

        //trending now items
        trendingNowRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingNowModelList = new ArrayList<>();
        trendingNowAdapter = new TrendingNowAdapter(getActivity(),trendingNowModelList);
        trendingNowRecycler.setAdapter(trendingNowAdapter);

        db.collection("trending_now")
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
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //trending now items
        trendingNowRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingNowModelList = new ArrayList<>();
        trendingNowAdapter = new TrendingNowAdapter(getActivity(),trendingNowModelList);
        trendingNowRecycler.setAdapter(trendingNowAdapter);

        db.collection("masks")
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
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // trending in masks collection
        trendingInMasksRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingInMasksModelList = new ArrayList<>();
        trendingInMasksAdapter = new TrendingInMasksAdapter(getActivity(), trendingInMasksModelList);
        trendingInMasksRecycler.setAdapter(trendingInMasksAdapter);

        db.collection("masks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                TrendingInMasksModel trendingInMasksModel = document.toObject(TrendingInMasksModel.class);
                                trendingInMasksModelList.add(trendingInMasksModel);
                                trendingInMasksAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // trending in wipes collection
        trendingInWipesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingInWipesModelList = new ArrayList<>();
        trendingInWipesAdapter = new TrendingInWipesAdapter(getActivity(), trendingInWipesModelList);
        trendingInWipesRecycler.setAdapter(trendingInWipesAdapter);

        db.collection("wipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                TrendingInWipesModel trendingInWipesModel = document.toObject(TrendingInWipesModel.class);
                                trendingInWipesModelList.add(trendingInWipesModel);
                                trendingInWipesAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // trending in sprays collection
        trendingInSpraysRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingInSpraysModelList = new ArrayList<>();
        trendingInSpraysAdapter = new TrendingInSpraysAdapter(getActivity(), trendingInSpraysModelList);
        trendingInSpraysRecycler.setAdapter(trendingInSpraysAdapter);

        db.collection("sprays")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                TrendingInSpraysModel trendingInSpraysModel = document.toObject(TrendingInSpraysModel.class);
                                trendingInSpraysModelList.add(trendingInSpraysModel);
                                trendingInSpraysAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        // trending in hand sanitizers collection
        trendingInHandSanitizersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        trendingInHandSanitizersModelList = new ArrayList<>();
        trendingInHandSanitizersAdapter = new TrendingInHandSanitizersAdapter(getActivity(), trendingInHandSanitizersModelList);
        trendingInHandSanitizersRecycler.setAdapter(trendingInHandSanitizersAdapter);

        db.collection("hand_sanitizers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                TrendingInHandSanitizersModel trendingInHandSanitizersModel = document.toObject(TrendingInHandSanitizersModel.class);
                                trendingInHandSanitizersModelList.add(trendingInHandSanitizersModel);
                                trendingInHandSanitizersAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return view;
    }

}
