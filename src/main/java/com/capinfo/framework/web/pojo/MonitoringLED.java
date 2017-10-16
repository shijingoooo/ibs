package com.capinfo.framework.web.pojo;

public class MonitoringLED {
    private Integer id;
    private String IdDev;

    private Integer idDevice;

    private String ipSvr;

    private Integer portSvr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdDev() {
        return IdDev;
    }

    public void setIdDev(String idDev) {
        IdDev = idDev;
    }

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public String getIpSvr() {
        return ipSvr;
    }

    public void setIpSvr(String ipSvr) {
        this.ipSvr = ipSvr;
    }

    public Integer getPortSvr() {
        return portSvr;
    }

    public void setPortSvr(Integer portSvr) {
        this.portSvr = portSvr;
    }
}