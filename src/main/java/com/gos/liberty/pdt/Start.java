package com.gos.liberty.pdt;

/**
 * Created by Jules on 03/06/2018.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Start {

    private Bitmap sheep_white = null;
    private Bitmap sheep_black = null;
    private Bitmap sheep_red = null;
    private Bitmap sheep_green = null;
    private Bitmap sheep_blue = null;


    private boolean is_Start = true;

    private boolean plante = true;


    private Rect rect;
    private Canvas canvas;
    private Paint paint;

    private int posX = 0, posY = 0, sizeX = 0, sizeY = 0;
    int direction_x[] = {1, -1, -1, 1, 1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1};
    int direction_y[] = {1, 1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1};

    int moveX[] = {500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,};
    int moveY[] = {700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700, 700};
    int random_color[] = {0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 2, 1, 3, 1, 2, 3, 4, 3, 2, 4, 0, 1, 2, 3, 4, 2, 1};


    int compteur = 0;

    private boolean setting = false, setting_2 = false;


    public Start(Resources resources) {
        sheep_white = BitmapFactory.decodeResource(resources, R.drawable.sheep_white);
        sheep_black = BitmapFactory.decodeResource(resources, R.drawable.sheep_black);
        sheep_blue = BitmapFactory.decodeResource(resources, R.drawable.sheep_blue);
        sheep_red = BitmapFactory.decodeResource(resources, R.drawable.sheep_red);
        sheep_green = BitmapFactory.decodeResource(resources, R.drawable.sheep_green);


    }

    public void defineSize(Canvas canvas) {
        int directionX = 1, directionY = 1;
        sizeX = ((canvas.getWidth()) - (canvas.getWidth() / 15)) / 6;
        sizeY = sizeX;
        posX = (canvas.getWidth() / 2) - (sizeX / 2);
        posY = (canvas.getHeight() / 2) - (sizeX / 2);


    }

    public void setCanvas(Canvas _canvas, Paint _paint) {
        canvas = _canvas;
        paint = _paint;
    }

    public void draw(Canvas canvas, Rect frame, Paint paint, String string, int i) {


        switch (random_color[i]) {
            case 0:
                string = "black";
                break;
            case 1:
                string = "white";
                break;
            case 2:
                string = "red";
                break;
            case 3:
                string = "blue";
                break;
            case 4:
                string = "green";
                break;
        }

        switch (string) {
            case "black":
                frame.set(moveX[i], moveY[i], moveX[i] + sizeX, moveY[i] + sizeY);
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_black, null, frame, paint);
                break;
            case "red":
                frame.set(moveX[i], moveY[i], moveX[i] + sizeX, moveY[i] + sizeY);
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_red, null, frame, paint);
                break;
            case "blue":
                frame.set(moveX[i], moveY[i], moveX[i] + sizeX, moveY[i] + sizeY);
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_blue, null, frame, paint);
                break;
            case "green":
                frame.set(moveX[i], moveY[i], moveX[i] + sizeX, moveY[i] + sizeY);
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_green, null, frame, paint);
                break;
            case "white":
                frame.set(moveX[i], moveY[i], moveX[i] + sizeX, moveY[i] + sizeY);
                paint.setAlpha(255);
                canvas.drawBitmap(sheep_white, null, frame, paint);
                break;


        }
    }


    //       }

    public void mouvement_blabla(Canvas canvas, int i) {


        if (moveX[i] > canvas.getWidth() - sizeX || moveX[i] < 0)
            direction_x[i] = -direction_x[i];
        if (moveY[i] > canvas.getHeight() - sizeX || moveY[i] < 0)
            direction_y[i] = -direction_y[i];

        moveX[i] = moveX[i] + (direction_x[i] * (i % 7));
        moveY[i] = moveY[i] + (direction_y[i] * (i % 7));

    }

    public int get_timer() {
        compteur++;
        return compteur / 3000;
    }

    public void set_timer_zero() {
        compteur = 0;
    }


    public void false_is_Start() {
        is_Start = false;
    }

    public void true_is_Start() {
        is_Start = true;
    }

    public boolean is_start() {
        return is_Start;
    }


    public void red_sheep() {
        for (int i = 0; i < 30; i++) {
            random_color[i] = 2;

        }
    }

    public void blue_sheep() {
        for (int i = 0; i < 30; i++) {
            random_color[i] = 3;

        }
    }


    public void color_sheep(int color) {

        for (int i = 0; i < 30; i++) {
            random_color[i] = color;

        }
    }

    public void one_sheep(Canvas canvas, Rect frame, Paint paint, int color, int ecarx, int ecary) {


        ecarx = posX + ecarx;
        ecary = posY + ecary;
        random_color[0] = color;
        frame.set(ecarx, ecary, ecarx + sizeX, ecary + sizeY);
        paint.setAlpha(255);
        canvas.drawBitmap(sheep_red, null, frame, paint);
    }
    public int moove_sheep(Canvas canvas , int direction, int vitesse) {

        if (moveY[direction] + posX + sizeY >= canvas.getWidth())
            direction_y[direction] = -direction_y[direction] ;

        if (moveY[direction] + posX < 0)
            direction_y[direction] = -direction_y[direction];

        moveY[direction] = moveY[direction]+ direction_y[direction] * vitesse;
        return moveY[direction] ;
    }




public int moove_sheephigh(Canvas canvas ,int direction, int vitesse) {

        if (moveX[direction] + posY + sizeY > canvas.getHeight())
            direction_x[direction] = -direction_x[direction] ;

        if (moveX[direction] + posY < 0)
            direction_x[direction] = -direction_x[direction];

    moveX[direction] = moveX[direction]+ direction_x[direction] * vitesse;
        return moveX[direction] ;
}

}