package com.inspection.classuml;

public abstract class BaseAlgorithm implements IAlgorithm {

    String param;

    public abstract void applyParam(String param);

    public String algorithm(String param){
        applyParam(param);
        return execute(param);
    }

    @Override
    public abstract String execute(String param);
}
