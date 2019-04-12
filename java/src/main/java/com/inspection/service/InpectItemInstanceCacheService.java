package com.inspection.service;

import com.inspection.model.InspectItemInstance;

import java.util.Map;

public interface InpectItemInstanceCacheService {
    void add(InspectItemInstance inspectItemInstance);
    InspectItemInstance get(String inspectItemInstanceId);
}
