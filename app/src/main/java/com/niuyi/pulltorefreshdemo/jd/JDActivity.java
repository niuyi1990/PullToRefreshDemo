package com.niuyi.pulltorefreshdemo.jd;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.niuyi.pulltorefreshdemo.R;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class JDActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyJdRefreshLayout mRefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);

        mRefreshlayout = (MyJdRefreshLayout) findViewById(R.id.refreshlayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);

        mRefreshlayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        mRefreshlayout.refreshComplete();
                        Toast.makeText(getApplication(), "刷新成功", Toast.LENGTH_SHORT).show();
                    }

                }, 3000);

            }
        });
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jd, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 15;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
