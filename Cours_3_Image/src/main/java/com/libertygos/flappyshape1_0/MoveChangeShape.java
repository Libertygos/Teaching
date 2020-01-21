package com.libertygos.flappyshape1_0;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class MoveChangeShape {

    private Bitmap circle = null;
    private Bitmap triangle = null;
    private Bitmap star = null;
    private Bitmap square = null;


    /*private boolean is_Start = true;

    private boolean plante = true;*/


    private Rect rect;
    private Canvas canvas;
    private Paint paint;

    private int posX = 0, posY = 0, sizeX = 0, sizeY = 0;

    public MoveChangeShape(Resources resources) {

        circle = BitmapFactory.decodeResource(resources, R.drawable._circle);
        triangle = BitmapFactory.decodeResource(resources, R.drawable._triangle);
        star = BitmapFactory.decodeResource(resources, R.drawable._star);
        square = BitmapFactory.decodeResource(resources, R.drawable.square);

    }

    public void defineSize(Canvas canvas) {
        int directionX = 1, directionY = 1;
        sizeX = ((canvas.getWidth())) / 4;
        sizeY = sizeX;
        posX = 0;
        posY = (canvas.getHeight() - sizeX );


    }

    public void setCanvas(Canvas _canvas, Paint _paint) {
        canvas = _canvas;
        paint = _paint;
    }

    public void draw(Canvas canvas, Rect frame, Paint paint) {
        for(int i = 0 ; i < 4 ; i ++) {

            frame.set(posX + (sizeX * i), posY, posX + (sizeX * (i +1)), posY + sizeY);
            paint.setAlpha(255);

            switch(i) {
                case 0 :
                    canvas.drawBitmap(circle, null, frame, paint);
                    break;
                case 1 :
                    canvas.drawBitmap(triangle, null, frame, paint);
                    break;
                case 2 :
                    canvas.drawBitmap(square, null, frame, paint);
                    break;
                case 3 :
                    canvas.drawBitmap(star, null, frame, paint);
                    break;
            }
        }

    }
}
