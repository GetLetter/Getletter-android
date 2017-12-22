package com.android.getletter.getletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Thomas on 20/12/2017.
 */

public class RecipientActivity extends AppCompatActivity{
    private FirebaseAnalytics mFirebaseAnalytics;
    private Intent homePageItent;
    private String userToken;
    private EditText address;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        homePageItent = getIntent();
        userToken = homePageItent.getStringExtra("userToken");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final Button back = findViewById(R.id.btn_back);
        address = findViewById(R.id.input3);
        Button submit = (Button) findViewById(R.id.btn_continue);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> data = new HashMap<String, String>();
                EditText nom =  findViewById(R.id.input2);
                EditText prenom =  findViewById(R.id.input1);

                String name =prenom.getText() + " " + nom.getText();

                data.put("address", address.getText().toString());
                data.put("name", name);
            }
        });

        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("bite", hasFocus+"");
                setLocation();
            }
        });

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

    private void setLocation() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);

                Log.i("GOOGLE", "Place: " + place.getName());
                address.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i("GOOGLE", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
