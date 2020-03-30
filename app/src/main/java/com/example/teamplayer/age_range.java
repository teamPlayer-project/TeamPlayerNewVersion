package com.example.teamplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class age_range extends AppCompatActivity {
    List<String> ages = new ArrayList<>();
    ScrollChoice scrollChoice;
    String dataToSave;
    Button buttonDone;
    String classBack;
    String activityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_range);
        scrollChoice = (ScrollChoice)findViewById(R.id.scroll_choice);
        buttonDone = (Button) findViewById(R.id.done);
        classBack = getIntent().getStringExtra("ACTIVITY");
        activityName = getIntent().getStringExtra("ACTIVITY_NAME");


        loadData();
        continueLoad();

        buttonDone.setOnClickListener(new View.OnClickListener(){
            //@override
            public void onClick(View v){
                if(classBack.equals("activity_create_new")) {
                    Intent intent = new Intent(age_range.this, activity_create_new.class);
                    intent.putExtra("AGE", dataToSave);
                    intent.putExtra("ACTIVITY_NAME", activityName);
                    startActivity(intent);
                }
            }
        });

    }

    public void loadData() {
        ages.add("12-16");
        ages.add("16-18");
        ages.add("18-21");
        ages.add("21-30");
        ages.add("30-40");
        ages.add("40-50");
        ages.add("50+");
        ages.add("Other");
    }

    public void continueLoad() {
        scrollChoice.addItems(ages, 4);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                dataToSave = name;
                //dataToSave.put("ageRange", name);
               // Button button = (Button)findViewById(R.id.age_range);
               // button.setText(name);

            }
        });
    }



    public void doneButton(View view) {
        Intent intent=new Intent(this,activity_create_new.class);
        startActivity(intent);
    }
}
