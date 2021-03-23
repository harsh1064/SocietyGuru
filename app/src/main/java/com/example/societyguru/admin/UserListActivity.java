 package com.example.societyguru.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.societyguru.R;
import com.example.societyguru.adapter.UserListAdapter;
import com.example.societyguru.adapter.chairman.OnUserOptionClick;
import com.example.societyguru.enums.UserType;
import com.example.societyguru.model.SocietyModel;
import com.example.societyguru.model.UserModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.protobuf.Option;

import java.util.ArrayList;
import java.util.List;

import static com.example.societyguru.enums.UserType.CHAIRMAN;

public class UserListActivity extends AppCompatActivity {

    SharedPreferences preferences;
    List<UserModel> userModelList = new ArrayList<>();
    RecyclerView recyclerView;
    UserListAdapter adapter;
    OnUserOptionClick optionClick;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.rv_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        preferences = getSharedPreferences("USERS", MODE_PRIVATE);

        getuserList(preferences.getString("USERS",""));


    }

    private void getuserList(String users) {
        Query query = FirebaseFirestore.getInstance().collection("USERS")
                .whereEqualTo(UserModel.UserEnum.userType.name(), UserType.CHAIRMAN);
               // .whereEqualTo("userType",CHAIRMAN);
                //.whereEqualTo(UserModel.class,UserType.CHAIRMAN.name());
               // .whereEqualTo(String.valueOf(UserModel.class),UserType.CHAIRMAN.name());
               // .whereEqualTo(UserType.CHAIRMAN.name(),UserType.CHAIRMAN);


        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query,UserModel.class)
                .build();

        adapter = new UserListAdapter(options,optionClick,this, optionClick);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserListActivity.this,AdminDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}