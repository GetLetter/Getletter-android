package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Thomas on 20/12/2017.
 */

public class RecipientActivity extends AppCompatActivity{
    private FirebaseAnalytics mFirebaseAnalytics;
    private Intent homePageItent;
    private String userToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        homePageItent = getIntent();
        userToken = homePageItent.getStringExtra("userToken");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final Button back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currenTime = Calendar.getInstance().getTime();
                Bundle Abort_params = new Bundle();
                Abort_params.putString("User_Token", userToken);
                Abort_params.putString("Date_time", currenTime.toString());
                mFirebaseAnalytics.logEvent("AbortLetter", Abort_params);

                Intent backIntent = new Intent(getBaseContext(), HomepageActivity.class);
                backIntent.putExtra("userToken", userToken);
                startActivity(backIntent);
            }
        });

    }
}
