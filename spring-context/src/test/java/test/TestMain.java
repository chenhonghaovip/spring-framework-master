package test;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.config.AOPConfig;
import test.dto.Fox;

import java.util.Arrays;

/**
 * @author chenhonghao
 * @date 2020-01-13 20:47
 */
public class TestMain {

    @Test
    public void test11(){
        AnnotationConfigApplicationContext an = new AnnotationConfigApplicationContext(AOPConfig.class);
        Fox fox = (Fox) an.getBean("fox");
		System.out.println("ioc容器中所有bean实例开始");
		Arrays.stream(an.getBeanDefinitionNames()).forEach(System.out::println);
		System.out.println("ioc容器中所有bean实例结束");
		// 容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象）
		an.publishEvent(new ApplicationEvent("我发布了一个事件"){});

		// 1、进入到CglibAopProxy中的intercept方法中
        fox.sout();
        an.close();
    }
}
