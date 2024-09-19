package com.teamx.retroStore.dao.master.user;

import com.teamx.retroStore.model.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeDao extends JpaRepository<Privilege, Integer> {
}
