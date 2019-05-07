package wu000209.loginexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView homeUser;
    private TextView homeUserPassword;
    private CheckBox homeRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeUser = findViewById( R.id.homeUser );
        homeUserPassword =  findViewById( R.id.homePassword );
        homeRemember =  findViewById(R.id.homeCheckBox);

        // Get the bundle of extras that was sent to this activity
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            String userEmailFromLoginActivity = bundle.getString( "USERNAME" );
            String userPasswordFromLoginActivity = bundle.getString( "PASSWORD" );
            Boolean rememberMe = bundle.getBoolean("REMEMBERME");

            homeUser.setText( "Name: " +userEmailFromLoginActivity );
            homeUserPassword.setText( "Password: " +userPasswordFromLoginActivity );
            homeRemember.setChecked(rememberMe);
        }
    }
}
