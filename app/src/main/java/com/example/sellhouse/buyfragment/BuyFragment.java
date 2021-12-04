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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.PropertyDetailsActivity;
import com.example.sellhouse.R;
import com.example.sellhouse.model.House_Model;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyFragment extends Fragment {

    RecyclerView buyHouseRecyclerView;
    ImageView gridBTN;

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

    private void initViews() {
        ArrayList<House_Model> animalNames = new ArrayList<>();
        animalNames.add(new House_Model("$1,200,000", R.drawable.home, "35 Hanover Street\nHuntington Station, NY 11746", 3, 1));
        animalNames.add(new House_Model("$5,800,000", R.drawable.home, "646 W. Mulberry Court\n Bronx, NY 10472", 2, 1));
        animalNames.add(new House_Model("$2,000,000", R.drawable.home, "623 Golf Rd.\n Tonawanda, NY 14150", 2, 1));
        animalNames.add(new House_Model("$6,999,999", R.drawable.home, "8910 Hickory Ave.\nPatchogue, NY 11772", 2, 1));
        buyHouseRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        BuyHouseAdapter adapter = new BuyHouseAdapter(this.getActivity(), animalNames);
        buyHouseRecyclerView.setAdapter(adapter);
        buyHouseRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), buyHouseRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                House_Model houseData = animalNames.get(position);
                Intent intent = new Intent(getActivity(), PropertyDetailsActivity.class);
                House_Model houseDetails = new House_Model(houseData.price, houseData.image,houseData.address,houseData.bedroom,houseData.bathroom);
                Log.d("houseDetails", "onClick: "+houseDetails);
                Log.d("houseData", "houseData: "+houseData);
                intent.putExtra("houseDetails", houseDetails);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    private void chooseGridRecyclerView() {
        Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.ic_grid);
        mIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.SRC_IN);
        gridBTN.setImageDrawable(mIcon);
    }

    private void chooseListRecyclerView() {
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