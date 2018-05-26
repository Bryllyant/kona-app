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

-- // kona_update_email_campaign
-- Migration SQL that makes the change goes here.


alter table kona__email_campaign
    add copyright_holder varchar(255) default null after subject;

alter table kona__email_campaign
    add permission_reminder varchar(2000) default null after copyright_holder;

alter table kona__email_campaign
    add company_name varchar(255) default null after permission_reminder;

alter table kona__email_campaign
    add street1 varchar(255) default null after company_name;

alter table kona__email_campaign
    add street2 varchar(255) default null after street1;

alter table kona__email_campaign
    add city varchar(255) default null after street2;

alter table kona__email_campaign
    add state varchar(255) default null after city;

alter table kona__email_campaign
    add postal_code varchar(255) default null after state;

alter table kona__email_campaign
    add country varchar(255) default null after postal_code;

alter table kona__email_campaign drop key ft_kona_email_campaign;

alter table kona__email_campaign
    add FULLTEXT KEY `ft_kona_email_campaign` (
        uid
        ,name
        ,slug
        ,description
        ,from_address
        ,subject
        ,copyright_holder
        ,permission_reminder
        ,company_name
    );

-- //@UNDO
-- SQL to undo the change goes here.

alter table kona__email_campaign drop key ft_kona_email_campaign;

alter table kona__email_campaign drop country;
alter table kona__email_campaign drop postal_code;
alter table kona__email_campaign drop state;
alter table kona__email_campaign drop city;
alter table kona__email_campaign drop street2;
alter table kona__email_campaign drop street1;
alter table kona__email_campaign drop company_name;
alter table kona__email_campaign drop permission_reminder;
alter table kona__email_campaign drop copyright_holder;

alter table kona__email_campaign
    add FULLTEXT KEY `ft_kona_email_campaign` (`uid`,`name`,`slug`,`description`,`from_address`,`subject`);
