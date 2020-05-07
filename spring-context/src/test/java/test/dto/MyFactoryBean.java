package test.dto;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.FactoryBean;

/**
 * Spring 中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean
 * 即 FactoryBean。FactoryBean跟普通Bean不同，其返回的对象不是指定类的一个实例，
 * 而是该FactoryBean的getObject方法所返回的对象。
 *
 * 例如在集成spring与mybatis时，为了使用IOC容器进行解耦，我们需要将第三方框架中的核心类加入到spring的IOC容器中去
 * 创建一个 SqlSessionFactoryBean 并添加到IOC容器中，这样就可以将它生成的 SqlSessionFactory 也一并交由IOC容器管理了
 *
 * @author chenhonghao
 * @date 2020-01-20 19:52
 */
@Slf4j
public class MyFactoryBean implements FactoryBean<Apple> {
    @Override
    public Apple getObject() {
        return new Apple();
    }

    @Override
    public Class<Apple> getObjectType() {
        return Apple.class;
    }
}
