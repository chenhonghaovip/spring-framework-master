/**
 * Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.mapping;

import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BoundSqlTest {

	@Test
	public void testHasAdditionalParameter() throws Exception {
		List<ParameterMapping> params = Collections.emptyList();
		BoundSql boundSql = new BoundSql(new Configuration(), "some sql", params, new Object());

		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		boundSql.setAdditionalParameter("map", map);

		Person bean = new Person();
		bean.id = 1;
		boundSql.setAdditionalParameter("person", bean);

		String[] array = new String[]{"User1", "User2"};
		boundSql.setAdditionalParameter("array", array);

		assertFalse(boundSql.hasAdditionalParameter("pet"));
		assertFalse(boundSql.hasAdditionalParameter("pet.name"));

		assertTrue(boundSql.hasAdditionalParameter("map"));
		assertTrue(boundSql.hasAdditionalParameter("map.key1"));
		assertTrue("should return true even if the child property does not exists.", boundSql.hasAdditionalParameter("map.key2"));

		assertTrue(boundSql.hasAdditionalParameter("person"));
		assertTrue(boundSql.hasAdditionalParameter("person.id"));
		assertTrue("should return true even if the child property does not exists.", boundSql.hasAdditionalParameter("person.name"));

		assertTrue(boundSql.hasAdditionalParameter("array[0]"));
		assertTrue("should return true even if the element does not exists.", boundSql.hasAdditionalParameter("array[99]"));
	}

	public static class Person {
		public Integer id;
	}

}
