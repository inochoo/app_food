package com.sibaamap.app_food.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sibaamap.app_food.R;
import com.sibaamap.app_food.models.HomeVerModel;
import com.sibaamap.app_food.models.SeeMoreModel;
import com.sibaamap.app_food.models.ViewAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {


    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    private TextView quantity;
    private int totalPrice = 0;
    private int totalQuantity = 1;
    private ImageView detailedImg;
    private TextView price,rating,description;
    private Button addCart;
    private ImageView addItem,removeItem;
    private Toolbar toolbar;
    private ViewAllModel viewAllModel = null;
    private SeeMoreModel seeMoreModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        getWindow().setStatusBarColor(getColor(R.color.purple_500));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        Init();

        getIntentItem();

        getIntenSeeMore();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                    price.setText(String.valueOf(totalPrice)+"$");


                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                    price.setText(String.valueOf(totalPrice)+"$");

                }
            }
        });


    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Your Order Has Been Placed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void getIntenSeeMore(){
        final Object object1 = getIntent().getSerializableExtra("seemore");
        if(object1 instanceof SeeMoreModel){
            seeMoreModel = (SeeMoreModel) object1;
        }

        if(seeMoreModel != null){
            Glide.with(getApplicationContext()).load(seeMoreModel.getImage()).into(detailedImg);
            rating.setText(seeMoreModel.getRating());
            description.setText(seeMoreModel.getDescription());
            price.setText(seeMoreModel.getPrice()+"$");
        }
    }
    private void getIntentItem(){
        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }
        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImage()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText(viewAllModel.getPrice()+"$");

            totalPrice = viewAllModel.getPrice() * totalQuantity;

        }
    }

    private void Init() {
        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.img_detailed);
        price = findViewById(R.id.tv_detailed_price);
        rating = findViewById(R.id.tv_rating_detailed);
        description = findViewById(R.id.tv_des_detailed);
        addCart = findViewById(R.id.add_to_cart);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.apart_item);

        toolbar = (Toolbar) findViewById(R.id.toolbar_detailed);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}