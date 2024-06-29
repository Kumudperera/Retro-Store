package com.retroStore.service;

import com.retroStore.dao.ProcessExecutionPeriodDao;
import com.retroStore.dto.common.Page;
import com.retroStore.dto.master.ExecutionPeriodDTO;
import com.retroStore.dto.master.terminal.SelectedFolderPageDTO;
import com.retroStore.dto.master.terminal.SelectedFolderSearchRQ;
import com.retroStore.exception.impl.AppsException;
import com.retroStore.model.terminal.ProcessExecutionPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private ProcessExecutionPeriodDao processExecutionPeriodDao;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AppsException.class)
    public List<ExecutionPeriodDTO> getAllExecutionPeriods() {
        List<ExecutionPeriodDTO> result = new ArrayList<>();
        List<ProcessExecutionPeriod> processExecutionPeriods = this.processExecutionPeriodDao.findAll();

        for (ProcessExecutionPeriod processExecutionPeriod : processExecutionPeriods) {
            result.add(new ExecutionPeriodDTO(processExecutionPeriod));
        }

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AppsException.class)
    public Page<SelectedFolderPageDTO> getPagedSelectedFolders(SelectedFolderSearchRQ searchRQ) {
        return this.terminalService.getPagedSelectedFolders(searchRQ);
    }

}
