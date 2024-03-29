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
package org.apache.ibatis.submitted.batch_test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;

public class BatchTest {

	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUp() throws Exception {
		// create an SqlSessionFactory
		Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/batch_test/mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();

		// populate in-memory database
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/batch_test/CreateDB.sql");
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setLogWriter(null);
		runner.runScript(reader);
		conn.close();
		reader.close();
		session.close();
	}

	@Test
	public void shouldGetAUserNoException() {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			Mapper mapper = sqlSession.getMapper(Mapper.class);

			User user = mapper.getUser(1);

			user.setId(2);
			user.setName("User2");
			mapper.insertUser(user);
			Assert.assertEquals("Dept1", mapper.getUser(2).getDept().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());

		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}


}
