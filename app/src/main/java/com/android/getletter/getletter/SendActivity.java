package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

/**
 * Created by Thomas on 20/12/2017.
 */

public class SendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent messageIntent = getIntent();
        final String userToken = messageIntent.getStringExtra("userToken");
        final HashMap<String, String> recipientData = (HashMap<String, String>) messageIntent.getSerializableExtra("data");

        Button back = (Button) findViewById(R.id.btn_back);
        Button next = (Button) findViewById(R.id.btn_continue);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getBaseContext(), MessageActivity.class);
                backIntent.putExtra("userToken", userToken);
                backIntent.putExtra("data", recipientData);
                startActivity(backIntent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepageItent = new Intent(getBaseContext(), HomepageActivity.class);
                homepageItent.putExtra("userToken", userToken);
                startActivity(homepageItent);
            }
        });


    }
}
