package test.config;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;

/**
 * @author chenhonghao
 * @date 2020-06-22 15:11
 */
public class MyScanner extends AbstractAutoProxyCreator {
	private MyInterceptor myInterceptor;
	@Override
	protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
		return new Object[0];
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		return super.postProcessAfterInitialization(bean, beanName);
	}

	@Override
	protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
		return super.wrapIfNecessary(bean, beanName, cacheKey);
	}
}
