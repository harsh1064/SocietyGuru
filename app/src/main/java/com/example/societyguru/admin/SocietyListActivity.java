package com.example.societyguru.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.societyguru.R;
import com.example.societyguru.adapter.SocietyListAdapter;
import com.example.societyguru.adapter.admin.OnSocietyOptionClick;
import com.example.societyguru.admin.CreateSocietyActivity;
import com.example.societyguru.admin.SocietyInfoActivity;
import com.example.societyguru.model.SocietyModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class SocietyListActivity extends AppCompatActivity {
    FloatingActionButton btnaddsociety;
    SharedPreferences preferences;
    List<SocietyModel> societyModelList = new ArrayList<>();
    RecyclerView recyclerView;
    SocietyListAdapter adapter;
    OnSocietyOptionClick optionClick;
  //  PopupMenu popupMenu;
    Toolbar toolbar;
    //private Object optionClick;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_list);
       // toolbar = findViewById(R.id.t1);
        recyclerView = findViewById(R.id.rv_societies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        preferences = getSharedPreferences("SOCIETIES", MODE_PRIVATE);

        getsocietyList(preferences.getString("SOCIETIES",""));

        btnaddsociety = findViewById(R.id.btn_add_society);


//        setSupportActionBar(toolbar);
        btnaddsociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocietyListActivity.this, CreateSocietyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void getsocietyList(String socities) {
        Query query = FirebaseFirestore.getInstance().collection("SOCIETIES");

        FirestoreRecyclerOptions<SocietyModel> options = new FirestoreRecyclerOptions.Builder<SocietyModel>()
                .setQuery(query,SocietyModel.class)
                .build();

        adapter = new SocietyListAdapter(options,optionClick,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SocietyListActivity.this));
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

    private void blockSociety(SocietyModel model) {
    }

    private void updateSociety(SocietyModel model) {
    }




//    @Override
//    public void showoptions(SocietyModel model, View view) {
//        PopupMenu popupMenu = new PopupMenu((Context)this,view);
//        popupMenu.inflate(R.menu.admin_society_options);
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.society_update:
//                        Toast.makeText(SocietyListActivity.this, "society update", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.society_delete:
//                        Toast.makeText(SocietyListActivity.this, "society delete", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.society_block:
//                        Toast.makeText(SocietyListActivity.this, "society block", Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });
//        popupMenu.show();
//  }

        //onCreateOptionsMenu()
//
    @Override
    public void onBackPressed() {
//        System.gc();
//        System.exit(0);
//        moveTaskToBack(true);
        Intent intent = new Intent(SocietyListActivity.this,AdminDashboardActivity.class);
        startActivity(intent);
        finish();
//        super.onBackPressed();
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true);
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}