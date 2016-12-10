package com.yuda.test2;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.yuda.test2.Utils.BezierUtil;

/**
 * Created by yuda on 2016/12/10
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mFlagPoint;

    public BezierEvaluator(PointF f) {
        mFlagPoint = f;
    }

    @Override
    public PointF evaluate(float v, PointF f, PointF t1) {
        return BezierUtil.CalculateBezierPointForQuadratic(v,f,mFlagPoint,t1);
    }
}
