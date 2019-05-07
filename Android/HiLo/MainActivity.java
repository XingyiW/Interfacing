/**************************************************************************************************
 *  Purpose: Class MainActivity handles all of the user interaction with application that
 *  compare the input (user number) with machine number and give message of win
 *  or loss (high or low)
 *
 *  @author Xingyi Wu (wu000209@algonquinlive.com), Kevin Lai (lai00035@algonquinlive.com)
 *
 *************************************************************************************************/

package wu000209.e.hilo;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    Random rand = new Random();
    private int theNumber = rand.nextInt(1000) + 1;
    private EditText userNumber;
    int userGuess;
    int clickCount = 0;
    int highGuess=0;
    int lowGuess=0;
    int perfectwinCount;
    int winCount;
    int lossCount;
    String userGuessString;
    Toast toast;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getSupportFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNumber = findViewById(R.id.userNum);
        Button guessButton = findViewById(R.id.guessButton);
        Button resetButton = findViewById(R.id.resetButton);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount += 1;
                userGuessString = userNumber.getText().toString();

                if (clickCount > 0) {
                    try {
                        userGuess = Integer.parseInt(userGuessString);
                        if (userGuess < 0 || userGuess > 1000) {
                            userNumber.setError("Enter number between 0 to 1000.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        userNumber.setError("Please enter a number");
                        userNumber.requestFocus();
                        return;
                    }

                    if (clickCount <= 10) {
                        if (clickCount <= 5 && userGuess == theNumber) {
                            toast = Toast.makeText(getApplicationContext(), "Superior win! You need reset!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                            toast.show();
                            winCount += 1;
                            perfectwinCount += 1;
                            userNumber.setText("");
                            userNumber.setEnabled(false);


                        } else if (clickCount > 5 && userGuess == theNumber) {
                            toast = Toast.makeText(getApplicationContext(), "Excellent win! You need reset! ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                            toast.show();
                            winCount += 1;
                            userNumber.setText("");
                            userNumber.setEnabled(false);


                        } else if (userGuess > theNumber) {
                            toast = Toast.makeText(getApplicationContext(), "Too high!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                            toast.show();
                            highGuess += 1;
                            lossCount += 1;

                        } else if (userGuess < theNumber) {
                            toast = Toast.makeText(getApplicationContext(), "Too low!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                            toast.show();
                            lowGuess += 1;
                            lossCount += 1;

                        }

                        if (clickCount == 10) {
                            userNumber.setText("");
                            userNumber.setEnabled(false);
                            toast = Toast.makeText(getApplicationContext(), "Game Over Please Reset!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                            toast.show();

                        } else {
                            userNumber.setText("");
                        }

                    } else {

                        toast = Toast.makeText(getApplicationContext(), "Game Over Please Reset!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                        toast.show();
                        userNumber.setEnabled(false);
                    }
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickCount = 0;
                highGuess=0;
                lowGuess=0;
                theNumber = rand.nextInt(1000) + 1;
                userNumber.setEnabled(true);
                userNumber.setText("");

                toast = Toast.makeText(getApplicationContext(), "Game is reset.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1000);
                toast.show();

            }
        });

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intent.putExtra("RANDOM", theNumber);
                intent.putExtra("TOTALGUESS", clickCount);
                intent.putExtra("INPUT", userGuess);
                intent.putExtra("MAX", highGuess);
                intent.putExtra("MIN", lowGuess);
                intent.putExtra("PERFECTWIN", perfectwinCount);
                intent.putExtra("WIN", winCount);
                intent.putExtra("LOSSTIMES", lossCount);

                startActivity(intent);
                return true;

            }
        });


    }


}
