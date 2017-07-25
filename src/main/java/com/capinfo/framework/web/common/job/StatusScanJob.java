package com.capinfo.framework.web.common.job;

import com.capinfo.framework.common.mail.AccountEmailService;
import com.capinfo.framework.web.service.MonitoringDeviceGroupService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class StatusScanJob {


    @Resource
    private MonitoringDeviceGroupService deviceService;
    @Resource
    private AccountEmailService accountEmailService;

    @Value("${manager.email1}")
    private String managerEmailFir;
    @Value("${manager.email2}")
    private String managerEmailSec;
    @Value("${manager.phone1}")
    private String managerPhoneFir;
    @Value("${manager.phone2}")
    private String managerPhoneSec;


}
