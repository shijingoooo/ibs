package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.UserGroupRele;

import java.util.List;

public interface UserGroupReleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupRele record);

    int insertSelective(UserGroupRele record);

    UserGroupRele selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupRele record);

    int updateByPrimaryKey(UserGroupRele record);
    //根据用户ID删除联系
    int deleteByUserId(Integer id);

    List<UserGroupRele> findUserGroupReleList(Integer id);

    int findUserGroupCountByUserId(Integer id);
}