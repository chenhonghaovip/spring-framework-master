package test.post;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import test.dto.Fox;

/**
 * @author chenhonghao
 * @date 2020-01-08 10:10
 */
public class UserBeanPostProcessor implements BeanPostProcessor, Ordered {
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Fox) {
//            ((Fox) bean).setName("陈宏浩");
        }
//		System.out.println("postProcessBeforeInitialization-------"+bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("postProcessAfterInitialization++++++++"+bean.getClass().getSimpleName());
        return bean;
    }
}
