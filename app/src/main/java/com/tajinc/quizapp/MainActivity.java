package com.tajinc.quizapp;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Animation anim = null;
    Button  btn_play, btn_settings, btn_apps, btn_exit;
    Button btn_yes, btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 // создаем анимация для кнопок меню
        anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
// Присваиваем кнопкам свои ID
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_settings = (Button) findViewById(R.id.btn_settings);
        btn_apps = (Button) findViewById(R.id.btn_apps);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_no = (Button) findViewById(R.id.btn_no);
// старт анимации
        btn_play.startAnimation(anim);
        btn_settings.startAnimation(anim);
        btn_apps.startAnimation(anim);
        btn_exit.startAnimation(anim);
//Присваиваем обработчик
        btn_play.setOnClickListener(onClick);
        btn_apps.setOnClickListener(onClick);
        btn_settings.setOnClickListener(onClick);
        btn_exit.setOnClickListener(onClick);
    }
    //Создаем обработчик
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_play:
                    Intent intent = new Intent(MainActivity.this, com.tajinc.quizapp.CategoryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_settings:
                    Toast.makeText(MainActivity.this,"Этот раздел на стадии разработки",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_apps:
                    Uri uri = Uri.parse("http://www.facebook.com");
                    Intent web = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(web);
                    break;
                case R.id.btn_exit:
                    showDialog();
            }
        }
    };
    //TODO Диалог окно выхода из приложения
    public void showDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);
        btn_yes.startAnimation(anim);
        btn_no.startAnimation(anim);
        View.OnClickListener onclick_btn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_yes:
                        finish();
                        break;
                    case R.id.btn_no:
                        dialog.dismiss();
                        break;
                }
            }
        };
        btn_yes.setOnClickListener(onclick_btn);
        btn_no.setOnClickListener(onclick_btn);
        dialog.show();
    }
//TODO Метод для кнопки назад
    public void onBackPressed(){
        showDialog();
    }
    }