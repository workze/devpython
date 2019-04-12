package com.inspection.classuml;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class AgentInspector extends BaseInspector {

    InspectItemInstanceCache inspectItemInstanceCache;
    String algorithmClass;
    String algorithmParam;
    String inspectorParam;

    String agentIP;
    String agentPort;

    private void initParam(){

    }

    private String instanceId(){
        return "id";
    }

    void inspect(InspectItem item){
        //InspectItemInstance inspectItemInstance = new InspectItemInstance();
        //
        try {
            new OkHttpClient().newCall(new Request.Builder().build()).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
