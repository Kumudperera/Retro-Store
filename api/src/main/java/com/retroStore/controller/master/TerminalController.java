package com.retroStore.controller.master;


import com.retroStore.controller.BaseController;
import com.retroStore.dto.common.Page;
import com.retroStore.dto.master.ExecutionPeriodDTO;
import com.retroStore.dto.master.terminal.SelectedFolderDTO;
import com.retroStore.dto.master.terminal.SelectedFolderPageDTO;
import com.retroStore.dto.master.terminal.SelectedFolderSearchRQ;
import com.retroStore.exception.annotation.ResponseExceptionHandler;
import com.retroStore.security.dto.CredentialsDTO;
import com.retroStore.service.TerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix.v1}/terminal")
public class TerminalController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(TerminalController.class);

    @Autowired
    private TerminalService terminalService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/{terminalID}/selected-folders", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<SelectedFolderDTO> getSelectedFolder(@PathVariable Integer terminalID) throws Exception {
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get selected folders : {}, user {} ", terminalID, credentialsDTO.getUserID());

        SelectedFolderDTO selectedFolderDTO = new SelectedFolderDTO(); // TODO

        LOG.info("END : get selected folders : {}, user {} ", terminalID, credentialsDTO.getUserID());
        return new ResponseEntity<>(selectedFolderDTO, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/execution-periods", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<List<ExecutionPeriodDTO>> getAllExecutionPeriods() {
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get selected folders : user {} ", credentialsDTO.getUserID());

        List<ExecutionPeriodDTO> executionPeriodDTOs = this.terminalService.getAllExecutionPeriods();

        LOG.info("END : get selected folders : user {} ", credentialsDTO.getUserID());
        return new ResponseEntity<>(executionPeriodDTOs, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/selected-folders", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Page<SelectedFolderPageDTO>> getPagedUsers(@RequestBody SelectedFolderSearchRQ searchRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Search selected folders : {} , user {}", searchRQ, credentialsDTO.getUserID());

        Page<SelectedFolderPageDTO> pagedUsers = this.terminalService.getPagedSelectedFolders(searchRQ);

        LOG.info("END : Search selected folders : {} , user {}", searchRQ, credentialsDTO.getUserID());

        return new ResponseEntity<>(pagedUsers, HttpStatus.OK);
    }

}
