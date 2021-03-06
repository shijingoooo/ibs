package com.capinfo.framework.web.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MonitoringHourData {
    private Integer id;
    private Integer projectId;

    private BigDecimal actualTsp;

    private BigDecimal actualTwoPm;

    private BigDecimal actualTenPm;

    private BigDecimal actualNoise;

    private String actual02;

    private String actualNO2;

    private String actual03;

    private String actual04;

    private String actualAQI;

    private String actualIAQIPM10;

    private String actualIAQIPM25;

    private String actualIAQI02;

    private String actualIAQI03;

    private String actualIAQI04;

    private String actualIAQINO2;

    private BigDecimal actualTemperature;

    private Date colTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getActualTsp() {
        return actualTsp;
    }

    public void setActualTsp(BigDecimal actualTsp) {
        this.actualTsp = actualTsp;
    }

    public BigDecimal getActualTwoPm() {
        return actualTwoPm;
    }

    public void setActualTwoPm(BigDecimal actualTwoPm) {
        this.actualTwoPm = actualTwoPm;
    }

    public BigDecimal getActualTenPm() {
        return actualTenPm;
    }

    public void setActualTenPm(BigDecimal actualTenPm) {
        this.actualTenPm = actualTenPm;
    }

    public BigDecimal getActualNoise() {
        return actualNoise;
    }

    public void setActualNoise(BigDecimal actualNoise) {
        this.actualNoise = actualNoise;
    }

    public String getActual02() {
        return actual02;
    }

    public void setActual02(String actual02) {
        this.actual02 = actual02;
    }

    public String getActualNO2() {
        return actualNO2;
    }

    public void setActualNO2(String actualNO2) {
        this.actualNO2 = actualNO2;
    }

    public String getActual03() {
        return actual03;
    }

    public void setActual03(String actual03) {
        this.actual03 = actual03;
    }

    public String getActual04() {
        return actual04;
    }

    public void setActual04(String actual04) {
        this.actual04 = actual04;
    }

    public String getActualAQI() {
        return actualAQI;
    }

    public void setActualAQI(String actualAQI) {
        this.actualAQI = actualAQI;
    }

    public String getActualIAQIPM10() {
        return actualIAQIPM10;
    }

    public void setActualIAQIPM10(String actualIAQIPM10) {
        this.actualIAQIPM10 = actualIAQIPM10;
    }

    public String getActualIAQIPM25() {
        return actualIAQIPM25;
    }

    public void setActualIAQIPM25(String actualIAQIPM25) {
        this.actualIAQIPM25 = actualIAQIPM25;
    }

    public String getActualIAQI02() {
        return actualIAQI02;
    }

    public void setActualIAQI02(String actualIAQI02) {
        this.actualIAQI02 = actualIAQI02;
    }

    public String getActualIAQI03() {
        return actualIAQI03;
    }

    public void setActualIAQI03(String actualIAQI03) {
        this.actualIAQI03 = actualIAQI03;
    }

    public String getActualIAQI04() {
        return actualIAQI04;
    }

    public void setActualIAQI04(String actualIAQI04) {
        this.actualIAQI04 = actualIAQI04;
    }

    public String getActualIAQINO2() {
        return actualIAQINO2;
    }

    public void setActualIAQINO2(String actualIAQINO2) {
        this.actualIAQINO2 = actualIAQINO2;
    }

    public BigDecimal getActualTemperature() {
        return actualTemperature;
    }

    public void setActualTemperature(BigDecimal actualTemperature) {
        this.actualTemperature = actualTemperature;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }
}