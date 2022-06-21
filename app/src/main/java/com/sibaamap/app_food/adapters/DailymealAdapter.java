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
import com.sibaamap.app_food.activities.ViewAllActivity;
import com.sibaamap.app_food.models.DailymealModel;

import java.util.List;

public class DailymealAdapter extends RecyclerView.Adapter<DailymealAdapter.ViewHolder> {

    private Context context;
    private List<DailymealModel> mDailyMealList;

    public DailymealAdapter(Context context, List<DailymealModel> mDailyMealList) {
        this.context = context;
        this.mDailyMealList = mDailyMealList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailymealModel dailyMealModel = mDailyMealList.get(position);

        Glide.with(holder.imageView.getContext()).load(dailyMealModel.getImage()).into(holder.imageView);
        holder.name.setText(dailyMealModel.getName());
        holder.description.setText(dailyMealModel.getDescription());
        holder.discount.setText(dailyMealModel.getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type1",dailyMealModel.getType1());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDailyMealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name,discount,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.tv_dinner);
            discount = itemView.findViewById(R.id.tv_discount);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}
