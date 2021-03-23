package com.example.societyguru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.societyguru.admin.AdminDashboardActivity;
import com.example.societyguru.chairman.ChairmanDashboardActivity;
import com.example.societyguru.enums.UserType;
import com.example.societyguru.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import static com.example.societyguru.enums.UserType.CHAIRMAN;
import static com.example.societyguru.model.UserModel.UserEnum;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtemail, edtpassword;
    Button button;
    TextView forgotpassword, registration;
    // DocumentSnapshot documentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtemail = findViewById(R.id.edt_email);
        edtpassword = findViewById(R.id.edt_pass);
        button = findViewById(R.id.btn_login);
        forgotpassword = findViewById(R.id.tv_forgot_pass);
        registration = findViewById(R.id.tv_register);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateAndLogin();
            }
        });
    }

    private void ValidateAndLogin() {
        String email = edtemail.getText().toString().trim();
        String pass = edtpassword.getText().toString().trim();

        Boolean b = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtemail.setError("Enter valid email.");
            b = false;
        }
        if (pass.isEmpty()) {
            edtpassword.setError("Enter Password");
            b = false;
        }
        if (b) {
            loginUser();
        }
    }

    private void loginUser() {
        String email = edtemail.getText().toString().trim();
        String pass = edtpassword.getText().toString().trim();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (email.equals("admin@gmail.com")) {
                                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "Admin Login Successfully.", Toast.LENGTH_SHORT).show();
                            } else {
                                getUserinfo();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Please check internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getUserinfo() {
        FirebaseFirestore.getInstance().collection("USERS")
                .document("email")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot != null) {
                            ValidateUsers(documentSnapshot);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Detail..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void ValidateUsers(DocumentSnapshot documentSnapshot) {
        if (documentSnapshot.get("userType") == (UserEnum.userType.compareTo(CHAIRMAN))){
            Intent intent = new Intent(LoginActivity.this, ChairmanDashboardActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Chairman Login Successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
        }
//        if (documentSnapshot.get(UserEnum.userType.name()) == UserType.CHAIRMAN.name()) {
//            Toast.makeText(LoginActivity.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
//        }
    }
}