package com.example.vpcsd.orderlistlogic.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenkaichuang on 17-8-4.
 */

public class TestData {

    public static List<OrderSummary> getData(int a) {
        //模拟请求回来的数据
        AllOrderBean allOrderBean = new AllOrderBean();
        OrderSummary orderSummary1 = new OrderSummary();
        OrderSummary orderSummary2 = new OrderSummary();
        OrderGoodsItem orderGoodsItem1 = new OrderGoodsItem();
        OrderGoodsItem orderGoodsItem2 = new OrderGoodsItem();
        OrderGoodsItem orderGoodsItem3 = new OrderGoodsItem();
        OrderGoodsItem orderGoodsItem4 = new OrderGoodsItem();
        OrderGoodsInfo orderGoodsInfo1 = new OrderGoodsInfo();
        OrderGoodsInfo orderGoodsInfo2 = new OrderGoodsInfo();
        OrderGoodsInfo orderGoodsInfo3 = new OrderGoodsInfo();
        OrderGoodsInfo orderGoodsInfo4 = new OrderGoodsInfo();

        orderSummary1.setId(1);
        orderSummary1.setTotalPrice(7015);
        orderSummary1.setStatus("0");
        orderSummary1.setOrderCode("201708040" + a);
        orderGoodsInfo1.setOrderCode("201708040" + a);
        orderGoodsInfo1.setStatus("0");
        orderGoodsInfo1.setShopName("电脑城");
        orderGoodsItem1.setTotalPrice(7000);
        orderGoodsItem1.setProductName("苹果笔记本");
        orderGoodsItem1.setCount(1);
        orderGoodsItem1.setProductPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501820895003&di=78d8b526e086eda96232936246ce0e31&imgtype=0&src=http%3A%2F%2Fshanxiji.sinaimg.cn%2F2013%2F0603%2FU9080P1335DT20130603214319.jpg");
        orderGoodsItem1.setOrder(orderGoodsInfo1);

        orderGoodsInfo2.setOrderCode("201708040" + (a + 10));
        orderGoodsInfo2.setStatus("0");
        orderGoodsInfo2.setShopName("水果园");
        orderGoodsItem2.setTotalPrice(5);
        orderGoodsItem2.setProductName("西瓜");
        orderGoodsItem2.setCount(2);
        orderGoodsItem2.setProductPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501821235521&di=b398e40d73398fdd38427b3ce60ccf73&imgtype=0&src=http%3A%2F%2Fwww.baicaolu.com%2Fuploads%2F201511%2F1448082448fOrJM57d.jpg");
        orderGoodsItem2.setOrder(orderGoodsInfo2);

        orderGoodsInfo4.setOrderCode("201708040" + (a + 10));
        orderGoodsInfo4.setStatus("0");
        orderGoodsInfo4.setShopName("水果园");
        orderGoodsItem4.setTotalPrice(5);
        orderGoodsItem4.setProductName("圣女果");
        orderGoodsItem4.setCount(1);
        orderGoodsItem4.setProductPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501821235519&di=22832624f99545b251a7c681dd38aa55&imgtype=0&src=http%3A%2F%2Fpic28.nipic.com%2F20130330%2F10191009_195341527129_2.jpg");
        orderGoodsItem4.setOrder(orderGoodsInfo4);

        List<OrderGoodsItem> orderGoodsItemList = new ArrayList<>();
        orderGoodsItemList.add(orderGoodsItem1);
        orderGoodsItemList.add(orderGoodsItem4);
        orderGoodsItemList.add(orderGoodsItem2);
        orderSummary1.setOrderDetailList(orderGoodsItemList);

        orderSummary2.setId(2);
        orderSummary2.setTotalPrice(88);
        orderSummary2.setStatus("2");
        orderSummary2.setOrderCode("201708040" + (a + 20));
        orderGoodsInfo3.setOrderCode("201708040" + (a + 20));
        orderGoodsInfo3.setStatus("2");
        orderGoodsInfo3.setShopName("蛋糕店");
        orderGoodsItem3.setTotalPrice(88);
        orderGoodsItem3.setProductName("黑白蛋糕");
        orderGoodsItem3.setCount(1);
        orderGoodsItem3.setProductPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501821235514&di=53cc01cdd1eefdea29ef0df1714f07d4&imgtype=0&src=http%3A%2F%2Fuploads.xuexila.com%2Fallimg%2F1701%2F859-1F106113117-50.jpg");
        orderGoodsItem3.setOrder(orderGoodsInfo3);

        List<OrderGoodsItem> orderGoodsItemList1 = new ArrayList<>();
        orderGoodsItemList1.add(orderGoodsItem3);
        orderSummary2.setOrderDetailList(orderGoodsItemList1);

        List<OrderSummary> orderSummaries = new ArrayList<>();
        orderSummaries.add(orderSummary1);
        orderSummaries.add(orderSummary2);
        allOrderBean.setResultList(orderSummaries);
        return allOrderBean.getResultList();
    }
}
