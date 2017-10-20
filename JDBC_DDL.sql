CREATE TABLE WritingGroups
(
    groupName           VARCHAR(50)     NOT NULL,
    headWriter          VARCHAR(50),
    yearFormed          VARCHAR(4),
    subject             VARCHAR(50),
    CONSTRAINT  writingGroup_PK PRIMARY KEY(groupName) 
);

CREATE TABLE Publishers
(
    publisherName       VARCHAR(50)     NOT NULL,
    publisherAddress    VARCHAR(60),
    publisherPhone      VARCHAR(12),
    publisherEmail      VARCHAR(100),
    CONSTRAINT  publishers_PK PRIMARY KEY(publisherName) 
);

CREATE TABLE Books
(
    bookTitle           VARCHAR(50)     NOT NULL,
    groupName           VARCHAR(50)     NOT NULL,
    publisherName       VARCHAR(50)     NOT NULL,
    yearPublished       VARCHAR(4),
    numberPages         INT
);

ALTER TABLE Books
    ADD CONSTRAINT Books_PK PRIMARY KEY (bookTitle,groupName);

ALTER TABLE Books
    ADD CONSTRAINT Books_FK FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName);

ALTER TABLE Books
    ADD CONSTRAINT Books_FK1 FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName);
