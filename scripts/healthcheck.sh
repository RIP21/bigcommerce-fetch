#!/bin/sh

API_URL="http://localhost:8080/api"

echo `date`

status_code=`curl -s -o /dev/null -I -w "%{http_code}" $API_URL`

if [ 204 -ne ${status_code} ]; then
  echo "healthcheck.sh: api not running, restarting service!"
  pkill java
  cd /mnt
  java -jar bigcommerceFetch-backend-0.0.1-SNAPSHOT.jar >> java.log &
  echo "healthcheck.sh: api is restarting..."
fi