/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context.request.async;

import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.function.Consumer;

/**
 * Extends {@link NativeWebRequest} with methods for asynchronous request processing.
 *
 * @author Rossen Stoyanchev
 * @since 3.2
 */
public interface AsyncWebRequest extends NativeWebRequest {

	/**
	 * 设置超时时间
	 * Set the time required for concurrent handling to complete.
	 * This property should not be set when concurrent handling is in progress,
	 * i.e. when {@link #isAsyncStarted()} is {@code true}.
	 *
	 * @param timeout amount of time in milliseconds; {@code null} means no
	 *                timeout, i.e. rely on the default timeout of the container.
	 */
	void setTimeout(@Nullable Long timeout);

	/**
	 * 添加超时处理器
	 * Add a handler to invoke when concurrent handling has timed out.
	 */
	void addTimeoutHandler(Runnable runnable);

	/**
	 * 添加异常处理器
	 * Add a handler to invoke when an error occurred while concurrent
	 * handling of a request.
	 *
	 * @since 5.0
	 */
	void addErrorHandler(Consumer<Throwable> exceptionHandler);

	/**
	 * 添加请求处理完成后的处理器
	 * Add a handler to invoke when request processing completes.
	 */
	void addCompletionHandler(Runnable runnable);

	/**
	 * 开始异步执行
	 * Mark the start of asynchronous request processing so that when the main
	 * processing thread exits, the response remains open for further processing
	 * in another thread.
	 *
	 * @throws IllegalStateException if async processing has completed or is not supported
	 */
	void startAsync();

	/**
	 * 判断是否启动了异步处理
	 * Whether the request is in async mode following a call to {@link #startAsync()}.
	 * Returns "false" if asynchronous processing never started, has completed,
	 * or the request was dispatched for further processing.
	 */
	boolean isAsyncStarted();

	/**
	 * Dispatch the request to the container in order to resume processing after
	 * concurrent execution in an application thread.
	 */
	void dispatch();

	/**
	 * 判断异步处理是否已经处理完了
	 * Whether asynchronous processing has completed.
	 */
	boolean isAsyncComplete();

}
