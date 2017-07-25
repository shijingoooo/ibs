package com.donut.server.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Tools
 * @Description: 密码加密
 * @author 赵峰剑
 * @date 2014年1月26日 下午3:54:26
 * @version 1.0
 */
public class Tools
{
    /**
     * MD5加密
     * 
     * @param p
     * @param plainText
     * @return E
     */
    public static String md5(String plainText, int p)
    {
        String ret = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if (p == 16)
            {
                ret = buf.toString().substring(8, 24);
            }
            else
            {
                ret = buf.toString();
            }
        }
        catch(NoSuchAlgorithmException e)
        {
        }
        return ret;
    }

    public static String md5(String plainText)
    {
        return md5(plainText, 32);
    }
}
