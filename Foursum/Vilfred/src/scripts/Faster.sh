#!/bin/bash
javac Faster.java
mkdir -p Input


for filename in ../data/*.txt; do
	echo "File: " [$(basename "${filename}")]
	start=`date +%s`
	java-algs4 Faster < "$filename"
	end=`date +%s`

	printf "Seconds for completion: $((end - start)) \n \n"
	#echo $((end - start))
done
