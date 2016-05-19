# Usage: check_simulator_disabled.py [options]

# Options:
#   -h, --help         show this help message and exit
#   -z, --7zip  path to the 7Zip file to be downloaded.
#	-d, --db3 path to db3 file
#	-t, --teamcity        Print Teamcity service messages.
#
# Examples:
#     python check_simulator_disabled.py -z path/to/Install.7z -d path/to/where/db3/file/is/created 
#     python check_simulator_disabled.py -z path/to/Install.7z -d C:\Security\simulatorDisabledTest\Surveyor.db3    
# 

import sys, subprocess, os, shutil, stat, tempfile, urllib, sqlite3, time
from optparse import OptionParser

############################################################

# Build Script Interaction with Teamcity:
# http://confluence.jetbrains.com/display/TCD8/Build+Script+Interaction+with+TeamCity#BuildScriptInteractionwithTeamCity-teamcity-info.xml	

# Escapes a string so that it is suitable to be embedded in Teamcity service messagse.
def tc_escape(str):
	return str.replace('|', '||').replace("'", "|'").replace("\n", "|n").replace("\r", "|r").replace('[', '|[').replace(']', '|]')

def tc_test_started(dirname):
	if options.teamcity:
		print "##teamcity[testStarted name='" + tc_escape(dirname) + "']"
		
def tc_suite_started(dirname):
	if options.teamcity:
		print "##teamcity[testSuiteStarted  name='" + tc_escape(dirname) + "']"

def tc_suite_finished(dirname):
	if options.teamcity:
		print "##teamcity[testSuiteFinished name='" + tc_escape(dirname) + "']"

def tc_test_finished(dirname):
	if options.teamcity:
		print "##teamcity[testFinished name='" + tc_escape(dirname) + "']"

def tc_test_failed(dirname, message, details):
	if options.teamcity:
		print "##teamcity[testFailed name='" + tc_escape(dirname) + "' message='" + tc_escape(message) + "' details='" + tc_escape(details) + "']"

############################################################

def puts(str):
	if not options.teamcity:
		print(str)

# Returns True if db3 file is encrypted, or False if it is not.
def check_db3file():
	db3location=options.db3file
	if not os.path.exists(db3location):
		puts("DB3 file does not exist: " + db3location)
		return False
	puts("Checking DB3 file: " + db3location)
	with open(db3location, "rb") as file:
    		byte = file.read(21) # read the first 21 bytes    	
	file.close()	
	if  (byte[20]=='\x00'): #20th byte is 0 when not encrypted
		puts("\n\nDB3 file is not encrypted: "+db3location+"\n\n")
		return False
	else:
		puts("\n\nDB3 file is encrypted: "+db3location+"\n\n")
		tc_suite_finished(db3location+os.sep)
		return True

# Run analyzer.exe with supressHost. Sub process will keep retrying connecting to host, so do not wait for subprocess to complete.
#Instead wait for 5 sec till db3 files are downloaded.  
def run_analyzer(dirname):
	puts("DIRECTORY: " + dirname)
	filename = dirname + os.sep + 'Picarro.Surveyor.Analyzer.exe'
	puts("Excuting Analyzer: " + filename)
	cmd = [filename, "--SuppressHost"]
	pipe = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	time.sleep(5)
	pipe.kill()
		
# Decompress and then process a 7Zip file. Returns True if EXE did NOT execute, or False if it was succesful.
def extract_7zip(path):
	tmpdir = tempfile.mkdtemp()
	print("TEMPORARY_DIRECTORY: "+tmpdir)
	os.chmod(tmpdir, stat.S_IRWXU)
	os.chmod(tmpdir, stat.S_IRWXG)
	os.chmod(tmpdir, stat.S_IRWXO)
	pipe = subprocess.Popen([r"C:\Program Files\7-Zip\7z.exe", "x", path, "-o"+tmpdir, "-r", "-y"], stdout=subprocess.PIPE, stderr=None)
	puts("EXTRACTED: "+path+ " TO: "+tmpdir)
	out, err = pipe.communicate()
	if (pipe.returncode!=0):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nFILE_NOT_EXTRACTED: "+path+"\n\n")
	run_analyzer(tmpdir)
	ret=False
	if check_db3file():
		ret= True
	return ret

# This is to fix windows cp65001 encoding issue.
import codecs
codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)
parser = OptionParser()
parser.add_option("-z", "--7zip", dest="zip", action="append", metavar="ZIP",  help="7zip file to be validated")
parser.add_option("-d", "--db3", dest="db3file", action="store",  type="string", metavar="DB3",  help="Location of the db3 file.", default="Surveyor.db3")
parser.add_option("-t", "--teamcity",  dest="teamcity",    action="store_true",  help="Print Teamcity service messages.")

(options, args) = parser.parse_args()

if (options.zip is None) and (len(args) < 2):
	parser.print_help()

# Check 7Zip files specified on the command line.
if options.zip:
	for zip in options.zip:
		if not extract_7zip(zip):
			exit(1)
