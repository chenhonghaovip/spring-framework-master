package test.aspect;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author chenhonghao
 * @date 2020-04-23 16:11
 */
public class MyApplicationListener {
	@Async
	@EventListener
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("MyApplicationListener监听事件"+event);
	}
}
