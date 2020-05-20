CREATE TABLE IF NOT EXISTS attribute (
    id serial primary key,
    type text,
    isMultiValued boolean,
    name text
);

CREATE TABLE IF NOT EXISTS attribute2classgrps (
    attribute integer,
    classificationGroups integer,
    PRIMARY KEY (attribute, classificationGroups)
);