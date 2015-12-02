#!/usr/bin/env bash

set -m
set -e

sed '/nice            = 0/r lower_case_table_names=2' /usr/my.cnf
sed '/nice            = 0/r lower_case_table_names=2' /etc/mysql/my.cnf

sed '/nice            = 0/r max_allowed_packet=32M' /usr/my.cnf
sed '/nice            = 0/r max_allowed_packet=32M' /etc/mysql/my.cnf

/usr/bin/mysqld_safe &

sleep 10

mysql -h 127.0.0.1 -P 3306 -u root -pchangeit -e "CREATE DATABASE IF NOT EXISTS infoglue"
mysql -h 127.0.0.1 -P 3306 -u root -pchangeit -e "GRANT ALL ON your_db.* to 'infoglue'@'%' IDENTIFIED BY 'changeit'"
mysql -h 127.0.0.1 -P 3306 -u root -pchangeit -e "FLUSH PRIVILEGES"

mysql -h 127.0.0.1 -P 3306 -u root -pchangeit infoglue < /tmp/infoglue3.sql

echo "Created database"

export JAVA_OPTS="-Dfile.encoding=ISO-8859-1 -XX:MaxPermSize=256m -Xms128M -Xmx1024M"
catalina.sh run

sleep 120

catalina.sh stop
sleep 30

catalina.sh run