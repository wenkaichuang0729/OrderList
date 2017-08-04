package com.example.vpcsd.orderlistlogic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vpcsd.orderlistlogic.R;
import com.example.vpcsd.orderlistlogic.adapter.rcadapter.RcCommonAdapter;
import com.example.vpcsd.orderlistlogic.adapter.rcadapter.RcViewHolder;
import com.example.vpcsd.orderlistlogic.bean.OrderGoodsInfo;
import com.example.vpcsd.orderlistlogic.bean.OrderGoodsItem;
import com.example.vpcsd.orderlistlogic.bean.OrderPayInfo;

import java.util.List;

/**
 * Created by wenkaichuang
 */

public class OrderAdapter extends RcCommonAdapter<Object> {

    private Context context;
    private List<Object> data;
    private int ITEM_HEADER = 1,ITEM_CONTENT = 2,ITEM_FOOTER = 3;

    public OrderAdapter(Context context, List<Object> data){
        super(context, 1, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public RcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == ITEM_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_header, parent, false);
        }else if(viewType == ITEM_CONTENT){
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_content, parent, false);
        }else if(viewType == ITEM_FOOTER){
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_footer, parent, false);
        }
        return new RcViewHolder(context,view);
    }


    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof OrderGoodsInfo) {
            return ITEM_HEADER;
        }else if(data.get(position) instanceof OrderGoodsItem){
            return ITEM_CONTENT;
        }else if(data.get(position) instanceof OrderPayInfo){
            return ITEM_FOOTER;
        }
        return ITEM_CONTENT;
    }

    @Override
    protected void convert(RcViewHolder holder, Object data, final int position) {
        if(holder.getItemViewType()==ITEM_HEADER){
            OrderGoodsInfo datas = (OrderGoodsInfo)data;
            holder.setText(R.id.tv_orderno,"订单编号：" + datas.getOrderCode())
                  .setText(R.id.tv_shopname,datas.getShopName());
            if(datas.getStatus().equals("0")){
                holder.setText(R.id.tv_state,"待付款");
            }else if(datas.getStatus().equals("1")){
                holder.setText(R.id.tv_state,"待发货");
            }else if(datas.getStatus().equals("2")){
                holder.setText(R.id.tv_state,"待收货");
            }else if(datas.getStatus().equals("3")){
                holder.setText(R.id.tv_state,"交易完成");
            }
        }else if(holder.getItemViewType()==ITEM_CONTENT) {
            OrderGoodsItem datas = (OrderGoodsItem)data;
            holder.setGlidePic(context,R.id.iv_pic,datas.getProductPic())
                  .setText(R.id.tv_title,datas.getProductName())
                  .setText(R.id.tv_num,"共" + datas.getCount() + "件")
                  .setText(R.id.tv_price,"¥" + datas.getTotalPrice());

            final int pos = datas.getOrderid();
            final String name = datas.getProductName();

            holder.setOnClickListener(R.id.ll,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,name,Toast.LENGTH_SHORT).show();
                }
            });

        }else if(holder.getItemViewType()==ITEM_FOOTER) {
            OrderPayInfo datas = (OrderPayInfo)data;
            holder.setText(R.id.tv_total,datas.getTotalAmount() + "");
            final int pos = datas.getId();
            if(datas.getStatus().equals("0")){
                holder.setText(R.id.tv_submit,"付款");
            }else if (datas.getStatus().equals("2")){
                holder.setText(R.id.tv_submit,"确认收货");
            }else {
                holder.setText(R.id.tv_submit,"再次购买");
            }
            final String status = datas.getStatus();
            holder.setOnClickListener(R.id.tv_submit,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnBtClickListener.onBtClick(status,position);
                }
            });
        }
    }

    private OnBtClickListener mOnBtClickListener;
    public interface OnBtClickListener{
        void onBtClick(String status,int position);
    }

    public void setOnBtClickListener(OnBtClickListener mOnBtClickListener){
        this.mOnBtClickListener = mOnBtClickListener;
    }
}
