package com.example.navpreetkaur.geoquizassignment;

import android.graphics.Color;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast.*;
import android.util.Log;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_SHORT;

public class QuizActivity extends AppCompatActivity {
   private TextView view_question;
   private ImageButton next_button;
   private ImageButton back_button;

   private Question[] questions = new Question[10];
   private int qNumber = 0;
   private static final String answer_correct = "Correct :)";
   private static final String answer_Incorrect = "Incorrect :(";
   private static final String TAG = "QuizActivity";
   private static final String KEY_INDEX = "index";
   private static final String RESULT_SCORE = "score";

   private int result = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");


        setContentView(R.layout.activity_quiz);
        if(savedInstanceState != null)
        {

            qNumber = savedInstanceState.getInt(KEY_INDEX, 0);
            result = savedInstanceState.getInt(RESULT_SCORE, 0);


        }
        initQuestions();
        initQuestionText();
        initNextButton();
        intitAnswerButton(R.id.true_button, true);
        intitAnswerButton(R.id.false_button, false);
        initBackButton();
        showQuestionByTextView();
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        savedInstanceState.putInt(RESULT_SCORE, result);
        savedInstanceState.putInt(KEY_INDEX, qNumber);

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    private void initQuestions()
    {
        questions[0] = new Question("Capital of Canada is Toronto?",false);
        questions[1] = new Question("Are vegetables are not good for health?",false);
        questions[2] = new Question("Is it freezing outside?",true);
        questions[3] = new Question("Is fruit evil?",false);
        questions[4] = new Question("This is 2018!!",true);
        questions[5] = new Question("MVC stand for Multi View Control",false);
        questions[6] = new Question("Android Studio is very slow in windows..",true);
        questions[7] = new Question("Bill Gate make more than $11.5 billion dollar per year..",true);
        questions[8] = new Question("Justin Bieber is famous for his dance!!",false);
        questions[9] = new Question("Mark Zuckerberg is CEO of Amazon",false);
    }
    private void initQuestionText()
    {
        view_question = (TextView) findViewById(R.id.view_question);
        view_question.setText(questions[qNumber].getQuery());
    }
    private void initNextButton()
    {
        final Button false_b = (Button) findViewById(R.id.false_button);
        final Button true_b = (Button) findViewById(R.id.true_button);

        next_button = (ImageButton) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    false_b.setEnabled(true);
                    false_b.setText("False");
                    true_b.setEnabled(true);
                    true_b.setText("True");
                    qNumber++;
                    initQuestionText();
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    view_question = (TextView) findViewById(R.id.view_question);
                    view_question.setText("End of Quiz ");
                    false_b.setEnabled(false);
                    false_b.setText("");
                    true_b.setEnabled(false);
                    true_b.setText("");
                    true_b.setBackgroundColor(1);
                    false_b.setBackgroundColor(1);
                    next_button.setEnabled(false);
                    view_question.setText("You got : "+ (result)*10 + "%");

                    Toast toast =  Toast.makeText(QuizActivity.this,"You got : "+ Integer.toString(result*10)+"%",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,100,500);
                    toast.show();

                }
                catch(Exception e)
                {
                    view_question = (TextView) findViewById(R.id.view_question);
                    view_question.setText("");
                }

            }
        });
    }
    private void initBackButton()
    {
        back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qNumber--;
                    initQuestionText();
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    view_question = (TextView) findViewById(R.id.view_question);
                    view_question.setText("No more questions!!! ");
                    back_button.setEnabled(false);
                }
                catch(Exception e)
                {
                    view_question = (TextView) findViewById(R.id.view_question);
                    view_question.setText(" ");
                }

            }
        });
    }
    private  void intitAnswerButton(int id, final boolean answer)
    {
        final Button false_b = (Button) findViewById(R.id.false_button);
        final Button true_b = (Button) findViewById(R.id.true_button);
            final Button button = (Button) findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if(questions[qNumber].getAnswer() == answer)
                {
                  Toast toast =  Toast.makeText(QuizActivity.this,answer_correct,Toast.LENGTH_SHORT);
                  toast.setGravity(Gravity.TOP,100,100);
                  toast.show();
                    false_b.setEnabled(false);
                    false_b.setText("Disable");
                    true_b.setEnabled(false);
                    true_b.setText("Disable");
                    result++;


                }
                else
                {
                    Toast toast =  Toast.makeText(QuizActivity.this,answer_Incorrect,Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,100,100);
                    toast.show();
                    false_b.setEnabled(false);
                    false_b.setText("Disable");
                    true_b.setEnabled(false);
                    true_b.setText("Disable");
                }


                }

            });
    }
    private void showQuestionByTextView()
    {
        view_question =  (TextView) findViewById(R.id.view_question);
        view_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qNumber++;
                    initQuestionText();
                }
                catch(Exception e)
                {
                    view_question = (TextView) findViewById(R.id.view_question);
                    view_question.setText("End of Quiz");
                }
            }
        });
    }


}
