CREATE TABLE `tasksHome` (
    `id_th` INT NOT NULL,
	`title` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`must` INT NOT NULL,
	PRIMARY KEY (`id_th`)
);
CREATE TABLE `tasksJob` (
    `id_tj` INT NOT NULL,
	`title` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`deadline` VARCHAR(255),
	PRIMARY KEY (`id_tj`)
);
CREATE TABLE `meetingsHome` (
    `id_mh` INT NOT NULL,
	`s_date` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`family` INT NOT NULL,
	PRIMARY KEY (`id_mh`)
);
CREATE TABLE `meetingsJob` (
    `id_mj` INT NOT NULL,
	`s_date` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`withBoss` INT NOT NULL,
	PRIMARY KEY (`id_mj`)
);