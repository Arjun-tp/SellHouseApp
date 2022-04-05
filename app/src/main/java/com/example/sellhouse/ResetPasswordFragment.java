package com.example.sellhouse;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    EditText email, newPass, confirmPass;
    Button changePass;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        getActivity().setTitle("Reset Password");

        email = view.findViewById(R.id.extEmailReset);
        newPass = view.findViewById(R.id.extNewPassword);
        confirmPass = view.findViewById(R.id.extConfirmPassword);
        changePass = view.findViewById(R.id.btnChangePass);

        auth = FirebaseAuth.getInstance();


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailHere = email.getText().toString().trim();
                String passwordHere = newPass.getText().toString().trim();

                if (emailHere.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailHere).matches()){
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }

                auth.sendPasswordResetEmail(emailHere).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    

        return view;
    }
}