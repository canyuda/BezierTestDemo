package com.yuda.test2.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yuda on 2016/12/9
 */

public class DrawBoard extends View {
    private float mX;
    private float mY;
    private Paint mPaint;
    private Path mPath;
    public DrawBoard(Context context) {
        super(context);
    }

    public DrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    public DrawBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX,mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float cx = event.getX();
                float cy = event.getY();
                float x1 = (cx+mX)/2;
                float y1 = (cy+mY)/2;
                mPath.quadTo(mX,mY,x1,y1);
                mX = cx;
                mY = cy;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}
