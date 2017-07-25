package com.donut.server.common.fusionchart.data;

import java.util.List;

/**
 * @ClassName: ChartModel
 * @Description: funsionChart数据结构
 * @author 赵峰剑
 * @date 2014年11月21日 上午9:45:27
 * @version 1.0
 */
public class ChartModel
{
    /** 图表主标题 */
    private String caption;

    /** 图表副标题 */
    private String subcaption;

    /** 横向坐标轴(x轴)名称 */
    private String xAxisName;

    /** 纵向坐标轴(y轴)名称 */
    private String yAxisName;

    /** 指定纵轴(y轴)最小值，数字 */
    private long yAxisMinValue;

    /** 增加数字前缀 */
    private String numberPrefix;

    private String baseFontColor = "#bdc1c2";

    /** 是否在图表显示对应的数据值，默认为1(True) */
    private boolean showValues;

    /** 图表字体样式 */
    private String baseFont = "Tahoma";

    /** 图表字体大小 */
    private int baseFontSize = 12;

    /** 设置图表左边距 */
    private int chartLeftMargin;

    /** 设置图表右边距 */
    private int chartRightMargin;

    /** labelStep */
    private String labelStep;

    /** 水平分区线颜色，6位16进制颜色值 */
    private String divLineColor = "ffffff";

    /** 横向网格带交替的颜色，6位16进制颜色值 */
    private String alternateHGridColor = "CC3300";

    /** 图表背景色，6位16进制颜色值 */
    private String bgColor = "FFFFFF,CC3300";

    /** Line的线颜色 */
    private String lineColor = "FCB541";

    /** MSLine */
    private List<String> categoryList;

    /** 一个图一组数据 */
    private List<DataPoint> dataList;

    /** 一个图多组数据 */
    private List<DataPointSet> dataPointSetList;

    /** 图标类型 */
    private ChartType chartType;

    /**
     * 图表主标题的设定
     * 
     * @param caption
     *            图表主标题
     */

    public void setBaseFontColor(String baseFontColor)
    {
        this.baseFontColor = baseFontColor;
    }

