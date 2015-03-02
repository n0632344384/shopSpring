DROP DATABASE IF EXISTS `SHOP`;

CREATE DATABASE  IF NOT EXISTS `SHOP` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `SHOP`;

CREATE TABLE IF NOT EXISTS `ROLES` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`name` ENUM('USER', 'MANDATOR', 'ADMIN'),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `CATEGORIES` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(30) DEFAULT NULL,
	`parent` int unsigned DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ADDRESSES` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`zip` varchar(10) DEFAULT NULL,
	`state` varchar(20) DEFAULT NULL,
	`city` varchar(20) DEFAULT NULL,
	`street` varchar(30) DEFAULT NULL,
	`phone` varchar(30) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `USERS` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`login` varchar(10) DEFAULT NULL,
	`password` varchar(10) DEFAULT NULL,
	`firstName` varchar(20) DEFAULT NULL,
	`lastName` varchar(20) DEFAULT NULL,
	`email` varchar(30) DEFAULT NULL,
	`account` bigint DEFAULT NULL,
	`age` varchar(3) DEFAULT NULL,
	`gender` boolean DEFAULT NULL,
	`role_id` int unsigned DEFAULT NULL,
	`address_id` int unsigned DEFAULT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`role_id`) REFERENCES `ROLES` (`id`) ON UPDATE CASCADE,
	FOREIGN KEY (`address_id`) REFERENCES `ADDRESSES` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `PRODUCTS` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(100) DEFAULT NULL,
	`description` varchar(1000) DEFAULT NULL,
	`price` int DEFAULT NULL,
	`image` varchar(30) DEFAULT NULL,
	`category_id` int unsigned DEFAULT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`category_id`) REFERENCES `CATEGORIES` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ORDERS` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`date` date DEFAULT NULL,
	`time` time DEFAULT NULL,
	`user_id` int unsigned DEFAULT NULL,
	`product_id` int unsigned DEFAULT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`) ON UPDATE CASCADE,
	FOREIGN KEY (`product_id`) REFERENCES `PRODUCTS` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;