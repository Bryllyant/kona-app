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

-- // kona_update_email_address
-- Migration SQL that makes the change goes here.

alter table kona__email_address add `advertiser_id` varchar(255) DEFAULT NULL after uid;
alter table kona__email_address add `platform` varchar(255) DEFAULT NULL after advertiser_id;

alter table kona__email_address drop birth_year;
alter table kona__email_address add `birth_date` date DEFAULT NULL after gender;

alter table kona__email_address add KEY `ix_kona__email_address_source` (`source`);
alter table kona__email_address add KEY `ix_kona__email_address_advertiser_id` (`advertiser_id`);

alter table kona__email_address drop key ft_kona_email_address;

alter table kona__email_address
    add FULLTEXT KEY `ft_kona_email_address` (uid,first_name,last_name,email,mobile_number,source,postal_code);

-- //@UNDO
-- SQL to undo the change goes here.

alter table kona__email_address drop key ix_kona__email_address_source;
alter table kona__email_address drop key ix_kona__email_address_advertiser_id;

alter table kona__email_address drop birth_date;
alter table kona__email_address add `birth_year` int(11) unsigned  DEFAULT NULL after gender;

alter table kona__email_address drop platform;
alter table kona__email_address drop advertiser_id;

alter table kona__email_address drop key ft_kona_email_address;
alter table kona__email_address
    add FULLTEXT KEY `ft_kona_email_address` (uid,first_name,last_name,email,mobile_number,postal_code);
