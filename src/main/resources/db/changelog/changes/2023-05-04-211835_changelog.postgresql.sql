-- liquibase formatted sql

-- changeset lsuchan:enumarated-to-string
alter table field
alter column actual_crop_type type varchar(255) using actual_crop_type::varchar(255);
alter table zone
alter column actual_crop_type type varchar(255) using actual_crop_type::varchar(255);
alter table measurement
alter column  measurement_type type varchar(255) using measurement_type::varchar(255);

