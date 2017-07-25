package com.donut.server.common.fusionchart.builder;

import com.donut.server.common.fusionchart.data.ChartModel;
import com.donut.server.common.fusionchart.data.ChartResult;

/**
 * @ClassName: ChartBuilderTool
 * @Description: 构建图表数据集处理 选择数据集
 * @author 赵峰剑
 * @date 2014年11月21日 上午9:45:27
 * @version 1.0
 */
public class ChartBuilderTool
{
    public static ChartResult buildChart(ChartModel chartModel)
    {
        ChartBuilder chartBuilder = null;
        switch (chartModel.getChartType())
        {
        // case Column2D:
        // chartBuilder = new Column2DChartBuilder();
        // break;
        case Column3D:
            chartBuilder = new Column3DChartBuilder();
            break;
        case Pie3D:
            chartBuilder = new Pie3DChartBuilder();
            break;
        // case Line:
        // chartBuilder = new LineChartBuilder();
        // break;
        case MSLine:
            chartBuilder = new MSLineChartBuilder();
            break;
        case StackedColumn2D:
            chartBuilder = new StackedColumn2DChartBuilder();
            break;
        default:
            return null;
        }
        return chartBuilder.buildChart(chartModel);
    }
}
