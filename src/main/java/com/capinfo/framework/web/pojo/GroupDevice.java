package com.capinfo.framework.web.pojo;

import java.util.Date;

public class GroupDevice {

    private Integer id;
    private String group_dev_name;
    private String group_dev_type;
    private String group_dev_description;
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup_dev_name() {
        return group_dev_name;
    }

    public void setGroup_dev_name(String group_dev_name) {
        this.group_dev_name = group_dev_name;
    }

    public String getGroup_dev_type() {
        return group_dev_type;
    }

    public void setGroup_dev_type(String group_dev_type) {
        this.group_dev_type = group_dev_type;
    }

    public String getGroup_dev_description() {
        return group_dev_description;
    }

    public void setGroup_dev_description(String group_dev_description) {
        this.group_dev_description = group_dev_description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
