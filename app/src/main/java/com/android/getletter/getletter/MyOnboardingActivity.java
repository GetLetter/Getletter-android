package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;

import com.jrejaud.onboarder.OnboardingActivity;

public class MyOnboardingActivity extends OnboardingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        Intent homepageIntent = new Intent(getBaseContext(), HomepageActivity.class);
        startActivity(homepageIntent);
    }
}
