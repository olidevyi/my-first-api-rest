CREATE TABLE IF NOT EXISTS `db_springboot_dev`.`clients` (
    `id_client` INT ZEROFILL NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `lastName` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `registration_date` DATE NOT NULL,
    PRIMARY KEY(`id_client`)
);