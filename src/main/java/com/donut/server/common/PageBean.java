package com.donut.server.common;

import java.util.List;

/**
 * 分页显示的标准类,基本操作,是先给予-当前页数一共的数据条数-每页显示的条数, 然后在初始化该类,得到总共页数,和开始序号和结束序号,
 * 然后数据库分页用到开始序号和结束序号,得到数据集合后赋值给该类的list属性, 然后把该类发送到jsp页面,进行访问
 * 
 * @ClassName: PageBean
 * @Description: 企业用户_任务管理_任务一览
 * @author 赵峰剑
 * @date 2014年6月13日 下午1:40:07
 * @version 1.0
 */
public class PageBean<T>
{
    private long pageIndex;// 当前页数

    private int totalPages;// 一共的页数

    private long count;// 数据条数

    private int recordsPerPage;// 每页的数据条数

    private long start;// 起始数据位置

    private String pageHtml;// 分页

    private List<T> list = null;// 画面项目

    /** form名称 */
    private String form;

    /** action名称 */
    private String action;

    private String naviHtml;// 分页导航

    /**
     * form名称的设定
     * 
     * @param form
     *            form名称
     */
    public void setForm(String form)
    {
        this.form = form;
    }

    /**
     * form名称的取得
     * 
     * @return form名称
     */
    public String getForm()
    {
        return form;
    }

    public String getHtml()
    {
        setPageInfo();

        long nextPage = pageIndex + 1;
        long frontPage = pageIndex - 1;
        int endPage = totalPages - 1;

        StringBuilder sb = new StringBuilder();
        sb.append(" <script type=\"text/javascript\">");
        sb.append("\r");
        sb.append(" function forEmpty(obj){ ");
        sb.append("\r");
        sb.append(" var value=$.trim(obj.value); ");
        sb.append("\r");
        sb.append(" if(value=='') ");
        sb.append("\r");
        sb.append(" $(\"#pageIndex\").val('1'); ");
        sb.append("\r");
        sb.append(" } ");
        sb.append("\r");

        sb.append(" function goToPage(action, form_id, index, flg) { ");
        sb.append("\r");
        sb.append("   refresh(); ");
        sb.append("\r");
        sb.append("   var url = action + \"startIndex=\" + index ");
        sb.append("\r");
        sb.append("   + (flg == \"\" ? \"\" : \"&flg=\" + flg); ");
        sb.append("\r");
        sb.append("   $(\"#\" + form_id).attr(\"action\", url); ");
        sb.append("\r");
        sb.append("   $(\"#\" + form_id).submit(); ");
        sb.append("\r");
        sb.append("  } ");
        sb.append("\r");
        sb.append(" </script>");
        sb.append("\r");

        sb.append("     <!--翻页代码 -->     ");
        sb.append("     <div class=\"div_page\">     ");
        sb.append("     <span>     ");
        sb.append(count + "条记录/" + totalPages + "页");
        sb.append("     </span>&nbsp;&nbsp;     ");
        if (pageIndex == 0)
        {

            sb.append("     <span>首页</span>&nbsp;&nbsp;     ");
            sb.append("     <span>上一页</span>&nbsp;&nbsp;     ");
        }
        else
        {
            sb.append("     <a onclick=\"goToPage('" + this.action + "','"
                    + this.form + "','" + "0"
                    + "','');\"href=\"#\">首页</a>&nbsp;&nbsp;     ");
            sb.append("     <a onclick=\"goToPage('" + this.action + "','"
                    + this.form + "','" + frontPage
                    + "','');\" href=\"#\">上一页</a>&nbsp;&nbsp;     ");
        }

        sb.append(this.naviHtml);
        if ((pageIndex + 1) >= totalPages)
        {
            sb.append("     <span>下一页</span>&nbsp;&nbsp;     ");
            sb.append("     <span>尾页</span>&nbsp;&nbsp;     ");
        }
        else
        {
            sb.append("     <a onclick=\"goToPage('" + this.action + "','"
                    + this.form + "','" + nextPage
                    + "','');\" href=\"#\">下一页</a>&nbsp;&nbsp;     ");
            sb.append("     <a onclick=\"goToPage('" + this.action + "','"
                    + this.form + "','" + endPage
                    + "','')\" href=\"#\">尾页</a>&nbsp;&nbsp;     ");
        }

        sb.append("      <input type=\"text\" onBlur=\"forEmpty(this);\" onkeyup=\"this.value=this.value.replace(/\\D/g,'0')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'0')\"     ");
        sb.append("     name=\"pageIndex\" id=\"pageIndex\" value=\""
                + (count == 0 ? pageIndex : (pageIndex + 1)) + "\" />");
        if (totalPages > 1)
        {
            sb.append("     <button class=\"btn btn-mini btn-primary\"     ");
            sb.append("     onclick=\"goToPage('" + this.action + "','"
                    + this.form + "','0','flg');\">跳转</button>     ");
        }
        else
        {
            sb.append("     <button class=\"btn btn-mini btn-primary\" disabled=\"disabled\" >跳转</button>     ");
        }

        sb.append("     </div>     ");

        return sb.toString();
    }

