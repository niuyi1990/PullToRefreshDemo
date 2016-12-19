package com.niuyi.pulltorefreshdemo.ithome;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuyi.pulltorefreshdemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：${牛毅} on 2016/12/19 10:25
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyItRefreshHeader extends FrameLayout implements PtrUIHandler {

    private ImageView mIvArrow, mIvLoading;
    private TextView mTvRemain;
    private TextView mTvTime;

    private int mState;//状态标识符

    private static final int STATE_RESET = 0;
    private static final int STATE_PREPARE = 1;
    private static final int STATE_BEGIN = 2;
    private static final int STATE_COMPLETE = 3;

    private RotateAnimation up, down, circle;
    private View view;


    public MyItRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyItRefreshHeader(Context context) {
        super(context);
        init();
    }

    public MyItRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.my_ithome_refresh_header_view, this, false);

        mIvArrow = (ImageView) view.findViewById(R.id.iv_arrow);
        mIvLoading = (ImageView) view.findViewById(R.id.iv_loading);
        mTvRemain = (TextView) view.findViewById(R.id.tv_remain);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);

        up = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        up.setInterpolator(new LinearInterpolator());//不停顿
        up.setRepeatCount(0);//重复次数
        up.setFillAfter(true);//停在最后
        up.setDuration(500);

        down = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        down.setInterpolator(new LinearInterpolator());//不停顿
        down.setRepeatCount(0);//重复次数
        down.setFillAfter(true);//停在最后
        down.setDuration(500);

        circle = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        circle.setInterpolator(new LinearInterpolator());//不停顿
        circle.setRepeatCount(-1);//重复次数
        circle.setFillAfter(false);//停在最后
        circle.setDuration(500);

        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
        mIvArrow.setVisibility(View.VISIBLE);
        mIvLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;

        mIvArrow.setVisibility(View.INVISIBLE);
        mIvLoading.setVisibility(View.VISIBLE);

        mIvLoading.startAnimation(circle);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        mState = STATE_COMPLETE;
        mIvLoading.clearAnimation();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        switch (mState) {
            case STATE_RESET:

                break;
            case STATE_PREPARE:
                if (ptrIndicator.getCurrentPercent() <= 1.2) {
                    mTvRemain.setText("下拉刷新");
                } else {
                    mTvRemain.setText("释放立即刷新");
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("正在刷新...");
                break;
            case STATE_COMPLETE:
                mTvRemain.setText("下拉刷新");
                break;
        }
    }

}
