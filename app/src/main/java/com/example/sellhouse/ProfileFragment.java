package com.example.sellhouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    Button mButtonUpload, resetPassword;
    ImageView mImageView;
    EditText fullName, email, phone, addressLine1, addressLine2, postalCode;
    private Uri mImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        getActivity().setTitle("Profile");

        mButtonUpload = view.findViewById(R.id.btnUpload);
        mImageView = view.findViewById(R.id.imgProfile);
        fullName = view.findViewById(R.id.extFullName);
        email = view.findViewById(R.id.extProfileEmail);
        phone = view.findViewById(R.id.extPhone);
        addressLine1 = view.findViewById(R.id.extAddress1);
        addressLine2 = view.findViewById(R.id.extAddress2);
        postalCode = view.findViewById(R.id.extPostCode);


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;
    }

    private void openFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && data!=null && data.getData()!=null){
            mImageUri = data.getData();
//            mImageView.setImageURI(mImageUri);
            Picasso.with(getContext()).load(mImageUri).into(mImageView);
        }
    }
}