package com.jutils;

import com.jutils.base.RegexUtils;
import com.jutils.http.HttpUtil;

import java.io.IOException;
import java.util.List;

public class UtilTest {
    public static void main(String[] args) {

        String text = "name: wang, mail: hangguize";

        String s = RegexUtils.get(text, "\\wapng",2);
        List<String> list = RegexUtils.getAll(text,"ang");

        try {
            s = HttpUtil.get("http://api.map.baidu.com/telematics/v3/weather?location=%E5%98%89%E5%85%B4&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.currentTimeMillis();
        System.out.println(s);
        System.out.println("-- end ----");
    }
}
