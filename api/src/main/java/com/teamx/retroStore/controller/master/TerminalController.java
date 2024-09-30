package com.teamx.retroStore.controller.master;


import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.service.master.TerminalBuildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix.v1}/user")
public class TerminalController {

    private static final Logger LOG = LoggerFactory.getLogger(TerminalController.class);

    @Autowired
    private TerminalBuildService terminalBuildService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/build", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<String> buildApp() throws Exception {
        boolean isBuilt = terminalBuildService.buildApp();
        if (isBuilt) {
            return ResponseEntity.ok("Build completed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Build failed.");
        }
    }
}
