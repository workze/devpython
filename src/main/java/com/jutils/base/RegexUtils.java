package com.jutils.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类，验证数据是否符合规范
 *
 * @Author:chenssy
 * @date:2014年8月7日
 */
public class RegexUtils {

    /**
     * 判断字符串是否符合正则表达式
     *
     * @param str
     * @param regex
     * @return
     * @author : chenssy
     * @date : 2016年6月1日 下午12:43:05
     */
    public static boolean find(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.find();
        return b;
    }

    /**
     * 获取第1个满足条件的字符串
     * @param str
     * @param regex
     * @return
     */
    public static String get(String str, String regex) {
        return get(str, regex, 0);
    }

    /**
     * 获取第index+1个满足条件的字符串
     * @param str
     * @param regex
     * @param index
     * @return
     */
    public static String get(String str, String regex, int index) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        for (int i = 0; i < index + 1; i++) {
            if (!m.find()) {
                return "";
            }
        }
        return m.group();
    }

    /**
     * 获取所有满足条件的字符串
     * @param str
     * @param regex
     * @return
     */
    public static List<String> getAll(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        List<String> list = new LinkedList<String>();
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 判断输入的字符串是否符合Email格式.
     *
     * @param email 传入的字符串
     * @return 符合Email格式返回true，否则返回false
     * @autor:chenssy
     * @date:2014年8月7日
     */
    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param value 传入的字符串
     * @return
     * @autor:chenssy
     * @date:2014年8月7日
     */
    public static boolean isChinese(String value) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param value 传入的字符串
     * @return
     * @autor:chenssy
     * @date:2014年8月7日
     */
    public static boolean isDouble(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否为整数
     *
     * @param value 传入的字符串
     * @return
     * @autor:chenssy
     * @date:2014年8月7日
     */
    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(value).matches();
    }
}
