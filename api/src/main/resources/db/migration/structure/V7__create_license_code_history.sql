
CREATE TABLE `t_license_code_history` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `LICENSE_CODE_ID` int(11) NOT NULL,
    `PURCHASED_DATE` datetime NOT NULL,
    `NEXT_PAYMENT_DATE` datetime NOT NULL,
    `STATUS` char(3) DEFAULT 'ACT',
    `CREATED_BY` int(11) NOT NULL,
    `CREATED_DATE` datetime NOT NULL,
    `MODIFIED_BY` int(11) DEFAULT NULL,
    `MODIFIED_DATE` datetime DEFAULT NULL,
    `VERSION` bigint(20) NOT NULL,
    PRIMARY KEY (`ID`),
    KEY `t_license_code_history_fk1` (`LICENSE_CODE_ID`),
    KEY `t_license_code_history_fk2` (`CREATED_BY`),
    KEY `t_license_code_history_fk3` (`MODIFIED_BY`),
    CONSTRAINT `t_license_code_history_fk1` FOREIGN KEY (`LICENSE_CODE_ID`) REFERENCES `t_license_code` (`ID`),
    CONSTRAINT `t_license_code_history_fk2` FOREIGN KEY (`CREATED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_license_code_history_fk3` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_license_code_history_status` CHECK (`STATUS` in ('ACT','INA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;