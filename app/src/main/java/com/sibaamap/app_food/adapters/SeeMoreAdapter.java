package com.sibaamap.app_food.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.sibaamap.app_food.models.SeeMoreModel;

import java.util.List;

public class SeeMoreAdapter extends RecyclerView.Adapter<SeeMoreAdapter.ViewHolder> {

    private Context context;
    private List<SeeMoreModel> seeMoreModels;

    public SeeMoreAdapter(Context context, List<SeeMoreModel> seeMoreModels) {
        this.context = context;
        this.seeMoreModels = seeMoreModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_more_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SeeMoreModel seeMoreModel =  seeMoreModels.get(position);

        Glide.with(holder.imageView).load(seeMoreModel.getImage()).into(holder.imageView);
        holder.timing.setText(seeMoreModel.getTiming());
        holder.rating.setText(seeMoreModel.getRating());
        holder.description.setText(seeMoreModel.getDescription());
        holder.price.setText(seeMoreModel.getPrice()+"$");
        holder.name.setText(seeMoreModel.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("seemore",seeMoreModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seeMoreModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name,price,description,rating,timing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img_seemore);
            name = itemView.findViewById(R.id.detailed_name_seemore);
            price = itemView.findViewById(R.id.detailed_price_seemore);
            description = itemView.findViewById(R.id.detailed_des_seemore);
            rating= itemView.findViewById(R.id.tv_rating_seemore);
            timing = itemView.findViewById(R.id.tv_time_seemore);
        }
    }
}
