from __future__ import print_function
import sys
import bisect

N = int(sys.stdin.readline())
vals = list(map(int, sys.stdin.readlines()))

vals.sort()

for i in range(0, N): # i goes through {0,...,N-1}
    for j in range(i+1, N):
    	for k in range(j+1, N):
    		for l in range(k+1, N):
    			if vals[i] + vals[j] + vals[k] + vals[l] == 0:
    				print()
    				print(True)
    				print("Values: " + str(vals[i]) + " " + str(vals[j]) + " " + str(vals[k]) + " " + " " + str(vals[l]))
    				print()
    				sys.exit(1)
print(False)



