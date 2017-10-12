CREATE TABLE WritingGroups
(
    groupName           VARCHAR(20)     NOT NULL,
    headWriter          VARCHAR(20),
    yearFormed          INT,
    subject             VARCHAR(20),
    CONSTRAINT  writingGroup_PK PRIMARY KEY(groupName) 
);

CREATE TABLE Publishers
(
    publisherName       VARCHAR(20)     NOT NULL,
    publisherAddress    VARCHAR(60),
    publisherPhone      VARCHAR(15),
    publisherEmail      VARCHAR(20),
    CONSTRAINT  publishers_PK PRIMARY KEY(publisherName) 
);

CREATE TABLE Books
(
    bookTitle           VARCHAR(25)     NOT NULL,
    groupName           VARCHAR(20)     NOT NULL,
    publisherName       VARCHAR(20)     NOT NULL,
    yearPublished       VARCHAR(4),
    numberPages         INT
);

ALTER TABLE Books
    ADD CONSTRAINT Books_PK PRIMARY KEY (bookTitle,groupName);

ALTER TABLE Books
    ADD CONSTRAINT Books_FK FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName);

ALTER TABLE Books
    ADD CONSTRAINT Books_FK1 FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName);