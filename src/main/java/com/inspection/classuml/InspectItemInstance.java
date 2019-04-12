package com.inspection.classuml;

import lombok.Data;

@Data
public class InspectItemInstance {
    String inspectItemId;
    String inspectItemInstanceId;

    String value;
    String detail;
    String healthy;
}
