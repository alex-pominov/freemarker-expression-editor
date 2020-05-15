CREATE TABLE IF NOT EXISTS classification (
    id serial primary key,
    name text
);

CREATE TABLE IF NOT EXISTS classificationGroups (
    classification integer references classification(id),
    classificationKey integer,
    classGroupId serial,
    parentId integer,
    classGroupName text,
    CONSTRAINT id PRIMARY KEY (classification, classgroupid)
);

CREATE TABLE IF NOT EXISTS product2classgrps (
    product integer,
    classificationGroups integer,
    PRIMARY KEY (product, classificationGroups)
);

SELECT * FROM pg_indexes WHERE tablename = 'classificationGroups';