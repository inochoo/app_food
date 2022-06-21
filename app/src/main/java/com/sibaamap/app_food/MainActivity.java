package com.sibaamap.app_food;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sibaamap.app_food.activities.loginApp;
import com.sibaamap.app_food.ui.cart.CartFragment;
import com.sibaamap.app_food.ui.galley.DailyMealFragment;
import com.sibaamap.app_food.ui.home.HomeFragment;
import com.sibaamap.app_food.ui.profile.MyProfile;
import com.sibaamap.app_food.ui.slideshow.FavouriteFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MY_REQUEST_CODE = 10;

    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_DAILYMEAL=1;
    private static final int FRAGMENT_FAVOURITE=2;
    private static final int FRAGMENT_CART=3;
    private static final int FRAGMENT_MY_PROFILE=4;
    final private MyProfile myProfile = new MyProfile();

    private ImageView imgAvatar;
    private TextView tvEmail,tvName;

    private RatingBar ratFood;

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if (intent == null){
                    return;
                }
                Uri uri = intent.getData();
                myProfile.setUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    myProfile.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        getWindow().setStatusBarColor(getColor(R.color.purple_500));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //catch events click item navigation view
        mNavigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        showUserInformation();
    }
    private void Init(){

        mNavigationView = findViewById(R.id.navigation_view);
        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment( new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_dailyMeal){
            if(mCurrentFragment != FRAGMENT_DAILYMEAL){
                replaceFragment( new DailyMealFragment());
                mCurrentFragment = FRAGMENT_DAILYMEAL;
            }
        }else if(id == R.id.nav_cart){
            if(mCurrentFragment != FRAGMENT_CART){
                replaceFragment( new CartFragment());
                mCurrentFragment = FRAGMENT_CART;
            }
        }else if(id == R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, loginApp.class);
            startActivity(intent);
            finish();
        }else if(id == R.id.nav_my_profile){
            if(mCurrentFragment != FRAGMENT_MY_PROFILE){
                replaceFragment(myProfile);
                mCurrentFragment = FRAGMENT_MY_PROFILE;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //fix back when navigation open
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    public void showUserInformation(){
        //login
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            tvName.setVisibility(View.GONE);
        }else{
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(name);
        }

        tvName.setText(name);
        tvEmail.setText(email);
        //laod img
        Glide.with(this).load(photoUrl).error(R.drawable.avatar).into(imgAvatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                Toast.makeText(this, "vui long cho phep truy cap", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));

    }


}