#! /bin/bash
cd ..
javac-algs4 Simple.java 

for filename in ../data/*.txt; do
	echo "File: " [$(basename "${filename}")]
	start=`date +%s`
	java-algs4 Simple < "$filename"
	end=`date +%s`

	printf "Seconds for completion: $((end - start)) \n \n"
	#echo $((end - start))
done