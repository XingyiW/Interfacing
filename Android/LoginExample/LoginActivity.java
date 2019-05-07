package wu000209.loginexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private EditText userNameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.checkBox);

        Button logInButton = (Button)findViewById(R.id.loginButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userNameEditText.getText().toString();
                String pwd = passwordEditText.getText().toString();
                boolean isRememberMe = rememberMeCheckBox.isChecked();

                if(username.isEmpty() || username.length()!=8 || (!Character.isLowerCase(username.charAt(0)) || !Character.isLowerCase(username.charAt(1))) || (!Character.isLowerCase(username.charAt(2)) && !Character.isDigit(username.charAt(2))) || (!Character.isLowerCase(username.charAt(3)) && !Character.isDigit(username.charAt(3)))  || (!Character.isDigit(username.charAt(4))|| !Character.isDigit(username.charAt(5))|| !Character.isDigit(username.charAt(6))|| !Character.isDigit(username.charAt(7)))){
                    userNameEditText.setError("Please Enter Your Name (8 characters, lower-case alphabet in first 2 position, lower-case alphabet or digit in position 3 and 4, digit in position 5 to 8, example: aaaa0000 ");
                    userNameEditText.requestFocus();
                    return;
                }

                if(pwd.isEmpty() || pwd.length()<5 ){
                    passwordEditText.setError("Please Enter Your Password (the length of the password is greater than or equal to 5 ( >= 5 )");
                    passwordEditText.requestFocus();
                    return;
                }

                //Toast.makeText(getApplicationContext(),"Hello " + username +", Welcome to CST8227", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Username: " + username + " pw: " + pwd + " remember? " + isRememberMe, Toast.LENGTH_LONG).show();
                userNameEditText.setText("");
                passwordEditText.setText("");
                rememberMeCheckBox.setChecked(false);


                Intent intent = new Intent( getApplicationContext(), HomeActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                intent.putExtra( "USERNAME", username );
                intent.putExtra( "PASSWORD", pwd );
                intent.putExtra("REMEMBERME", isRememberMe);

                startActivity( intent );

                Log.i(LOG_TAG, "LEAVE onClick()");
            }
        });
    }
}
