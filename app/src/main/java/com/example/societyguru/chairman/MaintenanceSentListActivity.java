package com.example.societyguru.chairman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.societyguru.R;

public class MaintenanceSentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_sent_list);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MaintenanceSentListActivity.this,ChairmanDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}