
CREATE TABLE `t_terminal` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `TERMINAL_USER_ID` int(11) NOT NULL,
    `LICENSE_CODE_ID` int(11) NOT NULL,
    `OWNER_ID` int(11) NOT NULL,
    `STATUS` char(3) DEFAULT 'ACT',
    `CREATED_BY` int(11) NOT NULL,
    `CREATED_DATE` datetime NOT NULL,
    `MODIFIED_BY` int(11) DEFAULT NULL,
    `MODIFIED_DATE` datetime DEFAULT NULL,
    `VERSION` bigint(20) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `t_terminal_user_owner_license_uq` (`TERMINAL_USER_ID`, `LICENSE_CODE_ID`, `OWNER_ID`),
    KEY `t_terminal_fk1` (`TERMINAL_USER_ID`),
    KEY `t_terminal_fk2` (`OWNER_ID`),
    KEY `t_terminal_fk3` (`CREATED_BY`),
    KEY `t_terminal_fk4` (`MODIFIED_BY`),
    CONSTRAINT `t_terminal_fk1` FOREIGN KEY (`TERMINAL_USER_ID`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_terminal_fk2` FOREIGN KEY (`OWNER_ID`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_terminal_fk3` FOREIGN KEY (`CREATED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_terminal_fk4` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_terminal_status` CHECK (`STATUS` in ('ACT','INA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;