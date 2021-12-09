package com.yjxxt.gymsystem.bean;

public class Equipment {
    private Integer eqId;

    private String eqName;

    private String eqText;

    private String eqImage;

    private Integer eqNumber;

    private Integer isValid;

    public Integer getEqId() {
        return eqId;
    }

    public void setEqId(Integer eqId) {
        this.eqId = eqId;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName == null ? null : eqName.trim();
    }

    public String getEqText() {
        return eqText;
    }

    public void setEqText(String eqText) {
        this.eqText = eqText == null ? null : eqText.trim();
    }

    public String getEqImage() {
        return eqImage;
    }

    public void setEqImage(String eqImage) {
        this.eqImage = eqImage == null ? null : eqImage.trim();
    }

    public Integer getEqNumber() {
        return eqNumber;
    }

    public void setEqNumber(Integer eqNumber) {
        this.eqNumber = eqNumber;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }


    @Override
    public String toString() {
        return "Equipment{" +
                "eqId=" + eqId +
                ", eqName='" + eqName + '\'' +
                ", eqText='" + eqText + '\'' +
                ", eqImage='" + eqImage + '\'' +
                ", eqNumber=" + eqNumber +
                ", isValid=" + isValid +
                '}';
    }
}