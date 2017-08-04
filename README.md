# OrderList
</br>优化订单列表,大量数据也不会造成卡顿
</br>使用AutoSwipeRefreshLayout+RecyclerView+通用Adapter(RecyclerView)
</br>这里Adapter是用张鸿洋封装的Adapter 使用方便
</br>List<Object>有三种数据类型：
</br>1、OrderGoodsInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
</br>2、OrderGoodsItem 表示小订单中的商品
</br>3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）

</br>将列表一个item分成3个部分(布局一般是固定的)
</br>然后通过Adapter根据不同部分来加载不同布局级数据
</br>具体实现:
</br>返回ViewType
```java
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
    
加载不同视图
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

加载数据(convert 该方法是封装好的Adapter)
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
```
