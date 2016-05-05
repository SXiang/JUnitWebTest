#!/bin/bash
#Usage: ./findUnCythonizedFiles.sh <path to cythonized pythom worker zip file> <python worker e.g:workercommon/capturewprker/coverageworker/eqworker>
cd $1
pythonWorkerZip=$(find . -type f -name $2*.gz)
dir=/tmp/$2/$(date +"%Y%m%d%H%M%S")
mkdir -p $dir 
tar -zxf $pythonWorkerZip -C $dir
cd $dir 
NonCythonizedFiles=$(find . -type f -name '*.py' -o -name '*.pyc' -o -name '*.pyf' -o -name '*.c' -o -name '*.cpp'|grep -v 'setup.py' |grep -v '__init__.py'|grep -v 'run.*worker.py'|wc -l)
if (($NonCythonizedFiles > 0)); then
	printf "Following files are not Cythonized"
	find . -type f -name '*.py' -o -name '*.pyc' -o -name '*.pyf' -o -name '*.c' -o -name '*.cpp'|grep -v 'setup.py' |grep -v '__init__.py'|grep -v 'run.*worker.py'
	cd /tmp
	rm -r -f $dir
	exit 1
else
	cd /tmp
	rm -r -f $dir
	exit 0
fi
