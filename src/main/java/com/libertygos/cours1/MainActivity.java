package com.libertygos.cours1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button_left;
    TextView text_left;

    Button button_right;
    TextView text_right;

    Button button_result;
    TextView text_result;

    int valeur_left, valeur_right, valeur_result ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valeur_left = 0;
        valeur_right = 0;
        valeur_result = 0;

        button_left = findViewById(R.id.button_left) ;
        text_left = findViewById(R.id.text_left) ;

        button_right = findViewById(R.id.button_right);
        text_right = findViewById(R.id.text_right);

        button_result = findViewById(R.id.button_result);
        text_result = findViewById(R.id.text_result);


        button_left.setText("+");
        text_left.setText(" " +valeur_left);

        button_right.setText("+");
        text_right.setText(" " +valeur_right);

        button_result.setText("=");
        text_result.setText(" " +valeur_result);


        button_left.setOnClickListener(listener_left);
        button_right.setOnClickListener(listener_right);
        button_result.setOnClickListener(listener_result);
    }

    View.OnClickListener listener_left = new View.OnClickListener() {
        public void onClick(View v) {
            valeur_left++; //équivalent à valeur_left = valeur_left +1 ;

            text_left.setText(" " +valeur_left);

        }
    };

    View.OnClickListener listener_right = new View.OnClickListener() {
        public void onClick(View v) {
            valeur_right++; //équivalent à valeur_right = valeur_right +1 ;

            text_right.setText(" " +valeur_right);

        }
    };

    View.OnClickListener listener_result = new View.OnClickListener() {
        public void onClick(View v) {
            valeur_result = valeur_left + valeur_right ;

            text_result.setText(" " +valeur_result);

        }
    };
}