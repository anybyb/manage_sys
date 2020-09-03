package com.marsmob.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/***
 * <p>Title: Commons.java</p>
 * <p>Description:工具类，提供常用的方法，如日期转换，获取随机数 </p>
 * @author wcc
 * @version 1.0
 */
public class Commons {
    private Commons() {

    }

    /**
     * 获取系统的当前时间
     * dateFormat 时间格式
     * @return
     */
    public static String getNowTime(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        String nowTime = simpleDateFormat.format(date);
        return nowTime;
    }

    /***
     * 判断参数是否全是整数数字(非数字，小数均无法通过)
     * @param str
     *        字符串
     * @return 数字字符串返回true.否则返回false
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(str);
        boolean b = matcher.matches();
        return b;
    }

    /**
     * 判断是否为数字或数字字符串
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        return str.matches("\\d+");
    }

    /***
     * @function：字符串转为数字。
     * @param ：字符串
     * @return ：int
     * @describe：如果转换成功，返回一个整型的数字；如果转换失败，返回-1.
     */
    public static int stringToInt(String parameter) {
        int i = -1;
        try {
            if ( parameter != null && !"".equals(parameter) ) {
                boolean b = Commons.isNumeric(parameter);
                if ( b ) {
                    i = Integer.parseInt(parameter);
                }
            }
        }
        catch (Exception e) {
            // 转换出现异常 比如输入的数据超出int范围
            i = -1;
        }
        return i;
    }
    
    
    /**
     * 随机获取字符串 【a-z0-9A-Z】
     * @param length
     *        随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomNumber(int length) {
        if ( length <= 0 ) {
            return "";
        }
        char[] randomChar = {  
        		'D','E', 'a', 'G','b',  'A','0', 'c', 'J','d','L','1', 'Q','I','g','X','I','h','Y','i','9', 'j','U','B','k','l',
                'm','W','n','3', 'o','C','p', '4',  'q','r','K','6', 'V','s','8','P', 't','u', 'R','v','7', 'w','N','x','M', '5', 'T','y','z',
                'F','2', 'H', 'e','f','O', 'S','Z'
                };
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }
    

    /***
     * @function：字符串转为数字。
     * @param ：字符串
     * @return ：int
     * @describe：如果转换成功，返回一个整型的数字；如果转换失败，返回-1.
     */
    public static long stringToLong(String parameter) { 
        long i = -1L;
        if ( parameter != null && !"".equals(parameter) ) {
            boolean b = Commons.isNumeric(parameter);
            if ( b ) {
                i = Integer.parseInt(parameter);
            }
        }
        return i;
    }

    /***
     * 是否为空字符串
     * @param str
     * @return
     */
    public static String isNull(String str) {
        String s = str.replaceAll("\\s", "");
        return s;
    }

