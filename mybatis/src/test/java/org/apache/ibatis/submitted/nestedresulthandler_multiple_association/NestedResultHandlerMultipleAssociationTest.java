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
package org.apache.ibatis.submitted.nestedresulthandler_multiple_association;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;
import java.util.List;

public class NestedResultHandlerMultipleAssociationTest {

	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUp() throws Exception {
		// create an SqlSessionFactory
		Reader reader = Resources
				.getResourceAsReader("org/apache/ibatis/submitted/nestedresulthandler_multiple_association/mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();

		// populate in-memory database
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		reader = Resources
				.getResourceAsReader("org/apache/ibatis/submitted/nestedresulthandler_multiple_association/CreateDB.sql");
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setLogWriter(null);
		runner.runScript(reader);
		conn.close();
		reader.close();
		session.close();
	}

	@Test
	public void failure() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// Parents have child going from somewhere to somewhere, they are stored in
		// a Binome object
		// In this test we have 2 parents:
		// Parent1 is going from Child1 to Child2
		// Parent2 is going from Child2 to Child3 and from Child1 to Child2
		// You'll see a NULL entry in the list instead of the Binome Child1/Child2
		List<ParentBean> list = sqlSession.selectList("selectParentBeans");
		for (ParentBean pb : list) {
			for (Binome<ChildBean, ChildBean> childs : pb.getChilds()) {
				Assert.assertNotNull(childs);
				Assert.assertNotNull(childs.getOne());
				Assert.assertNotNull(childs.getTwo());
			}
		}

		sqlSession.close();
	}

	@Test
	public void success() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		ParentBean parent = sqlSession.selectOne("selectParentBeanById", 2);

		// If you only select the Parent2 it works
		for (Binome<ChildBean, ChildBean> childs : parent.getChilds()) {
			Assert.assertNotNull(childs);
			Assert.assertNotNull(childs.getOne());
			Assert.assertNotNull(childs.getTwo());
		}
		sqlSession.close();
	}

}
