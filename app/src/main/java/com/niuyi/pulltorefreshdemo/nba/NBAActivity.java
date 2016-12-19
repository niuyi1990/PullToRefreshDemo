package com.niuyi.pulltorefreshdemo.nba;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class NBAActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.nba_refresh_layout)
    MyNbaRefreshLayout mNbaRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba);
        ButterKnife.bind(this);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        mRecycler.setAdapter(adapter);

        mNbaRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        mNbaRefreshLayout.refreshComplete();
                        Toast.makeText(getApplication(), "刷新成功", Toast.LENGTH_SHORT).show();
                    }

                }, 3000);

            }
        });
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
        @Override
        public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba, parent, false));
        }

        @Override
        public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, int position) {

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

