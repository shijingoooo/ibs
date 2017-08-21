package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.GroupDeviceMapper;
import com.capinfo.framework.web.pojo.GroupDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="groupDeviceService")
public class GroupDeviceService {
    @Autowired
    private GroupDeviceMapper groupDeviceMapper;

    /**根据设备组id获取设备组信息*/
    public GroupDevice findGroupDevice(Integer deviceGroupId) throws Exception {
        GroupDevice groupDevice = groupDeviceMapper.findGroupDevice(deviceGroupId);
        return groupDevice;
    }

}
