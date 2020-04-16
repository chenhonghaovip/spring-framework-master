package test;

import org.junit.Test;
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
        fox.sout();
    }
}
