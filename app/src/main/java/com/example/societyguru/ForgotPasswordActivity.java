package com.example.societyguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.societyguru.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextInputEditText edtemail;
    Button sendemail;
    TextView tvlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtemail = findViewById(R.id.edt_email);
        sendemail = findViewById(R.id.btn_send_email);
        tvlogin = findViewById(R.id.tv_login);

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }

            private void validate() {
                String email = edtemail.getText().toString().trim();

                Boolean b = true;
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtemail.setError("Enter valid email.");
                    b = false;
                }else if (email.isEmpty()){
                    edtemail.setError("please Enter Email.");
                }

                if (b){
                    ValidateAndSendemail();
                }
            }

            private void ValidateAndSendemail() {
                String email = edtemail.getText().toString().trim();
                FirebaseFirestore.getInstance().collection("USERS")
                        .document(email)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                //UserModel userModel = documentSnapshot.toObject(UserModel.class);
                                if (documentSnapshot == null){
                                    Toast.makeText(ForgotPasswordActivity.this, "User did not registered", Toast.LENGTH_SHORT).show();
                                }else {
                                    Sendemail();
                                }
                            }

                            private void Sendemail() {
                                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ForgotPasswordActivity.this, "Password reset Link sent.\nPlease check your mail.", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ForgotPasswordActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}