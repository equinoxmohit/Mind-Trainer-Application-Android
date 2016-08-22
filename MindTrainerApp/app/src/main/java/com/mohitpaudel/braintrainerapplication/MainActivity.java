package com.mohitpaudel.braintrainerapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart,button0,button1,button2,button3;
    private int num1,num2,correctPosition,incorrectAnswer,score=0,questions=0;
    private Random random;
    private TextView txtSum,txtMessage,txtScore,txtTimer,playAgainButton;
    private ArrayList<Integer> answers=new ArrayList<>();
    private GridLayout gridLayout;
    private RelativeLayout relativeFirst,relativeSecond;
    private MediaPlayer mediaPlayer;


    private void initComponents()
    {
       buttonStart=(Button) findViewById(R.id.btnStart);
       txtSum = (TextView) findViewById(R.id.txtSum);
       button0=(Button) findViewById(R.id.button0);
       button1=(Button) findViewById(R.id.button1);
       button2=(Button) findViewById(R.id.button2);
       button3=(Button) findViewById(R.id.button3);
       txtMessage=(TextView) findViewById(R.id.txtMessage);
       txtScore=(TextView) findViewById(R.id.txtScore);
       txtTimer=(TextView) findViewById(R.id.txtTimer);
       playAgainButton=(TextView) findViewById(R.id.playAgainButton);
       gridLayout=(GridLayout) findViewById(R.id.gridLayout);
       relativeFirst=(RelativeLayout) findViewById(R.id.relativeFirst);
       relativeSecond=(RelativeLayout) findViewById(R.id.relativeSecond);
       mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
    }



    public void  startGame(View view){
        relativeSecond.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
        buttonStart.setVisibility(View.INVISIBLE);
    }

    public void getAnswer()
    {
        random=new Random();
        num1=random.nextInt(31);
        num2=random.nextInt(31);

        txtSum.setText(Integer.toString(num1) + "+" +Integer.toString(num2));
        correctPosition=random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(correctPosition==i)
            {
                answers.add(num1+num2);
            }else
            {
                incorrectAnswer=random.nextInt(62);
                if(incorrectAnswer==num1+num2)
                {
                    incorrectAnswer=random.nextInt(62);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void randomButton(View view)
    {
       if(view.getTag().toString().equals(Integer.toString(correctPosition))){
           score++;
          txtMessage.setText("Correct Answer");
       }
       else
       {

           txtMessage.setText("Incorrect Answer");
       }
        questions++;
        txtScore.setText(Integer.toString(score) + "/" +Integer.toString(questions));
        getAnswer();
    }

    public void playAgain(View view)
    {
        score=0;
        questions=0;
        txtScore.setText("0/0");
        txtTimer.setText("30");
        txtMessage.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        getAnswer();
        countDown();
    }

    public void countDown()
    {
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
                txtTimer.setText("0");
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtMessage.setText("Your Scorecard:"+Integer.toString(score) + "/" +Integer.toString(questions));
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initComponents();
       //for now


        relativeSecond.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.VISIBLE);





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
