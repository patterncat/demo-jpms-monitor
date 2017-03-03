CREATE SEQUENCE hibernate_sequence;

/* Creates stats */
CREATE TABLE stats (id INT PRIMARY KEY, quota_id INT);
CREATE TABLE quotas (id INT PRIMARY KEY, dataPointCount BIGINT, aliveCount BIGINT);
INSERT INTO quotas VALUES (1, 10, 8);
INSERT INTO stats VALUES (1, 1)
