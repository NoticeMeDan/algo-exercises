from __future__ import print_function
import sys
import bisect
import time

N = int(sys.stdin.readline())
vals = list(map(int, sys.stdin.readlines()))

vals.sort()

now = time.time()
for i in range(0, N):
    for j in range(i+1, N):
    	for k in range(j+1, N):
    	    for l in range(k+1, N):
                if vals[i] + vals[j] + vals[k] + vals[l] == 0:
                    print(True)
                    # print(time.time() - now)
                    sys.exit()
# print(time.time() - now)
print(False)



