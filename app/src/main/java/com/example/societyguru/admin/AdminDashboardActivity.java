package com.example.societyguru.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.societyguru.LoginActivity;
import com.example.societyguru.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    MaterialCardView tvsociety,noticelayout,tvuserslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        preferences = getSharedPreferences("admin", MODE_PRIVATE);

        tvsociety = findViewById(R.id.tv_societies_layout);
        noticelayout = findViewById(R.id.tv_notice_layout);
        tvuserslayout = findViewById(R.id.tv_users_layout);

        tvuserslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this,UserListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvsociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this,SocietyListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        noticelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this,NoticeListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_log_out_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Society Guru")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("yes",null)
                .setNegativeButton("No",null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });

        //if (item.getItemId() == R.id.log_out) {

        //   FirebaseAuth.getInstance().signOut();



        // editor = preferences.edit();
        //  editor.clear();
        // editor.apply();
        // startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
        // finish();
        return super.onOptionsItemSelected(item);
    }
}