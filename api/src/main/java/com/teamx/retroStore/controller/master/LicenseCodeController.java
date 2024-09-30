package com.teamx.retroStore.controller.master;

import com.teamx.retroStore.controller.BaseController;
import com.teamx.retroStore.dto.master.licenseCode.CreateRQ;
import com.teamx.retroStore.dto.master.licenseCode.LicenseCodeDTO;
import com.teamx.retroStore.dto.master.licenseCode.LicenseCodeRQ;
import com.teamx.retroStore.dto.master.licenseCode.UpdateRQ;
import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.master.LicenseCodeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${api.prefix.v1}/license-code")
public class LicenseCodeController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(LicenseCodeController.class);

    @Autowired
    private LicenseCodeService licenseCodeService;


    @ResponseExceptionHandler
    @PostMapping(value = "")
    public ResponseEntity<LicenseCodeDTO> create(@Valid @RequestBody CreateRQ createRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Create License Code : {}, user {} ", createRQ, credentialsDTO.getId());

        LicenseCodeDTO licenseCode = this.licenseCodeService.create(new LicenseCodeRQ(createRQ), credentialsDTO);

        LOG.info("END : Create License Code : {}, user {} ", createRQ, credentialsDTO.getId());

        return new ResponseEntity<>(licenseCode, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @PutMapping(value = "/{id}")
    public ResponseEntity<LicenseCodeDTO> update(@PathVariable int id, @Valid @RequestBody UpdateRQ updateRQ) throws Exception {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Update License Code : {}, user {} ", updateRQ, credentialsDTO.getId());

        LicenseCodeDTO licenseCode = this.licenseCodeService.update(new LicenseCodeRQ(id, updateRQ), credentialsDTO);

        LOG.info("END : Update License Code : {}, user {} ", updateRQ, credentialsDTO.getId());

        return new ResponseEntity<>(licenseCode, HttpStatus.OK);
    }

}
