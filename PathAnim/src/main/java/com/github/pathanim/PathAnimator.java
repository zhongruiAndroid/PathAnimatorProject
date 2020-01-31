package com.github.pathanim;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/***
 *   created by zhongrui on 2020/2/1
 */
public class PathAnimator {
    public static final long DEFAULT_TIME = 1000;

    private long durationTime = DEFAULT_TIME;
    private List<PathPoint> pathPointList = new ArrayList<>();
    private View targetView;

    public long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }

    public PathAnimator moveTo(float x, float y) {
        addListForOperation(x, y, PathPoint.MOVE);
        return this;
    }

    public PathAnimator rMoveTo(float dx, float dy) {
        if (pathPointList.isEmpty()) {
            return moveTo(dx, dy);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return moveTo(pathPoint.getLastPoint().x + dx, pathPoint.getLastPoint().y + dy);
    }

    public PathAnimator lineTo(float x, float y) {
        addListForOperation(x, y, PathPoint.LINE);
        return this;
    }

    public PathAnimator rLineTo(float dx, float dy) {
        if (pathPointList.isEmpty()) {
            return lineTo(dx, dy);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return lineTo(pathPoint.getLastPoint().x + dx, pathPoint.getLastPoint().y + dy);
    }

    public PathAnimator quadTo(float x1, float y1, float x2, float y2) {
//        addListForOperation(x,y,PathPoint.CUBIC);
        return this;
    }

    public PathAnimator rQuadTo(float dx1, float dy1, float dx2, float dy2) {
        return this;
    }

    public PathAnimator cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        addListForCubic(x1, y1, x2, y2, x3, y3);
        return this;
    }

    public PathAnimator rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        if (pathPointList.isEmpty()) {
            return cubicTo(x1, y1, x2, y2, x3, y3);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return cubicTo(pathPoint.getLastPoint().x + x1,
                pathPoint.getLastPoint().y + y1,
                pathPoint.control1X + x2,
                pathPoint.control1Y + y2,
                pathPoint.control2X + x3,
                pathPoint.control2Y + y3
        );
    }

    private void addListForOperation(float x, float y, int operation) {
        PathPoint pathPoint = new PathPoint(operation, x, y);
        pathPointList.add(pathPoint);
    }

    private void addListForCubic(float x1, float y1, float x2, float y2, float x3, float y3) {
        PathPoint pathPoint = new PathPoint(PathPoint.CUBIC, x1, y1, x2, y2, x3, y3);
        pathPointList.add(pathPoint);
    }

    public void startAnimator(View targetView) {
        startAnimator(targetView, getDurationTime());
    }

    public void startAnimator(View targetView, long duration) {
        if(targetView==null){
            return;
        }
        if(duration<=0){
            duration=DEFAULT_TIME;
        }
        this.targetView=targetView;

        ObjectAnimator animator=ObjectAnimator.ofObject(this,"targetViewXY",new PathAnimatorEvaluator(),pathPointList.toArray());
        animator.setDuration(duration);
        animator.start();
    }

    public void setTargetViewXY(PathPoint pathPoint){
        if(targetView==null){
            return;
        }
        Log.i("=====","========xxx=="+pathPoint.x);
        targetView.setTranslationX(pathPoint.x);
        targetView.setTranslationY(pathPoint.y);
    }
}
