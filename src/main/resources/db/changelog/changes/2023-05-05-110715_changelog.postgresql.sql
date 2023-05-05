-- liquibase formatted sql

-- changeset lsuchan:1683284840528-1
CREATE TABLE device
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    active             BOOLEAN                                 NOT NULL,
    external_device_id VARCHAR(255),
    CONSTRAINT "devicePK" PRIMARY KEY (id)
);

-- changeset lsuchan:1683284840528-2
ALTER TABLE zone
    ADD device_id BIGINT;

-- changeset lsuchan:1683284840528-3
ALTER TABLE device
    ADD CONSTRAINT UC_DEVICEEXTERNAL_DEVICE_ID_COL UNIQUE (external_device_id);

-- changeset lsuchan:1683284840528-4
ALTER TABLE zone
    ADD CONSTRAINT "FKa79i8ys53a21l83q6xo5okv2k" FOREIGN KEY (device_id) REFERENCES device (id);
