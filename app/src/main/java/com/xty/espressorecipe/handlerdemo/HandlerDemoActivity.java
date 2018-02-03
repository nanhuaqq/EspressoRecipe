package com.xty.espressorecipe.handlerdemo;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.xty.espressorecipe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandlerDemoActivity extends AppCompatActivity {

    @BindView(R.id.tvHandler)
    TextView tvHandler;

    @BindView(R.id.tvHandlerThread)
    TextView tvHandlerThread;

    @BindView(R.id.btnHandler)
    Button btnHandler;

    @BindView(R.id.btnHandlerThread)
    Button btnHandlerThread;

    private Handler mHandler,threadHandler;

    private HandlerThread mHandlerThread;

    private int handlerCount = 0 ;

    private int handlerThreadCount = 0;

    public static final int FROM_HANDLER = 1;

    public static final int FROM_HANDLER_THREAD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);

        ButterKnife.bind(this);

        btnHandler.setOnClickListener(v -> {
            Message msg = mHandler.obtainMessage();
            msg.obj = "" + handlerCount;
            msg.arg1 = FROM_HANDLER;
            handlerCount ++ ;
            mHandler.sendMessage(msg);
        });

        btnHandlerThread.setOnClickListener(v -> {
            new Thread(() -> {
                Message msg = threadHandler.obtainMessage();
                msg.obj = "" + handlerThreadCount;
                msg.arg2 = FROM_HANDLER_THREAD;
                handlerThreadCount ++;
                threadHandler.sendMessage(msg);
            }).start();
        });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                msg.obj
//                msg.replyTo
                tvHandler.setText("" + msg.obj);
            }
        };

        mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();

        threadHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final String text = msg.obj + "";
                tvHandlerThread.post(new Runnable() {
                    @Override
                    public void run() {
                        //todo why msg.obj == null?
                        tvHandlerThread.setText(text);
                    }
                });
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
