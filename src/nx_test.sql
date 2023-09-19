-- Comment out the next 2 lines to import to a different database name

-- TODO: Get rid of this drop statement
DROP SCHEMA IF EXISTS `nx_test`;

CREATE DATABASE IF NOT EXISTS `nx_test`;
USE `nx_test`;


-- MySQL dump 10.17  Distrib 10.3.17-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: dv800a88
-- ------------------------------------------------------
-- Server version	10.3.17-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

-- ----------------------------
--  Table structure for `api_key`
-- ----------------------------
DROP TABLE IF EXISTS `api_key`;
CREATE TABLE `api_key`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `api_key` varchar(32) NOT NULL,
    `name`    varchar(128) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `api_key`
-- ----------------------------
BEGIN;
INSERT INTO `api_key`
VALUES ('1', 'one', 'something');
COMMIT;

-- ----------------------------
--  Table structure for `entity_event`
-- ----------------------------
DROP TABLE IF EXISTS `entity_event`;
CREATE TABLE `entity_event`
(
    `entity_event_id` int(11)     NOT NULL AUTO_INCREMENT,
    `entity_type_id`  int(11)     NOT NULL,
    `entity_id`       int(11)     NOT NULL,
    `application`     varchar(100)         DEFAULT NULL,
    `system_login_id` int(11)              DEFAULT NULL,
    `event`           varchar(50) NOT NULL,
    `date`            timestamp   NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`entity_event_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Table structure for `entity_status`
-- ----------------------------
DROP TABLE IF EXISTS `entity_status`;
CREATE TABLE `entity_status`
(
    `entity_status_id`       int(11)     NOT NULL AUTO_INCREMENT,
    `entity_type_id`         int(11)     NOT NULL,
    `entity_id`              int(11)     NOT NULL,
    `state`                  varchar(10) NOT NULL,
    `create_system_login_id` int(11)              DEFAULT NULL,
    `create_date`            timestamp   NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `update_system_login_id` int(11)              DEFAULT NULL,
    `update_date`            timestamp   NOT NULL DEFAULT '0000-00-00 00:00:00',
    PRIMARY KEY (`entity_status_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Table structure for `entity_type`
-- ----------------------------
DROP TABLE IF EXISTS `entity_type`;
CREATE TABLE `entity_type`
(
    `entity_type_id`      int(11)      NOT NULL AUTO_INCREMENT,
    `type`                varchar(100) NOT NULL,
    `record_events`       tinyint(1)   NOT NULL,
    `record_status`       tinyint(1)   NOT NULL,
    `logical_delete_flag` tinyint(1)   NOT NULL,
    PRIMARY KEY (`entity_type_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
--  Records of `entity_type`
-- ----------------------------
BEGIN;
INSERT INTO `entity_type`
VALUES ('1', 'rule', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('2', 'user_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('3', 'user', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('4', 'user_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('5', 'device_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('6', 'device', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('7', 'device_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('8', 'session_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('9', 'session', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('10', 'session_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('11', 'stream_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('12', 'stream', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('13', 'stream_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('14', 'notification_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('15', 'notification', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('16', 'notification_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('17', 'key_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('18', 'key', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('19', 'key_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('20', 'channel_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('21', 'channel', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('22', 'channel_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('23', 'circuit_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('24', 'circuit', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('25', 'circuit_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('26', 'partition_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('27', 'partition', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('28', 'partition_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('29', 'unit_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('30', 'unit', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('31', 'unit_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('32', 'button_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('33', 'button', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('34', 'button_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('35', 'profile_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('36', 'profile', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('37', 'profile_meta', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('38', 'permission_type', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('39', 'permission', '1', '1', '1');
INSERT INTO `entity_type`
VALUES ('40', 'permission_meta', '1', '1', '1');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule`
    DISABLE KEYS */;
INSERT INTO `rule`
VALUES (1, 'None');
INSERT INTO `rule`
VALUES (2, 'Boolean');
INSERT INTO `rule`
VALUES (3, 'Integer');
INSERT INTO `rule`
VALUES (4, 'String');
INSERT INTO `rule`
VALUES (5, 'Time');
/*!40000 ALTER TABLE `rule`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type`
    DISABLE KEYS */;
INSERT INTO `user_type`
VALUES (1, 'None');
INSERT INTO `user_type`
VALUES (2, 'SAT');
INSERT INTO `user_type`
VALUES (3, 'Keyset');
/*!40000 ALTER TABLE `user_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `id`           int(11)                                             NOT NULL AUTO_INCREMENT,
    `user_type_id` int(11)                                             NOT NULL DEFAULT 1,
    `login_id`     varchar(255)                                        NOT NULL,
    `password`     varchar(255)                                        NOT NULL,
    `first_name`   varchar(255)                                        NOT NULL,
    `last_name`    varchar(255)                                        NOT NULL,
    `auth_mode`    enum ('DB','LDAP','PAM','RSA','WinAD','Use Global') NOT NULL DEFAULT 'DB',
    `email`        varchar(1024)                                       NOT NULL DEFAULT 'N/A',
    PRIMARY KEY (`id`),
    KEY `user_type_id_fk` (`user_type_id`),
    CONSTRAINT `user_type_id_fk` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`login_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 1, 'N/A', '52047EFE3E51DB09711D9D9AAA3C6A52', 'N/A', 'N/A', 'DB', 'N/A');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_meta`
--

DROP TABLE IF EXISTS `user_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_meta`
(
    `id`      int(11)      NOT NULL AUTO_INCREMENT,
    `user_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id` int(11)      NOT NULL DEFAULT 1,
    `key`     varchar(255) NOT NULL,
    `value`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id_fk` (`user_id`),
    CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `user_meta`
--

LOCK TABLES `user_meta` WRITE;
/*!40000 ALTER TABLE `user_meta`
    DISABLE KEYS */;
INSERT INTO `user_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `user_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type`
--

DROP TABLE IF EXISTS `device_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `device_type`
--

LOCK TABLES `device_type` WRITE;
/*!40000 ALTER TABLE `device_type`
    DISABLE KEYS */;
INSERT INTO `device_type`
VALUES (1, 'None');
INSERT INTO `device_type`
VALUES (2, 'Fault Tolerant Server');
INSERT INTO `device_type`
VALUES (3, 'Keyset');
INSERT INTO `device_type`
VALUES (4, 'Server');
INSERT INTO `device_type`
VALUES (5, 'T1');
INSERT INTO `device_type`
VALUES (6, 'Workstation');
/*!40000 ALTER TABLE `device_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT,
    `device_type_id` int(11)      NOT NULL DEFAULT 1,
    `ipv_4`          varchar(255) NOT NULL,
    `ipv_6`          varchar(255) NOT NULL,
    `mac_address`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `device_type_id_fk` (`device_type_id`),
    CONSTRAINT `device_type_id_fk` FOREIGN KEY (`device_type_id`) REFERENCES `device_type` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`mac_address`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device`
    DISABLE KEYS */;
INSERT INTO `device`
VALUES (1, 1, 'N/A', 'N/A', 'N/A');
/*!40000 ALTER TABLE `device`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_meta`
--

DROP TABLE IF EXISTS `device_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_meta`
(
    `id`        int(11)      NOT NULL AUTO_INCREMENT,
    `device_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`   int(11)      NOT NULL DEFAULT 1,
    `key`       varchar(255) NOT NULL,
    `value`     varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `device_id_fk` (`device_id`),
    CONSTRAINT `device_id_fk` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `device_meta`
--

LOCK TABLES `device_meta` WRITE;
/*!40000 ALTER TABLE `device_meta`
    DISABLE KEYS */;
INSERT INTO `device_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `device_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_type`
--

DROP TABLE IF EXISTS `session_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `session_type`
--

LOCK TABLES `session_type` WRITE;
/*!40000 ALTER TABLE `session_type`
    DISABLE KEYS */;
INSERT INTO `session_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `session_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `session_type_id`   int(11) NOT NULL DEFAULT 1,
    `session_id`        varchar(255) NOT NULL DEFAULT 'None',
    `login_time`        TIMESTAMP        DEFAULT 0,
    `logout_time`       TIMESTAMP        DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `session_type_id_fk` (`session_type_id`),
    CONSTRAINT `session_type_id_fk` FOREIGN KEY (`session_type_id`) REFERENCES `session_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session`
    DISABLE KEYS */;
INSERT INTO `session`
VALUES (1, 1, 'N/A', 0, 0);
/*!40000 ALTER TABLE `session`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_meta`
--

DROP TABLE IF EXISTS `session_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `session_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `session_id_fk` (`session_id`),
    CONSTRAINT `session_id_fk` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `session_meta`
--

LOCK TABLES `session_meta` WRITE;
/*!40000 ALTER TABLE `session_meta`
    DISABLE KEYS */;
INSERT INTO `session_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `session_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream_type`
--

DROP TABLE IF EXISTS `stream_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `stream_type`
--

LOCK TABLES `stream_type` WRITE;
/*!40000 ALTER TABLE `stream_type`
    DISABLE KEYS */;
INSERT INTO `stream_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `stream_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream`
--

DROP TABLE IF EXISTS `stream`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream`
(
    `id`              int(11)      NOT NULL AUTO_INCREMENT,
    `stream_type_id`  int(11)      NOT NULL DEFAULT 1,
    `sample_rate`     varchar(255) NOT NULL,
    `sample_size`     int(11)      NOT NULL,
    `sample_signed`   tinyint(1)   NOT NULL,
    `sample_endian`   varchar(255) NOT NULL,
    `sample_encoding` varchar(255) NOT NULL,
    `sample_channels` int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `stream_type_id_fk` (`stream_type_id`),
    CONSTRAINT `stream_type_id_fk` FOREIGN KEY (`stream_type_id`) REFERENCES `stream_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `stream`
--

LOCK TABLES `stream` WRITE;
/*!40000 ALTER TABLE `stream`
    DISABLE KEYS */;
INSERT INTO `stream`
VALUES (1, 1, 'N/A', 0, 0, 'N/A', 'N/A', 0);
/*!40000 ALTER TABLE `stream`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream_meta`
--

DROP TABLE IF EXISTS `stream_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_meta`
(
    `id`        int(11)      NOT NULL AUTO_INCREMENT,
    `stream_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`   int(11)      NOT NULL DEFAULT 1,
    `key`       varchar(255) NOT NULL,
    `value`     varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `stream_id_fk` (`stream_id`),
    CONSTRAINT `stream_id_fk` FOREIGN KEY (`stream_id`) REFERENCES `stream` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `stream_meta`
--

LOCK TABLES `stream_meta` WRITE;
/*!40000 ALTER TABLE `stream_meta`
    DISABLE KEYS */;
INSERT INTO `stream_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `stream_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_type`
--

DROP TABLE IF EXISTS `notification_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `notification_type`
--

LOCK TABLES `notification_type` WRITE;
/*!40000 ALTER TABLE `notification_type`
    DISABLE KEYS */;
INSERT INTO `notification_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `notification_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification`
(
    `id`                   int(11)      NOT NULL AUTO_INCREMENT,
    `notification_type_id` int(11)      NOT NULL DEFAULT 1,
    `time`                 TIMESTAMP             DEFAULT 0,
    `message`              varchar(255) NOT NULL,
    `severity`             varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `notification_type_id_fk` (`notification_type_id`),
    CONSTRAINT `notification_type_id_fk` FOREIGN KEY (`notification_type_id`) REFERENCES `notification_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification`
    DISABLE KEYS */;
INSERT INTO `notification`
VALUES (1, 1, 0, 'N/A', 'N/A');
/*!40000 ALTER TABLE `notification`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_meta`
--

DROP TABLE IF EXISTS `notification_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_meta`
(
    `id`              int(11)      NOT NULL AUTO_INCREMENT,
    `notification_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`         int(11)      NOT NULL DEFAULT 1,
    `key`             varchar(255) NOT NULL,
    `value`           varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `notification_id_fk` (`notification_id`),
    CONSTRAINT `notification_id_fk` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `notification_meta`
--

LOCK TABLES `notification_meta` WRITE;
/*!40000 ALTER TABLE `notification_meta`
    DISABLE KEYS */;
INSERT INTO `notification_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `notification_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key_type`
--

DROP TABLE IF EXISTS `key_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `key_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `key_type`
--

LOCK TABLES `key_type` WRITE;
/*!40000 ALTER TABLE `key_type`
    DISABLE KEYS */;
INSERT INTO `key_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `key_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key`
--

DROP TABLE IF EXISTS `key`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `key`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `key_type_id` int(11) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    KEY `key_type_id_fk` (`key_type_id`),
    CONSTRAINT `key_type_id_fk` FOREIGN KEY (`key_type_id`) REFERENCES `key_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `key`
--

LOCK TABLES `key` WRITE;
/*!40000 ALTER TABLE `key`
    DISABLE KEYS */;
INSERT INTO `key`
VALUES (1, 1);
/*!40000 ALTER TABLE `key`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key_meta`
--

DROP TABLE IF EXISTS `key_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `key_meta`
(
    `id`      int(11)      NOT NULL AUTO_INCREMENT,
    `key_id`  int(11)      NOT NULL DEFAULT 1,
    `rule_id` int(11)      NOT NULL DEFAULT 1,
    `key`     varchar(255) NOT NULL,
    `value`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `key_id_fk` (`key_id`),
    CONSTRAINT `key_id_fk` FOREIGN KEY (`key_id`) REFERENCES `key` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `key_meta`
--

LOCK TABLES `key_meta` WRITE;
/*!40000 ALTER TABLE `key_meta`
    DISABLE KEYS */;
INSERT INTO `key_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `key_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_type`
--

DROP TABLE IF EXISTS `channel_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `channel_type`
--

LOCK TABLES `channel_type` WRITE;
/*!40000 ALTER TABLE `channel_type`
    DISABLE KEYS */;
INSERT INTO `channel_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `channel_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `channel_type_id` int(11) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    KEY `channel_type_id_fk` (`channel_type_id`),
    CONSTRAINT `channel_type_id_fk` FOREIGN KEY (`channel_type_id`) REFERENCES `channel_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel`
    DISABLE KEYS */;
INSERT INTO `channel`
VALUES (1, 1);
/*!40000 ALTER TABLE `channel`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_meta`
--

DROP TABLE IF EXISTS `channel_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `channel_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `channel_id_fk` (`channel_id`),
    CONSTRAINT `channel_id_fk` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `channel_meta`
--

LOCK TABLES `channel_meta` WRITE;
/*!40000 ALTER TABLE `channel_meta`
    DISABLE KEYS */;
INSERT INTO `channel_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `channel_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `circuit_type`
--

DROP TABLE IF EXISTS `circuit_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `circuit_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `circuit_type`
--

LOCK TABLES `circuit_type` WRITE;
/*!40000 ALTER TABLE `circuit_type`
    DISABLE KEYS */;
INSERT INTO `circuit_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `circuit_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `circuit`
--

DROP TABLE IF EXISTS `circuit`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `circuit`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `circuit_type_id` int(11) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    KEY `circuit_type_id_fk` (`circuit_type_id`),
    CONSTRAINT `circuit_type_id_fk` FOREIGN KEY (`circuit_type_id`) REFERENCES `circuit_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `circuit`
--

LOCK TABLES `circuit` WRITE;
/*!40000 ALTER TABLE `circuit`
    DISABLE KEYS */;
INSERT INTO `circuit`
VALUES (1, 1);
/*!40000 ALTER TABLE `circuit`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `circuit_meta`
--

DROP TABLE IF EXISTS `circuit_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `circuit_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `circuit_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `circuit_id_fk` (`circuit_id`),
    CONSTRAINT `circuit_id_fk` FOREIGN KEY (`circuit_id`) REFERENCES `circuit` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `circuit_meta`
--

LOCK TABLES `circuit_meta` WRITE;
/*!40000 ALTER TABLE `circuit_meta`
    DISABLE KEYS */;
INSERT INTO `circuit_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `circuit_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partition_type`
--

DROP TABLE IF EXISTS `partition_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partition_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `partition_type`
--

LOCK TABLES `partition_type` WRITE;
/*!40000 ALTER TABLE `partition_type`
    DISABLE KEYS */;
INSERT INTO `partition_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `partition_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partition`
--

DROP TABLE IF EXISTS `partition`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partition`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `partition_type_id` int(11) NOT NULL DEFAULT 1,
    `name`              varchar(255) NOT NULL,
    `description`       varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `partition_type_id_fk` (`partition_type_id`),
    CONSTRAINT `partition_type_id_fk` FOREIGN KEY (`partition_type_id`) REFERENCES `partition_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `partition`
--

LOCK TABLES `partition` WRITE;
/*!40000 ALTER TABLE `partition`
    DISABLE KEYS */;
INSERT INTO `partition`
VALUES (1, 1, 'None', 'None');
/*!40000 ALTER TABLE `partition`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partition_meta`
--

DROP TABLE IF EXISTS `partition_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partition_meta`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT,
    `partition_id`  int(11)      NOT NULL DEFAULT 1,
    `rule_id`       int(11)      NOT NULL DEFAULT 1,
    `key`           varchar(255) NOT NULL,
    `value`         varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `partition_id_fk` (`partition_id`),
    CONSTRAINT `partition_id_fk` FOREIGN KEY (`partition_id`) REFERENCES `partition` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `partition_meta`
--

LOCK TABLES `partition_meta` WRITE;
/*!40000 ALTER TABLE `partition_meta`
    DISABLE KEYS */;
INSERT INTO `partition_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `partition_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_type`
--

DROP TABLE IF EXISTS `unit_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `unit_type`
--

LOCK TABLES `unit_type` WRITE;
/*!40000 ALTER TABLE `unit_type`
    DISABLE KEYS */;
INSERT INTO `unit_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `unit_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `unit_type_id` int(11) NOT NULL DEFAULT 1,
    `name`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `unit_type_id_fk` (`unit_type_id`),
    CONSTRAINT `unit_type_id_fk` FOREIGN KEY (`unit_type_id`) REFERENCES `unit_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit`
    DISABLE KEYS */;
INSERT INTO `unit`
VALUES (1, 1, 'None');
/*!40000 ALTER TABLE `unit`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_meta`
--

DROP TABLE IF EXISTS `unit_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `unit_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `unit_id_fk` (`unit_id`),
    CONSTRAINT `unit_id_fk` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `unit_meta`
--

LOCK TABLES `unit_meta` WRITE;
/*!40000 ALTER TABLE `unit_meta`
    DISABLE KEYS */;
INSERT INTO `unit_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `unit_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `button_type`
--

DROP TABLE IF EXISTS `button_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `button_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `button_type`
--

LOCK TABLES `button_type` WRITE;
/*!40000 ALTER TABLE `button_type`
    DISABLE KEYS */;
INSERT INTO `button_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `button_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `button`
--

DROP TABLE IF EXISTS `button`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `button`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `button_type_id` int(11) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    KEY `button_type_id_fk` (`button_type_id`),
    CONSTRAINT `button_type_id_fk` FOREIGN KEY (`button_type_id`) REFERENCES `button_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `button`
--

LOCK TABLES `button` WRITE;
/*!40000 ALTER TABLE `button`
    DISABLE KEYS */;
INSERT INTO `button`
VALUES (1, 1);
/*!40000 ALTER TABLE `button`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `button_meta`
--

DROP TABLE IF EXISTS `button_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `button_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `button_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `button_id_fk` (`button_id`),
    CONSTRAINT `button_id_fk` FOREIGN KEY (`button_id`) REFERENCES `button` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `button_meta`
--

LOCK TABLES `button_meta` WRITE;
/*!40000 ALTER TABLE `button_meta`
    DISABLE KEYS */;
INSERT INTO `button_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `button_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_type`
--

DROP TABLE IF EXISTS `profile_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `profile_type`
--

LOCK TABLES `profile_type` WRITE;
/*!40000 ALTER TABLE `profile_type`
    DISABLE KEYS */;
INSERT INTO `profile_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `profile_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `profile_type_id` int(11) NOT NULL DEFAULT 1,
    `name`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `profile_type_id_fk` (`profile_type_id`),
    CONSTRAINT `profile_type_id_fk` FOREIGN KEY (`profile_type_id`) REFERENCES `profile_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile`
    DISABLE KEYS */;
INSERT INTO `profile`
VALUES (1, 1, 'None');
/*!40000 ALTER TABLE `profile`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_meta`
--

DROP TABLE IF EXISTS `profile_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `profile_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `profile_id_fk` (`profile_id`),
    CONSTRAINT `profile_id_fk` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `profile_meta`
--

LOCK TABLES `profile_meta` WRITE;
/*!40000 ALTER TABLE `profile_meta`
    DISABLE KEYS */;
INSERT INTO `profile_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `profile_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_type`
--

DROP TABLE IF EXISTS `permission_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_type`
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT 'None',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PrimeNdx` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `permission_type`
--

LOCK TABLES `permission_type` WRITE;
/*!40000 ALTER TABLE `permission_type`
    DISABLE KEYS */;
INSERT INTO `permission_type`
VALUES (1, 'None');
/*!40000 ALTER TABLE `permission_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `permission_type_id` int(11) NOT NULL DEFAULT 1,
    `name`          varchar(255) NOT NULL,
    `value`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `permission_type_id_fk` (`permission_type_id`),
    CONSTRAINT `permission_type_id_fk` FOREIGN KEY (`permission_type_id`) REFERENCES `permission_type` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission`
    DISABLE KEYS */;
INSERT INTO `permission`
VALUES (1, 1, 'None', 'None');
/*!40000 ALTER TABLE `permission`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_meta`
--

DROP TABLE IF EXISTS `permission_meta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_meta`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `permission_id` int(11)      NOT NULL DEFAULT 1,
    `rule_id`    int(11)      NOT NULL DEFAULT 1,
    `key`        varchar(255) NOT NULL,
    `value`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `permission_id_fk` (`permission_id`),
    CONSTRAINT `permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON UPDATE CASCADE,
    UNIQUE KEY `PrimeNdx` (`id`, `key`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Records of `permission_meta`
--

LOCK TABLES `permission_meta` WRITE;
/*!40000 ALTER TABLE `permission_meta`
    DISABLE KEYS */;
INSERT INTO `permission_meta`
VALUES (1, 1, 1, 'N/A', 'N/A');
/*!40000 ALTER TABLE `permission_meta`
    ENABLE KEYS */;
UNLOCK TABLES;

COMMIT;
