#!/bin/bash

tomee_version=1.6.0

here=`pwd`
project_root=$here/`dirname $0`/../..
tomee_dir=$project_root/target/tomee

if ! [ -d $tomee_dir ]
then
    mkdir -p $tomee_dir
fi
cd $tomee_dir

if ! [ -f apache-tomee-plus-$tomee_version/bin/startup.sh ]
then
    wget -O apache-tomee-$tomee_version-bin.tar.gz http://mir2.ovh.net/ftp.apache.org/dist/tomee/tomee-$tomee_version/apache-tomee-$tomee_version-plus.tar.gz
    tar -xf apache-tomee-$tomee_version-bin.tar.gz
    rm apache-tomee-$tomee_version-bin.tar.gz
    cp $project_root/src/tomee/conf/tomee.xml apache-tomee-plus-$tomee_version/conf/
    wget -O apache-tomee-plus-$tomee_version/lib/activemq-mqtt-5.9.0.jar http://repo1.maven.org/maven2/org/apache/activemq/activemq-mqtt/5.9.0/activemq-mqtt-5.9.0.jar
    wget -O apache-tomee-plus-$tomee_version/lib/mqtt-client-1.6.jar http://repo1.maven.org/maven2/org/fusesource/mqtt-client/mqtt-client/1.6/mqtt-client-1.6.jar
    chmod +x apache-tomee-plus-$tomee_version/bin/startup.sh
fi
./apache-tomee-plus-$tomee_version/bin/startup.sh
