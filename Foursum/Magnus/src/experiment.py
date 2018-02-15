# this is python3
import sys,random,subprocess,statistics,pathlib
from timeit import default_timer as timer

# looping with outermost loop is the repetition of the experiment would be even better

python='python3'
Nlist = [int(30*1.41**i) for i in range(6)]
lNlist = [int(30*1.41**i) for i in range(10)]
llNlist = [int(30*1.41**i) for i in range(14)]


# Nlist = [int(30*1.41**i) for i in range(3)]
# lNlist = [int(30*1.41**i) for i in range(5)]
# llNlist = [int(30*1.41**i) for i in range(8)]
# lNlist = Nlist
# llNlist = Nlist

print(llNlist)

pathlib.Path("./Tables").mkdir() # force it to be empty - parents=True, exist_ok=True)


#sys.exit()
triple=(python, 'Triple.py')

subprocess.call("javac Weed.java",shell=True)
weed=('java', '-cp', '.','Weed')
subprocess.call("javac javaSol/FastCmp.java",shell=True)
fastCmp=('java', '-cp', 'javaSol','FastCmp')
subprocess.call("javac javaSol/FastCmp1.java",shell=True)
fastCmp1=('java', '-cp', 'javaSol','FastCmp1')
subprocess.call("javac javaSol/FastHashMap.java",shell=True)
fastHash=('java', '-cp', 'javaSol','FastHashMap')
subprocess.call("javac javaSol/Fast.java",shell=True)
fast=('java', '-cp', 'javaSol','Fast')
subprocess.call("javac javaSol/Simple.java",shell=True)
simpJava=('java', '-cp', 'javaSol','Simple')

simpPyth=(python, 'pythonSol/simple.py')
fastPyth=(python, 'pythonSol/fast.py')
dictPyth=(python, 'pythonSol/fastDict.py')

def prodExp(prod,name,extra=[]):
    runExp(prod,simpJava,tableFile='Tables/'+name+'Java.table', Nlist=llNlist,extra=extra)
    runExp(prod,fastHash,tableFile='Tables/'+name+'JavaHash.table', Nlist=llNlist,extra=extra)
    runExp(prod,dictPyth,tableFile='Tables/'+name+'PythDict.table', Nlist=llNlist,extra=extra)
    runExp(prod,simpPyth,tableFile='Tables/'+name+'PythSimp.table', Nlist=Nlist,extra=extra)
    runExp(prod,fastPyth,tableFile='Tables/'+name+'PythFast.table', Nlist=lNlist,extra=extra)
    runExp(prod,fastCmp,tableFile='Tables/'+name+'FastCmp.table', Nlist=llNlist,extra=extra)

def _main():
    prodExp(weed,"Weed1",extra=['1'])
    prodExp(triple,"Triple")
    prodExp(weed,"Weed0",extra=['0'])


def runExp(producer,foursum,tableFile='', Nlist=[100], extra=[]):
    FastCmp = dict()
    for N in Nlist:
        FastCmp[N]=[]
    for i in range(4):
      for N in Nlist:
        start = timer()
        print( tableFile, tuple( list(producer) + [str(N)] +extra) )
        ps = subprocess.Popen(tuple( list(producer) + [str(N)] + extra), stdout=subprocess.PIPE,stderr=subprocess.DEVNULL)
        output = subprocess.check_output(foursum, stdin=ps.stdout,stderr=subprocess.DEVNULL)
        #output = subprocess.check_output(('cat'), stdin=ps.stdout)
        ps.wait()
        end = timer()
        measure = end-start
        FastCmp[N].append(measure)
        print("Time: " + str(measure)   )
        #        print('Output: ' + output.decode("utf-8") )

    if tableFile == '':
        table = sys.stdout
    else:
        table = open(tableFile,'w')
        
    for N in sorted(FastCmp.keys()):
        mm = FastCmp[N]
        mean = statistics.mean(mm)
        stddev = statistics.stdev(mm)
        print("{:4} {:.3f} {:.3f}".format(N,mean,stddev),file=table)

if __name__ == '__main__':
    _main()
