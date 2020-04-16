package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

}
