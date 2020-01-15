package com.gos.liberty.pdt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UbxGameView extends View {


    private static Start start ;
    private static MenuBlackSheep menu;
    private static SoloPlay solo;

    private boolean gameFinished = false;
    private boolean gameFail = false;
    public boolean define = true;
    private int timeElapsed = 0, countdown = 10000;
    private int compteur = 0 , compteurMax = 9 , compteur_1 = 3 , compteur_2 = 6;
    private int checker;
    private Random random = new Random();
    private int random_bis = 0 ;


    public UbxGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        start = new Start(this.getResources());
        menu = new MenuBlackSheep(this.getResources());
        solo = new SoloPlay(this.getResources());


    }

    /* Fonction de mise à jour de l'affichage de la vue */
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Rect frame = new Rect();

        start.defineSize(canvas);
        start.setCanvas(canvas, paint);
        menu.defineSize(canvas);
        menu.setCanvas(canvas,paint);
        solo.defineSize(canvas);
        solo.setCanvas(canvas,paint);

        int sizePolice = ((canvas.getWidth())-(canvas.getWidth()/10))/25;
        int sizePolice_start = ((canvas.getWidth())-(canvas.getWidth()/10))/10;


        if(start.is_start()) {


            if (start.get_timer() >= 30)
                start.set_timer_zero();







            for(int i = 0 ; i < 30 ; i ++) {
                if(start.get_timer() >= i) {
                    start.mouvement_blabla(canvas,i);
                    start.draw(canvas, frame, paint, "black", i);
                }
            }
            printString("Welcome to the  ", canvas, canvas.getHeight() / 3, (canvas.getHeight() / 5) - sizePolice * 2, Color.BLACK, sizePolice_start);
            printString("Cycle of Life  ", canvas, canvas.getHeight() / 3,
                    (canvas.getHeight() / 4) + sizePolice * 2, Color.DKGRAY, sizePolice_start);





        }

        if(menu.is_Menu_BS_F() && !solo.is_Solo_True()){
            menu.draw(canvas,frame,paint);
        }

        if(solo.is_Solo_True()){
             for(int i = 0 ; i < 8 ; i ++){
                for(int j =0 ; j < 8 ; j++){
                    solo.draw(canvas,frame,paint, solo.get_Type(i,j), i,j);
                    if (solo.get_Cara(i, j) != SoloPlay.typeCara.VIDE)
                        solo.draw(canvas, frame, paint, solo.get_Cara(i, j), i, j);
                    if(solo.get_which_sheep_grille(i,j) != SoloPlay.sheep.VIDE)
                        solo.draw_sheep_in_grille(canvas,frame,paint,solo.get_which_sheep_grille(i,j), i, j);
                    if(solo.is_select_enCours(i,j) && solo.isIs_select_cara()) {
                        solo.animation_select_Cara(canvas, frame, paint, i, j);
                    }
                    if(solo.is_can_move(i,j) && solo.isIs_select_cara()){
                        if(solo.is_move_one(i,j))
                            solo.show_can_move_one(canvas,frame,paint,i,j);
                        else
                            solo.show_can_move(canvas,frame,paint,i,j);
                    }
                    if(solo.is_can_move_sheep(i,j) && solo.isIs_select_sheep()){
                        solo.show_can_move_sheep(canvas,frame,paint,i,j);
                    }
                    if(solo.get_Cara(i,j) == SoloPlay.typeCara.BIRD && solo.is_frozen(0))
                        solo.draw_sheep_in_grille(canvas,frame,paint, SoloPlay.sheep.ICE_CUBE,i,j);
                    if(solo.get_Cara(i,j) == SoloPlay.typeCara.ELEPHANT && solo.is_frozen(1))
                        solo.draw_sheep_in_grille(canvas,frame,paint, SoloPlay.sheep.ICE_CUBE,i,j);
                    if(solo.get_Cara(i,j) == SoloPlay.typeCara.TURTLE && solo.is_frozen(2))
                        solo.draw_sheep_in_grille(canvas,frame,paint, SoloPlay.sheep.ICE_CUBE,i,j);



                }
            }

            switch(solo.getPlayer_One()) {
                case TURTLE:
                    printString("Vous êtes : " + solo.get_Player_One_String(solo.getPlayer_One()),
                            canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15), Color.BLUE, sizePolice * 2);
                    break;
                case BIRD:
                    printString("Vous êtes : " + solo.get_Player_One_String(solo.getPlayer_One()),
                            canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15), Color.RED, sizePolice * 2);
                    break;
                case ELEPHANT:
                    printString("Vous êtes : " + solo.get_Player_One_String(solo.getPlayer_One()),
                            canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15), Color.GREEN, sizePolice * 2);
                    break;
            }

            if(solo.get_winner() != SoloPlay.typeCara.VIDE){
                 switch(solo.get_winner()){
                     case ELEPHANT:
                         printString("WINNER : " + solo.get_Player_One_String(solo.getPlayer_One()),
                                 canvas, canvas.getWidth() / 2, (canvas.getHeight() / 2), Color.GREEN, sizePolice * 2);
                         break;
                     case BIRD:
                         printString("WINNER : " + solo.get_Player_One_String(solo.getPlayer_One()),
                                 canvas, canvas.getWidth() / 2, (canvas.getHeight() / 2), Color.RED, sizePolice * 2);
                         break;
                     case TURTLE:
                         printString("WINNER : " + solo.get_Player_One_String(solo.getPlayer_One()),
                                 canvas, canvas.getWidth() / 2, (canvas.getHeight() / 2), Color.BLUE, sizePolice * 2);
                         break;
                 }
            }

            for(int i = 0 ; i < 6 ; i ++) {
                if (solo.isIs_select_sheep() && solo.is_select_tab_sheep(i)) {
                    solo.animation_select_Sheep(canvas, frame, paint, i);
                }
            }

            if(!solo.is_action_1() && !solo.is_action_2() && !solo.is_action_3() ){
                printString("Veuillez selectionnez : " + solo.get_Player_One_String(solo.getPlayer_One()) + " ou le Dragon.",
                        canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15) + sizePolice*2, Color.BLACK, sizePolice);
            }
            if(solo.is_action_1()){
                     printString("Il vous reste : " + solo.get_compteur_move() + " déplacement(s)" + solo.get_compteur_move_restant(), canvas, canvas.getWidth() / 2,
                             (canvas.getHeight() / 15) + sizePolice*2, Color.BLACK, sizePolice);
                     if (solo.get_compteur_move_restant() < 3 && solo.get_compteur_move_restant() > 0) {
                         printString("Appuyez ici pour vous arrêter ", canvas, canvas.getWidth() / 2 + canvas.getWidth() / 4,
                                 canvas.getHeight() / 5 - sizePolice * 2, Color.BLACK, sizePolice);
                     }
                 }

            if(solo.is_action_2()){
                printString("Sélectionné un mouton", canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15) +sizePolice*2, Color.BLACK, sizePolice);
            }
            if(solo.is_action_3()){
                printString("Placé votre mouton", canvas, canvas.getWidth() / 2, (canvas.getHeight() / 15) +sizePolice*2, Color.BLACK, sizePolice);
            }


            solo.draw(canvas,frame,paint);




        }


        postInvalidate();
    }

    /* Mise à jour du compteur de temps */
    private void updateCountdown(Canvas canvas) {
        printString(Integer.toString(countdown), canvas, canvas.getWidth()/2, canvas.getHeight(), Color.BLUE, 80);
        if(!gameFinished) {
            timeElapsed++;
            if(countdown > 0)
                countdown--;
        }
    }

    public void game_solo_en_cours(float x, float y){


        if(solo.is_action_3()){


            if(solo.is_can_move_sheep(solo.convert_event_X(x),solo.convert_event_Y(y))){

                    solo.set_false_action_3();

                solo.lets_move_sheep(solo.convert_event_X(x),solo.convert_event_Y(y));
                for(int i = 0 ; i < 6 ; i ++){
                    solo.set_select_tab_sheep_false(i);
                }
                solo.set_compteur(0);
                solo.set_compteur_restant(0);
                solo.fin_de_tour();
                solo.check_if_frozen(solo.getPlayer_One());
            }
            else{
                for (int i = 0 ; i < 8 ; i ++){
                    for (int j = 0 ; j < 8 ; j++){
                        solo.set_cannot_move_sheep(i,j);
                    }
                }
                for(int i = 0 ;i <5 ; i++) {
                    solo.set_select_tab_sheep_false(i);
                }

                        solo.set_action_2();
                        solo.set_false_action_3();


            }

        }

        if(solo.is_action_1()) {



                    if(solo.is_move_one(solo.convert_event_X(x), solo.convert_event_Y(y))) {
                        solo.lets_move(solo.convert_event_X(x), solo.convert_event_Y(y));
                        if (solo.get_compteur_move_restant() == 0 && solo.is_action_1()) {
                            solo.set_compteur(0);
                            solo.set_false_action_1();
                            solo.set_action_2();
                            solo.set_all_select_false();
                            solo.apply_effect_sheeps();
                            if(!solo.still_sheep_available()){
                                switch(solo.getPlayer_One()){
                                    case BIRD:
                                        solo.set_winner(SoloPlay.typeCara.ELEPHANT);
                                        break;
                                    case ELEPHANT:
                                        solo.set_winner(SoloPlay.typeCara.TURTLE);
                                        break;
                                    case TURTLE:
                                        solo.set_winner(SoloPlay.typeCara.BIRD);
                                        break;
                                }
                            }


                        }



                    }


        }
        if(!solo.is_action_1() && !solo.is_action_2() && !solo.is_action_3() ) {
            solo.get_Select_case(solo.convert_event_X(x), solo.convert_event_Y(y));
            solo.empty_sheep_in_the_way();
            if(solo.isIs_select_cara() ) {
                solo.set_action_1();
                solo.move();
            }



        }


        solo.set_compte(0);

    }
    public void game_solo_sheep(float x, float y){



            solo.empty_sheep_in_the_way();

            solo.get_sheep_selected(solo.convert_event_sheep(x));

            if(solo.is_sheep_player_selected()) {
                solo.move_sheep();
                solo.set_action_3();
                solo.set_false_action_2();
}







        solo.set_compte(0);
                }

    /* Fin de la partie, arrêt de la balle */




    /* Fonction d'affichage de texte dans la vue */
    private void printString(String str, Canvas canvas, int xPos, int yPos, int color, int size) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(size);
        canvas.drawText(str, xPos, yPos, paint);
    }

    /* Detection d'appui sur l'écran */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :

                if(menu.is_Which_Menu_0()){

                    if(solo.get_winner() != SoloPlay.typeCara.VIDE) {
                        solo.restart_Game();
                    }

                    if(solo.check_click(event.getX(),event.getY()) && !solo.is_action_2())
                        this.game_solo_en_cours(event.getX(),event.getY());

                     if (solo.check_click_sheep(event.getX(),event.getY()) && solo.is_action_2() || solo.is_action_3()) {
                         solo.set_action_2();
                         this.game_solo_sheep(event.getX(), event.getY());
                     }

                     if(solo.click_check_up_right(event.getX(),event.getY()))
                         if(solo.get_compteur_move_restant() < 3 && solo.get_compteur_move_restant() >0) {
                             solo.set_compteur(0);
                             solo.set_false_action_1();
                             solo.set_action_2();
                             solo.set_all_select_false();
                             solo.apply_effect_sheeps();
                             for(int i = 0 ; i < 8 ; i ++){
                                 for(int j = 0 ; j < 8 ; j++){
                                     solo.set_all_cannot_move();
                                 }
                             }
                         }



                }

                if(menu.is_Menu_BS_F()){
                    menu.get_Coord_BS_Menu(event.getX(), event.getY());
                        if(menu.is_Which_Menu_0()){
                            solo.set_Solo_True();
                           // menu.set_Which_Menu_False();
                            solo.random_Remplir_Case();
                            solo.restart_Game();
                            menu.set_is_Menu_BS_False();
                        }
                }
                if(start.is_start()) {
                    start.false_is_Start();
                    menu.set_Is_Menu_BS_True();
                    menu.set_Which_Menu_False();
                    Log.d("TOUCH EVENT","Start is false menu is true");
                }



                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d("TOUCH EVENT","Action was MOVE");

                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d("TOUCH EVENT","Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("TOUCH EVENT","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("TOUCH EVENT","Movement occurred outside bounds of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

}