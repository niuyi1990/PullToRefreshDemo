package com.niuyi.pulltorefreshdemo.acfun;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuyi.pulltorefreshdemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：${牛毅} on 2016/12/19 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyAcFunRefreshHeader extends FrameLayout implements PtrUIHandler {

    ImageView mIvGirl,mIvAnim;
    TextView mTvRemain;

    private int mState;//状态标识{下拉状态分为：重置，准备，开始，完成四种}

    private static final int STATE_RESET = 0;//重置
    private static final int STATE_PRE = 1;//准备
    private static final int STATE_BEGIN = 2;//开始
    private static final int STATE_COM = 3;//完成

    private AnimationDrawable mAnim;

    public MyAcFunRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public MyAcFunRefreshHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public MyAcFunRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_acfun_refresh_header_view, this, false);
        mIvGirl = (ImageView) view.findViewById(R.id.iv_girl);
        mIvAnim = (ImageView) view.findViewById(R.id.iv_anim);
        mTvRemain = (TextView) view.findViewById(R.id.tv_remain);
        addView(view);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {//重置
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {//准备
        mState = STATE_PRE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {//开始
        mState = STATE_BEGIN;
        mIvGirl.setVisibility(View.GONE);
        mIvAnim.setVisibility(View.VISIBLE);
        mIvAnim.setBackgroundResource(R.drawable.acfun);
        mAnim = (AnimationDrawable) mIvAnim.getBackground();
        if (mAnim != null && !mAnim.isRunning()) {
            mAnim.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {//完成
        mState = STATE_COM;

        //刷新完成，停止动画
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.stop();
        }
        mIvGirl.setVisibility(View.VISIBLE);
        mIvAnim.setVisibility(View.GONE);
        mIvGirl.setImageResource(R.mipmap.ptr_loading_1);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {//更新
        switch (mState) {
            case STATE_RESET:

                break;
            case STATE_PRE:
                if (ptrIndicator.getCurrentPercent() < 1.3) {//设置文字改变
                    mTvRemain.setText("下拉刷新...");
                } else {
                    mTvRemain.setText("F5的能量女朋友出现了");
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("F5的能量女朋友出现了");
                break;
            case STATE_COM:
                mTvRemain.setText("更新完成...");
                break;
        }
    }
}