    private void setPageInfo()
    {
        // 计算总页数
        if (count > 0)
        {
            totalPages = (int) (count / recordsPerPage);
            if (count % recordsPerPage != 0)
                totalPages++;

            pageIndex = pageIndex >= totalPages ? totalPages - 1
                    : (pageIndex - 1 < 0 ? 0 : pageIndex);
            start = pageIndex * recordsPerPage;
        }
        else
        {
            pageIndex = 0;
            start = 0;
        }

        this.createNavi(5);
    }

    public void createNavi(long num)
    {
        long showNum = num + 1;
        long startIndex = 1;
        long curentPage = pageIndex + 1;
        long total = 0;

        if (totalPages <= showNum)
        {
            total = totalPages;
        }
        else if (totalPages > showNum && curentPage < showNum)
        {
            total = showNum;
        }
        else
        {
            if (((curentPage - 1) % num) == 0)
                startIndex = curentPage;
            else
                startIndex = curentPage - curentPage % showNum;

            if ((totalPages - curentPage) >= showNum)
                total = curentPage + num;
            else
                total = totalPages;
        }

        StringBuilder navi = new StringBuilder();
        int breakFlg = 0;
        for (long i = startIndex; i <= total; i++, breakFlg++)
        {
            if (breakFlg == showNum)
                break;
            if (i == curentPage)
                navi.append("<span class=\"navi\" style=\"background:#CAF1ED;color:#666\">"
                        + i + "</span>&nbsp;");
            else
                navi.append("<a onclick=\"goToPage('" + this.action + "','"
                        + this.form + "','" + (i - 1)
                        + "',''); \" href=\"#\" class=\"navi\">" + i
                        + "</a>&nbsp;");
        }

        this.naviHtml = navi.toString();
    }

    /**
     * 当前页数的设定
     * 
     * @param pageIndex
     *            当前页数
     */
    public void setPageIndex(long pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    /**
     * 当前页数的取得
     * 
     * @return 当前页数
     */
    public long getPageIndex()
    {
        return pageIndex;
    }

    /**
     * 一共的页数的设定
     * 
     * @param totalPages
     *            一共的页数
     */
    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    /**
     * 一共的页数的取得
     * 
     * @return 一共的页数
     */
    public int getTotalPages()
    {
        return totalPages;
    }

    /**
     * 数据条数的设定
     * 
     * @param count
     *            数据条数
     */
    public void setCount(long count)
    {
        this.count = count;
        this.setPageHtml(getHtml());
    }

    /**
     * 数据条数的取得
     * 
     * @return 数据条数
     */
    public long getCount()
    {
        return count;
    }

    /**
     * 每页的数据条数的设定
     * 
     * @param recordsPerPage
     *            每页的数据条数
     */
    public void setRecordsPerPage(int recordsPerPage)
    {
        this.recordsPerPage = recordsPerPage;
    }

    /**
     * 每页的数据条数的取得
     * 
     * @return 每页的数据条数
     */
    public int getRecordsPerPage()
    {
        return recordsPerPage;
    }

    /**
     * 起始数据位置的设定
     * 
     * @param start
     *            起始数据位置
     */
    public void setStart(int start)
    {
        this.start = start;
    }

    /**
     * 起始数据位置的取得
     * 
     * @return 起始数据位置
     */
    public long getStart()
    {
        return start;
    }

    /**
     * 分页的设定
     * 
     * @param pageHtml
     *            分页
     */
    public void setPageHtml(String pageHtml)
    {
        this.pageHtml = pageHtml;
    }

    /**
     * 分页的取得
     * 
     * @return 分页
     */
    public String getPageHtml()
    {
        return pageHtml;
    }

    /**
     * 画面项目的设定
     * 
     * @param list
     *            画面项目
     */
    public void setList(List<T> list)
    {
        this.list = list;
    }

    /**
     * 画面项目的取得
     * 
     * @return 画面项目
     */
    public List<T> getList()
    {
        return list;
    }

    /**
     * action事件的设定
     * 
     * @param action
     *            action事件
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * action事件的取得
     * 
     * @return action事件
     */
    public String getAction()
    {
        return action;
    }

}
