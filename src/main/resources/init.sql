CREATE TABLE IF NOT EXISTS user (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    age int(10) NOT NULL,
    avatar varchar(512) NOT NULL,
    description varchar(1024),
    PRIMARY KEY (id)
);
