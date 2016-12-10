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

public class ThirdBezierView extends View {
    private float mStartPointX;
    private float mStartPonitY;
    private float mEndPointX;
    private float mEndPointY;

    private float mFlagPointX0;
    private float mFlagPointY0;
    private float mFlagPointX1;
    private float mFlagPointY1;

    private Path mPath;
    private Paint mPaint;
    private Paint mPaintFlag;

    private boolean isSencondPoint = false;

    public ThirdBezierView(Context context) {
        super(context);
    }

    public ThirdBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setStrokeWidth(1);
        mPaintFlag.setColor(Color.RED);
        mPaintFlag.setStyle(Paint.Style.FILL);
        mPaintFlag.setTextSize(20);
    }

    public ThirdBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mStartPointX = w/4;
        mStartPonitY = h/2 - 200;

        mEndPointX = w*3/4;
        mEndPointY = h/2 -200;

        mFlagPointX0 = w/2 - 100;
        mFlagPointY0 = h/2-300;
        mFlagPointX1 = w/2 + 100;
        mFlagPointY1 = h/2-300;

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPonitY);

        mPath.cubicTo(mFlagPointX0,mFlagPointY0,mFlagPointX1,mFlagPointY1,mEndPointX,mEndPointY);
        canvas.drawPath(mPath,mPaint);

        canvas.drawLine(mStartPointX,mStartPonitY,mFlagPointX0,mFlagPointY0,mPaintFlag);
        canvas.drawLine(mFlagPointX0,mFlagPointY0,mFlagPointX1,mFlagPointY1,mPaintFlag);
        canvas.drawLine(mEndPointX,mEndPointY,mFlagPointX1,mFlagPointY1,mPaintFlag);

        canvas.drawPoint(mStartPointX,mStartPonitY,mPaintFlag);
        canvas.drawText("起点",mStartPointX,mStartPonitY,mPaintFlag);
        canvas.drawPoint(mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawText("终点",mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawPoint(mFlagPointX0,mFlagPointY0,mPaintFlag);
        canvas.drawText("控制点1",mFlagPointX0,mFlagPointY0,mPaintFlag);
        canvas.drawPoint(mFlagPointX1,mFlagPointY1,mPaintFlag);
        canvas.drawText("控制点2",mFlagPointX1,mFlagPointY1,mPaintFlag);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_POINTER_DOWN:
                isSencondPoint = true;
            case MotionEvent.ACTION_MOVE:
                mFlagPointX0 = event.getX(0);
                mFlagPointY0 = event.getY(0);
                if(isSencondPoint){
                    mFlagPointX1 = event.getX(1);
                    mFlagPointY1 = event.getY(1);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSencondPoint = false;
        }
        return true;
    }
}
