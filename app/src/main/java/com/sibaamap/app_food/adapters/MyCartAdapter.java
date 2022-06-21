package com.sibaamap.app_food.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    private Context context;
    private List<MyCartModel> mMyCartModelList;
    int totalPrice = 0;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> mMyCartModelList) {
        this.context = context;
        this.mMyCartModelList = mMyCartModelList;
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyCartModel myCartModel = mMyCartModelList.get(position);

        holder.name.setText(myCartModel.getProductName());
        holder.date.setText(myCartModel.getCurrentDate());
        holder.time.setText(myCartModel.getCurrentTime());
        holder.price.setText(myCartModel.getProductPrice());
        holder.quantity.setText(myCartModel.getTotalQuantity());
        holder.totalPrice.setText(myCartModel.getTotalPrice()+"$");


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(myCartModel.getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    mMyCartModelList.remove(myCartModel);
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(context,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });



    }

    @Override
    public int getItemCount() {
        if(mMyCartModelList != null){
            return mMyCartModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,price,date,time,quantity,totalPrice;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.delete);
            name = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_product_price);
            time = itemView.findViewById(R.id.tv_current_time);
            quantity = itemView.findViewById(R.id.tv_total_price);
            totalPrice = itemView.findViewById(R.id.tv_total_quantity);
            date = itemView.findViewById(R.id.tv_current_date);
        }
    }
}
