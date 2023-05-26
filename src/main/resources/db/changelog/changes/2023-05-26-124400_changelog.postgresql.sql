-- liquibase formatted sql

-- changeset lsuchan:1685105044684-1
ALTER TABLE field ADD device_id BIGINT;

-- changeset lsuchan:1685105044684-2
ALTER TABLE measurement ADD field_id BIGINT;

-- changeset lsuchan:1685105044684-3
ALTER TABLE measurement ADD CONSTRAINT "FKrm0td66fh16fb0fww1sexkhx8" FOREIGN KEY (field_id) REFERENCES field (id);

-- changeset lsuchan:1685105044684-4
ALTER TABLE field ADD CONSTRAINT "FKslokm6gijpeysqnri9wnjefm3" FOREIGN KEY (device_id) REFERENCES device (id);

-- changeset lsuchan:1685105044684-5
ALTER TABLE zone DROP CONSTRAINT "FKa79i8ys53a21l83q6xo5okv2k";

-- changeset lsuchan:1685105044684-6
ALTER TABLE field DROP CONSTRAINT "FKd8r26q6gt3ji2ywjl23vdv8t5";

-- changeset lsuchan:1685105044684-7
ALTER TABLE zone DROP CONSTRAINT "FKf1o8iausn20oumg98dlc9c6ng";

-- changeset lsuchan:1685105044684-8
ALTER TABLE measurement DROP CONSTRAINT "FKfnrfngh4tg8cbv572x2ucosrv";

-- changeset lsuchan:1685105044684-9
DROP TABLE zone;

-- changeset lsuchan:1685105044684-10
ALTER TABLE field DROP COLUMN measurement_id;

-- changeset lsuchan:1685105044684-11
ALTER TABLE measurement DROP COLUMN measurement_type;

-- changeset lsuchan:1685105044684-12
ALTER TABLE measurement DROP COLUMN zone_id;

