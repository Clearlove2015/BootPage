package com.zc.bootpage.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zc.bootpage.R;

public class WelcomeActivity extends AppCompatActivity {
    Handler handler = new Handler();

    SharedPreferences sp;

    private boolean isFirst;
    private static final String BOOT_KEY = "FIRST_BOOT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();//隐藏Toolbar

        sp = getSharedPreferences("boot_page", Context.MODE_PRIVATE);
        isFirst = sp.getBoolean(BOOT_KEY, true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirst) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(BOOT_KEY, false);
                    editor.commit();
                    startActivityForResult(new Intent(WelcomeActivity.this, BootActivity.class), 1000);
                } else {
                    startActivityForResult(new Intent(WelcomeActivity.this, MainActivity.class), 1001);
                }
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 || requestCode == 1001) {
            if (resultCode == 2000 || resultCode == 2001) {
                finish();
                System.exit(0);
            }
        }
    }
}
