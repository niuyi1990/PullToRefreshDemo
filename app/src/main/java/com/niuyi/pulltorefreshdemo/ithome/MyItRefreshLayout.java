package com.niuyi.pulltorefreshdemo.ithome;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 高仿it之家下来刷新
 * 作者：${牛毅} on 2016/12/19 10:25
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyItRefreshLayout extends PtrFrameLayout {

    public MyItRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public MyItRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyItRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        MyItRefreshHeader header = new MyItRefreshHeader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
    }
}
