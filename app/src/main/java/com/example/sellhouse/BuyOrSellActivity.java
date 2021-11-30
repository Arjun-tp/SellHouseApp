package com.example.sellhouse;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sellhouse.buyfragment.BuyFragment;
import com.example.sellhouse.notification.NotificationFragment;
import com.google.android.material.navigation.NavigationView;

public class BuyOrSellActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Toolbar toolbar;
    Button buyHouse, sellHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell);
        toolbar = findViewById(R.id.toolbar);
        buyHouse = findViewById(R.id.btnBuyHouse);
        sellHouse = findViewById(R.id.btnSellHouse);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupFragment(new HomeFragment());

//        sellHouse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
//                        new SellActivityOne()).commit();
//            }
//        });

//        buyHouse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            setupFragment(new HomeFragment());
        } else if (item.getItemId() == R.id.sellHouse) {
            setupFragment(new SellFragment());
        }else if (item.getItemId() == R.id.buyHouse) {
            setupFragment(new BuyFragment());
        } else if (item.getItemId() == R.id.nav_profile) {
            setupFragment(new ProfileFragment());
        } else if (item.getItemId() == R.id.nav_notifications) {
            setupFragment(new NotificationFragment());
        }else if (item.getItemId() == R.id.nav_about) {
            setupFragment(new AboutFragment());
        }else if (item.getItemId() == R.id.termsAndConditions) {
            setupFragment(new TermsFragment());
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setupFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                fragment).commit();
    }
}