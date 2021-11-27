package com.example.sellhouse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SellActivityOne extends Fragment {
    @Nullable

    EditText address1, address2, city, province, country, postCode;
    Button next;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_sell_activity_one,container,false);

        View view = inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        address1 = view.findViewById(R.id.extAddress1);
        address2 = view.findViewById(R.id.extAddress2);
        city = view.findViewById(R.id.extCity);
        province = view.findViewById(R.id.extProvince);
        country = view.findViewById(R.id.extCountry);
        postCode = view.findViewById(R.id.extPostalCode);
        next = view.findViewById(R.id.btnNext);

        SellActivityTwo.address1 = address1 != null ? address1.getText().toString().trim() : null;
        SellActivityTwo.address2 = address2 != null ? address2.getText().toString().trim() : null;
        SellActivityTwo.city = city != null ? city.getText().toString().trim() : null;
        SellActivityTwo.province = province != null ? province.getText().toString().trim() : null;
        SellActivityTwo.country = country != null ? country.getText().toString().trim() : null;
        SellActivityTwo.postCode = postCode != null ? postCode.getText().toString().trim() : null;


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("address1", address1.getText().toString().trim());
                bundle.putString("address2", address2.getText().toString().trim());
                bundle.putString("city", city.getText().toString().trim());
                bundle.putString("province", province.getText().toString().trim());
                bundle.putString("country", country.getText().toString().trim());
                bundle.putString("postCode", postCode.getText().toString().trim());

                Fragment fragment = new SellActivityTwo();
                fragment.setArguments(bundle);
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