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
package org.apache.ibatis.submitted.results_id;

import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.*;
import static org.assertj.core.api.BDDAssertions.then;

public class IdConflictTest {

	@Test
	public void shouldFailOnDuplicatedId() throws Exception {
		Configuration configuration = new Configuration();
		when(configuration).addMapper(IdConflictMapper.class);
		then(caughtException()).isInstanceOf(RuntimeException.class)
				.hasMessage("Result Maps collection already contains value for org.apache.ibatis.submitted.results_id.IdConflictMapper.userResult");
	}

}
