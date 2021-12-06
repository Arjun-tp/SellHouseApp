package com.example.sellhouse;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sellhouse.buyfragment.BuyFragment;

import java.util.ArrayList;

public class FilterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        getActivity().setTitle("Filter");
        ArrayList<String> catList = new ArrayList<>();
        ArrayList<String> roomList = new ArrayList<>();

        catList.add("House");
        catList.add("Condo");
        catList.add("Basement");
        roomList.add("1");
        roomList.add("2");
        roomList.add("3");
        roomList.add("4");

        Button apply;
        Spinner category, bedRoom, washRoom;
        EditText minPrice, maxPrice, city;

        category = view.findViewById(R.id.spCategory);
        bedRoom = view.findViewById(R.id.spBedrooms);
        washRoom = view.findViewById(R.id.spWashrooms);
        apply = view.findViewById(R.id.btnApply);
        minPrice = view.findViewById(R.id.extMinPrice);
        maxPrice = view.findViewById(R.id.extMaxPrice);
        city = view.findViewById(R.id.extCity);


        category.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, catList));
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), catList.get(i) + " Selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please select ", Toast.LENGTH_LONG).show();
            }
        });

        bedRoom.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, roomList));
        bedRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), roomList.get(i) + " Selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please select ", Toast.LENGTH_LONG).show();
            }
        });


        washRoom.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, roomList));
        washRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), roomList.get(i) + " Selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please select ", Toast.LENGTH_LONG).show();
            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BuyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}