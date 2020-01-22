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

    View.OnClickListener listener_random = new View.OnClickListener() {
        public void onClick(View v) {

            int x = 0 , y = 0 , z = 0 ;

            for(int i = 0 ; i < 5 ; i++){
                x++; //équivalent à x = x + 1 ;
            }
            for(int i = 0 ; i < 5 ; i++){
                y = y + i;
            }
            for(int i = 5 ; i > 0 ; i --){
                z = z + i ;
            }

            //Ici vous devez me dire les valeurs de  x, y et z avant de décommentez la ligne ci-dessous

            //Cette ligne affiche la valeur des trois variables
            //first_tab.setText("x = " + x + "; y = " + y + "; z = " + z);



        }
    };
    View.OnClickListener listener_sort = new View.OnClickListener() {
        public void onClick(View v) {
            int x = 0, y = 10 , z = 50;
            /*
            Ecrivez une SEULE boucle for de telle manière qu'après être passé dans la boucle
            x = y = z .
            Soit : for( ; ; ) {
            Votre code ...
            Votre code...
            Votre code....
            }
            Ici on affiche les variables (first_tab.setText("x = " + x + "; y = " + y + "; z = " + z); )
            Et on arrive sur une égalité des trois variables


             */

        }
    };

    /*private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }*/
}
