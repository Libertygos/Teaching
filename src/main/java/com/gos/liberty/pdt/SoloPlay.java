package com.gos.liberty.pdt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Jules on 03/06/2018.
 */

public class SoloPlay {

    private Bitmap sheep_white = null;
    private Bitmap sheep_red = null;
    private Bitmap sheep_green = null;
    private Bitmap sheep_blue = null;
    private Bitmap sheep_black = null;

    private Bitmap turtle = null;
    private Bitmap dragon = null;
    private Bitmap bird = null;
    private Bitmap elephant = null;

    private Bitmap grille = null;

    private Bitmap case_red = null;
    private Bitmap case_green = null;
    private Bitmap case_white = null;
    private Bitmap case_blue = null;
    private Bitmap case_black = null;
    private Bitmap case_sheep_in_the_way = null;

    private Bitmap hourglass_sheep = null;
    private Bitmap plus_sheep = null;
    private Bitmap swap_sheep = null;
    private Bitmap ice_cube_sheep = null;

    private Rect rect;
    private Canvas canvas;
    private Paint paint;

    private int posX= 0 ,posY = 0 ,pos_X_middle = 0, sizeX = 0 , sizeY = 0, direction = 1, compte = 0, pos_sheepX = 0, pos_sheep_Y = 0;
    private int compteur_mouv = 0, compteur_mouv_restant = 0;


    int way_taken [][][] = new int [3][3][3];
    int better_way = 0;
    int number_of_ways = 0;

    private sheep which_sheep_grille[][] = new sheep[8][8];
    private sheep which_sheep_in_the_way[][] = new sheep[8][8];


    private boolean is_there_a_sheep[][] = new boolean[3][3];
    private boolean is_Solo = false;
    private boolean is_Vide[][] = new boolean[8][8];
    private boolean select[][] = new boolean[8][8];
    public boolean can_move[][] = new boolean[8][8];
    public boolean sheep_in_the_way[][] = new boolean[8][8];
    public boolean can_move_sheep[][] = new boolean[8][8];
    private boolean is_select_cara;
    private boolean after_move_bool = false;
    private boolean is_select_sheep;
    private boolean is_select_sheep_tab[]= new boolean[6];

    private boolean is_froze[] = new boolean[3];

    private boolean action_1_en_cours;
    private boolean action_2_en_cours;
    private boolean action_3_en_cours;

    private typeCara game_won ;

    public enum typeCases {

        BLACK_CASE,
        RED_CASE,
        BLUE_CASE,
        GREEN_CASE,
        WHITE_CASE
    }

    public enum typeCara{
        TURTLE,
        DRAGON,
        BIRD,
        ELEPHANT,
        WHITE_SHEEP,
        BLACK_SHEEP,
        RED_SHEEP,
        GREEN_SHEEP,
        BLUE_SHEEP,
        VIDE

    }

    public enum sheep {
        HOURGLASS,
        ICE_CUBE,
        SWAP,
        PLUS,
        BLACK,
        VIDE
    }

    private typeCases cases[][] = new typeCases[8][8];
    private typeCara cara[][] = new typeCara[8][8];
    private typeCara player_One = typeCara.VIDE;
    private sheep sheep_player[] = new sheep[6];
    private sheep sheep_player_2[] = new sheep[6];
    private sheep sheep_player_3[] = new sheep[6];

    private int is_sheep_in_the_way_at_case_x[][][] = new int[3][3][3];




    public SoloPlay(Resources resources){
        sheep_white = BitmapFactory.decodeResource(resources, R.drawable.sheep_white);
        sheep_blue = BitmapFactory.decodeResource(resources, R.drawable.sheep_blue);
        sheep_red = BitmapFactory.decodeResource(resources, R.drawable.sheep_red);
        sheep_green = BitmapFactory.decodeResource(resources, R.drawable.sheep_green);

        turtle = BitmapFactory.decodeResource(resources, R.drawable.cara_water_001);
        elephant = BitmapFactory.decodeResource(resources, R.drawable.elephant_cara);
        bird = BitmapFactory.decodeResource(resources, R.drawable.bird_cara);
        dragon = BitmapFactory.decodeResource(resources, R.drawable.dragon_cara);


        hourglass_sheep = BitmapFactory.decodeResource(resources, R.drawable.hourglass_cartoon_vertical);
        swap_sheep = BitmapFactory.decodeResource(resources, R.drawable.arrows_cartoon);
        plus_sheep = BitmapFactory.decodeResource(resources, R.drawable.plus);
        ice_cube_sheep = BitmapFactory.decodeResource(resources, R.drawable.ice_cube_drawing);
        sheep_black = BitmapFactory.decodeResource(resources, R.drawable.sheep_black);

        case_black = BitmapFactory.decodeResource(resources, R.drawable.case_black);
        case_blue = BitmapFactory.decodeResource(resources, R.drawable.case_blue);
        case_green = BitmapFactory.decodeResource(resources, R.drawable.case_green);
        case_red = BitmapFactory.decodeResource(resources, R.drawable.case_red);
        case_white = BitmapFactory.decodeResource(resources, R.drawable.case_white);
        case_sheep_in_the_way = BitmapFactory.decodeResource(resources, R.drawable.case_sheep_in_the_way);



    }



    public void remplis_case(int number_left, int number_right, typeCases type_cases ){

             cases[number_left][number_right] = type_cases;

    }

    public void random_Remplir_Case(){
        double random = 0 ;

        for(int i = 0 ; i < 8 ; i++){
            for(int j =0 ; j < 8 ; j++) {
                random = Math.random() * 3;
                if (random < 1)
                    remplis_case(i,j, typeCases.RED_CASE);
                else if(random > 1 && random < 2)
                    remplis_case(i,j, typeCases.GREEN_CASE);
                else
                    remplis_case(i,j,typeCases.BLUE_CASE);
            }

        }
    }

    public typeCases get_Type(int number_left, int number_right){
        return cases[number_left][number_right];
    }

    public typeCara get_Cara(int number_left, int number_right){
        return cara[number_left][number_right];
    }

    public void set_Cara(int number_left, int number_right, typeCara typeCara){
        cara[number_left][number_right] = typeCara;
    }

    public void set_All_Cara_False(){
        for (int i = 0 ; i < 8 ; i ++){
            for(int j = 0 ; j < 8 ; j++){
                cara[i][j] = typeCara.VIDE;

            }
        }
    }

    public typeCara getPlayer_One(){
        return player_One;
    }

    public void set_player_One(typeCara typeCara){
        player_One = typeCara;
    }

    public String get_Player_One_String(typeCara player_One){
        switch (player_One){
            case ELEPHANT:
                return "L'Elephant";
            case TURTLE:
                return "La Tortue";
            case BIRD:
               return  "L'Oiseau";
        }
        return "Il y a eu une erreur";
    }

    public void setPlayer_One(typeCara typeCara){
        player_One = typeCara;
    }


