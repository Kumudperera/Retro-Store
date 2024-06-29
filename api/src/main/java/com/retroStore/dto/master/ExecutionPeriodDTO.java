package com.retroStore.dto.master;

import com.retroStore.model.terminal.ProcessExecutionPeriod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExecutionPeriodDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;

    private String name;

    private String description;

    public ExecutionPeriodDTO(ProcessExecutionPeriod executionPeriod) {
        this.ID = executionPeriod.getID();
        this.name = executionPeriod.getName();
        this.description = executionPeriod.getDescription();
    }

}
