package com.niuyi.pulltorefreshdemo.jd;

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
 * 仿京东的下拉刷新头
 * 框架搭建在android-Ultra-Pull-To-Refresh-With-Load-More库的基础上，
 * 配置下拉相关参数请参考此库，经供参考和学习
 * 作者：${牛毅} on 2016/12/16 14:37
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyJdRefreshHeader extends FrameLayout implements PtrUIHandler {

    private TextView mTvRemain;//下拉不同位置的提示文字
    private ImageView mIvMan;//下来刷新的小人
    private ImageView mIvGoods;//商品包裹

    private int mState;//状态标识{下拉状态分为：重置，准备，开始，完成四种}

    private static final int STATE_RESET = 0;//重置
    private static final int STATE_PRE = 1;//准备
    private static final int STATE_BEGIN = 2;//开始
    private static final int STATE_COM = 3;//完成
    private AnimationDrawable mAnim;

    public MyJdRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyJdRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyJdRefreshHeader(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_jd_refresh_header_view, this, false);

        mTvRemain = (TextView) view.findViewById(R.id.tv_remain);
        mIvMan = (ImageView) view.findViewById(R.id.iv_man);
        mIvGoods = (ImageView) view.findViewById(R.id.iv_goods);

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
        //开始执行跑步动画,此时需要隐藏掉商品图
        mIvGoods.setVisibility(View.GONE);
        mIvMan.setBackgroundResource(R.drawable.jd);
        mAnim = (AnimationDrawable) mIvMan.getBackground();
        if (mAnim != null && !mAnim.isRunning()) {
            mAnim.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {//完成
        mState = STATE_COM;

        //刷新完成，停止动画，显示商品图
        mIvGoods.setVisibility(View.VISIBLE);
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.stop();
        }

        mIvMan.setBackgroundResource(R.mipmap.a2a);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {//更新
        switch (mState) {
            case STATE_RESET:

                break;
            case STATE_PRE:
                mIvMan.setAlpha(ptrIndicator.getCurrentPercent());
                mIvGoods.setAlpha(ptrIndicator.getCurrentPercent());

                FrameLayout.LayoutParams params = (LayoutParams) mIvMan.getLayoutParams();

                if (ptrIndicator.getCurrentPercent() <= 1) {
                    mIvMan.setScaleX(ptrIndicator.getCurrentPercent());
                    mIvMan.setScaleY(ptrIndicator.getCurrentPercent());
                    mIvGoods.setScaleX(ptrIndicator.getCurrentPercent());
                    mIvGoods.setScaleY(ptrIndicator.getCurrentPercent());

                    //设置小人与文字距离，看起来像是跑来取快递一样的效果
                    params.setMargins(0, 0, (int) (100 - 100 * ptrIndicator.getCurrentPercent()), 0);
                    mIvMan.setLayoutParams(params);
                }

                if (ptrIndicator.getCurrentPercent() < 1.3) {//设置文字改变
                    mTvRemain.setText("下拉刷新");
                } else {
                    mTvRemain.setText("松手刷新");
                }
                break;
            case STATE_BEGIN:
                mTvRemain.setText("刷新中...");
                break;
            case STATE_COM:
                mTvRemain.setText("刷新完成");
                break;
        }
    }
}
