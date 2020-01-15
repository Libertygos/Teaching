package com.libertygos.cours1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button_left; //"Objet" de type Button s'appelant button_left
    TextView text_left; //"Objet" de type TextView s'appelant text_left

    Button button_right; //"Objet" de type Button s'appelant button_right
    TextView text_right; //"Objet" de type TextView s'appelant text_right

    Button button_result; //"Objet" de type Button s'appelant button_result
    TextView text_result; //"Objet" de type TextView s'appelant text_right

    int valeur_left, valeur_right, valeur_result ; // trois "variables" de type INT





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valeur_left = 0; //après cette ligne la variable "valeur_left" vaut 0
        valeur_right = 0;
        valeur_result = 0;

        button_left = findViewById(R.id.button_left) ; //lie l'Objet button_left (en java) au Button XML (qui a comme id : button_left )
        text_left = findViewById(R.id.text_left) ;

        button_right = findViewById(R.id.button_right);
        text_right = findViewById(R.id.text_right);

        button_result = findViewById(R.id.button_result);
        text_result = findViewById(R.id.text_result);


        button_left.setText("+"); //défini le texte de l'Objet Button (XML)
        text_left.setText(" " +valeur_left);

        button_right.setText("+");
        text_right.setText(" " +valeur_right);

        button_result.setText("=");
        text_result.setText(" " +valeur_result);


        button_left.setOnClickListener(listener_left); //lie l'Objet (Java) button_left au listener_left
        button_right.setOnClickListener(listener_right);
        button_result.setOnClickListener(listener_result);
    }

    /*fais ce qu'il y a entre les accolades quand on appuie sur le bouton lié à ce listner*/
    View.OnClickListener listener_left = new View.OnClickListener() {
        public void onClick(View v) {
            valeur_left++; //équivalent à valeur_left = valeur_left +1

            text_left.setText(" " +valeur_left);

            /*
            Le text_left doit apparaître rouge lorsque valeur_left dépasse 5
                text_left.setTextColor(Color.RED);
                Cette fonction change la couleur du texte
             */

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

            /*
            Le texte doit apparaître Vert quand valeur_result dépasse 10 et bleu lorsque valeur_result dépasse 20
                text_result.setTextColor(Color.GREEN);
                text_result.setTextColor(Color.BLUE);
             */

        }
    };
}