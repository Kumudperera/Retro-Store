package com.retroStore.common.command;

import com.retroStore.exception.impl.AppsException;

public interface Command<T extends ExecutionContext> {

    public T execute(T context) throws AppsException;
}
