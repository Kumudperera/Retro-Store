
CREATE TABLE `t_storage_package` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(50) NOT NULL,
    `DESCRIPTION` varchar(100) NOT NULL,
    `STORAGE` decimal(11,4) NOT NULL,
    `PRICE` decimal(20,5) NOT NULL,
    `STATUS` char(3) DEFAULT 'ACT',
    `CREATED_BY` int(11) NOT NULL,
    `CREATED_DATE` datetime NOT NULL,
    `MODIFIED_BY` int(11) DEFAULT NULL,
    `MODIFIED_DATE` datetime DEFAULT NULL,
    `VERSION` bigint(20) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `t_storage_package_name_uq` (`NAME`),
    KEY `t_storage_package_fk1` (`CREATED_BY`),
    KEY `t_storage_package_fk2` (`MODIFIED_BY`),
    CONSTRAINT `t_storage_package_fk1` FOREIGN KEY (`CREATED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_storage_package_fk2` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `t_user` (`ID`),
    CONSTRAINT `t_storage_package_status` CHECK (`STATUS` in ('ACT','INA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;