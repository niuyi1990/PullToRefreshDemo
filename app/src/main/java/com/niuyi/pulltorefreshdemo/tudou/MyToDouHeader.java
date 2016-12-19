package com.niuyi.pulltorefreshdemo.tudou;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
public class MyToDouHeader extends FrameLayout implements PtrUIHandler {

    private ImageView mIvPlay;
    private TextView mTvRemain;

    private int mState;//状态标识符

    private static final int STATE_RESET = 0;
    private static final int STATE_PREPARE = 1;
    private static final int STATE_BEGIN = 2;
    private static final int STATE_COMPLETE = 3;

    private RotateAnimation rotateAnim, rotateASelf;
    private AnimationSet set;


    public MyToDouHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyToDouHeader(Context context) {
        super(context);
        init();
    }

    public MyToDouHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_todou_refresh_header_view, this, false);

        mIvPlay = (ImageView) view.findViewById(R.id.iv_play);
        mTvRemain = (TextView) view.findViewById(R.id.tv_remain);

        set = new AnimationSet(true);

        rotateAnim = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setInterpolator(new LinearInterpolator());//不停顿
        rotateAnim.setRepeatCount(-1);//重复次数
        rotateAnim.setFillAfter(false);//停在最后
        rotateAnim.setDuration(300);

        rotateASelf = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF, 0.2f);
        rotateASelf.setInterpolator(new LinearInterpolator());//不停顿
        rotateASelf.setRepeatCount(-1);//重复次数
        rotateASelf.setFillAfter(false);//停在最后
        rotateASelf.setDuration(300);

        set.addAnimation(rotateAnim);
        set.addAnimation(rotateASelf);

        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
        if (rotateAnim != null) mIvPlay.startAnimation(set);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        mState = STATE_COMPLETE;
        mIvPlay.clearAnimation();
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
                    mTvRemain.setText("释放刷新");
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("正在刷新");
                break;
            case STATE_COMPLETE:
                mTvRemain.setText("下拉刷新");
                break;
        }
    }

}
