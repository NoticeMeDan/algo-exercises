#!/ bin/bash

nn="100 200 400 8 0 0"
javac Weed.java

if [! −d Input]
then
	mkdir Input
	for n in $nn
	do
		java Weed $n 1 > Input/Weed1_$n.in
	done
fi
