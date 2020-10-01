package com.natalyakreneva.hometask04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomView extends View {
    private static final int RADIUS_C=150;
    private static final int RADIUS_S=450;
    private int centerY;
    private int centerX;
    private RectF oval;
    private Paint paintCircle;
    private Paint paintSectorUpperLeft;
    private Paint paintSectorUpperRigth;
    private Paint paintSectorBottomLeft;
    private Paint paintSectorBottomRigth;
    private Boolean isInsideInnerCircle;
    private Boolean isInsideSectors;
    public int selectedX;
    public int selectedY;
    protected OnMyTouchListener onMyTouchListener;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        oval =new RectF();
        paintCircle = new Paint ();
        paintSectorUpperLeft =new Paint();
        paintSectorUpperRigth = new Paint();
        paintSectorBottomLeft = new Paint();
        paintSectorBottomRigth = new Paint();

        paintCircle.setStyle(Paint.Style.FILL);
        paintSectorBottomRigth.setStyle(Paint.Style.FILL);
        paintSectorBottomLeft.setStyle(Paint.Style.FILL);
        paintSectorUpperRigth.setStyle(Paint.Style.FILL);
        paintSectorUpperLeft.setStyle(Paint.Style.FILL);

        setDefaultPaintColors();
     }


    @Override
    protected void onAttachedToWindow () {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerY=h/2;
        centerX=w/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        oval.set(centerX - RADIUS_S, centerY - RADIUS_S, centerX + RADIUS_S, centerY + RADIUS_S);
        canvas.drawArc(oval,0,90,true,paintSectorBottomRigth);
        canvas.drawArc(oval,90,90,true,paintSectorBottomLeft);
        canvas.drawArc(oval,180,90,true,paintSectorUpperLeft);
        canvas.drawArc(oval,270,90,true,paintSectorUpperRigth);

        canvas.drawCircle(centerX,centerY,RADIUS_C,paintCircle);
    }

    protected void setDefaultPaintColors() {
        paintCircle.setColor(Color.MAGENTA);
        paintSectorUpperLeft.setColor(Color.RED);
        paintSectorUpperRigth.setColor(Color.GREEN);
        paintSectorBottomLeft.setColor(Color.BLUE);
        paintSectorBottomRigth.setColor(Color.YELLOW);
    }

    protected int getRandomColor () {
        List<Integer> list = new ArrayList<Integer>();
        list.add(Color.BLUE);
        list.add(Color.WHITE);
        list.add(Color.BLACK);
        list.add(Color.GREEN);
        list.add (Color.YELLOW);
        list.add(Color.RED);
        list.add(Color.MAGENTA);

        int index = (int)(Math.random()*list.size());
        return list.get(index);
    }


    private Boolean InsideInnerCircle (int selectedX, int selectedY) {
        if ((Math.pow((selectedX-centerX), 2)+Math.pow((selectedY-centerY), 2))<=RADIUS_C*RADIUS_C) {
           return true;
        }
        else return false;
    }


    private Boolean InsideSectors (int selectedX, int selectedY) {
        if (((Math.pow((selectedX-centerX), 2)+Math.pow((selectedY-centerY), 2))<=RADIUS_S*RADIUS_S)
                &&
                ((Math.pow((selectedX-centerX), 2)+Math.pow((selectedY-centerY), 2))>=RADIUS_C*RADIUS_C)
            ) {
          return true;
        }
        else return false;
    }

    protected void changeColors () {
        isInsideInnerCircle =InsideInnerCircle(selectedX,selectedY);
        isInsideSectors = InsideSectors(selectedX, selectedY);
       if (isInsideSectors) {
            if ((selectedX <= centerX) && (selectedY <= centerY)) {
                paintSectorUpperLeft.setColor(getRandomColor());
            } else {
                if ((selectedX >= centerX) && (selectedY <= centerY)) {
                    paintSectorUpperRigth.setColor(getRandomColor());
                } else {
                    if ((selectedX <= centerX) && (selectedY >= centerY)) {
                        paintSectorBottomLeft.setColor(getRandomColor());
                    } else {
                        paintSectorBottomRigth.setColor(getRandomColor());
                    }

                }
            }
        }
        else {
       if (isInsideInnerCircle) {
               paintSectorUpperLeft.setColor(getRandomColor());
                paintSectorUpperRigth.setColor(getRandomColor());
                paintSectorBottomLeft.setColor(getRandomColor());
                paintSectorBottomRigth.setColor(getRandomColor());
            }
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        selectedX = (int) event.getX();
        selectedY = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onMyTouchListener != null) {
                onMyTouchListener.onCustomTouchEvent(selectedX, selectedY);
            }
            changeColors();
        }

       return super.onTouchEvent(event);
    }

    public void setOnMyTouchListener(OnMyTouchListener onMyTouchListener) {
        this.onMyTouchListener = onMyTouchListener;
    }

}
