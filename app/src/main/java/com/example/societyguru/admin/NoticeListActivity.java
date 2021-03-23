package com.example.societyguru.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.societyguru.R;
import com.example.societyguru.adapter.NoticeListAdapter;
import com.example.societyguru.adapter.SocietyListAdapter;
import com.example.societyguru.model.NoticeModel;
import com.example.societyguru.model.SocietyModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class NoticeListActivity extends AppCompatActivity {

    FloatingActionButton addnotice;
    SharedPreferences preferences;
    List<NoticeModel> noticeModelList = new ArrayList<>();
    RecyclerView recyclerView;
    NoticeListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        recyclerView = findViewById(R.id.rv_notice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        preferences = getSharedPreferences("NOTICE", MODE_PRIVATE);

        getNoticeList(preferences.getString("NOTICE",""));


        addnotice = findViewById(R.id.btn_add_notice);
        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeListActivity.this,CreateNoticeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getNoticeList(String notice) {
        Query query = FirebaseFirestore.getInstance().collection("NOTICE");

        FirestoreRecyclerOptions<NoticeModel> options = new FirestoreRecyclerOptions.Builder<NoticeModel>()
                .setQuery(query,NoticeModel.class)
                .build();

        adapter = new NoticeListAdapter(options,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoticeListActivity.this));
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
        Intent intent = new Intent(NoticeListActivity.this,AdminDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}