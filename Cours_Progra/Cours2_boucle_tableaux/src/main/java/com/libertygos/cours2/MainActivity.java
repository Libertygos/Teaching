package com.libertygos.cours2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button_random;
    Button button_sort;
    TextView first_tab;

    TextView second_tab, third_tab, fourth_tab, fifth_tab; //ignorer ceci

    int tab_1[] = new int[5];

    int tab_2[] = new int[5]; //ignorer ceci
    int tab_3[] = new int[5]; //ignorer ceci
    int tab_4[] = new int[5]; //ignorer ceci
    int tab_5[] = new int[5]; //ignorer ceci

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_random = findViewById(R.id.button_random) ;
        button_sort = findViewById(R.id.button_sort);

        first_tab = findViewById(R.id.first_tab);
        second_tab = findViewById(R.id.second_tab);
        third_tab = findViewById(R.id.third_tab);
        fourth_tab = findViewById(R.id.fourth_tab);
        fifth_tab = findViewById(R.id.fifth_tab);

        second_tab.setVisibility(View.INVISIBLE); //ignorer ceci
        third_tab.setVisibility(View.INVISIBLE); //ignorer ceci
        fourth_tab.setVisibility(View.INVISIBLE);//ignorer ceci
        fifth_tab.setVisibility(View.INVISIBLE);//ignorer ceci

        button_random.setOnClickListener(listener_random);
        button_sort.setOnClickListener(listener_sort);
    }

    /*fais ce qu'il y a entre les accolades quand on appuie sur le bouton lié à ce listner*/
    View.OnClickListener listener_random = new View.OnClickListener() {
        public void onClick(View v) {

            for(int i = 0 ; i < tab_1.length ; i++){
                tab_1[i] = getRandomNumberInRange(0,10);
            }
            first_tab.setText("[" + tab_1[0] + "," + tab_1[1] + "," +tab_1[2] + "," + tab_1[3] + "," + tab_1[4] +"]");


        }
    };
    /*fais ce qu'il y a entre les accolades quand on appuie sur le bouton lié à ce listner*/
    View.OnClickListener listener_sort = new View.OnClickListener() {
        public void onClick(View v) {


        }
    };

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
