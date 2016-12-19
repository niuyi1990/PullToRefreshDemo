package com.niuyi.pulltorefreshdemo.nba;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 模仿nba客户端的下拉刷新
 * 作者：${牛毅} on 2016/12/16 16:49
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyNbaRefreshLayout extends PtrFrameLayout {

    public MyNbaRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public MyNbaRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyNbaRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        MyNbaRefreshHeader header = new MyNbaRefreshHeader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
    }
}
