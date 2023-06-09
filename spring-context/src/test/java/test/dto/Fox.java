package test.dto;

import org.springframework.context.annotation.Import;
import test.config.InterceptorRegistrar;
import test.mapper.WaybillBcListMapper;

import javax.annotation.Resource;

/**
 * @author chenhonghao
 * @date 2020-01-21 14:45
 */
@Import({UserDao.class, InterceptorRegistrar.class})
public class Fox {
	@Resource
	private WaybillBcListMapper waybillBcListMapper;

    public Fox() {
    }

    public void sout(){
        System.out.println("fox 。。。 sout");
    }
}
