package com.xty.espressorecipe.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xty.espressorecipe.R;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class MyCustomView extends View{
    private int percent;
    public static final String TAG = "MyCustomView";
    public MyCustomView(Context context) {
        super(context);
        init(null);
        Log.d(TAG,"constructor");
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        Log.d(TAG,"constructor");
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        Log.d(TAG,"constructor");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
        Log.d(TAG,"constructor");
    }

    private void init(AttributeSet attrs){
        if (attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomView);
            if (a == null){
                return;
            }
            percent = a.getInt(R.styleable.MyCustomView_percent,50);
            a.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG,"onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(18,18);
        Log.d(TAG,"onMeasure");
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.d(TAG,"layout");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG,"onLayout");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG,"dispatchDraw");
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d(TAG,"draw");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");

        canvas.drawText("mycustomview",0f,0f,new Paint());
    }

    @Override
    public void invalidate() {
        super.invalidate();
        Log.d(TAG,"invalidate");
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
        Log.d(TAG,"postInvalidate");
    }
}
