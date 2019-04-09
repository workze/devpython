package com.inspection.executor;

import com.inspection.algorithm.BaseAlgorithm;
import com.inspection.model.InspectItemInstance;

public class InspectItemInstanceRunnable implements Runnable{

    InspectItemInstance inspectItem;

    InspectItemInstanceRunnable(final InspectItemInstance inspectItem){
        this.inspectItem = inspectItem;
    }

    @Override
    public void run() {
        try {
            String algorithmName = inspectItem.getInspectItem().getAlgorithmName();
            BaseAlgorithm algorithm = (BaseAlgorithm) Class.forName(algorithmName).newInstance();
            algorithm.execute(inspectItem);
        } catch (Exception e) {
            //
        }
    }
}
