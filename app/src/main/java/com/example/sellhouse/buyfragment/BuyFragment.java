package com.example.sellhouse.buyfragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.R;
import com.example.sellhouse.model.House_Model;

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
}