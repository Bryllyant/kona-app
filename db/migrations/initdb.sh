#!/bin/bash

OUT="./scripts/bootstrap.sql"

SQL="./init"

#echo "drop database if exists kona;" > $OUT
#echo "create database kona;" >> $OUT
#echo "use kona;" >> $OUT

cat \
    $SQL/bootstrap.sql \
    $SQL/seed-tables.sql \
    $SQL/create-system-user.sql  \
    $SQL/entity-name-rules.sql \
    > $OUT


./migrate --force --env=dev bootstrap

./migrate --env=dev up

