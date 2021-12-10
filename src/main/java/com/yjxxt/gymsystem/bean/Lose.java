package com.yjxxt.gymsystem.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Lose {
    private Integer loseId;

    private String loseName;

    private String loseImages;

    private String loseAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loseJdate;

    private Integer loseStatus;

    private String scAvenger;

    private Date loseLdate;

    private Integer isValid;

    public Integer getLoseId() {
        return loseId;
    }

    public void setLoseId(Integer loseId) {
        this.loseId = loseId;
    }

    public String getLoseName() {
        return loseName;
    }

    public void setLoseName(String loseName) {
        this.loseName = loseName == null ? null : loseName.trim();
    }

    public String getLoseImages() {
        return loseImages;
    }

    public void setLoseImages(String loseImages) {
        this.loseImages = loseImages == null ? null : loseImages.trim();
    }

    public String getLoseAddress() {
        return loseAddress;
    }

    public void setLoseAddress(String loseAddress) {
        this.loseAddress = loseAddress == null ? null : loseAddress.trim();
    }

    public Date getLoseJdate() {
        return loseJdate;
    }

    public void setLoseJdate(Date loseJdate) {
        this.loseJdate = loseJdate;
    }

    public Integer getLoseStatus() {
        return loseStatus;
    }

    public void setLoseStatus(Integer loseStatus) {
        this.loseStatus = loseStatus;
    }

    public String getScAvenger() {
        return scAvenger;
    }

    public void setScAvenger(String scAvenger) {
        this.scAvenger = scAvenger == null ? null : scAvenger.trim();
    }

    public Date getLoseLdate() {
        return loseLdate;
    }

    public void setLoseLdate(Date loseLdate) {
        this.loseLdate = loseLdate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Lose{" +
                "loseId=" + loseId +
                ", loseName='" + loseName + '\'' +
                ", loseImages='" + loseImages + '\'' +
                ", loseAddress='" + loseAddress + '\'' +
                ", loseJdate=" + loseJdate +
                ", loseStatus=" + loseStatus +
                ", scAvenger='" + scAvenger + '\'' +
                ", loseLdate=" + loseLdate +
                ", isValid=" + isValid +
                '}';
    }
}