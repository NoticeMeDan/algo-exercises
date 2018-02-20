#! /bin/bash
cd ..


for filename in ../data/data_small/*.txt; do
	echo "File: " [$(basename "${filename}")]
	start=`date +%s`
	python Simple.py < "$filename"
	end=`date +%s`

	printf "Seconds for completion: $((end - start)) \n \n"
	#echo $((end - start))
done