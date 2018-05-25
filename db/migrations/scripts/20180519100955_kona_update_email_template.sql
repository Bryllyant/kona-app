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

-- // kona_update_email_template
-- Migration SQL that makes the change goes here.

alter table kona__email_template add `text_template` longtext DEFAULT NULL after description;
alter table kona__email_template add `html_template` longtext DEFAULT NULL after text_template;

alter table kona__email_template drop text_header;
alter table kona__email_template drop text_footer;
alter table kona__email_template drop html_header;
alter table kona__email_template drop html_footer;

alter table kona__email_template drop KEY `ft_kona_email_template`;

alter table kona__email_template
    add FULLTEXT KEY `ft_kona_email_template` (uid,name,slug,description,text_template,html_template);

-- //@UNDO
-- SQL to undo the change goes here.

alter table kona__email_template add `text_header` longtext DEFAULT NULL after description;
alter table kona__email_template add `text_footer` longtext DEFAULT NULL after text_header;
alter table kona__email_template add `html_header` longtext DEFAULT NULL after text_footer;
alter table kona__email_template add `html_footer` longtext DEFAULT NULL after html_header;

alter table kona__email_template drop text_template;
alter table kona__email_template drop html_template;


alter table kona__email_template drop KEY `ft_kona_email_template`;
alter table kona__email_template
    add FULLTEXT KEY `ft_kona_email_template` (uid,name,slug,description,text_header,text_footer,html_header,html_footer);
