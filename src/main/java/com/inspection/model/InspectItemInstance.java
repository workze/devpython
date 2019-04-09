package com.inspection.model;

import lombok.Data;


@Data
public class InspectItemInstance {
    String instanceId;
    InspectItem inspectItem;
    InspectResult inspectResult;

    public boolean isFinished(){
        return true;
    }
}
