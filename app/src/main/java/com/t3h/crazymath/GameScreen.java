package com.t3h.crazymath;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by NamNv on 19/03/2017.
 */

public class GameScreen extends BaseActivity implements View.OnClickListener {
    private TextView mNum1;
    private TextView mNum2;
    private TextView mResult;
    private TextView mScore;
    private TextView mTime;
    private Handler mHandler;
    private int mTextScore = 0;
    private int mResultRandom;
    private int mResultTrue;
    private int mTimePlay;
    private boolean mCheck;
    private List<Integer> listRe;
    private MyThread thread;

    @Override
    protected int getLayoutMain() {
        return R.layout.game_layout;
    }

    @Override
    protected void findViewByIds() {
        mNum1 = (TextView) findViewById(R.id.num1);
        mNum2 = (TextView) findViewById(R.id.num2);
        mResult = (TextView) findViewById(R.id.result);
        mScore = (TextView) findViewById(R.id.tv_score);
        mTime = (TextView) findViewById(R.id.tv_time);
        listRe = new ArrayList<>();

    }

    public static int getNum() {
        Random rd = new Random();
        return rd.nextInt(100) + 1;

    }

    @Override
    protected void initComponents() {

        revertTime();
        createNumbers();
        checkResult();
        mTimePlay = 3;
        mTime.setText(mTimePlay + "");
        if (thread != null) {
            thread.mIsInterrup = true;
            thread.interrupt();

        }
        thread = new MyThread();
        thread.mIsInterrup = false;
        thread.start();
    }

    private void revertTime() {
        mTime.setText(mTimePlay + "");
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mTime.setText(msg.arg1 + "");
            }
        };
    }

    private void createNumbers() {
        int x = getNum();
        int y = getNum();
        mResultTrue = x + y;
        mNum1.setText(x + "");
        mNum2.setText(y + "");

        listRe.add(x + y);
        listRe.add(x + y + 10);
        listRe.add(x + y - 1);
        listRe.add(x + y - 10);

        Random rd = new Random();
        int re = rd.nextInt(listRe.size() - 1);
        mResultRandom = listRe.get(re);
        mResult.setText(mResultRandom + "");
    }


    private void checkResult() {
        if (mResultTrue == mResultRandom) {
            mCheck = true;
        } else {
            mCheck = false;
        }
    }

    @Override
    protected void setEvents() {
        findViewById(R.id.btn_true).setOnClickListener(this);
        findViewById(R.id.btn_false).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_true:
                if (mCheck) {
                    Toast.makeText(this, "Bạn đã trả lời đúng", Toast.LENGTH_SHORT).show();
                    mTextScore += 1;
                    mScore.setText(mTextScore + "");
                    listRe.clear();
                    initComponents();
                } else {
                    Toast.makeText(this, "Bạn đã trả lời sai", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_false:
                if (!mCheck) {
                    Toast.makeText(this, "Bạn đã trả lời đúng", Toast.LENGTH_SHORT).show();
                    mTextScore += 1;
                    mScore.setText(mTextScore + "");
                    listRe.clear();
                    initComponents();
                } else {
                    Toast.makeText(this, "Bạn đã trả lời sai", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private class MyThread extends Thread {
        private boolean mIsInterrup;

        @Override
        public void run() {
            while (!mIsInterrup && mTimePlay > 0) {
                mTimePlay -= 1;
                // tao phong bi
                Message message = new Message();
                // noi dung
                message.arg1 = mTimePlay;
                // gui phong bi cho handler
                message.setTarget(mHandler);
                //cho message chay
                mHandler.sendMessage(message);
                SystemClock.sleep(1000);
            }
            if (mTimePlay == 0) {
                Intent intent = new Intent();
                intent.setClass(GameScreen.this, GameOver.class);
                startActivity(intent);
                finish();
            }

        }
    }


}
