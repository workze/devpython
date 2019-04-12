package com.inspection.service;

import com.inspection.classuml.*;

import java.util.List;

public class EnhanceItem {
    String itemId;
    String algorithm;
    String algorithmParam;

    String executor;

    BaseInspector executor() {
        return new LocalInspector();
    }

    IAlgorithm algorithm() {
        return new CPUAlgorithm();
    }

    List<InspectItemInstance> instances() {
        return null;
    }

    void inspect() {


    }
}
