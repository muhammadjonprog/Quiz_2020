package com.tajinc.quizapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AstronomyActivity extends AppCompatActivity {

    public Button btn_next, btn_true, btn_false, btn_restart,btn_menu;
    public TextView txt, question_pos, tv_timer, tv_true_false;
    private int quest_index = 0, count_ques = 1, count_true = 0, count_f = 0, count_time = 30;
    private RatingBar rb;
    float r_b = 3.0f;
    private boolean running, run_dialog = true;
    private int percent;
    public int count_heart = 3;

    private TrueFalse[] questions = new TrueFalse[]{
            new TrueFalse(R.string.quest141, true),
            new TrueFalse(R.string.quest142, true),
            new TrueFalse(R.string.quest143, true),
            new TrueFalse(R.string.quest144, true),
            new TrueFalse(R.string.quest145, true),
            new TrueFalse(R.string.quest146, true),
            new TrueFalse(R.string.quest147, true),
            new TrueFalse(R.string.quest148, true),
            new TrueFalse(R.string.quest149, true),
            new TrueFalse(R.string.quest150, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_true = (Button) findViewById(R.id.btn_true);
        btn_false = (Button) findViewById(R.id.btn_false);
        txt = (TextView) findViewById(R.id.result_txt);
        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_menu = (Button) findViewById(R.id.btn_menu);
        rb = (RatingBar) findViewById(R.id.rating);
        question_pos = (TextView) findViewById(R.id.question_pos);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_true_false = (TextView) findViewById(R.id.tv_true_false);
        runTimer();


        final int question = questions[quest_index].getQuestion();
        txt.setText(question);
        rb.setNumStars(3);
        rb.setStepSize(1.0f);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                resetTimer();
            }
        });
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                resetTimer();
            }
        });
        //TODO Итог игры в виде отчет после прохождения опросов
        question_pos.setText(count_ques + "/" + questions.length);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quest_index < questions.length - 1) {
                    quest_index++;
                    count_ques++;
                    final int question = questions[quest_index].getQuestion();
                    txt.setText(question);
                    question_pos.setText(count_ques + "/" + questions.length);
                    tv_true_false.setVisibility(View.INVISIBLE);
                    btn_true.setVisibility(View.VISIBLE);
                    btn_false.setVisibility(View.VISIBLE);
                    tv_timer.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.INVISIBLE);
                    startTimer();
                } else {
                    run_dialog = false;
                    tv_true_false.setVisibility(View.INVISIBLE);
                    percent = count_true * 100 / questions.length;
                    if (percent >= 90) {
                        txt.setText("Молодцы.Сдали тест!" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Вы умный человек,оценка - Отлично.");
                    } else if (percent <= 20) {
                        txt.setText("Вы не сдали тест" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Оценка - Неудовлетворително." + "\n" + "Но чтобы было отлично, надо 90% верных ответов!");
                    } else {
                        txt.setText("Молодцы.Сдали тест!" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Вы умный, оценка - Хорошо." + "\n" + "Но чтобы было отлично, надо 90% верных ответов!");
                    }
                    btn_next.setVisibility(View.INVISIBLE);
                    btn_restart.setVisibility(View.VISIBLE);
                    btn_menu.setVisibility(View.VISIBLE);
                    question_pos.setVisibility(View.INVISIBLE);
                    rb.setVisibility(View.INVISIBLE);
                }
            }
        });
