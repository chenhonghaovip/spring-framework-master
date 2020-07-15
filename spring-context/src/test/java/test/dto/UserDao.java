package test.dto;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhonghao
 * @date 2020-04-29 11:01
 */
public class UserDao implements UserDaoInterface{

	@Override
	@Transactional
	public void test(){
		System.out.println("111");
	}
}
