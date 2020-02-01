package com.test.pathanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.github.pathanim.PathAnimator;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt =findViewById(R.id.bt);
        iv = findViewById(R.id.iv);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PathAnimator animator=new PathAnimator();

                animator.lineTo(500,0);
                animator.cubicTo(500,500,0,0,0,500);
                animator.lineTo(700,500);

//              animator.rLineTo(500,0);
//              animator.rCubicTo(0,500,-500,0,-500,500);
//              animator.rLineTo(700,0);

                animator.setDuration(3000);
                animator.setInterpolator(new LinearInterpolator());
                animator.startAnimator(iv);
            }
        });
    }
}
