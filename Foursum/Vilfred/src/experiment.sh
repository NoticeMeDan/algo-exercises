#!/bin/bash

nn="100 200 400 800"
javac Weed.java
if [ ! -d Input ]
then
    mkdir Input
    for n in $nn
    do
        java Weed $n 1 > Input/Weed1_$n.in
    done
fi
rm simple.table
for n in $nn
do
    /usr/bin/time -f "$n %e" bash -c \
                  "java -cp javaSol Simple <Input/Weed1_$n.in > /dev/null 2>&1" \
                  >> simple.table  2>&1
done