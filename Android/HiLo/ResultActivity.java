package wu000209.e.hilo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView inputNum;
    private TextView randNum;
    private TextView totalGuess;
    private TextView max;
    private TextView min;
    private TextView perfectWin;
    private TextView winTimes;
    private TextView lossTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        inputNum = findViewById(R.id.inputNumber);
        randNum = findViewById(R.id.randomNumber);
        totalGuess = findViewById(R.id.totalNumber);
        max = findViewById(R.id.maxNum);
        min = findViewById(R.id.minNum);
        perfectWin = findViewById(R.id.winNum);
        winTimes = findViewById(R.id.winTime);
        lossTimes = findViewById(R.id.lossTime);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int userInput = bundle.getInt("INPUT");

            int randomNum = bundle.getInt("RANDOM");
            int totalNum = bundle.getInt("TOTALGUESS");
            int maxNum = bundle.getInt("MAX");
            int minNum = bundle.getInt("MIN");
            int perfecWinCount = bundle.getInt("PERFECTWIN");
            int winCounts = bundle.getInt("WIN");
            int lossCounts = bundle.getInt("LOSSTIMES");

            inputNum.setText("Your number: " + userInput);
            randNum.setText("My number: " + randomNum);
            totalGuess.setText("Guess Times: " + totalNum);
            max.setText("Number of high guess: " + maxNum);
            min.setText("Number of low guess: " + minNum);
            perfectWin.setText("Number of perfect win(s): " + perfecWinCount);
            winTimes.setText("Win(s) " + winCounts + " times");
            lossTimes.setText("loss(es) " + lossCounts + " times");

        }

    }
}
