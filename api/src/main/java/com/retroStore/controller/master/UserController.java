package com.retroStore.controller.master;

import com.retroStore.controller.BaseController;
import com.retroStore.dto.master.terminal.SelectedFolderDTO;
import com.retroStore.exception.annotation.ResponseExceptionHandler;
import com.retroStore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix.v1}/user")
public class UserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/registration", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<SelectedFolderDTO> getSelectedFolder(@PathVariable Integer terminalID) throws Exception {
        LOG.info("START : user registration");

        SelectedFolderDTO selectedFolderDTO = new SelectedFolderDTO(); // TODO

        LOG.info("END : user registration");
        return new ResponseEntity<>(selectedFolderDTO, HttpStatus.OK);
    }
}
