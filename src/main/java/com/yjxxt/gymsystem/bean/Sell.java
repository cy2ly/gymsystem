package com.yjxxt.gymsystem.bean;

public class Sell {
    private Integer sellId;

    private String sellName;

    private Integer sellAmount;

    private Double sellPrice;

    private String sellRemark;

    private Integer isVaild;

    private Integer productId;

    @Override
    public String toString() {
        return "Sell{" +
                "sellId=" + sellId +
                ", sellName='" + sellName + '\'' +
                ", sellAmount=" + sellAmount +
                ", sellPrice=" + sellPrice +
                ", sellRemark='" + sellRemark + '\'' +
                ", isVaild=" + isVaild +
                ", productId=" + productId +
                '}';
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName == null ? null : sellName.trim();
    }

    public Integer getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(Integer sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellRemark() {
        return sellRemark;
    }

    public void setSellRemark(String sellRemark) {
        this.sellRemark = sellRemark == null ? null : sellRemark.trim();
    }


    public Integer getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(Integer isVaild) {
        this.isVaild = isVaild;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}