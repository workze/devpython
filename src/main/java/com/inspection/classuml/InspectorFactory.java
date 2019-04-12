package com.inspection.classuml;

public class InspectorFactory {

    public static BaseInspector createInspector(String executor) {
        switch (executor) {
            case "agent":

                break;
            default:
                break;
        }
        return null;
    }

}
