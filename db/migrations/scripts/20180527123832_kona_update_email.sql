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

-- // kona_update_email
-- Migration SQL that makes the change goes here.

alter table kona__email
    add KEY `ix_kona__email_email_campaign` (`email_campaign_id`);

alter table kona__email drop key ux_kona__email_campaign_channel_address;


-- //@UNDO
-- SQL to undo the change goes here.


alter table kona__email
    add UNIQUE KEY `ux_kona__email_campaign_channel_address` (`email_campaign_id`,`email_address_id`);

alter table kona__email drop key ix_kona__email_email_campaign;
