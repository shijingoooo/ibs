package com.capinfo.framework.common.util;

import java.util.ArrayList;
import java.util.List;

public class RandomUtil 
{
	/**
	 * 生成随机数
	 * @param nBit
	 * 随机数的位数
	 * @param mode
	 * 随机数的模式：1：纯数字，其他：数字和字母组合
	 * @return
	 * 随机数
	 */
	public static String createRandomNum(int nBit, int mode) {
		
		if(nBit < 1 || nBit > 50){
			nBit = 8;
		}
		
		String sStr = "1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String[] str = new String[30];
		
		if(mode == 1){
			str = StringUtil.splitString2Array(sStr.substring(0,17), ",");
		}else{
			str = StringUtil.splitString2Array(sStr, ",");
		}
		
		String sRet = "";
		for(int i=0; i<nBit; i++) {
			sRet = sRet + str[getRangeNum(0,str.length-1)];
		}
		
		return sRet;
	}
	
	public static String createRandomNumUpper(int nBit, int mode) {
		
		if(nBit < 1 || nBit > 50){
			nBit = 8;
		}
		
		String sStr = "1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] str = new String[30];
		
		if(mode == 1){
			str = StringUtil.splitString2Array(sStr.substring(0,17), ",");
		}else{
			str = StringUtil.splitString2Array(sStr, ",");
		}
		
		String sRet = "";
		for(int i=0; i<nBit; i++){
			sRet = sRet + str[getRangeNum(0,str.length-1)];
		}
		
		return sRet;
	}
	
	/**
	 * 获得任意区间内的随机数
	 * @param nLow
	 * 区间最小值
	 * @param nHigh
	 * 区间最大值
	 * @return
	 * 随即整数
	 */
	public static int getRangeNum(int nLow, int nHigh)
	{
		double dRet = Math.random() * (1 + nHigh - nLow) + nLow;
		return (int)Math.floor(dRet);
	}
	
	/**
	 * 将十进制数字转义成二，八，十六，六十二进制
	 * @param 	source		原始数据
	 * @param 	digit			位数，只能2,8,16,62四种
	 * @return		结果
	 * @throws Exception
	 */
	public static String decimalistToByte(Long source, int digit) throws Exception
	{
		String result = "";
		if(digit == 2)
		{
			result = Long.toBinaryString(source);  
		}
		else if(digit == 8)
		{
			result = Long.toOctalString(source);  
		}
		else if(digit == 16)
		{
			result = Long.toHexString(source);  
		}
		else if(digit == 62)
		{
			result = tenToSixtyTwo(source, null);  
		}
		return result;
	}
	
	/**
	 * 将二，八，十六，六十二进制转义成十进制
	 * @param 	source		原始数据
	 * @param 	digit			位数，只能2,8,16,62四种
	 * @return		结果
	 * @throws Exception
	 */
	public static long byteToDecimalist(String source, int digit) throws Exception
	{
		long result = -1;
		if(digit == 2 || digit == 8 || digit == 16)
		{
			result = Long.parseLong(source, digit);
		}
		else if(digit == 62)
		{
			result = sixtyTwoToTen(source);
		}
		return result;
	}
	
	/**
	  * 十进制转六十二进制
	  * @author 		:	GaoYang
	  * @param i
	  * @param list
	  * @return
	  */
      private static String tenToSixtyTwo(long i,List<Object> list)
      {
    	  if(list==null){
    		  list = new ArrayList<Object>();
    	  }
    	  long result = i/62;
    	 long val = (long)i%62;
    	  list.add(Convert((int)val));
    	  if(result==0){
    		  StringBuilder returnVal = new StringBuilder();
    		  for(int j=list.size()-1;j>=0;j--){
    			  returnVal.append(list.get(j));
    		  }
    		  return returnVal.toString();
    	  }else{
    		  return tenToSixtyTwo(result,list);
    	  }
      }
	      
      /**
       * 六十二进制转十进制
       * @author 		:	GaoYang
       * @param s
       * @return
     * @throws Exception 
       */
      private static long sixtyTwoToTen(String s) throws Exception{
    	  long sum = 0 ;
    	  char cs[] = s.toCharArray();
    	  for(long i=0;i<cs.length;i++){
    		  long t = Convert(cs[(int)i]);
    		  if(i==cs.length-1){
    			  long temp = sum;
    			  sum += t;
    			  if(sum<temp){
    				  throw new Exception("数值超过long类型");
    			  }
    			  if(sum<0){
    	    		  throw new Exception("数值超过long类型");
    	    	  }
    		  }else if(i==cs.length-2){
    			  long temp = sum;
    			  sum+=t*62;
    			  if(sum<temp){
    				  throw new Exception("数值超过long类型");
    			  }
    		  }
    		  else{
    			  long add = 62;
    				  for(long m = 0;m<cs.length-i-2;m++){
    					  long temp = add;
    					  add*=62;
    					  if(add<temp){
    	    				  throw new Exception("数值超过long类型");
    	    			  }
    				  }
    				  if(add*t<add&&t!=0){
    					  throw new Exception("数值超过long类型");
    				  }else {
    					  long temp = sum;
    					  sum+=add*t;
    					  if(sum<temp){
    	    				  throw new Exception("数值超过long类型");
    	    			  }
    				  }
    		  }
    	  }
    	  if(sum<0){
    		  throw new Exception("数值超过long类型");
    	  }
    	  return sum;
      }
	
