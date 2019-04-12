package com.ze.devhelper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Host {

    private long id;
    private String ip;
    private int port;
    private String user;
    private String password;
    private String alias;
    private long createtime;
    private int status;
    private int type;

    public Host() {
    }

    public Host(String ip, int port, String user, String password) {
        this.id = 0;
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String id(){
        return ip;
    }

    public Host mock() {
        this.ip = "192.168.1.10";
        this.port = 22;
        this.user = "root";
        this.password = "root123";
        return this;
    }

}
