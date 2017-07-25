package com.donut.server.common;

import javax.servlet.http.HttpServletRequest;

public class IpUtil
{
    /**
     * IP转成数字类型
     * 
     * @param strIP
     * @return
     */
    public static long ipToLong(String strIP)
    {
        long[] ip = new long[4];
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        int position4 = strIP.indexOf(",", position3 + 1);
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        if (position4 > 0)
        {
            ip[3] = Long.parseLong(strIP.substring(position3 + 1, position4)
                    .trim());
        }
        else
        {
            ip[3] = Long.parseLong(strIP.substring(position3 + 1).trim());
        }
        // ip1*256*256*256+ip2*256*256+ip3*256+ip4
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static String getClientIP(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
