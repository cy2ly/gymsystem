package com.yjxxt.gymsystem.query;

import com.yjxxt.gymsystem.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoseQuery extends BaseQuery{
    private String loseName;

}
