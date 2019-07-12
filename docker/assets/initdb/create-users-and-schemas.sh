#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE USER avenuecode WITH PASSWORD '123456' NOCREATEDB;

  CREATE DATABASE avenuecode_database OWNER avenuecode;
EOSQL
