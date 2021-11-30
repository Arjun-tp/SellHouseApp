package com.example.sellhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sellhouse.buyfragment.BuyFragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        getActivity().setTitle("Home");

        view.findViewById(R.id.btnSellHouse).setOnClickListener(view12 -> setupFragment(new SellFragment()));
        view.findViewById(R.id.btnBuyHouse).setOnClickListener(view1 -> setupFragment(new BuyFragment()));


        return view;
    }

    public void setupFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragmentContainer,
                fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}