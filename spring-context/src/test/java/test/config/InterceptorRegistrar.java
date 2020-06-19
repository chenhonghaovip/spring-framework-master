package test.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author chenhonghao
 * @date 2020-06-19 15:02
 */
public class InterceptorRegistrar implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{MyInterceptor.class.getName()};
	}
}
