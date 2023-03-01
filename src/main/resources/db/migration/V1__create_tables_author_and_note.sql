CREATE TABLE author(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(68) NOT NULL, -- {bcrypt}
    authority VARCHAR(7) NOT NULL,
    CHECK (CHAR_LENGTH(name) >= 5 AND CHAR_LENGTH(name) <= 50),
    CHECK (authority IN ('USER', 'ADMIN', 'MANAGER'))
);

CREATE TABLE note(
    id VARCHAR(36) PRIMARY KEY, -- UUID
    title VARCHAR(100),
    content VARCHAR(10000),
    access_type VARCHAR(7) DEFAULT 'PRIVATE' NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE,
    CHECK (CHAR_LENGTH(title) >= 5 AND CHAR_LENGTH(title) <= 100),
    CHECK (CHAR_LENGTH(content) >= 5 AND CHAR_LENGTH(content) <= 10000),
    CHECK (access_type IN ('PRIVATE', 'PUBLIC'))
);