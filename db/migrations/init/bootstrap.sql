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
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

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
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `published_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__api_version_name` (`name`),

  UNIQUE KEY `ux_kona__api_version_uid` (`uid`),

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
  `uid` varchar(255) NOT NULL,
  `app_id` bigint(20) unsigned NOT NULL,
  `api_version_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `scope` varchar(255) NOT NULL DEFAULT 'read,write',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `access_token_timeout` int(11) unsigned DEFAULT NULL, -- seconds / needs to be integer
  `refresh_token_timeout` int(11) unsigned DEFAULT NULL, -- second / needs to be integer
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__app_creds_uid` (`uid`),

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
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
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
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
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

  FULLTEXT `ft_kona__device` (uid,type,advertiser_id,os_name,ble_mac_address,lan_mac_address,pnp_id,vendor,
  manufacturer,model,serial_no,device_uuid,capabilities)

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
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__registration_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__registration_referred_by` (`referred_by_id`),

    FULLTEXT `ft_kona__registration` (uid,username,hostname,user_agent,os_name),

  SPATIAL `ix_kona__registration_coords` (coords),

  CONSTRAINT `fk_kona__registration_device` FOREIGN KEY (`device_id`) 
        REFERENCES `kona__device` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__registration_referred_by` FOREIGN KEY (`referred_by_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__registration_app` FOREIGN KEY (`app_id`) 
        REFERENCES `kona__app` (`id`),

  CONSTRAINT `fk_kona__registration_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

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
  `token_uri` varchar(1024) DEFAULT NULL,  -- endpoint to exchange code for access token
  `scope` varchar(1024) DEFAULT NULL,
  `client_id` varchar(1024) DEFAULT NULL,
  `client_secret` varchar(1024) DEFAULT NULL,
  `redirect_uri` varchar(1024) DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__remote_service_uid` (`uid`),

  UNIQUE KEY `ux_kona__remote_service_slug` (`slug`),

  FULLTEXT `ft_kona__remote_service` (uid,name,client_id,client_secret)

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
CREATE TABLE `kona__script` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `language` varchar(255) NOT NULL,
  `return_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `body` longtext NOT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__script_uid` (`uid`),

  UNIQUE KEY `ux_kona__script_slug` (`slug`),

  FULLTEXT `ft_kona__script` (`uid`,`name`,`slug`,`body`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__short_url` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL, -- short url created for this user
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `group_id` bigint(20) unsigned DEFAULT NULL,
  `channel_id` bigint(20) unsigned DEFAULT NULL,
  `target_id` bigint(20) unsigned DEFAULT NULL,
  `reply_id` bigint(20) unsigned DEFAULT NULL,
  `reply_message_id` bigint(20) unsigned DEFAULT NULL,
  `script_id` bigint(20) unsigned DEFAULT NULL,
  `domain` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `short_url` varchar(255) NOT NULL,
  `long_url` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `single_mapped` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `channel_redirect` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `expiration_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__short_url_uid` (`uid`),

  UNIQUE KEY `ux_kona__short_url_path` (`path`),

  UNIQUE KEY `ux_kona__short_url_short_url` (`short_url`),

  KEY `ix_kona__short_url_user` (`user_id`),

  KEY `ix_kona__short_url_campaign` (`campaign_id`),

  KEY `ix_kona__short_url_group` (`group_id`),

  KEY `ix_kona__short_url_channel` (`channel_id`),

  KEY `ix_kona__short_url_target` (`target_id`),

  KEY `ix_kona__short_url_reply` (`reply_id`),

  KEY `ix_kona__short_url_reply_message` (`reply_message_id`),

  KEY `ix_kona__short_url_long_url` (`long_url`(255)),

  FULLTEXT `ft_kona__short_url` (`uid`,`domain`,`path`,`short_url`,`long_url`,`description`),

  CONSTRAINT `fk_kona__short_url_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_group` FOREIGN KEY (`group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_channel` FOREIGN KEY (`channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_target` FOREIGN KEY (`target_id`)
        REFERENCES `kona__campaign_target` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_reply` FOREIGN KEY (`reply_id`)
        REFERENCES `kona__campaign_reply` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_reply_message` FOREIGN KEY (`reply_message_id`)
        REFERENCES `kona__campaign_reply_message` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__short_url_script` FOREIGN KEY (`script_id`)
        REFERENCES `kona__script` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
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

  UNIQUE KEY `ux_kona__token_uid` (`uid`),

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
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__auth_role_uid` (`uid`),

  UNIQUE KEY `ux_kona__auth_role_slug` (`slug`),

  FULLTEXT `ft_kona__auth_role` (`uid`,`name`,`slug`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__auth_priv` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__auth_priv_uid` (`uid`),

  UNIQUE KEY `ux_kona__auth_priv_slug` (`slug`),

  FULLTEXT `ft_kona__auth_priv` (`uid`,`name`,`slug`)

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
  `type` varchar(255) DEFAULT NULL,
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
  `last_login_date` datetime(6) DEFAULT NULL,
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

  KEY `ix_kona__user_account` (`account_id`),

  FULLTEXT `ft_kona__user` (`uid`,`username`,`email`,`mobile_number`,`first_name`,`last_name`,`display_name`),


  CONSTRAINT `fk_kona__user_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_position` FOREIGN KEY (`position_id`) 
        REFERENCES `kona__position` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__user_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__user_photo` FOREIGN KEY (`photo_id`) 
        REFERENCES `kona__media` (`id`) ON DELETE SET NULL

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
  `uid` varchar(255) NOT NULL,
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

  UNIQUE KEY `ux_kona__user_auth_uid` (`uid`),

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

  KEY `ix_kona__media_user` (`user_id`),

  KEY `ix_kona__media_account` (`account_id`),

  FULLTEXT `ft_kona__media` (uid,name,slug,description,url,folder_path,url_path,thumbnail_url_path),

  SPATIAL `ix_kona__media_coords` (coords),

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

  CONSTRAINT `fk_kona__media_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__user_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__user_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__auth_code_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__auth_code_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

  UNIQUE KEY `ux_kona__entity_name_rule` (`pattern`),

  FULLTEXT `ft_kona__entity_name_rule` (uid,pattern)

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
  `owner_id` bigint(20) unsigned default NULL,
  `category_id` bigint(20) unsigned default NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `private_access` tinyint(1) unsigned DEFAULT null,
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

  `ref_place_id` varchar(255) DEFAULT NULL,
  `ref_google_url` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_ref_place_id` (`ref_place_id`),

  SPATIAL `ix_kona__place_coords` (coords),

  KEY `ix_kona__place_owner` (`owner_id`),

  KEY `ux_kona__place_category` (`category_id`),

  KEY `ix_kona__place_photo` (`photo_id`),

  FULLTEXT KEY `ft_kona_place` (`uid`, `name`, `slug`, `description`),

  CONSTRAINT `fk_kona__place_category` FOREIGN KEY (`category_id`) 
		REFERENCES `kona__place_category` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__place_owner` FOREIGN KEY (`owner_id`)
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

-- a googlePlace can have multiple plans
CREATE TABLE `kona__place_plan` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `place_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `plan` longtext DEFAULT NULL, -- JSON blob describing interior space
  `floor` int(11) unsigned DEFAULT NULL, -- indoor floor associated with map
  `perimeter` longtext DEFAULT NULL, -- JSON list of lat/lng tuples defining perimeter of space
  `encoded_perimeter` longtext DEFAULT NULL, -- encoded string of the perimeter
  `perimeter_coords` geometry NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__place_plan_uid` (`uid`),

  UNIQUE KEY `ux_kona__place_plan` (`place_id`, `slug`),

  KEY `ix_kona_place_plan_place` (`place_id`),

  SPATIAL `ix_kona__place_plan_perimeter_coords` (perimeter_coords),

  FULLTEXT KEY `ft_kona_place_plan` (`uid`,`name`,`slug`,`plan`),

  CONSTRAINT `fk_kona__place_plan_place` FOREIGN KEY (`place_id`)
		REFERENCES `kona__place` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned DEFAULT NULL,
  `place_id` bigint(20) unsigned DEFAULT NULL,
  `dwell_time` bigint(20) unsigned DEFAULT NULL, -- time (ms) a person remains in this position
  `sample_no` bigint(20) unsigned DEFAULT NULL,
  `battery` double DEFAULT NULL,
  `charging` tinyint(1) unsigned DEFAULT null,
  `network` varchar(255) DEFAULT NULL, -- wifi|cell
  `source` varchar(255) default NULL, -- gps|access-point|ip-location|beacon|mixed (location_method)
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

  KEY `ix_kona__position_device` (`device_id`),

  KEY `ix_kona__position_place` (`place_id`),

  KEY `ix_kona__position_user` (`user_id`),

  KEY `ix_kona__position_position_date` (`position_date`),

  KEY `ix_kona__position_dwell_time` (`dwell_time`),

  KEY `ix_kona__position_horizontal_accuracy` (`horizontal_accuracy`),

  SPATIAL `ix_kona__position_coords` (coords),

  CONSTRAINT `fk_kona__position_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__device` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__position_place` FOREIGN KEY (`place_id`) 
        REFERENCES `kona__place` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__position_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------
--
--CREATE TABLE `kona__notification_channel` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__notification_channel_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `event` longtext default NULL,
  `event_date` datetime(6) default NULL,
  `last_viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_kona__notification_uid` (`uid`),

  KEY `ix_kona__notification_user` (`user_id`),

  FULLTEXT KEY `ft_kona_notification` (`uid`,`event`),

  CONSTRAINT `fk_kona__notification_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__notification_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `notification_id` bigint(20) unsigned NOT NULL,
  `channel` varchar(255) NOT NULL,
  `view_count` int(11) NOT NULL DEFAULT '0',
  `sent_date` datetime(6) default NULL,
  `viewed_date` datetime(6) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE `ux_kona__notification_delivery_uid` (`uid`),

  KEY `ux_kona__notification_delivery_notification` (`notification_id`),

  FULLTEXT KEY `ft_kona_notification_delivery` (`channel`,`uid`),

  CONSTRAINT `fk_kona__notification_delivery_notification` FOREIGN KEY (`notification_id`)
        REFERENCES `kona__notification` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__support_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
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

  FULLTEXT KEY `ft_kona_support_message` (uid,first_name,last_name,email,mobile_number,message),

  CONSTRAINT `fk_kona__support_message_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL 

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__sms` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_group_id` bigint(20) unsigned DEFAULT NULL,
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

  UNIQUE KEY `ix_kona__sms_campaign_channel_to` (`campaign_channel_id`,`to_number`),

  KEY `ix_kona__sms_campaign` (`campaign_id`),

  KEY `ix_kona__sms_campaign_group` (`campaign_group_id`),

  KEY `ix_kona__sms_to_user` (`to_user_id`),

  KEY `ix_kona__sms_to_number` (`to_number`),

  FULLTEXT KEY `ft_kona_sms` (uid,to_number,from_number,message,media_urls,message_sid,status,error_code,error_message),

  CONSTRAINT `fk_kona__sms_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__sms_campaign_group` FOREIGN KEY (`campaign_group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__sms_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__sms_to_user` FOREIGN KEY (`to_user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------

CREATE TABLE `kona__email` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_group_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `email_group_id` bigint(20) unsigned DEFAULT NULL,
  `email_address_id` bigint(20) unsigned DEFAULT NULL,
  `email_content_id` bigint(20) unsigned DEFAULT NULL,
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

  UNIQUE KEY `ix_kona__email_campaign_channel_address` (`campaign_channel_id`,`email_address_id`),

  KEY `ix_kona__email_campaign` (`campaign_id`),

  KEY `ix_kona__email_campaign_group` (`campaign_group_id`),

  KEY `ix_kona__email_email_group` (`email_group_id`),

  KEY `ix_kona__email_email_address` (`email_address_id`),

  KEY `ix_kona__email_email_content` (`email_content_id`),

  FULLTEXT KEY `ft_kona_email` (uid,ses_id,from_address,to_address,subject),

  CONSTRAINT `fk_kona__email_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_campaign_group` FOREIGN KEY (`campaign_group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_email_content` FOREIGN KEY (`email_content_id`)
        REFERENCES `kona__email_content` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_email_group` FOREIGN KEY (`email_group_id`)
        REFERENCES `kona__email_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__email_email_address` FOREIGN KEY (`email_address_id`)
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
  `birth_year` int(11) unsigned default NULL,
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

  FULLTEXT KEY `ft_kona_email_address` (uid,first_name,last_name,email,mobile_number,source,postal_code),

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

  FULLTEXT KEY `ft_kona_email_content` (uid,text,html),

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
  `uid` varchar(255) NOT NULL,
  `email_id` bigint(20) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `target` varchar(2000) DEFAULT NULL,
  `error` varchar(2000) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `event_date` datetime(6) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_event_uid` (`uid`),

  KEY `ix_kona__email_event_email` (`email_id`),

  FULLTEXT KEY `ft_kona_email_event` (uid,type,target,error,user_agent),

  CONSTRAINT `fk_kona__email_event_email` FOREIGN KEY (`email_id`) 
        REFERENCES `kona__email` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__email_event_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(64) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

  UNIQUE KEY `ux_kona__email_group_slug` (`slug`),

  FULLTEXT KEY `ft_kona_email_group` (uid,name,slug)

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
  `provider_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned NOT NULL,
  `platform` varchar(255) NOT NULL, -- should match value in push
  `token` varchar(1024) DEFAULT NULL,
  `endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__push_device_uid` (`uid`),

  -- different users using same app on same device
  UNIQUE KEY `ux_kona__push_device` (`user_id`,`device_id`,`sandbox`),

  UNIQUE KEY `ux_kona__push_device_token` (`token`(255)),

  KEY `ix_kona__push_device_user` (`user_id`),

  KEY `ix_kona__push_device_provider` (`provider_id`),

  KEY `ix_kona__push_device_device` (`device_id`),

  FULLTEXT KEY `ft_kona_push_device` (uid,platform,token,endpoint),

  CONSTRAINT `fk_kona__push_device_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__device` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device_provider` FOREIGN KEY (`provider_id`)
        REFERENCES `kona__push_provider` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__push_provider` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `platform` varchar(255) NOT NULL,
  `server_key` longtext DEFAULT NULL,
  `server_secret` longtext DEFAULT NULL,
  `endpoint` varchar(1024) DEFAULT NULL,
  `sandbox` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__push_provider_uid` (`uid`),

  UNIQUE KEY `ux_kona__push_provider` (`platform`,`sandbox`),

  FULLTEXT KEY `ft_kona_push_provider` (uid,platform,server_key,server_secret,endpoint)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__push` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` bigint(20) unsigned NOT NULL,
  `campaign_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_group_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `platform` varchar(255) NOT NULL,
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

  UNIQUE KEY `ix_kona__push_campaign_channel_device` (`campaign_channel_id`,`device_id`),

  KEY `ix_kona__push_campaign` (`campaign_id`),

  KEY `ix_kona__push_campaign_group` (`campaign_group_id`),

  KEY `ix_kona__push_user` (`user_id`),

  KEY `ix_kona__push_device` (`device_id`),

  FULLTEXT KEY `ft_kona_push` (uid,platform,provider_message_id,title,message,status,error_code,error_message),

  CONSTRAINT `fk_kona__push_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__push_campaign_group` FOREIGN KEY (`campaign_group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__push_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__push_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__push_device` FOREIGN KEY (`device_id`)
        REFERENCES `kona__push_device` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `friend_id` bigint(20) unsigned DEFAULT NULL,
  `circle_id` bigint(20) unsigned DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `friendship_requested` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__friendship_uid` (`uid`),

  UNIQUE KEY `ux_kona__friendship_user_friend` (`user_id`,`friend_id`),

  KEY `ix_kona__friendship_status` (`status`),

  KEY `ix_kona__friendship_user` (`user_id`),

  KEY `ix_kona__friendship_friend` (`friend_id`),

  KEY `ix_kona__friendship_circle` (`circle_id`),

  CONSTRAINT `fk_kona__friendship_friend` FOREIGN KEY (`friend_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_circle` FOREIGN KEY (`circle_id`)
        REFERENCES `kona__friendship_circle` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__friendship_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_event` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
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

  KEY `ix_kona__friendship_event_friendship` (`friendship_id`),

  KEY `ix_kona__friendship_event_user` (`user_id`),

  KEY `ix_kona__friendship_event_friend` (`friend_id`),

  FULLTEXT KEY `ft_kona_friendship_event` (uid,event),

  CONSTRAINT `fk_kona__friendship_event_friend` FOREIGN KEY (`friend_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_event_friendship` FOREIGN KEY (`friendship_id`) 
        REFERENCES `kona__friendship` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__friendship_event_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__friendship_event_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__friendship_event_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--CREATE TABLE `kona__friendship_status` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__friendship_status_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__friendship_circle` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
      `uid` varchar(255) NOT NULL,
      `user_id` bigint(20) unsigned NOT NULL,
      `name` varchar(255) NOT NULL,
      `slug` varchar(255) NOT NULL,
      `default_circle` tinyint(1) unsigned NOT NULL DEFAULT '0',
      `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
      `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

      PRIMARY KEY (`id`),

      UNIQUE KEY `id` (`id`),
    
      UNIQUE KEY `ux_kona__friendship_circle_uid` (`uid`),

      UNIQUE KEY `ux_kona__friendship_circle_slug` (`user_id`,`slug`),

        FULLTEXT KEY `ft_kona_friendship_circle` (uid,name,slug),

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

  FULLTEXT KEY `ft_kona_contact` (uid,first_name,last_name,display_name,email,mobile_number,social_handles),

  CONSTRAINT `fk_kona__contact_photo` FOREIGN KEY (`photo_id`)
    REFERENCES `kona__media` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__contact_ref_user` FOREIGN KEY (`ref_user_id`)
    REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__contact_owner` FOREIGN KEY (`owner_id`)
    REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__invitation_channel` (
--    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--    `name` varchar(255) DEFAULT NULL,
--    `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--    `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--    PRIMARY KEY (`id`),
--
--    UNIQUE KEY `ux_kona__invitation_channel_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--CREATE TABLE `kona__invitation_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__invitation_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--CREATE TABLE `kona__invitation_status` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) DEFAULT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `ux_kona__invitation_status_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__invitation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `channel` varchar(255) NOT NULL,
  `status`  varchar(255) NOT NULL,
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

  KEY `ix_kona__invitation_owner` (`owner_id`),

  KEY `ix_kona__invitation_contact` (`contact_id`),

  KEY `ix_kona__invitation_invitee_user` (`invitee_user_id`),

  FULLTEXT `ft_kona__invitation` (uid,type,channel,status,invitation_code,message,email,mobile_number,first_name,last_name,display_name),

  CONSTRAINT `fk_kona__invitation_contact` FOREIGN KEY (`contact_id`)
        REFERENCES `kona__contact` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invitation_invitee_user` FOREIGN KEY (`invitee_user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invitation_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__payment_account_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(32) NOT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__payment_account_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `default_account` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `max_transaction_amount` decimal(10,2) DEFAULT NULL,
  `max_transaction_count` int(11) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) default NULL,
  `provider_name` varchar(255) DEFAULT NULL,
  `provider_slug` varchar(255) DEFAULT NULL,
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

  UNIQUE KEY `ux_kona__payment_account_slug` (`account_id`, `slug`),

    -- this should be true but beware of possible conflicts
  UNIQUE KEY `ux_kona__payment_account_provider_customer_id` (`provider_slug`, `provider_customer_id`),

    -- this should be true but beware of possible conflicts
  UNIQUE KEY `ux_kona__payment_account_account_number` (`provider_slug`, `account_number`),

  KEY `ix_kona__payment_account_account` (`account_id`),

  KEY `ix_kona__payment_account_owner` (`owner_id`),

  FULLTEXT `ft_kona__payment_account` (uid,type,name,slug,provider_name,provider_customer_id,card_token,
    card_last4,card_type,account_holder,account_number,routing_number),

  CONSTRAINT `fk_kona__payment_account_account` FOREIGN KEY (`account_id`)
        REFERENCES `kona__account` (`id`),

  CONSTRAINT `fk_kona__payment_account_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `kpi` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `conversion_count` int(11) unsigned NOT NULL default '0',
  `conversion_target` int(11) unsigned NOT NULL default '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_slug` (`slug`),

  UNIQUE KEY `ux_kona__campaign_uid` (`uid`),

  KEY `ix_kona__campaign_owner` (`owner_id`),

  FULLTEXT `ft_kona__campaign` (`uid`,`goal`,`kpi`,`name`,`slug`, `description`),

  CONSTRAINT `fk_kona__campaign_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `conversion_count` int(11) unsigned NOT NULL default '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__campaign_group_uid` (`uid`),

  KEY `ix_kona__campaign_group_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_group_partner` (`partner_id`),

  FULLTEXT `ft_kona__campaign_group` (uid,name,description),

  CONSTRAINT `fk_kona__campaign_group_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_partner` FOREIGN KEY (`partner_id`)
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `promo_code` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `target_strategy` varchar(255) NOT NULL, -- all targets must be of the same type
  `reply_strategy` varchar(255) NOT NULL, -- all targets must be of the same type

  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,

  `adwords_keywords` varchar(4000) DEFAULT NULL,

  `sms_number` varchar(255) DEFAULT NULL,
  `sms_keyword` varchar(255) DEFAULT NULL,

    -- short url responsible for target_strategy redirect
  `short_url` varchar(255) DEFAULT NULL,

  `conversion_count` int(11) unsigned NOT NULL default '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `reply_enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_channel_uid` (`uid`),

  UNIQUE KEY `ux_kona__campaign_channel_slug` (`group_id`, `slug`),

  UNIQUE KEY `ux_kona__campaign_channel_sms_number` (`sms_number`, `sms_keyword`),

  UNIQUE KEY `ux_kona__campaign_channel_promo_code` (`promo_code`),

  UNIQUE KEY `ux_kona__campaign_channel_short_url` (`short_url`),

  KEY `ix_kona__campaign_channel_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_channel_group` (`group_id`),

  FULLTEXT `ft_kona__campaign_channel` (uid,type,target_strategy,reply_strategy,name,slug,adwords_keywords,sms_keyword,sms_number,short_url),

  CONSTRAINT `fk_kona__campaign_channel_campaign` FOREIGN KEY (`campaign_id`)
    REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_channel_group` FOREIGN KEY (`group_id`)
    REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_channel_promo_code` FOREIGN KEY (`promo_code`)
    REFERENCES `kona__promo_code` (`promo_code`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_target_short_url` FOREIGN KEY (`short_url`)
    REFERENCES `kona__short_url` (`short_url`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

-- all the targets of a channel should be the same type
-- purpose of multiple targets per channel is to do A/B testing
-- target is dynamically chosen based on channel target_strategy

CREATE TABLE `kona__campaign_target` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,

  `landing_page_id` bigint(20) unsigned DEFAULT NULL,

  -- include all query params to uniquely identify this target
  `url` varchar(255) DEFAULT NULL,

  `analytics_tracking_id` varchar(255) DEFAULT NULL,
  `conversion_pixel` varchar(2000) DEFAULT NULL,
  `conversion_count` int(11) unsigned NOT NULL default '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_target_uid` (`uid`),

  UNIQUE KEY `ux_kona__campaign_target_slug` (`channel_id`, `slug`),

  UNIQUE KEY `ux_kona__campaign_target_url` (`url`),

  KEY `ix_kona__campaign_channel_landing_target_page` (`landing_page_id`),

  KEY `ix_kona__campaign_target_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_target_group` (`group_id`),

  KEY `ix_kona__campaign_target_channel` (`channel_id`),

  FULLTEXT `ft_kona__campaign_target` (uid,type,name,slug,url,analytics_tracking_id),

  CONSTRAINT `fk_kona__campaign_target_campaign` FOREIGN KEY (`campaign_id`)
    REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_target_group` FOREIGN KEY (`group_id`)
    REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_target_channel` FOREIGN KEY (`channel_id`)
    REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_target_landing_page` FOREIGN KEY (`landing_page_id`)
    REFERENCES `kona__landing_page` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

-- a reply is the message sent to a user upon a campaign conversion
-- a channel can have multiple replies for A/B testing
-- a reply may be associated with a specific target_id
-- replies are dynamically selected based on channel reply_strategy

CREATE TABLE `kona__campaign_reply` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `target_id` bigint(20) unsigned default NULL,

  `type` varchar(255) NOT NULL, -- email|sms|push
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,

  `subject` varchar(255) default NULL,
  `content` longtext NOT NULL, -- template

  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_reply_uid` (`uid`),

  UNIQUE KEY `ux_kona__campaign_reply_slug` (`target_id`, `slug`),

  KEY `ix_kona__campaign_reply_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_reply_group` (`group_id`),

  KEY `ix_kona__campaign_reply_channel` (`channel_id`),

  KEY `ix_kona__campaign_reply_target` (`target_id`),

  FULLTEXT `ft_kona__campaign_reply` (uid,type,name,slug,subject,content),

  CONSTRAINT `fk_kona__campaign_reply_campaign` FOREIGN KEY (`campaign_id`)
    REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_group` FOREIGN KEY (`group_id`)
    REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_channel` FOREIGN KEY (`channel_id`)
    REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_target` FOREIGN KEY (`target_id`)
    REFERENCES `kona__campaign_target` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- a reply is the message sent to a user upon a campaign conversion
-- a target can have multiple replies for A/B testing

CREATE TABLE `kona__campaign_reply_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `target_id` bigint(20) unsigned NOT NULL,
  `reply_id` bigint(20) unsigned NOT NULL,

  `to_user_id` bigint(20) unsigned default NULL,
  `to_email` varchar(255) default NULL,
  `to_mobile_number` varchar(255) default NULL,

  `email_id` bigint(20) unsigned default NULL,
  `sms_id` bigint(20) unsigned default NULL,
  `push_id` bigint(20) unsigned default NULL,

  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_reply_message_uid` (`uid`),

  UNIQUE KEY `ux_kona__campaign_reply_message_email` (`email_id`),

  UNIQUE KEY `ux_kona__campaign_reply_message_sms` (`sms_id`),

  UNIQUE KEY `ux_kona__campaign_reply_message_push` (`push_id`),

  KEY `ix_kona__campaign_reply_campaign` (`campaign_id`),

  KEY `ix_kona__campaign_reply_group` (`group_id`),

  KEY `ix_kona__campaign_reply_channel` (`channel_id`),

  KEY `ix_kona__campaign_reply_target` (`target_id`),

  KEY `ix_kona__campaign_reply_to_user` (`to_user_id`),

  KEY `ix_kona__campaign_reply_to_email` (`to_email`),

  KEY `ix_kona__campaign_reply_to_mobile_number` (`to_mobile_number`),

  FULLTEXT `ft_kona__campaign_reply_message` (uid,to_email,to_mobile_number),

  CONSTRAINT `fk_kona__campaign_reply_message_campaign` FOREIGN KEY (`campaign_id`)
    REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_message_group` FOREIGN KEY (`group_id`)
    REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_message_channel` FOREIGN KEY (`channel_id`)
    REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_message_target` FOREIGN KEY (`target_id`)
    REFERENCES `kona__campaign_target` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_reply_message_email` FOREIGN KEY (`email_id`)
    REFERENCES `kona__email` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_reply_message_sms` FOREIGN KEY (`sms_id`)
    REFERENCES `kona__sms` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_reply_message_push` FOREIGN KEY (`push_id`)
    REFERENCES `kona__push` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_reply_message_to_user` FOREIGN KEY (`to_user_id`)
    REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__campaign_analytics` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `target_id` bigint(20) unsigned NOT NULL,
  `reply_id` bigint(20) unsigned DEFAULT NULL, -- if set, this is a post conversion event
  `reply_message_id` bigint(20) unsigned DEFAULT NULL, -- if set, this is a post conversion event
  `category` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `label` varchar(2000) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `conversion_event` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `conversion_user_id` bigint(20) unsigned DEFAULT NULL,
  `conversion_email` varchar(255) DEFAULT NULL,
  `conversion_mobile_number` varchar(255) DEFAULT NULL,
  `source_mobile_number` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `user_agent` varchar(512) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `utc_offset` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `coords` geometry NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__campaign_analytics_uid` (`uid`),

  KEY `ix_kona__campaign_analytics_campaign` (`channel_id`),
  KEY `ix_kona__campaign_analytics_group` (`group_id`),
  KEY `ix_kona__campaign_analytics_channel` (`channel_id`),
  KEY `ix_kona__campaign_analytics_target` (`target_id`),
  KEY `ix_kona__campaign_analytics_reply` (`reply_id`),
  KEY `ix_kona__campaign_analytics_reply_message` (`reply_message_id`),

  KEY `ix_kona__campaign_analytics_conversion_user` (`conversion_user_id`),

  FULLTEXT `ft_kona__campaign_analytics` (uid,category,action,label,source_mobile_number,source_url,hostname,user_agent,conversion_email,conversion_mobile_number),

  SPATIAL `ix_kona__campaign_analytics_coords` (coords),

  CONSTRAINT `fk_kona__campaign_analytics_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_analytics_group` FOREIGN KEY (`group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_analytics_channel` FOREIGN KEY (`channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_analytics_target` FOREIGN KEY (`target_id`)
        REFERENCES `kona__campaign_target` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__campaign_analytics_reply` FOREIGN KEY (`reply_id`)
        REFERENCES `kona__campaign_reply` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_analytics_reply_message` FOREIGN KEY (`reply_message_id`)
        REFERENCES `kona__campaign_reply_message` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__campaign_analytics_conversion_user` FOREIGN KEY (`conversion_user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------
--
--CREATE TABLE `kona__campaign_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(255) NOT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__campaign_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__cart_account` (`account_id`),

  KEY `ix_kona__cart_user` (`user_id`),

  KEY `ix_kona__cart_token` (`token_id`),

  KEY `ix_kona__cart_invoice` (`invoice_id`),

  KEY `ix_kona__cart_currency` (`currency_id`),

  FULLTEXT `ft_kona__cart` (uid),

  CONSTRAINT `fk_kona__cart_token` FOREIGN KEY (`token_id`)
        REFERENCES `kona__token` (`id`) on DELETE SET NULL,

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
  `product_sku_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__cart_item_product_sku` (`product_sku_id`),

  KEY `ix_kona__cart_item_promo` (`promo_id`),

  CONSTRAINT `fk_kona__cart_item_cart` FOREIGN KEY (`cart_id`) 
        REFERENCES `kona__cart` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__cart_item_promo` FOREIGN KEY (`promo_id`) 
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__cart_item_product_sku` FOREIGN KEY (`product_sku_id`)
        REFERENCES `kona__product_sku` (`id`) ON DELETE CASCADE

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
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__invoice_user` (`user_id`),

  KEY `ix_kona__invoice_account` (`account_id`),

  KEY `ix_kona__invoice_currency` (`currency_id`),

  KEY `ix_kona__invoice_promo` (`promo_id`),

  KEY `ix_kona__invoice_campaign_channel` (`campaign_channel_id`),

  FULLTEXT `ft_kona__invoice` (uid,invoice_no,payment_card_last4, payment_ref,notes),

  CONSTRAINT `fk_kona__invoice_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__invoice_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invoice_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo` (`id`),

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
  `product_sku_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__invoice_item_product_sku` (`product_sku_id`),

  KEY `ix_kona__invoice_item_promo` (`promo_id`),

  KEY `ix_kona__invoice_item_campaign_channel` (`campaign_channel_id`),

  CONSTRAINT `fk_kona__invoice_item_invoice` FOREIGN KEY (`invoice_id`) 
        REFERENCES `kona__invoice` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__invoice_item_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__invoice_item_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo` (`id`),

  CONSTRAINT `fk_kona__invoice_item_product_sku` FOREIGN KEY (`product_sku_id`)
        REFERENCES `kona__product_sku` (`id`)

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

  FULLTEXT `ft_kona__partner` (uid,name,description,email,phone_number,social_handles,contact_first_name,
    contact_last_name,contact_phone_number,contact_mobile_number),

  CONSTRAINT `fk_kona__partner_place` FOREIGN KEY (`place_id`) 
        REFERENCES `kona__place` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__partner_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__payment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `token_id` bigint(20) unsigned DEFAULT NULL,
  `payment_account_id` bigint(20) unsigned DEFAULT NULL,
  `currency_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
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
  `processor_receipt` longtext default null,
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

  KEY `ix_kona__payment_currency` (`currency_id`),

  KEY `ix_kona__payment_account` (`account_id`),

  KEY `ix_kona__payment_user` (`user_id`),

  KEY `ix_kona__payment_token` (`token_id`),

  KEY `ix_kona__payment_invoice` (`invoice_id`),

  KEY `ix_kona__payment_promo` (`promo_id`),

  KEY `ix_kona__payment_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__payment_payment_account` (`payment_account_id`),

  FULLTEXT `ft_kona__payment` (uid,type,status,card_token,card_last4,processor_ref,processor_error),

  SPATIAL `ix_kona__payment_coords` (coords),

  CONSTRAINT `fk_kona__payment_account` FOREIGN KEY (`account_id`) 
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_currency` FOREIGN KEY (`currency_id`)
        REFERENCES `kona__currency` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_invoice` FOREIGN KEY (`invoice_id`) 
        REFERENCES `kona__invoice` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__payment_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__payment_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__payment_payment_account` FOREIGN KEY (`payment_account_id`) 
        REFERENCES `kona__payment_account` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__payment_token` FOREIGN KEY (`token_id`)
        REFERENCES `kona__payment_token` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__payment_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

--CREATE TABLE `kona__payment_status` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(32) NOT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__payment_status_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--CREATE TABLE `kona__payment_type` (
--  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
--  `name` varchar(32) NOT NULL,
--  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
--  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
--
--  PRIMARY KEY (`id`),
--
--  UNIQUE KEY `id` (`id`),
--
--  UNIQUE KEY `ux_kona__payment_type_name` (`name`)
--
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__pre_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `product_sku_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `payment_id` bigint(20) unsigned DEFAULT NULL,
  `referred_by_id` bigint(20) unsigned DEFAULT NULL,
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

  KEY `ix_kona__pre_order_referred_by` (`referred_by_id`),

  KEY `ix_kona__pre_order_partner` (`partner_id`),

  KEY `ix_kona__pre_order_payment` (`payment_id`),

  KEY `ix_kona__pre_order_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__pre_order_product_sku` (`product_sku_id`),

  FULLTEXT `ft_kona__pre_order` (uid,payment_card_last4,payment_ref,first_name,last_name,email,mobile_number,
        shipping_address1,shipping_address2,shipping_city,shipping_postal_code),

  CONSTRAINT `fk_kona__pre_order_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_payment` FOREIGN KEY (`payment_id`) 
        REFERENCES `kona__payment` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_product_sku` FOREIGN KEY (`product_sku_id`)
        REFERENCES `kona__product_sku` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__pre_order_referred_by` FOREIGN KEY (`referred_by_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__promo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
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

  UNIQUE KEY `ux_kona__promo_slug` (`slug`),

  FULLTEXT `ft_kona_promo` (uid,name,slug,description)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- a Promo can have more than one code
-- Each code must be unique
-- Each code maps exactly to a single campaign channel

CREATE TABLE `kona__promo_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `promo_id` bigint(20) unsigned NOT NULL,
  `campaign_channel_id` bigint(20) unsigned NOT NULL,
  `promo_code` varchar(255) NOT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__promo_code_uid` (`uid`),

  UNIQUE KEY `ix_kona__promo_code_promo_code` (`promo_code`),

  UNIQUE KEY `ux_kona__promo_code_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__promo_code_promo` (`promo_id`),

  FULLTEXT `ft_kona_promo_code` (uid,promo_code),

  CONSTRAINT `fk_kona__promo_code_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__promo_code_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo_code_promo` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- promo can apply to one more products or to all products

CREATE TABLE `kona__promo_product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `promo_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned default NULL,
  `product_category_id` bigint(20) unsigned default NULL,
  `product_sku_id` bigint(20) unsigned default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__promo_product_uid` (`uid`),

  KEY `ix_kona__promo_product_promo` (`promo_id`),

  KEY `ix_kona__promo_product_product` (`product_id`),

  KEY `ix_kona__promo_product_product_category` (`product_category_id`),

  KEY `ix_kona__promo_product_product_sku` (`product_sku_id`),

  CONSTRAINT `fk_kona__promo_product_product_category` FOREIGN KEY (`product_category_id`)
        REFERENCES `kona__product_category` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__promo_product_product` FOREIGN KEY (`product_id`)
        REFERENCES `kona__product` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__promo_product_product_sku` FOREIGN KEY (`product_sku_id`)
        REFERENCES `kona__product_sku` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__promo_product_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo_code_promo` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__sales_lead` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `channel_id` bigint(20) unsigned NOT NULL,
  `target_id` bigint(20) unsigned NOT NULL,
  `analytics_id` bigint(20) unsigned NOT NULL,
  `campaign_conversion` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `referred_by_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL, -- possible email/mobile_number belongs to existing user
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `social_handles` varchar(2000) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `utc_offset` int(11) DEFAULT NULL,
  `formatted_address` varchar(512) DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  `interests` varchar(2000) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__sales_lead_uid` (`uid`),

  KEY `ix_kona__sales_lead_referred_by` (`referred_by_id`),

  KEY `ix_kona__sales_lead_user` (`user_id`),

  KEY `ix_kona__sales_lead_campaign` (`channel_id`),

  KEY `ix_kona__sales_lead_group` (`group_id`),

  KEY `ix_kona__sales_lead_channel` (`channel_id`),

  KEY `ix_kona__sales_lead_target` (`target_id`),

  KEY `ix_kona__sales_lead_email` (`email`),

  KEY `ix_kona__sales_lead_mobile_number` (`mobile_number`),

  KEY `ix_kona__sales_lead_phone_number` (`phone_number`),

  FULLTEXT `ft_kona_sales_lead` (uid,first_name,last_name,email,phone_number,mobile_number,social_handles,message,interests),

  CONSTRAINT `fk_kona__sales_lead_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_group` FOREIGN KEY (`group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_channel` FOREIGN KEY (`channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_target` FOREIGN KEY (`target_id`)
        REFERENCES `kona__campaign_target` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_referred_by` FOREIGN KEY (`referred_by_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT `fk_kona__sales_lead_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------------------------

CREATE TABLE `kona__product_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(2000) default NULL,
  `display_order` int(11) unsigned DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__product_category_uid` (`uid`),

  UNIQUE KEY `ux_kona__product_category_slug` (`slug`),

  KEY `ix_kona__product_category_parent` (`parent_id`),

  FULLTEXT `ft_kona_product_category` (uid,name,slug,description),

  CONSTRAINT `fk_kona__product_category_parent` FOREIGN KEY (`parent_id`)
        REFERENCES `kona__product_category` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `display_order` int(11) unsigned DEFAULT NULL,
  `subscription` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__product_uid` (`uid`),

  UNIQUE KEY `ux_kona__product_slug` (`slug`),

  FULLTEXT `ft_kona_product` (uid,name,description)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------
-- product should have a different sku for every variant of the product
-- e.g. product: T-Shirt  variants: color, size
CREATE TABLE `kona__product_sku` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL, -- Monthly Subscription
  `sku` varchar(255) NOT NULL, -- subscription-days-30
  `variants` varchar(2000) default NULL, -- json object
  `description` varchar(2000) default NULL,
  `display_order` int(11) unsigned DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `setup_fee` decimal(10,2) DEFAULT NULL,
  `trial_days` int(11) DEFAULT NULL,
  `subscription` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `subscription_days` int(11) DEFAULT NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `id` (`id`),

  UNIQUE KEY `ux_kona__product_sku_uid` (`uid`),

  UNIQUE KEY `ux_kona__product_sku_sku` (`sku`),

  KEY `ix_kona__product_sku_product` (`product_id`),

  FULLTEXT `ft_kona_product_sku` (uid,name,sku,variants,description),

  CONSTRAINT `fk_kona__product_sku_product` FOREIGN KEY (`product_id`)
        REFERENCES `kona__product` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

CREATE TABLE `kona__purchase` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `account_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `product_sku_id` bigint(20) unsigned NOT NULL,
  `promo_id` bigint(20) unsigned DEFAULT NULL,
  `partner_id` bigint(20) unsigned DEFAULT NULL,
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL,
  `invoice_id` bigint(20) unsigned DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL, -- Payment.Type
  `kind` varchar(255) DEFAULT NULL, -- field used by google play
  `quantity` int(11) NOT NULL DEFAULT '1',
  `auto_renew` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `expiration_date` datetime(6) DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__purchase_uid` (`uid`),

  KEY `ix_kona__purchase_parent` (`parent_id`),

  KEY `ix_kona__purchase_account` (`account_id`),

  KEY `ix_kona__purchase_user` (`user_id`),

  KEY `ix_kona__purchase_product` (`product_id`),

  KEY `ix_kona__purchase_product_sku` (`product_sku_id`),

  KEY `ix_kona__purchase_promo` (`promo_id`),

  KEY `ix_kona__purchase_partner` (`partner_id`),

  KEY `ix_kona__purchase_campaign_channel` (`campaign_channel_id`),

  KEY `ix_kona__purchase_invoice` (`invoice_id`),

  CONSTRAINT `fk_kona__purchase_account` FOREIGN KEY (`account_id`)
        REFERENCES `kona__account` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__purchase_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__purchase_invoice` FOREIGN KEY (`invoice_id`)
        REFERENCES `kona__invoice` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__purchase_parent` FOREIGN KEY (`parent_id`) 
        REFERENCES `kona__purchase` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__purchase_partner` FOREIGN KEY (`partner_id`) 
        REFERENCES `kona__partner` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__purchase_promo` FOREIGN KEY (`promo_id`)
        REFERENCES `kona__promo` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__purchase_product` FOREIGN KEY (`product_id`) 
        REFERENCES `kona__product` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__purchase_product_sku` FOREIGN KEY (`product_sku_id`)
        REFERENCES `kona__product_sku` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__purchase_user` FOREIGN KEY (`user_id`) 
        REFERENCES `kona__user` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned default NULL,
  `file_id` bigint(20) unsigned default NULL,  -- should be not null but object is created first, then file is uploaded
  `url_path` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_template_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_template_slug` (`slug`),

  KEY `ix_kona__landing_page_template_owner` (`owner_id`),

  KEY `ix_kona__landing_page_template_file` (`file_id`),

  FULLTEXT `ft_kona__landing_page_template` (`uid`,`name`, `slug`, `description`),

  CONSTRAINT `fk_kona__landing_page_template_owner` FOREIGN KEY (`owner_id`)
		REFERENCES `kona__user` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_template_file` FOREIGN KEY (`file_id`)
        REFERENCES `kona__file` (`id`) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------------------------

CREATE TABLE `kona__landing_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE `ux_kona__landing_page_uid` (`uid`),

  UNIQUE `ux_kona__landing_page_slug` (`slug`),

  KEY `ix_kona__landing_page_template` (`template_id`),

  FULLTEXT `ft_kona__landing_page` (`uid`,`name`, `description`),

  CONSTRAINT `fk_kona__landing_page_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__landing_page_template` (`id`) ON DELETE RESTRICT

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

-- the reason this does not follow name/slug is because the name parameter may yet need to be turned
-- into a slug by the app.  For example, the user might create a parameter called API_KEY, the display name
-- might be called API Key, and the app may need to create a slug called api-key

CREATE TABLE `kona__landing_page_template_param` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `template_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL, -- actual parameter name (i.e. background-color, css.background.color, etc.)
  `slug` varchar(255) NOT NULL, -- help disambiguate similar param names (e.g. background_color and background-color)
  `display_name` varchar(255) NOT NULL, -- NOTE: this does not follow the usual name/slug pattern
  `type` varchar(255) NOT NULL,
  `required` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_template_param_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_template_param_slug` (`template_id`, `slug`),

  KEY `ix_kona__landing_page_template_param_template` (`template_id`),

  CONSTRAINT `fk_kona__landing_page_template_param_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__landing_page_template` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------------------------

--#
--# Appears to be a bug with MySQL driver and MyBatis where BLOB columns (specifically LONGTEXT) columns that are null
--# are not returned when selectByExampleWithBLOBs is called.
--#
--# Caught: org.mybatis.spring.MyBatisSystemException:
--#       nested exception is org.apache.ibatis.executor.result.ResultMapException:
--#       Error attempting to get column 'param_value' from result set.  Cause: java.lang.ArrayIndexOutOfBoundsException
--#
--
--# mariadb JDBC driver seems to work fine.

CREATE TABLE `kona__landing_page_param` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `landing_page_id` bigint(20) unsigned NOT NULL,
  `template_param_id` bigint(20) unsigned NOT NULL,
  `file_id` bigint(20) unsigned default NULL,
  `value` longtext default NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__landing_page_param_uid` (`uid`),

  UNIQUE KEY `ux_kona__landing_page_param_page_template_param` (`landing_page_id`, `template_param_id`),

  KEY `ix_kona__landing_page_param_page` (`landing_page_id`),

  KEY `ix_kona__landing_page_param_template_param` (`template_param_id`),

  CONSTRAINT `fk_kona__landing_page_param_file` FOREIGN KEY (`file_id`)
        REFERENCES `kona__file` (`id`) ON DELETE SET NULL,

  CONSTRAINT `fk_kona__landing_page_param_page` FOREIGN KEY (`landing_page_id`)
        REFERENCES `kona__landing_page` (`id`) ON DELETE CASCADE,

  CONSTRAINT `fk_kona__landing_page_param_template_param` FOREIGN KEY (`template_param_id`)
        REFERENCES `kona__landing_page_template_param` (`id`) ON DELETE CASCADE

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
