package com.inspection.inner;

import com.inspection.classuml.BaseAlgorithm;
import com.inspection.classuml.InspectItemInstance;

public class ConnectAlgorithm extends BaseAlgorithm {

    ConnectAlgorithmParam param;

    @Override
    public String execute(final InspectItemInstance item) {
        applyParam();
        //item.getInspectResult().setStatus(InspectStatusEnum.GREEN);
        return null;
    }

    void applyParam(){

    }
}