	/**
	 * 当给定的字符串不能满足位数时用(数字或字母填补)
	 * @param 	source	源字符串
	 * @param 	toChar	用这个变量填补
	 * @param 	digit		指定的位数
	 * @param 	place		在最前或是最后填补begin:前面填补，end:后面填补
	 * @return		结果
	 * @throws Exception
	 */
	public static String paddedChar(String source, String toChar, int digit, String place) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		
		if(place.equals("begin"))
		{
			for(int i=0; i<digit-source.length(); i++)
			{
				sb.append(toChar);
			}
			sb.append(source);
		}
		else if(place.equals("end"))
		{
			sb.append(source);
			for(int i=0; i<digit-source.length(); i++)
			{
				sb.append(toChar);
			}
		}
		
		return sb.toString();
	}
	
	private static char Convert(int val)
    {
        switch (val)
        {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';

            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            case 16:
                return 'g';
            case 17:
                return 'h';
            case 18:
                return 'i';
            case 19:
                return 'j';
            case 20:
                return 'k';
            case 21:
                return 'l';
            case 22:
                return 'm';
            case 23:
                return 'n';
            case 24:
                return 'o';
            case 25:
                return 'p';
            case 26:
                return 'q';
            case 27:
                return 'r';
            case 28:
                return 's';
            case 29:
                return 't';
            case 30:
                return 'u';
            case 31:
                return 'v';
            case 32:
                return 'w';
            case 33:
                return 'x';
            case 34:
                return 'y';
            case 35:
                return 'z';

            case 36:
                return 'A';
            case 37:
                return 'B';
            case 38:
                return 'C';
            case 39:
                return 'D';
            case 40:
                return 'E';
            case 41:
                return 'F';
            case 42:
                return 'G';
            case 43:
                return 'H';
            case 44:
                return 'I';
            case 45:
                return 'J';
            case 46:
                return 'K';
            case 47:
                return 'L';
            case 48:
                return 'M';
            case 49:
                return 'N';
            case 50:
                return 'O';
            case 51:
                return 'P';
            case 52:
                return 'Q';
            case 53:
                return 'R';
            case 54:
                return 'S';
            case 55:
                return 'T';
            case 56:
                return 'U';
            case 57:
                return 'V';
            case 58:
                return 'W';
            case 59:
                return 'X';
            case 60:
                return 'Y';
            case 61:
                return 'Z';
        }
        return '0';
    }
	 
	 private static long Convert(char c)
     {
         switch (c)
         {
             case '0':
                 return 0;
             case '1':
                 return 1;
             case '2':
                 return 2;
             case '3':
                 return 3;
             case '4':
                 return 4;
             case '5':
                 return 5;
             case '6':
                 return 6;
             case '7':
                 return 7;
             case '8':
                 return 8;
             case '9':
                 return 9;
 
             case 'a':
                 return 10;
             case 'b':
                 return 11;
             case 'c':
                 return 12;
             case 'd':
                 return 13;
             case 'e':
                 return 14;
             case 'f':
                 return 15;
             case 'g':
                 return 16;
             case 'h':
                 return 17;
             case 'i':
                 return 18;
             case 'j':
                 return 19;
             case 'k':
                 return 20;
             case 'l':
                 return 21;
             case 'm':
                 return 22;
             case 'n':
                 return 23;
             case 'o':
                 return 24;
             case 'p':
                 return 25;
             case 'q':
                 return 26;
             case 'r':
                 return 27;
             case 's':
                 return 28;
             case 't':
                 return 29;
             case 'u':
                 return 30;
             case 'v':
                 return 31;
             case 'w':
                 return 32;
             case 'x':
                 return 33;
             case 'y':
                 return 34;
             case 'z':
                 return 35;
 
             case 'A':
                 return 36;
             case 'B':
                 return 37;
             case 'C':
                 return 38;
             case 'D':
                 return 39;
             case 'E':
                 return 40;
             case 'F':
                 return 41;
             case 'G':
                 return 42;
             case 'H':
                 return 43;
             case 'I':
                 return 44;
             case 'J':
                 return 45;
             case 'K':
                 return 46;
             case 'L':
                 return 47;
             case 'M':
                 return 48;
             case 'N':
                 return 49;
             case 'O':
                 return 50;
             case 'P':
                 return 51;
             case 'Q':
                 return 52;
             case 'R':
                 return 53;
             case 'S':
                 return 54;
             case 'T':
                 return 55;
             case 'U':
                 return 56;
             case 'V':
                 return 57;
             case 'W':
                 return 58;
             case 'X':
                 return 59;
             case 'Y':
                 return 60;
             case 'Z':
                 return 61;
         }
         return 0;
     }
}
