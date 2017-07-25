package com.capinfo.framework.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 获取指定指标之间的内容
	 * 
	 * @param sContent
	 *            要处理的字符串
	 * @param sTagName
	 *            指标名称
	 * @return 指标的内容
	 */
	public static String getTagValue(String sContent, String sTagName) {
		String sTagValue = "";
		String sTemp;
		if (sContent != null && sTagName != null) {
			sTemp = "<" + sTagName + ">";
			int nPosBegin = sContent.indexOf(sTemp);
			if (nPosBegin >= 0) {
				sTemp = "</" + sTagName + ">";
				int nPosEnd = sContent.indexOf(sTemp);
				if (nPosEnd >= 0) {
					sTagValue = sContent.substring(
							nPosBegin + sTagName.length() + 2, nPosEnd);
				}
			}
		}
		return sTagValue;
	}

	public static String[] getTagValues(String sContent, String sTagName) {
		String[] sTagValues = null;
		String sTemp = "";
		String sEndTag = "</" + sTagName + ">";
		int nCount = 0;
		int nFromIndex = 0;
		int nPos = 0;
		int i = 0;

		for (nPos = 0; (nPos = sContent.indexOf(sEndTag, nFromIndex)) > 0;) {
			nCount++;
			nFromIndex = nPos + sEndTag.length();
		}
		if (nCount > 0) {
			sTagValues = new String[nCount];
		}

		nFromIndex = 0;

		for (nPos = 0; (nPos = sContent.indexOf(sEndTag, nFromIndex)) > 0;) {
			sTemp = sContent.substring(nFromIndex);
			sTagValues[i] = getTagValue(sTemp, sTagName);
			nFromIndex = nPos + sEndTag.length();
			i++;
		}

		return sTagValues;
	}

	/**
	 * 将给定的字符串按照给定的分隔符分割成一个数组
	 * 
	 * @param strToSplit
	 *            要处理的字符串
	 * @param separator
	 *            分隔符
	 * @return 分解后形成的数组
	 */
	public static String[] splitString2Array(String strToSplit, String separator) {
		if (strToSplit == null || strToSplit.length() == 0)
			return null;
		if (separator == null || separator.length() == 0)
			return null;
		if (strToSplit.startsWith(separator)) // 如果是以分隔符打头，则将打头的分隔符去掉
		{
			strToSplit = strToSplit.substring(separator.length());
		}
		int count = 0;
		int index = 0;
		int lastIndex = strToSplit.lastIndexOf(separator);
		if (lastIndex + separator.length() < strToSplit.length()) // 如果不是以分割符结尾则补一个
		{
			strToSplit += separator;
		}
		for (int pos = 0; (pos = strToSplit.indexOf(separator, index)) > 0;) {
			count++;
			index = pos + separator.length();
		}

		if (count == 0)
			return (new String[] { strToSplit });
		String retStrArray[] = new String[count];
		index = 0;
		int pos;
		for (int i = 0; (pos = strToSplit.indexOf(separator, index)) > 0; i++) {
			retStrArray[i] = strToSplit.substring(index, pos);
			index = pos + separator.length();
		}
		return retStrArray;
	}

	/**
	 * 时间转换成字符串
	 * 
	 * @param dateValue
	 *            需要处理的时间
	 * @return 返回处理后的时间字符串值
	 */
	public static String sqlDate2String(java.sql.Date dateValue) {
		String sRet;
		if (dateValue == null) {
			sRet = "";
		} else {
			sRet = dateValue.toString();
		}
		return sRet;
	}

	/**
	 * 时间转换成字符串
	 * 
	 * @param dateValue
	 *            需要处理的时间
	 * @return 返回处理后的时间字符串值
	 */
	public static String sqlTimestamp2String(java.sql.Timestamp timestamp) {
		String sRet;
		if (timestamp == null) {
			sRet = "";
		} else {
			sRet = timestamp.toString();
		}
		return sRet;
	}

	/**
	 * 字符串转换成时间
	 * 
	 * @param sDateValue
	 *            需要处理的字符串
	 * @return 返回处理后的时间
	 */
	public static java.sql.Date string2SqlDate(String sDateValue) {
		java.sql.Date dateRet = null;
		if (sDateValue == null) {
			dateRet = null;
		} else {
			try {
				dateRet = java.sql.Date.valueOf(sDateValue);
			} catch (Exception e) {
				dateRet = null;
			}
		}
		return dateRet;
	}

	/**
	 * 字符串转换成时间
	 * 
	 * @param sDateValue
	 *            需要处理的字符串
	 * @return 返回处理后的时间
	 */
	public static java.sql.Timestamp string2SqlTimestamp(String sDateTimeValue) {
		java.sql.Timestamp timestamp = null;
		if (sDateTimeValue == null) {
			timestamp = null;
		} else {
			try {
				timestamp = java.sql.Timestamp.valueOf(sDateTimeValue);
			} catch (Exception e) {
				timestamp = null;
			}
		}
		return timestamp;
	}

	/**
	 * 转换时间为数字
	 * 
	 * @param sTime
	 *            hh:mm:ss 格式的字符串
	 * @param nCountType
	 *            转换的单位类型: 0: 小时; 1: 分钟; 2: 秒
	 * @return
	 */
	public static int getTime2Count(String sTime, int nCountType) {
		int nCount = -1;
		String[] time = sTime.trim().split(":");
		nCount = Integer.parseInt(time[0]) * 60 * 60
				+ Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2])
				* 1;
		if (nCountType == 0) {
			nCount = nCount / 3600;
		} else if (nCountType == 1) {
			nCount = nCount / 60;
		} else if (nCountType == 2) {
			nCount = nCount / 1;
		}
		return nCount;
	}

	/**
	 * 获取指定开始时间和结束时间之间的秒数
	 * 
	 * @param startDateTime
	 *            开始时间 yyyy-mm-dd hh:mm:ss格式
	 * @param endDateTime
	 *            结束时间
	 * @return 秒数
	 */
	public static long getSecondByDateTime(String startDateTime,
			String endDateTime) {
		Calendar start = StringUtil.getStringAsCalendar(startDateTime, 2);
		Calendar end = StringUtil.getStringAsCalendar(endDateTime, 2);

		long second = (end.getTime().getTime() - start.getTime().getTime()) / 1000;

		return second;
	}

	/**
	 * 数字转换为时间字符串
	 * 
	 * @param nCount
	 *            时间数字
	 * @param nCountType
	 *            时间数字的单位类型: 0: 小时; 1: 分钟; 2: 秒
	 * @return 格式为 hh:mm:ss 的字符串
	 */
	public static String getCount2Time(long lCount, int nCountType) {
		String sTime = "00:00:00";
		long lTempCount = lCount;
		if (nCountType == 0) {
			lTempCount = lCount * 60 * 60;
		} else if (nCountType == 1) {
			lTempCount = lCount * 60;
		}

		long lHour = lTempCount / 3600;
		lTempCount = lTempCount % 3600;
		long lMin = lTempCount / 60;
		lTempCount = lTempCount % 60;
		long lSec = lTempCount;

		String sHour = lHour + "", sMin = lMin + "", sSec = lSec + "";
		if (lHour < 10) {
			sHour = "0" + sHour;
		} else if (lHour >= 24) {
			sHour = "00";
		}
		if (lMin < 10) {
			sMin = "0" + sMin;
		}
		if (lSec < 10) {
			sSec = "0" + sSec;
		}

		sTime = sHour + ":" + sMin + ":" + sSec;
		return sTime;
	}

	/**
	 * 数字转换为时间字符串2(该方法忽略24小时制)
	 * 
	 * @param nCount
	 *            时间数字
	 * @param nCountType
	 *            时间数字的单位类型: 0: 小时; 1: 分钟; 2: 秒
	 * @return 格式为 hh:mm:ss 的字符串
	 */
	public static String getCount2Time2(long lCount, int nCountType) {
		String sTime = "00:00:00";
		long nTempCount = lCount;
		if (nCountType == 0) {
			nTempCount = lCount * 60 * 60;
		} else if (nCountType == 1) {
			nTempCount = lCount * 60;
		}

		long nHour = nTempCount / 3600;
		nTempCount = nTempCount % 3600;
		long nMin = nTempCount / 60;
		nTempCount = nTempCount % 60;
		long nSec = nTempCount;

		String sHour = nHour + "", sMin = nMin + "", sSec = nSec + "";
		if (nHour < 10) {
			sHour = "0" + sHour;
		}
		if (nMin < 10) {
			sMin = "0" + sMin;
		}
		if (nSec < 10) {
			sSec = "0" + sSec;
		}

		sTime = sHour + ":" + sMin + ":" + sSec;
		return sTime;
	}

	/**
	 * true,false 转换成1，0
	 * 
	 * @param bValue
	 *            输入boolean值
	 * @return 返回 1，0
	 */
	public static int boolean2Int(boolean bValue) {
		int nRet;
		if (bValue) {
			nRet = 1;
		} else {
			nRet = 0;
		}
		return nRet;
	}

	/**
	 * 1，0转换成true,false
	 * 
	 * @param nValue
	 *            输入值
	 * @return 返回 true,false
	 */
	public static boolean int2Boolean(int nValue) {
		boolean bRet;
		if (nValue == 0) {
			bRet = false;
		} else {
			bRet = true;
		}
		return bRet;
	}

	/**
	 * true,false转换成checkBox的 checked
	 * 
	 * @param bValue
	 *            输入值
	 * @return true "checked", false ""
	 */
	public static String boolean2checkBox(boolean bValue) {
		String sRet;
		if (bValue) {
			sRet = "checked";
		} else {
			sRet = "";
		}
		return sRet;
	}

	/**
	 * checkbox的值转换成true,false
	 * 
	 * @param sValue
	 *            checkbox值
	 * @return 返回 true,false
	 */
	public static boolean checkBox2Boolean(String sValue) {
		boolean bRet;
		if (sValue == null) {
			bRet = false;
		} else {
			bRet = true;
		}
		return bRet;
	}

	/**
	 * checkbox的值转换成 0,1，选上1，未选0
	 * 
	 * @param sValue
	 *            checkbox值
	 * @return 返回 true,false
	 */
	public static int checkBox2Int(String sValue) {
		int nRet;
		if (sValue == null) {
			nRet = 0;
		} else {
			nRet = 1;
		}
		return nRet;
	}

	/**
	 * int 值转换成checkbox 1选上，0未选上
	 * 
	 * @param nValue
	 *            int值
	 * @return 返回 true,false
	 */
	public static String int2checkBox(int nValue) {
		String sRet;
		if (nValue == 1) {
			sRet = "checked";
		} else {
			sRet = "";
		}
		return sRet;
	}

	/**
	 * 根据给定的字符类型 年-月-日-时-分-秒 格式为"yyyy-mm-dd hh:mm:ss"来知道 这天是星期几。
	 * "yyyy-mm-dd hh:mm:ss",由调用者保证
	 * 
	 * @param currentTime
	 *            给定的当前日期时间，如果为null，则返回数字7,表示不成功
	 * @return 1表示星期一 ......0表示星期日
	 */
	@SuppressWarnings("deprecation")
	public static int getWeekAsString(String currentTime) {
		int nWeek = -1;
		if (currentTime != null) {
			int year = Integer.parseInt(currentTime.substring(0, 4));
			int month = Integer.parseInt(currentTime.substring(5, 7));
			int day = Integer.parseInt(currentTime.substring(8, 10));
			int hour = Integer.parseInt(currentTime.substring(11, 13));
			int min = Integer.parseInt(currentTime.substring(14, 16));
			int ss = Integer.parseInt(currentTime.substring(17, 19));
			// Calendar calendar = Calendar.getInstance();
			// calendar.set(year-1900,month-1,day,hour,min,ss);
			// nWeek = calendar.get(Calendar.DAY_OF_WEEK);
			Date date = new Date(year - 1900, month - 1, day, hour, min, ss);
			nWeek = date.getDay();

		} else {
			nWeek = 7; // 表示不成功
		}
		return nWeek;
	}

	/**
	 * 根据给定的当前日期计算前一天，后一天
	 * 
	 * @param currentDate
	 *            给定的当前日期，如果为null，则取系统的当前时间,格式为 "yyyy-mm-dd",由调用者保证
	 * @return "yyyy-mm-dd"格式的当前日期，前一天，后一天的日期值,数组的第一个值
	 *         是前一天的值，第二个值是当前的值，最后一个是后一天的值
	 */
	public static String[] get3DateStrings(String currentDate) {
		String[] retVal = new String[3];
		Calendar calendar = Calendar.getInstance();
		if (currentDate != null) {
			int year = Integer.parseInt(currentDate.substring(0, 4));
			int month = Integer.parseInt(currentDate.substring(5,
					currentDate.lastIndexOf("-")));
			int day = Integer.parseInt(currentDate.substring(currentDate
					.lastIndexOf("-") + 1));
			calendar.set(year, month - 1, day); // 月的下标从0开始，所以减1
		}
		retVal[1] = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		retVal[0] = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		retVal[2] = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
		return retVal;
	}

	/**
	 * 将给定的结果集用XML的格式输出
	 * 
	 * @param dataSet
	 *            给定的结果集
	 * @param dsName
	 *            输出包围数据的<XML>时使用的id值
	 * @param out
	 *            给定的输出器
	 * @return 输出的数据字符串
	 * @throws java.sql.SQLException
	 *             对给定的结果集操作时出现的异常
	 */
	public static String printResultSet2XML(ResultSet dataSet, String dsName)
			throws java.sql.SQLException {
		int count = 0;
		StringBuffer sXML = new StringBuffer(1024);
		if (dataSet != null) {
			// BufferedWriter writer = new BufferedWriter(out);
			ResultSetMetaData metaData = dataSet.getMetaData();
			int colNum = metaData.getColumnCount();
			String data = null;
			int type = -1;
			try {
				if (dsName != null) {
					sXML.append("<xml id=\"").append(dsName).append("\">\n");
				}
				sXML.append("<data>\n");
				while (dataSet.next()) {
					count++;
					sXML.append("\t\t<row>\n");
					for (int i = 1; i <= colNum; i++) {
						sXML.append("\t\t\t<");
						sXML.append(metaData.getColumnName(i).toLowerCase());
						sXML.append(">");

						type = metaData.getColumnType(i);
						if ((type == java.sql.Types.VARCHAR)
								|| (type == java.sql.Types.CHAR)) {
							sXML.append("<![CDATA[");
						}
						data = dataSet.getString(i);
						if (data != null) {
							sXML.append(data);
						}
						if ((type == java.sql.Types.VARCHAR)
								|| (type == java.sql.Types.CHAR)) {
							sXML.append("]]>");
						}

						sXML.append("</");
						sXML.append(metaData.getColumnName(i).toLowerCase());
						sXML.append(">\n");
					}
					sXML.append("\t\t</row>\n");
				}
				sXML.append("\t\t<recordNumber>").append(count)
						.append("</recordNumber>\n");
				sXML.append("</data>\n");
				if (dsName != null) {
					sXML.append("</xml>\n");
				}
			} catch (Exception ex) {
				count = 0;
			}
		}
		return sXML.toString();
	}

	/**
	 * 将给定的结果集用给定的输出器用XML的格式输出
	 * 
	 * @param dataSet
	 *            给定的结果集
	 * @param dsName
	 *            输出包围数据的<XML>时使用的id值
	 * @param out
	 *            给定的输出器
	 * @throws java.sql.SQLException
	 *             对给定的结果集操作时出现的异常
	 */
	public static void printResultSet2XML(ResultSet dataSet, String dsName,
			Writer out) throws java.sql.SQLException {
		try {
			BufferedWriter writer = new BufferedWriter(out);
			writer.write(printResultSet2XML(dataSet, dsName));
			writer.flush();
		} catch (Exception e) {
		}
	}

	/**
	 * 获取当前日期,格式为 年-月-日
	 * 
	 * @return 当前时间
	 */
	public static String getDateAsString(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}

		StringBuffer buffer = new StringBuffer(32);
		int year = cal.get(Calendar.YEAR);
		buffer.append(year).append("-");

		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			buffer.append("0");
		}
		buffer.append(month).append("-");

		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			buffer.append("0");
		}
		buffer.append(day);

		return buffer.toString();
	}

	public static String getCurentDateAsString() {
		return getDateAsString(null);
	}

	/**
	 * 格式化日期为: 年-月-日 的形式
	 * 
	 * @param date
	 *            日期时间的字符串
	 * @return 结果字符串
	 */
	public static String getStringWithDate(Date tDate) {
		if (tDate == null) {
			tDate = new Date();
		}
		DateFormat dateFormat = DateFormat.getDateInstance();
		String sDate = dateFormat.format(tDate);
		StringBuffer buffer = new StringBuffer();
		int mid = sDate.indexOf("-");
		int end = sDate.lastIndexOf("-");
		// 年
		String sYear = sDate.substring(0, mid);
		// 月
		String sMonth = sDate.substring(mid + 1, end);
		// int nMonth=Integer.parseInt(sMonth)-1;
		int nMonth = Integer.parseInt(sMonth);
		if (nMonth < 10)
			sMonth = "0" + String.valueOf(nMonth);
		// 日
		String sDay = sDate.substring(end + 1);
		int nDay = Integer.parseInt(sDay);
		if (nDay < 10)
			sDay = "0" + String.valueOf(nDay);
		// 年-月-日
		buffer.append(sYear).append("-").append(sMonth).append("-")
				.append(sDay);

		return buffer.toString();
	}

	/**
	 * 获取当前时间,格式为 时：分：妙
	 * 
	 * @return 当前时间
	 */
	public static String getCurentTimeAsString() {
		StringBuffer buffer = new StringBuffer(32);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			buffer.append("0");
		}
		buffer.append(hour).append(":");

		int minute = cal.get(Calendar.MINUTE);
		if (minute < 10) {
			buffer.append("0");
		}
		buffer.append(minute).append(":");

		int second = cal.get(Calendar.SECOND);
		if (second < 10) {
			buffer.append("0");
		}
		buffer.append(second);

		return buffer.toString();
	}

	/**
	 * 获取当前日期时间,格式为 年-月-日 时：分：妙
	 * 
	 * @return 当前时间
	 */
	public static String getCurentDateTimeAsString() {
		StringBuffer buffer = new StringBuffer(32);
		buffer.append(getCurentDateAsString()).append(" ")
				.append(getCurentTimeAsString());
		return buffer.toString();
	}

	/**
	 * 转换时间日期为字符串 如果nDateTimeType == 0, 格式为 yyyy-mm-dd 如果nDateTimeType == 1, 格式为
	 * hh:mm:ss 如果nDateTimeType == 其他, 格式为 yyyy-mm-dd hh:mm:ss
	 * 
	 * @param calendar
	 * @param nDateTimeType
	 *            : 0, 只日期; 1, 只时间; 其他: 日期时间
	 * @return
	 */
	public static String getCalendarAsString(Calendar calendar,
			int nDateTimeType) {
		String sYear = String.valueOf(calendar.get(Calendar.YEAR));
		String sMonth = (calendar.get(Calendar.MONTH) + 1) >= 10 ? String
				.valueOf((calendar.get(Calendar.MONTH) + 1)) : "0"
				+ (calendar.get(Calendar.MONTH) + 1);
		String sDay = calendar.get(Calendar.DAY_OF_MONTH) >= 10 ? String
				.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) : "0"
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String sHour = calendar.get(Calendar.HOUR_OF_DAY) >= 10 ? String
				.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) : "0"
				+ String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String sMin = calendar.get(Calendar.MINUTE) >= 10 ? String
				.valueOf(calendar.get(Calendar.MINUTE)) : "0"
				+ String.valueOf(calendar.get(Calendar.MINUTE));
		String sSecond = calendar.get(Calendar.SECOND) >= 10 ? String
				.valueOf(calendar.get(Calendar.SECOND)) : "0"
				+ String.valueOf(calendar.get(Calendar.SECOND));

		String sReturnDateTime = sYear + "-" + sMonth + "-" + sDay + " "
				+ sHour + ":" + sMin + ":" + sSecond;

		if (nDateTimeType == 0) {
			sReturnDateTime = sYear + "-" + sMonth + "-" + sDay;
		}
		if (nDateTimeType == 1) {
			sReturnDateTime = sHour + ":" + sMin + ":" + sSecond;
		}

		return sReturnDateTime;
	}

	/**
	 * 转换字符串为时间日期 如果nDateTimeType == 0, 格式为 yyyy-mm-dd 如果nDateTimeType == 1, 格式为
	 * hh:mm:ss 如果nDateTimeType == 其他, 格式为 yyyy-mm-dd hh:mm:ss
	 * 
	 * @param sDateTime
	 * @param nDateTimeType
	 *            0, 只日期; 1, 只时间; 其他: 日期时间
	 * @return
	 */
	public static Calendar getStringAsCalendar(String sDateTime,
			int nDateTimeType) {
		Calendar calendar = Calendar.getInstance();
		if (sDateTime != null && (!"".equals(sDateTime))) {
			String sDate = splitString2Array(sDateTime, " ")[0].trim();
			String sTime = splitString2Array(sDateTime, " ")[1].trim();

			if (nDateTimeType == 0) {
				String sDate2[] = splitString2Array(sDate, "-");
				calendar.set(Calendar.YEAR, Integer.parseInt(sDate2[0]));
				calendar.set(Calendar.MONTH, (Integer.parseInt(sDate2[1]) - 1));
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate2[2]));
			} else if (nDateTimeType == 1) {
				String sTime2[] = splitString2Array(sTime, ":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sTime2[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(sTime2[1]));
				calendar.set(Calendar.SECOND, Integer.parseInt(sTime2[2]));
			} else {
				String sDate2[] = splitString2Array(sDate, "-");
				String sTime2[] = splitString2Array(sTime, ":");
				calendar.set(Calendar.YEAR, Integer.parseInt(sDate2[0]));
				calendar.set(Calendar.MONTH, (Integer.parseInt(sDate2[1]) - 1));
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate2[2]));
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sTime2[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(sTime2[1]));
				calendar.set(Calendar.SECOND, Integer.parseInt(sTime2[2]));
			}
		}

		return calendar;
	}

	/**
	 * 返回制定时间格式的字符串
	 * 
	 * @param sCurDateTime
	 *            如果nDateTimeType == 0, 格式为 yyyy-mm-dd 如果nDateTimeType == 1, 格式为
	 *            hh:mm:ss 如果nDateTimeType == 其他, 格式为 yyyy-mm-dd hh:mm:ss
	 * @param nDateTimeType
	 *            日期时间的格式: 0, 只日期; 1, 只时间; 其他: 日期时间
	 * @return 如果nDateTimeType == 0, 格式为 yyyymmdd 如果nDateTimeType == 1, 格式为
	 *         hhmmss 如果nDateTimeType == 其他, 格式为 yyyymmddhhmmss
	 */
	public static Long getStrDateTime2Long(String sCurDateTime,
			int nDateTimeType) {
		if ("".equals(sCurDateTime)) {
			sCurDateTime = getCurentDateTimeAsString();
			nDateTimeType = -1;
		}

		sCurDateTime = replaceString(sCurDateTime, " ", "").trim();

		if (nDateTimeType == 0) {
			sCurDateTime = replaceString(sCurDateTime, "-", "");
		} else if (nDateTimeType == 1) {
			sCurDateTime = replaceString(sCurDateTime, ":", "");
		} else {
			sCurDateTime = replaceString(sCurDateTime, "-", "");
			sCurDateTime = replaceString(sCurDateTime, ":", "");
		}

		return Long.parseLong(sCurDateTime);
	}

	/**
	 * id数组转换成一个字符串
	 * 
	 * @param sArray
	 *            id数组
	 * @return 拼凑好的字符串
	 */
	public static String stringArray2SqlIn(String[] sArray) {
		StringBuffer sBuffer = new StringBuffer(256);
		if (sArray == null) {
			return sBuffer.toString();
		}

		int nLen = sArray.length;
		for (int i = 0; i < nLen; i++) {
			if (i == 0) {
				sBuffer.append(sArray[i]);
			} else {
				sBuffer.append(",").append(sArray[i]);
			}
		}
		return sBuffer.toString();
	}

	/**
	 * list集合转换成一个字符串
	 * 
	 * @param list
	 *            list集合
	 * @return 拼凑好的字符串
	 */
	public static String stringList2SqlInt(List<?> list) {
		StringBuffer sBuffer = new StringBuffer(256);
		if (list == null || list.size() <= 0) {
			return sBuffer.toString();
		}

		int nLen = list.size();
		for (int i = 0; i < nLen; i++) {
			String id = (String) list.get(i);
			if (i == 0) {
				sBuffer.append(id);
			} else {
				sBuffer.append(",").append(id);
			}
		}

		return sBuffer.toString();
	}

	/**
	 * list集合转换成一个字符串
	 * 
	 * @param list
	 *            list集合
	 * @return 拼凑好的字符串
	 */
	public static String intList2SqlInt(List<?> list) {
		StringBuffer sBuffer = new StringBuffer(256);
		if (list == null || list.size() <= 0) {
			return sBuffer.toString();
		}

		int nLen = list.size();
		for (int i = 0; i < nLen; i++) {
			Integer id = (Integer) list.get(i);
			if (i == 0) {
				sBuffer.append(id);
			} else {
				sBuffer.append(",").append(id);
			}
		}

		return sBuffer.toString();
	}

	/**
	 * 替换字符串
	 * 
	 * @param strSource
	 *            源字符串
	 * @param strOld
	 *            要替换的
	 * @param strNew
	 *            替换成
	 * @return 替换后的字符串
	 */
	public static String replaceString(String strSource, String strOld,
			String strNew) {
		if (strSource == null)
			return null;

		int length = strSource.length();
		if (length < 1)
			return strSource;

		int offset = 0;
		int index = strSource.indexOf(strOld, offset);
		if (index < 0)
			return strSource;

		int oldLength = strOld.length();
		if (oldLength < 1)
			return strSource;

		StringBuffer strBuff = new StringBuffer(1024);
		while (index > -1) {
			strBuff.append(strSource.substring(offset, index));
			offset = index + oldLength;
			strBuff.append(strNew);

			index = strSource.indexOf(strOld, offset);
		}
		if (offset < length) {
			strBuff.append(strSource.substring(offset, length));
		}
		return strBuff.toString();
	}

	/**
	 * 将source中的from串替换未to
	 * 
	 * @param source
	 *            要进行替换操作的字符串
	 * @param from
	 *            要替换的串
	 * @param to
	 *            取代原来的串
	 * @return 结果
	 */
	public static String replace(String source, String from, String to) {
		if (!from.equals(to)) {
			int index = source.indexOf(from);
			if (index >= 0) {
				source = source.substring(0, index) + to
						+ source.substring(index + from.length());
				source = replace(source, from, to);
			}
		}
		return source;
	}

	/**
	 * 根据秒返回时分秒
	 * 
	 * @param nSecond
	 * @return
	 */
	public static String getTimeStringFromSecond(int nSecondAll) {
		int nHour = (int) (nSecondAll / 3600);
		int nMinute = (int) ((nSecondAll % 3600) / 60);
		int nSecond = (int) (nSecondAll % 60);
		StringBuffer sRet = new StringBuffer(128);

		if (nHour < 10) {
			sRet.append("0");
		}
		sRet.append(nHour);
		sRet.append(":");
		if (nMinute < 10) {
			sRet.append("0");
		}
		sRet.append(nMinute);
		sRet.append(":");
		if (nSecond < 10) {
			sRet.append("0");
		}
		sRet.append(nSecond);
		return sRet.toString();
	}

	/**
	 * 将ip地址转换为一个字符串
	 */
	public static String ip2String(String ipAddr) {
		String retVal = "";
		String temp = "";
		temp = ipAddr;

		String[] ipSegments = null;

		ipSegments = splitString2Array(temp, ".");

		for (int i = 0; i < ipSegments.length; i++) {
			{
				if (ipSegments[i].length() == 1)
					retVal = retVal + "00" + ipSegments[i];

				if (ipSegments[i].length() == 2)
					retVal = retVal + "0" + ipSegments[i];

				if (ipSegments[i].length() == 3)
					retVal = retVal + ipSegments[i];

			}

		}
		return retVal;
	}

	/**
	 * 在content中查找第count次出现seach的索引,从前向后查找
	 * 
	 * @param content
	 * @param seach
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public static int indexOfCount(String content, String seach, int count)
			throws Exception {
		String startText = "";
		int index = -1;
		for (int i = 1; i <= count; i++) {
			index = content.indexOf(seach);

			if (index > -1) {
				if (count > 1) {
					String text = content.substring(0, index + seach.length());
					startText = startText + text;
					content = StringUtils.removeStart(content, text);
					index = startText.length() - seach.length();
				}
			} else {
				break;
			}
		}

		return index;
	}

	/**
	 * 在content中查找第count次出现seach的索引,从后向前查找
	 * 
	 * @param content
	 * @param seach
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public static int lastIndexOfCount(String content, String seach, int count)
			throws Exception {
		int index = -1;
		for (int i = 1; i <= count; i++) {
			index = content.lastIndexOf(seach);

			if (index > -1) {
				if (count > 1) {
					content = content.substring(0, index);
					index = content.length();
				}
			} else {
				break;
			}
		}

		return index;
	}

	public static String getChineseGBK(String string) {
		byte[] BYTE = null;
		try {
			BYTE = string.getBytes("ISO_8859_1");
			string = new String(BYTE, "GBK");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return string;
	}

	public static String getChineseUTF8(String string) {
		byte[] BYTE = null;
		try {
			BYTE = string.getBytes("ISO_8859_1");
			string = new String(BYTE, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return string;
	}

	public static String getLocalLanguage(String string) {
		return getChineseUTF8(string);
	}

	public static String toHex(byte value[]) {
		return toHexString(value, false);
	}

	private static String toHexString(byte data[], boolean isFormat) {
		StringBuffer sb = new StringBuffer(data.length * 2
				+ (isFormat ? data.length + data.length / 16 : 0));
		for (int i = 0; i < data.length; i++) {
			int tmp = data[i] & 0xff;
			int s1 = tmp / 16;
			int s2 = tmp % 16;
			if (s1 < 10)
				sb.append((char) (s1 + 48));
			else if (s1 >= 10)
				sb.append((char) (s1 + 55));
			if (s2 < 10)
				sb.append((char) (s2 + 48));
			else if (s2 >= 10)
				sb.append((char) (s2 + 55));
			if (!isFormat)
				continue;
			sb.append(" ");
			if ((i + 1) % 16 == 0)
				sb.append("\n");
		}

		return sb.toString();
	}

	public static int ByteArrayToInt(byte ba[]) throws NumberFormatException {
		if (ba == null || ba.length == 0)
			return 0;
		if (ba.length > 4)
			throw new NumberFormatException("input contains more than 4 bytes");
		int temp = 0;
		int sum = ba[0] & 0xff;
		for (int i = 1; i < ba.length; i++) {
			temp = ba[i] & 0xff;
			sum = sum * 256 + temp;
		}

		return sum;
	}

	public static String isNull(Object isNullParam) {
		if (isNullParam == null) {
			return "";
		}
		return String.valueOf(isNullParam);
	}

	/**
	 * 去除字符串中html代码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String htmlToText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		// java.util.regex.Pattern p_html;
		// java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			// String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			// p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
			// m_html = p_html.matcher(htmlStr);

			// htmlStr = m_html.replaceAll(""); //过滤html标签
			// 过滤html标签
			htmlStr = htmlStr.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
					.replaceAll("</[a-zA-Z]+[1-9]?>", "");
			htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");// 去除字符串中的空格,回车,换行符,制表符
			htmlStr = htmlStr.replaceAll("&nbsp;", " ");// 去除字符串中的空格
			htmlStr = replaceHtmlNote(htmlStr);
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return getContent(textStr);// 返回文本字符串
	}

	/**
	 * 去除字符串中的html注释
	 * */
	public static String removeHtmlComment(String content) {
		StringBuffer sb = new StringBuffer();
		while (true) {
			int startIndex = content.indexOf("<!--");// 找到content中的第一个<!--标记
			if (startIndex > -1) {
				int endIndex = content.indexOf("-->", startIndex);// 找到content中的第一个-->标记，从startIndex到endIndex中的是注释内容
				String startText = content.substring(0, startIndex);// 截取<!--之前的内容
				String img = content.substring(startIndex, endIndex + 3);// <!--
																			// -->之中的内容
				String startContent = startText + img;
				content = StringUtils.remove(content, startContent);
				sb.append(startText.trim());
			} else {// 没有注释
				sb.append(content);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 把一个字符串[@@]内的字符串替换成空值
	 * 
	 * @param content
	 *            传进的字符串
	 * @return 替换成空值后的字符串
	 */
	public static String clearFaceCode(String content) {
		while (true) {
			int startIndex = content.indexOf("[@");
			int endIndex = content.indexOf("@]");

			if (startIndex != -1 && endIndex != -1) {
				content = content.replace(
						content.substring(startIndex, (endIndex + 2)), "");
			} else {
				break;
			}
		}
		return content;
	}

	/**
	 * 取出html中的注释标签
	 * 
	 * @author : GaoYang
	 * @param htmlString
	 * @return
	 */
	public static String replaceHtmlNote(String htmlString) {
		if (htmlString.indexOf("<!--") >= 0 && htmlString.indexOf("-->") > 0) {
			return replaceHtmlNote(htmlString.substring(0,
					htmlString.indexOf("<!--"))
					+ htmlString.substring(htmlString.indexOf("-->") + 3));
		} else {
			return htmlString;
		}
	}

	/**
	 * html转义源符号
	 * 
	 * @param s
	 * @return
	 */
	public static String getContent(String s) {
		s = s.replaceAll("&ensp;", " ");
		s = s.replaceAll("&nbsp;", " ");
		s = s.replaceAll("&emsp;", "　");
		s = s.replaceAll("&reg;", "®");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&ldquo;", "“");
		s = s.replaceAll("&rdquo;", "”");
		s = s.replaceAll("&quot;", "“");
		s = s.replaceAll("&rsquo;", "’");
		s = s.replaceAll("&lsquo;", "‘");
		s = s.replaceAll("&mdash;", "—");
		s = s.replaceAll("&ndash;", "–");
		s = s.replaceAll("&middot;", "·");
		s = s.replaceAll("&trade;", "™");
		s = s.replaceAll("&copy;", "©");
		s = s.replaceAll("&hellip;", "…");
		s = s.replaceAll("<br>", "\r\n");
		s = s.replaceAll("<br/>", "\r\n");
		s = s.replaceAll("<br />", "\r\n");
		s = s.replaceAll("  ", "　");
		s = s.replaceAll("&amp;", "&");
		return s;
	}

	/**
	 * html转义特殊符号
	 * 
	 * @param s
	 * @return
	 */
	public static String getHtmlContent(String s) {
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll("　", " ");
		s = s.replaceAll("…", "&hellip;");
		s = s.replaceAll("®", "&reg;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("\r\n", "<br>");
		s = s.replaceAll("\r\n", "<br/>");
		s = s.replaceAll("\r\n", "<br />");
		s = s.replaceAll("“", "&ldquo;");
		s = s.replaceAll("”", "&rdquo;");
		s = s.replaceAll("“", "&quot;");
		s = s.replaceAll("’", "&rsquo;");
		s = s.replaceAll("‘", "&lsquo;");
		s = s.replaceAll("—", "&mdash;");
		s = s.replaceAll("–", "&ndash;");
		s = s.replaceAll("·", "&middot;");
		s = s.replaceAll("™", "&trade;");
		s = s.replaceAll("©", "&copy;");
		return s;
	}

	/**
	 * 根据文件名获取文件名称和后缀名
	 * 
	 * @param filename
	 * @return str[0]:文件名称,str[1]:后缀名
	 */
	public static String[] getNameAndSuffix(String filename) {
		String[] str = null;
		int index = filename.lastIndexOf(".");
		if (index > -1) {
			str = new String[2];
			str[0] = filename.substring(0, index);
			str[1] = filename.substring(index + 1);
		} else {
			str = new String[1];
			str[0] = filename;
		}

		return str;
	}

	public static String getShowmobile(String userName, String tel) {

		if (StringUtils.isNotEmpty(userName)) {
			if (isMobileNO(userName)) {
				userName = userName.substring(0, 3) + "****"
						+ userName.substring(7, 11);
			}
		} else {
			if (isMobileNO(userName)) {
				userName = tel.substring(0, 3) + "****" + tel.substring(7, 11);
			}
		}
		return userName;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();

	}

	public static synchronized String create10RandomNumByTimes()
			throws Exception {
		Thread.sleep(1);
		StringBuilder sdate = new StringBuilder();
		sdate.append(System.currentTimeMillis());
		return sdate.toString().substring(2, 12);
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
	 * 文字列变换
	 *
	 * @param str
	 *            输入文字列
	 * @return pInt
	 */
	public static int objToInt(Object str)
	{
		return str == null || "".equals(str) ? 0 : new BigDecimal(String.valueOf(str))
				.intValue();
	}

	/**
	 * 检查文字列是<code>null</code。
	 *
	 * @param str
	 *            文字列
	 * @return true或者false
	 */
	public static boolean isEmpty(Object str)
	{
		if (str == null || objToStr(str).length() == 0)
			return true;
		else
			return false;
	}
}
