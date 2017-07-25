
package com.donut.server.service;



import com.donut.server.common.PageBean;
import com.donut.server.dao.mapper.A01UserMapper;
import com.donut.server.model.UserInfoModel;
import com.donut.server.model.UserMgModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserMgService
{
    @Resource
    private A01UserMapper a01UserMapper;



    public void selectUserList(UserMgModel userMgModel)
    {
        try
        {

            PageBean<UserInfoModel> pageBean = new PageBean<UserInfoModel>();
            pageBean.setForm("userListForm");
            pageBean.setAction("selectUserList?");
            pageBean.setRecordsPerPage(10);
            pageBean.setPageIndex(userMgModel.getPageIndex());

            Long size = 0L;
            size=a01UserMapper.selectUserTotals(userMgModel);
            pageBean.setCount(size);


            userMgModel.setRecordPerPages(pageBean.getRecordsPerPage());
            userMgModel.setStartIndex(pageBean.getStart());
            List<UserInfoModel> orderInfoList =a01UserMapper.selectUserList(userMgModel);
            userMgModel.setUserInfoList(orderInfoList);
            userMgModel.setPageDisplay(pageBean.getPageHtml());
            userMgModel.setPageIndex(pageBean.getPageIndex());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
