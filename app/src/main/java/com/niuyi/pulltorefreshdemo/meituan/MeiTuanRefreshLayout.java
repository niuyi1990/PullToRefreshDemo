package com.niuyi.pulltorefreshdemo.meituan;

import android.content.Context;
import android.util.AttributeSet;

import com.niuyi.pulltorefreshdemo.jd.MyJdRefreshHeader;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 作者：${牛毅}
 * 时间：2017/03/15 15：01
 * 邮箱：niuyi19900923@gmail.com
 * 描述：美团外卖的下拉刷新布局
 */
public class MeiTuanRefreshLayout extends PtrFrameLayout {

    public MeiTuanRefreshLayout(Context context) {
        super(context);
        initHearder();
    }

    public MeiTuanRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHearder();
    }

    public MeiTuanRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initHearder();
    }

    private void initHearder() {
        MeiTuanRefreshHerader header = new MeiTuanRefreshHerader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
    }
}
