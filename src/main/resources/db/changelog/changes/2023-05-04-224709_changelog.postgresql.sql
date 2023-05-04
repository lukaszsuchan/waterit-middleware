-- liquibase formatted sql

-- changeset lsuchan:fix-column-type-1
alter table field
alter column actual_crop_type type varchar(25) using actual_crop_type::varchar(25);

