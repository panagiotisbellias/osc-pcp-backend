CREATE USER people WITH PASSWORD 'people';
GRANT CREATE ON DATABASE postgres TO people;
ALTER ROLE people SET search_path TO people;
