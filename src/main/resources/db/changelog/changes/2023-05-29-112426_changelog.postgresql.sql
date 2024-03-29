-- liquibase formatted sql

-- changeset lsuchan:1685359472150-1
CREATE TABLE water_requirement
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date     TIMESTAMP(6) WITHOUT TIME ZONE,
    value    numeric(38, 2),
    field_id BIGINT,
    CONSTRAINT "water_requirementPK" PRIMARY KEY (id)
);

-- changeset lsuchan:1685359472150-2
ALTER TABLE water_requirement
    ADD CONSTRAINT "FKs0g4jn4nj99yrvpwjol6ndxhh" FOREIGN KEY (field_id) REFERENCES field (id);

