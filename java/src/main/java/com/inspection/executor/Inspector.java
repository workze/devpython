package com.inspection.executor;

import com.inspection.model.InspectItem;
import com.inspection.model.InspectItemInstance;
import com.inspection.model.InspectResult;

import java.util.List;
import java.util.concurrent.*;

public class Inspector {

    String inspectorStatus; // running, paused, resumed, stopped

    private List<InspectItemInstance> inspectItemInstances;
    private ThreadPoolExecutor inspectItemExecutorPool = new ThreadPoolExecutor(8,8,
            0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.CallerRunsPolicy());
    private ExecutorService inspectService = Executors.newSingleThreadExecutor();

    public synchronized void startInspect(final List<InspectItem> inspectItems) {
        inspectService.execute(()->{
            for (InspectItemInstance inspectItem : inspectItemInstances) {
                inspectItemExecutorPool.execute(new InspectItemInstanceRunnable(inspectItem));
            }
        });
    }

    public List<InspectResult> queryResult(){
        return null;
    }

    public synchronized boolean stopInspect(){
        this.inspectItemExecutorPool.shutdownNow();
        return true;
    }

    public synchronized boolean pauseInspect(){
        return true;
    }

    public synchronized boolean resumeInspect(){
        return true;
    }

    public synchronized boolean restartInspect(){
        return true;
    }

}
