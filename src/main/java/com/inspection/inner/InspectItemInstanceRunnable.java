package com.inspection.inner;

import com.inspection.classuml.BaseAlgorithm;
import com.inspection.classuml.InspectItemInstance;

public class InspectItemInstanceRunnable implements Runnable{

    InspectItemInstance inspectItem;
    BaseAlgorithm algorithm;

    InspectItemInstanceRunnable(final InspectItemInstance inspectItem){
        this.inspectItem = inspectItem;
    }

    @Override
    public void run() {
        try {
            String algorithmName = inspectItem.getInspectItem().getAlgorithmName();
            algorithm = (BaseAlgorithm) Class.forName(algorithmName).newInstance();
            algorithm.execute(inspectItem);
        } catch (Exception e) {
            //
        }
    }
}
