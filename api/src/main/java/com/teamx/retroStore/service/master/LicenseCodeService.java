package com.teamx.retroStore.service.master;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dao.master.licenseCode.LicenseCodeDao;
import com.teamx.retroStore.dao.master.licenseCode.LicenseCodeHistoryDao;
import com.teamx.retroStore.dao.master.storagePackage.StoragePackageDao;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dto.master.licenseCode.CreateRQ;
import com.teamx.retroStore.dto.master.licenseCode.LicenseCodeDTO;
import com.teamx.retroStore.dto.master.licenseCode.LicenseCodeRQ;
import com.teamx.retroStore.dto.master.licenseCode.UpdateRQ;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.model.LicenseCode;
import com.teamx.retroStore.model.StoragePackage;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class LicenseCodeService {
    private static Logger LOG = LoggerFactory.getLogger(LicenseCodeService.class);

    @Autowired
    private LicenseCodeDao licenseCodeDao;

    @Autowired
    private LicenseCodeHistoryDao licenseCodeHistoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StoragePackageDao storagePackageDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LicenseCodeDTO create(LicenseCodeRQ createRQ, CredentialsDTO credentialsDTO) throws AppsException {
        Date now = new Date();
        User companyUser = this.userDao.findByIdAndUserType(createRQ.getCompanyUserId(), DomainConstants.UserType.COMPANY_USER)
                .orElseThrow(() -> new AppsException("Company user not found for company user Id "
                        .concat(createRQ.getCompanyUserId().toString())));
        StoragePackage storagePackage = this.storagePackageDao.findById(createRQ.getStoragePackageId())
                .orElseThrow(() -> new AppsException("Storage package not found for storage package Id "
                        .concat(createRQ.getStoragePackageId().toString())));

        LicenseCode licenseCode = new LicenseCode();
        licenseCode.setCode(UUID.randomUUID().toString());
        licenseCode.setCompanyUser(companyUser);
        licenseCode.setStatus(AppsConstants.Status.ACT);
        licenseCode.setValid(AppsConstants.YesNo.Y);
        licenseCode.setCreatedDate(now);
        licenseCode.setCreatedBy(credentialsDTO.getId());
        licenseCode.setStoragePackage(storagePackage);
        licenseCode.setRemainStorage(storagePackage.getStorage());
        licenseCode.setPurchasedStorage(storagePackage.getStorage());
        licenseCode.setPurchasedDate(now);
        licenseCode.setNextPaymentDate(CalendarUtil.addMonths(now, storagePackage.getPeriod().getNoOfMonths()));

        licenseCode.addLicenseCodeHistory(now, credentialsDTO);

        licenseCode = this.licenseCodeDao.saveAndFlush(licenseCode);

        return new LicenseCodeDTO(licenseCode);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LicenseCodeDTO update(LicenseCodeRQ updateRQ, CredentialsDTO credentialsDTO) throws AppsException {
        Date now = new Date();
        LicenseCode licenseCode = this.licenseCodeDao.findById(updateRQ.getId())
                .orElseThrow(() -> new AppsException("License code is not found for Id "
                        .concat(updateRQ.getId().toString())));
        BigDecimal remainStorage = licenseCode.getRemainStorage();
        StoragePackage storagePackage = licenseCode.getStoragePackage();

        if (updateRQ.getUsedStorage() != null) {
            remainStorage = licenseCode.getStoragePackage()
                    .getStorage().subtract(updateRQ.getUsedStorage());
        }

        if (remainStorage.signum() == -1) {
            throw new AppsException("Remain can't be minus");
        }

        // Package change
        if (updateRQ.getValid().equals(AppsConstants.YesNo.Y) &&
                updateRQ.getStoragePackageId() != null &&
                !storagePackage.getId().equals(updateRQ.getStoragePackageId())) {
            storagePackage = this.storagePackageDao.findById(updateRQ.getStoragePackageId())
                    .orElseThrow(() -> new AppsException("Storage package not found for storage package Id "
                            .concat(updateRQ.getStoragePackageId().toString())));
            remainStorage = remainStorage.add(storagePackage.getStorage());
            licenseCode.setStoragePackage(storagePackage);
        }

        // License Renewal
        if (updateRQ.getValid().equals(AppsConstants.YesNo.Y)
                && !licenseCode.getValid().equals(updateRQ.getValid())) {
            licenseCode.addLicenseCodeHistory(now, credentialsDTO);
        }

        licenseCode.setValid(updateRQ.getValid());
        licenseCode.setRemainStorage(remainStorage);
        licenseCode.setStatus(updateRQ.getStatus());
        licenseCode.setModifiedDate(now);
        licenseCode.setModifiedBy(credentialsDTO.getId());

        licenseCode = this.licenseCodeDao.saveAndFlush(licenseCode);

        return new LicenseCodeDTO(licenseCode);
    }
}
