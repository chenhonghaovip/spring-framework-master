package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import test.aspect.MyApplicationListener;
import test.aspect.MyTestApplicationListener;
import test.aspect.TestAspect;
import test.dto.Fox;
import test.post.UserBeanPostProcessor;

/**
 * @author chenhonghao
 * @date 2020-03-20 15:51
 */
@EnableAspectJAutoProxy
@Configuration
public class AOPConfig {

    @Bean
    public TestAspect testAspect(){
        return new TestAspect();
    }

    @Bean
    public Fox fox(){
        return new Fox();
    }

	@Bean
	public UserBeanPostProcessor userBeanPostProcessor(){
		return new UserBeanPostProcessor();
	}

	@Bean
	public MyApplicationListener myApplicationListener(){
    	return new MyApplicationListener();
	}

	@Bean
	public MyTestApplicationListener myTestApplicationListener(){
		return new MyTestApplicationListener();
	}
}
