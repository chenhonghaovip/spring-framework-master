package test.dto;

import test.mapper.WaybillBcListMapper;

import javax.annotation.Resource;

/**
 * @author chenhonghao
 * @date 2020-01-21 14:45
 */
public class Fox {
	@Resource
	private WaybillBcListMapper waybillBcListMapper;

    public Fox() {
    }

    public void sout(){
        System.out.println("fox 。。。 sout");
    }
}
