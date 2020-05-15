package test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import test.aspect.MyApplicationListener;
import test.aspect.MyTestApplicationListener;
import test.aspect.TestAspect;
import test.dto.Fox;
import test.dto.MyFactoryBean;
import test.dto.UserDao;
import test.post.UserBeanPostProcessor;

import javax.sql.DataSource;

/**
 * @author chenhonghao
 * @date 2020-03-20 15:51
 */
@Import({UserDao.class})
@EnableTransactionManagement
@MapperScan(value = {"test.mapper"})
//@EnableAspectJAutoProxy
@Configuration
public class AOPConfig {
	private static final String MAPPER_LOCATION = "classpath*:test/mybatis/*.xml";

    @Bean
    public TestAspect testAspect(){
        return new TestAspect();
    }

    @Bean
    public Fox fox(){
        return new Fox();
    }

	@Bean
	public UserBeanPostProcessor userBeanPostProcessor(){
		return new UserBeanPostProcessor();
	}

	@Bean
	public MyApplicationListener myApplicationListener(){
    	return new MyApplicationListener();
	}

	@Bean
	public MyTestApplicationListener myTestApplicationListener(){
		return new MyTestApplicationListener();
	}

	@Bean
	public MyFactoryBean myFactoryBean(){
    	return new MyFactoryBean();
	}

	@Bean
	public UserDao userDao(){
    	return new UserDao();
	}

	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    	return new SqlSessionTemplate(masterSqlSessionFactory());
	}

	@Bean
	@Primary
	public DataSource masterDataSource() {
		return new DriverManagerDataSource("jdbc:mysql://49.235.212.2:33066/clouds_waybill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true","root","nzON899V7hYFuoUS");
	}

	@Bean
	@Primary
	public SqlSessionFactory masterSqlSessionFactory()
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(masterDataSource());
		sessionFactory.setConfiguration(new org.apache.ibatis.session.Configuration());
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(AOPConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}


}
