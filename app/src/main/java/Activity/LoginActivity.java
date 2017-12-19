package Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomas.getletter_android.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Thomas on 19/12/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private FirebaseAnalytics mFirebaseAnalytics;

    private TextView logo, punchline, conditions;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view
        setContentView(R.layout.activity_login);

        logo = (TextView) findViewById(R.id.logo);
        punchline = (TextView) findViewById(R.id.punchline);
        conditions = (TextView) findViewById(R.id.conditions);
        btnLogin = (Button) findViewById(R.id.btn_login);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final TextView testText = findViewById(R.id.address_str);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("ouiui", "Place: " + place);
                testText.setText(place.getAddress());

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("nonon", "An error occurred: " + status);
            }
        });


    }

}
