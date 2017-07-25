package com.donut.server.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.CharRange;
import org.apache.commons.lang.CharSet;

/**
 * @ClassName: StringUtil
 * @Description: 字符串转换共通
 * @author 赵峰剑
 * @date 2014年8月22日 下午3:54:26
 * @version 1.0
 */
public class StringUtil
{
    /** ASCII 文字 （半角英数）。 */
    private static CharSet alphaNumChar = CharSet.getInstance("0-9A-Za-z");

    /**
     * ASCII 文字 （半角英数）。
     */
    private final static CharRange digit = new CharRange('\u0030', '\u0039');

    /**
     * 指定长度的random生成
     * 
     * @param length
     *            长度
     * @return random结果
     */
    public static String createRandomString(int length, boolean isNumber)
    {
        // 英数字组合
        int NUM_CHAR = 59;
        // 数字
        if (isNumber)
            NUM_CHAR = 10;

        String t = "";
        int j = 0;
        String[] v = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M",
                "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        for (int i = 0; i < length; i++)
        {
            j = (int) (NUM_CHAR * Math.random());
            t = t + v[j];
        }

        return t;
    }

    /**
     * 文字列变换
     * 
     * @param pInt
     *            输入文字列
     * @return pInt
     */
    public static int stringToInt(String pInt)
    {
        return pInt == null || "".equals(pInt) ? 0 : new BigDecimal(pInt)
                .intValue();
    }

    /**
     * Object对象向文字列变更。
     * 
     * @param obj
     *            Object对象
     * @return 向文字
     */
    public static String objToStr(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        else
        {
            return String.valueOf(obj);
        }
    }

    /**
     * Object对象向文字列变更。
     * 
     * @param obj
     *            Object对象
     * @return 向文字
     */
    public static String trim(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        else
        {
            return String.valueOf(obj).trim();
        }
    }

    /**
     * Object对象删除空格。
     * 
     * @param obj
     *            Object对象
     * @return 删除空格文字
     */
    public static String StrTrim(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        else
        {
            return String.valueOf(obj).trim();
        }
    }

