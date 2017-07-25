package com.donut.server.common.fusionchart.data;

/**
 * @ClassName: DataPoint
 * @Description: XML键值对
 * @author 赵峰剑
 * @date 2014年11月21日 上午9:55:17
 * @version 1.0
 */
public class DataPoint
{
    /** 标识 */
    private String label;

    /** 值 */
    private String value;

    /**
     * 标识的设定
     *
     * @param label
     *            标识
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * 标识的取得
     *
     * @return 标识
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * 值的设定
     *
     * @param value
     *            值
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * 值的取得
     *
     * @return 值
     */
    public String getValue()
    {
        return value;
    }
}
