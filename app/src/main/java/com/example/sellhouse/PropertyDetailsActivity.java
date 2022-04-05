package com.example.sellhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sellhouse.buyfragment.PropertyDetailsFragment;

public class PropertyDetailsActivity extends AppCompatActivity {

    Toolbar mActionBarToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housedetails);

        Intent intent = getIntent();
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbarMovieDetails);
//        setSupportActionBar(mActionBarToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActionBarToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (savedInstanceState == null) {
            Fragment detailsFragment = PropertyDetailsFragment.newInstance( this,mActionBarToolbar);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, detailsFragment,
                            PropertyDetailsFragment.class.getSimpleName())
                    .commit();
        }
    }
}
