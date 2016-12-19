package com.niuyi.pulltorefreshdemo.nba;

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
 * 作者：${牛毅} on 2016/12/16 16:48
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyNbaRefreshHeader extends FrameLayout implements PtrUIHandler {

    private ImageView mIvBasketball;
    private ImageView mIvAnim;
    private TextView mTvRemain;
    private TextView mTvTime;

    private int mState;//状态标识

    private static final int STATE_RESET = 0;//重置
    private static final int STATE_PRE = 1;//准备
    private static final int STATE_BEGIN = 2;//开始
    private static final int STATE_COM = 3;//完成

    private AnimationDrawable mAnim;

    public MyNbaRefreshHeader(Context context) {
        super(context);
        init();
    }

    public MyNbaRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyNbaRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_nba_refresh_layout_header, this, false);
        mIvBasketball = (ImageView) view.findViewById(R.id.iv_basketball);
        mIvAnim = (ImageView) view.findViewById(R.id.iv_anim);
        mTvRemain = (TextView) view.findViewById(R.id.tv_remain);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);

        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PRE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
        //开启篮球跳动的动画
        mIvBasketball.setVisibility(View.GONE);
        mIvAnim.setVisibility(View.VISIBLE);
        mIvAnim.setBackgroundResource(R.drawable.nba_jump);
        mAnim = (AnimationDrawable) mIvAnim.getBackground();
        if (mAnim != null && !mAnim.isRunning()) {
            mAnim.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        mState = STATE_COM;
        //刷新完成，停止动画，显示商品图
        mIvBasketball.setVisibility(View.VISIBLE);
        mIvAnim.setVisibility(View.INVISIBLE);
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.stop();
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        switch (mState) {
            case STATE_RESET:

                break;
            case STATE_PRE:
                if (ptrIndicator.getCurrentPercent() <= 1.4) {
                    mTvRemain.setText("下拉刷新");
                } else {
                    mTvRemain.setText("释放刷新");
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("正在更新...");
                break;
            case STATE_COM:
                mTvRemain.setText("更新完成");
                break;
        }
    }
}
