package com.github.pathanim;

import android.graphics.PointF;

/***
 *   created by zhongrui on 2020/2/1
 */
public class PathPoint {
    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int CUBIC = 2;

    protected float x;
    protected float y;
    protected float control1X;
    protected float control1Y;

    protected float control2X;
    protected float control2Y;


    protected int operation = MOVE;

    public PathPoint(int operation, float valueX, float valueY) {
        this.operation = operation;
        this.x = valueX;
        this.y = valueY;
    }

    public PathPoint(int operation, float valueX, float valueY, float x2, float y2, float x3, float y3) {
        this.operation = operation;
        this.x = valueX;
        this.y = valueY;
        control1X =x2;
        control1Y =y2;
        control2X =x3;
        control2Y =y3;
    }

    public PointF getLastPoint() {
        return new PointF(x, y);
    }


}
