package com.niuyi.pulltorefreshdemo.acfun;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.niuyi.pulltorefreshdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class AcFunActivity extends AppCompatActivity {

    @BindView(R.id.acfun_refreshlayout)
    MyAcFunRefreshLayout mAcfunRefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_fun);
        ButterKnife.bind(this);


        mAcfunRefreshlayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        mAcfunRefreshlayout.refreshComplete();
                        Toast.makeText(getApplication(), "刷新成功", Toast.LENGTH_SHORT).show();
                    }

                }, 3000);

            }
        });
    }

}
