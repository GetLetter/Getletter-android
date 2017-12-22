package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jrejaud.onboarder.OnboardingActivity;

public class MyOnboardingActivity extends OnboardingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        // get userToken
        Intent loginIntent = getIntent();
        String userToken = loginIntent.getStringExtra("userToken");

        Intent homepageIntent = new Intent(getBaseContext(), HomepageActivity.class);
        homepageIntent.putExtra("userToken", userToken);
        startActivity(homepageIntent);
    }
}
