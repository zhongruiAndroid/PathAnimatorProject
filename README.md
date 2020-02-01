# PathAnimatorProject
<img src="https://github.com/zhongruiAndroid/PathAnimatorProject/blob/master/screenshot/pathanim.gif" alt="image" >
<br/>  

##### 不规则路径位移属性动画
```java

PathAnimator animator=new PathAnimator();

animator.lineTo(500,0);
animator.cubicTo(500,500,0,0,0,500);
animator.lineTo(700,500);
//上面的方法等价于下面
//animator.rLineTo(500,0);
//animator.rCubicTo(0,500,-500,0,-500,500);
//animator.rLineTo(700,0);

animator.setDuration(3000);
animator.setInterpolator(new LinearInterpolator());
animator.startAnimator(需要位移的view);
```
#### canvas中(0,0)坐标是view所在坐标系的左上角
#### 但是这里(0,0)坐标是view位移前的位置,也就是view初始位置在哪里,哪里就是(0,0)
| 以下方法和Path的用法一致 | 说明           | 通过上一个坐标的偏移量确定某个位置 |
|------------------------------|----------------|------------------------------------|
| moveTo                       |      移动至某点  | rMoveTo                            |
| lineTo                       |    确定一条直线  | rLineTo                            |
| quadTo                       | 二阶贝塞尔曲线 | rQuadTo                            |
| cubicTo                      | 三阶贝塞尔曲线 | rCubicTo                           |



| 用法和Animator一致 | 说明                                     |                                               |
|--------------------|------------------------------------------|-----------------------------------------------|
| setDuration        | 设置动画执行时间                         |                                               |
| setRepeatCount     | 设置动画重复执行次数                     |                                               |
| setRepeatMode      | 设置动画重复模式                         | PathAnimator.RESTART<br/>PathAnimator.REVERSE |
| setStartDelay      | 设置动画延迟执行时间:毫秒                |                                               |
| setInterpolator    | 设置插值器                               |                                               |
| setCurrentPlayTime | 设置动画从哪个时间点开始                 |                                               |
| setCurrentFraction | 设置动画从某个区间点开始,范围区间[0f,1f] |                                               |
| startAnimator      | 开始执行动画                             |                                               |
