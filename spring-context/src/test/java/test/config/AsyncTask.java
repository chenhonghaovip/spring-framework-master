package test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * @author chenhonghao
 * @date 2019-07-15 10:53
 */
public class AsyncTask {
    private Logger logger = LoggerFactory.getLogger(AsyncTask.class);


    /**
     * 异步调用，通知结算
     * @return Future
     */
    @Async
    public void doTaskOne(){
        logger.info("start sync doTaskOne and batchId = ");
    }
}
