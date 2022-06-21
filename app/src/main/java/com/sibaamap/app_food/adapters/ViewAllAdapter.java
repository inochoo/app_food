package com.sibaamap.app_food.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.activities.DetailedActivity;
import com.sibaamap.app_food.activities.ViewAllActivity;
import com.sibaamap.app_food.models.HomeHorModel;
import com.sibaamap.app_food.models.ViewAllModel;

import java.io.Serializable;
import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {


    private Context context;
    private List<ViewAllModel> mViewAllModels;

    public ViewAllAdapter(Context context, List<ViewAllModel> mViewAllModels) {
        this.context = context;
        this.mViewAllModels = mViewAllModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewAllModel viewAllModel = mViewAllModels.get(position);

        Glide.with(holder.imageView).load(viewAllModel.getImage()).into(holder.imageView);
        holder.timing.setText(viewAllModel.getTiming());
        holder.rating.setText(viewAllModel.getRating());
        holder.price.setText(viewAllModel.getPrice()+"$");
        holder.name.setText(viewAllModel.getName());
        holder.description.setText(viewAllModel.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",viewAllModel);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mViewAllModels != null){
            return mViewAllModels.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name,price,rating,timing,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.detailed_des_viewall);
            imageView = itemView.findViewById(R.id.detailed_img_viewall);
            name = itemView.findViewById(R.id.detailed_name_viewall);
            price = itemView.findViewById(R.id.detailed_price_viewall);
            rating = itemView.findViewById(R.id.tv_rating_viewall);
            timing = itemView.findViewById(R.id.tv_time_viewall);

        }
    }
}
