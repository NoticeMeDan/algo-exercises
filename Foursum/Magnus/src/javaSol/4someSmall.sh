#!/bin/bash
echo "Running numbers..." >&2

for filename in ../../dataSmall/*.txt; do
		echo "FILE: " [$(basename "${filename}")]
        java Simplefaster < "$filename"
done
echo "Complete..." >&2