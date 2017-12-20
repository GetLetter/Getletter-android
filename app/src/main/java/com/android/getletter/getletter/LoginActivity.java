package com.android.getletter.getletter;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Thomas on 19/12/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private TextView logo, punchline, conditions;
    private Button btnLogin;


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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
