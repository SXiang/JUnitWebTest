#!/usr/bin/env python
import os
import sys
import urllib as ul
from common import envPathExists

# Check arguments
if (len(sys.argv) > 1):
    hostRepoDir = sys.argv[1]
else:
    sys.exit(
        "Host repository folder NOT specified\n"
        "ERROR: Correct SCRIPT SYNTAX is: setup-simulator.py [host-repository-folder]"
    )

# Ensure C:\Python27 folder exist. If not prompt user to install python 2.7.x
pythonDir = "C:\Python27"
pythonScriptsDir = "C:\Python27\Scripts"
getPipFile = "get-pip.py"
pipInstallScriptUrl = "https://bootstrap.pypa.io/get-pip.py"
if not os.path.exists(pythonDir):
    sys.exit(
        "Python 2.7.x NOT detected on the system\n"
        "ERROR: Before proceeding with this script ensure that Python 2.7.x is installed on your machine. Folder - 'C:\Python27' was NOT found on the machine"
    )

# Create .pth site-package discovery file
pthf = pythonDir + '\Lib\site-packages\picarro.pth'
print("Creating site-package discovery file - '" + pthf + "' ...")
pthPath = hostRepoDir + '\src\main\python'
f = open(pthf, 'w')
f.write(pthPath)
f.close()
print("Successfully creating site-package discovery file - '" + pthf + "'.")

# Install pip
print('Verifying if PIP is installed on the machine.')
if not os.path.exists(pythonScriptsDir + '\pip.exe'):
    print('PIP NOT found. Installing PIP...')
    content = ul.urlopen(pipInstallScriptUrl).read()
    f = open(pythonDir + '\get-pip.py', 'w')
    f.write(content)
    f.close()
    os.system('python C:\Python27\get-pip.py')
    print('PIP installed successfully.')
    if not envPathExists(pythonScriptsDir):
        os_path = os.environ['PATH']
        os.environ['PATH'] = pythonScriptsDir + ';' + os_path
        sys.exit("ERROR: PIP has been installed and added to Windows PATH environment variable. For PATH environment to take effect close this window and re-run this script from a new command window.")
else:
    print('PIP is already installed on the machine.')
