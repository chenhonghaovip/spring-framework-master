/**
 * Copyright 2009-2017 the original author or authors.
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

package org.apache.ibatis.reflection;

import org.apache.ibatis.lang.UsesJava8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@UsesJava8
public class ParamNameUtil {
	public static List<String> getParamNames(Method method) {
		return getParameterNames(method);
	}

	public static List<String> getParamNames(Constructor<?> constructor) {
		return getParameterNames(constructor);
	}

	private static List<String> getParameterNames(Executable executable) {
		final List<String> names = new ArrayList<String>();
		final Parameter[] params = executable.getParameters();
		for (Parameter param : params) {
			names.add(param.getName());
		}
		return names;
	}

	private ParamNameUtil() {
		super();
	}
}
