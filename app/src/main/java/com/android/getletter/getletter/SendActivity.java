package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
/**
 * Created by Thomas on 20/12/2017.
 */

public class SendActivity extends AppCompatActivity {

    private Spinner select1, select2;
    private boolean selected1, selected2;
    private View line;
    private TextView text2, text3, text4, text5, text6, text7, text8, totalvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        selected1 = false;
        selected2 = false;

        line = (View) findViewById(R.id.line);
        select1 = (Spinner) findViewById(R.id.select1);
        select2 = (Spinner) findViewById(R.id.select2);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) findViewById(R.id.text8);
        totalvalue = (TextView) findViewById(R.id.totalvalue);

        line.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        text4.setVisibility(View.GONE);
        text5.setVisibility(View.GONE);
        text6.setVisibility(View.GONE);
        text7.setVisibility(View.GONE);
        text8.setVisibility(View.GONE);
        totalvalue.setVisibility(View.GONE);

        select1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selected1 = true;
                checkSelect();
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                selected1 = false;
                checkSelect();
            }
        });

        select2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selected2 = true;
                checkSelect();
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                selected2 = false;
                checkSelect();
            }
        });

        Intent messageIntent = getIntent();
        final String userToken = messageIntent.getStringExtra("userToken");
        final HashMap<String, String> recipientData = (HashMap<String, String>) messageIntent.getSerializableExtra("data");

        Button back = (Button) findViewById(R.id.btn_back);
        Button next = (Button) findViewById(R.id.btn_continue);

        back.setOnClickListener(new View.OnClickListener() {
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

    public void checkSelect() {
        if (selected1 && selected2) {
            line.setVisibility(View.VISIBLE);
            text2.setVisibility(View.VISIBLE);
            text3.setVisibility(View.VISIBLE);
            text4.setVisibility(View.VISIBLE);
            text5.setVisibility(View.VISIBLE);
            text7.setVisibility(View.VISIBLE);
            text6.setVisibility(View.VISIBLE);
            text8.setVisibility(View.VISIBLE);
        } else {
            line.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
            text4.setVisibility(View.GONE);
            text6.setVisibility(View.GONE);
            text5.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
            text8.setVisibility(View.GONE);
            totalvalue.setVisibility(View.GONE);
        }
    }
}