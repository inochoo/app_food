package com.sibaamap.app_food.ui.cart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.activities.PlacedOrderActivity;
import com.sibaamap.app_food.adapters.MyCartAdapter;
import com.sibaamap.app_food.models.MyCartModel;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private TextView overTotalAmount;

    private RecyclerView myCartRec;
    private List<MyCartModel> mMyCartModel;
    private MyCartAdapter mMyCartAdapter;
    private ProgressBar progressBar;
    private Button buyNow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cart_fragment,container,false);


        buyNow = root.findViewById(R.id.btn_buy_now);

        overTotalAmount = root.findViewById(R.id.tv_total_price);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        myCartRec = root.findViewById(R.id.rcv_mycart);
        myCartRec.setVisibility(View.GONE);
        myCartRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        mMyCartModel = new ArrayList<>();
        mMyCartAdapter = new MyCartAdapter(getActivity(),mMyCartModel);
        myCartRec.setAdapter(mMyCartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                        cartModel.setDocumentId(documentId);

                        mMyCartModel.add(cartModel);
                        mMyCartAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        myCartRec.setVisibility(View.VISIBLE);
                    }

                    calculateTotalAmount(mMyCartModel);
                }
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) mMyCartModel);
                startActivity(intent);
            }
        });

        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> mMyCartModel) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel : mMyCartModel){
            totalAmount += myCartModel.getTotalPrice();

        }
        overTotalAmount.setText("Total Amount :" + totalAmount+"$");
    }


}
