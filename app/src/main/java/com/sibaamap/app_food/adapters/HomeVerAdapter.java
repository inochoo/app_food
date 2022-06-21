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
import com.sibaamap.app_food.activities.ViewAllActivity;
import com.sibaamap.app_food.models.HomeVerModel;

import java.util.List;
import java.util.zip.Inflater;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder>  {
    private Context context;
    private List<HomeVerModel> list;

    public HomeVerAdapter(Context context, List<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeVerModel homeVerModel = list.get(position);
        Glide.with(context).load(homeVerModel.getImage()).into(holder.imageView);
        holder.name.setText(homeVerModel.getName());
        holder.timing.setText(homeVerModel.getTiming());
        holder.price.setText(homeVerModel.getPrice()+"$");
        holder.rating.setText(homeVerModel.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("kind",homeVerModel.getKind());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name,timing,rating,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.tv_time);
            rating = itemView.findViewById(R.id.tv_start);
            price = itemView.findViewById(R.id.tv_price);


        }
    }
}
