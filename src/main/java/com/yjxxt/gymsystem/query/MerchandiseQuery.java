package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;

public class MerchandiseQuery extends BaseQuery {
    private String productName;//商品名称

    @Override
    public String toString() {
        return "MerchandiseQuery{" +
                "productName='" + productName + '\'' +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public MerchandiseQuery() {
    }
}
