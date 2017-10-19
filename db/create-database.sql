-- drop user if exists kona;
create user kona identified by 'kona';

drop database if exists kona;
create database kona;

grant all on kona.* to kona;
