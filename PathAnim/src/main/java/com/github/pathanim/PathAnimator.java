package com.github.pathanim;

import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/***
 *   created by zhongrui on 2020/2/1
 */
public class PathAnimator {
    public static final long DEFAULT_TIME = 1000;

    private List<PathPoint> pathPointList = new ArrayList<>();
    private View targetView;


    public void setTargetViewXY(PathPoint pathPoint) {
        if (targetView == null) {
            return;
        }
        targetView.setTranslationX(pathPoint.x);
        targetView.setTranslationY(pathPoint.y);
    }

    private void initMove() {
        if (pathPointList.isEmpty()) {
            moveTo(0, 0);
        }
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
        initMove();
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
        initMove();
        addListForQuad(x1, y1, x2, y2 );
        return this;
    }

    public PathAnimator rQuadTo(float dx1, float dy1, float dx2, float dy2) {
        if (pathPointList.isEmpty()) {
            return quadTo(dx1, dy1, dx2, dy2);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return quadTo(
                pathPoint.getLastPoint().x + dx1,
                pathPoint.getLastPoint().y + dy1,
                pathPoint.getLastPoint().x + dx2,
                pathPoint.getLastPoint().y + dy2
        );
    }

    public PathAnimator cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        initMove();
        addListForCubic(x1, y1, x2, y2, x3, y3);
        return this;
    }

    public PathAnimator rCubicTo(float dx1, float dy1, float dx2, float dy2, float dx3, float dy3) {
        if (pathPointList.isEmpty()) {
            return cubicTo(dx1, dy1, dx2, dy2, dx3, dy3);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return cubicTo(
                pathPoint.getLastPoint().x + dx1,
                pathPoint.getLastPoint().y + dy1,
                pathPoint.getLastPoint().x + dx2,
                pathPoint.getLastPoint().y + dy2,
                pathPoint.getLastPoint().x + dx3,
                pathPoint.getLastPoint().y + dy3
        );
    }

    private void addListForOperation(float x, float y, int operation) {
        PathPoint pathPoint = new PathPoint(operation, x, y);
        pathPointList.add(pathPoint);
    }

    private void addListForCubic(float x1, float y1, float x2, float y2, float x3, float y3) {
        PathPoint pathPoint = new PathPoint(x1, y1, x2, y2, x3, y3);
        pathPointList.add(pathPoint);
    }

    private void addListForQuad(float x1, float y1, float x2, float y2) {
        PathPoint pathPoint = new PathPoint(x1, y1, x2, y2);
        pathPointList.add(pathPoint);
    }

    public ObjectAnimator createAnim(View targetView) {
        this.targetView = targetView;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "targetViewXY", new PathAnimatorEvaluator(), pathPointList.toArray());
        return animator;
    }
}
