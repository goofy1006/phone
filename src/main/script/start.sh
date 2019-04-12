#!/bin/sh
echo "action"
APP_PORT=$1
APP_ID=$2
JAVA_FILE="./fz-shopping-phone-1.0.1.jar"

if [ "--help" == $1 -o "?" == $1]; then
	echo "Format:\n start.sh [port] [appId]"
	exit
fi

if [ "$APP_PORT" == ""]; then
	APP_PORT=8080
fi

if [ "$APP_ID" == ""]; then
	APP_ID=1
fi

echo "Launching application [shopping-$APP_ID] at port [$APP_PORT]..."

echo "java -Xms512M -Xmx1024M -XX:MaxPermSize=256M -XX:+UseParallelGC -XX:ParallelGCThreads=4 -Dapp.name=shopping -Dapp.id=$APP_ID -Dapp.profile=dev -Dserver.port=$APP_PORT -jar $JAR_FILE"

nohup java -Xms512M -Xmx1024M -XX:MaxPermSize=256M -XX:+UseParallelGC -XX:ParallelGCThreads=4 -Dapp.name=shopping -Dapp.id=$APP_ID -Dapp.profile=dev -Dserver.port=$APP_PORT -jar ./ &