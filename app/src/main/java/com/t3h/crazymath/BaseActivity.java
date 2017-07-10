package com.t3h.crazymath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutMain());
        findViewByIds();
        initComponents();
        setEvents();
    }

    protected abstract int getLayoutMain();

    protected abstract void findViewByIds();

    protected abstract void initComponents();

    protected abstract void setEvents();


}
