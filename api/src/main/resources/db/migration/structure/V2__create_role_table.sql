CREATE TABLE `t_role` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(255) DEFAULT NULL,
    `DESCRIPTION` varchar(255) DEFAULT NULL,
    `STATUS` char(3) DEFAULT 'ACT',
    `CREATED_BY` int(11) NOT NULL,
    `CREATED_DATE` datetime NOT NULL,
    `MODIFIED_BY` int(11) DEFAULT NULL,
    `MODIFIED_DATE` datetime DEFAULT NULL,
    `VERSION` bigint(20) NOT NULL,
    PRIMARY KEY (`ID`),
    KEY `t_role_fk1` (`CREATED_BY`),
    KEY `t_role_fk2` (`MODIFIED_BY`),
    CONSTRAINT `T_ROLE_FK1` FOREIGN KEY (`CREATED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_role_fk2` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_role_status` CHECK (`STATUS` in ('ACT','INA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `t_user_role` (
    `USER_ID` int(11) NOT NULL,
    `ROLE_ID` int(11) NOT NULL,
    PRIMARY KEY (`USER_ID`,`ROLE_ID`) USING BTREE,
    KEY `t_user_role_fk2` (`ROLE_ID`) USING BTREE,
    CONSTRAINT `t_user_role_fk1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_user_role_fk2` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;