package com.example.vpcsd.orderlistlogic.adapter.rcadapter;


/**
 * Created by zhy on 16/6/22.
 */
public interface RcItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(RcViewHolder holder, T t, int position);

}
