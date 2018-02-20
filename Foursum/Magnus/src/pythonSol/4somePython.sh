#!/bin/bash
echo "Running numbers..." >&2
for filename in ../../data/*; do
		echo "FILE: " [$(basename "${filename}")]
		start=`date +%s`
		python simple.py < "$filename"
		end=`date +%s`
		printf "Seconds for completion: $((end - start)) \n \n"
done
echo "Complete..." >&2