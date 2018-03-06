drop database if exists kona;
create database kona;
use kona;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --------------------------------------------------------------------------

CREATE TABLE `_changelog` (
  `ID` decimal(20,0) NOT NULL,
  `APPLIED_AT` varchar(25) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,

  PRIMARY KEY (`ID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  -- `stripe_uid` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `verified` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__account_slug` (`slug`),

  UNIQUE KEY `ux_kona__account_uid` (`uid`),

  UNIQUE KEY `ux_kona__account_owner` (`owner_id`),

  FULLTEXT `ft_kona__account` (`uid`,`name`,`slug`),

  CONSTRAINT `fk_kona__account_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__api_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `version_id` bigint(20) unsigned NOT NULL,
  `app_client_id` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `end_point` varchar(255) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `method_name` varchar(255) NOT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__api_log_uid` (`uid`),

  KEY `ix_kona__api_log_owner` (`owner_id`),

  KEY `ix_kona__api_log_app` (`app_id`),

  KEY `ix_kona__api_log_version` (`version_id`),

  KEY `ix_kona__api_log_app_client` (`app_client_id`),

  KEY `ix_kona__api_log_user` (`user_id`),

  SPATIAL `ix_kona__api_log_coords` (coords),

  FULLTEXT `ft_kona__api_log` (`uid`,`app_client_id`,`access_token`, `end_point`),

  CONSTRAINT `fk_kona__api_log_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__api_log_app_client` FOREIGN KEY (`app_client_id`) 
        REFERENCES `kona__app_creds` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__api_log_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__api_log_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__api_log_version` FOREIGN KEY (`version_id`) 
        REFERENCES `kona__api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__api_version` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `published_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__api_version_name` (`name`),

  FULLTEXT `ft_kona__api_version` (`name`,`description`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__app` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `logo_id` bigint(20) unsigned DEFAULT NULL,
  `logo_url_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `app_url` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_url` varchar(255) DEFAULT NULL,
  `privacy_url` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_slug` (`slug`),

  UNIQUE KEY `ux_kona__app_uid` (`uid`),

  KEY `ix_kona__app_user` (`user_id`),

  KEY `ix_kona__app_logo` (`logo_id`),

  FULLTEXT `ft_kona__app` (`name`,`slug`,`description`,`company_name`),

  CONSTRAINT `fk_kona__app_logo` FOREIGN KEY (`logo_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__app_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__app_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned default NULL, -- set null for global
  `parent_id` bigint(20) unsigned default NULL,
  `env` varchar(255) DEFAULT NULL, -- set null for global
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `override_global` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_config_uid` (`uid`),

  UNIQUE KEY `ux_kona__app_config_env_name` (`app_id`,`env`,`name`),

  KEY `ix_kona__app_config_app` (`app_id`),

  KEY `ix_kona__app_config_parent` (`parent_id`),

  FULLTEXT `ft_kona__app_config` (`env`,`name`,`value`),

  CONSTRAINT `fk_kona__app_config_parent` FOREIGN KEY (`parent_id`)
        REFERENCES `kona__app_config` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__app_config_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__app_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) unsigned NOT NULL,
  `api_version_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `scope` varchar(255) NOT NULL DEFAULT 'read,write',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `access_token_timeout` int(11) unsigned DEFAULT NULL, -- seconds / needs to be integer
  `refresh_token_timeout` int(11) unsigned DEFAULT NULL, -- second / needs to be integer
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_creds_name` (`app_id`,`name`),

  UNIQUE KEY `ux_kona__app_creds_client_id` (`client_id`),

  UNIQUE KEY `ux_kona__app_creds_client_secret` (`client_secret`),

  KEY `ix_kona__app_creds_api_version` (`api_version_id`),

  CONSTRAINT `fk_kona__app_creds_api_version` FOREIGN KEY (`api_version_id`) 
        REFERENCES `kona__api_version` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__app_creds_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__policy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `version` int(11) unsigned NOT NULL,
  `active` tinyint(1) unsigned NOT NULL DEFAULT '0', -- currently active version
  `published_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__policy_uid` (`uid`),

  UNIQUE KEY `ux_kona__policy_type_version` (`type`, `version`),

  FULLTEXT `ft_kona__policy` (`uid`,`content`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

-- external app users that may or may not be direct users of our app/service

CREATE TABLE `kona__app_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `ref_user_id` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `revoked_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_user_uid` (`uid`),

  UNIQUE KEY `ux_kona__app_user_app_user` (`app_id`,`user_id`),

  UNIQUE KEY `ux_kona__app_user_app_ref_user_id` (`app_id`,`ref_user_id`),

  KEY `ix_kona__user_app_user` (`user_id`),

  KEY `ix_kona__user_app_token` (`token_id`),

  FULLTEXT `ft_kona__app_user` (`uid`,`ref_user_id`,`email`,`mobile_number`,`first_name`,`last_name`,`display_name`),

  SPATIAL `ix_kona__user_app_coords` (coords),

  CONSTRAINT `fk_kona__app_user_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__app_user_token` FOREIGN KEY (`token_id`) 
        REFERENCES `kona__token` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__app_user_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__app_webhook` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `events` varchar(4000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_webhook_uid` (`uid`),

  UNIQUE KEY `ux_kona__app_webhook_app_slug` (`app_id`,`slug`),

  KEY `ix_kona__app_webhook_app` (`app_id`),

  FULLTEXT `ft_kona__app_webhook` (`uid`,`name`,`slug`,`url`,`events`),

  CONSTRAINT `fk_kona__app_webhook_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `code` varchar(255) NOT NULL,
  `valid` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `use_count` int(11) unsigned NOT NULL DEFAULT '0',
  `max_use_count` int(11) unsigned default NULL, -- null means no max 
  `expiration_date` datetime(6) DEFAULT NULL,
  `confirmed_date` datetime(6) DEFAULT NULL,
  `last_accessed_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__auth_code_code` (`code`),

  KEY `ix_kona__auth_code_user` (`user_id`),

  FULLTEXT `ft_kona__auth_code` (`code`),

  CONSTRAINT `fk_kona__auth_code_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__file` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `access` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) NOT NULL,
  `size` bigint(20) unsigned NOT NULL,
  `hidden` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `temp_file` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `src_hostname` varchar(255) DEFAULT NULL,
  `src_filename` varchar(255) DEFAULT NULL,
  `local_path` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  `upload_time` bigint(20) unsigned DEFAULT NULL,
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE `ux_kona__file_uid` (`uid`),

  UNIQUE `ux_kona__file_local_path` (`local_path`),

  UNIQUE `ux_kona__file_url_path` (`url_path`),

  KEY `ix_kona__file_parent` (`parent_id`),

  KEY `ix_kona__file_user` (`user_id`),

  KEY `ix_kona__file_account` (`account_id`),

  KEY `ix_kona__file_token` (`token_id`),

  FULLTEXT `ft_kona__file` (uid,name,content_type,src_hostname,local_path,url_path),

  CONSTRAINT `fk_kona__file_parent` FOREIGN KEY (`parent_id`)
        REFERENCES `kona__file` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__file_token` FOREIGN KEY (`token_id`)
        REFERENCES `kona__token` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__file_account` FOREIGN KEY (`account_id`)
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__file_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__redirect` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `short_url_id` bigint(20) unsigned NOT NULL,
  `request_url` varchar(2000) NOT NULL,
  `redirect_url` varchar(2000) NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `user_agent` varchar(512) NOT NULL,
  `referer` varchar(2000) DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `requested_date` datetime(6) NOT NULL,
  `redirected_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE `ux_kona__redirect_uid` (`uid`),

  KEY `ix_kona__redirect_short_url` (`short_url_id`),

  FULLTEXT `ft_kona__redirect` (uid,request_url,redirect_url),

  CONSTRAINT `fk_kona__redirect_short_url` FOREIGN KEY (`short_url_id`)
        REFERENCES `kona__short_url` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL, -- broad category for this device
  `parent_id` bigint(20) unsigned default NULL, -- is this device part of another device
  `advertiser_id` varchar(255) default NULL,
  `limit_ad_tracking_enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `os_name` varchar(255) default NULL,
  `os_version` varchar(255) default NULL,
  `ble_mac_address` varchar(255) default NULL,
  `lan_mac_address` varchar(255) default NULL,
  `pnp_id` varchar(255) default NULL,
  `vendor` varchar(255) default NULL,
  `manufacturer` varchar(255) default NULL,
  `model` varchar(255) default NULL,
  `serial_no` varchar(255) default NULL,
  `device_uuid` varchar(255) default NULL, -- internal device uuid
  `hardware_version` varchar(255) default NULL,
  `firmware_version` varchar(255) default NULL,
  `capabilities` varchar(2000) default NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `deleted_date` datetime DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__device_uid` (`uid`),

  UNIQUE KEY `ux_kona__device_uuid` (`device_uuid`),

  UNIQUE KEY `ux_kona__device_advertiser_id` (`advertiser_id`),

  FULLTEXT `ft_kona__device` (uid,type,advertiser_id,os_name,ble_mac_address,lan_mac_address,pnp_id,vendor,manufacturer,model,serial_no,device_uuid,capabilities)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--
-- NOTE: do not include uid here since it will create confusion with the device uid.
--
CREATE TABLE `kona__user_device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL, 
  `device_id` bigint(20) unsigned NOT NULL, 
  `name` varchar(255) default NULL,
  `slug` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__user_device_user_device` (`user_id`, `device_id`),

  UNIQUE KEY `ux_kona__user_device_user_slug` (`user_id`, `slug`),

  KEY `ix_kona__user_device_device` (`device_id`),

  CONSTRAINT `fk_kona__user_device_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_device_device` FOREIGN KEY (`device_id`) 
        REFERENCES `kona__device` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__registration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `referred_by_id` bigint(20) unsigned DEFAULT NULL,
  `app_client_id` varchar(255) default NULL, -- app client_id
  `username` varchar(255) default NULL,
  `hostname` varchar(255) default NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `os_name` varchar(255) default NULL,
  `os_version` varchar(255) default NULL,
  `app_version` varchar(255) DEFAULT NULL,
  `app_build` varchar(255) DEFAULT NULL,
  `signup_time` bigint(20) unsigned DEFAULT NULL, -- time (ms) as reported by client
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `email_verified` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `email_pending` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `mobile_verified` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `mobile_pending` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `reminded_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_kona__registration_uid` (`uid`),

  UNIQUE `ux_kona__registration_user` (`user_id`),

  KEY `ix_kona__registration_device` (`device_id`),

  KEY `ix_kona__registration_app` (`app_id`),

  KEY `ix_kona__registration_account` (`account_id`),

  KEY `ix_kona__registration_campaign` (`campaign_id`),

  KEY `ix_kona__registration_referred_by` (`referred_by_id`),

    FULLTEXT `ft_kona__registration` (uid,username,hostname,user_agent,os_name),

  SPATIAL `ix_kona__registration_coords` (coords),

  CONSTRAINT `fk_kona__registration_device` FOREIGN KEY (`device_id`) 
        REFERENCES `kona__device` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__registration_referred_by` FOREIGN KEY (`referred_by_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__registration_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`),

  CONSTRAINT `fk_kona__registration_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__registration_account` FOREIGN KEY (`account_id`)
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__registration_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__remote_service` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `authorize_uri` varchar(1024) DEFAULT NULL,
  `token_uri` varchar(1024) DEFAULT NULL,
  `scope` varchar(1024) DEFAULT NULL,
  `client_key` varchar(1024) DEFAULT NULL,
  `client_secret` varchar(1024) DEFAULT NULL,
  `redirect_uri` varchar(1024) DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__remote_service_uid` (`uid`),

  UNIQUE KEY `ux_kona__remote_service_slug` (`slug`),

  FULLTEXT `ft_kona__remote_service` (uid,name,client_key,client_secret)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__remote_service_app_creds` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `uid` varchar(255) NOT NULL,
--  `remote_service_id` bigint(20) unsigned NOT NULL,
--
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__remote_service_app_creds_uid` (`uid`),
--
--  KEY `ix_kona__remote_service_app_creds_remote_service` (`remote_service_id`),
--
--  CONSTRAINT `fk_kona__remote_service_app_creds_remote_service` FOREIGN KEY (`remote_service_id`)
--        REFERENCES `kona__remote_service` (`id`) ON DELETE CASCADE
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__remote_service_user_creds` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `remote_service_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `remote_service_user_id` varchar(255) DEFAULT NULL,
  `remote_service_screen_name` varchar(255) DEFAULT NULL,
  `auth_type` enum('oauth','oauth2','credentials') DEFAULT NULL,
  `access_token` varchar(1024) DEFAULT NULL,
  `refresh_token` varchar(1024) DEFAULT NULL,
  `token_secret` varchar(1024) DEFAULT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  `connected_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__remote_service_user_creds_uid` (`uid`),

  UNIQUE KEY `ux_kona__remote_service_user_creds_account_slug` (`account_id`,`slug`),

  KEY `ix_kona__remote_service_user_creds_user` (`user_id`),

  KEY `ix_kona__remote_service_user_creds_remote_service` (`remote_service_id`),

  KEY `ix_kona__remote_service_user_creds_remote_service_user` (`remote_service_user_id`),

  KEY `ix_kona__remote_service_user_creds_remote_service_screen_name` (`remote_service_screen_name`),

  FULLTEXT `ft_kona__remote_service_user_creds` (uid,name,remote_service_user_id,remote_service_screen_name),

  CONSTRAINT `fk_kona__remote_service_user_creds_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__remote_service_user_creds_remote_service` FOREIGN KEY (`remote_service_id`)
        REFERENCES `kona__remote_service` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__remote_service_user_creds_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(4000) NOT NULL,
  `override_global` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__setting_uid` (`uid`),

  UNIQUE KEY `ux_kona__setting_user_name` (`user_id`,`name`),

  UNIQUE KEY `ux_kona__setting_account_name` (`account_id`,`name`),

  KEY `ix_kona__setting_user` (`user_id`),

  KEY `ix_kona__setting_account` (`account_id`),

  KEY `ix_kona__setting_parent` (`parent_id`),

  KEY `ix_kona__setting_name` (`name`),

  FULLTEXT `ft_kona__setting` (uid,name,value),

  CONSTRAINT `fk_kona__setting_parent` FOREIGN KEY (`parent_id`)
        REFERENCES `kona__setting` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__setting_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__setting_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__short_url` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `domain` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `short_url` varchar(255) NOT NULL,
  `long_url` varchar(8000) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `script` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `expired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__short_url_short_url` (`short_url`),

  UNIQUE KEY `ux_kona__short_url_path` (`path`),

  UNIQUE KEY `ux_kona__short_url_uid` (`uid`),

  KEY `ix_kona__short_url_user` (`user_id`),

  KEY `ix_kona__short_url_campaign` (`campaign_id`),

  KEY `ix_kona__short_url_long_url` (`long_url`(255)),

  FULLTEXT `ft_kona__short_url` (`uid`,`domain`,`path`,`short_url`,`long_url`,`description`),

  CONSTRAINT `fk_kona__short_url_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned default NULL,
  `app_client_id` varchar(255) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `username` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `app_version` varchar(255) DEFAULT NULL,
  `app_build` varchar(255) DEFAULT NULL,
  `active` tinyint(1) unsigned NOT NULL DEFAULT '0', -- token currently being used
  `approved` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `access_count` bigint(20) unsigned DEFAULT NULL,
  `login_date` datetime(6) NOT NULL,
  `last_login_date` datetime(6) DEFAULT NULL,
  `logout_date` datetime(6) DEFAULT NULL,
  `access_expiration_date` datetime(6) DEFAULT NULL,
  `refresh_expiration_date` datetime(6) DEFAULT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__token_access_token` (`access_token`),

  UNIQUE KEY `ux_kona__token_refresh_token` (`refresh_token`),

  KEY `ix_kona__token_app_client` (`app_client_id`),

  KEY `ix_kona__token_app` (`app_id`),

  KEY `ix_kona__token_device` (`device_id`),

  KEY `ix_kona__token_user` (`user_id`),

  KEY `ix_kona__token_hostname` (`hostname`),

  FULLTEXT `ft_kona__token` (`app_client_id`,`access_token`,`refresh_token`,`username`,`user_agent`),

  SPATIAL `ix_kona__token_coords` (coords),

  CONSTRAINT `fk_kona__token_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`),

  CONSTRAINT `fk_kona__token_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__device` (`id`),

  CONSTRAINT `fk_kona__token_app_client` FOREIGN KEY (`app_client_id`) 
        REFERENCES `kona__app_creds` (`client_id`),

  CONSTRAINT `fk_kona__token_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__token_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) NOT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__token_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__auth_role_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_priv` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__auth_priv_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_role_priv` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `priv_id` bigint(20) unsigned NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ,
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__auth_role_priv_uid` (`uid`),

  UNIQUE KEY `ux_kona__auth_role_priv` (`role_id`, `priv_id`),

  CONSTRAINT `fk_kona__auth_role_priv_role` FOREIGN KEY (`role_id`)
        REFERENCES `kona__auth_role` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__auth_role_priv_priv` FOREIGN KEY (`priv_id`)
        REFERENCES `kona__auth_priv` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `position_id` bigint(20) unsigned DEFAULT NULL,
  `photo_id` bigint(20) unsigned DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL, 
  `thumbnail_url` varchar(255) DEFAULT NULL, 
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `presence` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__user_username` (`username`),

  UNIQUE KEY `ux_kona__user_uid` (`uid`),

  UNIQUE KEY `ux_kona__user_email` (`email`),

  UNIQUE KEY `ux_kona__user_mobile_number` (`mobile_number`),

  KEY `ix_kona__user_parent` (`parent_id`),

  KEY `ix_kona__user_position` (`position_id`),

  KEY `ix_kona__user_photo` (`photo_id`),

  KEY `ix_kona__user_type` (`type_id`),


  KEY `ix_kona__user_account` (`account_id`),

  FULLTEXT `ft_kona__user` (`uid`,`username`,`email`,`mobile_number`,`first_name`,`last_name`,`display_name`),


  CONSTRAINT `fk_kona__user_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_position` FOREIGN KEY (`position_id`) 
        REFERENCES `kona__position` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_photo` FOREIGN KEY (`photo_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_type` FOREIGN KEY (`type_id`)
        REFERENCES `kona__user_type` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ,
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__user_role_uid` (`uid`),

  UNIQUE KEY `ux_kona__user_role` (`user_id`, `role_id`),

  CONSTRAINT `fk_kona__user_role_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_role_role` FOREIGN KEY (`role_id`)
        REFERENCES `kona__auth_role` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__user_auth` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `voice_sample_id` bigint(20) unsigned default NULL,
  `fingerprint_id` bigint(20) unsigned default NULL,
  `facial_scan_id` bigint(20) unsigned default NULL,
  `password` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `question1` varchar(255) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `question2` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ,
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__user_auth_user` (`user_id`),

  KEY `ix_kona__user_voice_sample` (`voice_sample_id`),

  KEY `ix_kona__user_fingerprint` (`fingerprint_id`),

  KEY `ix_kona__user_facial_scan` (`facial_scan_id`),

  CONSTRAINT `fk_kona__user_auth_voice_sample` FOREIGN KEY (`voice_sample_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_auth_fingerprint` FOREIGN KEY (`fingerprint_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_auth_facial_scan` FOREIGN KEY (`facial_scan_id`)
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_auth_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__media` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned default NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `file_id` bigint(20) unsigned DEFAULT NULL,
  `thumbnail_id` bigint(20) unsigned DEFAULT NULL,
  `folder_path` varchar(255) DEFAULT NULL, -- similar to s3: flat but can be conceptually nested /folder1/subfolder2
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL, -- absolute url, may be external
  `url_path` varchar(255) DEFAULT NULL, -- local url path
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `thumbnail_url_path` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) default NULL,
  `width` int(11) unsigned DEFAULT NULL,
  `height` int(11) unsigned DEFAULT NULL,
  `size` bigint(20) unsigned DEFAULT NULL,
  `bits_per_pixel` int(11) DEFAULT NULL,
  `frames_per_second` int(11) DEFAULT NULL,
  `duration` bigint(20) unsigned DEFAULT NULL,
  `thumbnail_width` int(11) unsigned DEFAULT NULL,
  `thumbnail_height` int(11) unsigned DEFAULT NULL,
  `thumbnail_size` bigint(20) unsigned DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `resizeable` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `sprite` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `sprite_x_offset` int(11) default NULL,
  `sprite_y_offset` int(11) default NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__media_uid` (`uid`),

  UNIQUE KEY `ux_kona__media_file` (`file_id`),

  UNIQUE KEY `ux_kona__media_thumbnail` (`thumbnail_id`),

  UNIQUE KEY `ux_kona__media_slug` (`account_id`, `folder_path`, `slug`),

  KEY `ux_kona__media_parent` (`parent_id`),

  SPATIAL `ix_kona__media_coords` (coords),

  KEY `ix_kona__media_app` (`app_id`),

  KEY `ix_kona__media_user` (`user_id`),

  KEY `ix_kona__media_account` (`account_id`),

  CONSTRAINT `fk_kona__media_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE CASCADE,

        -- do not cascade since referenced file may need to be removed and added in 2 steps
  CONSTRAINT `fk_kona__media_file` FOREIGN KEY (`file_id`) 
        REFERENCES `kona__file` (`id`) ON DELETE SET NULL,

        -- do not cascade since referenced file may need to be removed and added in 2 steps
  CONSTRAINT `fk_kona__media_thumbnail` FOREIGN KEY (`thumbnail_id`) 
        REFERENCES `kona__file` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__media_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__media_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__media_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__user_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__user_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_code_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL, 
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__auth_code_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__entity_name_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `pattern` varchar(255) DEFAULT NULL,
  `black_listed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `reserved` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__entity_name_uid` (`uid`),

  UNIQUE KEY `ux_kona__entity_name_rule` (`pattern`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__place_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_category_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_category` (`slug`),

  FULLTEXT KEY `ft_kona_place_category` (`uid`, `name`, `slug`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__place` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `added_by_id` bigint(20) unsigned default NULL,
  `category_id` bigint(20) unsigned default NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `photo_id` bigint(20) unsigned DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `borough` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `utc_offset` int(11) DEFAULT NULL,
  `formatted_address` varchar(512) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `social_handles` varchar(2000) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `indoor_floor` int(11) unsigned DEFAULT NULL,
  `coords` geometry NOT NULL,
  `perimeter` longtext DEFAULT NULL, -- JSON list of lat/lng tuples defining perimeter of space
  `encoded_perimeter` varchar(2000) DEFAULT NULL, -- encoded string of the perimeter
  `perimeter_coords` geometry NOT NULL,
  `ref_place_id` varchar(255) DEFAULT NULL,
  `ref_google_url` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_ref_place_id` (`ref_place_id`),

  SPATIAL `ix_kona__place_coords` (coords),

  KEY `ix_kona__place_added_by` (`added_by_id`),

  KEY `ux_kona__place_category` (`category_id`),

  KEY `ix_kona__place_photo` (`photo_id`),

  FULLTEXT KEY `ft_kona_place` (`uid`, `name`, `slug`, `description`),

  CONSTRAINT `fk_kona__place_category` FOREIGN KEY (`category_id`) 
		REFERENCES `kona__place_category` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__place_added_by` FOREIGN KEY (`added_by_id`) 
		REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__place_photo` FOREIGN KEY (`photo_id`) 
		REFERENCES `kona__media` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__place_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `place_id` bigint(20) unsigned NOT NULL,
  `slug` varchar(255) DEFAULT NULL, 
  `tag` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_tag_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_tag` (`place_id`, `slug`),

  KEY `ix_kona_place_tag_place` (`place_id`),

  FULLTEXT KEY `ft_kona_place_tag` (`uid`,`tag`,`slug`),

  CONSTRAINT `fk_kona__place_tag_place` FOREIGN KEY (`place_id`) 
		REFERENCES `kona__place` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

-- a place can have multiple maps
CREATE TABLE `kona__place_map` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `place_id` bigint(20) unsigned NOT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `map` longtext DEFAULT NULL, -- JSON blob describing interior space
  `floor` int(11) unsigned DEFAULT NULL, -- indoor floor associated with map
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_map_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_map` (`place_id`, `slug`),

  KEY `ix_kona_place_map_place` (`place_id`),

  CONSTRAINT `fk_kona__place_map_place` FOREIGN KEY (`place_id`)
		REFERENCES `kona__place` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned DEFAULT NULL,
  `place_id` bigint(20) unsigned DEFAULT NULL,
  `dwell_time` bigint(20) unsigned DEFAULT NULL, -- time (ms) a person remains in this position
  `sample_no` bigint(20) unsigned DEFAULT NULL,
  `battery` double DEFAULT NULL,
  `charging` tinyint(1) unsigned DEFAULT null,
  `network` varchar(255) DEFAULT NULL, -- wifi|cell
  `source` varchar(255) default NULL, -- gps|access-point|ip-address|beacon|mixed (location_method)
  `background` tinyint(1) unsigned DEFAULT null,
  `carrier` varchar(255) default NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `indoor_floor` int(11) unsigned DEFAULT NULL,
  `horizontal_accuracy` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `altitude_accuracy` double DEFAULT NULL,
  `heading` double DEFAULT NULL,
  `speed` double DEFAULT NULL,
  `position_date` datetime DEFAULT NULL, -- date of actual position
  `captured_date` datetime DEFAULT NULL, -- date captured on device
  `ipv4` varchar(255) DEFAULT NULL,
  `ipv6` varchar(255) DEFAULT NULL,
  `wifi_ssid` varchar(255) DEFAULT NULL,
  `wifi_bssid` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_message` varchar(255) DEFAULT NULL,
  `error_date` datetime DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__position_uid` (`uid`),

  SPATIAL `ix_kona__position_coords` (coords),

  KEY `ix_kona__position_app` (`app_id`),

  KEY `ix_kona__position_device` (`device_id`),

  KEY `ix_kona__position_place` (`place_id`),

  KEY `ix_kona__position_user` (`user_id`),

  KEY `ix_kona__position_position_date` (`position_date`),

  KEY `ix_kona__position_dwell_time` (`dwell_time`),

  KEY `ix_kona__position_horizontal_accuracy` (`horizontal_accuracy`),

  CONSTRAINT `fk_kona__position_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__position_device` FOREIGN KEY (`device_id`) 
        REFERENCES `kona__device` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__position_place` FOREIGN KEY (`place_id`) 
        REFERENCES `kona__place` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__position_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__notification_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__notification_channel_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `event` text default NULL,
  `event_date` datetime(6) default NULL,
  `last_viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_kona__notification_uid` (`uid`),

  KEY `ix_kona__notification_user` (`user_id`),

  CONSTRAINT `fk_kona__notification_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__notification_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notification_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `code` varchar(255) NOT NULL,
  `delivered_date` datetime(6) default NULL,
  `viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_kona__notification_delivery_code` (`code`),

  KEY `ux_kona__notification_delivery_notification` (`notification_id`),

  KEY `ix_kona__notification_delivery_channel` (`channel_id`),

  CONSTRAINT `fk_kona__notification_delivery_channel` FOREIGN KEY (`channel_id`)
        REFERENCES `kona__notification_channel` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__notification_delivery_notification` FOREIGN KEY (`notification_id`)
        REFERENCES `kona__notification` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__support_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__support_message_uid` (`uid`),

  KEY `ix_kona__support_message_user` (`user_id`),

  KEY `ix_kona__support_message_app` (`app_id`),

  CONSTRAINT `fk_kona__support_message_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__support_message_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL 

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__sms` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `to_user_id` bigint(20) unsigned DEFAULT NULL,
  `to_number` varchar(255) NOT NULL,
  `from_number` varchar(255) NOT NULL,
  `message` varchar(4000) NOT NULL,
  `media_urls` varchar(4000) DEFAULT NULL,
  `message_sid` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_message` varchar(2000) DEFAULT NULL,
  `failed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `delivered` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `opted_out` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `click_count` int(11) NOT NULL DEFAULT '0',
  `sent_date` datetime(6) NOT NULL,
  `viewed_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__sms_uid` (`uid`),

  UNIQUE KEY `ix_kona__sms_message_sid` (`message_sid`),

  UNIQUE KEY `ix_kona__sms_campaign_channel_to` (`campaign_id`,`campaign_channel_id`,`to_number`),

  KEY `ix_kona__sms_campaign` (`campaign_id`),

  KEY `ix_kona__sms_to_user` (`to_user_id`),

  KEY `ix_kona__sms_to_number` (`to_number`),

  KEY `ix_kona__sms_campaign_channel` (`campaign_channel_id`),

  CONSTRAINT `fk_kona__sms_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__sms_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__sms_to_user` FOREIGN KEY (`to_user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------

CREATE TABLE `kona__email` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `group_id` bigint(20) unsigned DEFAULT NULL,
  `to_address_id` bigint(20) unsigned DEFAULT NULL,
  `content_id` bigint(20) unsigned DEFAULT NULL,
  `ses_id` varchar(255) DEFAULT NULL,
  `from_address` varchar(255) NOT NULL,
  `to_address` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `failed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `delivered` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `bounced` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `complained` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `opted_out` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `open_count` int(11) NOT NULL DEFAULT '0',
  `click_count` int(11) NOT NULL DEFAULT '0',
  `print_count` int(11) NOT NULL DEFAULT '0',
  `forward_count` int(11) NOT NULL DEFAULT '0',
  `sent_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__email_uid` (`uid`),

  UNIQUE KEY `ix_kona__email_ses_id` (`ses_id`),

  UNIQUE KEY `ix_kona__email_campaign_channel_to` (`campaign_id`,`campaign_channel_id`,`to_address_id`),

  KEY `ix_kona__email_owner` (`owner_id`),

  KEY `ix_kona__email_campaign` (`campaign_id`),

  KEY `ix_kona__email_group` (`group_id`),

  KEY `ix_kona__email_to_address` (`to_address_id`),

  KEY `ix_kona__email_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__email_content` (`content_id`),

  CONSTRAINT `fk_kona__email_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__email_content` FOREIGN KEY (`content_id`)
        REFERENCES `kona__email_content` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_campaign_channel` FOREIGN KEY (`campaign_channel_id`) 
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_group` FOREIGN KEY (`group_id`) 
        REFERENCES `kona__email_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_to_address` FOREIGN KEY (`to_address_id`) 
        REFERENCES `kona__email_address` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mobile_number` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `birth_year` varchar(255) default NULL,
  `company` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `extra` varchar(255) default NULL,
  `street1` varchar(255) default NULL,
  `street2` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `state` varchar(255) default NULL,
  `postal_code` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `source` varchar(255) DEFAULT NULL,
  `scrubbed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `confirmed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `opted_in_date` datetime(6) DEFAULT NULL,
  `opted_out_date` datetime(6) DEFAULT NULL,
  `bounced_date` datetime(6) DEFAULT NULL,
  `complained_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_address_uid` (`uid`),

  UNIQUE KEY `ux_kona__email_address_email` (`email`),

  KEY `ix_kona__email_address_user` (`user_id`),

  CONSTRAINT `fk_kona__email_address_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_content` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL, -- owner of the content, not who the message was sent to
  `text` longtext DEFAULT NULL,
  `html` longtext DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_content_uid` (`uid`),

  KEY `ix_kona__email_content_owner` (`owner_id`),

  CONSTRAINT `fk_kona__email_content_owner` FOREIGN KEY (`owner_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_attachment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `content_id` bigint(20) unsigned NOT NULL,
  `file_id` bigint(20) unsigned NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_attachment_uid` (`uid`),

  KEY `ix_kona__email_attachment_content` (`content_id`),

  KEY `ix_kona__email_attachment_file` (`file_id`),

  CONSTRAINT `fk_kona__email_attachment_content` FOREIGN KEY (`content_id`)
        REFERENCES `kona__email_content` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__email_attachment_file` FOREIGN KEY (`file_id`)
        REFERENCES `kona__file` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `email_id` bigint(20) unsigned NOT NULL,
  `target` varchar(2000) DEFAULT NULL,
  `error` varchar(2000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  KEY `ix_kona__email_event_type` (`type_id`),

  KEY `ix_kona__email_event_email` (`email_id`),

  CONSTRAINT `fk_kona__email_event_email` FOREIGN KEY (`email_id`) 
        REFERENCES `kona__email` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__email_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `kona__email_event_type` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_group_uid` (`uid`),

  UNIQUE KEY `ux_kona__email_group_slug` (`slug`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__email_group_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `address_id` bigint(20) unsigned NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ix_kona__email_group_address_uid` (`uid`),

  UNIQUE KEY `ix_kona__email_group_address_group_address` (`group_id`,`address_id`),

  KEY `fk_kona__email_group_address_address` (`address_id`),

  CONSTRAINT `fk_kona__email_group_address_address` FOREIGN KEY (`address_id`) 
        REFERENCES `kona__email_address` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__email_group_address_group` FOREIGN KEY (`group_id`) 
        REFERENCES `kona__email_group` (`id`) ON DELETE CASCADE 

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------


CREATE TABLE `kona__push_device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `push_provider_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned NOT NULL,
  `push_platform` varchar(255) NOT NULL, -- should match value in push
  `push_token` varchar(1024) DEFAULT NULL,
  `push_endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__push_device_uid` (`uid`),

  -- different users using same app on same device
  UNIQUE KEY `ux_kona__push_device` (`app_id`,`user_id`,`device_id`,`sandbox`),

  UNIQUE KEY `ux_kona__push_device_push_token` (`push_token`(255)),

  KEY `ix_kona__push_device_app` (`app_id`),

  KEY `ix_kona__push_device_user` (`user_id`),

  KEY `ix_kona__push_device_device` (`device_id`),

  CONSTRAINT `fk_kona__push_device_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__device` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device_push_provider` FOREIGN KEY (`push_provider_id`)
        REFERENCES `kona__push_provider` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__push_provider` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `push_platform` varchar(255) NOT NULL,
  `push_server_key` text DEFAULT NULL,
  `push_server_secret` text DEFAULT NULL,
  `push_endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__push_provider_uid` (`uid`),

  UNIQUE KEY `ux_kona__push_provider` (`app_id`,`push_platform`,`sandbox`),

  CONSTRAINT `fk_kona__push_provider_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__push` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `sandbox` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `provider_message_id` varchar(255) default NULL, -- unique identifier from provider to track this message
  `title` varchar(255) DEFAULT NULL,
  `message` varchar(2000) NOT NULL,
  `image_url` varchar(512) DEFAULT NULL,
  `action_url` varchar(2000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_message` varchar(2000) DEFAULT NULL,
  `failed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `delivered` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `opted_out` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `viewed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `sent_date` datetime(6) NOT NULL,
  `delivered_date` datetime(6) DEFAULT NULL,
  `viewed_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),


  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__push_uid` (`uid`),
  UNIQUE KEY `ux_kona__push_provider_message_id` (`provider_message_id`),

  KEY `ix_kona__push_app` (`app_id`),

  KEY `ix_kona__push_user` (`user_id`),

  KEY `ix_kona__push_device` (`device_id`),

  KEY `ix_kona__push_campaign` (`campaign_id`),

  KEY `ix_kona__push_campaign_channel` (`campaign_channel_id`),

  CONSTRAINT `fk_kona__push_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__push_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__push_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__push_device` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `circle_id` bigint(20) unsigned DEFAULT NULL,
  `status_id` bigint(20) unsigned NOT NULL,
  `friendship_requested` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__friendship_uid` (`uid`),

  UNIQUE KEY `ux_kona__friendship_user_friend` (`user_id`,`friend_id`),

  KEY `ix_kona__friendship_user` (`user_id`),

  KEY `ix_kona__friendship_friend` (`friend_id`),

  KEY `ix_kona__friendship_circle` (`circle_id`),

  KEY `ix_kona__friendship_status` (`status_id`),

  CONSTRAINT `fk_kona__friendship_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_status` FOREIGN KEY (`status_id`) 
        REFERENCES `kona__friendship_status` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__friendship_circle` FOREIGN KEY (`circle_id`) 
        REFERENCES `kona__friendship_circle` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__friendship_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `friendship_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `event` varchar(2000) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  KEY `ux_kona__friendship_event_uid` (`uid`),

  KEY `ix_kona__friendship_event_type` (`type_id`),

  KEY `ix_kona__friendship_event_friendship` (`friendship_id`),

  KEY `ix_kona__friendship_event_user` (`user_id`),

  KEY `ix_kona__friendship_event_friend` (`friend_id`),

  CONSTRAINT `fk_kona__friendship_event_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_event_friendship` FOREIGN KEY (`friendship_id`) 
        REFERENCES `kona__friendship` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_event_type` FOREIGN KEY (`type_id`) 
        REFERENCES `kona__friendship_event_type` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__friendship_event_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_event_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__friendship_event_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__friendship_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_circle` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
      `uid` varchar(255) NOT NULL,
      `user_id` bigint(20) unsigned NOT NULL,
      `slug` varchar(255) NOT NULL,
      `name` varchar(255) NOT NULL,
      `default_circle` tinyint(1) unsigned NOT NULL DEFAULT '0',
      `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

      PRIMARY KEY (`id`),

      UNIQUE KEY `id` (`id`),
    
      UNIQUE KEY `ux_kona__friendship_circle_uid` (`uid`),

      UNIQUE KEY `ux_kona__friendship_circle_slug` (`user_id`,`slug`),

      CONSTRAINT `fk_kona__circle_user` FOREIGN KEY (`user_id`) 
            REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__contact` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `ref_user_id` bigint(20) unsigned DEFAULT NULL,
  `place_id` bigint(20) unsigned DEFAULT NULL,
  `photo_id` bigint(20) unsigned DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL, -- may be external reference
  `thumbnail_url` varchar(255) DEFAULT NULL, -- may be external reference

  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,

  `url` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `social_handles` varchar(2000) DEFAULT NULL,

  `email_verified` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `mobile_verified` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `invited_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ix_kona__contact_uid` (`uid`),

  KEY `ix_kona__contact_owner` (`owner_id`),

  KEY `ix_kona__contact_ref_user` (`ref_user_id`),

  KEY `ix_kona__contact_photo` (`photo_id`),

  CONSTRAINT `fk_kona__contact_photo` FOREIGN KEY (`photo_id`)
    REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__contact_ref_user` FOREIGN KEY (`ref_user_id`)
    REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__contact_owner` FOREIGN KEY (`owner_id`)
    REFERENCES `kona__owner` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__invitation_channel` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),

    UNIQUE KEY `ux_kona__invitation_channel_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__invitation_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__invitation_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__invitation_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__invitation_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__invitation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `status_id` bigint(20) unsigned NOT NULL DEFAULT '100',
  `owner_id` bigint(20) unsigned NOT NULL,
  `contact_id` bigint(20) unsigned DEFAULT NULL,
  `invitee_user_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `invitation_code` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `invited_count` int(11) DEFAULT NULL,
  `invited_date` datetime(6) DEFAULT NULL,
  `viewed_date` datetime(6) DEFAULT NULL,
  `ignored_date` datetime(6) DEFAULT NULL,
  `accepted_date` datetime(6) DEFAULT NULL,
  `registered_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__invitation_uid` (`uid`),

  UNIQUE KEY `ux_kona__invitation_code` (`invitation_code`),

  KEY `ix_kona__invitation_email` (`email`),

  KEY `ix_kona__invitation_mobile_number` (`mobile_number`),

  KEY `ix_kona__invitation_type` (`type_id`),

  KEY `ix_kona__invitation_channel` (`channel_id`),

  KEY `ix_kona__invitation_status` (`status_id`),

  KEY `ix_kona__invitation_owner` (`owner_id`),

  KEY `ix_kona__invitation_contact` (`contact_id`),

  KEY `ix_kona__invitation_invitee_user` (`invitee_user_id`),

  CONSTRAINT `fk_kona__invitation_contact` FOREIGN KEY (`contact_id`)
        REFERENCES `kona__contact` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invitation_channel` FOREIGN KEY (`channel_id`) 
        REFERENCES `kona__invitation_channel` (`id`),

  CONSTRAINT `fk_kona__invitation_invitee_user` FOREIGN KEY (`invitee_user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invitation_status` FOREIGN KEY (`status_id`) 
        REFERENCES `kona__invitation_status` (`id`),

  CONSTRAINT `fk_kona__invitation_type` FOREIGN KEY (`type_id`) 
        REFERENCES `kona__invitation_type` (`id`),

  CONSTRAINT `fk_kona__invitation_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment_account_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__payment_account_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `added_by_id` bigint(20) unsigned NOT NULL,
  `default_account` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `max_transaction_amount` decimal(10,2) DEFAULT NULL,
  `max_transaction_count` int(11) unsigned DEFAULT NULL,
  `slug` varchar(255) default NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider_name` varchar(255) DEFAULT NULL,
  `provider_customer_id` varchar(255) DEFAULT NULL,
  `card_token` varchar(255) DEFAULT NULL,
  `card_last4` varchar(255) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `account_holder` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `routing_number` varchar(255) DEFAULT NULL,
  `account_street1` varchar(255) DEFAULT NULL,
  `account_street2` varchar(255) DEFAULT NULL,
  `account_city` varchar(255) DEFAULT NULL,
  `account_state` varchar(255) DEFAULT NULL,
  `account_postal_code` varchar(255) DEFAULT NULL,
  `account_country` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__payment_account_uid` (`uid`),

  UNIQUE KEY `ux_kona__payment_account_slug` (`app_id`, `account_id`, `slug`),

    -- this should be true but beware of possible conflicts
  UNIQUE KEY `ux_kona__payment_account_provider_customer_id` (`provider_customer_id`),

  KEY `ix_kona__payment_account_account` (`account_id`),

  KEY `ix_kona__payment_account_added_by` (`added_by_id`),

  KEY `ix_kona__payment_account_type` (`type_id`),

  CONSTRAINT `fk_kona__payment_account_type` FOREIGN KEY (`type_id`)
        REFERENCES `kona__payment_account_type` (`id`),

  CONSTRAINT `fk_kona__payment_account_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`),

  CONSTRAINT `fk_kona__payment_account_account` FOREIGN KEY (`account_id`)
        REFERENCES `kona__account` (`id`),

  CONSTRAINT `fk_kona__payment_account_added_by` FOREIGN KEY (`added_by_id`)
        REFERENCES `kona__user` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_slug` (`slug`),

  UNIQUE KEY `ux_kona__campaign_uid` (`uid`),

  KEY `ix_kona__campaign_app` (`app_id`),

  KEY `ix_kona__campaign_promo` (`promo_id`),

  KEY `ix_kona__campaign_partner` (`partner_id`),

  CONSTRAINT `fk_kona__campaign_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `slug` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `sms_number` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  `facebook_tracking_id` varchar(255) DEFAULT NULL,
  `google_tracking_id` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `title` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `main_content` varchar(2000) DEFAULT NULL,
  `conversion_content` varchar(2000) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_channel_uid` (`uid`),

  UNIQUE KEY `ux_kona__campaign_channel_campaign_type_slug` (`campaign_id`,`type_id`,`slug`),

  UNIQUE KEY `ux_kona__campaign_channel_sms_number` (`sms_number`),

  UNIQUE KEY `ux_kona__campaign_channel_slug_url_path` (`slug`,`url_path`),

  KEY `ix_kona__campaign_channel_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_channel_type` (`type_id`),

  CONSTRAINT `fk_kona__campaign_channel_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_channel_type` FOREIGN KEY (`type_id`) 
        REFERENCES `kona__campaign_type` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `event_category` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_label` varchar(255) DEFAULT NULL,
  `event_value` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_event_uid` (`uid`),

  KEY `ix_kona__campaign_event_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_event_channel` (`channel_id`),

  KEY `ix_kona__campaign_event_user` (`user_id`),

  SPATIAL `ix_kona__campaing_event_coords` (coords),

  CONSTRAINT `fk_kona__campaign_event_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_event_channel` FOREIGN KEY (`channel_id`) 
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_event_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `default_card_last4` varchar(4) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `shipping` decimal(10,2) DEFAULT NULL,
  `tax` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `checked_out` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `invoiced` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `expired` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `checked_out_date` datetime(6) DEFAULT NULL,
  `invoiced_date` datetime(6) DEFAULT NULL,
  `expired_date` datetime(6) DEFAULT NULL, -- date cart was externally expired
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__cart_uid` (`uid`),

  KEY `ix_kona__cart_app` (`app_id`),

  KEY `ix_kona__cart_user` (`user_id`),

  KEY `ix_kona__cart_account` (`account_id`),

  KEY `ix_kona__cart_invoice` (`invoice_id`),

  KEY `ix_kona__cart_currency` (`currency_id`),

  SPATIAL `ix_kona__cart_coords` (coords),

  CONSTRAINT `fk_kona__cart_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) on DELETE CASCADE,

  CONSTRAINT `fk_kona__cart_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__cart_currency` FOREIGN KEY (`currency_id`) 
        REFERENCES `kona__currency` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__cart_invoice` FOREIGN KEY (`invoice_id`) 
        REFERENCES `kona__invoice` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__cart_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__cart_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `cart_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `description` varchar(4000) DEFAULT NULL,
  `discount_description` varchar(4000) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `subscription_start_date` datetime(6) DEFAULT NULL,
  `subscription_end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__cart_item_uid` (`uid`),

  KEY `ix_kona__cart_item_cart` (`cart_id`),

  KEY `ix_kona__cart_item_product` (`product_id`),

  KEY `ix_kona__cart_item_promo` (`promo_id`),

  CONSTRAINT `fk_kona__cart_item_cart` FOREIGN KEY (`cart_id`) 
        REFERENCES `kona__cart` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__cart_item_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__cart_item_product` FOREIGN KEY (`product_id`) 
        REFERENCES `kona__product` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------


CREATE TABLE `kona__currency` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `symbol` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `iso_code` varchar(4) NOT NULL,
  `country` varchar(64) DEFAULT NULL,
  `subdivison` varchar(64) DEFAULT NULL,
  `regime` varchar(64) DEFAULT NULL,
  `locale` varchar(8) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ix_kona__currency_iso_code` (`iso_code`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `start_balance` decimal(10,2) DEFAULT NULL,
  `end_balance` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `tax` decimal(10,2) DEFAULT NULL,
  `shipping` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `amount_due` decimal(10,2) NOT NULL,
  `amount_paid` decimal(10,2) DEFAULT NULL,
  `paid` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `closed` tinyint(1) unsigned NOT NULL DEFAULT '0',
--  `subscription_start_date` datetime(6) DEFAULT NULL,
--  `subscription_end_date` datetime(6) DEFAULT NULL,
  `invoice_date` datetime(6) NOT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `closed_date` datetime(6) DEFAULT NULL,
  `payment_attempted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `payment_attempt_count` int(11) unsigned DEFAULT NULL,
  `last_payment_attempt_date` datetime(6) DEFAULT NULL,
  `next_payment_attempt_date` datetime(6) DEFAULT NULL,
  `payment_card_last4` varchar(4) DEFAULT NULL,
  `payment_ref` varchar(512) DEFAULT NULL,
  `notes` varchar(4000) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__invoice_uid` (`uid`),

  UNIQUE KEY `ux_kona__invoice_invoice_no` (`invoice_no`),

  KEY `ix_kona__invoice_app` (`app_id`),

  KEY `ix_kona__invoice_user` (`user_id`),

  KEY `ix_kona__invoice_account` (`account_id`),

  KEY `ix_kona__invoice_currency` (`currency_id`),

  CONSTRAINT `fk_kona__invoice_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_invoice_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`),

  CONSTRAINT `fk_invoice_currency` FOREIGN KEY (`currency_id`) 
        REFERENCES `kona__currency` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_invoice_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------


CREATE TABLE `kona__invoice_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `invoice_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `discount_description` varchar(4000) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `subtotal` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `subscription_start_date` datetime(6) DEFAULT NULL,
  `subscription_end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__invoice_item_uid` (`uid`),

  KEY `ix_kona__invoice_item_invoice` (`invoice_id`),

  KEY `ix_kona__invoice_product` (`product_id`),

  KEY `fk_kona__invoice_item_promo` (`promo_id`),

  CONSTRAINT `fk_kona__invoice_item_invoice` FOREIGN KEY (`invoice_id`) 
        REFERENCES `kona__invoice` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__invoice_item_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invoice_item_product` FOREIGN KEY (`product_id`) 
        REFERENCES `kona__product` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------


CREATE TABLE `kona__partner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `place_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `social_handles` varchar(2000) DEFAULT NULL,
  `contact_first_name` varchar(255) DEFAULT NULL,
  `contact_last_name` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_phone_number` varchar(255) DEFAULT NULL,
  `contact_mobile_number` varchar(255) DEFAULT NULL,
  `population` int(11) unsigned DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `deleted_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__partner_uid` (`uid`),

  UNIQUE KEY `ux_kona__partner_slug` (`slug`),

  KEY `ix_kona__partner_parent` (`parent_id`),

  KEY `ix_kona__partner_place` (`place_id`),

  CONSTRAINT `fk_kona__partner_place` FOREIGN KEY (`place_id`) 
        REFERENCES `kona__place` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__partner_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `type_id` bigint(20) unsigned DEFAULT NULL,
  `status_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `payment_account_id` bigint(20) unsigned DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `card_token` varchar(255) DEFAULT NULL,
  `card_last4` varchar(4) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `amount_refunded` decimal(10,2) DEFAULT NULL,
  `processor_ref` varchar(255) DEFAULT NULL, -- unique key determines max size here
  `processor_receipt` text,
  `processor_error` varchar(2000) DEFAULT NULL,
  `processor_fee` decimal(10,2) DEFAULT NULL,
  `paid` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `refunded` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `disputed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `failed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `paid_date` datetime(6) DEFAULT NULL,
  `disputed_date` datetime(6) DEFAULT NULL,
  `refunded_date` datetime(6) DEFAULT NULL,
  `failed_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__payment_uid` (`uid`),

  UNIQUE KEY `ux_kona__payment_processor_ref` (`processor_ref`),

  KEY `ix_kona__payment_app` (`app_id`),

  KEY `ix_kona__payment_type` (`type_id`),

  KEY `ix_kona__payment_status` (`status_id`),

  KEY `ix_kona__payment_currency` (`currency_id`),

  KEY `ix_kona__payment_user` (`user_id`),

  KEY `ix_kona__payment_account` (`account_id`),

  KEY `ix_kona__payment_invoice` (`invoice_id`),

  KEY `ix_kona__payment_promo` (`promo_id`),

  KEY `ix_kona__payment_payment_account` (`payment_account_id`),

  SPATIAL `ix_kona__payment_coords` (coords),

  CONSTRAINT `fk_kona__payment_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) on DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_currency` FOREIGN KEY (`currency_id`) 
        REFERENCES `kona__currency` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_invoice` FOREIGN KEY (`invoice_id`) 
        REFERENCES `kona__invoice` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__payment_payment_account` FOREIGN KEY (`payment_account_id`) 
        REFERENCES `kona__payment_account` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__payment_status` FOREIGN KEY (`status_id`) 
        REFERENCES `kona__payment_status` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_type` FOREIGN KEY (`type_id`) 
        REFERENCES `kona__payment_type` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__payment_status_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__payment_type_name` (`name`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__pre_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `payment_id` bigint(20) unsigned DEFAULT NULL,
  `ref_app_id` bigint(20) unsigned DEFAULT NULL,
  `referred_by_user_id` bigint(20) unsigned DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `reconciled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `proxy_payment` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `processor` enum('stripe') DEFAULT NULL,
  `payment_description` varchar(4000) DEFAULT NULL,
  `payment_token` varchar(255) DEFAULT NULL,
  `payment_card_last4` varchar(255) DEFAULT NULL,
  `payment_ref` varchar(512) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `shipping_address1` varchar(255) DEFAULT NULL,
  `shipping_address2` varchar(255) DEFAULT NULL,
  `shipping_city` varchar(255) DEFAULT NULL,
  `shipping_state` varchar(255) DEFAULT NULL,
  `shipping_postal_code` varchar(255) DEFAULT NULL,
  `shipping_country` varchar(255) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `shipped_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__pre_order_uid` (`uid`),

  KEY `ix_kona__pre_order_referred_by` (`referred_by_user_id`),

  KEY `ix_kona__pre_order_app` (`app_id`),

  KEY `ix_kona__pre_order_partner` (`partner_id`),

  KEY `ix_kona__pre_order_ref_app` (`ref_app_id`),

  KEY `ix_kona__pre_order_payment` (`payment_id`),

  KEY `ix_kona__pre_order_campaign` (`campaign_id`),

  CONSTRAINT `fk_kona__pre_order_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_payment` FOREIGN KEY (`payment_id`) 
        REFERENCES `kona__payment` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_ref_app` FOREIGN KEY (`ref_app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_referred_by` FOREIGN KEY (`referred_by_user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__promo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `promo_code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `visible` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `signup_default` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `use_count` int(11) unsigned DEFAULT NULL,
  `use_per_account` int(11) DEFAULT '1',
  `max_use_count` int(11) unsigned DEFAULT NULL,
  `discount_pct` int(11) DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `trial_days` int(11) DEFAULT NULL,
  `subscription_days` int(11) DEFAULT NULL,
  `validation_rule` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__promo_uid` (`uid`),

  UNIQUE KEY `ix_kona__promo_code` (`promo_code`),

  KEY `ix_kona__promo_app` (`app_id`),

  KEY `ix_kona__promo_product` (`product_id`),

  CONSTRAINT `fk_kona__promo_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,

  CONSTRAINT `fk_kona__promo_product` FOREIGN KEY (`product_id`) 
        REFERENCES `kona__product` (`id`) ON UPDATE CASCADE ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

-- CREATE TABLE `kona__promo_page` (
--   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--   `app_id` bigint(20) unsigned DEFAULT NULL,
--   `promo_id` bigint(20) unsigned DEFAULT NULL,
--   `partner_id` bigint(20) unsigned DEFAULT NULL,
--   `campaign_id` bigint(20) unsigned DEFAULT NULL,
--   `name` varchar(255) NOT NULL,
--   `path` varchar(255) DEFAULT NULL,
--   `phone_number` varchar(255) DEFAULT NULL,
--   `title` varchar(255) DEFAULT NULL,
--   `banner_url` varchar(255) DEFAULT NULL,
--   `logo_url` varchar(255) DEFAULT NULL,
--   `intro_message` varchar(2000) DEFAULT NULL,
--   `registered_message` varchar(2000) DEFAULT NULL,
--   `facebook_tracking_id` varchar(255) DEFAULT NULL,
--   `google_tracking_id` varchar(255) DEFAULT NULL,
--   `enabled` tinyint(1) NOT NULL DEFAULT '0',
--   `root` tinyint(1) NOT NULL DEFAULT '0',
--   `expired_date` datetime(6) DEFAULT NULL,
--   `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--   `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
-- 
--   PRIMARY KEY (`id`),
-- 
--   UNIQUE KEY `id` (`id`),
-- 
--   UNIQUE KEY `ux_kona__promo_page_phone_number` (`phone_number`),
-- 
--   UNIQUE KEY `ux_kona__promo_page_path_name` (`path`,`name`),
-- 
--   KEY `ix_kona__promo_page_app` (`app_id`),
-- 
--   KEY `ix_kona__promo_page_promo` (`promo_id`),
-- 
--   KEY `ix_kona__promo_page_partner` (`partner_id`),
-- 
--   KEY `ix_kona__promo_page_campaign` (`campaign_id`),
-- 
--   CONSTRAINT `fk_kona__promo_page_app` FOREIGN KEY (`app_id`) 
--         REFERENCES `kona__app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
-- 
--   CONSTRAINT `fk_kona__promo_page_campaign` FOREIGN KEY (`campaign_id`) 
--         REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
-- 
--   CONSTRAINT `fk_kona__promo_page_partner` FOREIGN KEY (`partner_id`) 
--         REFERENCES `kona__partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
-- 
--   CONSTRAINT `fk_kona__promo_page_promo` FOREIGN KEY (`promo_id`) 
--         REFERENCES `kona__promo` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
-- 
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__sales_lead` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned DEFAULT NULL,
  `ref_app_id` bigint(20) unsigned DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `twitterUsername` varchar(255) DEFAULT NULL,
  `facebookUsername` varchar(255) DEFAULT NULL,
  `referred_by_user_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `channel` enum('website','referral') DEFAULT NULL,
  `interests` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__sales_lead_uid` (`uid`),

  KEY `ix_kona__sales_lead_referred_by` (`referred_by_user_id`),

  KEY `ix_kona__sales_lead_app` (`app_id`),

  KEY `ix_kona__sales_lead_ref_app` (`ref_app_id`),

  KEY `ix_kona__sales_lead_campaign` (`campaign_id`),

  CONSTRAINT `fk_kona__sales_lead_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_ref_app` FOREIGN KEY (`ref_app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_referred_by` FOREIGN KEY (`referred_by_user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `slug` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `display_order` int(11) unsigned DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `trial_days` int(11) DEFAULT NULL,
  `subscription` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `subscription_days` int(11) DEFAULT NULL,
  `support_type` enum('Email','Priority') DEFAULT NULL,
  -- `default_plan` tinyint(1) unsigned DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__product_uid` (`uid`),

  UNIQUE KEY `ux_kona__product_app_slug` (`app_id`,`slug`),

  KEY `ix_kona__product_slug` (`slug`),

  CONSTRAINT `fk_kona__product_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__purchase` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `payment_type_id` bigint(20) unsigned DEFAULT NULL,
  `kind` varchar(255) DEFAULT NULL, -- field used by google play
  `invoice_no` varchar(255) DEFAULT NULL,
  `auto_renew` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `expiration_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__purchase_uid` (`account_id`,`product_id`),

  UNIQUE KEY `ux_kona__purchase` (`account_id`,`product_id`),

  KEY `ix_kona__purchase_parent` (`parent_id`),

  KEY `ix_kona__purchase_user` (`user_id`),

  KEY `ix_kona__purchase_product` (`product_id`),

  KEY `ix_kona__purchase_app` (`app_id`),

  KEY `ix_kona__purchase_promo` (`promo_id`),

  KEY `ix_kona__purchase_partner` (`partner_id`),

  KEY `ix_kona__purchase_campaign` (`campaign_id`),

  KEY `ix_kona__purchase_payment_type` (`payment_type_id`),

  KEY `ix_kona__purchase_invoice` (`invoice_no`),

  CONSTRAINT `fk_kona__purchase_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__purchase_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__purchase_campaign` FOREIGN KEY (`campaign_id`) 
        REFERENCES `kona__campaign` (`id`),

  CONSTRAINT `fk_kona__purchase_invoice` FOREIGN KEY (`invoice_no`) 
        REFERENCES `kona__invoice` (`invoice_no`),

  CONSTRAINT `fk_kona__purchase_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__purchase` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__purchase_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `kona__partner` (`id`),

  CONSTRAINT `fk_kona__purchase_payment_type` FOREIGN KEY (`payment_type_id`) 
        REFERENCES `kona__payment_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__purchase_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`),

  CONSTRAINT `fk_kona__purchase_product` FOREIGN KEY (`product_id`) 
        REFERENCES `kona__product` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__purchase_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `added_by_id` bigint(20) unsigned default NULL,
  `file_id` bigint(20) unsigned NOT NULL,
  `file_url_path` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_template_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_template_slug` (`slug`),

  KEY `ix_kona__landing_page_template_added_by` (`added_by_id`),

  KEY `ix_kona__landing_page_template_file` (`file_id`),

  FULLTEXT `ft_kona__landing_page_template` (`uid`,`name`, `slug`, `description`),

  CONSTRAINT `fk_kona__landing_page_template_added_by` FOREIGN KEY (`added_by_id`)
		REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_template_file` FOREIGN KEY (`file_id`)
        REFERENCES `kona__file` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned default NULL,
  `added_by_id` bigint(20) unsigned default NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `url_path` varchar(255) NOT NULL,
  `short_url` varchar(255) default NULL,
  `facebook_tracking_id` varchar(255) DEFAULT NULL,
  `google_tracking_id` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE `ux_kona__landing_page_uid` (`uid`),

  UNIQUE `ux_kona__landing_page_url_path` (`url_path`),

  UNIQUE `ix_kona__landing_page_short_url` (`short_url`),

  KEY `ix_kona__landing_page_added_by` (`added_by_id`),

  KEY `ix_kona__landing_page_template` (`template_id`),


  FULLTEXT `ft_kona__landing_page` (`uid`,`name`, `description`, `url_path`, `facebook_tracking_id`, `google_tracking_id`),

  CONSTRAINT `fk_kona__landing_page_app` FOREIGN KEY (`app_id`)
        REFERENCES `kona__app` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_added_by` FOREIGN KEY (`added_by_id`)
		REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__landing_page_template` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__landing_page_short_url` FOREIGN KEY (`short_url`)
        REFERENCES `kona__short_url` (`short_url`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page_template_param` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `required` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_template_param_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_template_param_name` (`template_id`, `name`),

  KEY `ix_kona__landing_page_template_param_template` (`template_id`),

  CONSTRAINT `fk_kona__landing_page_template_param_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__landing_page_template` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page_param` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `landing_page_id` bigint(20) unsigned NOT NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `file_id` bigint(20) unsigned default NULL,
  `name` varchar(255) NOT NULL,
  `value` longtext NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_param_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_param_name` (`landing_page_id`, `name`),

  KEY `ix_kona__landing_page_param_page` (`landing_page_id`),

  KEY `ix_kona__landing_page_param_template` (`template_id`),

  CONSTRAINT `fk_kona__landing_page_param_file` FOREIGN KEY (`file_id`)
        REFERENCES `kona__file` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_param_page` FOREIGN KEY (`landing_page_id`)
        REFERENCES `kona__landing_page` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__landing_page_param_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__landing_page_template` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__landing_page_param_name` FOREIGN KEY (`template_id`, `name`)
        REFERENCES `kona__landing_page_template_param` (`template_id`, `name`) ON DELETE RESTRICT ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- --------------------------------------------------------------------------

INSERT INTO `kona__auth_role`
VALUES 
    (100,'SYSTEM', now(), now()),
    (200,'ADMIN', now(), now()),
    (300,'USER', now(), now()),
    (400,'GUEST', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__user_type` 
VALUES 
    (100,'SYSTEM', now(), now()),
    (200,'USER', now(), now()),
    (300,'TEST', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__notification_channel`
VALUES
    (100,'IN_APP', now(), now()),
    (200,'EMAIL', now(), now()),
    (300,'SMS', now(), now()),
    (400,'PUSH', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__email_event_type`
VALUES
    (100,'ATTEMPTED', now(), now()),
    (110,'FAILED',  now(), now()),
    (200,'DELIVERED', now(), now()),
    (300,'BOUNCED', now(), now()),
    (400,'COMPLAINED', now(), now()),
    (500,'UNSUBSCRIBED', now(), now()),
    (600,'OPENED', now(), now()),
    (700,'FORWARDED', now(), now()),
    (800,'PRINTED', now(), now()),
    (900,'CLICKED', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__invitation_type` 
VALUES 
    (100,'ACCOUNT', now(), now()),
    (200,'FRIEND', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__invitation_channel` 
VALUES 
    (100,'IN_APP', now(), now()),
    (200,'EMAIL', now(), now()),
    (300,'SMS', now(), now()),
    (400,'TWITTER', now(), now()),
    (500,'FACEBOOK', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__invitation_status` 
VALUES 
    (100,'PENDING', now(), now()),
    (200,'ACCEPTED', now(), now()),
    (300,'DECLINED', now(), now()),
    (400,'IGNORED', now(), now());


-- --------------------------------------------------------------------------

INSERT INTO `kona__friendship_status` 
VALUES 
    (100,'NONE', now(), now()),
    (200,'FRIENDS', now(), now()),
    (300,'PENDING', now(), now()),
    (400,'FOLLOWING', now(), now()),
    (500,'FOLLOWED', now(), now()),
    (600,'BLOCKING', now(), now()),
    (700,'BLOCKED', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__friendship_event_type`
VALUES
    (100,'FOLLOW', now(), now()),
    (200,'UNFOLLOW', now(), now()),
    (300,'BLOCK', now(), now()),
    (400,'UNBLOCK', now(), now()),
    (500,'FRIENDSHIP_REQUEST', now(), now()),
    (600,'FRIENDSHIP_ACCEPT', now(), now()),
    (700,'FRIENDSHIP_REJECT', now(), now()),
    (800,'FRIENDSHIP_REVOKE', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__currency` VALUES (1,'Dirhams',NULL,'AED','United Arab Emirates',NULL,NULL,NULL),(2,'Afghanis',NULL,'AFN','Afghanistan',NULL,NULL,NULL),(3,'Leke',NULL,'ALL','Albania',NULL,NULL,NULL),(4,'Drams',NULL,'AMD','Armenia',NULL,NULL,NULL),(5,'Guilders (also called Florins)',NULL,'ANG','Netherlands Antilles',NULL,NULL,NULL),(6,'Kwanza',NULL,'AOA','Angola',NULL,NULL,NULL),(7,'Pesos',NULL,'ARS','Argentina',NULL,NULL,NULL),(8,'Dollars',NULL,'AUD','Australia',NULL,NULL,NULL),(9,'Guilders (also called Florins)',NULL,'AWG','Aruba',NULL,NULL,NULL),(10,'Manats [obsolete]',NULL,'AZM','Azerbaijan',NULL,NULL,NULL),(11,'New Manats',NULL,'AZN','Azerbaijan',NULL,NULL,NULL),(12,'Convertible Marka',NULL,'BAM','Bosnia and Herzegovina',NULL,NULL,NULL),(13,'Dollars',NULL,'BBD','Barbados',NULL,NULL,NULL),(14,'Taka',NULL,'BDT','Bangladesh',NULL,NULL,NULL),(15,'Leva',NULL,'BGN','Bulgaria',NULL,NULL,NULL),(16,'Dinars',NULL,'BHD','Bahrain',NULL,NULL,NULL),(17,'Francs',NULL,'BIF','Burundi',NULL,NULL,NULL),(18,'Dollars',NULL,'BMD','Bermuda',NULL,NULL,NULL),(19,'Dollars',NULL,'BND','Brunei Darussalam',NULL,NULL,NULL),(20,'Bolivianos',NULL,'BOB','Bolivia',NULL,NULL,NULL),(21,'Brazil Real',NULL,'BRL','Brazil',NULL,NULL,NULL),(22,'Dollars',NULL,'BSD','Bahamas',NULL,NULL,NULL),(23,'Ngultrum',NULL,'BTN','Bhutan',NULL,NULL,NULL),(24,'Pulas',NULL,'BWP','Botswana',NULL,NULL,NULL),(25,'Rubles',NULL,'BYR','Belarus',NULL,NULL,NULL),(26,'Dollars',NULL,'BZD','Belize',NULL,NULL,NULL),(27,'Dollars',NULL,'CAD','Canada',NULL,NULL,NULL),(28,'Congolese Francs',NULL,'CDF','Congo/Kinshasa',NULL,NULL,NULL),(29,'Francs',NULL,'CHF','Switzerland',NULL,NULL,NULL),(30,'Pesos',NULL,'CLP','Chile',NULL,NULL,NULL),(31,'Yuan Renminbi',NULL,'CNY','China',NULL,NULL,NULL),(32,'Pesos',NULL,'COP','Colombia',NULL,NULL,NULL),(33,'Colones',NULL,'CRC','Costa Rica',NULL,NULL,NULL),(34,'Dinars',NULL,'RSD','Serbia',NULL,NULL,NULL),(35,'Pesos',NULL,'CUP','Cuba',NULL,NULL,NULL),(36,'Escudos',NULL,'CVE','Cape Verde',NULL,NULL,NULL),(37,'Pounds',NULL,'CYP','Cyprus',NULL,NULL,NULL),(38,'Koruny',NULL,'CZK','Czech Republic',NULL,NULL,NULL),(39,'Francs',NULL,'DJF','Djibouti',NULL,NULL,NULL),(40,'Kroner',NULL,'DKK','Denmark',NULL,NULL,NULL),(41,'Pesos',NULL,'DOP','Dominican Republic',NULL,NULL,NULL),(42,'Algeria Dinars',NULL,'DZD','Algeria',NULL,NULL,NULL),(43,'Krooni',NULL,'EEK','Estonia',NULL,NULL,NULL),(44,'Pounds',NULL,'EGP','Egypt',NULL,NULL,NULL),(45,'Nakfa',NULL,'ERN','Eritrea',NULL,NULL,NULL),(46,'Birr',NULL,'ETB','Ethiopia',NULL,NULL,NULL),(47,'Euro',NULL,'EUR','Euro Member Countries',NULL,NULL,NULL),(48,'Dollars',NULL,'FJD','Fiji',NULL,NULL,NULL),(49,'Pounds',NULL,'FKP','Falkland Islands (Malvinas)',NULL,NULL,NULL),(50,'Pounds',NULL,'GBP','United Kingdom',NULL,NULL,NULL),(51,'Lari',NULL,'GEL','Georgia',NULL,NULL,NULL),(52,'Pounds',NULL,'GGP','Guernsey',NULL,NULL,NULL),(53,'Cedis',NULL,'GHC','Ghana',NULL,NULL,NULL),(54,'Pounds',NULL,'GIP','Gibraltar',NULL,NULL,NULL),(55,'Dalasi',NULL,'GMD','Gambia',NULL,NULL,NULL),(56,'Francs',NULL,'GNF','Guinea',NULL,NULL,NULL),(57,'Quetzales',NULL,'GTQ','Guatemala',NULL,NULL,NULL),(58,'Dollars',NULL,'GYD','Guyana',NULL,NULL,NULL),(59,'Dollars',NULL,'HKD','Hong Kong',NULL,NULL,NULL),(60,'Lempiras',NULL,'HNL','Honduras',NULL,NULL,NULL),(61,'Kuna',NULL,'HRK','Croatia',NULL,NULL,NULL),(62,'Gourdes',NULL,'HTG','Haiti',NULL,NULL,NULL),(63,'Forint',NULL,'HUF','Hungary',NULL,NULL,NULL),(64,'Rupiahs',NULL,'IDR','Indonesia',NULL,NULL,NULL),(65,'New Shekels',NULL,'ILS','Israel',NULL,NULL,NULL),(66,'Pounds',NULL,'IMP','Isle of Man',NULL,NULL,NULL),(67,'Rupees',NULL,'INR','India',NULL,NULL,NULL),(68,'Dinars',NULL,'IQD','Iraq',NULL,NULL,NULL),(69,'Rials',NULL,'IRR','Iran',NULL,NULL,NULL),(70,'Kronur',NULL,'ISK','Iceland',NULL,NULL,NULL),(71,'Pounds',NULL,'JEP','Jersey',NULL,NULL,NULL),(72,'Dollars',NULL,'JMD','Jamaica',NULL,NULL,NULL),(73,'Dinars',NULL,'JOD','Jordan',NULL,NULL,NULL),(74,'Yen',NULL,'JPY','Japan',NULL,NULL,NULL),(75,'Shillings',NULL,'KES','Kenya',NULL,NULL,NULL),(76,'Soms',NULL,'KGS','Kyrgyzstan',NULL,NULL,NULL),(77,'Riels',NULL,'KHR','Cambodia',NULL,NULL,NULL),(78,'Francs',NULL,'KMF','Comoros',NULL,NULL,NULL),(79,'Won',NULL,'KPW','Korea (North)',NULL,NULL,NULL),(80,'Won',NULL,'KRW','Korea (South)',NULL,NULL,NULL),(81,'Dinars',NULL,'KWD','Kuwait',NULL,NULL,NULL),(82,'Dollars',NULL,'KYD','Cayman Islands',NULL,NULL,NULL),(83,'Tenge',NULL,'KZT','Kazakhstan',NULL,NULL,NULL),(84,'Kips',NULL,'LAK','Laos',NULL,NULL,NULL),(85,'Pounds',NULL,'LBP','Lebanon',NULL,NULL,NULL),(86,'Rupees',NULL,'LKR','Sri Lanka',NULL,NULL,NULL),(87,'Dollars',NULL,'LRD','Liberia',NULL,NULL,NULL),(88,'Maloti',NULL,'LSL','Lesotho',NULL,NULL,NULL),(89,'Litai',NULL,'LTL','Lithuania',NULL,NULL,NULL),(90,'Lati',NULL,'LVL','Latvia',NULL,NULL,NULL),(91,'Dinars',NULL,'LYD','Libya',NULL,NULL,NULL),(92,'Dirhams',NULL,'MAD','Morocco',NULL,NULL,NULL),(93,'Lei',NULL,'MDL','Moldova',NULL,NULL,NULL),(94,'Ariary',NULL,'MGA','Madagascar',NULL,NULL,NULL),(95,'Denars',NULL,'MKD','Macedonia',NULL,NULL,NULL),(96,'Kyats',NULL,'MMK','Myanmar (Burma)',NULL,NULL,NULL),(97,'Tugriks',NULL,'MNT','Mongolia',NULL,NULL,NULL),(98,'Patacas',NULL,'MOP','Macau',NULL,NULL,NULL),(99,'Ouguiyas',NULL,'MRO','Mauritania',NULL,NULL,NULL),(100,'Liri',NULL,'MTL','Malta',NULL,NULL,NULL),(101,'Rupees',NULL,'MUR','Mauritius',NULL,NULL,NULL),(102,'Rufiyaa',NULL,'MVR','Maldives (Maldive Islands)',NULL,NULL,NULL),(103,'Kwachas',NULL,'MWK','Malawi',NULL,NULL,NULL),(104,'Pesos',NULL,'MXN','Mexico',NULL,NULL,NULL),(105,'Ringgits',NULL,'MYR','Malaysia',NULL,NULL,NULL),(106,'Meticais [obsolete]',NULL,'MZM','Mozambique',NULL,NULL,NULL),(107,'Meticais [newer unit same name]',NULL,'MZN','Mozambique',NULL,NULL,NULL),(108,'Dollars',NULL,'NAD','Namibia',NULL,NULL,NULL),(109,'Nairas',NULL,'NGN','Nigeria',NULL,NULL,NULL),(110,'Cordobas',NULL,'NIO','Nicaragua',NULL,NULL,NULL),(111,'Krone',NULL,'NOK','Norway',NULL,NULL,NULL),(112,'Nepal Rupees',NULL,'NPR','Nepal',NULL,NULL,NULL),(113,'Dollars',NULL,'NZD','New Zealand',NULL,NULL,NULL),(114,'Rials',NULL,'OMR','Oman',NULL,NULL,NULL),(115,'Balboa',NULL,'PAB','Panama',NULL,NULL,NULL),(116,'Nuevos Soles',NULL,'PEN','Peru',NULL,NULL,NULL),(117,'Kina',NULL,'PGK','Papua New Guinea',NULL,NULL,NULL),(118,'Pesos',NULL,'PHP','Philippines',NULL,NULL,NULL),(119,'Rupees',NULL,'PKR','Pakistan',NULL,NULL,NULL),(120,'Zlotych',NULL,'PLN','Poland',NULL,NULL,NULL),(121,'Guarani',NULL,'PYG','Paraguay',NULL,NULL,NULL),(122,'Rials',NULL,'QAR','Qatar',NULL,NULL,NULL),(123,'Lei [obsolete]',NULL,'ROL','Romania',NULL,NULL,NULL),(124,'New Lei',NULL,'RON','Romania',NULL,NULL,NULL),(125,'Rubles',NULL,'RUB','Russia',NULL,NULL,NULL),(126,'Rwanda Francs',NULL,'RWF','Rwanda',NULL,NULL,NULL),(127,'Riyals',NULL,'SAR','Saudi Arabia',NULL,NULL,NULL),(128,'Dollars',NULL,'SBD','Solomon Islands',NULL,NULL,NULL),(129,'Rupees',NULL,'SCR','Seychelles',NULL,NULL,NULL),(130,'Dinars',NULL,'SDG','Sudan',NULL,NULL,NULL),(131,'Kronor',NULL,'SEK','Sweden',NULL,NULL,NULL),(132,'Dollars',NULL,'SGD','Singapore',NULL,NULL,NULL),(133,'Pounds',NULL,'SHP','Saint Helena',NULL,NULL,NULL),(134,'Tolars [obsolete]',NULL,'SIT','Slovenia',NULL,NULL,NULL),(135,'Koruny',NULL,'SKK','Slovakia',NULL,NULL,NULL),(136,'Leones',NULL,'SLL','Sierra Leone',NULL,NULL,NULL),(137,'Shillings',NULL,'SOS','Somalia',NULL,NULL,NULL),(138,'Luigini',NULL,'SPL','Seborga',NULL,NULL,NULL),(139,'Dollars',NULL,'SRD','Suriname',NULL,NULL,NULL),(140,'Dobras',NULL,'STD','São Tome and Principe',NULL,NULL,NULL),(141,'Colones',NULL,'SVC','El Salvador',NULL,NULL,NULL),(142,'Pounds',NULL,'SYP','Syria',NULL,NULL,NULL),(143,'Emalangeni',NULL,'SZL','Swaziland',NULL,NULL,NULL),(144,'Baht',NULL,'THB','Thailand',NULL,NULL,NULL),(145,'Somoni',NULL,'TJS','Tajikistan',NULL,NULL,NULL),(146,'Manats',NULL,'TMM','Turkmenistan',NULL,NULL,NULL),(147,'Dinars',NULL,'TND','Tunisia',NULL,NULL,NULL),(148,'Pa\'anga',NULL,'TOP','Tonga',NULL,NULL,NULL),(149,'New Lira',NULL,'TRY','Turkey',NULL,NULL,NULL),(150,'Dollars',NULL,'TTD','Trinidad and Tobago',NULL,NULL,NULL),(151,'Tuvalu Dollars',NULL,'TVD','Tuvalu',NULL,NULL,NULL),(152,'New Dollars',NULL,'TWD','Taiwan',NULL,NULL,NULL),(153,'Shillings',NULL,'TZS','Tanzania',NULL,NULL,NULL),(154,'Hryvnia',NULL,'UAH','Ukraine',NULL,NULL,NULL),(155,'Shillings',NULL,'UGX','Uganda',NULL,NULL,NULL),(156,'Dollars',NULL,'USD','United States of America',NULL,NULL,NULL),(157,'Pesos',NULL,'UYU','Uruguay',NULL,NULL,NULL),(158,'Sums',NULL,'UZS','Uzbekistan',NULL,NULL,NULL),(159,'Bolivares',NULL,'VEB','Venezuela',NULL,NULL,NULL),(160,'Dong',NULL,'VND','Viet Nam',NULL,NULL,NULL),(161,'Vatu',NULL,'VUV','Vanuatu',NULL,NULL,NULL),(162,'Tala',NULL,'WST','Samoa',NULL,NULL,NULL),(163,'Francs',NULL,'XAF','Communauté Financière Africaine BEAC',NULL,NULL,NULL),(164,'Ounces',NULL,'XAG','Silver',NULL,NULL,NULL),(165,'Ounces',NULL,'XAU','Gold',NULL,NULL,NULL),(166,'East Caribbean Dollars',NULL,'XCD',NULL,NULL,NULL,NULL),(167,'International Monetary Fund (IMF) Special Drawing Rights',NULL,'XDR',NULL,NULL,NULL,NULL),(168,'Francs',NULL,'XOF','Communauté Financière Africaine BCEAO',NULL,NULL,NULL),(169,'Palladium Ounces',NULL,'XPD',NULL,NULL,NULL,NULL),(170,'Comptoirs Français du Pacifique Francs',NULL,'XPF',NULL,NULL,NULL,NULL),(171,'Ounces',NULL,'XPT','Platinum',NULL,NULL,NULL),(172,'Rials',NULL,'YER','Yemen',NULL,NULL,NULL),(173,'Rand',NULL,'ZAR','South Africa',NULL,NULL,NULL),(174,'Kwacha',NULL,'ZMK','Zambia',NULL,NULL,NULL),(175,'Zimbabwe Dollars',NULL,'ZWD','Zimbabwe',NULL,NULL,NULL);

-- --------------------------------------------------------------------------

INSERT INTO `kona__payment_status` 
VALUES 
    (100,'SUCCESS', now(), now()),
    (200,'CARD_INVALID_NUMBER', now(), now()),
    (210,'CARD_INVALID_MONTH', now(), now()),
    (220,'CARD_INVALID_YEAR', now(), now()),
    (230,'CARD_INVALID_CVC', now(), now()),
    (240,'CARD_INVALID_ADDRESS', now(), now()),
    (250,'CARD_INVALID_ZIP', now(), now()),
    (260,'CARD_EXPIRED', now(), now()),
    (270,'CARD_DECLINED', now(), now()),
    (280,'CARD_MISSING', now(), now()),
    (300,'ACCOUNT_INVALID', now(), now()),
    (310,'ACCOUNT_DISABLED', now(), now()),
    (400,'AMOUNT_INVALID', now(), now()),
    (900,'PROCESSOR_ERROR', now(), now()),
    (999,'SYSTEM_ERROR', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__payment_type` 
VALUES 
    (100,'CASH', now(), now()),
    (200,'CHECK', now(), now()),
    (300,'CARD', now(), now()),
    (400,'WIRE', now(), now()),
    (500,'ACH', now(), now()),
    (600,'PAYPAL', now(), now()),
    (700,'CREDIT', now(), now()),
    (800,'PROMO', now(), now()),
    (900,'EXTERNAL', now(), now()),
    (901,'APPLE_APPSTORE', now(), now()),
    (902,'GOOGLE_PLAY', now(), now()),
    (999,'OTHER', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__payment_account_type`
VALUES
    (100,'CREDIT_CARD', now(), now()),
    (200,'DEBIT_CARD', now(), now()),
    (300,'PAYPAL', now(), now()),
    (400,'BANK_PERSONAL_CHECKING', now(), now()),
    (500,'BANK_PERSONAL_SAVINGS', now(), now()),
    (600,'BANK_BUSINESS_CHECKING', now(), now()),
    (700,'BANK_BUSINESS_SAVINGS', now(), now());

-- --------------------------------------------------------------------------

INSERT INTO `kona__campaign_type` 
VALUES 
    (100,'BLOG', now(), now()),
    (200,'EMAIL', now(), now()),
    (300,'GOOGLE', now(), now()),
    (400,'FACEBOOK', now(), now()),
    (500,'TWITTER', now(), now()),
    (600,'BANNER', now(), now()),
    (700,'SOCIAL_DEAL', now(), now()),
    (999,'OTHER', now(), now());
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

set @userId = 1;
set @accountId = 1;
set @accountSlug = 'system';
set @accountName = 'System';


-- ----------------------- 
-- Create account record
-- ----------------------- 
insert into kona__account(
    id,
    uid,
    owner_id,
    slug,
    name,
    enabled,
    verified,
    created_date
) values (
    @accountId,
    replace(uuid(),'-',''),
    @userId,
    @accountSlug,
    @accountName,
    1,
    1,
    now()
);


-- ----------------------- 
-- Create System user
-- This is strictly an internal account that's used to run
-- background jobs, etc. and has no user_auth record.
-- ----------------------- 
set @username = 'system';
set @firstName = 'System';
set @lastName = 'User';
set @displayName = 'System';

set @typeId = 100; -- SYSTEM Type
set @roleId = 100; -- SYSTEM Role


insert into kona__user(
    id, 
    uid, 
    type_id, 
    account_id,
    username,
    first_name, 
    last_name, 
    display_name,
    enabled,
    created_date
) values (
    @userId, 
    replace(uuid(),'-',''),
    @typeId, 
    @accountId,
    @username,
    @firstName, 
    @lastName, 
    @displayName, 
    1, 
    now()
);

insert into kona__user_role(
    uid,
    user_id,
    role_id
) values (
    replace(uuid(),'-',''),
    @userId,
    @roleId
);



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- // seed auth tables
-- Migration SQL that makes the change goes here.

INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'anal',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'anus',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'arse',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'ass',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'ballsack',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'balls',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bastard',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bitch',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'biatch',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bloody',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'blowjob',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'blow job',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bollock',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bollok',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'boner',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'boob',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bugger',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'bum',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'butt',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'buttplug',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'clitoris',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'cock',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'coon',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'crap',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'cunt',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'damn',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'dick',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'dildo',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'dyke',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fag',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'feck',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fellate',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fellatio',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'felching',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fuck',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'f u c k',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fudgepacker',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'fudge packer',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'flange',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'Goddamn',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'God damn',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'hell',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'homo',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'jerk',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'jizz',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'knobend',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'knob end',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'labia',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'lmao',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'lmfao',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'muff',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'nigger',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'nigga',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'omg',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'penis',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'piss',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'poop',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'prick',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'pube',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'pussy',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'queer',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'scrotum',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'sex',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'shit',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'s hit',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'sh1t',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'slut',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'smegma',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'spunk',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'tit',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'tosser',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'turd',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'twat',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'vagina',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'wank',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'whore',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'wtf',1,0,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'start',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'stop',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'begin',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'end',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'snapchat',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'instagram',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'linkedin',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'twitter',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'facebook',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'google',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'google+',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'email',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'sms',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'text',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'www',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'web',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'system',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'rpc',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'login',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'logout',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'app',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'apps',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'route',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'routes',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'admin',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'qa',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'setting',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'settings',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'docs',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'api',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'file',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'files',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'download',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'downloads',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'about',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'legal',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'terms',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'privacy',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'confirm',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'alert',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'alerts',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'threat',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'threats',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'asset',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'assets',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'promo',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'promos',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'partner',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'partners',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'account',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'user',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'jdoe',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'johndoe',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'janedoe',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'john.doe',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'jane.doe',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'service',0,1,now(),now());
INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'services',0,1,now(),now());
-- INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'sharif',0,1,now(),now());
-- INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'violet',0,1,now(),now());
-- INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'chris',0,1,now(),now());
-- INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'christopher',0,1,now(),now());
-- INSERT INTO `kona__entity_name_rule` VALUES (NULL,replace(uuid(),'-',''),'aiden',0,1,now(),now());


-- //@UNDO
-- SQL to undo the change goes here.
delete from kona__entity_name_rule;
