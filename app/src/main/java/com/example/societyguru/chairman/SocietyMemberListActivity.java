package com.example.societyguru.chairman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.societyguru.R;

public class SocietyMemberListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_member_list);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SocietyMemberListActivity.this,ChairmanDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}