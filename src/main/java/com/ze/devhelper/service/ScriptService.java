package com.ze.devhelper.service;

import com.ze.devhelper.dto.Script;

public class ScriptService {

    public String getScript(String name){
        if("df".equals(name)){
            return "df -h";
        }
        if("pwd".equals(name)){
            return "pwd";
        }
        return "ls";
    }

    public Script getScriptByName(String name){
        Script script = new Script();
        if("df".equals(name)){
            script.setContent("df -h");
        }
        if("pwd".equals(name)){
            script.setContent("pwd");
        }


        return script;
    }

}
