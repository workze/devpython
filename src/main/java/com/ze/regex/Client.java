package com.ze.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Client {

    public static void main(String[] args) {
        String path = "/rest/{id}";
        String uri = "/rest/1";

        String pth2 = path.replaceAll("\\{.*\\}",".*");
        System.out.println(pth2);
        Pattern pattern = Pattern.compile(pth2);
        Matcher mather = pattern.matcher(uri);
        System.out.println(mather.matches());
    }

}
