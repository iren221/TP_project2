package com.example.tp_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class RecommendationsActivity extends AppCompatActivity {

    String[] name_recommend = new String[] {"Первый совет", "Второй совет", "Третий совет", "Четвертый совет", "Пятый совет"};
    int[] photo_recommend = new int[] {R.drawable.bear, R.drawable.cat, R.drawable.dog, R.drawable.koz, R.drawable.kva};
    ListView list_recommend;
    AppCompatImageButton tabbar_pets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        list_recommend = findViewById(R.id.list_recommend);
        tabbar_pets = findViewById(R.id.tabbar_pets);

        ProgramAdapter programAdapter = new ProgramAdapter(RecommendationsActivity.this, name_recommend, photo_recommend);
        list_recommend.setAdapter(programAdapter);

        tabbar_pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), List_all_pet.class);
                startActivity(intent2);
            }
        });

    }
}