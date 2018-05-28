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

alter table kona__email_address
add confirmed_date datetime(6) DEFAULT NULL
after confirmed;

alter table kona__email_address
add scrubbed_date datetime(6) DEFAULT NULL
after enabled;


update kona__email_address set
    confirmed_date = created_date,
    scrubbed_date = created_date
    where confirmed = 1;

alter table kona__email_address drop scrubbed;
alter table kona__email_address drop confirmed;

-- //@UNDO
-- SQL to undo the change goes here.


alter table kona__email_address
    add `scrubbed` tinyint(1) unsigned NOT NULL DEFAULT '0'
    after source;

alter table kona__email_address
    add `confirmed` tinyint(1) unsigned NOT NULL DEFAULT '0'
    after enabled;

update kona__email_address set confirmed = 1,
    where confirmed_date is not null;

update kona__email_address set scrubbed = 1,
    where scrubbed_date is not null;

alter table kona__email_address drop confirmed_date;
alter table kona__email_address drop scrubbed_date;
