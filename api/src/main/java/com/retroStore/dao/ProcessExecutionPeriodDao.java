package com.retroStore.dao;

import com.retroStore.model.terminal.ProcessExecutionPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessExecutionPeriodDao extends JpaRepository<ProcessExecutionPeriod, Integer> {
}
