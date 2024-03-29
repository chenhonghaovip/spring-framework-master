/**
 * Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.submitted.nonexistentvariables;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

public class NonExistentVariablesTest {

	protected static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUp() throws Exception {
		Connection conn = null;

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:nonexistentvariables", "sa", "");

			Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/nonexistentvariables/CreateDB.sql");

			ScriptRunner runner = new ScriptRunner(conn);
			runner.setLogWriter(null);
			runner.setErrorLogWriter(null);
			runner.runScript(reader);
			conn.commit();
			reader.close();

			reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/nonexistentvariables/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Test(expected = PersistenceException.class)
	public void testWrongParameter() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper mapper = sqlSession.getMapper(Mapper.class);
			mapper.count(1, "John");
			fail("should have failed");
		} finally {
			sqlSession.close();
		}
	}
}