    public void restart_Game(){

        setPlayer_One(typeCara.BIRD);

        set_All_Cara_False();
        remplir_Grille_Classic();
        cara[0][0] = typeCara.BIRD;
        cara[0][7] = typeCara.ELEPHANT;
        cara[7][0] = typeCara.DRAGON;
        cara[7][7] = typeCara.TURTLE;
        sheep_player[0] = sheep.HOURGLASS;
        sheep_player[1] = sheep.SWAP;
        sheep_player[2] = sheep.PLUS;
        sheep_player[3] = sheep.ICE_CUBE;
        sheep_player[4] = sheep.BLACK;
        sheep_player[5] = sheep.VIDE;

        sheep_player_2[0] = sheep.HOURGLASS;
        sheep_player_2[1] = sheep.SWAP;
        sheep_player_2[2] = sheep.PLUS;
        sheep_player_2[3] = sheep.ICE_CUBE;
        sheep_player_2[4] = sheep.BLACK;
        sheep_player_2[5] = sheep.VIDE;

        sheep_player_3[0] = sheep.HOURGLASS;
        sheep_player_3[1] = sheep.SWAP;
        sheep_player_3[2] = sheep.PLUS;
        sheep_player_3[3] = sheep.ICE_CUBE;
        sheep_player_3[4] = sheep.BLACK;
        sheep_player_3[5] = sheep.VIDE;

        set_all_which_sheep_grille_false();

        action_1_en_cours =false;
        action_2_en_cours =false;
        action_3_en_cours =false;

        game_won = typeCara.VIDE;

        set_compteur_restant(0);
        set_compteur(0);

        for(int i = 0 ; i < 8 ; i ++){
            for(int j = 0 ; j <8 ; j++){
                set_sheep_not_in_the_way(i,j);
                which_sheep_grille[i][j] = sheep.VIDE;
                which_sheep_in_the_way[i][j] = sheep.VIDE;
            }
        }

        for(int i = 0 ; i < 3 ; i++){
            for(int k = 0 ; k < 3 ; k++){
                for(int j = 0 ; j < 3 ; j++){
                    set_unfroze(i);
                }
            }
        }


    }

