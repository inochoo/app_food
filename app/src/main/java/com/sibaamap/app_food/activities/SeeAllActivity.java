package com.sibaamap.app_food.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.adapters.DailymealAdapter;
import com.sibaamap.app_food.adapters.SeeMoreAdapter;
import com.sibaamap.app_food.models.DailymealModel;
import com.sibaamap.app_food.models.SeeMoreModel;

import java.util.ArrayList;
import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    private RecyclerView seeMoreRec;
    private List<SeeMoreModel> mSeeMoreModels;
    private SeeMoreAdapter seeMoreAdapter;

    private DatabaseReference database;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        getWindow().setStatusBarColor(getColor(R.color.purple_500));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        seeMoreRec = findViewById(R.id.detailed_rec_all);
        db = FirebaseFirestore.getInstance();

        seeMoreRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mSeeMoreModels = new ArrayList<>();
        seeMoreAdapter = new SeeMoreAdapter(this, mSeeMoreModels);
        seeMoreRec.setAdapter(seeMoreAdapter);

        db.collection("see_more")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                SeeMoreModel seeMoreModel = documentSnapshot.toObject(SeeMoreModel.class);
                                mSeeMoreModels.add(seeMoreModel);
                                seeMoreAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(SeeAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }
}