package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.getletter.getletter.response.LoginResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jrejaud.onboarder.OnboardingPage;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Thomas on 19/12/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private TextView logo, punchline, conditions;
    private Button btnLogin;
    private RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view
        setContentView(R.layout.activity_login);

        logo = (TextView) findViewById(R.id.logo);
        punchline = (TextView) findViewById(R.id.punchline);
        conditions = (TextView) findViewById(R.id.conditions);
        btnLogin = (Button) findViewById(R.id.btn_login);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(LoginActivity.this);  // requestQueue != null

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseRequest<LoginResponse> request = new BaseRequest.Builder<>(Request.Method.GET, "https://randomuser.me/api?limit=1", LoginResponse.class)
                        .listener(new RequestListener<LoginResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, LoginResponse loginResponse) {
                                Log.d("YOURAPP", "" + loginResponse.getResults());
                                doOnboarding();
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError volleyError) {
                                Log.d("YOURAPP", "Dummy error");
                            }
                        }).build();
                requestQueue.add(request);


            }
        });
    }

    public void doOnboarding() {
        //Building Onboarding Pages
        OnboardingPage page1 = new OnboardingPage("Renseignez les informations sur votre destinataire.",null , null);
        OnboardingPage page2 = new OnboardingPage("Personalisez votre carte et son message.",null , null);
        OnboardingPage page3 = new OnboardingPage("Le destinataire  reçoit vos voeux, d'une très belle manière.",null , "Envoyer ma première carte");

        //Finally, add all the Onboarding Pages to a list
        List<OnboardingPage> onboardingPages = new LinkedList<>();
        onboardingPages.add(page1);
        onboardingPages.add(page2);
        onboardingPages.add(page3);

        //Optionally set the title and body text colors for a specific page.
        page1.setTitleTextColor(R.color.white);
        page2.setTitleTextColor(R.color.white);
        page3.setTitleTextColor(R.color.white);


        //Create a bundle for the Onboarding Activity
        Bundle onboardingActivityBundle = MyOnboardingActivity.newBundleColorBackground(R.color.colorPrimary, onboardingPages);

        //Optionally set if the user can swipe between fragments. True by default.
        onboardingActivityBundle.putBoolean(MyOnboardingActivity.SWIPING_ENABLED, true);

        //Optionally set if the activity has dot pagination at the bottom of the screen (only available when swiping is enabled). True by default.
        onboardingActivityBundle.putBoolean(MyOnboardingActivity.HIDE_DOT_PAGINATION, false);

        Intent OnboardingIntent = new Intent(getBaseContext(), MyOnboardingActivity.class);
        OnboardingIntent.putExtras(onboardingActivityBundle);
        startActivity(OnboardingIntent);
    }

}
