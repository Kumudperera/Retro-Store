package com.teamx.retroStore.service.master;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dao.master.storagePackage.JDBCDao;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.storagePackage.CreateRQ;
import com.teamx.retroStore.dto.master.storagePackage.SearchRQ;
import com.teamx.retroStore.dto.master.storagePackage.StoragePackageDTO;
import com.teamx.retroStore.dto.master.user.RoleDTO;
import com.teamx.retroStore.dto.master.user.RoleSearchRQ;
import com.teamx.retroStore.model.StoragePackage;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StoragePackageService {

    @Autowired
    private JDBCDao jdbcDao;

    public StoragePackageDTO create(CreateRQ createRQ, CredentialsDTO credentialsDTO) {
        Date now = new Date();
        StoragePackage storagePackage = new StoragePackage();

        storagePackage.setPeriodPrice(createRQ.getPrice());
        storagePackage.setStorage(createRQ.getStorage());
        storagePackage.setName(createRQ.getName());
        storagePackage.setDescription(createRQ.getDescription());
        storagePackage.setStatus(AppsConstants.Status.ACT);

        storagePackage.setCreatedDate(now);
        storagePackage.setCreatedBy(credentialsDTO.getId());


        return new StoragePackageDTO(storagePackage);

    }

    public List<StoragePackageDTO> getStoragePackages(SearchRQ searchRQ) {
        return this.jdbcDao.getStoragePackages(searchRQ);
    }
}
