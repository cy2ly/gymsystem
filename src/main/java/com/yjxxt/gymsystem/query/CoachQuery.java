package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachQuery extends BaseQuery {
    private String coachName;//教练名称
    private Integer coachSex;//教练性别
    private Integer minYear;//教龄范围min
    private Integer maxYear;//教龄范围max
}
