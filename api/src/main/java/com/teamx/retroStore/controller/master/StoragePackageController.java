package com.teamx.retroStore.controller.master;

import com.teamx.retroStore.controller.BaseController;
import com.teamx.retroStore.dto.master.storagePackage.SearchRQ;
import com.teamx.retroStore.dto.master.storagePackage.StoragePackageDTO;
import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.master.StoragePackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.prefix.v1}/storage-packages")
public class StoragePackageController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(StoragePackageController.class);

    @Autowired
    private StoragePackageService storagePackageService;

    @ResponseExceptionHandler
    @GetMapping(value = "")
    public ResponseEntity<List<StoragePackageDTO>> index() {

        SearchRQ searchRQ = new SearchRQ();
        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Get storage packages : {} , user {}", searchRQ, credentialsDTO.getId());

        List<StoragePackageDTO> storagePackages = this.storagePackageService.getStoragePackages(searchRQ);

        LOG.info("END : Get storage packages : {} , user {}", searchRQ, credentialsDTO.getId());

        return new ResponseEntity<>(storagePackages, HttpStatus.OK);
    }

}
