#!/bin/bash
echo "Running numbers..." >&2
for filename in ../../dataSmall/*; do
		echo "FILE: " [$(basename "${filename}")]
		python simple.py < "$filename"
done
echo "Complete..." >&2