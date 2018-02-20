from __future__ import print_function
import sys

def binary_search(arr,item,low = 0, high = None):
    if high == None:
        high = len(arr)
    mid = low + (high-low) //2  
    if high - low + 1 <= 0 or mid==high:
        return False
    else:
        guess = arr[mid]
        if guess == item:
            return mid
        if item < guess:
            return binary_search(arr, item, low, mid)
        else:
            return binary_search(arr, item, (mid+1), high)

N = int(sys.stdin.readline())
vals = list(map(int, sys.stdin.readlines()))

vals.sort()
i = 0
while i < N:
	# your code goes here and uses the following
	j = i+1
	while j < N:
		k = j+1
		while k < N:
			l = (binary_search(vals, -(vals[i] + vals[j] + vals[k])))
			# if l != False:
			# 	print("L value: " + str(l))
			# 	print("k value: " + str(k))
			if l > k: 
				print()
				print(True)
				print("Index: val[" + str(i) + "] , vals[" + str(j) + "] , vals[" + str(k) + "] , vals[" + str(l) + "].")
				print("Values: " + str(vals[i]) + " , " + str(vals[j]) + " , " + str(vals[k]) + " , " + str(vals[l-1]))
				print()
				sys.exit()
			k+=1
		j+=1
	i+=1		
print(False)