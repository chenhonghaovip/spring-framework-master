package com.example.demo;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhihei
 * @date 2024/4/3
 */
public class DynamicJarLoader {
	public static void runJar(String pathToJar, String className, String methodName) throws Exception {
		URL[] classLoaderUrls = new URL[]{new URL("file:///" + "/Users/zhihei/IdeaProjects/demo/target/demo-0.0.1-SNAPSHOT.jar")};
		try (URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls)) {
			Class<?> clazz = urlClassLoader.loadClass(className);
			Method method = clazz.getDeclaredMethod(methodName);
			Object instance = clazz.getDeclaredConstructor().newInstance();

			// 如果方法是静态的，可以将instance设为null
			method.invoke(instance);
		}
	}

	public static void main(String[] args) {
		try {
			String pathToJar = "/path/to/file.jar";
			String className = "com.example.demo.Test";
			String methodName = "test";
			runJar(pathToJar, className, methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
