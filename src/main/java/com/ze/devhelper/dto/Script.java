package com.ze.devhelper.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Script {

    private long id = 0;
    private String scriptname;
    private String content = "ls";
    private long module;
    // 1 command; 2 script; 3 python;
    private long type = 1;
    private long createtime;
    private long updatetime;


}
