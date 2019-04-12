package com.inspection.algorithm;

import com.inspection.algorithm.para.ConnectAlgorithmParam;
import com.inspection.model.InspectItemInstance;
import com.inspection.model.InspectStatusEnum;

public class ConnectAlgorithm extends BaseAlgorithm {

    ConnectAlgorithmParam param;

    @Override
    public void execute(final InspectItemInstance item) {
        initParam();
        item.getInspectResult().setStatus(InspectStatusEnum.Green);
    }

    void initParam(){

    }
}
