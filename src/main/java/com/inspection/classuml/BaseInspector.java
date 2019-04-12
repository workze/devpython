package com.inspection.classuml;

public abstract class BaseInspector {

    InspectItemInstanceCache inspectItemInstanceCache;

    abstract void inspect(InspectItem item);
}
