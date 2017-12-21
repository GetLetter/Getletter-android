package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.getletter.getletter.response.LettersListResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Thomas on 20/12/2017.
 */

public class HomepageActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private Map<String, String> headers;
    // get userToken
    private Intent loginIntent;
    private String userToken;
    private FirebaseAnalytics mFirebaseAnalytics;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        loginIntent = getIntent();
        userToken = loginIntent.getStringExtra("userToken");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Button btnSend = (Button) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currenTime = Calendar.getInstance().getTime();

                Bundle Newletter_params = new Bundle();
                Newletter_params.putString("User_token", userToken);
                Newletter_params.putString("Date_time", currenTime.toString());
                mFirebaseAnalytics.logEvent("NewLetter", Newletter_params);


                Intent RecipientIndent = new Intent(getBaseContext(), RecipientActivity.class);
                RecipientIndent.putExtra("userToken", userToken);
                startActivity(RecipientIndent);
            }
        });

        getPreviousLetters();
    }

    private void getPreviousLetters() {
        requestQueue = Volley.newRequestQueue(HomepageActivity.this);  // requestQueue != null

        // set api call headears
        headers = new HashMap<>();
        headers.put("Authorization", userToken);

        BaseRequest<LettersListResponse> request = new BaseRequest.Builder<>(Request.Method.GET, "https://get-letter-api.herokuapp.com/items", LettersListResponse.class)
                .headers(headers)
                .listener(new RequestListener<LettersListResponse>() {
                    @Override
                    public void onSuccess(Request request, NetworkResponse response, LettersListResponse result) {
                       List items = result.getItems();
                       fillrecycler(items);
                    }

                    @Override
                    public void onFailure(Request request, NetworkResponse response, VolleyError volleyError) {
                        Log.d("YOURAPP", "" + volleyError);
                    }
                }).build();
        requestQueue.add(request);
    }

    public void fillrecycler(final List letterList) {
        ItemAdapter<LetterItem> itemAdapter = new ItemAdapter<>();
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        // recyclerView.setAdapter(fastAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager = new GridLayoutManager(this, 3);
        // recyclerView.setLayoutManager(layoutManager);

        //for (Object letter: letterList) {
            // itemAdapter.add(letter);
        //}
    }
}


class AuthorizationApi {
    String Authorization;

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }
}