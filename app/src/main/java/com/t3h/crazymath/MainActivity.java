package com.t3h.crazymath;

import android.content.Intent;
import android.view.View;

/**
 * Created by NamNv on 19/03/2017.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutMain() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewByIds() {

    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void setEvents() {
        findViewById(R.id.btn_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
