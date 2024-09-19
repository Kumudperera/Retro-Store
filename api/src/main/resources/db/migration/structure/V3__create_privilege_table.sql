
CREATE TABLE `t_privilege_category` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `CATEGORY` varchar(255) DEFAULT NULL,
    `MODULE` varchar(10) NOT NULL DEFAULT 'WEB',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `t_privilege` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `CODE` varchar(100) DEFAULT NULL,
    `NAME` varchar(255) DEFAULT NULL,
    `BEHAVIOUR_DESCRIPTION` varchar(255) DEFAULT NULL,
    `PRIVILEGE_CATEGORY_ID` int(11) DEFAULT NULL,
    `STATUS` char(3) DEFAULT 'ACT',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `t_privilege_uq` (`CODE`),
    KEY `t_privilege_fk1` (`PRIVILEGE_CATEGORY_ID`),
    CONSTRAINT `t_privilege_fk1` FOREIGN KEY (`PRIVILEGE_CATEGORY_ID`) REFERENCES `t_privilege_category` (`ID`),
    CONSTRAINT `t_privilege_status` CHECK (`STATUS` in ('ACT','INA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `t_role_privilege` (
    `ROLE_ID` int(11) NOT NULL,
    `PRIVILEGE_ID` int(11) NOT NULL,
    KEY `t_role_privilege_fk1` (`PRIVILEGE_ID`),
    KEY `t_role_privilege_fk2` (`ROLE_ID`),
    CONSTRAINT `t_role_privilege_fk1` FOREIGN KEY (`PRIVILEGE_ID`) REFERENCES `t_privilege` (`ID`),
    CONSTRAINT `t_role_privilege_fk2` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;