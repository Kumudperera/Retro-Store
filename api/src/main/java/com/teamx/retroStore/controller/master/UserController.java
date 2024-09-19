package com.teamx.retroStore.controller.master;

import com.teamx.retroStore.controller.BaseController;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.*;
import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.master.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${api.prefix.v1}/user")
public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/getPagedUsers", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<Page<UserDTO>> getPagedUsers(@RequestBody UserSearchRQ searchRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Search users : {} , user {}", searchRQ, credentialsDTO.getID());

        Page<UserDTO> pagedUsers = this.userService.getPagedUsers(searchRQ);

        LOG.info("END : Search users : {} , user {}", searchRQ, credentialsDTO.getID());

        return new ResponseEntity<>(pagedUsers, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/saveOrUpdateUser", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> saveOrUpdateUser(@RequestBody UserRQ userRQ) throws Exception {

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setID(1);
        LOG.info("START : Save/ update user : {} , user {}", userRQ, credentialsDTO.getID());

        UserDTO userRes = this.userService.saveOrUpdateUser(userRQ, credentialsDTO);

        LOG.info("END : Save/ update user : {} , user {}", userRQ, credentialsDTO.getID());

        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/{userID}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserUpdateDTO(@PathVariable Integer userID) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get user : {}, user {} ", userID, credentialsDTO.getID());

        UserDTO roleUpdateDTO = this.userService.getUserUpdateDTO(userID);

        LOG.info("END : get user : {}, user {} ", userID, credentialsDTO.getID());

        return new ResponseEntity<>(roleUpdateDTO, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/updateProfileData", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateProfileData(@RequestBody UserProfileUpdateRQ userProfileUpdateRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : update user profile data: {}, user {} ", userProfileUpdateRQ, credentialsDTO.getID());

        UserDTO user = this.userService.updateUserProfileData(userProfileUpdateRQ);

        LOG.info("END : update user profile data : {}, user {} ", userProfileUpdateRQ, credentialsDTO.getID());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/updateUserPassword", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUserPassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) throws AppsException {

        LOG.info("START : forgot user update password: {}", passwordUpdateDTO);

        PasswordUpdateRQ passwordUpdateRQ = new PasswordUpdateRQ();
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setID(1);
        credentialsDTO.setUsername("SYSTEM");
        passwordUpdateRQ.setPasswordUpdateDTO(passwordUpdateDTO);
        passwordUpdateRQ.setCredentialsDTO(credentialsDTO);

        UserDTO userDTO = this.userService.updateUserPassword(passwordUpdateRQ);

        LOG.info("END : update user password : {} by {}", passwordUpdateDTO, credentialsDTO.getID());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /*@ResponseExceptionHandler
    @RequestMapping(value = "/updateUserForgetPassword", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUserForgetPassword(@RequestBody UserValidateRQ userValidateRQ) throws AppsException {

        LOG.info("START : forgot user update password: {}", userValidateRQ);
        UserDTO validatedUserDTO = this.userService.validateReaderResetPassword(userValidateRQ);
        LOG.info("START : forgot user update password: {}", userValidateRQ);
        return new ResponseEntity<>(validatedUserDTO, HttpStatus.OK);
    }*/


}
