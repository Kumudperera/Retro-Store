package com.teamx.retroStore.common.command;

import com.teamx.retroStore.exception.impl.AppsException;

public interface Command<T extends ExecutionContext> {

    public T execute(T context) throws AppsException;
}
