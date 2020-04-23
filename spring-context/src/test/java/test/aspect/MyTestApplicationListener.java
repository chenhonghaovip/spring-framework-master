package test.aspect;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author chenhonghao
 * @date 2020-04-23 16:11
 */
public class MyTestApplicationListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("MyTestApplicationListener监听事件"+event);
	}
}
