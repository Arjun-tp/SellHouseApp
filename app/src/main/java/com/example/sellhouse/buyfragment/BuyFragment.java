package com.example.sellhouse.buyfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.FilterFragment;
import com.example.sellhouse.HomeFragment;
import com.example.sellhouse.PropertyDetailsActivity;
import com.example.sellhouse.R;
import com.example.sellhouse.model.House_Model;
import com.example.sellhouse.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyFragment extends Fragment {

    RecyclerView buyHouseRecyclerView;
    ImageView gridBTN;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("House");
    BuyHouseAdapter adapter;
    BuyHouseGridAdapter gridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_house, container, false);
        getActivity().setTitle("Buy House");
        buyHouseRecyclerView = view.findViewById(R.id.buyHouseRecyclerView);
        gridBTN = view.findViewById(R.id.gridBTN);
        view.findViewById(R.id.gridBTN).setOnClickListener(view13 -> chooseGridRecyclerView());
        view.findViewById(R.id.listBTN).setOnClickListener(view13 -> chooseListRecyclerView());

        initViews();
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter){
            Fragment fragment = new FilterFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_search){
            Log.d("Search", "Here");
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        ArrayList<House_Model> houseLists = new ArrayList<>();
        buyHouseRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new BuyHouseAdapter(this.getActivity(), houseLists);
        gridAdapter = new BuyHouseGridAdapter(this.getActivity(), houseLists);
        buyHouseRecyclerView.setAdapter(adapter);
        buyHouseRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), buyHouseRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                House_Model houseData = houseLists.get(position);
                Intent intent = new Intent(getActivity(), PropertyDetailsActivity.class);
                House_Model houseDetails = new House_Model(houseData.price, houseData.image,houseData.address1,houseData.noOfBedrooms,houseData.noOfWashrooms, houseData.description);
                Log.d("houseDetails", "onClick: "+houseDetails.image);
                Log.d("houseData", "houseData: "+houseData.image);
                intent.putExtra("houseDetails", houseDetails);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapShot) {
                for (DataSnapshot dataSnapshot : snapShot.getChildren()){
                    House_Model nModel = dataSnapshot.getValue(House_Model.class);
                    houseLists.add(nModel);
                    Log.d("nModel : %v", String.valueOf(nModel.image));
                }
                adapter.notifyDataSetChanged();
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    private void chooseGridRecyclerView() {
        buyHouseRecyclerView.setAdapter(gridAdapter);
        Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.ic_grid);
        mIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.SRC_IN);
        gridBTN.setImageDrawable(mIcon);

    }

    private void chooseListRecyclerView() {
        buyHouseRecyclerView.setAdapter(adapter);
        Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.ic_grid);
        mIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.SRC_IN);
        gridBTN.setImageDrawable(mIcon);

    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}