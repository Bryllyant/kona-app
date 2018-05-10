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

-- // update campaign tables
-- Migration SQL that makes the change goes here.

alter table kona__campaign_group modify name varchar(255) NOT NULL;
alter table kona__campaign_group add `slug` varchar(255) NOT NULL after name;
alter table kona__campaign_group drop key `ft_kona__campaign_group`;
alter table kona__campaign_group add FULLTEXT `ft_kona__campaign_group` (uid,name,slug,description);

alter table kona__campaign modify name varchar(255) NOT NULL;

alter table kona__campaign_target drop key ux_kona__campaign_target_url;
alter table kona__campaign_target add KEY `ix_kona__campaign_target_url` (`url`);


-- //@UNDO
-- SQL to undo the change goes here.

alter table kona__campaign modify name varchar(255) default NULL;

alter table kona__campaign_group modify name varchar(255) default NULL;
alter table kona__campaign_group drop slug;
alter table kona__campaign_group drop key `ft_kona__campaign_group`;
alter table kona__campaign_group add FULLTEXT `ft_kona__campaign_group` (uid,name,description);

alter table kona__campaign_target drop key ix_kona__campaign_target_url;
alter table kona__campaign_target add UNIQUE KEY `ux_kona__campaign_target_url` (`url`);
