package com.niuyi.pulltorefreshdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.niuyi.pulltorefreshdemo.acfun.AcFunActivity;
import com.niuyi.pulltorefreshdemo.meituan.MeiTuanActivity;
import com.niuyi.pulltorefreshdemo.tudou.ToDouActivity;
import com.niuyi.pulltorefreshdemo.jd.JDActivity;
import com.niuyi.pulltorefreshdemo.nba.NBAActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_jd)
    Button mBtnJd;
    @BindView(R.id.btn_nba)
    Button mBtnNba;
    @BindView(R.id.btn_todou)
    Button mBtnIt;
    @BindView(R.id.btn_acfun)
    Button mBtnAcfun;
    private Button mBtnJD;
    private Button mBtnNBA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_jd, R.id.btn_nba, R.id.btn_todou, R.id.btn_acfun, R.id.btn_meituan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jd:
                startActivity(new Intent(this, JDActivity.class));
                break;
            case R.id.btn_nba:
                startActivity(new Intent(this, NBAActivity.class));
                break;
            case R.id.btn_todou:
                startActivity(new Intent(this, ToDouActivity.class));
                break;
            case R.id.btn_acfun:
                startActivity(new Intent(this, AcFunActivity.class));
                break;
            case R.id.btn_meituan:
                startActivity(new Intent(this, MeiTuanActivity.class));
                break;
        }
    }
}
