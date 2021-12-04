package com.example.sellhouse.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.R;
import com.example.sellhouse.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    RecyclerView notificationRecyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Notification");

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
        ArrayList<Notification> notificationList = new ArrayList<>();
//        notificationList.add(new Notification("Early Bird Offer: 10%","", R.drawable.photo));
//        notificationList.add(new Notification("Offer: 20%", "", R.drawable.photo));
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        NotificationAdapter adapter = new NotificationAdapter(this.getActivity(), notificationList);
        notificationRecyclerView.setAdapter(adapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapShot) {
                for (DataSnapshot dataSnapshot : snapShot.getChildren()){
                    Notification nModel = dataSnapshot.getValue(Notification.class);
                    notificationList.add(nModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}