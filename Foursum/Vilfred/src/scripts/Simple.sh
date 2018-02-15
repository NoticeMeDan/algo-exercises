#!/bin/bash

timestamp() {
  date +"%T"
}

mkdir -p Input


for filename in ../data/*.txt; do
	for ((i = 0; i<=3; i++)) do
		start=`date +%s`
		java-algs4 Simple < "$filename"
		end=`date +%s`

		echo $((end - start))
	done
done
