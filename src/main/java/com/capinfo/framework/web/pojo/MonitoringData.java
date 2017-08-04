package com.capinfo.framework.web.pojo;

import java.util.Date;

/**
 * 检测数据
 *
 * @author kevin_zhan
 */
public class MonitoringData {


    protected Integer id;

    // 粉尘 实际测量值
    protected Float actual_tsp;

    // 校准值
    protected Float calibration_tsp;

    // pm2.5
    protected Float actual_two_pm;

    // 校准值
    protected Float calibration_two_pm;

    // pm10
    protected Float actual_ten_pm;

    // 校准值
    protected Float calibration_ten_pm;

    // 噪声
    protected Float actual_noise;

    // 校准值
    protected Float calibration_noise;

    // 背景噪声
    protected Float actual_bg_noise;

    // 校准值
    protected Float calibration_bg_noise;

    // 最大声值级别
    protected Integer actual_max_level;

    // 校准值
    protected Integer calibration_max_level;

    // 测前仪器校准值
    protected Float before_dev_calibration;

    // 测后仪器校准值
    protected Float after_dev_calibration;

    // 温度
    protected Float actual_temperature;

    // 湿度
    protected Float actual_humidity;

    // 气压
    protected Integer actual_pressure;

    // 降雨量
    protected Float actual_rainfall;

    // 是否下雨
    protected Integer is_rain;

    // 风速
    protected Float actual_wind_speed;

    // 风向 按360度划分，0° 北向，90°东向，180°南向，270°西向
    protected Integer actual_wind_direction;

    // 电池电量
    protected Integer electric_quantity;
    // 采集时间
    protected Date col_time;

    protected Integer long_time;

    // 数据标示
    protected String data_sign;

    protected Integer creater;

    protected Integer updater;

    protected Date create_time;

    protected Date update_time;

    protected Integer version;

    protected Integer sh_sign;

    protected Integer device_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getActual_tsp() {
        return actual_tsp;
    }

    public void setActual_tsp(Float actual_tsp) {
        this.actual_tsp = actual_tsp;
    }

    public Float getCalibration_tsp() {
        return calibration_tsp;
    }

    public void setCalibration_tsp(Float calibration_tsp) {
        this.calibration_tsp = calibration_tsp;
    }

    public Float getActual_two_pm() {
        return actual_two_pm;
    }

    public void setActual_two_pm(Float actual_two_pm) {
        this.actual_two_pm = actual_two_pm;
    }

    public Float getCalibration_two_pm() {
        return calibration_two_pm;
    }

    public void setCalibration_two_pm(Float calibration_two_pm) {
        this.calibration_two_pm = calibration_two_pm;
    }

    public Float getActual_ten_pm() {
        return actual_ten_pm;
    }

    public void setActual_ten_pm(Float actual_ten_pm) {
        this.actual_ten_pm = actual_ten_pm;
    }

    public Float getCalibration_ten_pm() {
        return calibration_ten_pm;
    }

    public void setCalibration_ten_pm(Float calibration_ten_pm) {
        this.calibration_ten_pm = calibration_ten_pm;
    }

    public Float getActual_noise() {
        return actual_noise;
    }

    public void setActual_noise(Float actual_noise) {
        this.actual_noise = actual_noise;
    }

    public Float getCalibration_noise() {
        return calibration_noise;
    }

    public void setCalibration_noise(Float calibration_noise) {
        this.calibration_noise = calibration_noise;
    }

    public Float getActual_bg_noise() {
        return actual_bg_noise;
    }

    public void setActual_bg_noise(Float actual_bg_noise) {
        this.actual_bg_noise = actual_bg_noise;
    }

    public Float getCalibration_bg_noise() {
        return calibration_bg_noise;
    }

    public void setCalibration_bg_noise(Float calibration_bg_noise) {
        this.calibration_bg_noise = calibration_bg_noise;
    }

    public Integer getActual_max_level() {
        return actual_max_level;
    }

    public void setActual_max_level(Integer actual_max_level) {
        this.actual_max_level = actual_max_level;
    }

    public Integer getCalibration_max_level() {
        return calibration_max_level;
    }

    public void setCalibration_max_level(Integer calibration_max_level) {
        this.calibration_max_level = calibration_max_level;
    }

    public Float getBefore_dev_calibration() {
        return before_dev_calibration;
    }

    public void setBefore_dev_calibration(Float before_dev_calibration) {
        this.before_dev_calibration = before_dev_calibration;
    }

    public Float getAfter_dev_calibration() {
        return after_dev_calibration;
    }

    public void setAfter_dev_calibration(Float after_dev_calibration) {
        this.after_dev_calibration = after_dev_calibration;
    }

    public Float getActual_temperature() {
        return actual_temperature;
    }

    public void setActual_temperature(Float actual_temperature) {
        this.actual_temperature = actual_temperature;
    }

    public Float getActual_humidity() {
        return actual_humidity;
    }

    public void setActual_humidity(Float actual_humidity) {
        this.actual_humidity = actual_humidity;
    }

    public Integer getActual_pressure() {
        return actual_pressure;
    }

    public void setActual_pressure(Integer actual_pressure) {
        this.actual_pressure = actual_pressure;
    }

    public Float getActual_rainfall() {
        return actual_rainfall;
    }

    public void setActual_rainfall(Float actual_rainfall) {
        this.actual_rainfall = actual_rainfall;
    }

    public Integer getIs_rain() {
        return is_rain;
    }

    public void setIs_rain(Integer is_rain) {
        this.is_rain = is_rain;
    }

    public Float getActual_wind_speed() {
        return actual_wind_speed;
    }

    public void setActual_wind_speed(Float actual_wind_speed) {
        this.actual_wind_speed = actual_wind_speed;
    }

    public Integer getActual_wind_direction() {
        return actual_wind_direction;
    }

    public void setActual_wind_direction(Integer actual_wind_direction) {
        this.actual_wind_direction = actual_wind_direction;
    }

    public Integer getElectric_quantity() {
        return electric_quantity;
    }

    public void setElectric_quantity(Integer electric_quantity) {
        this.electric_quantity = electric_quantity;
    }

    public Date getCol_time() {
        return col_time;
    }

    public void setCol_time(Date col_time) {
        this.col_time = col_time;
    }

    public Integer getLong_time() {
        return long_time;
    }

    public void setLong_time(Integer long_time) {
        this.long_time = long_time;
    }

    public String getData_sign() {
        return data_sign;
    }

    public void setData_sign(String data_sign) {
        this.data_sign = data_sign;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSh_sign() {
        return sh_sign;
    }

    public void setSh_sign(Integer sh_sign) {
        this.sh_sign = sh_sign;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }
}
