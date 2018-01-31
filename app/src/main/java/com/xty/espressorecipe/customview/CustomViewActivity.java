package com.xty.espressorecipe.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.xty.espressorecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button1;

    @BindView(R.id.button2)
    Button button2;

    @BindView(R.id.button3)
    Button button3;

    @BindView(R.id.myCustomView)
    MyCustomView myCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);

        button1.setOnClickListener(v -> {
            myCustomView.invalidate();
        });

        button2.setOnClickListener(v -> {
            myCustomView.postInvalidate();
        });

        button3.setOnClickListener(v -> {
            myCustomView.requestLayout();
        });
    }
}
