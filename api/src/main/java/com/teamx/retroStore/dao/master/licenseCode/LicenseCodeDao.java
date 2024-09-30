package com.teamx.retroStore.dao.master.licenseCode;

import com.teamx.retroStore.model.LicenseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseCodeDao extends JpaRepository<LicenseCode, Integer> {

}
