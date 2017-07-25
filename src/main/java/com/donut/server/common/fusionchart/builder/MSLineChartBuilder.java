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
 * @ClassName: MSLineChartBuilder
 * @Description: 折线图用
 * @author 杨玉娇
 * @date 2015年1月9日 上午9:51:08
 * @version 1.0
 */

public class MSLineChartBuilder extends ChartBuilder
{

    @Override
    protected String build(ChartModel chartModel)
    {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("utf-8");
        Element graph = doc.addElement("chart");
        graph.addAttribute("caption", chartModel.getCaption());// 图表主标题
        graph.addAttribute("subcaption", chartModel.getSubcaption());// /图表子标题
        graph.addAttribute("xAxisName", chartModel.getXAxisName());// 横向坐标轴(x轴)名称
        graph.addAttribute("yAxisName", chartModel.getYAxisName());// 纵向坐标轴(y轴)名称
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

        graph.addAttribute("divLineAlpha", "20");// 水平分区线透明度
        graph.addAttribute("anchorRadius", "2");// 折点半径
        graph.addAttribute("lineThickness", "2");// 线的粗细
        graph.addAttribute("divLineIsDashed", "1");// 水平分区线是否为虚线
        graph.addAttribute("showAlternateHGridColor", "1");// 是否在横向网格带交替的颜色，默认为0(False)
        graph.addAttribute("alternateHGridAlpha", "5");// 横向网格带的透明度，[0-100]
        graph.addAttribute("shadowAlpha", "40");//
        // label步伐(可以设置5个一显示/和category中showLabel='0'有些类似但不完全相同,区别在于这个属性是先展示后去掉不要的label,后者正好相反.这样对label会不会自动折行很有关系)
        graph.addAttribute("labelStep", "1");//
        graph.addAttribute("numvdivlines", "10");// 画布内部垂直分区线条数，数字
        graph.addAttribute("bgAngle", "270");// 图表背景的角度
        graph.addAttribute("bgAlpha", "10,10");// 图表背景透明度
        graph.addAttribute("formatNumberScale", "0");//
        graph.addAttribute("rotateYAxisName", "0");// 是否旋转y轴名字
        graph.addAttribute("rotateXAxisName", "0");// 是否旋转X轴名字
        graph.addAttribute("rotateLabels", "1");// (是否旋转x轴的坐标值)
        graph.addAttribute("slantLabels", "1");// (将x轴坐标值旋转为倾斜的还是完全垂直的)

        //
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
            dataSet.addAttribute("anchorBorderColor", pointSet.getColor());// 折点边框颜色
            dataSet.addAttribute("anchorBgColor", pointSet.getColor());// 折点背景色
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
