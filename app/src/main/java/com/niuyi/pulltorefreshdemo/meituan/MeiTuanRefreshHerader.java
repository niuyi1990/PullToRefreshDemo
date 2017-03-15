package com.niuyi.pulltorefreshdemo.meituan;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.niuyi.pulltorefreshdemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：${牛毅}
 * 时间：2017/03/15 15：01
 * 邮箱：niuyi19900923@gmail.com
 * 描述：美团外卖的下拉刷新头
 */
public class MeiTuanRefreshHerader extends FrameLayout implements PtrUIHandler {

    private int mState;//状态标识{下拉状态分为：重置，准备，开始，完成四种}

    private static final int STATE_RESET = 0;//重置
    private static final int STATE_PRE = 1;//准备
    private static final int STATE_BEGIN = 2;//开始
    private static final int STATE_COM = 3;//完成

    private MeiTuanRefreshFirstStepView mFirstView;
    private MeiTuanRefreshSecondStepView mSecondView;
    private AnimationDrawable secondAnim;
    private MeiTuanRefreshThirdStepView mThirdView;
    private AnimationDrawable thirdAnim;

    private TextView mTvRemain;

    public MeiTuanRefreshHerader(@NonNull Context context) {
        super(context);
        init();
    }

    public MeiTuanRefreshHerader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiTuanRefreshHerader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.mei_tuan_refresh_herader_view, this, false);

        mFirstView = (MeiTuanRefreshFirstStepView) headerView.findViewById(R.id.first_view);
        mTvRemain = (TextView) headerView.findViewById(R.id.tv_remain);
        mSecondView = (MeiTuanRefreshSecondStepView) headerView.findViewById(R.id.second_view);
        mSecondView.setBackgroundResource(R.drawable.pull_to_refresh_second_anim);
        secondAnim = (AnimationDrawable) mSecondView.getBackground();
        mThirdView = (MeiTuanRefreshThirdStepView) headerView.findViewById(R.id.third_view);
        mThirdView.setBackgroundResource(R.drawable.pull_to_refresh_third_anim);
        thirdAnim = (AnimationDrawable) mThirdView.getBackground();

        addView(headerView);
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
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        mState = STATE_COM;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        switch (mState) {
            case STATE_RESET:
                mTvRemain.setText("下拉刷新");
                //第一状态view显示出来
                mFirstView.setVisibility(View.VISIBLE);
                //第二状态view隐藏起来
                mSecondView.setVisibility(View.GONE);
                //第二状态动画停止
                secondAnim.stop();
                //第三状态view隐藏起来
                mThirdView.setVisibility(View.GONE);
                //第三状态动画停止
                thirdAnim.stop();
                break;
            case STATE_PRE:
                //这里控制第一个view下拉时放大的动画
                if (ptrIndicator.getCurrentPercent() <= 1) {
                    //第一状态view显示出来
                    mFirstView.setVisibility(View.VISIBLE);
                    //给第一个状态的View设置当前进度值
                    mFirstView.setCurrentProgress(ptrIndicator.getCurrentPercent());
                    //重画
                    mFirstView.postInvalidate();
                }

                if (ptrIndicator.getCurrentPercent() < 1.3) {//设置文字改变
                    mTvRemain.setText("下拉刷新");
                    //第一状态view显示出来
                    mFirstView.setVisibility(View.VISIBLE);
                    //第二状态view隐藏起来
                    mSecondView.setVisibility(View.GONE);
                    //第二状态动画停止
                    secondAnim.stop();
                    //第三状态view隐藏起来
                    mThirdView.setVisibility(View.GONE);
                    //第三状态动画停止
                    thirdAnim.stop();
                } else {
                    mTvRemain.setText("松手刷新");
                    //第一状态view隐藏起来
                    mFirstView.setVisibility(View.GONE);
                    //第二状态view显示出来
                    mSecondView.setVisibility(View.VISIBLE);
                    //播放第二个View动画
                    secondAnim.start();
                    //第三状态view隐藏起来
                    mThirdView.setVisibility(View.GONE);
                    //停止第三状态的动画
                    thirdAnim.stop();
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("正在刷新");
                //第一状态view隐藏起来
                mFirstView.setVisibility(View.GONE);
                //第三状态view显示出来
                mThirdView.setVisibility(View.VISIBLE);
                //第二状态view隐藏起来
                mSecondView.setVisibility(View.GONE);
                //停止第二状态动画
                secondAnim.stop();
                //启动第三状态view
                thirdAnim.start();
                break;
            case STATE_COM:
                mTvRemain.setText("刷新完成");
                break;
            default:
                break;
        }
    }
}
