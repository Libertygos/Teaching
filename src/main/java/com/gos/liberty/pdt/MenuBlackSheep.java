package com.gos.liberty.pdt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Jules on 03/06/2018.
 */

public class MenuBlackSheep {

    private Bitmap sheep_white = null;
    private Bitmap sheep_red = null;
    private Bitmap sheep_green = null;
    private Bitmap sheep_blue = null;
    private Bitmap sheep_black = null;
    private Rect rect;
    private Canvas canvas;
    private Paint paint;

    private boolean is_Menu_BS = false;
    private boolean which_Menu[]= new boolean[3];

    private int posX= 0 ,posY = 0 , sizeX = 0 , sizeY = 0;
    private int posY_2 = 0 , posY_3 = 0;

    private String info = "";


    public MenuBlackSheep(Resources resources){
        sheep_white = BitmapFactory.decodeResource(resources, R.drawable.sheep_white);
        sheep_black = BitmapFactory.decodeResource(resources, R.drawable.sheep_black);
        sheep_blue = BitmapFactory.decodeResource(resources, R.drawable.sheep_blue);
        sheep_red = BitmapFactory.decodeResource(resources, R.drawable.sheep_red);
        sheep_green = BitmapFactory.decodeResource(resources, R.drawable.sheep_green);
    }

    public boolean is_Menu_BS_F(){
        return is_Menu_BS;
    }
    public void set_is_Menu_BS_False(){
        is_Menu_BS = false;
    }
    public void set_Is_Menu_BS_True(){
        is_Menu_BS = true;
    }


    public void set_Which_Menu_False(){
       which_Menu[0] = false;
       which_Menu[1] = false;
       which_Menu[2] = false;
    }

    public boolean is_Which_Menu_0(){
        return which_Menu[0];
    }
    public boolean is_Which_Menu_1(){
        return which_Menu[1];
    }
    public boolean is_Which_Menu_2(){
        return which_Menu[2];
    }

    public void choose_which_Menu_0(){
        which_Menu[0] = true;
    }
    public void choose_which_Menu_1(){
        which_Menu[1] = true;
    }
    public void choose_which_Menu_2(){
        which_Menu[2] = true;
    }

    public boolean is_all_which_Menu_false(){

               if(!is_Which_Menu_0() && !is_Which_Menu_1() && !is_Which_Menu_2())
                   return true;

        return false;
    }


    public void defineSize(Canvas canvas) {
        int directionX = 1, directionY = 1;
        sizeX = ((canvas.getWidth())-(canvas.getWidth()/15))/3;
        sizeY = sizeX;
        posX = canvas.getWidth()/3;
        posY = 0 + canvas.getHeight()/10;
        posY_2 = sizeY*2;
        posY_3 = sizeY*3 + sizeY/2;

       /*posX = canvas.getWidth()/20;
        posY = canvas.getHeight() - (sizeY + (canvas.getHeight()/10));*/
    }

    public void setCanvas(Canvas _canvas, Paint _paint) {
        canvas = _canvas;
        paint = _paint;
    }

    public void get_Coord_BS_Menu(float x, float y){
        if(y > 0 && y < posY_2) {
            set_Which_Menu_False();
            choose_which_Menu_0();

        }
        else if(y > posY_2 && y < posY_3){
            set_Which_Menu_False();
            choose_which_Menu_1();
        }
        else{
            set_Which_Menu_False();
            choose_which_Menu_2();
        }


    }

    public void draw(Canvas canvas, Rect frame, Paint paint) {

        int sizePolice = ((canvas.getWidth())-(canvas.getWidth()/10))/10;

        if(is_all_which_Menu_false()) {
            printString("Choissisez un mouton", canvas, posX + sizeX/2, posY_3 + sizePolice*2 + sizeY, Color.BLACK, sizePolice/2);
        }
        else if (is_Which_Menu_0()) {
            printString("Vous avez choisi(e) le mouton \"joueur\"", canvas, posX + sizeX/2, posY_3 + sizePolice*2 + sizeY, Color.BLUE, sizePolice/2);

        }
        else if(is_Which_Menu_1()){
            printString("Désolé ce mouton n'est pas encore disponible", canvas, posX + sizeX/2, posY_3 + sizePolice*2 + sizeY, Color.GREEN, sizePolice/2);
        }
        else{
            printString("Désolé ce mouton n'est pas encore disponible", canvas, posX + sizeX/2, posY_3 + sizePolice*2 + sizeY, Color.RED, sizePolice/2);
        }

        frame.set(posX, posY, posX + sizeX, posY + sizeY);
        paint.setAlpha(255);
        canvas.drawBitmap(sheep_blue,null, frame, paint);
        printString("Jouer ", canvas, posX + sizeX/2, posY + sizePolice + sizeY, Color.BLUE, sizePolice);


        frame.set(posX, posY_2, posX + sizeX, posY_2 + sizeY);
        paint.setAlpha(50);
        canvas.drawBitmap(sheep_green,null, frame, paint);
        printString("Jouer En Ligne ", canvas, posX + sizeX/2, posY_2 + sizePolice + sizeY, Color.GREEN, sizePolice);


        frame.set(posX, posY_3, posX + sizeX, posY_3 + sizeY);
        paint.setAlpha(50);
        canvas.drawBitmap(sheep_red,null, frame, paint);
        printString("Ajouter des amis ", canvas, posX + sizeX/2, posY_3 + sizePolice + sizeY, Color.RED, sizePolice);


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
