package com.sibaamap.app_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.sibaamap.app_food.ui.cart.CartFragment;
import com.sibaamap.app_food.ui.galley.DailyMealFragment;
import com.sibaamap.app_food.ui.home.HomeFragment;
import com.sibaamap.app_food.ui.slideshow.FavouriteFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_DAILYMEAL=1;
    private static final int FRAGMENT_FAVOURITE=2;
    private static final int FRAGMENT_CART=3;

    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getColor(R.color.purple_500));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //catch events click item navigation view
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
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

        }else if(id == R.id.nav_favorite){
            if(mCurrentFragment != FRAGMENT_FAVOURITE){
                replaceFragment( new FavouriteFragment());
                mCurrentFragment = FRAGMENT_FAVOURITE;
            }

        }else if(id == R.id.nav_cart){
            if(mCurrentFragment != FRAGMENT_CART){
                replaceFragment( new CartFragment());
                mCurrentFragment = FRAGMENT_CART;
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
}