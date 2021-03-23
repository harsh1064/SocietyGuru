package com.example.societyguru.chairman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.societyguru.LoginActivity;
import com.example.societyguru.R;
import com.example.societyguru.admin.AdminDashboardActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class ChairmanDashboardActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    MaterialCardView tvprofile,tvmember,tvmaintenance,tvevent,tvmembermaintenance,tvnotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairman_dashboard);

        preferences = getSharedPreferences("Chairman", MODE_PRIVATE);

        tvprofile = findViewById(R.id.tv_profile_layout);
        tvmember = findViewById(R.id.tv_members_layout);
        tvmaintenance = findViewById(R.id.tv_maintenance_layout);
        tvevent = findViewById(R.id.tv_event_layout);
        tvmembermaintenance = findViewById(R.id.tv_member_maintenance_layout);
        tvnotice = findViewById(R.id.tv_notice_layout);

        tvprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,ChairmanProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,SocietyMemberListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvmaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,MaintenanceListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,EventListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvmembermaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,MaintenanceSentListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairmanDashboardActivity.this,ChairmanNoticeActivity.class);
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
                startActivity(new Intent(ChairmanDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });
        return super.onOptionsItemSelected(item);
    }
}