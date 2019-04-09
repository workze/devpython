package com.inspection.algorithm;

import com.inspection.model.InspectItemInstance;
import com.inspection.model.InspectStatusEnum;

public abstract class BaseAlgorithm implements IAlgorithm {

    String param;
    InspectItemInstance inspectItemInstance;

    public BaseAlgorithm(){

    }

    public BaseAlgorithm(final InspectItemInstance inspectItemInstance){

    }

    @Override
    public void execute(final InspectItemInstance item) {
        item.getInspectResult().setStatus(InspectStatusEnum.Green);
    }
}
