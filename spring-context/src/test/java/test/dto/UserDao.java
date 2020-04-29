package test.dto;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhonghao
 * @date 2020-04-29 11:01
 */
public class UserDao {

	@Transactional(rollbackFor = Exception.class)
	public void test(){

	}
}
