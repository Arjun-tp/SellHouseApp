package com.example.sellhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Created by @mohamedebrahim96 on 25,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        getActivity().setTitle("Notification");

        return view;
    }
}