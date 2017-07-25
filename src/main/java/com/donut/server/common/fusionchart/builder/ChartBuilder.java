package com.donut.server.common.fusionchart.builder;

import com.donut.server.common.fusionchart.data.ChartModel;
import com.donut.server.common.fusionchart.data.ChartResult;

/**
 * @ClassName: ChartModel
 * @Description: 构建图表数据集
 * @date 2014年11月21日 上午9:45:27
 * @version 1.0
 */
public abstract class ChartBuilder
{
    public ChartResult buildChart(ChartModel chartModel)
    {
        ChartResult chartResult = new ChartResult();
        // 表格类型
        chartResult.setChartType(chartModel.getChartType().name() + ".swf");
        String chartData = build(chartModel);
        // 数据源
        chartResult.setChartData(chartData.replaceAll("\"", "'").replaceAll(
                "\n", ""));
        return chartResult;
    }

    protected abstract String build(ChartModel chartModel);
}
