package com.teamx.retroStore.dao.master.licenseCode;

import com.teamx.retroStore.model.LicenseCodeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseCodeHistoryDao extends JpaRepository<LicenseCodeHistory, Integer> {
}
