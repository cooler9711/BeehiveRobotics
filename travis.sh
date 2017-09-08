#!/bin/bash

mkdir TeamCode/build/$NOW
ls TeamCode/build
cp -r TeamCode/build/outputs/apk TeamCode/build/$NOW
echo $piPass | sftp root@24.2.66.247 -o stricthostkeychecking=no
cd /var/www/robotics
mkdir $NOW
cd $NOW
put TeamCode/build/$NOW

# Using backticks you can put it in any command
