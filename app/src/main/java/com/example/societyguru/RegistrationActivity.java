package com.example.societyguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.societyguru.model.SocietyModel;
import com.example.societyguru.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Spinner spinner;
    Button button;
    TextView tvlogin;
    TextInputEditText fname,lname,email,mobile,houseno,pass,cpass;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        spinner = findViewById(R.id.spnr_society);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(RegistrationActivity.this,R.layout.spinner_item_view,arrayList);
        spinner.setAdapter(adapter);

        button = findViewById(R.id.btn_register);

        tvlogin = findViewById(R.id.tv_login);

        fname = findViewById(R.id.edt_f_name);
        lname = findViewById(R.id.edt_l_name);
        email = findViewById(R.id.edt_email);
        mobile = findViewById(R.id.edt_mobile);
        houseno = findViewById(R.id.edt_flat_house_no);
        pass = findViewById(R.id.edt_pass);
        cpass = findViewById(R.id.edt_c_pass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }

            private void Validate() {
                String edtfname = fname.getText().toString().trim();
                String edtlname = lname.getText().toString().trim();
                String edtemail = email.getText().toString().trim();
                String edtmobile = mobile.getText().toString().trim();
                String edtflathouseno = houseno.getText().toString().trim();
                String edtpass = pass.getText().toString().trim();
                String edtcpass = cpass.getText().toString().trim();

                Boolean b =  true;
                if (edtfname.isEmpty()){
                    fname.setError("Please enter valid first name.");
                    b = false;
                }
                if (edtlname.isEmpty()){
                    lname.setError("Please enter valid last name.");
                    b = false;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(edtemail).matches()){
                    email.setError("Enter valid email.");
                    b = false;
                }
                if (edtmobile.isEmpty()){
                    mobile.setError("Please enter valid mobile number.");
                    b = false;
                }
                if (edtflathouseno.isEmpty()){
                    houseno.setError("Please enter flat / house number.");
                    b =  false;
                }
                if (edtpass.length() < 6){
                    pass.setError("Password must atleast 6 character long.");
                    b = false;
                }
                if (edtpass != edtcpass){
                    cpass.setError("Password does not match.");
                    b = false;
                }
                if (b){
                    CheckUser();
                }
            }

            private void CheckUser() {
                if (UserExists()){
                    Toast.makeText(RegistrationActivity.this, "Email ID is already registered.", Toast.LENGTH_SHORT).show();
                }else {
                    CreateUser();
                }
            }

            private void CreateUser() {
                String edtfname = fname.getText().toString().trim();
                String edtlname = lname.getText().toString().trim();
                String edtemail = email.getText().toString().trim();
                String edtmobile = mobile.getText().toString().trim();
                String edtflathouseno = houseno.getText().toString().trim();
                String edtpass = pass.getText().toString().trim();

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtemail,edtpass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                StoreUser();
                            }

                            private void StoreUser() {
                                UserModel userModel = new UserModel(edtfname,edtlname,edtemail,edtmobile,edtflathouseno,edtpass);

                                FirebaseFirestore.getInstance().collection("USERS").add(userModel)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(RegistrationActivity.this, "Registration success, Please wait for Chairman's Approval.", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegistrationActivity.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private boolean UserExists() {
                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(String.valueOf(email))
                        .addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                            @Override
                            public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {

                            }
                        });
                return false;
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}