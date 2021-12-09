package com.yjxxt.gymsystem.bean;

public class MemberType {
    private Integer typeId;

    private String typeName;

    private String typeDay;

    private String typeMoney;

    private Integer isValid;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeDay() {
        return typeDay;
    }

    public void setTypeDay(String typeDay) {
        this.typeDay = typeDay == null ? null : typeDay.trim();
    }

    public String getTypeMoney() {
        return typeMoney;
    }

    public void setTypeMoney(String typeMoney) {
        this.typeMoney = typeMoney == null ? null : typeMoney.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}