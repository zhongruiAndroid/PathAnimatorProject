package com.github.pathanim;

import android.animation.TypeEvaluator;

/***
 *   created by zhongrui on 2020/2/1
 */
public class PathAnimatorEvaluator implements TypeEvaluator<PathPoint> {
    @Override
    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        float x = 0;
        float y = 0;
        float temp = 1f - fraction;
        if (endValue.operation == PathPoint.CUBIC_3) {
            x = startValue.x * temp * temp * temp
                    + 3 * endValue.control1X * fraction * temp * temp
                    + 3 * endValue.control2X * fraction * fraction * temp
                    + endValue.x * fraction * fraction * fraction;


            y = startValue.y * temp * temp * temp
                    + 3 * endValue.control1Y * fraction * temp * temp
                    + 3 * endValue.control2Y * fraction * fraction * temp
                    + endValue.y * fraction * fraction * fraction;

        }else if (endValue.operation == PathPoint.CUBIC) {
            x = startValue.x * temp  * temp
                    + 2 * endValue.control1X * fraction * temp
                    + endValue.x * fraction * fraction ;

            y = startValue.y * temp  * temp
                    + 2 * endValue.control1Y * fraction * temp
                    + endValue.y * fraction * fraction ;

        } else if (endValue.operation == PathPoint.LINE) {
            x = (endValue.x - startValue.x) * fraction + startValue.x;
            y = (endValue.y - startValue.y) * fraction + startValue.y;
        } else    {
            x = endValue.x;
            y = endValue.y;
        }
        PathPoint pathPoint = new PathPoint(PathPoint.MOVE, x, y);
        return pathPoint;
    }
}
