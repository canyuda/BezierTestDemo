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

public class SecondBezierView extends View {
    private float mStartPointX;
    private float mStartPonitY;
    private float mEndPointX;
    private float mEndPointY;

    private float mFlagPointX;
    private float mFlagPointY;

    private Path mPath;
    private Paint mPaint;
    private Paint mPaintFlag;

    public SecondBezierView(Context context) {
        super(context);
    }

    public SecondBezierView(Context context, AttributeSet attrs) {
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

    public SecondBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mStartPointX = w/4;
        mStartPonitY = h/2 - 200;

        mEndPointX = w*3/4;
        mEndPointY = h/2 -200;

        mFlagPointX = w/2;
        mFlagPointY = h/2-300;

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPonitY);

        mPath.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);
        canvas.drawPath(mPath,mPaint);

        canvas.drawLine(mStartPointX,mStartPonitY,mFlagPointX,mFlagPointY,mPaintFlag);
        canvas.drawLine(mEndPointX,mEndPointY,mFlagPointX,mFlagPointY,mPaintFlag);

        canvas.drawPoint(mStartPointX,mStartPonitY,mPaintFlag);
        canvas.drawText("起点",mStartPointX,mStartPonitY,mPaintFlag);
        canvas.drawPoint(mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawText("终点",mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawPoint(mFlagPointX,mFlagPointY,mPaintFlag);
        canvas.drawText("控制点",mFlagPointX,mFlagPointY,mPaintFlag);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
