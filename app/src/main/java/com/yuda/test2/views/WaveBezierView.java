package com.yuda.test2.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

/**
 * Created by yuda on 2016/12/10
 */

public class WaveBezierView extends View implements View.OnClickListener{
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

    private int mWaveLenght;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;
    private int mWaveCount;
    private ValueAnimator mValueAnimator;
    private int mOffset;

    public WaveBezierView(Context context) {
        super(context);
    }

    public WaveBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWaveLenght = 100;
        setOnClickListener(this);
    }

    public WaveBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath = new Path();

        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h/2;

        mWaveCount = (int) Math.round(mScreenWidth/mWaveLenght + 1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLenght+mOffset,mCenterY);
        for (int i = 0;i<mWaveCount;i++){
            mPath.quadTo(-mWaveLenght*3/4+i*mWaveLenght+mOffset,mCenterY+15,-mWaveLenght/2+i*mWaveLenght+mOffset,mCenterY);
            mPath.quadTo(-mWaveLenght/4+i*mWaveLenght+mOffset,mCenterY-15,i*mWaveLenght+mOffset,mCenterY);
        }
        mPath.lineTo(mScreenWidth,mScreenHeight);
        mPath.lineTo(0,mScreenHeight);
        //test1(canvas);
        //test2(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    private void test2(Canvas canvas) {

        mPath.quadTo(100,mStartPonitY-100,200,mStartPonitY);
        mPath.quadTo(300,mStartPonitY+100,400,mStartPonitY);

    }

    private void test1(Canvas canvas) {
        mPath.cubicTo(mFlagPointX0,mFlagPointY0,mFlagPointX1,mFlagPointY1,mEndPointX,mEndPointY);

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
    public void onClick(View view) {
        Toast.makeText(getContext(), "点击了该View控件", Toast.LENGTH_SHORT).show();
        mValueAnimator = ValueAnimator.ofInt(0,mWaveLenght);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
        this.setClickable(false);
    }
}
