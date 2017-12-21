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
import com.google.firebase.analytics.FirebaseAnalytics;
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
    private FirebaseAnalytics mFirebaseAnalytics;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view
        setContentView(R.layout.activity_login);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        logo = (TextView) findViewById(R.id.logo);
        punchline = (TextView) findViewById(R.id.punchline);
        conditions = (TextView) findViewById(R.id.conditions);
        btnLogin = (Button) findViewById(R.id.btn_login);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(LoginActivity.this);  // requestQueue != null

        final User userCredentials = new User();
        userCredentials.setEmail("corentin@gmail.com");
        userCredentials.setPassword("123456");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseRequest<LoginResponse> request = new BaseRequest.Builder<>(Request.Method.POST, "https://get-letter-api.herokuapp.com/authenticate", LoginResponse.class)
                        .object(userCredentials)
                        .listener(new RequestListener<LoginResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, LoginResponse loginResponse) {
                                String userToken = loginResponse.getAuth_token();

                                mFirebaseAnalytics.setUserProperty("Early Adopter", "true");

                                doOnboarding(userToken);
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError volleyError) {
                                Log.d("YOURAPP", "" + volleyError);
                            }
                        }).build();
                requestQueue.add(request);


            }
        });
    }

    public void doOnboarding(String userToken) {
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
        page1.setTitleTextColor(R.color.colorPrimary);
        page2.setTitleTextColor(R.color.colorPrimary);
        page3.setTitleTextColor(R.color.colorPrimary);


        //Create a bundle for the Onboarding Activity
        Bundle onboardingActivityBundle = MyOnboardingActivity.newBundleColorBackground(R.color.white, onboardingPages);

        //Optionally set if the user can swipe between fragments. True by default.
        onboardingActivityBundle.putBoolean(MyOnboardingActivity.SWIPING_ENABLED, true);

        //Optionally set if the activity has dot pagination at the bottom of the screen (only available when swiping is enabled). True by default.
        onboardingActivityBundle.putBoolean(MyOnboardingActivity.HIDE_DOT_PAGINATION, false);

        Intent OnboardingIntent = new Intent(getBaseContext(), MyOnboardingActivity.class);
        OnboardingIntent.putExtras(onboardingActivityBundle);
        OnboardingIntent.putExtra("userToken", userToken);
        startActivity(OnboardingIntent);
    }

}



class User {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
