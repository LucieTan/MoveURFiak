package jeuCalcul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moveurfiak.R;

public class CalculActivity extends AppCompatActivity {

    Button btn_start, btn_rep0, btn_rep1, btn_rep2, btn_rep3;
    TextView tv_score, tv_questions, tv_timer, tv_bottommessage;
    ProgressBar prog_timer;

    Jeu g = new Jeu();
    int secondsRemaining = 30;

    // Initialisation du timer(30s)
    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + "sec");
            prog_timer.setProgress(30 - secondsRemaining);
        }
        @Override
        public void onFinish() {
            finDeGame();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_start = findViewById(R.id.btn_start);
        btn_rep0 = findViewById(R.id.btn_rep0);
        btn_rep1 = findViewById(R.id.btn_rep1);
        btn_rep2 = findViewById(R.id.btn_rep2);
        btn_rep3 = findViewById(R.id.btn_rep3);

        tv_score = findViewById(R.id.tv_score);
        tv_bottommessage = findViewById(R.id.tv_bottommessage);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);
        prog_timer=findViewById(R.id.prog_timer);

        tv_timer.setText("0 Sec");
        tv_questions.setText("");
        tv_bottommessage.setText("Démarrez la partie !");
        tv_score.setText("0 pts");
        prog_timer.setMax(100);

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                nextTurn();
                timer.start();
            }
        };
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();
            }
        };
        btn_start.setOnClickListener(startButtonClickListener);
        btn_rep0.setOnClickListener(answerButtonClickListener);
        btn_rep1.setOnClickListener(answerButtonClickListener);
        btn_rep2.setOnClickListener(answerButtonClickListener);
        btn_rep3.setOnClickListener(answerButtonClickListener);

    }

    private void finDeGame(){
        if( g.getScore() < 30){
            Intent intent = new Intent(getApplicationContext(),CalculActivity.class);
            startActivity(intent);
            finish();
        }else{
            btn_rep0.setEnabled(false);
            btn_rep1.setEnabled(false);
            btn_rep2.setEnabled(false);
            btn_rep3.setEnabled(false);
            tv_bottommessage.setText("Le jeu est terminé : " + g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
        }

    }

    private void nextTurn() {
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_rep0.setText(Integer.toString(answer[0]));
        btn_rep1.setText(Integer.toString(answer[1]));
        btn_rep2.setText(Integer.toString(answer[2]));
        btn_rep3.setText(Integer.toString(answer[3]));

        btn_rep0.setEnabled(true);
        btn_rep1.setEnabled(true);
        btn_rep2.setEnabled(true);
        btn_rep3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_bottommessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() -1 ));
    }
}
