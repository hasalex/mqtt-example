#!/bin/bash

here=$pwd
project_root=`dirname $0`/../..
moquette_dir=$project_root/target/moquette
moquette_jar=moquette-broker-0.3-jar-with-dependencies.jar

if ! [ -d $moquette_dir ]
then
    mkdir -p $moquette_dir
fi
cd $moquette_dir

if ! [ -f $moquette_jar ]
then
    wget -O $moquette_jar https://moquette-mqtt.googlecode.com/files/moquette-broker-0.3-jar-with-dependencies.jar
fi
java -jar $moquette_jar
cd $here
