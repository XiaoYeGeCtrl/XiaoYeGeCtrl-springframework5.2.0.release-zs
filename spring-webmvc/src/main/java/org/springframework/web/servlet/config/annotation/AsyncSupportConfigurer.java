/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.web.servlet.config.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

/**
 * Helps with configuring options for asynchronous request processing.
 *
 * @author Rossen Stoyanchev
 * @since 3.2
 */
public class AsyncSupportConfigurer {

    private final List<CallableProcessingInterceptor> callableInterceptors = new ArrayList<>();
    private final List<DeferredResultProcessingInterceptor> deferredResultInterceptors = new ArrayList<>();
    @Nullable
    private AsyncTaskExecutor taskExecutor;
    @Nullable
    private Long timeout;

    /**
     * Specify the amount of time, in milliseconds, before asynchronous request
     * handling times out. In Servlet 3, the timeout begins after the main request
     * processing thread has exited and ends when the request is dispatched again
     * for further processing of the concurrently produced result.
     * <p>If this value is not set, the default timeout of the underlying
     * implementation is used, e.g. 10 seconds on Tomcat with Servlet 3.
     *
     * @param timeout the timeout value in milliseconds
     */
    public AsyncSupportConfigurer setDefaultTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Configure lifecycle interceptors with callbacks around concurrent request
     * execution that starts when a controller returns a
     * {@link java.util.concurrent.Callable}.
     *
     * @param interceptors the interceptors to register
     */
    public AsyncSupportConfigurer registerCallableInterceptors(CallableProcessingInterceptor... interceptors) {
        this.callableInterceptors.addAll(Arrays.asList(interceptors));
        return this;
    }

    /**
     * Configure lifecycle interceptors with callbacks around concurrent request
     * execution that starts when a controller returns a {@link DeferredResult}.
     *
     * @param interceptors the interceptors to register
     */
    public AsyncSupportConfigurer registerDeferredResultInterceptors(
            DeferredResultProcessingInterceptor... interceptors) {

        this.deferredResultInterceptors.addAll(Arrays.asList(interceptors));
        return this;
    }

    @Nullable
    protected AsyncTaskExecutor getTaskExecutor() {
        return this.taskExecutor;
    }

    /**
     * The provided task executor is used to:
     * <ol>
     * <li>Handle {@link Callable} controller method return values.
     * <li>Perform blocking writes when streaming to the response
     * through a reactive (e.g. Reactor, RxJava) controller method return value.
     * </ol>
     * <p>By default only a {@link SimpleAsyncTaskExecutor} is used. However when
     * using the above two use cases, it's recommended to configure an executor
     * backed by a thread pool such as {@link ThreadPoolTaskExecutor}.
     *
     * @param taskExecutor the task executor instance to use by default
     */
    public AsyncSupportConfigurer setTaskExecutor(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        return this;
    }

    @Nullable
    protected Long getTimeout() {
        return this.timeout;
    }

    protected List<CallableProcessingInterceptor> getCallableInterceptors() {
        return this.callableInterceptors;
    }

    protected List<DeferredResultProcessingInterceptor> getDeferredResultInterceptors() {
        return this.deferredResultInterceptors;
    }

}
