package com.retroStore.controller;

import com.retroStore.common.constant.DomainConstants;
import com.retroStore.dto.common.FileDTO;
import com.retroStore.dto.common.UploadFileRQ;
import com.retroStore.exception.annotation.ResponseExceptionHandler;
import com.retroStore.exception.impl.AppsException;
import com.retroStore.security.dto.CredentialsDTO;
import com.retroStore.service.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "${api.prefix.v1}/storage")
public class StorageController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    private StorageService storageService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileType") DomainConstants.UploadFileType fileType) throws AppsException {
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START: Upload file by user {}", credentialsDTO.getUserID());

        UploadFileRQ fileRQ = new UploadFileRQ();
        fileRQ.setFile(file);
        fileRQ.setFileType(fileType);
        fileRQ.setCredentialsDTO(credentialsDTO);
        String attachmentReference = this.storageService.storeFile(fileRQ);
        LOG.info("END: Upload file by user {}", credentialsDTO.getUserID());

        return new ResponseEntity<>(attachmentReference, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadImage/{url}", headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<FileDTO> downloadImage(@PathVariable String url) {
        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Download Image : {} bu user : {}", url, credentialsDTO.getUserID());
        FileDTO fileDTO = new FileDTO();
        try {
            byte[] bytes = this.storageService.downloadFileFromAWS("images/" + url, credentialsDTO);
            fileDTO.setBase64StringFromBytes(bytes);

        } catch (AppsException e) {
            LOG.info("ERROR : Download Image : {} by user : {} failed", url, credentialsDTO.getUserID(), e);
        }
        LOG.info("END : Download Image : {} bu user : {}", url, credentialsDTO.getUserID());

        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadPDF", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<FileDTO> downloadPDF(@RequestBody FileDTO file) {

        CredentialsDTO credentialsDTO = getCredentialsDTO();
        LOG.info("START : Download PDF : {} by user : {}", file.getAttachmentReference(), credentialsDTO.getUserID());
        FileDTO fileDTO = new FileDTO();

        try {
            byte[] bytes;
            if (file.getIsPublicFile()) {
                bytes = this.storageService.downloadFileFromAWS("publicUploads/pdf/" + file.getAttachmentReference(), credentialsDTO);
            } else {
                bytes = this.storageService.downloadFileFromAWS("pdf/" + file.getAttachmentReference(), credentialsDTO);
            }
            fileDTO.setBase64StringFromBytes(bytes);
            LOG.info("END : Download PDF : {} by user : {}", file.getAttachmentReference(), credentialsDTO.getUserID());

        } catch (AppsException e) {
            LOG.error("ERROR : Download PDF : {} by user : {} failed", file.getAttachmentReference(), credentialsDTO.getUserID(), e);
        }
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @ResponseExceptionHandler
    @RequestMapping(value = "/uploadFileWithName", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileType") DomainConstants.UploadFileType fileType, @RequestParam("fileName") String fileName) throws AppsException {
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START: Upload file with file name by user {}", credentialsDTO.getUserID());

        UploadFileRQ fileRQ = new UploadFileRQ();
        fileRQ.setFile(file);
        fileRQ.setFileType(fileType);
        fileRQ.setFileName(fileName);
        fileRQ.setCredentialsDTO(credentialsDTO);

        String attachmentReference = this.storageService.storeFile(fileRQ);
        LOG.info("END: Upload file with file name by user {}", credentialsDTO.getUserID());

        return new ResponseEntity<>(attachmentReference, HttpStatus.OK);
    }

}
