-- liquibase formatted sql

-- changeset lsuchan:1683285673317-1
ALTER TABLE measurement ADD zone_id BIGINT;

-- changeset lsuchan:1683285673317-2
ALTER TABLE measurement ADD CONSTRAINT "FKfnrfngh4tg8cbv572x2ucosrv" FOREIGN KEY (zone_id) REFERENCES zone (id);

-- changeset lsuchan:1683285673317-3
ALTER TABLE zone DROP CONSTRAINT "FKbm5nxi2ix3xie226g15cumdn8";

-- changeset lsuchan:1683285673317-4
ALTER TABLE zone DROP COLUMN measurement_id;

