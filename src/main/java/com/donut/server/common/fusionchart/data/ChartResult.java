package com.donut.server.common.fusionchart.data;

/**
 * @ClassName: ChartResult
 * @Description: 表格数据结果
 * @author 赵峰剑
 * @date 2014年11月21日 上午10:08:42
 * @version 1.0
 */
public class ChartResult
{
    /** 表格类型 */
    private String chartType;

    /** 数据源 */
    private String chartData;

    /**
     * 表格类型的设定
     *
     * @param chartType
     *            表格类型
     */
    public void setChartType(String chartType)
    {
        this.chartType = chartType;
    }

    /**
     * 表格类型的取得
     *
     * @return 表格类型
     */
    public String getChartType()
    {
        return chartType;
    }

    /**
     * 数据源的设定
     *
     * @param chartData
     *            数据源
     */
    public void setChartData(String chartData)
    {
        this.chartData = chartData;
    }

    /**
     * 数据源的取得
     *
     * @return 数据源
     */
    public String getChartData()
    {
        return chartData;
    }
}
