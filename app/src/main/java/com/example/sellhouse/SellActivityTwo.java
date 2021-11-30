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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sellhouse.buyfragment.BuyFragment;
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

public class SellActivityTwo extends Fragment {
    @Nullable

    private static final int PICK_IMAGE_REQUEST_ONE = 1;
    private static final int PICK_IMAGE_REQUEST_TWO = 2;
    private static final int PICK_IMAGE_REQUEST_THREE = 3;
    EditText propertyType, noOfBedrooms, noOfWashrooms, price, description;
    ImageView imageViewOne, imageViewTwo, imageViewThree;
//    TextView dummy;
    Button post;
    public static String address1 = "";
    public static String address2 = "";
    public static String city = "";
    public static String province = "";
    public static String country = "";
    public static String postCode = "";
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private Uri imageViewOneUri, imageViewTwoUri, imageViewThreeUri;
    private String address1Here, address2Here, cityHere, provinceHere, countryHere, postCodeHere;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_activity_two, container, false);
        propertyType = view.findViewById(R.id.extPropertyType);
        noOfBedrooms = view.findViewById(R.id.extNumberOfBedrooms);
        noOfWashrooms = view.findViewById(R.id.extNumberOfWashrooms);
        price = view.findViewById(R.id.extPrice);
        description = view.findViewById(R.id.extDescription);
        imageViewOne = view.findViewById(R.id.imageView11);
        imageViewTwo = view.findViewById(R.id.imageView22);
        imageViewThree = view.findViewById(R.id.imageView33);
        post = view.findViewById(R.id.btnPost);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("House");
        mStorageRef = FirebaseStorage.getInstance().getReference("House");
//        dummy = view.findViewById(R.id.txvDummy);

        Bundle bundle = this.getArguments();
        address1Here = bundle.getString("address1");
        address2Here = bundle.getString("address2");
        cityHere = bundle.getString("city");
        provinceHere = bundle.getString("province");
        countryHere = bundle.getString("country");
        postCodeHere = bundle.getString("postCode");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

        imageViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser("imageOne");
            }
        });

        imageViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser("imageTwo");
            }
        });

        imageViewThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser("imageThree");
            }
        });

        return view;
    }

    private void openFileChooser(String image){
        if (image.equals("imageOne")){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST_ONE);
        }
        if (image.equals("imageTwo")){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST_TWO);
        }
        if (image.equals("imageThree")){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST_THREE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_ONE && data!=null && data.getData()!=null){
            imageViewOneUri = data.getData();
//            mImageView.setImageURI(imageViewOneUri);
            Picasso.with(getContext()).load(imageViewOneUri).into(imageViewOne);
        }
        if (requestCode == PICK_IMAGE_REQUEST_TWO && data!=null && data.getData()!=null){
            imageViewTwoUri = data.getData();
//            mImageView.setImageURI(imageViewTwoUri);
            Picasso.with(getContext()).load(imageViewTwoUri).into(imageViewTwo);
        }
        if (requestCode == PICK_IMAGE_REQUEST_THREE && data!=null && data.getData()!=null){
            imageViewThreeUri = data.getData();
//            mImageView.setImageURI(imageViewThreeUri);
            Picasso.with(getContext()).load(imageViewThreeUri).into(imageViewThree);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (imageViewOneUri != null || imageViewTwoUri != null || imageViewThreeUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageViewOneUri));
            fileReference.putFile(imageViewOneUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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
                    House upload = new House(
                            address1Here, address2Here, cityHere, provinceHere, countryHere, postCodeHere,
                            propertyType.getText().toString().trim(),
                            noOfBedrooms.getText().toString().trim(),
                            noOfWashrooms.getText().toString().trim(),
                            price.getText().toString().trim(),
                            description.getText().toString().trim(),
                            imageViewOneUri != null ? imageViewOneUri.toString() : "", imageViewTwoUri != null ? imageViewTwoUri.toString() : "", imageViewThreeUri != null ? imageViewThreeUri.toString() : "");
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid())).setValue(upload);
                    Toast.makeText(getContext(), "Property Added Successfully!", Toast.LENGTH_LONG).show();

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            new BuyFragment()).commit();
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

}