    public String getBaseFontColor()
    {
        return baseFontColor;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    /**
     * 图表主标题的取得
     * 
     * @return 图表主标题
     */
    public String getCaption()
    {
        return caption;
    }

    /**
     * 图表副标题的设定
     * 
     * @param subcaption
     *            图表副标题
     */
    public void setSubcaption(String subcaption)
    {
        this.subcaption = subcaption;
    }

    /**
     * 图表副标题的取得
     * 
     * @return 图表副标题
     */
    public String getSubcaption()
    {
        return subcaption;
    }

    /**
     * 横向坐标轴(x轴)名称的设定
     * 
     * @param xAxisName
     *            横向坐标轴(x轴)名称
     */
    public void setXAxisName(String xAxisName)
    {
        this.xAxisName = xAxisName;
    }

    /**
     * 横向坐标轴(x轴)名称的取得
     * 
     * @return 横向坐标轴(x轴)名称
     */
    public String getXAxisName()
    {
        return xAxisName;
    }

    /**
     * 纵向坐标轴(y轴)名称的设定
     * 
     * @param yAxisName
     *            纵向坐标轴(y轴)名称
     */
    public void setYAxisName(String yAxisName)
    {
        this.yAxisName = yAxisName;
    }

    /**
     * 纵向坐标轴(y轴)名称的取得
     * 
     * @return 纵向坐标轴(y轴)名称
     */
    public String getYAxisName()
    {
        return yAxisName;
    }

    /**
     * 指定纵轴(y轴)最小值，数字的设定
     * 
     * @param yAxisMinValue
     *            指定纵轴(y轴)最小值，数字
     */
    public void setYAxisMinValue(long yAxisMinValue)
    {
        this.yAxisMinValue = yAxisMinValue;
    }

    /**
     * 指定纵轴(y轴)最小值，数字的取得
     * 
     * @return 指定纵轴(y轴)最小值，数字
     */
    public long getYAxisMinValue()
    {
        return yAxisMinValue;
    }

    /**
     * 增加数字前缀的设定
     * 
     * @param numberPrefix
     *            增加数字前缀
     */
    public void setNumberPrefix(String numberPrefix)
    {
        this.numberPrefix = numberPrefix;
    }

    /**
     * 增加数字前缀的取得
     * 
     * @return 增加数字前缀
     */
    public String getNumberPrefix()
    {
        return numberPrefix;
    }

    /**
     * 是否在图表显示对应的数据值，默认为1(True)的设定
     * 
     * @param showValues
     *            是否在图表显示对应的数据值，默认为1(True)
     */
    public void setShowValues(boolean showValues)
    {
        this.showValues = showValues;
    }

    /**
     * 是否在图表显示对应的数据值，默认为1(True)的取得
     * 
     * @return 是否在图表显示对应的数据值，默认为1(True)
     */
    public boolean getShowValues()
    {
        return showValues;
    }

    /**
     * 图表字体样式的设定
     * 
     * @param baseFont
     *            图表字体样式
     */
    public void setBaseFont(String baseFont)
    {
        this.baseFont = baseFont;
    }

    /**
     * 图表字体样式的取得
     * 
     * @return 图表字体样式
     */
    public String getBaseFont()
    {
        return baseFont;
    }

    /**
     * 图表字体大小的设定
     * 
     * @param baseFontSize
     *            图表字体大小
     */
    public void setBaseFontSize(int baseFontSize)
    {
        this.baseFontSize = baseFontSize;
    }

    /**
     * 图表字体大小的取得
     * 
     * @return 图表字体大小
     */
    public int getBaseFontSize()
    {
        return baseFontSize;
    }

    /**
     * 设置图表左边距的设定
     * 
     * @param chartLeftMargin
     *            设置图表左边距
     */
    public void setChartLeftMargin(int chartLeftMargin)
    {
        this.chartLeftMargin = chartLeftMargin;
    }

    /**
     * 设置图表左边距的取得
     * 
     * @return 设置图表左边距
     */
    public int getChartLeftMargin()
    {
        return chartLeftMargin;
    }

    /**
     * 设置图表右边距的设定
     * 
     * @param chartRightMargin
     *            设置图表右边距
     */
    public void setChartRightMargin(int chartRightMargin)
    {
        this.chartRightMargin = chartRightMargin;
    }

    /**
     * 设置图表右边距的取得
     * 
     * @return 设置图表右边距
     */
    public int getChartRightMargin()
    {
        return chartRightMargin;
    }

    /**
     * labelStep的设定
     * 
     * @param labelStep
     *            labelStep
     */
    public void setLabelStep(String labelStep)
    {
        this.labelStep = labelStep;
    }

    /**
     * labelStep的取得
     * 
     * @return labelStep
     */
    public String getLabelStep()
    {
        return labelStep;
    }

    /**
     * 水平分区线颜色，6位16进制颜色值的设定
     * 
     * @param divLineColor
     *            水平分区线颜色，6位16进制颜色值
     */
    public void setDivLineColor(String divLineColor)
    {
        this.divLineColor = divLineColor;
    }

    /**
     * 水平分区线颜色，6位16进制颜色值的取得
     * 
     * @return 水平分区线颜色，6位16进制颜色值
     */
    public String getDivLineColor()
    {
        return divLineColor;
    }

    /**
     * 横向网格带交替的颜色，6位16进制颜色值的设定
     * 
     * @param alternateHGridColor
     *            横向网格带交替的颜色，6位16进制颜色值
     */
    public void setAlternateHGridColor(String alternateHGridColor)
    {
        this.alternateHGridColor = alternateHGridColor;
    }

    /**
     * 横向网格带交替的颜色，6位16进制颜色值的取得
     * 
     * @return 横向网格带交替的颜色，6位16进制颜色值
     */
    public String getAlternateHGridColor()
    {
        return alternateHGridColor;
    }

    /**
     * 图表背景色，6位16进制颜色值的设定
     * 
     * @param bgColor
     *            图表背景色，6位16进制颜色值
     */
    public void setBgColor(String bgColor)
    {
        this.bgColor = bgColor;
    }

    /**
     * 图表背景色，6位16进制颜色值的取得
     * 
     * @return 图表背景色，6位16进制颜色值
     */
    public String getBgColor()
    {
        return bgColor;
    }

    /**
     * Line的线颜色的设定
     * 
     * @param lineColor
     *            Line的线颜色
     */
    public void setLineColor(String lineColor)
    {
        this.lineColor = lineColor;
    }

    /**
     * Line的线颜色的取得
     * 
     * @return Line的线颜色
     */
    public String getLineColor()
    {
        return lineColor;
    }

    /**
     * MSLine的设定
     * 
     * @param categoryList
     *            MSLine
     */
    public void setCategoryList(List<String> categoryList)
    {
        this.categoryList = categoryList;
    }

    /**
     * MSLine的取得
     * 
     * @return MSLine
     */
    public List<String> getCategoryList()
    {
        return categoryList;
    }

    /**
     * 一个图一组数据的设定
     * 
     * @param dataList
     *            一个图一组数据
     */
    public void setDataList(List<DataPoint> dataList)
    {
        this.dataList = dataList;
    }

    /**
     * 一个图一组数据的取得
     * 
     * @return 一个图一组数据
     */
    public List<DataPoint> getDataList()
    {
        return dataList;
    }

    /**
     * 一个图多组数据的设定
     * 
     * @param dataPointSetList
     *            一个图多组数据
     */
    public void setDataPointSetList(List<DataPointSet> dataPointSetList)
    {
        this.dataPointSetList = dataPointSetList;
    }

    /**
     * 一个图多组数据的取得
     * 
     * @return 一个图多组数据
     */
    public List<DataPointSet> getDataPointSetList()
    {
        return dataPointSetList;
    }

    /**
     * 图标类型的设定
     * 
     * @param chartType
     *            图标类型
     */
    public void setChartType(ChartType chartType)
    {
        this.chartType = chartType;
    }

    /**
     * 图标类型的取得
     * 
     * @return 图标类型
     */
    public ChartType getChartType()
    {
        return chartType;
    }
}
