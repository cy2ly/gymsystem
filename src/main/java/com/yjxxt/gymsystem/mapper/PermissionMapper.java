package com.yjxxt.gymsystem.mapper;


import com.yjxxt.gymsystem.base.BaseMapper;
import com.yjxxt.gymsystem.bean.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    int countRoleMudulesByRoleId(Integer roleId);

    int deleteRoleModuleByRoleId(Integer roleId);


    List<Integer> selectModelByRoleId(Integer roleId);



    List<String> selectUserHasRolesHasPermissions(Integer userId);

    int countPermissionsByModuleId(Integer mid);

    int deletePermissionsByModuleId(Integer mid);
}