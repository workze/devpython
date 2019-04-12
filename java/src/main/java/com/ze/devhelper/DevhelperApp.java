package com.ze.devhelper;

import com.ze.devhelper.service.JschService;

public class DevhelperApp {

    private JschService jschClientService;

    public static void main(String[] args) {


        new DevhelperApp().printdisk("192.168.1.10");
        System.out.println("end");
    }

    public DevhelperApp(){
        jschClientService = new JschService();
        jschClientService.initialize();
    }

    private void printdisk(String ip){
        String str = "";
        Result result;
        JschClient client = jschClientService.getJschClient(ip);
//        System.out.println(client.execCommand("df -h"));
        Result re = client.uploadString("this is content");

        result = client.downloadString(re.getMessage());
        result = client.execShellScript("cat /etc/hosts | grep 1");
        client.close();
        System.out.println(str);
        System.out.println(result.getMessage());
    }

}
