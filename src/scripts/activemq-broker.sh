#!/bin/bash

activemq_version=5.9.0

project_root=`dirname $0`/../..
activemq_dir=$project_root/target/activemq

if ! [ -d $activemq_dir ]
then
    mkdir -p $activemq_dir
fi
cd $activemq_dir

if ! [ -f apache-activemq-$activemq_version/bin/activemq ]
then
    wget -O apache-activemq-$activemq_version-bin.tar.gz http://mir2.ovh.net/ftp.apache.org/dist/activemq/apache-activemq/$activemq_version/apache-activemq-$activemq_version-bin.tar.gz
    tar -xf apache-activemq-$activemq_version-bin.tar.gz
    rm apache-activemq-$activemq_version-bin.tar.gz
    chmod +x apache-activemq-$activemq_version/bin/activemq
fi
./apache-activemq-$activemq_version/bin/activemq start
