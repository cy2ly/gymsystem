package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;
//查询表
public class SellQuery extends BaseQuery {
    private Integer productId;//商品编号

    @Override
    public String toString() {
        return "SellQuery{" +
                "productId=" + productId +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public SellQuery() {
    }
}
