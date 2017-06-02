drop table attachment if exists;
CREATE TABLE attachment(
  id INTEGER IDENTITY PRIMARY KEY,
  created BIGINT,
  name  VARCHAR(150),
  mime  VARCHAR(150),
  size  BIGINT,
  path  LONGVARCHAR
);