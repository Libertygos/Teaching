package com.libertygos.flappyshape1_0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;


public class UbxGameView extends View {

    private static  MoveChangeShape move;

    public UbxGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        move = new MoveChangeShape(this.getResources());
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Rect frame = new Rect();
        int sizePolice = ((canvas.getWidth())-(canvas.getWidth()/10))/25;

        move.defineSize(canvas);
        move.setCanvas(canvas, paint);

        move.draw(canvas, frame, paint);
    }

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
                Log.d("TOUCH EVENT","Action was DOWN");
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
