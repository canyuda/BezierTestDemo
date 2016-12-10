package com.yuda.test2.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.yuda.test2.BezierEvaluator;

/**
 * Created by yuda on 2016/12/10
 */

public class ShoppingCartView extends View implements View.OnClickListener{
    private float mStartPointX;
    private float mStartPonitY;
    private float mEndPointX;
    private float mEndPointY;

    private float mFlagPointX;
    private float mFlagPointY;

    private Path mPath;
    private Paint mPaint;
    private Paint mPaintFlag;
    private Paint mPaintCircle;
    private float mMovePointX;
    private float mMovePointY;
    public ShoppingCartView(Context context) {
        super(context);
    }

    public ShoppingCartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setStrokeWidth(1);
        mPaintFlag.setColor(Color.RED);
        mPaintFlag.setStyle(Paint.Style.FILL);
        mPaintFlag.setTextSize(20);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(Color.GREEN);
    }

    public ShoppingCartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mStartPointX = 100;
        mStartPonitY = 300;

        mEndPointX = 500;
        mEndPointY = 1000;

        mMovePointX = mStartPointX;
        mMovePointY = mStartPonitY;

        mFlagPointX = 500;
        mFlagPointY = 100;

        mPath = new Path();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPonitY);

        mPath.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);
        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(mMovePointX,mMovePointY,20,mPaintCircle);

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
    public void onClick(View view) {
        BezierEvaluator evaluator = new BezierEvaluator(new PointF(mFlagPointX,mFlagPointY));
        ValueAnimator animator = ValueAnimator.ofObject(
                evaluator,
                new PointF(mStartPointX,mStartPonitY),
                new PointF(mEndPointX,mEndPointY)
        );
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                mMovePointX = pointF.x;
                mMovePointY = pointF.y;
                invalidate();
            }
        });
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
