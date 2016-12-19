package com.niuyi.pulltorefreshdemo.jd;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 下拉刷新的Layout
 * 作者：${牛毅} on 2016/12/16 15:07
 * 邮箱：niuyi19900923@hotmail.com
 */
public class MyJdRefreshLayout extends PtrFrameLayout {

    public MyJdRefreshLayout(Context context) {
        super(context);
        initView();
    }

    public MyJdRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyJdRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        MyJdRefreshHeader header = new MyJdRefreshHeader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
    }

}
