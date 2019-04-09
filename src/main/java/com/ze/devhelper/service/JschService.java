package com.ze.devhelper.service;

import com.ze.devhelper.JschClient;
import com.ze.devhelper.dto.Host;

import java.io.File;
import java.util.*;

public class JschService {

//    private  List<JschClient> clientPool = new ArrayList<JschClient>();
    private Map<String,JschClient> clientMap = new HashMap<String, JschClient>();

    public  JschClient getJschClient(String ip){
        return clientMap.get(ip);
    }

    public void initialize(){
        List<Host> hosts = new HostService().getAllHosts();

        for(Host host : hosts){
            JschClient client = new JschClient(host);
            clientMap.put(host.getIp(),client);
        }
    }

    public String registerJschClient(Properties config){
        return "";
    }

    public int curJschClientNum(){
        return 0;
    }

}
