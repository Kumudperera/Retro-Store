package com.teamx.retroStore.controller.master;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.controller.BaseController;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.PrivilegeDTO;
import com.teamx.retroStore.dto.master.user.RoleDTO;
import com.teamx.retroStore.dto.master.user.RoleSearchRQ;
import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.master.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "${api.prefix.v1}/role")
public class RoleController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/getPagedRoles", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Page<RoleDTO>> getPagedRoles(@RequestBody RoleSearchRQ roleSearchRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Search roles : {} , user {}", roleSearchRQ, credentialsDTO.getId());

        Page<RoleDTO> pagedRoles = this.roleService.getPagedRoles(roleSearchRQ);

        LOG.info("END : Search roles : {} , user {}", roleSearchRQ, credentialsDTO.getId());

        return new ResponseEntity<>(pagedRoles, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/getRoleDTO/{roleID}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<RoleDTO> getRoleUpdateDTO(@PathVariable Integer roleID) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get role : {}, user {} ", roleID, credentialsDTO.getId());

        RoleDTO roleUpdateDTO = this.roleService.getRoleUpdateDTO(roleID);

        LOG.info("END : get role : {}, user {} ", roleID, credentialsDTO.getId());

        return new ResponseEntity<>(roleUpdateDTO, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/saveOrUpdateRole", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<RoleDTO> saveOrUpdateRole(@RequestBody RoleDTO roleUpdateDTO) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : save/update role : {}, user {}", roleUpdateDTO, credentialsDTO.getId());

        RoleDTO roleUpdateDTOResponse = this.roleService.saveOrUpdateRole(roleUpdateDTO, credentialsDTO);

        LOG.info("END : save/update role : {}, user {}", roleUpdateDTO, credentialsDTO.getId());

        return new ResponseEntity<>(roleUpdateDTOResponse, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/getSystemPrivileges", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<PrivilegeDTO>>> getSystemPrivileges() throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get System Privileges for user {}", credentialsDTO.getId());

        Map<String, List<PrivilegeDTO>> privileges = this.roleService.getCategoryWisePrivileges(AppsConstants.Status.ACT);

        LOG.info("END : System Privileges for user {}", credentialsDTO.getId());

        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch all roles",
            description = "Fetches all roles entities and their data from data source")
    @ResponseExceptionHandler
    @RequestMapping(value = "/getRoles", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> getRoles() throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : get roles by user {}", credentialsDTO.getId());

        List<RoleDTO> roles = this.roleService.getRoles(null);

        LOG.info("END : get roles by user {}", credentialsDTO.getId());

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/getSystemRoles", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> getSystemRoles() throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get system roles by user {}", credentialsDTO.getId());

        List<RoleDTO> roles = this.roleService.getSystemRoles();

        LOG.info("END : Get system roles by user {}", credentialsDTO.getId());

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
