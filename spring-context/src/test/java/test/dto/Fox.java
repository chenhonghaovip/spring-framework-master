package test.dto;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhonghao
 * @date 2020-01-21 14:45
 */
public class Fox {
	@Autowired
	private UserDao userDao;

    public Fox() {
    }

    public void sout(){
        System.out.println("fox 。。。 sout");
    }
}
