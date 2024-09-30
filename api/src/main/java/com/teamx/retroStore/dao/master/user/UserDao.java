package com.teamx.retroStore.dao.master.user;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsernameIgnoreCase(String username);

    User findByEmailIgnoreCase(String username);

    User findByEmail(String username);

    Optional<User> findByIdAndUserType(Integer id, DomainConstants.UserType userType);

}
