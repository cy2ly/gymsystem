package com.yjxxt.gymsystem.query;


import com.yjxxt.gymsystem.base.BaseQuery;

/**
 * @Author Student
 * @date 2021/9/16 15:21
 */
public class MemberQuery extends BaseQuery {
    private String memberName;
    private Integer memberType;

    public MemberQuery() {
    }

    public MemberQuery(String memberName, Integer memerType) {
        this.memberName = memberName;
        this.memberType = memerType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemerType(Integer memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "MemberQuery{" +
                "memberName='" + memberName + '\'' +
                ", memerType=" + memberType +
                '}';
    }
}
