package com.donut.server.common.fusionchart.builder;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.donut.server.common.fusionchart.data.ChartModel;
import com.donut.server.common.fusionchart.data.DataPoint;





/**
 * @ClassName: Pie3DChartBuilder
 * @Description: 构建3D图表 饼图
 * @author 赵峰剑
 * @date 2014年11月21日 上午9:45:27
 * @version 1.0
 */
public class Pie3DChartBuilder extends ChartBuilder
{

    @Override
    protected String build(ChartModel chartModel)
    {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("utf-8");
        Element graph = doc.addElement("chart");
        // 图表主标题
        graph.addAttribute("caption", chartModel.getCaption());
        // 横向坐标轴(x轴)名称
        graph.addAttribute("xAxisName", chartModel.getXAxisName());
        // 纵向坐标轴(y轴)名称
        graph.addAttribute("yAxisName", chartModel.getYAxisName());
        // 指定纵轴(y轴)最小值，数字
        graph.addAttribute("yAxisMinValue", chartModel.getYAxisMinValue() + "");
        if (chartModel.getShowValues())
        {
            graph.addAttribute("showValues", "1");
        }
        else
        {
            graph.addAttribute("showValues", "0");
        }
        graph.addAttribute("chartLeftMargin", -20 + "");
        // 设置图表右边距
        graph.addAttribute("chartRightMargin", chartModel.getChartRightMargin()
                + "");
        // 图表字体样式
        graph.addAttribute("baseFont", chartModel.getBaseFont());
        // 图表字体大小
        graph.addAttribute("baseFontSize", chartModel.getBaseFontSize() + "");
        graph.addAttribute("maxColWidth", "30");
        graph.addAttribute("palette", "0");
        graph.addAttribute("shownames", "1");
        graph.addAttribute("legendBorderAlpha", "0");
        graph.addAttribute("useRoundEdges", "1");
        graph.addAttribute("animation", "1");
        graph.addAttribute("decimalPrecision", "0");
        graph.addAttribute("formatNumberScale", "0");
        graph.addAttribute("rotateYAxisName", "0");
        graph.addAttribute("showFCMenuItem", "0");
        graph.addAttribute("bgColor", "F3F5E8");
        graph.addAttribute("borderColor", "A2A3A0");
        graph.addAttribute("showBorder", "1");
        // 配置bg颜色
        graph.addAttribute("bgColor", "91AF46,FFFFFF");
        graph.addAttribute("bgAlpha", "50,30");
        graph.addAttribute("bgRatio", "15,85");
        graph.addAttribute("bgAngle", "270");
        graph.addAttribute("divLineColor", "91AF46");
        graph.addAttribute("canvasBorderColor", "91AF46");
        graph.addAttribute("baseFontColor", "666666");
        graph.addAttribute("lineColor", "91AF46");
        graph.addAttribute("outCnvbaseFontColor", "333333");
        graph.addAttribute("manageLabelOverflow", "1");
        graph.addAttribute("pieRadius", "130");
        graph.addAttribute("showBorder", "0");
        graph.addAttribute("rotateLabels", "1");// (是否旋转x轴的坐标值)
        graph.addAttribute("slantLabels", "1");// (将x轴坐标值旋转为倾斜的还是完全垂直的)

        // 图片输出
        // graph.addAttribute("exportEnabled", "1");
        // graph.addAttribute("exportAtClient", "0");
        // graph.addAttribute("exportHandler",
        // "/AdminPlatform/FCExporter.servlet");
        // graph.addAttribute("exportAction", "save");
        // graph.addAttribute("exportFileName", "/images/report/savefile");
        // graph.addAttribute("exportCallback", "FC_Exported");

        // 数值构建XML样式
        for (DataPoint point : chartModel.getDataList())
        {
            Element set = graph.addElement("set");
            if (point.getLabel() != null)
            {
                set.addAttribute("label", point.getLabel());
            }
            if (point.getValue() != null)
            {
                set.addAttribute("value", point.getValue());
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
