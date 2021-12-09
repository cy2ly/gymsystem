package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuery extends BaseQuery {
    private String courseName;
    private String coachName;
}
