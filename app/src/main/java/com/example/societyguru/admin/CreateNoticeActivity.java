package com.example.societyguru.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.societyguru.R;
import com.example.societyguru.model.NoticeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateNoticeActivity extends AppCompatActivity {
    EditText edttitle,edtdesc;
    Button sendnotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        edttitle = findViewById(R.id.edt_title);
        edtdesc = findViewById(R.id.edt_desc);

        sendnotice = findViewById(R.id.btn_send_notice);
        sendnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateNotice();
            }

            private void validateNotice() {
                String title = edttitle.getText().toString().trim();
                String desc = edtdesc.getText().toString().trim();

                Boolean b = true;
                if (title.isEmpty()){
                    edttitle.setError("Please enter title.");
                    b = false;
                }
                if (desc.isEmpty()){
                    edtdesc.setError("Please enter description.");
                    b = false;
                }
                if (b){
                    createnotice();
                }
            }

            private void createnotice() {
                String title = edttitle.getText().toString().trim();
                String desc = edtdesc.getText().toString().trim();

                NoticeModel noticeModel = new NoticeModel(title,desc);

                FirebaseFirestore.getInstance().collection("NOTICE")
                        .add(noticeModel)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(CreateNoticeActivity.this, "Notice Created", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateNoticeActivity.this,NoticeListActivity.class);
        startActivity(intent);
        finish();
    }
}