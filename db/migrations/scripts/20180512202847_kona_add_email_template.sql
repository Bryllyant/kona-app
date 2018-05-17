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

-- // kona_add_email_template
-- Migration SQL that makes the change goes here.

CREATE TABLE `kona__email_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `owner_id` bigint(20) unsigned NOT NULL, -- owner of the template
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` varchar(4000) default NULL,
  `text_header` longtext DEFAULT NULL,
  `text_footer` longtext DEFAULT NULL,
  `html_header` longtext DEFAULT NULL,
  `html_footer` longtext DEFAULT NULL,
  `created_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),

  PRIMARY KEY (`id`),

  UNIQUE KEY `ux_kona__email_template_uid` (`uid`),

  UNIQUE KEY `ux_kona__email_template_slug` (`slug`),

  KEY `ix_kona__email_template_owner` (`owner_id`),

  FULLTEXT KEY `ft_kona_email_template` (uid,name,slug,description,text_header,text_footer,html_header,html_footer),

  CONSTRAINT `fk_kona__email_template_owner` FOREIGN KEY (`owner_id`)
        REFERENCES `kona__user` (`id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


alter table kona__email_content add template_id bigint(20) unsigned DEFAULT NULL after owner_id;
alter table kona__email_content add name varchar(255) NOT NULL after template_id;
alter table kona__email_content add slug varchar(255) NOT NULL after name;
alter table kona__email_content add description varchar(4000) DEFAULT NULL after slug;
alter table kona__email_content add system tinyint(1) unsigned NOT NULL DEFAULT '0' after description;

update kona__email_content
set name=uid, slug=uid, system=1
where length(name) = 0 or name=uid;

alter table kona__email_content add UNIQUE `ux_kona__email_content_slug` (`slug`);
alter table kona__email_content add KEY `ix_kona__email_content_template` (`template_id`);

alter table kona__email_content
    add CONSTRAINT `fk_kona__email_content_template` FOREIGN KEY (`template_id`)
        REFERENCES `kona__email_template` (`id`) ON DELETE RESTRICT;


alter table kona__email_content drop key `ft_kona_email_content`;
alter table kona__email_content add FULLTEXT `ft_kona_email_content` (uid,name,slug,description,text,html);

-- //@UNDO
-- SQL to undo the change goes here.


alter table kona__email_content drop foreign key fk_kona__email_content_template;
alter table kona__email_content drop key ux_kona__email_content_slug;
alter table kona__email_content drop key ix_kona__email_content_template;

alter table kona__email_content drop system;
alter table kona__email_content drop description;
alter table kona__email_content drop slug;
alter table kona__email_content drop name;
alter table kona__email_content drop template_id;

alter table kona__email_content drop key `ft_kona_email_content`;
alter table kona__email_content add FULLTEXT KEY `ft_kona_email_content` (uid,text,html);

drop table kona__email_template;
