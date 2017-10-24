INSERT INTO Publishers(publisherName, publisherAddress, publisherPhone, publisherEmail)
values
('CSULB Library','1234 Main St','562-123-1234','csulblibrary@gmail.com'),
('CSU HQ','5678 Second St','562-567-5678','csuhq@gmail.com'),
('Holden Literature','345 Holden Ave','213-345-3456','holden@gmail.com'),
('Salinger Corps','1600 Salinger Blvd','800-588-2300','salinger@yahoo.com'),
('Blue Creek Pubs','12 Blue Creek Rd', '800-888-8888','bluecreek@earthlink.net'),
('ECS Tutors','250 State College Blvd','562-714-1927','ecstutoring@csulb.edu'),
('Wikipedia','360 World Ln','562-360-2017','wikipedia@weknoweverything.net'),
('Merriam-Webster','1200 Merriam Way','909-898-7878','merweb@hotmail.com'),
('Penguin','1 Arctic Ln','767-656-5454','penguinpub@santa.com'),
('Bloomsbury','4 Privet Drive','934-100-1015','hogwarts@gmail.com'),
('Brown and Co','82 Maine St','654-783-7234','teamjacob@twilight.net'),
('Scholastic','42 Wimp Ave','562-456-7890','wimpykid@gmail.com'),
('Random House','3400 Branch Rd','562-546-5473','savethetree@hippy.com')
;

INSERT INTO WritingGroups(groupName, headWriter, yearFormed, subject)
values
('CECS department','Dr. Monge','1990','coding'),
('Penpals','Dr. Wright','1995','high school books'),
('Wiki Group','Vickie Shroop','1999','information'),
('Nerds United','Mr. Dr. Professor Nerd','2005','definitions'),
('Marwood n peeps','Alice Marwood','2014','horror'),
('Smith and Co','Bob Smith','2017','geometry'),
('NY Writers','Mrs. Nyoo Bjork','2012','mystery'),
('JK Authors','J K Rowling','1998','fantasy'),
('Vamps','Stephanie Meyer','2005','fantasy'),
('Shy Anonymous','Mr. Shy','2000','school fiction'),
('MPO-ers','Magic Johnson','1992','fantasy')
;

INSERT INTO Books(bookTitle, groupName, publisherName, yearPublished, numberPages)
values
('SQL for Dummies','CECS department','CSULB Library','1990',300),
('JDBC for Dummies','CECS department','CSU HQ','1992',220),
('The Great Gatsby','Penpals','Holden Literature','1995',180),
('The Catcher in the Rye','Penpals','Salinger Corps','1951',277),
('To Kill a Mockingbird','Penpals', 'Blue Creek Pubs','1960',323),
('CECS map','CECS department','ECS Tutors','2001',4),
('Dictionary','Wiki Group','Wikipedia','1999',900),
('Dictionary','Nerds United','Merriam-Webster','2005',930),
('The Killer Next Door','Marwood n peeps','Penguin','2014',400),
('The Line','Smith and Co','Penguin','2017',224),
('The Quest','NY Writers','Penguin','2012',832),
('Harry Potter 1','JK Authors','Bloomsbury','1998',309),
('Twilight','Vamps','Brown and Co','2005',498),
('Dairy of a Wimpy Kid','Shy Anonymous','Scholastic','2000',221),
('Magic Tree House','MPO-ers','Random House','1992',199),
('UML for Dummies','CECS department','CSULB Library','1990',300),
('Java Beginnings','CECS department','CSU HQ','1992',220),
('Hey There, Stranger','Penpals','Holden Literature','1995',180),
('When I Go','Penpals','Salinger Corps','1951',277),
('Happy Days','Penpals', 'Blue Creek Pubs','1960',323),
('VEC map','CECS department','ECS Tutors','2001',4),
('EN2 map','CECS department','ECS Tutors','2001',4),
('Thesaurus','Wiki Group','Wikipedia','1999',900),
('Thesaurus','Nerds United','Merriam-Webster','2005',930),
('Neighbors','Marwood n peeps','Penguin','2014',400),
('Geometric Objects','Smith and Co','Penguin','2017',224),
('Adventure Ahead','NY Writers','Penguin','2012',832),
('Harry Potter 2','JK Authors','Bloomsbury','1999',379),
('Breaking Dawn','Vamps','Brown and Co','2005',498),
('Rodrick Rules','Shy Anonymous','Scholastic','2000',221),
('Magic School Bus','MPO-ers','Random House','1992',199)
;
