package com.tajinc.quizapp;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView List;
    List<String> Name;
    List<Integer> logo;
    Animation anim = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        anim = AnimationUtils.loadAnimation(this,R.anim.myscale);

        List = findViewById(R.id.List);
        Name = new ArrayList<>();
        logo = new ArrayList<>();
        //присваиваем название
        Name.add("Программирования");
        Name.add("Спорт");
        Name.add("География");
        Name.add("Кино");
        Name.add("Астрономия");
        Name.add("История");
        //присваиваем лого
        logo.add(R.drawable.coding);
        logo.add(R.drawable.sport);
        logo.add(R.drawable.geography);
        logo.add(R.drawable.movie);
        logo.add(R.drawable.astronomy);
        logo.add(R.drawable.history);

    Adapter adapter = new Adapter(this, Name, logo);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        List.setLayoutManager(gridLayoutManager);
        List.startAnimation(anim);
        List.setAdapter(adapter);
    }
}
