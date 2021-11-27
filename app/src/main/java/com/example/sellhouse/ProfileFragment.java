package com.example.sellhouse;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    Button mButtonUpload, resetPassword;
    ImageView mImageView;
    EditText fullName, email, phone, addressLine1, addressLine2, postalCode;
    private Uri mImageUri;
    ProgressBar mProgressBar;
    public static String loginMail;
    public static String mobile;
    public static String password;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        getActivity().setTitle("Profile");

        Log.d("===========", FirebaseAuth.getInstance().getCurrentUser().getUid());

        mButtonUpload = view.findViewById(R.id.btnUpload);
        mImageView = view.findViewById(R.id.imgProfile);
        fullName = view.findViewById(R.id.extProvince);
        email = view.findViewById(R.id.extCity);
        phone = view.findViewById(R.id.extPhone);
        addressLine1 = view.findViewById(R.id.extAddress1);
        addressLine2 = view.findViewById(R.id.extAddress2);
        postalCode = view.findViewById(R.id.extPostCode);
        mProgressBar = view.findViewById(R.id.progressBar);
        resetPassword = view.findViewById(R.id.btnResetPassword);

//        mProgressBar.;

        mStorageRef = FirebaseStorage.getInstance().getReference("Users");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        mDatabaseRef.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()));

        email.setText(loginMail);
        email.setEnabled(false);

        mDatabaseRef.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullNameFromDb = dataSnapshot.child("fullName").getValue(String.class);
                if (fullNameFromDb != null){
                    fullName.setText(fullNameFromDb);
                }
                String mobileNumberFromDb = dataSnapshot.child("phone").getValue(String.class);
                if (mobileNumberFromDb != null){
                    phone.setText(mobileNumberFromDb);
                }
                String Address1FromDb = dataSnapshot.child("address1").getValue(String.class);
                if (Address1FromDb != null){
                    addressLine1.setText(Address1FromDb);
                }
                String Address2FromDb = dataSnapshot.child("address2").getValue(String.class);
                if (Address2FromDb != null){
                    addressLine2.setText(Address2FromDb);
                }
                String postalCodeFromDb = dataSnapshot.child("postCode").getValue(String.class);
                if (postalCodeFromDb != null){
                    postalCode.setText(postalCodeFromDb);
                }
                String imageFromDb = dataSnapshot.child("imageUrl").getValue(String.class);
//                if (imageFromDb != null){
//                    Picasso.with(getContext()).load(imageFromDb).into(mImageView);
//                }
//                Log.d("################", mobileNumberFromDb);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ResetPasswordFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

        return view;
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            mProgressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(getContext(), "Upload Successful!", Toast.LENGTH_LONG).show();
                    User upload = new User(
                            email.getText().toString().trim(),
                            mobile,
                            password,
                            fullName.getText().toString().trim(),
                            phone.getText().toString().trim(),
                            addressLine1.getText().toString().trim(),
                            addressLine2.getText().toString().trim(),
                            postalCode.getText().toString().trim(),
                            mStorageRef.getDownloadUrl().toString());
//                    mStorageRef.getDownloadUrl().toString()
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).setValue(upload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    mProgressBar.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(getContext(), "No file Selected!", Toast.LENGTH_SHORT).show();
        }
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