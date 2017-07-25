package com.donut.server.common.fusionchart.builder;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.donut.server.common.fusionchart.data.ChartModel;
import com.donut.server.common.fusionchart.data.DataPoint;
import com.donut.server.common.fusionchart.data.DataPointSet;

/**
 * @ClassName: StackedColumn2DChartBuilder
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 王治威
 * @date 2015年9月2日 下午5:25:10
 * @version 1.0
 */
public class StackedColumn2DChartBuilder extends ChartBuilder
{
    @Override
    protected String build(ChartModel chartModel)
    {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("utf-8");
        Element graph = doc.addElement("chart");
        graph.addAttribute("yAxisMinValue", chartModel.getYAxisMinValue() + "");// 指定纵轴(y轴)最小值，数字
        graph.addAttribute("numberPrefix", chartModel.getNumberPrefix());// 增加数字前缀
        // 是否在图表显示对应的数据值，默认为1(True)
        if (chartModel.getShowValues())
        {
            graph.addAttribute("showValues", "1");
        }
        else
        {
            graph.addAttribute("showValues", "0");//
        }

        graph.addAttribute("chartLeftMargin", chartModel.getChartLeftMargin()
                + "");// 设置图表左边距
        graph.addAttribute("chartRightMargin", chartModel.getChartRightMargin()
                + ""); // 设置图表右边距
        graph.addAttribute("baseFont", chartModel.getBaseFont());// 图表字体样式
        graph.addAttribute("baseFontSize", chartModel.getBaseFontSize() + "");// 图表字体大小
        // 列宽
        graph.addAttribute("maxColWidth", "30");
        // 调色板
        graph.addAttribute("palette", "0");
        // 是否显示名称

        graph.addAttribute("bgColor", "#ffffff");
        graph.addAttribute("numDivLines", "4");
        graph.addAttribute("plotFillRatio", "100");
        graph.addAttribute("showBorder", "0");
        graph.addAttribute("showPlotBorder", "100");
        graph.addAttribute("canvasBorderAlpha", "0");
        graph.addAttribute("divLineAlpha", "0");
        graph.addAttribute("showAlternateHGridColor", "0");
        graph.addAttribute("useRoundEdges", "0");
        graph.addAttribute("shownames", "1");
        graph.addAttribute("legendBorderAlpha", "0");
        graph.addAttribute("animation", "1");
        graph.addAttribute("decimalPrecision", "0");
        graph.addAttribute("formatNumberScale", "0");
        graph.addAttribute("rotateYAxisName", "0");
        graph.addAttribute("showFCMenuItem", "0");
        graph.addAttribute("rotateLabels", "0");// (是否旋转x轴的坐标值)
        graph.addAttribute("slantLabels", "0");// (将x轴坐标值旋转为倾斜的还是完全垂直的)

        Element categories = graph.addElement("categories");
        for (String category : chartModel.getCategoryList())
        {
            Element cate = categories.addElement("category");
            cate.addAttribute("label", category);
        }

        // 数值构建XML样式
        for (DataPointSet pointSet : chartModel.getDataPointSetList())
        {
            Element dataSet = graph.addElement("dataset");
            dataSet.addAttribute("seriesName", pointSet.getSeriesName());
            dataSet.addAttribute("color", pointSet.getColor());

            for (DataPoint dp : pointSet.getDataPointList())
            {
                Element set = dataSet.addElement("set");
                set.addAttribute("value", dp.getValue());
            }
        }

        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        try
        {
            xw.write(doc);
            xw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
