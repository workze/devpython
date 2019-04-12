package com.inspection.model;

import lombok.Data;

@Data
public class InspectResult {
    String value;
    InspectStatusEnum status; // green, yellow, red, unknown
    String desc;
    String detail;
}
