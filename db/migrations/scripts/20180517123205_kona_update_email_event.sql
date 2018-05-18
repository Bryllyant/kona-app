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

-- // kona_update_email_event
-- Migration SQL that makes the change goes here.

alter table kona__email_event
    add user_id bigint(20) unsigned default null
    after email_id;

alter table kona__email_event
    add email_campaign_id bigint(20) unsigned default null
    after user_id;

alter table kona__email_event
    add key `ix_kona__email_event_user` (`user_id`);

alter table kona__email_event
    add key `ix_kona__email_event_email_campaign` (`email_campaign_id`);

alter table kona__email_event
    add CONSTRAINT `fk_kona__email_event_user` FOREIGN KEY (`user_id`)
        REFERENCES `kona__user` (`id`) ON DELETE SET NULL;

alter table kona__email_event
    add CONSTRAINT `fk_kona__email_event_email_campaign` FOREIGN KEY (`email_campaign_id`)
        REFERENCES `kona__email_campaign` (`id`) ON DELETE SET NULL;

-- //@UNDO
-- SQL to undo the change goes here.


alter table kona__email_event drop foreign key fk_kona__email_event_user;
alter table kona__email_event drop key ix_kona__email_event_user;
alter table kona__email_event drop user_id;


alter table kona__email_event drop foreign key fk_kona__email_event_email_campaign;
alter table kona__email_event drop key ix_kona__email_event_email_campaign;
alter table kona__email_event drop email_campaign_id;


