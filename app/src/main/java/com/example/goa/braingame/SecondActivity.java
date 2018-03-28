package com.example.goa.braingame;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.chandora.testyourmaths.R;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private int TAG;
    private Button firstButton, secondButton, thirdButton, fourthButton;
    private Button quesButton;
    private TextView score, timerText, totalPointsText;

    private ArrayList<Integer> arrayList;
    private int locationOfCorrectAnswer;
    Button b;
    private int totalScore = 0;
    CountDownTimer timer;
    private int totalPoints = 0;
    private ConstraintLayout constraintLayout;
    private GridLayout gridLayout;
    private TextView completedScore, correctScore, wrongAnswer, unanswered;
    private Button playAgain, homeButton;
    private int correct = 0, incorrect = 0, unAnswered = 0;

    private boolean allow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firstButton = (Button) findViewById(R.id.firstButton);
        secondButton = (Button) findViewById(R.id.secondBUtton);
        thirdButton = (Button) findViewById(R.id.thirdButton);
        fourthButton = (Button) findViewById(R.id.fourthButton);
        quesButton = (Button) findViewById(R.id.quesButton);
        score = (TextView) findViewById(R.id.score);
        timerText = (TextView) findViewById(R.id.timerText);
        totalPointsText = (TextView) findViewById(R.id.totalPoints);
        arrayList = new ArrayList<>();
        constraintLayout = (ConstraintLayout) findViewById(R.id.layoutContainer);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        allow = false;
        Bundle data = getIntent().getExtras();
        totalPoints = 0;
        totalScore = 0;

        if (data != null) {
            TAG = data.getInt("TAG");
        }
        totalScore++;
        score.setText(totalScore + "/10");
        generateQuestion();
    }


    public void secondClick(View view) {

        if (allow) {

            allow = false;

            int id = view.getId();
            final Button ids[] = {
                    firstButton,
                    secondButton,
                    thirdButton,
                    fourthButton
            };
            b = (Button) findViewById(id);

            if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))) {
                b.setBackgroundResource(R.drawable.green_background);
                totalPoints = totalPoints + 100;
                correct++;
            } else {
                b.setBackgroundResource(R.drawable.red_background);
                totalPoints = totalPoints - 25;
                incorrect++;
                for (int i = 0; i < 4; i++) {
                    if (locationOfCorrectAnswer == i) {
                        ids[i].setBackgroundResource(R.drawable.green_background);
                        break;
                    }
                }
            }
            new Handler().postDelayed(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void run() {
                    for (int i = 0; i < 4; i++) {
                        ids[i].setBackgroundResource(R.drawable.round1);
                    }
                    timer.cancel();
                    totalScore++;
                    score.setText(totalScore + "/10");
                    totalPointsText.setText("Points : " + totalPoints);

                    generateQuestion();
                }
            }, 1500);

        }
    }

    public void dialogShow() {


        final Dialog dialog = new Dialog(SecondActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        completedScore = dialog.findViewById(R.id.completedScore);
        correctScore = dialog.findViewById(R.id.correctAnswer);
        wrongAnswer = dialog.findViewById(R.id.wrongAnswer);
        playAgain = dialog.findViewById(R.id.playAgain);
        homeButton = dialog.findViewById(R.id.homeButton);
        unanswered = dialog.findViewById(R.id.unanswered);
        dialog.setCancelable(false);
        completedScore.setText("score: " + totalPoints);
        correctScore.setText("correct answer: " + correct);
        wrongAnswer.setText("wrong answers: " + incorrect);
        unAnswered = 10 - (correct + incorrect);
        unanswered.setText("unanswered: " + unAnswered);
        dialog.show();

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                totalPoints = 0;
                totalScore = 1;
                score.setText(totalScore + "/10");
                totalPointsText.setText("Points : 00");
                correct = 0;
                incorrect = 0;
                unAnswered = 0;
                generateQuestion();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void generateQuestion() {

        allow = true;

        if (totalScore == 11) {
            if (!isFinishing()) {
                score.setText((totalScore - 1) + "/10");
                dialogShow();
                return;
            }
        }
        setTimer();

        int num = 0;
        int extraAdd = 0;
        int tempResult;


        Random r = new Random();
        int a;
        int b;

        if (TAG == 1) {

            if (totalScore < 3) {
                a = r.nextInt(10) + 5;
                b = r.nextInt(7) + 8;
                tempResult = a + b;
                if (tempResult < 20) {
                    num = 10;
                    extraAdd = 12;
                } else {
                    num = 15;
                    extraAdd = 18;
                }

            } else if (totalScore < 5) {
                a = r.nextInt(20) + 15;
                b = r.nextInt(15) + 10;
                tempResult = a + b;

                if (tempResult < 40) {
                    num = 25;
                    extraAdd = 22;
                } else {
                    num = 40;
                    extraAdd = 35;
                }


            } else if (totalScore < 8) {
                a = r.nextInt(50) + 35;
                b = r.nextInt(45) + 30;
                tempResult = a + b;

                if (tempResult < 90) {
                    num = 40;
                    extraAdd = 60;
                } else if (tempResult < 130) {
                    num = 60;
                    extraAdd = 80;
                } else {
                    num = 60;
                    extraAdd = 120;
                }

            } else {
                a = r.nextInt(70) + 65;
                b = r.nextInt(55) + 35;
                tempResult = a + b;
                if (tempResult < 130) {
                    num = 50;
                    extraAdd = 90;
                } else if (tempResult < 170) {
                    num = 70;
                    extraAdd = 120;
                } else if (tempResult < 200) {
                    num = 50;
                    extraAdd = 165;
                } else {
                    num = 50;
                    extraAdd = 190;
                }

            }

        } else if (TAG == 2) {
            a = r.nextInt(20) + 20;
            b = r.nextInt(20) + 5;
            if (totalScore < 4) {

                while (a < b) {
                    a = r.nextInt(20) + 20;
                    b = r.nextInt(20) + 5;
                }

                tempResult = a - b;
                if (tempResult < 22) {
                    num = 10;
                    extraAdd = 14;
                } else {
                    num = 20;
                    extraAdd = 20;
                }

            } else if (totalScore < 8) {
                a = r.nextInt(30) + 30;
                b = r.nextInt(20) + 20;

                while (a < b) {
                    a = r.nextInt(30) + 30;
                    b = r.nextInt(20) + 20;
                }
                tempResult = a - b;
                if (tempResult < 25) {
                    num = 20;
                    extraAdd = 8;
                } else {
                    num = 48;
                    extraAdd = 22;
                }
            } else {
                a = r.nextInt(50) + 40;
                b = r.nextInt(35) + 25;

                while (a < b) {
                    a = r.nextInt(50) + 40;
                    b = r.nextInt(35) + 25;
                }
                tempResult = a - b;
                if (tempResult < 30) {
                    num = 20;
                    extraAdd = 13;
                } else if (tempResult < 50) {
                    num = 25;
                    extraAdd = 28;
                } else {
                    num = 25;
                    extraAdd = 48;
                }
            }
        } else if (TAG == 3) {
            a = r.nextInt(7) + 3;
            b = r.nextInt(4) + 6;
            tempResult = a * b;
            if (totalScore < 4) {
                if (tempResult < 40) {
                    num = 28;
                    extraAdd = 16;
                } else if (tempResult < 70) {
                    num = 25;
                    extraAdd = 38;
                } else {
                    num = 43;
                    extraAdd = 68;
                }
            } else if (totalScore < 8) {
                a = r.nextInt(7) + 9;
                b = r.nextInt(8) + 7;
                while (!((a >= 8 && a <= 15) && (b >= 8 && b <= 15))) {
                    a = r.nextInt(7) + 9;
                    b = r.nextInt(8) + 7;
                }
                tempResult = a * b;
                if (tempResult < 100){
                    num = 50;
                    extraAdd = 60;
                }else if (tempResult < 160){
                    num = 70;
                    extraAdd = 98;
                }else {
                    num = 83;
                    extraAdd = 160;
                }
            } else if (totalScore >= 8) {
                a = r.nextInt(8) + 12;
                b = r.nextInt(10) + 10;

                while (!((a >= 10 && a <= 20) && (b >= 10 && b <= 20))) {
                    a = r.nextInt(8) + 12;
                    b = r.nextInt(10) + 10;
                }
                tempResult = a * b;
                if (tempResult < 200){
                    num = 90;
                    extraAdd = 115;
                }else if (tempResult < 300){
                    num = 105;
                    extraAdd = 200;
                }else {
                    num = 105;
                    extraAdd = 300;
                }
            }

        } else {
            a = r.nextInt(30) + 20;
            b = r.nextInt(10) + 4;

            if (totalScore < 3) {
                while (a % b != 0) {
                    a = r.nextInt(30) + 20;
                    b = r.nextInt(10) + 4;
                }
                tempResult = a / b;
                if (tempResult < 8){
                    num = 5;
                    extraAdd = 4;
                }else {
                    num = 7;
                    extraAdd = 7;
                }
            } else if (totalScore < 7) {
                a = r.nextInt(50) + 40;
                b = r.nextInt(10) + 5;
                num = 19;//5
                extraAdd = 6;
                while (a % b != 0) {
                    a = r.nextInt(50) + 30;
                    b = r.nextInt(10) + 5;
                }
                tempResult = a / b;
                if (tempResult < 12){
                    num = 8;//5
                    extraAdd = 6;
                }else {
                    num = 8;//5
                    extraAdd = 12;
                }
            } else {
                a = r.nextInt(120) + 80;
                b = r.nextInt(10) + 10;

                while (a % b != 0) {
                    a = r.nextInt(110) + 90;
                    b = r.nextInt(10) + 10;
                }
                tempResult = a / b;
                if (tempResult < 12){
                    num = 8;//5
                    extraAdd = 7;
                }else if (tempResult < 17){
                    num = 8;//5
                    extraAdd = 12;
                }else {
                    num = 7;//5
                    extraAdd = 16;
                }
            }
        }

        int result;

        if (TAG == 1) {
            quesButton.setText(a + " + " + b);
            result = a + b;
        } else if (TAG == 3) {
            quesButton.setText(a + " × " + b);
            result = a * b;
        } else if (TAG == 2) {
            quesButton.setText(a + " - " + b);
            result = a - b;
        } else {
            quesButton.setText(a + " ÷ " + b);
            result = a / b;
        }


        locationOfCorrectAnswer = r.nextInt(4);
        arrayList.clear();

        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                arrayList.add(result);
            } else {
                incorrectAnswer = r.nextInt(num) + extraAdd;
                for (int z = 0; z < arrayList.size(); z++) {
                    while (incorrectAnswer == arrayList.get(z)) {
                        incorrectAnswer = r.nextInt(num) + extraAdd;
                    }
                }

                while (incorrectAnswer == result) {
                    incorrectAnswer = r.nextInt(num) + extraAdd;
                    for (int z = 0; z < arrayList.size(); z++) {
                        while (incorrectAnswer == arrayList.get(z)) {
                            incorrectAnswer = r.nextInt(num) + extraAdd;
                        }
                    }
                }
                arrayList.add(incorrectAnswer);
            }
        }

        firstButton.setText(Integer.toString(arrayList.get(0)));
        secondButton.setText(Integer.toString(arrayList.get(1)));
        thirdButton.setText(Integer.toString(arrayList.get(2)));
        fourthButton.setText(Integer.toString(arrayList.get(3)));

    }


    public void setTimer() {

        int time;

        if (totalScore < 3) {
            time = 10100;
        } else if (totalScore < 8) {
            time = 12100;
        } else {
            time = 15100;
        }

        timer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millis) {
                timerText.setText(String.valueOf(millis / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                totalScore++;
                score.setText(totalScore + "/10");
                timer.cancel();
                generateQuestion();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {

//        timer.cancel();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Leave Quiz")
//                .setMessage("Do you want to quit the current game?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        timer.start();
//
//                        dialogInterface.dismiss();
//                    }
//                })
//                .setCancelable(false)
//                .show();
        return;


    }
    public void back(){

        timer.cancel();
        final Dialog dialog = new Dialog(SecondActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.quit_dialog);
        Button resume = dialog.findViewById(R.id.resumeButton);
        Button quit = dialog.findViewById(R.id.quitButton);
        TextView heading = dialog.findViewById(R.id.headingText);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"kaushan.otf");
        heading.setTypeface(typeface);
        dialog.setCancelable(false);
        dialog.show();

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
                dialog.dismiss();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
