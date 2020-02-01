package com.github.pathanim;

import android.graphics.PointF;

/***
 *   created by zhongrui on 2020/2/1
 */
public class PathPoint  {
    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int CUBIC = 2;
    public static final int CUBIC_3 = 3;

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

    public PathPoint( float x1, float y1, float x2, float y2, float endX, float endY) {
        this.operation = CUBIC_3;
        control1X = x1;
        control1Y = y1;
        control2X = x2;
        control2Y = y2;

        this.x = endX;
        this.y = endY;
    }
    public PathPoint( float valueX, float valueY, float endX, float endY ) {
        this.operation = CUBIC;
        control1X = valueX;
        control1Y = valueY;
        this.x = endX;
        this.y = endY;
    }

    public PointF getLastPoint() {
        return new PointF(x, y);
    }
}
