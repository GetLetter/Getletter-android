package Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomas.getletter_android.R;
/**
 * Created by Thomas on 19/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

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



    }

}
