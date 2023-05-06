-- liquibase formatted sql

-- changeset lsuchan:1683360171109-1
ALTER TABLE token ADD access_token VARCHAR(255);

-- changeset lsuchan:1683360171109-2
ALTER TABLE token ADD CONSTRAINT UC_TOKENACCESS_TOKEN_COL UNIQUE (access_token);

-- changeset lsuchan:1683360171109-3
ALTER TABLE token DROP COLUMN token;

