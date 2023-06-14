/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clinton Begin
 */
public class InterceptorChain {

	private final List<Interceptor> interceptors = new ArrayList<Interceptor>();

	/**
	 * @param target 原始执行器
	 * @return 被各个拦截器处理过的执行器
	 * @see Plugin#wrap(Object, Interceptor)
	 * <p>
	 * 此处会调用各个数据库拦截器的plugin方法对执行器进行处理（此处拦截器即为在sqlSessionFactoryBean.setPlugins()中配置的拦截器）
	 */
	public Object pluginAll(Object target) {
		for (Interceptor interceptor : interceptors) {
			// 各个拦截器会执行Plugin.wrap(target, this)方法，对执行器进行代理处理，InvocationHandler类型为Plugin.class
			target = interceptor.plugin(target);
		}
		return target;
	}

	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	public List<Interceptor> getInterceptors() {
		return Collections.unmodifiableList(interceptors);
	}

}
