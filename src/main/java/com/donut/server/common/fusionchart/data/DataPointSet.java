package com.donut.server.common.fusionchart.data;

import java.util.List;

/**
 * @ClassName: DataPointSet
 * @Description: XML键值对 多数据源
 * @author 赵峰剑
 * @date 2014年11月21日 上午9:56:42
 * @version 1.0
 */
public class DataPointSet
{
    /** 序列化名称 */
    private String seriesName;

    /** 颜色 */
    private String color;

    /** 数据集 */
    private List<DataPoint> dataPointList;

    /**
     * 序列化名称的设定
     *
     * @param seriesName
     *            序列化名称
     */
    public void setSeriesName(String seriesName)
    {
        this.seriesName = seriesName;
    }

    /**
     * 序列化名称的取得
     *
     * @return 序列化名称
     */
    public String getSeriesName()
    {
        return seriesName;
    }

    /**
     * 颜色的设定
     *
     * @param color
     *            颜色
     */
    public void setColor(String color)
    {
        this.color = color;
    }

    /**
     * 颜色的取得
     *
     * @return 颜色
     */
    public String getColor()
    {
        return color;
    }

    /**
     * 数据集的设定
     *
     * @param dataPointList
     *            数据集
     */
    public void setDataPointList(List<DataPoint> dataPointList)
    {
        this.dataPointList = dataPointList;
    }

    /**
     * 数据集的取得
     *
     * @return 数据集
     */
    public List<DataPoint> getDataPointList()
    {
        return dataPointList;
    }
}
