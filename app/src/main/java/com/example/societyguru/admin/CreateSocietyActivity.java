package com.example.societyguru.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.societyguru.R;
import com.example.societyguru.enums.SocietyStatus;
import com.example.societyguru.enums.UserType;
import com.example.societyguru.model.SocietyModel;
import com.example.societyguru.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CreateSocietyActivity extends AppCompatActivity {

    //ActivityCreateSocietyBinding createSocietyBinding;

    TextInputEditText sname, area, city, state, country, pincode,
            chairmanfname, chairmanlname, chairmanemail,
            chairmanContact, chairmanflathouseno, chairmanpass, chairmancpass;
    Button btncreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);

        btncreate = findViewById(R.id.btn_create);
        sname = findViewById(R.id.edt_name);
        area = findViewById(R.id.edt_area);
        city = findViewById(R.id.edt_city);
        state = findViewById(R.id.edt_state);
        country = findViewById(R.id.edt_country);
        pincode = findViewById(R.id.edt_pincode);
        chairmanfname = findViewById(R.id.edt_chairman_f_name);
        chairmanlname = findViewById(R.id.edt_chairman_l_name);
        chairmanemail = findViewById(R.id.edt_chairman_email);
        chairmanContact = findViewById(R.id.edt_chairman_mobile);
        chairmanflathouseno = findViewById(R.id.edt_chairman_flat_house_no);
        chairmanpass = findViewById(R.id.edt_chairman_pass);
        chairmancpass = findViewById(R.id.edt_chairman_c_pass);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatesociety();
            }

            private void validatesociety() {
                String edtname = sname.getText().toString().trim();
                String edtarea = area.getText().toString().trim();
                String edtcity = city.getText().toString().trim();
                String edtstate = state.getText().toString().trim();
                String edtcountry = country.getText().toString().trim();
                String edtpincode = pincode.getText().toString().trim();
                String edtchairmanfname = chairmanfname.getText().toString().trim();
                String edtchairmanlname = chairmanlname.getText().toString().trim();
                String edtchairmanemail = chairmanemail.getText().toString().trim();
                String edtchairmanmobile = chairmanContact.getText().toString().trim();
                String edtchairmanflathouseno = chairmanflathouseno.getText().toString().trim();
                String edtpass = chairmanpass.getText().toString().trim();
                String edtcpass = chairmancpass.getText().toString().trim();

                Boolean b = true;
                if (edtname.isEmpty()){
                    sname.setError("Please enter society name.");
                    b = false;
                }
                if (edtarea.isEmpty()){
                    area.setError("Please enter area.");
                    b = false;
                }
                if (edtcity.isEmpty()){
                    city.setError("Please enter city.");
                    b = false;
                }
                if (edtstate.isEmpty()){
                    state.setError("Please enter state.");
                    b = false;
                }
                if (edtcountry.isEmpty()){
                    country.setError("Please enter country.");
                    b = false;
                }
                if (edtpincode.isEmpty()){
                    pincode.setError("Please enter valid pincode.");
                    b = false;
                }
                if (edtchairmanfname.isEmpty()){
                    chairmanfname.setError("Please enter first name.");
                    b = false;
                }
                if (edtchairmanlname.isEmpty()){
                    chairmanlname.setError("Please enter last name.");
                    b = false;
                }
                if (edtchairmanemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(edtchairmanemail).matches()){
                    chairmanemail.setError("Please enter valid email.");
                    b = false;
                }
                if (edtchairmanmobile.isEmpty() || !Patterns.PHONE.matcher(edtchairmanmobile).matches()){
                    chairmanContact.setError("Please enter valid mobile number.");
                    b = false;
                }
                if (edtchairmanflathouseno.isEmpty()){
                    chairmanflathouseno.setError("Please enter flat / house number.");
                    b = false;
                }
                if (edtpass.isEmpty() || edtpass.length() < 6){
                    chairmanpass.setError("Password must be 6 characters long.");
                    b = false;
                }
//                if (edtcpass != edtpass){
//                    chairmancpass.setError("Password does not match.");
//                    b = false;
//                }
                if (b){
                    CheckSociety();
                }
            }

            private void CheckSociety() {
                String edtname = sname.getText().toString().trim();
                String edtarea = area.getText().toString().trim();
                String edtcity = city.getText().toString().trim();
                String edtstate = state.getText().toString().trim();
                String edtcountry = country.getText().toString().trim();
                String edtpincode = pincode.getText().toString().trim();

                FirebaseFirestore.getInstance().collection("SOCIETIES")
                        .whereEqualTo("sname",edtname)
                        .whereEqualTo("area",edtarea)
                        .whereEqualTo("city",edtcity)
                        .whereEqualTo("state",edtstate)
                        .whereEqualTo("country",edtcountry)
                        .whereEqualTo("pincode",edtpincode)
                        .limit(1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.getResult().isEmpty()){
                                    CreateSociety();
                                }else{
                                    Toast.makeText(CreateSocietyActivity.this, "This society already registered.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            private void CreateSociety() {
                                String edtname = sname.getText().toString().trim();
                                String edtarea = area.getText().toString().trim();
                                String edtcity = city.getText().toString().trim();
                                String edtstate = state.getText().toString().trim();
                                String edtcountry = country.getText().toString().trim();
                                String edtpincode = pincode.getText().toString().trim();
                                String edtchairmanmobile = chairmanContact.getText().toString().trim();
                                String edtchairmanemail = chairmanemail.getText().toString().trim();
                                String status = SocietyStatus.ACTIVE.name();

                                SocietyModel societyModel = new SocietyModel(
                                        edtname,
                                        edtarea,
                                        edtcity,
                                        edtstate,
                                        edtcountry,
                                        edtpincode,
                                        edtchairmanmobile,
                                        edtchairmanemail,
                                        status);

                                FirebaseFirestore.getInstance().collection("SOCIETIES").add(societyModel)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                CreateChairman();
                                            }

                                            private void CreateChairman() {
                                                String edtchairmanemail = chairmanemail.getText().toString().trim();
                                                String edtchairmanpass = chairmanpass.getText().toString().trim();

                                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtchairmanemail,edtchairmanpass)
                                                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                            @Override
                                                            public void onSuccess(AuthResult authResult) {
                                                                StoreChairman();
                                                            }
                                                        });
                                            }

                                            private void StoreChairman() {
                                                String edtchairmanfname = chairmanfname.getText().toString().trim();
                                                String edtchairmanlname = chairmanlname.getText().toString().trim();
                                                String edtchairmanemail = chairmanemail.getText().toString().trim();
                                                String edtchairmanmobile = chairmanContact.getText().toString().trim();
                                                String edtchairmanflathouseno = chairmanflathouseno.getText().toString().trim();
                                                String edtchairmanpass = chairmanpass.getText().toString().trim();
                                                String userType = UserType.CHAIRMAN.name();

                                                UserModel userModel = new UserModel(
                                                        edtchairmanfname,
                                                        edtchairmanlname,
                                                        edtchairmanemail,
                                                        edtchairmanmobile,
                                                        edtchairmanflathouseno,
                                                        edtchairmanpass,
                                                        userType);

                                                FirebaseFirestore.getInstance().collection("USERS").add(userModel)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Toast.makeText(CreateSocietyActivity.this, "Society Created", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(CreateSocietyActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateSocietyActivity.this, "Society Not Created", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateSocietyActivity.this,SocietyListActivity.class);
        startActivity(intent);
        finish();
    }
}