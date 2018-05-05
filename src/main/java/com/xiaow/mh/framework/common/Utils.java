package com.xiaow.mh.framework.common;


import com.xiaow.mh.business.domain.Equipment;
import com.xiaow.mh.framework.domain.AppDO;
import com.xiaow.mh.interfaces.DTO.AppDTO;
import com.xiaow.mh.interfaces.DTO.Mhroler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by POJO on 6/15/16.
 */
public class Utils {

    public static boolean notEmpty(Collection lst) {
        return lst != null && lst.size() > 0;
    }

    public static boolean isEmpty(Collection lst){
        return lst == null || lst.size() == 0;
    }

    public static boolean notEmpty(String str) {
        return str != null && !str.equals("");
    }


    /**
     * 两整数相除保留两位小数
     * @param count1
     * @param count2
     * @return
     */
    public static Double getRate(Integer count1, Integer count2){
        if(count2.intValue() == 0){
            return new Double(0);
        }
        Double rate = count1.doubleValue()/count2.doubleValue();
        BigDecimal b = new BigDecimal(rate);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取map中set的大小
     * @param singer
     * @param phase
     * @return
     */
    public static int getSize(Map<Integer,Set<String>> singer, Integer phase) {
        return singer.get(phase) == null ? 0 : singer.get(phase).size();
    }

    public static  Set<String> sumSet(Set<String> set1, Set<String> set2){
        if(set1 != null && set2 != null){
            set1.addAll(set2);
            return set1;
        }
        if(set1 != null && set2 == null){
            return set1;
        }
        if(set1 == null && set2 != null){
            return set2;
        }
        return null;
    }

//    /**
//     * 根据cookieName获得cookie
//     *
//     * @param request
//     * @return
//     */
//    public static Cookie getCookieByName(HttpServletRequest request) {
//        return getCookieByName(Constants.signCookie, request);
//    }

    /**
     * 根据cookieName获得cookie
     *
     * @param cookieName
     * @param request
     * @return
     */
    public static Cookie getCookieByName(String cookieName, HttpServletRequest request) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(cookieName)) {
            Cookie cookie = (Cookie) cookieMap.get(cookieName);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 读取cookie
     *
     * @param request
     * @return
     */
    public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 根据网址获得该网址的domain全称
     *
     * @param src
     * @return
     */
    public static String validateDomainName(String src) {
        String str1 = "(http://|https://)?([^/]*)(/.*)?";
        Pattern p = Pattern.compile(str1);
        Matcher m = p.matcher(src);
        boolean f = m.find();
        StringBuffer sb = new StringBuffer();
        while (f) {
            m.appendReplacement(sb, "$2");
            f = m.find();
        }
        m.appendTail(sb);
        return sb.toString();
    }

//    /**
//     * 验证cookie是否合法
//     * @param val
//     * @return
//     */
//    public static Boolean webKeyOKAY(String val) {
//        return val.equals(Constants.cookieWebkey);
//    }


    /**
     * 增加普通cookie<br/>
     * maxAge -1为浏览器周期 0为删除 正数为有效期 单位为秒
     *
     * @param cookieName
     * @param cookieValue
     * @param maxAge
     * @param response
     */
    public static void addCookie(String cookieName, String cookieValue, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
//
//    /**
//     * 添加广告有关信息cookie
//     * @param response
//     */
//    public static void addADCookie(HttpServletResponse response, String adOrigin, String adUrl) {
//        if(adUrl != null){
//            addCookie(Constants.adCookie, adOrigin + "," + adUrl, 24*60*60, response);
//        }else {
//            addCookie(Constants.adCookie, adOrigin , 24*60*60, response);
//        }
//
//    }
//
//    /**
//     * 添加用户标识cookie
//     * @param response
//     */
//    public static void addSignCookie(HttpServletResponse response, String signCookie) {
//        addCookie(Constants.signCookie, signCookie, Integer.MAX_VALUE, response);
//    }

    /**
     * 根据userAgent判断是pc端还是手机端
     * @param userAgent
     * @return
     */
    public static Boolean isMobile(String userAgent){
        if(userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("iPad")) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 将图片转化为字节流
     * @param imagePath
     * @return
     */
    public static byte[] imageToByte(String imagePath) throws Exception{

        File f = new File(imagePath);
        if (!f.exists()) {
            throw new FileNotFoundException(imagePath);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static void addProperty(Equipment equipment, AppDTO dto){
        Field[] fields = dto.getClass().getDeclaredFields();
        Field propertyField = null;
        String property1Type = null;
        String property2Type = null;
        for(Field field: fields) {
            try {
                propertyField = equipment.getClass().getDeclaredField(field.getName());
                field.setAccessible(true);
                propertyField.setAccessible(true);
                property1Type = propertyField.getType().toString();
                property2Type = field.getType().toString();
                if (!property2Type.endsWith("int")) {
                   continue;
                }
                int value2 = field.getInt(dto);
                if (property1Type.endsWith("Integer")) {
                    int value1 = propertyField.get(equipment) == null ? 0 : ((Integer)propertyField.get(equipment)).intValue();
                    propertyField.set(equipment,value1 + value2);
                } else if (property1Type.endsWith("int")) {
                    int value1 = propertyField.getInt(equipment);
                    propertyField.setInt(equipment,value1 + value2);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }
}
