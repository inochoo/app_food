package com.sibaamap.app_food.ui.galley;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.adapters.DailymealAdapter;
import com.sibaamap.app_food.adapters.HomeHorAdapter;
import com.sibaamap.app_food.models.DailymealModel;
import com.sibaamap.app_food.models.HomeHorModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {

    private RecyclerView dailyMealRec;
    private List<DailymealModel> mDailyMealModels;
    private DailymealAdapter dailymealAdapter;

    private DatabaseReference database;
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daily_meal_fragment,container,false);

        dailyMealRec = root.findViewById(R.id.daily_meal_rec);
        db = FirebaseFirestore.getInstance();

        //dailyMeal
        dailyMealRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        mDailyMealModels = new ArrayList<>();
        dailymealAdapter = new DailymealAdapter(getActivity(),mDailyMealModels);
        dailyMealRec.setAdapter(dailymealAdapter);

        db.collection("daily_meal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                DailymealModel dailymealModel = documentSnapshot.toObject(DailymealModel.class);
                                mDailyMealModels.add(dailymealModel);
                                dailymealAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });





        return root;
    }
}
