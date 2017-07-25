package com.donut.server.dao.mapper;

import com.donut.server.dao.model.A01User;
import com.donut.server.model.UserInfoModel;
import com.donut.server.model.UserMgModel;

import java.util.List;

public interface A01UserMapper
{
    int deleteByPrimaryKey(String uuid);

    int insert(A01User record);

    int insertSelective(A01User record);

    A01User selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(A01User record);

    int updateByPrimaryKey(A01User record);

    Long selectUserTotals(UserMgModel userMgModel);
    List<UserInfoModel> selectUserList(UserMgModel userMgModel);

}