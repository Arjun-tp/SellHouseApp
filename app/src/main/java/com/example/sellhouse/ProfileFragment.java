package com.example.sellhouse;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

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
    private String profileImageUrl;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    SharedPreferences myPref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        getActivity().setTitle("Profile");

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

        mStorageRef = FirebaseStorage.getInstance().getReference("Users");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
        myPref = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

//        mDatabaseRef.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()));

//        if (profileImageUrl != null){
//            Picasso.get().load(profileImageUrl).into(mImageView);
//        } else {
//            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.placeholder_image));
//        }
        
        email.setText(loginMail);
        email.setEnabled(false);
        mImageView.setImageDrawable(getResources().getDrawable(R.drawable.placeholder_image));
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
                if (imageFromDb != null){
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference("Users/" + String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()) + ".jpg");
                    try {
                        File localImage = File.createTempFile("tempfile",".jpg");
                        storageReference.getFile(localImage)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localImage.getAbsolutePath());
                                        mImageView.setImageBitmap(bitmap);
                                                Log.d("01111111111111","");

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Failed to get image", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(profileImageUrl);
//                    String dataToGet = myPref.getString("channel", "No Image found");
                   /* Glide.with(getContext())
                            .load(imageFromDb)
                            .into(mImageView);
                    Picasso.get()
                            .load(imageFromDb)
                            .into(mImageView);
                    Picasso.with(getContext()).load(imageFromDb).into(mImageView);*/
                }
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
            StorageReference fileReference = mStorageRef
                    .child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()) + "." + getFileExtension(mImageUri));
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
                    fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            profileImageUrl =task.getResult().toString();
                            String dataToSave = profileImageUrl;
                            myPref.edit().putString("channel", dataToSave).apply();
                            Picasso.get().load(profileImageUrl).into(mImageView);
                        }
                    });
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



                    Fragment fragment = new HomeFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toast.makeText(getContext(), "Profile Updated!", Toast.LENGTH_LONG).show();

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
           /* Picasso.get()
                    .load(mImageUri)
                    .into(mImageView);*/
            Glide.with(getContext())
                    .load(mImageUri)
                    .into(mImageView);
        }
    }
}
