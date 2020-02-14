# PathAnimatorProject
<img src="https://github.com/zhongruiAndroid/PathAnimatorProject/blob/master/screenshot/pathanim.gif" alt="image" >
<br/>  

### 不规则路径位移属性动画
```java

PathAnimator animator=new PathAnimator();

animator.lineTo(500,0);
animator.cubicTo(500,500,0,0,0,500);
animator.lineTo(700,500);
//上面的方法等价于下面
//animator.rLineTo(500,0);
//animator.rCubicTo(0,500,-500,0,-500,500);
//animator.rLineTo(700,0);

ObjectAnimator anim = animator.createAnim(需要位移的view);
anim.setDuration(3000);
anim.setInterpolator(new LinearInterpolator());
anim.start();
```
#### canvas中(0,0)坐标是view所在坐标系的左上角
#### 但是这里(0,0)坐标是view位移前的位置,也就是view初始位置在哪里,哪里就是(0,0)
| 以下方法和Path的用法一致 | 说明           | 通过上一个坐标的偏移量确定某个位置 |
|------------------------------|----------------|------------------------------------|
| moveTo                       |      移动至某点  | rMoveTo                            |
| lineTo                       |    确定一条直线  | rLineTo                            |
| quadTo                       | 二阶贝塞尔曲线 | rQuadTo                            |
| cubicTo                      | 三阶贝塞尔曲线 | rCubicTo                           |



| [ ![Download](https://api.bintray.com/packages/zhongrui/mylibrary/PathAnim/images/download.svg) ](https://bintray.com/zhongrui/mylibrary/PathAnim/_latestVersion) | 最新版本号|
|--------|----|
```gradle
implementation 'com.github:PathAnim:版本号'
```

