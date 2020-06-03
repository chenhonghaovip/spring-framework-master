package test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
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
@EnableAspectJAutoProxy
@Configuration
public class AOPConfig {
	private static final String MAPPER_LOCATION = "classpath*:test/mybatis/*.xml";

    @Bean
    public Fox fox(){
        return new Fox();
    }

	@Bean
	public TestAspect testAspect(){
		return new TestAspect();
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
	public DataSource masterDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource("jdbc:mysql://49.235.212.2:33066/clouds_waybill?useUnicode=true&characterEncoding=utf-8", "root", "nzON899V7hYFuoUS");
//		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return driverManagerDataSource;
	}
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
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

	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(masterSqlSessionFactory());
	}

	@Bean
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager dataSourceTransactionManager){
		return new TransactionTemplate(dataSourceTransactionManager);
	}


}
