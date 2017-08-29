package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.GroupDevice;

import java.util.List;

public interface GroupDeviceMapper {

    GroupDevice findGroupDevice(Integer groupDeviceId);

    List<Integer> findDevices(GroupDevice groupDevice);
}
