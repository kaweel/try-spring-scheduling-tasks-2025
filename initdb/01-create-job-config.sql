CREATE TABLE IF NOT EXISTS job_config (
    name VARCHAR(50) PRIMARY KEY,
    expression VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL
);