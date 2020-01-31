package com.test.pathanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
                animator.moveTo(100,200);
                animator.lineTo(500,700);
//                animator.cubicTo(300,200,400,600,600,700);
                animator.startAnimator(iv);
            }
        });
    }
}
