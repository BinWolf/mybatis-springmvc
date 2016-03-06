package com.wolf.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wolf on 16/3/6.
 */
public class IDateUtils {
    public static String pattern = "yyyy-MM-dd";

    public static String NOW = "yyyy-MM-dd HH:mm:ss";

    private static HashMap<String,SimpleDateFormat> formatCache = new HashMap();
    /**
     * 获取两个日期相差天
     * @param d1
     * @param d2
     * @return
     */
    public static long getDiffDay(String d1, String d2){
        return getDiffDay(d1,d2,pattern);
    }

    public static long getDiffDay(String d1, String d2,String pattern) {
        if(StringUtils.isEmpty(d1) || StringUtils.isEmpty(d2)){
            return 0;
        }
        try {

            Date date1 = IDateUtils.parse(d1, pattern);
            Date date2 = IDateUtils.parse(d2, pattern);
            long diff = date2.getTime() - date1.getTime();

            return Math.round(diff / 86400000);
            // DurationFormatUtils.formatPeriod(date1.getTime(), date2.getTime(), "d");
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public static Date parse(String d1, String pattern) throws ParseException {
        SimpleDateFormat sdf =  formatCache.get(pattern);
        if(sdf == null){
           sdf = new SimpleDateFormat(pattern);
           formatCache.put(pattern,sdf);
        }
        return sdf.parse(d1);
    }

    public static Date parseDate(String date,String[] patterns) throws ParseException {
       return DateUtils.parseDate(date,patterns);
    }

    /**
     *  缓存对象
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getDateFormat(String pattern){
        SimpleDateFormat sdf  = formatCache.get(pattern);
        if(sdf == null){
            sdf = new SimpleDateFormat(pattern);
            formatCache.put(pattern,sdf);
        }
        return sdf;
    }
    /**
     *  格式化输出
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date,String pattern){
        SimpleDateFormat sdf = getDateFormat(pattern);
        return  sdf.format(date);
    }
    /**
     * 获取今天的日期
     * @param pattern
     * @return
     */
    public static String  getToday(String pattern) {
        SimpleDateFormat sdf = getDateFormat(pattern);
        return  sdf.format(new Date());
    }

    public  static String getYesterday(String pattern){
        SimpleDateFormat sdf = getDateFormat(pattern);
        return  sdf.format( DateUtils.addDays(new Date(), -1));
    }
    
    /**
     * 获取明天
     * @param pattern
     * @return
     */
    public  static String getTomorrow(String pattern){
        SimpleDateFormat sdf = getDateFormat(pattern);
        return  sdf.format( DateUtils.addDays(new Date(), 1));
    }
    
    /**
     * 获取当前时间，返回yyyyMMddHHmmss格式
     * @return String 
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat sdf = getDateFormat("yyyyMMddHHmmss");
    	return sdf.format(new Date());
    }

    public static String getSysDate(){
        return getToday(IDateUtils.NOW);
    }
    /**
     * 将yyyy-MM-dd HH:mm:ss.S、
     * yyyy-MM-dd HH:mm:ss.SS、
     * yyyy-MM-dd HH:mm:ss.SSS、
     * yyyyMMdd
     * 转化为DATE类型
     * @return Date 
     * @param date
     * @return
     */
    public static Date conventCharStrToDate(String date){
    	try {
    		if(StringUtils.isEmpty(date)){
        		return new Date();
        	}
        	if(date.length()==21){
        		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(date);
        	}else if(date.length()==22){
        		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(date);
        	}else if(date.length()==23){
        		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date);
        	}else if(date.length()==8){
        		return new SimpleDateFormat("yyyyMMdd").parse(date);
        	}else{
        		return new Date();
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    private static final String[] patterns = new String[]{"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss.SS","yyyy-MM-dd HH:mm:ss.SSS","yyyyMMdd"};

    public static Date conventToDate(String date){

        if(StringUtils.isEmpty(date)){
            return new Date();
        }
        try {
            return DateUtils.parseDate(date,IDateUtils.patterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将date转化为yyyyMMdd
     * @return String 
     * @return
     */
    public static String conventDateToYMd(Date date){
    	if(null == date){
    		return new SimpleDateFormat("yyyyMMdd").format(new Date());
    	}
    	return new SimpleDateFormat("yyyyMMdd").format(date);
    }


    /**
     * 将date转化为yyyy-MM-dd HH:mm:ss
     * @return String 
     * @return
     */
    public static String conventDateTo_YMDHMS(Date date){
        SimpleDateFormat sdf = getDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(null == date){
    		return sdf.format(new Date());
    	}
    	return sdf.format(date);
    }
    /**
     * 将date转化为yyyy-MM-dd HH:mm:ss.SS
     * @return String 
     * @return
     */
    public static String conventDateTo_YMDHMS_2S(Date date){
    	if(null == date){
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
    	}
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(date);
    }

}
