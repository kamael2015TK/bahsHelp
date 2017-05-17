FROM mysql
MAINTAINER taras_karpin
ENV MYSQL_ROOT_PASSWORD sensorDB
ENV MYSQL_DATABASE DataBase
ADD sql.sql /docker-entrypoint-initdb.d
EXPOSE 3306