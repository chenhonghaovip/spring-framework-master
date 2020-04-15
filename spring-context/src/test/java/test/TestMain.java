package test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chenhonghao
 * @date 2020-01-13 20:47
 */
public class TestMain {

    @Test
    public void test11(){
        AnnotationConfigApplicationContext an = new AnnotationConfigApplicationContext(AOPConfig.class);
        Fox fox = (Fox) an.getBean("fox");
        fox.sout();
    }
}
