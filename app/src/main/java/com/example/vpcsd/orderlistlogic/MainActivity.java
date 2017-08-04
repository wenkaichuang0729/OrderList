package com.example.vpcsd.orderlistlogic;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vpcsd.orderlistlogic.adapter.OrderAdapter;
import com.example.vpcsd.orderlistlogic.adapter.rcadapter.RcLoadmoreWrapper;
import com.example.vpcsd.orderlistlogic.bean.AllOrderBean;
import com.example.vpcsd.orderlistlogic.bean.OrderDataHelper;
import com.example.vpcsd.orderlistlogic.bean.OrderGoodsInfo;
import com.example.vpcsd.orderlistlogic.bean.OrderGoodsItem;
import com.example.vpcsd.orderlistlogic.bean.OrderSummary;
import com.example.vpcsd.orderlistlogic.bean.TestData;

import java.util.ArrayList;
import java.util.List;

/*
 * wenkaichuang  
 * 优化列表嵌套列表数量多时造成卡顿
 * 这里Adapter是用张鸿洋封装的Adapter 使用方便
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rlvAllOrder;
    private OrderAdapter mAllOrderAdapter;
    private List<Object> mAllOrderList = new ArrayList<>();
    private RcLoadmoreWrapper mLoadMoreWrapper;
    private int page = 1;
    private AutoSwipeRefreshLayout autoSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlvAllOrder = (RecyclerView) findViewById(R.id.rlv_allorder);
        autoSwipeRefreshLayout = (AutoSwipeRefreshLayout) findViewById(R.id.asrl);

        rlvAllOrder.setLayoutManager(new LinearLayoutManager(this));
        mAllOrderAdapter = new OrderAdapter(this, mAllOrderList);
        mAllOrderAdapter.setOnBtClickListener(new OrderAdapter.OnBtClickListener() {
            @Override
            public void onBtClick(String status, int position) {
                if (status.equals("0")) {
                    Toast.makeText(MainActivity.this, "付款", Toast.LENGTH_SHORT).show();
                } else if (status.equals("2")) {
                    Toast.makeText(MainActivity.this, "确认收货", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "再次购买", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //添加这个是给列表添加底部上拉加载更多
        mLoadMoreWrapper = new RcLoadmoreWrapper(mAllOrderAdapter);
        //设置上拉加载视图
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        //上拉加载更多监听
        mLoadMoreWrapper.setOnLoadMoreListener(new RcLoadmoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData(page);
                    }
                }, 1000);

            }
        });
        rlvAllOrder.setAdapter(mLoadMoreWrapper);
        //自动刷新
        autoSwipeRefreshLayout.autoRefresh();
        //添加刷新监听器
        autoSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAllOrderList.clear();
                page = 1;
                initData(page);
            }
        });


    }

    private void initData(int a) {
        //模拟请求回来的数据
        List<OrderSummary> data = TestData.getData(a);

        mAllOrderList.addAll(OrderDataHelper.getDataAfterHandle(data));
        page++;
        if (mLoadMoreWrapper != null) {

            // 开发只要回来的数据小于发送请求的pageSize就不显示加载更多
            /*if (data.size()<pageSize)
            {
                mLoadMoreWrapper.setLoadOver(false);
            } else {
                mLoadMoreWrapper.setLoadOver(true);
            }*/
            //判断什么时候不需要显示加载更多  这里只是测试用的   以实际为主
            if (mAllOrderList.size() >= 20) {
                mLoadMoreWrapper.setLoadOver(false);
            } else {
                mLoadMoreWrapper.setLoadOver(true);
            }
        }
        mLoadMoreWrapper.notifyDataSetChanged();
        //关闭下拉刷新
        autoSwipeRefreshLayout.setRefreshing(false);
    }
}
