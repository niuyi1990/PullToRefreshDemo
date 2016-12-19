package com.niuyi.pulltorefreshdemo.acfun;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 作者：${牛毅} on 2016/12/19 13:08
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyAcFunRefreshLayout extends PtrFrameLayout {

    public MyAcFunRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public MyAcFunRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public MyAcFunRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        MyAcFunRefreshHeader header = new MyAcFunRefreshHeader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
    }
}
