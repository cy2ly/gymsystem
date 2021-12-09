package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;

public class EquipmentQuery extends BaseQuery {
    private String eqName;
    private String eqText;
    private String eqImage;

    public EquipmentQuery() {
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqText() {
        return eqText;
    }

    public void setEqText(String eqText) {
        this.eqText = eqText;
    }

    public String getEqImage() {
        return eqImage;
    }

    public void setEqImage(String eqImage) {
        this.eqImage = eqImage;
    }
}
