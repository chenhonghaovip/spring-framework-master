package test.config;


import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author chenhonghao
 * @date 2020-06-19 14:04
 */
public class MyInterceptor implements MethodInterceptor {


	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return invocation.proceed();
	}


	public Advice getAdvice() {
		return new MyInterceptor();
	}


	public boolean isPerInstance() {
		return false;
	}
}
