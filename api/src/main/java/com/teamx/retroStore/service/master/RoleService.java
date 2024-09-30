package com.teamx.retroStore.service.master;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dao.master.user.PrivilegeDao;
import com.teamx.retroStore.dao.master.user.RoleDao;
import com.teamx.retroStore.dao.master.user.jdbc.RoleJDBCDao;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.PrivilegeDTO;
import com.teamx.retroStore.dto.master.user.RoleDTO;
import com.teamx.retroStore.dto.master.user.RoleSearchRQ;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.model.user.Role;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleService {

    private static Logger LOG = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private RoleJDBCDao roleJDBCDao;

    @Autowired
    private SecurityService securityService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public RoleDTO saveOrUpdateRole(RoleDTO roleUpdateDTO, CredentialsDTO credentialsDTO) throws AppsException {
        LOG.info("START : Role update : {} : {}", roleUpdateDTO, credentialsDTO.getId());
        Date date = new Date();
        Role role = null;

        boolean isNewRole = roleUpdateDTO.getID() == null;

        if (!isNewRole) {
            role = roleDao.getReferenceById(roleUpdateDTO.getID());
            role.setModifiedBy(credentialsDTO.getId());
            role.setModifiedDate(date);
        } else {
            role = new Role();

            role.setCreatedBy(credentialsDTO.getId());
            role.setCreatedDate(date);
        }

        role.setName(roleUpdateDTO.getName());
        role.setDescription(roleUpdateDTO.getDescription());
        role.setStatus(roleUpdateDTO.getStatus());

        if (isNewRole) {
            role.getPrivileges().addAll(privilegeDao.findAllById(roleUpdateDTO.getAddedPrivileges()));
        } else {
            if (!roleUpdateDTO.getDeletedPrivileges().isEmpty()) {
                role.removePrivileges(privilegeDao.findAllById(roleUpdateDTO.getDeletedPrivileges()));
            }
            if (!roleUpdateDTO.getAddedPrivileges().isEmpty()) {
                role.getPrivileges().addAll(privilegeDao.findAllById(roleUpdateDTO.getAddedPrivileges()));
            }
        }

        role = roleDao.saveAndFlush(role);
        this.securityService.destroyUserCache();

        LOG.info("END : Role update : {} : {}", roleUpdateDTO, credentialsDTO.getId());
        return new RoleDTO(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RoleDTO getRoleUpdateDTO(Integer roleID) {
        return new RoleDTO(roleDao.getReferenceById(roleID));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<RoleDTO> getPagedRoles(RoleSearchRQ searchRQ) {
        return roleJDBCDao.getPagedRoles(searchRQ);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleDTO> getRoles(AppsConstants.Status status) {
        return roleJDBCDao.getRoles(status);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleDTO> getSystemRoles() {
        return roleJDBCDao.getSystemRoles();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, List<PrivilegeDTO>> getCategoryWisePrivileges(AppsConstants.Status status) {
        Map<String, List<PrivilegeDTO>> result = new HashMap<>();
        List<PrivilegeDTO> privileges = roleJDBCDao.getPrivileges(status);
        for (PrivilegeDTO privilegeDTO : privileges) {
            result.putIfAbsent(privilegeDTO.getCategory(), new ArrayList<>());
            result.get(privilegeDTO.getCategory()).add(privilegeDTO);
        }
        return result;
    }
}
