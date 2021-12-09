package com.yjxxt.gymsystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Coach {
    private Integer coachId;

    private String coachName;

    private Integer coachAge;

    private Integer coachSex;

    private String coachPhone;

    private String coachAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date coachDate;

    private String coachYear;

    private String coachSalary;

    private Integer isValid;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName == null ? null : coachName.trim();
    }

    public Integer getCoachAge() {
        return coachAge;
    }

    public void setCoachAge(Integer coachAge) {
        this.coachAge = coachAge;
    }

    public Integer getCoachSex() {
        return coachSex;
    }

    public void setCoachSex(Integer coachSex) {
        this.coachSex = coachSex;
    }

    public String getCoachPhone() {
        return coachPhone;
    }

    public void setCoachPhone(String coachPhone) {
        this.coachPhone = coachPhone == null ? null : coachPhone.trim();
    }

    public String getCoachAddress() {
        return coachAddress;
    }

    public void setCoachAddress(String coachAddress) {
        this.coachAddress = coachAddress == null ? null : coachAddress.trim();
    }

    public Date getCoachDate() {
        return coachDate;
    }

    public void setCoachDate(Date coachDate) {
        this.coachDate = coachDate;
    }

    public String getCoachYear() {
        return coachYear;
    }

    public void setCoachYear(String coachYear) {
        this.coachYear = coachYear == null ? null : coachYear.trim();
    }

    public String getCoachSalary() {
        return coachSalary;
    }

    public void setCoachSalary(String coachSalary) {
        this.coachSalary = coachSalary == null ? null : coachSalary.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}