    /**
     * 随机获取UUID字符串(无中划线)
     * @return UUID字符串
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
                + uuid.substring(24);
    }
   

    /**
     * 随机获取字符串  全小写字母
     * @param length
     *        随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomLowercase(int length) {
        if ( length <= 0 ) {
            return "";
        }
        char[] randomChar = { 'a',  'x', 'c',  't', 'e', 'b','f',  'h','q','g', 'i', 'j', 'k', 'l',  'n',
        		'o', 'm','p', 'r',  'u', 'v', 'w',  'y','s', 'z','d'};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }
    
    
    /**
     * 随机获取字符串  全小写字母和数字【0-9a-z】
     * @param length
     *        随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomLowercaseOrNumber(int length) {
        if ( length <= 0 ) {
            return "";
        }
        char[] randomChar = { 'a',  'x','1', 'c',  't', 'e', 'b','f', '8', 'h','q','g', 'i','2', 'j', '5','k', 'l','3',  'n',
        		'o', 'm','p','6', 'r',  'u', 'v', 'w', '4', 'y','s', '7','z','d','0','9'};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }
    
    
    
    
    /**
     * 随机获取字符串   全数字
     * @param length
     *        随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        if ( length <= 0 ) {
            return "";
        }
        char[] randomChar = { '3', '1', '2', '0', '4', '5', '7', '6', '9', '8'};
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }
    
    

    /**
     * 根据指定长度 分隔字符串
     * @param str
     *        需要处理的字符串
     * @param length
     *        分隔长度
     * @return 字符串集合
     */
    public static List<String> splitString(String str, int length) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i += length) {
            int endIndex = i + length;
            if ( endIndex <= str.length() ) {
                list.add(str.substring(i, i + length));
            }
            else {
                list.add(str.substring(i, str.length() - 1));
            }
        }
        return list;
    }

    /**
     * 将字符串List转化为字符串，以分隔符间隔.
     * @param list
     *        需要处理的List.
     * @param separator
     *        分隔符.
     * @return 转化后的字符串
     */
    public static String toString(List<String> list, String separator) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : list) {
            stringBuffer.append(separator + str);
        }
        stringBuffer.deleteCharAt(0);
        return stringBuffer.toString();
    }

    // 编码转换
    public static String Bytes(String str) {
        try {
            str = new String(str.getBytes("ISO-8859-1"), "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 文件编码转换
     * @param downloadFileName 要转换的字符串
     * @return
     */
    public static String getFileEcodingName(String downloadFileName) {
        try {
            downloadFileName = new String(downloadFileName.getBytes(), "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadFileName;
    }

    public static String URLencode(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String URLdecode(String str) {
        try {
            str = URLDecoder.decode(str, "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    /***
     * 校验邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
    	if(!StringUtils.isBlank(email)){
    		    //String str = "^([a-zA-Z0-9]*[-_.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
    		    String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    		    Pattern p = Pattern.compile(str);
    	        Matcher m = p.matcher(email);
    	        return m.matches();
    	}else{
    		return false;
    	}
      
    }

    /***
     * 字符串过滤<br>
     * 过滤掉字符串中的<,>,符号。<br>
     * 将其转为& lt "& gt"符号
     * @param s 要过滤的字符串
     * @return 过滤后的String
     */
    public static String strFiltration(String s) {
        s = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        System.out.println("过滤后" + s);
        return s;
    }

    /**
     * 字符串转为日期
     * yyyy-MM-dd
     * @param sDate
     * @return
     */
    public static Date String2Date(String sDate) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(sDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /***
     * 字符串转为日期
     * @author Administrator
     * @creaetime 2014年10月23日 下午5:16:24
     * @param sDate字符串类型的日期
     * @param fomat 格式化 如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date String2Date(String sDate,String fomat) {
        SimpleDateFormat sf = new SimpleDateFormat(fomat);
        Date date = null;
        try {
            date = sf.parse(sDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转为字符串
     * yyyy-MM-dd
     * @param sDate
     * @return
     */
    public static String date2String(Date date, String fomat) {
        SimpleDateFormat sf = new SimpleDateFormat(fomat);
        if ( null != date ) {
            String sDate = sf.format(date);
            return sDate;
        }
        return null;
    }

    /***
     * 获取当前日期前(后)n天
     * @param nowDate 当前日期
     * @param day 要向前或者向后的天数
     *        -n表示当前日期的前n天
     *        n表示当前日期的后n天
     * @return
     */

    public static Date getBeforeOrBackFromNowDate(Date nowDate, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nowDate);
        calendar.add(calendar.DATE, day);
        return calendar.getTime();
    }
    
    /***
     * 校验手机号码是否正确
     * (正则根据业务不同而有所不同。这里特别支持170 177字段)
     * @author Administrator
     * @creaetime 2014年5月4日 下午2:19:42
     * @return 正确：true；错误:false
     */
    public static boolean chekPhoneNumber(String phoneNumber ){
      //  String str = "^[1][3,5,8][(0-3)|(5-9)]{9}$ | ^[1][3,8][0-9]{9}$ ";
        String str = "^((13[0-9])|(15[0-3,5-9])|(18[0-9])|(170)|(177))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
    
    
    /**
     * 正则表达式验证
     * @param s1 要验证的字段值
     * @param p1 要验证正则表达式
     * @return true/false
     */
  	public static boolean patternCheck(String s1,String p1){
  		boolean  ret = false;
  		if(!StringUtils.isBlank(s1)){
  			 ret =s1.matches(p1);
  		}
  		return ret;
  	}
    
    /***
     * bolob转为byte
     * @param blob
     * @return
     * @throws Exception
     */
    public static byte[] blob2ByteArr(Blob blob){

        byte[] b = null;
        try {
            if (blob != null) {
                long in = 0;
                b = blob.getBytes(in, (int) (blob.length()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }
    

    
    
    /** 
     * 将一个 JavaBean 对象转化为一个  Map </br>
     * 如果属性值为null就存为空字符串
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static Map convertBean2Map(Object bean)  {  
        Class type = bean.getClass();  
        Map returnMap = new HashMap();  
        try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);  
  
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
			for (int i = 0; i< propertyDescriptors.length; i++) {  
			    PropertyDescriptor descriptor = propertyDescriptors[i];  
			    String propertyName = descriptor.getName();  
			    if (!propertyName.equals("class")) {  
			        Method readMethod = descriptor.getReadMethod();  
			        Object result = readMethod.invoke(bean, new Object[0]);  
			        // 可以自定义空值是否设置到map中去
			        if (result != null) {  
			            returnMap.put(propertyName, result);  
			        } else {  
			            returnMap.put(propertyName, "");  
			        }  
			      
			    }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return returnMap;  
    }  
    
    
   /*** 
    * 将一个 Map 对象转化为一个 JavaBean 
    * @param type 要转化的类型 
    * @param map 包含属性值的 map 
    * @return 转化出来的 JavaBean 对象 
    * @throws IntrospectionException 如果分析类属性失败 
    * @throws IllegalAccessException 如果实例化 JavaBean 失败 
    * @throws InstantiationException 如果实例化 JavaBean 失败 
    * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
    */  
   @SuppressWarnings("rawtypes")  
   public static Object convertMap2Bean(Class type, Map map)  
           throws IntrospectionException, IllegalAccessException,  
           InstantiationException, InvocationTargetException {  
       BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
       Object obj = type.newInstance(); // 创建 JavaBean 对象  
 
       // 给 JavaBean 对象的属性赋值  
       PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
       for (int i = 0; i< propertyDescriptors.length; i++) {  
           PropertyDescriptor descriptor = propertyDescriptors[i];  
           String propertyName = descriptor.getName();  
 
           if (map.containsKey(propertyName)) {  
               // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
               Object value = map.get(propertyName);  
 
               Object[] args = new Object[1];  
               args[0] = value;  
 
               descriptor.getWriteMethod().invoke(obj, args);  
           }  
       }  
       return obj;  
   }  
   
    /**
    * String  转为 list 
    * @param str 要转换的字符串
    * @param split 字符串中分割标志 如：“abc,bca,dfc”;这里字符串中的“,”
    * @return list或者null
    */
    public static List<String> convertString2List(String str,String split){
	   if(!StringUtils.isBlank(str)){
			String [] ss = str.split(split);
			List<String> list = Arrays.asList(ss);
			return  list;
		}else{
			return null;
		}
   }
    
 
    
    
    /**
     * 序列化
     * 请在父类(如果有父类)或者本类实现Serializable接口
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    
    
    /**
     * decimal 除法运算 保留两位小数
     * @param bytes
     * @return 
     */
    public static float decimalDivide(BigDecimal a,BigDecimal b) {
    	try {
			BigDecimal resualt = a.divide(b,2,BigDecimal.ROUND_HALF_EVEN);
			return resualt.floatValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    /**
     * decimal 金额转换，分转为元
     * @param int
     * @return   String 如果转换失败返回一个null
     */
    public static String minute2yuan(int  minute) {
    	try {
            BigDecimal a = new BigDecimal(minute);
    		BigDecimal resualt = a.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_EVEN);
			return resualt.toString();
		} catch (Exception e) {
               e.printStackTrace();
		}
    	return null;
    }
    
    
    /**
     * decimal 金额转换，分转为元
     * @param int
     * @return   String 如果转换失败返回一个0
     */
    public static String minute2yuanRetrunNumber(int  minute) {
    	try {
            BigDecimal a = new BigDecimal(minute);
    		BigDecimal resualt = a.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_EVEN);
			return resualt.toString();
		} catch (Exception e) {
               e.printStackTrace();
		}
    	return "0";
    }
    
    
    /**
     * decimal 金额转换，元转分
     * @param  int
     * @return   String
     */
    public static String yuan2minute(Float  yuan) {
    	try {
    		String  yuans = decimalFormat(yuan);
            BigDecimal a = new BigDecimal(yuans);
    		BigDecimal resualt = a.multiply(new BigDecimal(100));
    		Integer i = resualt.intValue();
			return i.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    /**
     * decimal 金额转换，元转分
     * @param  int
     * @return   String
     */
    public static int yuan2minute(String  yuan) {
    	try {
            BigDecimal a = new BigDecimal(yuan);
    		BigDecimal resualt = a.multiply(new BigDecimal(100));
    		Integer i = resualt.intValue();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
   
    
    
    /**
     * 格式化数字,保留两位小数。
     * @param Object obj 数字类型[i,f,d s,l]
     * @return   String 
     */
    public static String decimalFormat(Number number) {
    	try {
    		DecimalFormat df = new DecimalFormat("#0.00");
			return df.format(number);
		} catch (Exception e) {
            e.printStackTrace();
		}
    	return null;
    }
    
    
 
    
    
   /**
    * 将对象转为map
    * @param obj
    * @return map
    */
    public static Map<String, String> objTOMap(Object obj) {
		Map<String, String> params = null;
		try {
			String json = JSON.toJSONString(obj);
			params = new HashMap<String, String>();
			JSONObject jsonObject = JSONObject.parseObject(json);  
			Map<String,Object> m = (Map<String,Object>)jsonObject;
			Set<String> keys = m.keySet();
			for(String k : keys){
				Object v = m.get(k);
				String vStr = null;
				if(v instanceof Date){
					vStr = String.valueOf(((Date)v).getTime());
					params.put(k, vStr);
					continue;
				}
				params.put(k, v.toString());
			}
		} catch (Exception e) {
		
		}
    	return params;
    }
    
    /**
     * 秒转为：小时分秒格式
     * @param second
     * @return h+"时"+d+"分"+s+"秒"; 
     */
	public static String secondChange(int second){  
        int h = 0;  
        int d = 0;  
        int s = 0;  
        int temp = second%3600;  
             if(second>3600){  
               h= second/3600;  
                    if(temp!=0){  
               if(temp>60){  
               d = temp/60;  
            if(temp%60!=0){  
               s = temp%60;  
            }  
            }else{  
               s = temp;  
            }  
           }  
          }else{  
              d = second/60;  
           if(second%60!=0){  
              s = second%60;  
           }  
          }  

         return h+"时"+d+"分"+s+"秒";  
       }  
	
	 /**
     * 秒转为：分秒格式
     * @param second
     * @return d+"分"+s+"秒"; 
     */
	public static String secondConverMinute(int second){  
         int d = 0;  
         d = second/60;  
         if(second%60!=0){
        	 int s = second%60;
        	 if(d == 0){
        		 return s+"秒";   
        	 }else{
                 return d+"分"+s+"秒";  
        	 }
 
         } else{
        	 return d+"分钟";  
         }  
       }  
	
	/**
     * 生日转换计算年龄
     * @param birthday 默认18岁
     * @return 年龄; 
     */
	public static String birthdayConverAge(String  birthday){  
        try {
        	if(!StringUtils.isBlank(birthday)){
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    			Date birthdayDate= format.parse(birthday);
    			Date nowDate= new Date(); 
    			long i= (nowDate.getTime()-birthdayDate.getTime())/(1000*60*60*24);
    			String age = String.valueOf( i/365);
    		    return age;
        	}
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return "18";
       
	}  
	
	/**
	 * 生成全数字的订单编号，规则：
	 * 当前时间的毫秒数+length长度的0-9的随机数+一个唯一标志如用户id等
	 * @param length 随机数的长度 必选参数
	 * @param id 唯一标志的值，可选参数,null或者具体值
	 * @return 订单号
	 */
	public static String generateOrderId(int length, Integer id) {
		  if(id != null){
			  return  System.currentTimeMillis()+Commons.getRandomString(length)+id;
		   }else{
			   return  System.currentTimeMillis()+Commons.getRandomString(length);
		   }
	}
	
	
	/**
	 * 计算当天余下的时间
	 * @return 余下时间秒
	 */
	public static long calculateNowDayRestTime() {
      Calendar curDate = Calendar.getInstance();
      Calendar tommorowDate = new GregorianCalendar(curDate
		.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
		.get(Calendar.DATE) + 1, 0, 0, 0);
     long l  =(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
     return l;
	}
	
	/**
	 * 生成全数字的订单编号，规则：前三随机送+系统毫秒+后随机四位
	 * @return 订单号
	 */
	public static String generateOrderId() {
	    return 	Commons.getRandomString(3)+System.currentTimeMillis()+Commons.getRandomString(4);
	}
		
	
    public static void main(String[] args) {
      System.out.println(birthdayConverAge(null));	
	}
     
	
    
}
