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

-- // kona_update_campaign_target
-- Migration SQL that makes the change goes here.

alter table kona__campaign_target
    add `app_store_provider_id` varchar(255) DEFAULT NULL
    after analytics_tracking_id;

alter table kona__campaign_target add website_url varchar(255) DEFAULT NULL after url;
alter table kona__campaign_target add app_store_url varchar(255) DEFAULT NULL after website_url;
alter table kona__campaign_target add google_play_url varchar(255) DEFAULT NULL after app_store_url;

alter table kona__campaign_target drop key ft_kona__campaign_target;

alter table kona__campaign_target
    add FULLTEXT `ft_kona__campaign_target` (uid,type,name,slug,url,website_url,app_store_url,google_play_url,analytics_tracking_id,app_store_provider_id);



-- //@UNDO
-- SQL to undo the change goes here.

alter table kona__campaign_target drop app_store_provider_id;


alter table kona__campaign_target drop website_url;
alter table kona__campaign_target drop app_store_url;
alter table kona__campaign_target drop google_play_url;

alter table kona__campaign_target drop key ft_kona__campaign_target;

alter table kona__campaign_target
    add FULLTEXT `ft_kona__campaign_target` (uid,type,name,slug,url,analytics_tracking_id);

