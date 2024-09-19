package com.teamx.retroStore.service.master;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.config.AppsProperties;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dto.master.user.PasswordUpdateDTO;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.dto.master.user.UserRQ;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import com.teamx.retroStore.service.master.support.UpdatePasswordHandler;
import com.teamx.retroStore.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(value = "classpath:apps.properties")
@PropertySource(name = "apps.properties", value = "classpath:apps.properties")
public class UserServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;


    @Autowired
    private UserService userService;


    @Autowired
    private AppsProperties appsProperties;

    @Test
    void setAdminUserPassword() throws Exception {
        User adminUser = userDao.findByUsernameIgnoreCase(appsProperties.getAdminUsername());
        adminUser.setPassword(PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), appsProperties.getAdminUserPassword()));

        this.securityService.destroyUserCache();
        userDao.saveAndFlush(adminUser);
    }
}
