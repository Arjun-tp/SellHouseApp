package com.example.sellhouse.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.R;
import com.example.sellhouse.model.Notification;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    RecyclerView notificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        getActivity().setTitle("Notification");
        notificationRecyclerView = view.findViewById(R.id.notificationRecyclerView);

        initViews();
        return view;
    }

    private void initViews() {
        ArrayList<Notification> animalNames = new ArrayList<>();
        animalNames.add(new Notification("Early Bird Offer: 10%", R.drawable.photo));
        animalNames.add(new Notification("Offer: 20%", R.drawable.photo));
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        NotificationAdapter adapter = new NotificationAdapter(this.getActivity(), animalNames);
        notificationRecyclerView.setAdapter(adapter);
    }
}