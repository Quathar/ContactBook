-- INIT DATA SCRIPT

DELETE FROM contacts;
DELETE FROM hobbies;
ALTER TABLE contacts ALTER COLUMN id RESTART WITH 1;
ALTER TABLE hobbies  ALTER COLUMN id RESTART WITH 1;

INSERT INTO contacts (name, address, notes, surnames, gender, birth_date, type)
VALUES
    ('Alex',              'Street A', 'Studies at Clara del Rey', 'R', 'M', '2345-06-01', 'PERSON'),
    ('Wendy',             'Street W', 'Psychology', 'Rodríguez', 'F', '2345-06-02', 'PERSON'),
    ('Daniel',            'Street D', 'Studies at Clara del Rey', 'M', 'M', '2910-04-20', 'PERSON'),
    ('Coca Cola',         'Cola Street, 23', 'Carries a secret ingredient', null, null, null, 'COMPANY'),
    ('Winnie',            'Central Mediterranean Avenue, 34', 'Lazy dog', null, null, null, 'PET'),
    ('Pedro',             '420 Street', 'Likes green', 'Porro', 'M', '1999-10-14', 'PERSON'),
    ('7UP',               'Vía up, 67', 'Bubble drink', null, null, null, 'COMPANY'),
    ('Oddy',              'Central Mediterranean Avenue, 192', 'Grumpy dog'),
    ('Pacheco',           'Toledo Street, 3', 'Went back to Cryptomines', 'Pacheco', 'M', '1999-10-14', 'PERSON'),
    ('TRUHKO MakeUp',     'Atocha Street, 94', 'Makeup school', null, null, null, 'COMPANY'),
    ('Milú',              'Glasgow Avenue, 45', 'Happy dog', null, null, null, 'PET'),
    ('Tony',              'New York, 5th', 'Iron-Man', 'Stark', 'M', '1999-10-14', 'PERSON'),
    ('Steve',             'Queens Street, 3', 'Captain America', 'Rogers', 'M', '1999-10-14', 'PERSON'),
    ('Harry',             'Spade Boulevard , 2', 'Green Goblin', 'Osborn', 'M', '1999-10-14', 'PERSON'),
    ('Rocket Raccoon',    'His spaceship', 'Groot best friend'),
    ('Al',                'The Hell', 'Spawn', 'Simmons', 'M', '1999-10-14', 'PERSON'),
    ('James',             'Mutant Street, XMen-House', 'Wolverine', 'Logan', 'M', '1999-10-14', 'PERSON'),
    ('Groot',             'Groot', 'Groot', null, null, null, 'PET'),
    ('Bruce',             'Wayne Mansion', 'Batman', 'Wayne', 'M', '1999-10-14', 'PERSON'),
    ('Loki',              'Valaskjölf, Asgard', 'The origin of all fraud', 'Odinson', 'M', '1999-10-14', 'PERSON'),
    ('Dick',              'Wayne Mansion', 'Nightwing', 'Grayson', 'M', '1999-10-14', 'PERSON'),
    ('Stephen',           'Sancto Sanctorum, New York', 'Doctor Strange', 'Strange', 'M', '1999-10-14', 'PERSON'),
    ('J. A. R. V. I. S.', 'Internet', 'Vision', 'Utron', 'O', '1999-10-14', 'PERSON'),
    ('Peter',             'New York, 98th', 'Spider-man', 'Parker', 'F', '1999-10-14', 'PERSON'),
    ('Thor',              'Valaskjölf, Asgard', 'Thunder God', 'Odinson', 'M', '1999-10-14', 'PERSON'),
    ('Wanda',             'Its own illusion', 'Scarlet Witch', 'Maximoff', 'F', '1983-02-14', 'PERSON'),
    ('Alberto Marcos',    'Los Nogales', 'Classmate', 'Martínez S.', 'M', '1983-02-14', 'PERSON'),
    ('Mario Carlos',      'Hacienda los Rosales', 'Soap opera actor', 'Sandoval', 'M', '1983-02-14', 'PERSON'),
    ('Pearson-Hardman',   'Manhattan, 3', 'Law firm', null, null, null, 'COMPANY'),
    ('Sam',               'La pradera', 'Shepherd. Ralf''s best friend.', null, null, null, 'PET');
INSERT INTO hobbies (name)
VALUES
    ('Football'),
    ('Basketball'),
    ('Handball'),
    ('Stock market investing'),
    ('Ball grabbing'),
    ('Going to the theater'),
    ('Traveling'),
    ('Restaurants'),
    ('Sleeping'),
    ('Playing bingo'),
    ('Reading'),
    ('Hiking'),
    ('Climbing'),
    ('Calisthenics'),
    ('Tennis'),
    ('Cooking'),
    ('Playing video games'),
    ('Taking a stroll'),
    ('Programming'),
    ('Languages'),
    ('Making money'),
    ('Volleyball'),
    ('Chess'),
    ('Groot');