    /**
     * 检查文字列是<code>null</code。
     * 
     * @param str
     *            文字列
     * @return true或者false
     */
    public static boolean isEmpty(String str)
    {
        if (str == null || str.length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 文字列比較<br>
     * 
     * @param pStr1
     *            文字列1
     * @param pStr2
     *            文字列2
     * @return true or not
     * 
     */
    public static boolean strEquals(String pStr1, String pStr2)
    {
        return pStr1 == null ? pStr2 == null : pStr1.equals(pStr2);
    }

    /**
     * urlEncode转码<br>
     * 
     * @param str
     *            文字列1
     * @return String
     * 
     */
    public static String urlEncode(String str, String code)
    {
        String result = "";
        try
        {
            result = URLEncoder.encode(str, code);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * urlDecode转码<br>
     * 
     * @param str
     *            文字列1
     * @return String
     * 
     */
    public static String urlDecode(String str, String code)
    {
        String result = "";
        try
        {
            result = new String(str.getBytes("ISO-8859-1"), code);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Double判断
     * 
     * @param pInt
     *            Double判断
     * @return pInt
     */
    public static boolean isDouble(String pInt)
    {
        try
        {
            Double.parseDouble(pInt);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 文字列到double。
     * 
     * @param doubleStr
     *            文字列
     * @return double型数値
     */
    public static double strToDouble(String doubleStr)
    {
        if (isEmpty(doubleStr))
        {
            return Double.parseDouble("0.0");
        }
        try
        {
            return new BigDecimal(doubleStr).doubleValue();
        }
        catch(Exception e)
        {
            return Double.parseDouble("0.0");
        }
    }

    /**
     * 文字列到float。
     * 
     * @param doubleStr
     *            文字列
     * @return float型数値
     */
    public static float strToFloat(String doubleStr)
    {
        if (isEmpty(doubleStr))
        {
            return Float.parseFloat("0.0");
        }
        try
        {
            return new BigDecimal(doubleStr).floatValue();
        }
        catch(Exception e)
        {
            return Float.parseFloat("0.0");
        }
    }

    /**
     * 文字列byte数取得。
     * 
     * @param src
     *            文字列
     * @return byte数
     * @author
     */
    public static int getUtf8Bytes(String src)
    {
        if (isEmpty(src))
        {
            return 0;
        }
        else
        {
            try
            {
                return src.getBytes("UTF-8").length;
            }
            catch(UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 转换成数组
     * 
     * @param pInt
     *            ,dot 参数
     * @return 数组
     */
    public static String[] split(String pInt, String dot)
    {
        return pInt.split(dot, -1);
    }

    /**
     * String的length处理<br>
     * 
     * @param str
     *            変換対象
     * @return str是null的时候、0返回 str是null以外的时候、str.length()返回
     */
    public static int length(String str)
    {
        if (str == null)
        {
            return 0;
        }
        else
        {
            return str.length();
        }
    }

    /**
     * 文字列是ASCII文字(英数)的验证。
     * 
     * @param str
     *            文字列
     * @return false
     */
    public static boolean isAsciiAlphaNumCharOnly(String str)
    {
        boolean asciiOnly = true;
        if (StringUtil.isEmpty(str))
        {
            return asciiOnly;
        }
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++)
        {
            if (!alphaNumChar.contains(cs[i]))
            {
                asciiOnly = false;
            }
        }
        return asciiOnly;
    }

    /**
     * 半角数字验证
     * 
     * @param str
     *            文字列
     * @return 半角数字 0-9 时 true
     */
    public static boolean isDigit(String str)
    {
        boolean isdigit = false;
        if (isEmpty(str))
        {
            return isdigit;
        }
        isdigit = true;
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++)
        {
            if (!digit.contains(cs[i]))
            {
                isdigit = false;
            }
        }
        return isdigit;
    }

    /**
     * 固定位半角数字验证
     * 
     * @param str
     *            文字列
     * @param count
     *            位数
     * @return 半角数字 0-9 时 true
     */
    public static boolean isCountNum(String str, int count)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        if (str.length() > count)
        {
            return false;
        }
        return isDigit(str);
    }

    /**
     * 
     * 
     * @Title: is6Num
     * @Description: 判断是否为首位不是0的6为的数字
     * @param @param str
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean is6Num(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return true;
        }
        String reg = "^([1-9][0-9]{5})$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 
     * 
     * @Title: is2numeric
     * @Description: 判断是否为2位的数字
     * @param @param str
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean is2numeric(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return true;
        }
        String reg = "^([0-9]{2})$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 
     * 
     * @Title: is01num
     * @Description: 判断是否为输入0到1之间(包括0和1两个整数)的小数
     * @param @param flo
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean is01num(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return true;
        }
        String reg = "^(0([\\.]\\d*[0-9]+)|0|1)$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 
     * 
     * @Title: proportionCheck
     * @Description: 分账占比Check（正确格式：1:2:7）
     * @param @param str
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean proportionCheck(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        String reg = "^([0-9]{1}:[0-9]{1}:[0-9]{1})$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 
     * 
     * @Title: proportionCheck2
     * @Description: 分账占比的三个数字之和需等于10
     * @param @param str
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean proportionCheck2(String str)
    {
        boolean boo = true;
        Pattern pat = Pattern.compile("[:]+");
        String[] result = pat.split(str);
        String num0 = result[0];
        String num1 = result[1];
        String num2 = result[2];
        int num = Integer.parseInt(num0) + Integer.parseInt(num1)
                + Integer.parseInt(num2);
        if (num != 10)
        {
            boo = false;
        }
        return boo;
    }

    /**
     * 文件名验证
     * 
     * @param str
     *            文件名
     * @return .app或.apk 时 true
     */
    public static boolean isAppName(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        String reg = "^([0-9A-Za-z]+\\.(apk|ipa))$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * 文件名路径验证
     * 
     * @param str
     *            文件名
     * @return .app或.apk 时 true
     */
    public static boolean isAppUrlName(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        String reg = "^((http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?)$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        if (rs)
            return str.endsWith(".apk") || str.endsWith(".app");
        return rs;
    }

    /**
     * Long转换
     * 
     * @param str
     *            文件名
     * @return
     */
    public static long strToLong(String str)
    {
        if (isEmpty(str))
        {
            return 0;
        }
        try
        {
            return Long.parseLong(str);
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    /**
     * Long判断
     * 
     * @param str
     *            文件名
     * @return
     */
    public static boolean isLong(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }
        try
        {
            Long.parseLong(str);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Integer判断
     * 
     * @param str
     *            文件名
     * @return
     */
    public static boolean isInteger(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }
        try
        {
            Integer.parseInt(str);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * 
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     * 
     * @param str
     *            要检查的字符串
     * 
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str)
    {
        int length;

        if ((str == null) || ((length = str.length()) == 0))
        {
            return true;
        }

        for (int i = 0; i < length; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * 消息推送截取消息
     * 
     * @param msg
     *            消息体
     * @return json
     */
    public static String getMsg(String msg)
    {
        if (isEmpty(msg))
            return "";
        else
            return msg.length() <= 15 ? msg : msg.substring(0, 15);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * 
     * @param mobile
     *            移动、联通、电信运营商的号码段
     *            <p>
     *            移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *            、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     *            </p>
     *            <p>
     *            联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     *            </p>
     *            <p>
     *            电信的号段：133、153、180（未启用）、189
     *            </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isMobile(String mobile,String validate)
    {

        String regex = validate;
        return Pattern.matches(regex, mobile);

    }

    /**
     * 
     * 
     * @Title: isSpechars
     * @Description: 判断传入的字符是否是特殊字符
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isSpechars(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        String reg = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * @Title: IgnoreStringPlus
     * @Description: 特殊字符转换
     * @param str
     *            设定字符
     * @return 返回结果
     * @throws
     */
    public static String IgnoreStrPlus(String str)
    {
        if (!isEmpty(str))
        {
            str = str.replace("[", "［");
            str = str.replace("\t", "");
            str = str.replace("%", "％");
            str = str.replace("^", "＾");
            str = str.replace("*", "＊");
            str = str.replace("(", "（");
            str = str.replace(")", "）");
            str = str.replace("'", "’");
            str = str.replace("[", "［");
            str = str.replace("]", "］");
            str = str.replace("<", "＜");
            str = str.replace(">", "＞");
            str = str.replace("\"", "”");
            return str;
        }
        else
        {
            return "";
        }
    }

    /**
     * @Title: isEmail
     * @Description:判断邮箱
     * @param email
     *            设定字符
     * @return 返回结果
     * @throws
     */
    public static boolean isEmail(String email)
    {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 
     * 
     * @Title: isPs
     * @Description: 判断是否为6-32的数字字母组合
     * @param @param str
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isPs(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return true;
        }
        String reg = "^.{6,32}$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        return rs;
    }

    /**
     * @Title: isPhoneNumberValid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param phoneNumber
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
       /* if (isMobile(phoneNumber))
            return true;*/
        boolean isValid = false;
        String expression = "[0]{1}[0-9]{2,3}-[0-9]{7,8}";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            isValid = true;
        }
        return isValid;
    }

    public static String getRandomUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getToken(String IndentifyId, String passwdhex)
    {
        String token = null;
        if (passwdhex != null && !"".equals(passwdhex))
        {
            token = DigestUtils.md5Hex(DigestUtils.md5Hex(IndentifyId)
                    + passwdhex
                    + DigestUtils.md5Hex(System.currentTimeMillis() + ""));
        }
        else
        {
            token = DigestUtils.md5Hex(DigestUtils.md5Hex(IndentifyId)
                    + DigestUtils.md5Hex(System.currentTimeMillis() + ""));
        }

        return token;
    }

    public static String list2String(List<String> strList, String sep)
    {
        String retValue = "";
        if (sep == null)
        {
            sep = "";
        }
        if (strList == null || strList.size() == 0)
        {
            retValue = "";
        }
        else
        {
            for (int index = 0; index < strList.size(); ++index)
            {
                if (index > 0)
                {
                    retValue = retValue.concat(sep + strList.get(index));
                }
                else
                {
                    retValue = strList.get(index);
                }
            }
        }
        return retValue;
    }
}
