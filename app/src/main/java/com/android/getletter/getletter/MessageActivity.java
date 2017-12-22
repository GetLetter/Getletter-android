package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by Thomas on 20/12/2017.
 */

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent recipientIntent = getIntent();
        final String userToken = recipientIntent.getStringExtra("userToken");
        final HashMap<String, String> recipientData = (HashMap<String, String>) recipientIntent.getSerializableExtra("data");

        Button back = (Button) findViewById(R.id.btn_back);
        Button next = (Button) findViewById(R.id.btn_continue);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getBaseContext(), RecipientActivity.class);
                backIntent.putExtra("userToken", userToken);
                backIntent.putExtra("data", recipientData);
                startActivity(backIntent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText message = (EditText) findViewById(R.id.editText2);
                if( message.getText().toString().trim().equals("")){
                    message.setError( "Le message est vide !" );
                    return;
                }
                Intent sendIntent = new Intent(getBaseContext(), SendActivity.class);
                sendIntent.putExtra("userToken", userToken);
                sendIntent.putExtra("data", recipientData);
                startActivity(sendIntent);
            }
        });


        }
    }
