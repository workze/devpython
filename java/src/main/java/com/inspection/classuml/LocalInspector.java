package com.inspection.classuml;

public class LocalInspector extends BaseInspector {

    InspectItemInstanceCache inspectItemInstanceCache;

    void inspect(InspectItem item){

        BaseAlgorithm algorithm = new CPUAlgorithm();
        InspectItemInstance inspectItemInstance = new InspectItemInstance();

        try {
            algorithm.execute(item.getAlgorithmParam());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
