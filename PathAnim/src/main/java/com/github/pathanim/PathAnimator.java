package com.github.pathanim;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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
//        addListForOperation(x,y,PathPoint.CUBIC);
        return this;
    }

    public PathAnimator rQuadTo(float dx1, float dy1, float dx2, float dy2) {
        return this;
    }

    public PathAnimator cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        initMove();
        addListForCubic(x1, y1, x2, y2, x3, y3);
        return this;
    }

    public PathAnimator rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        if (pathPointList.isEmpty()) {
            return cubicTo(x1, y1, x2, y2, x3, y3);
        }
        PathPoint pathPoint = pathPointList.get(pathPointList.size() - 1);
        return cubicTo(
                pathPoint.getLastPoint().x + x1,
                pathPoint.getLastPoint().y + y1,
                pathPoint.getLastPoint().x + x2,
                pathPoint.getLastPoint().y + y2,
                pathPoint.getLastPoint().x + x3,
                pathPoint.getLastPoint().y + y3
        );
    }

    private void addListForOperation(float x, float y, int operation) {
        PathPoint pathPoint = new PathPoint(operation, x, y);
        pathPointList.add(pathPoint);
    }

    private void addListForCubic(float x1, float y1, float x2, float y2, float x3, float y3) {
        PathPoint pathPoint = new PathPoint(PathPoint.CUBIC, x3, y3, x1, y1, x2, y2);
        pathPointList.add(pathPoint);
    }

    public void startAnimator(View targetView) {
        startAnimator(targetView, getDuration());
    }

    public void startAnimator(View targetView, long duration) {
        if (targetView == null) {
            return;
        }
        if (duration <= 0) {
            duration = DEFAULT_TIME;
        }
        this.targetView = targetView;

        ObjectAnimator animator = ObjectAnimator.ofObject(this, "targetViewXY", new PathAnimatorEvaluator(), pathPointList.toArray());

        animator.setDuration(duration);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            animator.setAutoCancel(mAutoCancel);
        }
        if (playTime != INVALID_ATTR) {
            animator.setCurrentPlayTime(playTime);
        }
        if (fraction != INVALID_ATTR && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            animator.setCurrentFraction(fraction);
        }
        if (mRepeatCount != INVALID_ATTR) {
            animator.setRepeatCount(mRepeatCount);
        }
        if (mRepeatMode != INVALID_ATTR) {
            animator.setRepeatMode(mRepeatMode);
        }
        if (mStartDelay != INVALID_ATTR) {
            animator.setStartDelay(mStartDelay);
        }
        if (mInterpolator != null) {
            animator.setInterpolator(mInterpolator);
        }
        animator.start();
    }

    /************************************************************************/
    private final int INVALID_ATTR = -100;
    private float fraction = INVALID_ATTR;
    private long durationTime = DEFAULT_TIME;
    private boolean mAutoCancel = false;
    private long playTime = INVALID_ATTR;
    private int mRepeatCount = INVALID_ATTR;
    private int mRepeatMode = RESTART;
    private long mStartDelay = INVALID_ATTR;
    private TimeInterpolator mInterpolator;

    /************************************************************************/
    @IntDef({RESTART, REVERSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public static final int RESTART = ValueAnimator.RESTART;
    public static final int REVERSE = ValueAnimator.REVERSE;
    public static final int INFINITE = ValueAnimator.INFINITE;


    public PathAnimator setCurrentFraction(float fraction) {
        this.fraction = fraction;
        return this;
    }

    public long getDuration() {
        return durationTime;
    }

    public PathAnimator setDuration(long duration) {
        this.durationTime = duration;
        return this;
    }

    public PathAnimator setAutoCancel(boolean cancel) {
        mAutoCancel = cancel;
        return this;
    }

    public PathAnimator setCurrentPlayTime(long playTime) {
        this.playTime = playTime;
        return this;
    }

    public PathAnimator setRepeatCount(int value) {
        mRepeatCount = value;
        return this;
    }

    public PathAnimator setRepeatMode(@RepeatMode int value) {
        mRepeatMode = value;
        return this;
    }

    public PathAnimator setStartDelay(long startDelay) {
        mStartDelay = startDelay;
        return this;
    }

    public PathAnimator setInterpolator(TimeInterpolator value) {
        if (value != null) {
            mInterpolator = value;
        } else {
            mInterpolator = new LinearInterpolator();
        }
        return this;
    }
}
