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
package org.apache.ibatis.type;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DateTypeHandlerTest extends BaseTypeHandlerTest {

	private static final TypeHandler<Date> TYPE_HANDLER = new DateTypeHandler();
	private static final Date DATE = new Date();
	private static final Timestamp TIMESTAMP = new Timestamp(DATE.getTime());

	@Override
	@Test
	public void shouldSetParameter() throws Exception {
		TYPE_HANDLER.setParameter(ps, 1, DATE, null);
		verify(ps).setTimestamp(1, new java.sql.Timestamp(DATE.getTime()));
	}

	@Override
	@Test
	public void shouldGetResultFromResultSetByName() throws Exception {
		when(rs.getTimestamp("column")).thenReturn(TIMESTAMP);
		when(rs.wasNull()).thenReturn(false);
		assertEquals(DATE, TYPE_HANDLER.getResult(rs, "column"));
	}

	@Override
	@Test
	public void shouldGetResultNullFromResultSetByName() throws Exception {
		when(rs.getTimestamp("column")).thenReturn(null);
		when(rs.wasNull()).thenReturn(true);
		assertNull(TYPE_HANDLER.getResult(rs, "column"));
	}

	@Override
	@Test
	public void shouldGetResultFromResultSetByPosition() throws Exception {
		when(rs.getTimestamp(1)).thenReturn(TIMESTAMP);
		when(rs.wasNull()).thenReturn(false);
		assertEquals(DATE, TYPE_HANDLER.getResult(rs, 1));
	}

	@Override
	@Test
	public void shouldGetResultNullFromResultSetByPosition() throws Exception {
		when(rs.getTimestamp(1)).thenReturn(null);
		when(rs.wasNull()).thenReturn(true);
		assertNull(TYPE_HANDLER.getResult(rs, 1));
	}

	@Override
	@Test
	public void shouldGetResultFromCallableStatement() throws Exception {
		when(cs.getTimestamp(1)).thenReturn(TIMESTAMP);
		when(cs.wasNull()).thenReturn(false);
		assertEquals(DATE, TYPE_HANDLER.getResult(cs, 1));
	}

	@Override
	@Test
	public void shouldGetResultNullFromCallableStatement() throws Exception {
		when(cs.getTimestamp(1)).thenReturn(null);
		when(cs.wasNull()).thenReturn(true);
		assertNull(TYPE_HANDLER.getResult(cs, 1));
	}

}