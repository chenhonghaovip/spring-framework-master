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
package org.apache.ibatis.submitted.hashmaptypehandler;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

public interface Mapper {

	@Select("select * from users where id = #{id} and name = #{name}")
	User getUser(@Param("id") Integer id, @Param("name") String name);

	User getUserXml(@Param("id") Integer id, @Param("name") String name);

	@Select("select * from users where name = #{map}")
	User getUserWithTypeHandler(HashMap<String, String> map);

	User getUserWithTypeHandlerXml(HashMap<String, String> map);

}
