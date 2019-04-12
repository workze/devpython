package com.inspection.classuml;

import java.util.List;
import java.util.concurrent.*;

public class InspectService {

    private List<InspectItem> inspectItems;
    String inspectorStatus; // running, paused, resumed, stopped

    private ThreadPoolExecutor inspectItemExecutorPool = new ThreadPoolExecutor(8,8,
            0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public synchronized void startInspect() {
        Executors.newSingleThreadExecutor().execute(()->{
            inspectAll();
        });
    }

    private void inspectAll(){
        for (InspectItem inspectItem : inspectItems) {
            //inspectItemExecutorPool.inspect(new InspectItemInstanceRunnable(inspectItem));
            inspectItemExecutorPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InspectorFactory.createInspector(inspectItem.inspector)
                                .inspect(inspectItem);
                        /*String algorithmName = inspectItem.getAlgorithmName();
                        BaseAlgorithm algorithm = (BaseAlgorithm) Class.forName(algorithmName).newInstance();
                        algorithm.inspect(inspectItem.getAlgorithmParam());*/
                    } catch (Exception e) {
                        //
                    }
                }
            });
        }
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
