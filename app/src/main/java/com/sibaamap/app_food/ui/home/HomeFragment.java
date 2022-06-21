package com.sibaamap.app_food.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.activities.SeeAllActivity;
import com.sibaamap.app_food.adapters.HomeHorAdapter;
import com.sibaamap.app_food.adapters.HomeVerAdapter;
import com.sibaamap.app_food.adapters.UpdateVerticalRec;
import com.sibaamap.app_food.adapters.ViewAllAdapter;
import com.sibaamap.app_food.models.HomeHorModel;
import com.sibaamap.app_food.models.HomeVerModel;
import com.sibaamap.app_food.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    private RecyclerView homeHorizontalRec,homeVerticalRec;
    private List<HomeHorModel> homeHorModelList;
    private HomeHorAdapter homeHorAdapter;

    private DatabaseReference database;
    FirebaseFirestore db;

    private TextView tvSeeMore;
    /**************Vertical**************/

    private ArrayList<HomeVerModel> homeVerModelList;
    private HomeVerAdapter homeVerAdapter;

    /************** Search view **************/
    private EditText search_box;
    private List<ViewAllModel> viewAllModels;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter mViewAllAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db = FirebaseFirestore.getInstance();

        tvSeeMore = root.findViewById(R.id.home_seemore);
        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeeAllActivity.class);
                startActivity(intent);
            }
        });




        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        //home_category
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorModelList = new ArrayList<>();
        homeHorAdapter = new HomeHorAdapter(getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);

        db.collection("home_categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                HomeHorModel homeHorModel = documentSnapshot.toObject(HomeHorModel.class);
                                homeHorModelList.add(homeHorModel);
                                homeHorAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //home_Recommended
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeVerModelList = new ArrayList<>();
        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);

        db.collection("home_recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                HomeVerModel homeVerModel = documentSnapshot.toObject(HomeVerModel.class);
                                homeVerModelList.add(homeVerModel);
                                homeVerAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /************** Search view **************/
        recyclerViewSearch = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.edt_search_food);
        viewAllModels = new ArrayList<>();
        mViewAllAdapter = new ViewAllAdapter(getContext(),viewAllModels);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(mViewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    viewAllModels.clear();
                    mViewAllAdapter.notifyDataSetChanged();
                }else {
                    searchProduct(s.toString());
                }
            }
        });


        return root;
    }

    private void searchProduct(String type) {
        if(!type.isEmpty()){
            db.collection("all_items").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful() && task.getResult() != null){
                                viewAllModels.clear();
                                mViewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModels.add(viewAllModel);
                                    mViewAllAdapter.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }
    }


}
