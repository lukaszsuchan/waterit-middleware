-- liquibase formatted sql

-- changeset lsuchan:1683235660867-1
CREATE TABLE field
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    actual_crop_type SMALLINT,
    area             numeric(38, 2),
    name             VARCHAR(255),
    measurement_id   BIGINT,
    user_id          BIGINT,
    CONSTRAINT "fieldPK" PRIMARY KEY (id)
);

-- changeset lsuchan:1683235660867-2
CREATE TABLE zone
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    actual_crop_type VARCHAR(255),
    area             numeric(38, 2),
    name             VARCHAR(255),
    field_id         BIGINT,
    measurement_id   BIGINT,
    CONSTRAINT "zonePK" PRIMARY KEY (id)
);

-- changeset lsuchan:1683235660867-3
ALTER TABLE measurement
    ADD measurement_type VARCHAR(255);

-- changeset lsuchan:1683235660867-4
ALTER TABLE zone
    ADD CONSTRAINT "FKbm5nxi2ix3xie226g15cumdn8" FOREIGN KEY (measurement_id) REFERENCES measurement (id);

-- changeset lsuchan:1683235660867-5
ALTER TABLE field
    ADD CONSTRAINT "FKd8r26q6gt3ji2ywjl23vdv8t5" FOREIGN KEY (measurement_id) REFERENCES measurement (id);

-- changeset lsuchan:1683235660867-6
ALTER TABLE zone
    ADD CONSTRAINT "FKf1o8iausn20oumg98dlc9c6ng" FOREIGN KEY (field_id) REFERENCES field (id);

-- changeset lsuchan:1683235660867-7
ALTER TABLE field
    ADD CONSTRAINT "FKl3t3t2bepmqkf6iyvo6eibjdx" FOREIGN KEY (user_id) REFERENCES "user" (id);

