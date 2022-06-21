package com.sibaamap.app_food.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.adapters.ViewAllAdapter;
import com.sibaamap.app_food.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    private Toolbar toolbar;

    private RecyclerView viewAllRec;
    private ViewAllAdapter mViewAllAdapter;
    private List<ViewAllModel> mViewAllModels;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        getWindow().setStatusBarColor(getColor(R.color.purple_500));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        toolbar = findViewById(R.id.toolbar_view_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();


        viewAllRec = findViewById(R.id.view_all_rec);
        viewAllRec.setVisibility(View.GONE);
        viewAllRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mViewAllModels = new ArrayList<>();
        mViewAllAdapter = new ViewAllAdapter(this,mViewAllModels);
        viewAllRec.setAdapter(mViewAllAdapter);
        getCategories();
        getRecommended();
        getDellyMeal();
        //getting burger
        //recommended
    }
    private void getDellyMeal(){
        String typeD = getIntent().getStringExtra("type1");
        if(typeD != null && typeD.equalsIgnoreCase("lunch")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","lunch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);

                    }

                }
            });
        }
        if(typeD != null && typeD.equalsIgnoreCase("breakfast")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","breakfast").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        if(typeD != null && typeD.equalsIgnoreCase("dinner")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","dinner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        if(typeD != null && typeD.equalsIgnoreCase("coffee")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","coffee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }


    }
    private void getRecommended(){
        String type1 = getIntent().getStringExtra("kind");
        if(type1 != null && type1.equalsIgnoreCase("sandwich")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","sandwich1").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        if(type1 != null && type1.equalsIgnoreCase("hamberger")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","burgerbo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        if(type1 != null && type1.equalsIgnoreCase("pizza")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","pizzalover").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }
    private void getCategories(){
        String type = getIntent().getStringExtra("type");
        if(type != null && type.equalsIgnoreCase("hamberger")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","burger").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        //getting ice cream
        if(type != null && type.equalsIgnoreCase("icecream")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","icecream").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        //getting fries
        if(type != null && type.equalsIgnoreCase("fries")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","fries").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        //getting sandwich
        if(type != null && type.equalsIgnoreCase("sandwich")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","sandwich").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        //getting pizza
        if(type != null && type.equalsIgnoreCase("pizza")){
            firebaseFirestore.collection("all_items").whereEqualTo("type","pizza").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        mViewAllModels.add(viewAllModel);
                        mViewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllRec.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}