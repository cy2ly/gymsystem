package com.yjxxt.gymsystem.query;


import com.yjxxt.gymsystem.base.BaseQuery;

public class MemberTypeQuery extends BaseQuery {
    private String memberTypeName;

    public MemberTypeQuery() {
    }

    public MemberTypeQuery(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    @Override
    public String toString() {
        return "MemberTypeQuery{" +
                "memberTypeName='" + memberTypeName + '\'' +
                '}';
    }
}
