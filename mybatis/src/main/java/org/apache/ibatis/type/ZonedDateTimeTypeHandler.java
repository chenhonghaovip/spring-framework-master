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
package org.apache.ibatis.type;

import org.apache.ibatis.lang.UsesJava8;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @since 3.4.5
 * @author Tomas Rohovsky
 */
@UsesJava8
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTimestamp(i, Timestamp.from(parameter.toInstant()));
	}

	@Override
	public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnName);
		return getZonedDateTime(timestamp);
	}

	@Override
	public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnIndex);
		return getZonedDateTime(timestamp);
	}

	@Override
	public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp timestamp = cs.getTimestamp(columnIndex);
		return getZonedDateTime(timestamp);
	}

	private static ZonedDateTime getZonedDateTime(Timestamp timestamp) {
		if (timestamp != null) {
			return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
		}
		return null;
	}
}
