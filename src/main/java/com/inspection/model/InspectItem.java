package com.inspection.model;

import lombok.Data;

@Data
public class InspectItem {
    String inspectItemId;
    String groupId;
    String algorithmName;
    String algorithmParam;
    Boolean multiInstance;

    public InspectItemInstance instances(){
        return null;
    }
}