    public void get_Select_case(int x, int y){

        if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    select[i][j] = false;
                }
            }
            select[x][y] = true;
            if(cara[x][y] == getPlayer_One() || cara[x][y] == typeCara.DRAGON )
                set_is_select_cara();
            else
                no_is_select_cara();
        }

    }

    public void get_sheep_selected (int x){

        for (int j = 0; j < 6; j++) {
            is_select_sheep_tab[j] = false;
        }

        if( x >= 0 && x < 6) {
            switch (getPlayer_One()) {
                case BIRD:
                    if (sheep_player[x] != sheep.VIDE)

                    {

                        set_is_select_sheep();
                        is_select_sheep_tab[x] = true;
                    } else {
                        no_is_select_sheep();
                    }
                    break;
                case ELEPHANT:
                    if (sheep_player_2[x] != sheep.VIDE)

                    {

                        set_is_select_sheep();
                        is_select_sheep_tab[x] = true;
                    } else {
                        no_is_select_sheep();
                    }
                    break;
                case TURTLE:
                    if (sheep_player_3[x] != sheep.VIDE)

                    {

                        set_is_select_sheep();
                        is_select_sheep_tab[x] = true;
                    } else {
                        no_is_select_sheep();
                    }
                    break;
            }
        }
  }

  public boolean is_sheep_player_selected(){
        for(int i = 0 ; i <6 ; i ++){
            if( is_select_sheep_tab[i])
                return true;
        }
        return false;
  }
    public boolean is_sheep_player_empty(typeCara which_player){
        switch(which_player) {
            case BIRD:

                for (int i = 0; i < 6; i++) {
                    if (sheep_player[i] == sheep.VIDE)
                        return true;
                }
                break;
            case TURTLE:
                for (int i = 0; i < 6; i++) {
                    if (sheep_player_3[i] == sheep.VIDE)
                        return true;
                }
                break;
            case ELEPHANT:
                for (int i = 0; i < 6; i++) {
                    if (sheep_player_2[i] == sheep.VIDE)
                        return true;
                }
                break;
        }
        return false;
    }

    public boolean still_sheep_available(){
        switch(getPlayer_One()) {
            case BIRD:
                for (int i = 0; i < 6; i++) {
                    if (sheep_player[i] != sheep.VIDE)
                        return true;
                }
                break;
            case ELEPHANT:
                for (int i = 0; i < 6; i++) {
                    if (sheep_player_2[i] != sheep.VIDE)
                        return true;
                }
                break;
            case TURTLE:
                for (int i = 0; i < 6; i++) {
                    if (sheep_player_3[i] != sheep.VIDE)
                        return true;
                }
                break;
        }
        return false;
    }







    public int convert_event_X(float x){
        x = (x-posX)/sizeX;
        return (int) x;
    }

    public int convert_event_Y(float y){

        y = (y-posY)/sizeX;
        return (int) y;
    }

    public int convert_event_sheep (float x) {

        int new_x = 0;
        x = (x + sizeX / 3 - pos_sheepX) / sizeX;

        new_x = (int) x - 1;
        return new_x;
    }



    public void set_Solo_True(){
        is_Solo = true;
    }
    public void set_action_1(){action_1_en_cours = true;}
    public void set_action_2(){action_2_en_cours = true;}
    public void set_action_3(){action_3_en_cours = true;}
    public void set_false_action_1(){action_1_en_cours = false;}
    public void set_false_action_2(){action_2_en_cours = false;}
    public void set_false_action_3(){action_3_en_cours = false;}

    public void set_winner(typeCara typeCara){game_won = typeCara;}
    public typeCara get_winner(){return game_won;}

    public void set_can_move(int x, int y){can_move[x][y] = true;}
    public void set_cannot_move(int x, int y){can_move[x][y] = false;}

    public void set_can_move_sheep(int x, int y){can_move_sheep[x][y] = true;}
    public void set_cannot_move_sheep(int x, int y){can_move_sheep[x][y] = false;}

    public void set_is_sheep_in_the_way(int x, int y){sheep_in_the_way[x][y] = true;}
    public void set_sheep_not_in_the_way(int x, int y){sheep_in_the_way[x][y] = false;}
    public boolean is_sheep_in_the_way(int x, int y) {return sheep_in_the_way[x][y];}

    public void set_all_cannot_move(){
        for(int i = 0 ; i < 8 ; i ++ ){
            for(int j = 0 ; j < 8 ; j++){
                set_cannot_move(i,j);
                set_sheep_not_in_the_way(i,j);
            }
        }
    }

    public boolean is_Solo_True(){
        return is_Solo;
    }
    public boolean is_action_1(){return action_1_en_cours;}
    public boolean is_action_2(){return action_2_en_cours;}
    public boolean is_action_3(){return action_3_en_cours;}
    public boolean is_can_move(int x, int y){return can_move[x][y];}
    public boolean is_can_move_sheep(int x, int y){return can_move_sheep[x][y];}

    public boolean is_frozen(int x){return is_froze[x];}
    public void set_unfroze(int x){is_froze[x] = false;}

    public void set_compteur(int x){ compteur_mouv = x; }
    public boolean is_compteur_mouv(){
        if(compteur_mouv > 0 )
            return true;
        return false;
    }
    public int get_compteur_move(){ return compteur_mouv; }

    public void set_compteur_restant(int x){ compteur_mouv_restant = x; }
    public boolean is_compteur_mouv_restant(){
        if(compteur_mouv_restant > 0 )
            return true;
        return false;
    }
    public int get_compteur_move_restant(){ return compteur_mouv_restant; }



    public sheep get_which_sheep_grille(int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y <8)
            return which_sheep_grille[x][y];
        return sheep.VIDE;
    }

    public sheep get_which_sheep_in_the_way(int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y <8)
            return which_sheep_in_the_way[x][y];
        return sheep.VIDE;
    }

    public void empty_sheep_in_the_way(){
        for(int i = 0 ; i <8 ; i++){
            for(int j = 0 ; j <8 ; j ++){
                which_sheep_in_the_way[i][j] = sheep.VIDE;
            }
        }
    }

    public boolean is_not_which_sheep_grille_empty(int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y <8)
            if(which_sheep_in_the_way[x][y] != sheep.VIDE)
                return true;
        return false;
    }

    public void set_all_which_sheep_grille_false(){
        for(int i = 0 ; i < 8 ; i ++){
            for(int j = 0 ; j <8 ; j++){
                which_sheep_grille[i][j] = sheep.VIDE;
            }
        }
    }

    public boolean isIs_select_cara(){ return is_select_cara; }
    public void set_is_select_cara(){ is_select_cara = true; }
    public void no_is_select_cara(){ is_select_cara = false; }

    public boolean isIs_select_sheep(){ return is_select_sheep; }
    public void set_is_select_sheep(){ is_select_sheep = true; }
    public void no_is_select_sheep(){ is_select_sheep = false; }

    public sheep get_sheep_tab(int x){
        switch(getPlayer_One()) {
            case BIRD :
            if (x < 6)
                return sheep_player[x];
            else
                return sheep.VIDE;
            case ELEPHANT:
                if (x < 6)
                    return sheep_player_2[x];
                else
                    return sheep.VIDE;
            case TURTLE:
                if (x < 6)
                    return sheep_player_3[x];
                else
                    return sheep.VIDE;
        }
        return sheep.VIDE;

    }

    public boolean is_select_tab_sheep(int x){
        if(x >= 0 && x < 6)
            return is_select_sheep_tab[x];
        return false;
    }
    public void set_select_tab_sheep(int x){
        if(x >= 0 && x < 6)
            is_select_sheep_tab[x] = true;

    }
    public void set_select_tab_sheep_false(int x){
        if(x >= 0 && x < 6)
            is_select_sheep_tab[x] = false;

    }

    public boolean is_select_enCours(int x, int y){
        if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return select[x][y];
        }
        else
            return false;
    }


    public void set_all_select_false(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                select[i][j] = false;
            }
        }
    }


    public void defineSize(Canvas canvas) {

        posY = canvas.getHeight()/ 5;
        pos_X_middle = canvas.getWidth()/2;
        sizeX = (canvas.getWidth()) /8;
        sizeY = sizeX;
        pos_sheepX = canvas.getWidth()/50;
        pos_sheep_Y = canvas.getHeight()- canvas.getHeight()/15;

       /*posX = canvas.getWidth()/20;
        posY = canvas.getHeight() - (sizeY + (canvas.getHeight()/10));*/
    }

    public void setCanvas(Canvas _canvas, Paint _paint) {
        canvas = _canvas;
        paint = _paint;
    }

    public typeCara which_cara_played(typeCara typeCara){
        switch(typeCara){
            case TURTLE:
                return SoloPlay.typeCara.TURTLE;
            case BIRD:
                return SoloPlay.typeCara.BIRD;
            case ELEPHANT:
                return SoloPlay.typeCara.ELEPHANT;

        }
        return SoloPlay.typeCara.VIDE;
    }

    public boolean check_click(float x, float y){

            x = (int) x;
            y = (int) y;
            if (y < posY || y > (posY) + (8 * sizeX))
                return false;
            return true;




    }

    public boolean click_check_up_right(float x, float y){
        if(y < posY && x > pos_X_middle)
            return true;
        return false;
    }

    public boolean click_check_up_left(float x, float y){
        if(y < posY && x < pos_X_middle)
            return true;
        return false;
    }

    public boolean check_click_sheep(float x, float y){
        if(x > (sizeX - sizeX/2 + pos_sheepX) && x < (sizeX*(6) + sizeX/2 + pos_sheepX*(6)) && y < pos_sheep_Y && y > pos_sheep_Y - sizeX)
            return true;
        return false;

        //sizeX*(i+1) - sizeX/2 + (canvas.getWidth()/50)*i,
        //(canvas.getHeight() - sizeX) - canvas.getHeight()/15, sizeX * (i+1) + sizeX/2 + (canvas.getWidth()/50)*i   , canvas.getHeight()- canvas.getHeight()/15
    }

    public void draw(Canvas canvas, Rect frame, Paint paint, typeCases typeCases, int x, int y) {

        int alpha = 255;


        switch (typeCases) {


            case RED_CASE:
                if(this.get_Cara(x,y) == typeCara.BIRD)
                    alpha = 0;

                    frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                    paint.setAlpha(alpha);
                    canvas.drawBitmap(case_red, null, frame, paint);
                break;
            case BLACK_CASE:
                if(this.get_Cara(x,y) == typeCara.DRAGON)
                    alpha = 0;
                frame.set(posX + sizeX*x , posY + sizeX*y, posX + (sizeX*(x +1)), posY + (sizeX*(y +1)));
                paint.setAlpha(alpha);
                canvas.drawBitmap(case_black, null, frame, paint);
                break;

            case GREEN_CASE:
                if(this.get_Cara(x,y) == typeCara.ELEPHANT)
                    alpha = 0;

                frame.set(posX + sizeX*x , posY + sizeX*y, posX + (sizeX*(x +1)), posY + (sizeX*(y +1)));
                paint.setAlpha(alpha);
                canvas.drawBitmap(case_green, null, frame, paint);
                break;

            case BLUE_CASE:
                if(this.get_Cara(x,y) == typeCara.TURTLE)
                    alpha = 0;

                frame.set(posX + sizeX*x , posY + sizeX*y, posX + (sizeX*(x +1)), posY + (sizeX*(y +1)));
                paint.setAlpha(alpha);
                canvas.drawBitmap(case_blue, null, frame, paint);
                break;
            case WHITE_CASE:
                frame.set(posX + sizeX*x , posY + sizeX*y, posX + (sizeX*(x +1)), posY + (sizeX*(y +1)));
                paint.setAlpha(alpha);
                canvas.drawBitmap(case_white, null, frame, paint);
                break;


        }






    }
    public void draw(Canvas canvas, Rect frame, Paint paint, typeCara typeCara, int x, int y) {

        switch(typeCara) {
            case ELEPHANT:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(elephant, null, frame, paint);
                break;

            case BIRD:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(bird, null, frame, paint);
                break;

            case DRAGON:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(dragon, null, frame, paint);
                break;

            case TURTLE:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(turtle, null, frame, paint);
                break;

            /*case WHITE_SHEEP:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_white, null, frame, paint);
                break;

            case BLACK_SHEEP:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_black, null, frame, paint);
                break;
            case RED_SHEEP:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_red, null, frame, paint);
                break;

            case GREEN_SHEEP:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_green, null, frame, paint);
                break;
            case BLUE_SHEEP:
                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_blue, null, frame, paint);
                break;*/

        }
    }

    public void draw_sheep_in_grille(Canvas canvas, Rect frame, Paint paint, sheep sheep, int x, int y) {

        switch (sheep) {
            case HOURGLASS:

                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(hourglass_sheep, null, frame, paint);
                break;
            case ICE_CUBE:

                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(ice_cube_sheep, null, frame, paint);
                break;
            case PLUS:

                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(plus_sheep, null, frame, paint);
                break;
            case SWAP:

                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(swap_sheep, null, frame, paint);
                break;
            case BLACK:

                frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_black, null, frame, paint);
                break;
        }
    }

    public void draw(Canvas canvas, Rect frame, Paint paint){
        switch(getPlayer_One()) {
            case BIRD:
            for (int i = 0; i < 6; i++) {
                    switch (sheep_player[i]) {
                        case HOURGLASS:

                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(hourglass_sheep, null, frame, paint);
                            break;
                        case ICE_CUBE:

                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(ice_cube_sheep, null, frame, paint);
                            break;
                        case PLUS:

                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(plus_sheep, null, frame, paint);
                            break;
                        case SWAP:

                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(swap_sheep, null, frame, paint);
                            break;
                        case BLACK:

                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(sheep_black, null, frame, paint);
                            break;
                        case VIDE:
                            frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                            paint.setAlpha(255);
                            canvas.drawBitmap(case_white, null, frame, paint);
                            break;
                    }

            }
            break;
            case ELEPHANT:
                for (int i = 0; i < 6; i++) {
                        switch (sheep_player_2[i]) {
                            case HOURGLASS:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(hourglass_sheep, null, frame, paint);
                                break;
                            case ICE_CUBE:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(ice_cube_sheep, null, frame, paint);
                                break;
                            case PLUS:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(plus_sheep, null, frame, paint);
                                break;
                            case SWAP:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(swap_sheep, null, frame, paint);
                                break;
                            case BLACK:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(sheep_black, null, frame, paint);
                                break;
                            case VIDE:
                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(case_white, null, frame, paint);
                                break;
                        }

                }
                break;
            case TURTLE:
                for (int i = 0; i < 6; i++) {
                        switch (sheep_player_3[i]) {
                            case HOURGLASS:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(hourglass_sheep, null, frame, paint);
                                break;
                            case ICE_CUBE:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(ice_cube_sheep, null, frame, paint);
                                break;
                            case PLUS:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(plus_sheep, null, frame, paint);
                                break;
                            case SWAP:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(swap_sheep, null, frame, paint);
                                break;
                            case BLACK:

                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(sheep_black, null, frame, paint);
                                break;
                            case VIDE:
                                frame.set(sizeX * (i + 1) - sizeX / 2 + (canvas.getWidth() / 50) * i, (canvas.getHeight() - sizeX) - canvas.getHeight() / 15, sizeX * (i + 1) + sizeX / 2 + (canvas.getWidth() / 50) * i, canvas.getHeight() - canvas.getHeight() / 15);
                                paint.setAlpha(255);
                                canvas.drawBitmap(case_white, null, frame, paint);
                                break;
                        }

                }
                break;
        }



    }
    public void animation_select_Cara(Canvas canvas, Rect frame, Paint paint, int x, int y ){


        if(compte >= 50)
            direction = -1;
        if(compte <= 0)
            direction = 1;

        if(compte >= 0 && compte <= 30) {
            frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
            canvas.drawBitmap(case_white, null, frame, paint);

        }
        compte = compte + direction;




    }
    public void animation_select_Sheep(Canvas canvas, Rect frame, Paint paint, int x ){


        if(compte >= 50)
            direction = -1;
        if(compte <= 0)
            direction = 1;

        if(compte >= 0 && compte <= 30) {
            frame.set(sizeX*(x+1) - sizeX/2 + pos_sheepX*(x), pos_sheep_Y , sizeX*(x+1) + sizeX/2 + pos_sheepX*(x), pos_sheep_Y - sizeX );
            canvas.drawBitmap(case_white, null, frame, paint);

        }
        compte = compte + direction;




    }
    public void show_can_move(Canvas canvas, Rect frame, Paint paint, int x, int y){
        if(is_can_move(x,y)){
            frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
            canvas.drawBitmap(case_sheep_in_the_way, null, frame, paint);
        }
    }
    public void show_can_move_one(Canvas canvas, Rect frame, Paint paint, int x, int y){
        if(is_can_move(x,y)){
            frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
            canvas.drawBitmap(case_white, null, frame, paint);
        }
    }

    public void show_can_move_sheep(Canvas canvas, Rect frame, Paint paint, int x, int y){
        if(is_can_move_sheep(x,y)){
            frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
            canvas.drawBitmap(case_white, null, frame, paint);
        }
    }

    public void show_sheep_in_the_way(Canvas canvas, Rect frame, Paint paint, int x, int y){

            frame.set(posX + sizeX * x, posY + sizeX * y, posX + (sizeX * (x + 1)), posY + (sizeX * (y + 1)));
            canvas.drawBitmap(case_black, null, frame, paint);

    }

    public void fin_de_tour(){
        switch(getPlayer_One()){
            case TURTLE:
                set_player_One(SoloPlay.typeCara.BIRD);
                break;
            case BIRD:
                set_player_One(SoloPlay.typeCara.ELEPHANT);
                break;
            case ELEPHANT:
               set_player_One(SoloPlay.typeCara.TURTLE);
                break;
        }
    }

    public void check_if_frozen(typeCara typeCara){
        switch (typeCara){
            case BIRD:
                if(is_frozen(0)) {
                    if(!is_frozen(1)) {
                        set_player_One(SoloPlay.typeCara.ELEPHANT);

                    }
                    else {
                        set_player_One(SoloPlay.typeCara.TURTLE);
                        set_unfroze(1);
                    }
                    set_unfroze(0);

                }
                break;
            case ELEPHANT:
                if(is_frozen(1)) {
                    if(!is_frozen(2)) {
                        set_player_One(SoloPlay.typeCara.TURTLE);

                    }
                    else {
                        set_player_One(SoloPlay.typeCara.BIRD);
                        set_unfroze(2);
                    }
                    set_unfroze(1);
                }
                break;
            case TURTLE:
                if(is_frozen(2)) {
                    if(!is_frozen(0)) {
                        set_player_One(SoloPlay.typeCara.BIRD);

                    }
                    else {
                        set_player_One(SoloPlay.typeCara.ELEPHANT);
                        set_unfroze(0);
                    }
                    set_unfroze(2);
                }
                break;
        }
    }

    public void apply_effect_sheeps(){
        for(int i = 0 ; i <8 ; i ++){
            for(int j = 0 ; j <8 ; j++){
                if(which_sheep_in_the_way[i][j] != sheep.VIDE){
                    switch(which_sheep_in_the_way[i][j]){
                        case PLUS:
                            set_false_action_1();
                            set_false_action_2();
                            set_false_action_3();
                            set_compteur_restant(0);
                            set_compteur(0);
                            set_all_select_false();
                            set_all_cannot_move();
                            break;
                        case HOURGLASS:
                            switch(getPlayer_One()){
                                case TURTLE:
                                    for(int m = 0 ; m <8 ; m ++){
                                        for(int n = 0 ; n <8 ; n++){
                                            if(cara[m][n] == typeCara.TURTLE)
                                                cara[m][n] = typeCara.VIDE;
                                        }
                                    }
                                    cara[7][7] = typeCara.TURTLE;
                                    break;
                                case BIRD:
                                    for(int m = 0 ; m <8 ; m ++){
                                        for(int n = 0 ; n <8 ; n++){
                                            if(cara[m][n] == typeCara.BIRD)
                                                cara[m][n] = typeCara.VIDE;
                                        }
                                    }
                                    cara[0][0] = typeCara.BIRD;
                                    break;
                                case ELEPHANT:
                                    for(int m = 0 ; m <8 ; m ++){
                                        for(int n = 0 ; n <8 ; n++){
                                            if(cara[m][n] == typeCara.ELEPHANT)
                                                cara[m][n] = typeCara.VIDE;
                                        }
                                    }
                                    cara[0][7] = typeCara.ELEPHANT;
                                    break;
                                case DRAGON:
                                    for(int m = 0 ; m <8 ; m ++){
                                        for(int n = 0 ; n <8 ; n++){
                                            if(cara[m][n] == typeCara.DRAGON)
                                                cara[m][n] = typeCara.VIDE;
                                        }
                                    }
                                    cara[7][0] = typeCara.DRAGON;
                                    break;

                            }
                            break;
                        case SWAP:
                            int indexX[] = new int [2];
                            int indexY[] = new int [2];
                            switch(getPlayer_One()){
                                case BIRD:
                                    for(int m = 0 ; m <8 ; m ++) {
                                        for (int n = 0; n < 8; n++) {
                                            if (cara[m][n] == typeCara.BIRD) {
                                                indexX[0] = m;
                                                indexY[0] = n;
                                            }
                                            if (cara[m][n] == typeCara.DRAGON) {
                                                indexX[1] = m;
                                                indexY[1] = n;
                                            }
                                        }
                                    }
                                    cara[indexX[0]][indexY[0]] = typeCara.DRAGON;
                                    cara[indexX[1]][indexY[1]] = typeCara.BIRD;
                                    break;
                                case ELEPHANT:
                                    for(int m = 0 ; m <8 ; m ++) {
                                        for (int n = 0; n < 8; n++) {
                                            if (cara[m][n] == typeCara.ELEPHANT) {
                                                indexX[0] = m;
                                                indexY[0] = n;
                                            }
                                            if (cara[m][n] == typeCara.DRAGON) {
                                                indexX[1] = m;
                                                indexY[1] = n;
                                            }
                                        }
                                    }
                                    cara[indexX[0]][indexY[0]] = typeCara.DRAGON;
                                    cara[indexX[1]][indexY[1]] = typeCara.ELEPHANT;
                                    break;
                                case TURTLE:
                                    for(int m = 0 ; m <8 ; m ++) {
                                        for (int n = 0; n < 8; n++) {
                                            if (cara[m][n] == typeCara.TURTLE) {
                                                indexX[0] = m;
                                                indexY[0] = n;
                                            }
                                            if (cara[m][n] == typeCara.DRAGON) {
                                                indexX[1] = m;
                                                indexY[1] = n;
                                            }
                                        }
                                    }
                                    cara[indexX[0]][indexY[0]] = typeCara.DRAGON;
                                    cara[indexX[1]][indexY[1]] = typeCara.TURTLE;
                                    break;

                            }
                            break;
                        case ICE_CUBE:
                            switch(getPlayer_One()){
                                case BIRD:
                                    is_froze[0] = true;
                                    break;
                                case ELEPHANT:
                                    is_froze[1] = true;
                                    break;
                                case TURTLE:
                                    is_froze[2] = true;
                                    break;

                            }
                            break;
                        case BLACK:
                            cases[i][j] = typeCases.BLACK_CASE;
                            break;

                    }
                    which_sheep_in_the_way[i][j] = sheep.VIDE;
                }
            }
        }
    }



    public boolean is_way_possible(int x, int y, int indexX, int indexY, int compteur,int distance, typeCases typeCases){
        int dX_index = Math.abs(x - indexX);
        int dY_index = Math.abs(y - indexY);


        switch(compteur) {
            case 1 :
            switch (distance){
                case 1 :
                        if (dX_index <= 1 && dY_index <= 1) {

                                return true;
                        }
                break;
                case 2 :
                    if (dX_index <= 1 && dY_index <= 1 && cases[x][y] == cases[indexX][indexY]) {


                        return true;
                    }
                    break;
                case 3 :
                    if (dX_index <= 1 && dY_index <= 1 && cases[x][y] == cases[indexX][indexY]) {



                        return true;
                    }
                    break;


            }
                break;
            case 2 :
                switch (distance){


                    case 1 :
                        return is_way_possible(x, y, indexX,indexY,1,1,typeCases);
                    case 2 :

                        for(int i = 0 ; i < 8 ; i++) {
                            for(int j = 0 ; j <8 ; j++) {
                                int distance_index_i = Math.abs(i - indexX);
                                int distance_index_j = Math.abs(j - indexY);
                                int distance_x_i = Math.abs(i - x);
                                int distance_y_j = Math.abs(j - y);
                                if(distance_index_i <= 1 && distance_index_j <= 1  && cases[i][j] == typeCases){
                                    if (is_way_possible(x, y, i, j, 1, 2, typeCases)) {

                                        return is_way_possible(x, y, i, j, 1, 2, typeCases);
                                        }
                                    }
                                }
                            }








                    case 3 :
                        for(int i = 0 ; i < 8 ; i++) {
                            for(int j = 0 ; j <8 ; j++) {
                                int distance_index_i = Math.abs(i - indexX);
                                int distance_index_j = Math.abs(j - indexY);
                                int distance_x_i = Math.abs(i - x);
                                int distance_y_j = Math.abs(j - y);
                                if(distance_index_i <= 1 && distance_index_j <= 1  && cases[i][j] == typeCases){
                                    if (is_way_possible(x, y, i, j, 1, 3, typeCases)) {
                                        return is_way_possible(x, y, i, j, 1, 3, typeCases);
                                    }
                                }
                            }
                        }






                }
                break;
            case 3 :
                switch (distance){
                    case 1 :
                        return is_way_possible(x, y, indexX,indexY,2,1,typeCases);
                    case 2 :

                        for(int i = 0 ; i < 8 ; i++) {
                            for(int j = 0 ; j <8 ; j++) {
                                int distance_index_i = Math.abs(i - indexX);
                                int distance_index_j = Math.abs(j - indexY);
                                int distance_x_i = Math.abs(i - x);
                                int distance_y_j = Math.abs(j - y);

                                if(distance_index_i <= 1 && distance_index_j <= 1  && cases[i][j] == typeCases){
                                    if (is_way_possible(x, y, i, j, 2, 2, typeCases)) {
                                        return is_way_possible(x, y, i, j, 2, 2, typeCases);
                                    }
                                }
                            }
                        }






                    case 3 :
                        for(int i = 0 ; i < 8 ; i++) {
                            for(int j = 0 ; j <8 ; j++) {
                                int distance_index_i = Math.abs(i - indexX);
                                int distance_index_j = Math.abs(j - indexY);
                                int distance_x_i = Math.abs(i - x);
                                int distance_y_j = Math.abs(j - y);

                                if(distance_index_i <= 1 && distance_index_j <= 1  && cases[i][j] == typeCases){
                                    if (is_way_possible(x, y, i, j, 2, 3, typeCases)) {

                                        return is_way_possible(x, y, i, j, 2, 3, typeCases);
                                    }
                                }
                            }
                        }





                }

                break;

        }
        return false;

    }

    public boolean is_move_one (int x, int y){
        int indexX = 0 , indexY = 0;
        int mX = 0 , mY = 0;

        for(int i = 0 ; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                if (is_select_enCours(i, j)) {
                    indexX = i;
                    indexY = j;
                }

            }
        }
            mX = Math.abs(x - indexX);
            mY = Math.abs(y - indexY);

            if(cara[indexX][indexY] != typeCara.DRAGON) {
                if (mX <= 1 && mY <= 1)
                    return true;
                else
                    return false;
            }
            else if(mX == 1 && mY == 1 || mX <= 2 && mY <= 0 || mX <= 0 && mY <=2)
                return true;
            else
                return false;
    }

    public void  lets_move(int x, int y) {
        int indexX = 0, indexY = 0, mX = 0, mY = 0, compteur_sheep_free = 7;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (is_select_enCours(i, j)) {
                    indexX = i;
                    indexY = j;
                }

            }
        }


        mX = Math.abs(indexX - x);
        mY = Math.abs(indexY - y);

        if (is_select_enCours(x,y)) {
            set_all_select_false();
            set_false_action_1();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    can_move[i][j] = false;
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    for (int j = 0; j < 3; j++) {
                        way_taken[i][k][j] = 0;
                        is_there_a_sheep[i][j] = false;
                    }
                }
            }
        }
        else {


            typeCara typeCara = cara[indexX][indexY];
            if (mX <= 1 && mY <= 1 || cara[x][y] != SoloPlay.typeCara.DRAGON) {
                switch (typeCara) {
                    case BIRD:
                        if (can_move[x][y] && cara[x][y] != SoloPlay.typeCara.DRAGON && cara[x][y] != SoloPlay.typeCara.ELEPHANT) {
                            if (cara[x][y] == SoloPlay.typeCara.TURTLE)
                                set_winner(SoloPlay.typeCara.BIRD);
                            if (cara[x][y] == SoloPlay.typeCara.ELEPHANT)
                                set_winner(SoloPlay.typeCara.ELEPHANT);
                            cara[indexX][indexY] = SoloPlay.typeCara.VIDE;
                            cara[x][y] = SoloPlay.typeCara.BIRD;
                            if(cases[x][y] == cases[indexX][indexY])
                                set_compteur_restant(get_compteur_move() - Math.max(mX, mY));
                            else
                                set_compteur_restant(0);
                            if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                if (is_sheep_player_empty(getPlayer_One())) {
                                    for (int i = 0; i < 6; i++) {
                                        if (sheep_player[i] == sheep.VIDE ) {

                                            compteur_sheep_free = i;

                                        }

                                    }
                                        sheep_player[compteur_sheep_free] = which_sheep_grille[x][y];
                                }
                                    which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                    which_sheep_grille[x][y] = sheep.VIDE;



                            }

                        }
                        else
                            set_false_action_1();

                        break;
                    case TURTLE:
                        if (can_move[x][y] && cara[x][y] != SoloPlay.typeCara.DRAGON && cara[x][y] != SoloPlay.typeCara.BIRD) {
                            if (cara[x][y] == SoloPlay.typeCara.ELEPHANT)
                                set_winner(SoloPlay.typeCara.TURTLE);
                            if (cara[x][y] == SoloPlay.typeCara.BIRD)
                                set_winner(SoloPlay.typeCara.BIRD);
                            cara[indexX][indexY] = SoloPlay.typeCara.VIDE;
                            cara[x][y] = SoloPlay.typeCara.TURTLE;
                            if(cases[x][y] == cases[indexX][indexY])
                                set_compteur_restant(get_compteur_move() - Math.max(mX, mY));
                            else
                                set_compteur_restant(0);
                            if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                if (is_sheep_player_empty(getPlayer_One())) {
                                    for (int i = 0; i < 6; i++) {
                                        if (sheep_player_3[i] == sheep.VIDE ) {

                                            compteur_sheep_free = i;

                                        }

                                    }
                                    sheep_player_3[compteur_sheep_free] = which_sheep_grille[x][y];
                                }
                                which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                which_sheep_grille[x][y] = sheep.VIDE;



                            }
                        }
                        else
                            set_false_action_1();

                        break;
                    case ELEPHANT:
                        if (can_move[x][y] && cara[x][y] != SoloPlay.typeCara.DRAGON && cara[x][y] != SoloPlay.typeCara.TURTLE) {
                            if (cara[x][y] == SoloPlay.typeCara.BIRD)
                                set_winner(SoloPlay.typeCara.ELEPHANT);
                            if (cara[x][y] == SoloPlay.typeCara.TURTLE)
                                set_winner(SoloPlay.typeCara.TURTLE);
                            cara[indexX][indexY] = SoloPlay.typeCara.VIDE;
                            cara[x][y] = SoloPlay.typeCara.ELEPHANT;
                            if(cases[x][y] == cases[indexX][indexY])
                                set_compteur_restant(get_compteur_move() - Math.max(mX, mY));
                            else
                                set_compteur_restant(0);
                            if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                if (is_sheep_player_empty(getPlayer_One())) {
                                    for (int i = 0; i < 6; i++) {
                                        if (sheep_player_2[i] == sheep.VIDE ) {

                                            compteur_sheep_free = i;

                                        }

                                    }
                                    sheep_player_2[compteur_sheep_free] = which_sheep_grille[x][y];
                                }
                                which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                which_sheep_grille[x][y] = sheep.VIDE;



                            }
                        }
                        else
                            set_false_action_1();

                        break;
                    case DRAGON:
                        if (can_move[x][y] && cara[x][y] != getPlayer_One()) {
                            cara[indexX][indexY] = SoloPlay.typeCara.VIDE;
                            cara[x][y] = SoloPlay.typeCara.DRAGON;
                            if (which_sheep_grille[x][y] != sheep.VIDE && is_sheep_player_empty(getPlayer_One())) {
                                switch (getPlayer_One()) {
                                    case BIRD:
                                        if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                            if (is_sheep_player_empty(getPlayer_One())) {
                                                for (int i = 0; i < 6; i++) {
                                                    if (sheep_player[i] == sheep.VIDE ) {

                                                        compteur_sheep_free = i;

                                                    }

                                                }
                                                sheep_player[compteur_sheep_free] = which_sheep_grille[x][y];
                                            }
                                            which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                            which_sheep_grille[x][y] = sheep.VIDE;



                                        }
                                        break;
                                    case TURTLE:
                                        if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                            if (is_sheep_player_empty(getPlayer_One())) {
                                                for (int i = 0; i < 6; i++) {
                                                    if (sheep_player_3[i] == sheep.VIDE ) {

                                                        compteur_sheep_free = i;

                                                    }

                                                }
                                                sheep_player_3[compteur_sheep_free] = which_sheep_grille[x][y];
                                            }
                                            which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                            which_sheep_grille[x][y] = sheep.VIDE;



                                        }
                                        break;
                                    case ELEPHANT:
                                        if (which_sheep_grille[x][y] != sheep.VIDE ) {
                                            if (is_sheep_player_empty(getPlayer_One())) {
                                                for (int i = 0; i < 6; i++) {
                                                    if (sheep_player_2[i] == sheep.VIDE ) {

                                                        compteur_sheep_free = i;

                                                    }

                                                }
                                                sheep_player_2[compteur_sheep_free] = which_sheep_grille[x][y];
                                            }
                                            which_sheep_in_the_way[x][y] = which_sheep_grille[x][y];
                                            which_sheep_grille[x][y] = sheep.VIDE;



                                        }
                                        break;
                                }


                            }
                            switch(getPlayer_One()){
                                case BIRD:

                                    if (cara[x][y] == SoloPlay.typeCara.TURTLE)
                                        set_winner(SoloPlay.typeCara.BIRD);

                                    if (cara[x][y] == SoloPlay.typeCara.ELEPHANT)
                                        set_winner(SoloPlay.typeCara.ELEPHANT);
                                    break;
                                case TURTLE:
                                    if (cara[x][y] == SoloPlay.typeCara.ELEPHANT)
                                        set_winner(SoloPlay.typeCara.TURTLE);

                                    if (cara[x][y] == SoloPlay.typeCara.BIRD)
                                        set_winner(SoloPlay.typeCara.BIRD);
                                    break;
                                case ELEPHANT:
                                    if (cara[x][y] == SoloPlay.typeCara.BIRD)
                                        set_winner(SoloPlay.typeCara.ELEPHANT);

                                    if (cara[x][y] == SoloPlay.typeCara.TURTLE)
                                        set_winner(SoloPlay.typeCara.TURTLE);
                                    break;
                            }
                        }
                        break;
                }


                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        can_move[i][j] = false;
                    }
                }

                for (int i = 0; i < 3; i++) {
                    for (int k = 0; k < 3; k++) {
                        for (int j = 0; j < 3; j++) {
                            way_taken[i][k][j] = 0;
                            is_there_a_sheep[i][j] = false;
                        }
                    }
                }

                set_all_select_false();

                if (cases[x][y] == cases[indexX][indexY] && is_compteur_mouv_restant()) {
                    select[x][y] = true;
                    move();
                } else
                    set_compteur_restant(0);
            }

        }
    }



    public void lets_move_sheep(int x, int y){
        int index = 0, indexX_select = 0 , indexY_select = 0, mX = 0, mY = 0;

        for(int i = 0 ; i < 6 ; i ++){
            if(is_select_sheep_tab[i])
                index = i;
        }


        switch(getPlayer_One()) {
            case BIRD:
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (can_move_sheep[x][y]) {

                    cara[x][y] = typeCara.WHITE_SHEEP;
                    which_sheep_grille[x][y] = sheep.HOURGLASS;
                    switch (sheep_player[index]) {
                        case PLUS:
                            which_sheep_grille[x][y] = sheep.PLUS;
                            break;
                        case HOURGLASS:
                            which_sheep_grille[x][y] = sheep.HOURGLASS;
                            break;
                        case ICE_CUBE:
                            which_sheep_grille[x][y] = sheep.ICE_CUBE;
                            break;
                        case SWAP:
                            which_sheep_grille[x][y] = sheep.SWAP;
                            break;
                        case BLACK:
                            which_sheep_grille[x][y] = sheep.BLACK;
                            break;
                    }
                    sheep_player[index] = sheep.VIDE;
                }


            }
            break;
            case ELEPHANT:
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (can_move_sheep[x][y]) {

                        cara[x][y] = typeCara.WHITE_SHEEP;
                        switch (sheep_player_2[index]) {
                            case PLUS:
                                which_sheep_grille[x][y] = sheep.PLUS;
                                break;
                            case HOURGLASS:
                                which_sheep_grille[x][y] = sheep.HOURGLASS;
                                break;
                            case ICE_CUBE:
                                which_sheep_grille[x][y] = sheep.ICE_CUBE;
                                break;
                            case SWAP:
                                which_sheep_grille[x][y] = sheep.SWAP;
                                break;
                            case BLACK:
                                which_sheep_grille[x][y] = sheep.BLACK;
                                break;
                        }
                        sheep_player_2[index] = sheep.VIDE;
                    }


                }
                break;
            case TURTLE:
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (can_move_sheep[x][y]) {

                        cara[x][y] = typeCara.WHITE_SHEEP;
                        switch (sheep_player_3[index]) {
                            case PLUS:
                                which_sheep_grille[x][y] = sheep.PLUS;
                                break;
                            case HOURGLASS:
                                which_sheep_grille[x][y] = sheep.HOURGLASS;
                                break;
                            case ICE_CUBE:
                                which_sheep_grille[x][y] = sheep.ICE_CUBE;
                                break;
                            case SWAP:
                                which_sheep_grille[x][y] = sheep.SWAP;
                                break;
                            case BLACK:
                                which_sheep_grille[x][y] = sheep.BLACK;
                                break;
                        }
                        sheep_player_3[index] = sheep.VIDE;
                    }


                }
                break;
        }
        for (int i = 0 ; i < 8 ; i ++){
            for (int j = 0 ; j < 8 ; j++){
                set_cannot_move_sheep(i,j);
                set_sheep_not_in_the_way(i,j);
            }
        }



    }

    public void move_sheep(){

        int indexX[] = new int[4] ;
        int indexY[] = new int[4] ;
        int mX [] = new int[4];
        int mY [] = new int[4];
        int index = 0;

        for(int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++) {
                set_cannot_move_sheep(i,j);
                set_sheep_not_in_the_way(i,j);
                if(cara[i][j] == typeCara.DRAGON ) {
                    indexX[0] = i;
                    indexY[0] = j;
                }
                else if ( cara[i][j] == typeCara.ELEPHANT ){
                    indexX[1] = i;
                    indexY[1] = j;
                }
                else if ( cara[i][j] == typeCara.TURTLE ){
                    indexX[2] = i;
                    indexY[2] = j;
                }
                else if ( cara[i][j] == typeCara.BIRD ){
                    indexX[3] = i;
                    indexY[3] = j;
                }
                }
            }
        for(int i = 0; i< 6 ; i++){
            if(is_select_sheep_tab[i])
                index = i;
        }
        for(int i = 0 ; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                mX[0] = Math.abs(i-indexX[0]);
                mY[0] = Math.abs(j-indexY[0]);
                mX[1] = Math.abs(i-indexX[1]);
                mY[1] = Math.abs(j-indexY[1]);
                mX[2] = Math.abs(i-indexX[2]);
                mY[2] = Math.abs(j-indexY[2]);
                mX[3] = Math.abs(i-indexX[3]);
                mY[3] = Math.abs(j-indexY[3]);

                if(cara[i][j] != typeCara.BLACK_SHEEP && cara[i][j] != typeCara.GREEN_SHEEP && cara[i][j] != typeCara.WHITE_SHEEP
                && cara[i][j] != typeCara.BLUE_SHEEP &&cara[i][j] != typeCara.RED_SHEEP){

                for(int k = 0 ; k < indexX.length ; k++) {
                    switch (k) {
                        case 0:
                            if (mX[k] <= 2 && mY[k] <= 2) {
                                if (mX[k] != 0 || mY[k] != 0) {
                                    can_move_sheep[i][j] = true;
                                }
                            }
                            break;
                        case 1:
                            if (getPlayer_One() != typeCara.ELEPHANT) {
                                if (mX[k] <= 2 && mY[k] <= 2) {
                                    if (mX[k] != 0 || mY[k] != 0) {
                                        can_move_sheep[i][j] = true;
                                    }
                                }
                            }
                            break;
                        case 2:
                            if (getPlayer_One() != typeCara.TURTLE) {
                                if (mX[k] <= 2 && mY[k] <= 2) {
                                    if (mX[k] != 0 || mY[k] != 0) {
                                        can_move_sheep[i][j] = true;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if (getPlayer_One() != typeCara.BIRD) {
                                if (mX[k] <= 2 && mY[k] <= 2) {
                                    if (mX[k] != 0 || mY[k] != 0) {
                                        can_move_sheep[i][j] = true;
                                    }
                                }
                            }
                            break;
                    }
                }
                }
            }
        }


    }

    public void move(){
       int indexX = 0 , indexY = 0 , mX = 0 , mY = 0;
       for(int i = 0 ; i < 8 ; i++){
           for (int j = 0 ; j < 8 ; j++){
               set_cannot_move(i,j);
               set_sheep_not_in_the_way(i,j);
               if(is_select_enCours(i,j)){
                   indexX = i;
                   indexY = j;
               }

           }
       }



        switch(cara[indexX][indexY]) {
            case BIRD:
                switch(cases[indexX][indexY]) {
                    case GREEN_CASE:
                        if(compteur_mouv_restant ==0) {
                            set_compteur(1);
                        }
                        else
                            set_compteur(get_compteur_move_restant());
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            mX = Math.abs(i - indexX);
                            mY = Math.abs(j - indexY);
                            if(mX <= 1 && mY <= 1) {
                                can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.GREEN_CASE);
                                //if(can_move[i][j])
                                    //sheep_in_the_way[i][j] = shortest_and_easiest_way(indexX,indexY, i,j ,get_compteur_move(), Math.max(mX,mY), typeCases.GREEN_CASE);

                            }
                        }
                    }
                    break;
                    case BLUE_CASE:
                        if(compteur_mouv_restant ==0) {
                            set_compteur(2);
                        }
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 2 && mY <= 2) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.BLUE_CASE);
                                    //if(can_move[i][j])
                                    //sheep_in_the_way[i][j] = shortest_and_easiest_way(indexX,indexY, i,j ,get_compteur_move(), Math.max(mX,mY), typeCases.BLUE_CASE);
                                }

                            }
                        }
                        break;
                    case RED_CASE:
                        if(compteur_mouv_restant ==0) {
                            set_compteur(3);
                        }
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i-indexX);
                                mY = Math.abs(j-indexY);
                                if(mX <= 3 && mY <= 3) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.RED_CASE);
                                    //if(can_move[i][j])
                                        //sheep_in_the_way[i][j] = shortest_and_easiest_way(indexX,indexY, i,j ,get_compteur_move(), Math.max(mX,mY), typeCases.RED_CASE);
                                }

                            }
                        }
                        break;
                    case BLACK_CASE:
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if (mX <= 1 && mY <= 1)
                                    can_move[i][j] = true;
                            }
                        }
                        break;

                }
                break;

            case TURTLE:
                switch(cases[indexX][indexY]) {
                    case RED_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(1);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 1 && mY <= 1) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.RED_CASE);
                                }
                            }
                        }
                        break;
                    case GREEN_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(2);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 2 && mY <= 2) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.GREEN_CASE);
                                }
                            }
                        }
                        break;
                    case BLUE_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(3);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 3 && mY <= 3) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.BLUE_CASE);
                                }
                            }
                        }
                        break;
                    case BLACK_CASE:
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if (mX <= 1 && mY <= 1)
                                    can_move[i][j] = true;
                            }
                        }
                        break;


                }
                break;
            case ELEPHANT:
                switch(cases[indexX][indexY]) {
                    case BLUE_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(1);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 1 && mY <= 1) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.BLUE_CASE);
                                }
                            }
                        }
                        break;
                    case RED_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(2);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 2 && mY <= 2) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.RED_CASE);
                                }
                            }
                        }
                        break;
                    case GREEN_CASE:
                        if(compteur_mouv_restant ==0)
                            set_compteur(3);
                        else
                            set_compteur(get_compteur_move_restant());
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if(mX <= 3 && mY <= 3) {
                                    can_move[i][j] = is_way_possible(indexX, indexY, i, j, get_compteur_move(), Math.max(mX,mY), typeCases.GREEN_CASE);
                                }
                            }
                        }
                        break;
                    case BLACK_CASE:
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                mX = Math.abs(i - indexX);
                                mY = Math.abs(j - indexY);
                                if (mX <= 1 && mY <= 1)
                                    can_move[i][j] = true;
                            }
                        }
                        break;


                }
                break;
            case DRAGON:
                set_compteur(2);
                for(int i = 0 ; i < 8 ; i++){
                    for(int j = 0 ; j < 8 ; j++){
                        mX = Math.abs(i - indexX);
                        mY = Math.abs( j - indexY);
                        if(mX <= 2 && mY <= 0 || mX<=0 && mY <=2 || mX == 1 && mY == 1)
                            can_move[i][j] = true;
                    }
                }
                break;

        }

        can_move[indexX][indexY] = false;
       sheep_in_the_way[indexX][indexY] = false;

    }



    public void set_compte(int x){
        compte = x;
    }

    public void remplir_Grille_Classic(){
        cases[0][0] = typeCases.RED_CASE;
        cases[0][1] = typeCases.GREEN_CASE;
        cases[0][2] = typeCases.RED_CASE;
        cases[0][3] = typeCases.BLUE_CASE;
        cases[0][4] = typeCases.GREEN_CASE;
        cases[0][5] = typeCases.GREEN_CASE;
        cases[0][6] = typeCases.RED_CASE;
        cases[0][7] = typeCases.GREEN_CASE;

        cases[1][0] = typeCases.BLUE_CASE;
        cases[1][1] = typeCases.RED_CASE;
        cases[1][2] = typeCases.GREEN_CASE;
        cases[1][3] = typeCases.BLUE_CASE;
        cases[1][4] = typeCases.GREEN_CASE;
        cases[1][5] = typeCases.BLUE_CASE;
        cases[1][6] = typeCases.GREEN_CASE;
        cases[1][7] = typeCases.BLUE_CASE;

        cases[2][0] = typeCases.RED_CASE;
        cases[2][1] = typeCases.GREEN_CASE;
        cases[2][2] = typeCases.GREEN_CASE;
        cases[2][3] = typeCases.RED_CASE;
        cases[2][4] = typeCases.GREEN_CASE;
        cases[2][5] = typeCases.BLUE_CASE;
        cases[2][6] = typeCases.BLUE_CASE;
        cases[2][7] = typeCases.GREEN_CASE;

        cases[3][0] = typeCases.RED_CASE;
        cases[3][1] = typeCases.RED_CASE;
        cases[3][2] = typeCases.RED_CASE;
        cases[3][3] = typeCases.BLUE_CASE;
        cases[3][4] = typeCases.BLACK_CASE;
        cases[3][5] = typeCases.RED_CASE;
        cases[3][6] = typeCases.RED_CASE;
        cases[3][7] = typeCases.RED_CASE;

        cases[4][0] = typeCases.BLUE_CASE;
        cases[4][1] = typeCases.BLUE_CASE;
        cases[4][2] = typeCases.GREEN_CASE;
        cases[4][3] = typeCases.BLUE_CASE;
        cases[4][4] = typeCases.GREEN_CASE;
        cases[4][5] = typeCases.BLUE_CASE;
        cases[4][6] = typeCases.BLUE_CASE;
        cases[4][7] = typeCases.BLUE_CASE;

        cases[5][0] = typeCases.RED_CASE;
        cases[5][1] = typeCases.BLUE_CASE;
        cases[5][2] = typeCases.GREEN_CASE;
        cases[5][3] = typeCases.GREEN_CASE;
        cases[5][4] = typeCases.GREEN_CASE;
        cases[5][5] = typeCases.RED_CASE;
        cases[5][6] = typeCases.RED_CASE;
        cases[5][7] = typeCases.BLUE_CASE;

        cases[6][0] = typeCases.RED_CASE;
        cases[6][1] = typeCases.GREEN_CASE;
        cases[6][2] = typeCases.RED_CASE;
        cases[6][3] = typeCases.RED_CASE;
        cases[6][4] = typeCases.GREEN_CASE;
        cases[6][5] = typeCases.RED_CASE;
        cases[6][6] = typeCases.BLUE_CASE;
        cases[6][7] = typeCases.GREEN_CASE;

        cases[7][0] = typeCases.GREEN_CASE;
        cases[7][1] = typeCases.BLUE_CASE;
        cases[7][2] = typeCases.BLUE_CASE;
        cases[7][3] = typeCases.RED_CASE;
        cases[7][4] = typeCases.GREEN_CASE;
        cases[7][5] = typeCases.BLUE_CASE;
        cases[7][6] = typeCases.RED_CASE;
        cases[7][7] = typeCases.BLUE_CASE;

    }
    private void printString(String str, Canvas canvas, int xPos, int yPos, int color, int size) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(size);
        canvas.drawText(str, xPos, yPos, paint);
    }


}
