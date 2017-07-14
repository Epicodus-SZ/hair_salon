#!/usr/bin/env bash
echo "Create database 'hair_salon', database: Postgres"
psql -c "CREATE DATABASE hair_salon;"
psql hair_salon < hair_salon_db_backup.sql
psql -c "CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;"
