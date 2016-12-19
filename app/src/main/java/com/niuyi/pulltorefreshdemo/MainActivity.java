package com.niuyi.pulltorefreshdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.niuyi.pulltorefreshdemo.jd.JDActivity;
import com.niuyi.pulltorefreshdemo.nba.NBAActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnJD;
    private Button mBtnNBA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnJD = (Button) findViewById(R.id.btn_jd);
        mBtnNBA = (Button) findViewById(R.id.btn_nba);

        mBtnJD.setOnClickListener(this);
        mBtnNBA.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jd:
                startActivity(new Intent(this, JDActivity.class));
                break;
            case R.id.btn_nba:
                startActivity(new Intent(this, NBAActivity.class));

                break;
        }
    }
}
