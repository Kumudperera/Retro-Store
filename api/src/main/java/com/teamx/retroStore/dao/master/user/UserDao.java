package com.teamx.retroStore.dao.master.user;

import com.teamx.retroStore.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByUsernameIgnoreCase(String username);

    User findByEmailIgnoreCase(String username);

    User findByEmail(String username);

//    User getUserByForgetPasswordUUID(String uuid);
}
