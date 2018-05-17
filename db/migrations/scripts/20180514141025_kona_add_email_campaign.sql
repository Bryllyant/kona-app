--
--    Copyright 2010-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

-- // kona_add_email_campaign
-- Migration SQL that makes the change goes here.

CREATE TABLE `kona__email_campaign` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL,
  `campaign_id` bigint(20) unsigned NOT NULL,
  `campaign_group_id` bigint(20) unsigned NOT NULL,
  `campaign_channel_id` bigint(20) unsigned NOT NULL,
  `email_group_id` bigint(20) unsigned NOT NULL,
  `email_content_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `status` varchar(255) NOT NULL,
  `from_address` varchar(255) NOT NULL,
  `reply_to` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,

  	failed_count double default 0.0,
	failed_rate double default 0.0,
	delivered_count double default 0.0,
	delivered_rate double default 0.0,
	bounced_count double default 0.0,
	bounced_rate double default 0.0,
	complained_count double default 0.0,
	complained_rate double default 0.0,
	opted_out_count double default 0.0,
	opted_out_rate double default 0.0,
	opened_count double default 0.0,
	opened_all_rate double default 0.0,
	clicked_count double default 0.0,
	clicked_all_rate double default 0.0,
	printed_count double default 0.0,
	printed_all_rate double default 0.0,
	forwarded_count double default 0.0,
	forwarded_all_rate double default 0.0,

	opened_delivered_rate double default 0.0,
	clicked_delivered_rate double default 0.0,
	printed_delivered_rate double default 0.0,
	forwarded_delivered_rate double default 0.0,

	clicked_opened_rate double default 0.0,
	printed_opened_rate double default 0.0,
	forwarded_opened_rate double default 0.0,

	email_count double default 0.0,

  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_campaign_uid` (`uid`),

  UNIQUE KEY `ux_kona__email_campaign_slug` (`slug`),

  UNIQUE KEY `ix_kona__email_campaign_channel_content_subject` (`campaign_channel_id`,`email_content_id`,`subject`),

  KEY `ix_kona__email_campaign_owner` (`owner_id`),

  KEY `ix_kona__email_campaign_campaign` (`campaign_id`),

  KEY `ix_kona__email_campaign_campaign_group` (`campaign_group_id`),

  KEY `ix_kona__email_campaign_email_group` (`email_group_id`),

  KEY `ix_kona__email_campaign_email_content` (`email_content_id`),

  FULLTEXT KEY `ft_kona_email_campaign` (uid,name,slug,description,from_address,subject),

  CONSTRAINT `fk_kona__email_campaign_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__email_campaign_campaign_group` FOREIGN KEY (`campaign_group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__email_campaign_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__email_campaign_email_content` FOREIGN KEY (`email_content_id`)
        REFERENCES `kona__email_content` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__email_campaign_email_group` FOREIGN KEY (`email_group_id`)
        REFERENCES `kona__email_group` (`id`) ON DELETE RESTRICT,

  CONSTRAINT `fk_kona__email_campaign_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


alter table kona__email
    add `email_campaign_id` bigint(20) unsigned DEFAULT NULL
    after uid;

alter table kona__email
    add unique key ux_kona__email_campaign_channel_address (`email_campaign_id`,`email_address_id`);

alter table kona__email add
  CONSTRAINT `fk_kona__email_email_campaign` FOREIGN KEY (`email_campaign_id`)
        REFERENCES `kona__email_campaign` (`id`) ON DELETE SET NULL;

alter table kona__email drop foreign key fk_kona__email_campaign;
alter table kona__email drop foreign key fk_kona__email_campaign_group;
alter table kona__email drop foreign key fk_kona__email_campaign_channel;
alter table kona__email drop foreign key fk_kona__email_email_group;

alter table kona__email drop key ix_kona__email_campaign_channel_address;
alter table kona__email drop key ix_kona__email_campaign;
alter table kona__email drop key ix_kona__email_campaign_group;
alter table kona__email drop key ix_kona__email_email_group;



alter table kona__email drop campaign_id;
alter table kona__email drop campaign_group_id;
alter table kona__email drop campaign_channel_id;
alter table kona__email drop email_group_id;


-- //@UNDO
-- SQL to undo the change goes here.


alter table kona__email drop foreign key fk_kona__email_email_campaign;

alter table kona__email drop key ux_kona__email_campaign_channel_address;

alter table kona__email drop email_campaign_id;


alter table kona__email add
    `campaign_id` bigint(20) unsigned DEFAULT NULL after uid;

alter table kona__email add
  `campaign_group_id` bigint(20) unsigned DEFAULT NULL after campaign_id;

alter table kona__email add
  `campaign_channel_id` bigint(20) unsigned DEFAULT NULL after campaign_group_id;

alter table kona__email add
  `email_group_id` bigint(20) unsigned DEFAULT NULL after campaign_channel_id;



alter table kona__email add
  UNIQUE KEY `ix_kona__email_campaign_channel_address` (`campaign_channel_id`,`email_address_id`);

alter table kona__email add
  KEY `ix_kona__email_campaign` (`campaign_id`);

alter table kona__email add
  KEY `ix_kona__email_campaign_group` (`campaign_group_id`);

alter table kona__email add
  KEY `ix_kona__email_email_group` (`email_group_id`);


alter table kona__email add
  CONSTRAINT `fk_kona__email_campaign` FOREIGN KEY (`campaign_id`)
        REFERENCES `kona__campaign` (`id`) ON DELETE SET NULL;

alter table kona__email add
  CONSTRAINT `fk_kona__email_campaign_group` FOREIGN KEY (`campaign_group_id`)
        REFERENCES `kona__campaign_group` (`id`) ON DELETE SET NULL;

alter table kona__email add
  CONSTRAINT `fk_kona__email_campaign_channel` FOREIGN KEY (`campaign_channel_id`)
        REFERENCES `kona__campaign_channel` (`id`) ON DELETE SET NULL;

alter table kona__email add
  CONSTRAINT `fk_kona__email_email_group` FOREIGN KEY (`email_group_id`)
        REFERENCES `kona__email_group` (`id`) ON DELETE SET NULL;



drop table kona__email_campaign;