//TODO Действие при кнопке рестарта.
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quest_index = 0;
                count_ques = 1;
                question_pos.setText(+count_ques + "/" + questions.length);
                final int question = questions[quest_index].getQuestion();
                txt.setText(question);
                txt.setTextColor(Color.WHITE);
                tv_timer.setVisibility(View.VISIBLE);
                tv_true_false.setVisibility(View.INVISIBLE);
                btn_true.setVisibility(View.VISIBLE);
                btn_false.setVisibility(View.VISIBLE);
                btn_restart.setVisibility(View.INVISIBLE);
                btn_menu.setVisibility(View.INVISIBLE);
                question_pos.setVisibility(View.VISIBLE);
                rb.setVisibility(View.VISIBLE);
                run_dialog = true;
                tv_true_false.setText("");
                count_true = 0;
                count_f = 0;
                percent = 1;
                startTimer();
                rb.setNumStars(3);
                count_heart = 3;
                rb.setRating(count_heart);
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(AstronomyActivity.this,MainActivity.class);
                startActivity(menu);
                finish();
            }
        });
    }


    //TODO Программа проверять ответ
    private void checkAnswer(boolean answer_user) {
        boolean answer = questions[quest_index].isTrueQuestion();
        if (answer_user == answer) {
            tv_true_false.setVisibility(View.VISIBLE);
            tv_true_false.setTextColor(Color.rgb(255, 231, 75));
            tv_true_false.setText("Правильный ответ");
            count_true++;
            btn_true.setVisibility(View.INVISIBLE);
            btn_false.setVisibility(View.INVISIBLE);
            tv_timer.setVisibility(View.INVISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        } else {
            ratingBar();
            tv_true_false.setVisibility(View.VISIBLE);
            tv_true_false.setTextColor(Color.rgb(226, 111, 155));
            tv_true_false.setText("Неправильный ответ");
            count_f++;
            btn_true.setVisibility(View.INVISIBLE);
            btn_false.setVisibility(View.INVISIBLE);
            tv_timer.setVisibility(View.INVISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }
    }

    //TODO Таймер для вопросов 30 секунд
    public void runTimer() {
        running = true;
        final Handler handler = new Handler();

        handler.post(new Runnable() {

            @Override
            public void run() {
                tv_timer.setText(String.valueOf(count_time));
                if (running && count_time > 0) {
                    count_time--;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }

    public void resetTimer() {
        running = false;
        count_time = 30;
    }

    public void startTimer() {
        running = true;
    }

    public void onBackPressed() {
        dialog();
    }

    public void dialog() {
        if (run_dialog) {
            final Dialog dialog = new Dialog(AstronomyActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_game);
            Button btn_yes_game = dialog.findViewById(R.id.btn_yes_game);
            Button btn_no_game = dialog.findViewById(R.id.btn_no_game);
            View.OnClickListener onClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.btn_yes_game:
                            dialog.cancel();
                            tv_timer.setVisibility(View.INVISIBLE);
                            btn_false.setVisibility(View.INVISIBLE);
                            btn_true.setVisibility(View.INVISIBLE);
                            rb.setVisibility(View.INVISIBLE);
                            question_pos.setVisibility(View.INVISIBLE);
                            rb.setVisibility(View.INVISIBLE);
                            btn_restart.setVisibility(View.VISIBLE);
                            btn_menu.setVisibility(View.VISIBLE);
                            tv_true_false.setVisibility(View.INVISIBLE);
                            run_dialog = false;
                            percent = count_true * 100 / questions.length;
                            if (percent >= 90) {
                                txt.setText("Молодцы.Сдали тест!" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Вы умный человек,оценка - Отлично.");
                            } else if (percent <= 20) {
                                txt.setText("Вы не сдали тест" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Оценка - Неудовлетворително." + "\n" + "Но чтобы было отлично, надо 90% верных ответов!");
                            } else {
                                txt.setText("Молодцы.Сдали тест!" + "\n" + "-Количество вопросов : " + questions.length + "\n" + "-Правильных ответов : " + count_true + "\n" + "-Неправильных : " + count_f + "\n" + "-Соотношение : " + percent + "%" + "\n" + "Вы умный, оценка - Хорошо." + "\n" + "Но чтобы было отлично, надо 90% верных ответов!");
                            }
                            resetTimer();
                            break;
                        case R.id.btn_no_game:
                            dialog.cancel();
                            break;
                    }
                }
            };
            btn_yes_game.setOnClickListener(onClick);
            btn_no_game.setOnClickListener(onClick);
            dialog.show();
        }
    }

    public void ratingBar() {
        count_heart--;
        rb.setRating(count_heart);
        if (count_heart == 0) {
            HeartOut();
        }
    }

    public void HeartOut() {
        final Dialog dialog = new Dialog(AstronomyActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.heart_out_dialog);
        Button btnMenu = dialog.findViewById(R.id.btnMenu);
        Button btnRestart = dialog.findViewById(R.id.btnRestart);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnMenu:
                        Intent intent = new Intent(AstronomyActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnRestart:
                        quest_index = 0;
                        count_ques = 1;
                        question_pos.setText(+count_ques + "/" + questions.length);
                        final int question = questions[quest_index].getQuestion();
                        txt.setText(question);
                        txt.setTextColor(Color.WHITE);
                        tv_timer.setVisibility(View.VISIBLE);
                        tv_true_false.setVisibility(View.INVISIBLE);
                        btn_true.setVisibility(View.VISIBLE);
                        btn_false.setVisibility(View.VISIBLE);
                        btn_restart.setVisibility(View.INVISIBLE);
                        question_pos.setVisibility(View.VISIBLE);
                        btn_next.setVisibility(View.INVISIBLE);
                        rb.setVisibility(View.VISIBLE);
                        run_dialog = true;
                        tv_true_false.setText("");
                        count_true = 0;
                        count_f = 0;
                        percent = 1;
                        running = true;
                        count_heart = 3;
                        rb.setRating(count_heart);
                        rb.setNumStars(3);
                        dialog.cancel();
                        break;
                }
            }
        };
        btnMenu.setOnClickListener(onClick);
        btnRestart.setOnClickListener(onClick);
        dialog.show();
    }
}
