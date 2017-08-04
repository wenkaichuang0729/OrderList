package com.example.vpcsd.orderlistlogic.bean;

/**
 * Created by wenkaichuang
 */

/**
 * orderDetailList字段的每一项
 */
public class OrderGoodsItem {

    private String productName;
    private String productPic;
    private int count;
    private double totalPrice;
    private int orderid;

    private OrderGoodsInfo order;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderGoodsInfo getOrder() {
        return order;
    }

    public void setOrder(OrderGoodsInfo order) {
        this.order = order;
    }
}
