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
    PRIMARY KEY (classification, classGroupId)
);

CREATE TABLE IF NOT EXISTS product2classgrps (
    product integer,
    classificationGroups integer,
    PRIMARY KEY (product, classificationGroups)
)