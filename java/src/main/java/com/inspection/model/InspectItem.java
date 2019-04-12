package com.inspection.model;

import lombok.Data;

import java.util.List;

@Data
public class InspectItem {
    String inspectItemId;
    String groupId;
    String algorithmName;
    String algorithmParam;

    Boolean multiInstance;

    String inspectType;
    String location;
}
