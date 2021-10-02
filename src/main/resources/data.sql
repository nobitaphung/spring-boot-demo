CREATE TABLE `users`
(
            `id` BIGINT NOT NULL AUTO_INCREMENT,
            `username` VARCHAR(20) NULL DEFAULT NULL,
            `email` VARCHAR(50) NULL,
            `password` VARCHAR(120) NOT NULL,
            `enabled` BIT NOT NULL,
            `emailVerified` BIT NOT NULL,
            `role` VARCHAR(20) NULL DEFAULT NULL,
            PRIMARY KEY (`id`),
            UNIQUE INDEX `uq_email` (`email` ASC),
            UNIQUE INDEX `uq_username` (`username` ASC)
);