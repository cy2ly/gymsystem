package com.yjxxt.gymsystem.service;


import com.yjxxt.gymsystem.base.BaseService;
import com.yjxxt.gymsystem.bean.Permission;
import com.yjxxt.gymsystem.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;


    /**
     * 查询用户拥有的资源权限码
     * @param userId
     * @return
     */
    public List<String> queryUserHasRolesHasPermissions(Integer userId){
       return permissionMapper.selectUserHasRolesHasPermissions(userId);
    }

}
