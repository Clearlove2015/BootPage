package com.zc.bootpage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zc.bootpage.R;
import com.zc.bootpage.adapter.MyPagerAdapter;
import com.zc.bootpage.fragment.FirstFragment;
import com.zc.bootpage.fragment.SecondFragment;
import com.zc.bootpage.fragment.ThirdFragment;
import com.zc.bootpage.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BootActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        getSupportActionBar().hide();//隐藏Toolbar
        ButterKnife.bind(this);

        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), getFragments()));
        indicator.setViewPager(viewpager);
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        Fragment first = new FirstFragment();
        Fragment second = new SecondFragment();
        Fragment third = new ThirdFragment();
        fragments.add(first);
        fragments.add(second);
        fragments.add(third);
        return fragments;
    }

    //点击进入主界面
    public void start(View view) {
        startActivityForResult(new Intent(BootActivity.this, MainActivity.class), 1002);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002 && resultCode == 2000) {
            setResult(2001);
            finish();
        }
    }
}
