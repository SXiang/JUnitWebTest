# Usage: check_obfuscation.py [options]
#
# Options:
#   -h, --help            show this help message and exit
#   -f FILE, --file=FILE  File to be checked.
#   -d DIR, --dir=DIR     Directtory to be checked.
#	-z ZIP, --7zip=ZIP    7Zip to be checked.
#   -t, --teamcity        Print Teamcity service messages.



# Examples:
#     python testDeobfuscator.py nonobf
#     python testDeobfuscator.py install
#
# Pre-req:
#     http://de4dot.com/ deobfuscator should be downloaded and extracted
#     Script should be copied to de4dot folder after extraction

# Teamcity: Use an external tool that the build relies on:
# https://confluence.jetbrains.com/pages/viewpage.action?pageId=48106023#HowTo...-UseanExternalToolthatMyBuildRelieson



import sys, subprocess, os, shutil, stat
from optparse import OptionParser

############################################################

# Build Script Interaction with Teamcity:
# http://confluence.jetbrains.com/display/TCD8/Build+Script+Interaction+with+TeamCity#BuildScriptInteractionwithTeamCity-teamcity-info.xml	

def tc_escape(str):
	return str.replace('|', '||').replace("'", "|'").replace("\n", "|n").replace("\r", "|r").replace('[', '|[').replace(']', '|]')

def tc_suite_started(dirname):
	if options.teamcity:
		print "##teamcity[testSuiteStarted  name='" + tc_escape(dirname) + "']"

def tc_suite_finished(dirname):
	if options.teamcity:
		print "##teamcity[testSuiteFinished name='" + tc_escape(dirname) + "']"

def tc_test_started(filename):
	if options.teamcity:
		print "##teamcity[testStarted name='" + tc_escape(filename) + "']"

def tc_test_finished(filename):
	if options.teamcity:
		print "##teamcity[testFinished name='" + tc_escape(filename) + "']"

def tc_test_failed(filename, message, details):
	if options.teamcity:
		print "##teamcity[testFailed name='" + tc_escape(filename) + "' message='" + tc_escape(message) + "' details='" + tc_escape(details) + "']"

############################################################

def puts(str):
	if options.teamcity!=True:
		print(str)


# Returns true if decompilation failed, or false if it was succesful.
def process_file(filename):
	tc_test_started(filename)
	puts("Testing file for obfuscation: " + filename)
	pipe = subprocess.Popen(["de4dot.exe", "-f", filename], stdout=subprocess.PIPE, stderr=None)
	out, err = pipe.communicate()

	# de4dot return 0 if decompile was succesful, or 1 if it fails.
	if (pipe.returncode==0):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nFILE_NOT_OBFUSCATED: "+filename+"\n\n")

		tc_test_failed(filename, 'File Was De-Obfuscated!', out_message)
		tc_test_finished(filename)
		return False
	else:
		tc_test_finished(filename)
		return True


def process_dir(dirname):
	tc_suite_started(dirname+os.sep)
	puts("DIRECTORY: " + dirname)
	for dirpath, dirs, files in os.walk(dirname):
		for fname in files:
			fname_check = fname.lower()
			if (fname_check.endswith(".exe") or fname_check.endswith(".dll")) and fname_check.startswith("picarro"):
				ret = process_file(os.path.join(dirname, fname))
				if ret==False:
					tc_suite_finished(dirname+os.sep)
					exit(1)
		for dname in dirs:
			process_dir(os.path.join(dirname, dname))
	tc_suite_finished(dirname+os.sep)

def extract_7z(path):
	tc_suite_started(path+os.sep)
	if not os.path.exists("C:\extract"):
		puts("Creating Folder: C:\\extract")
		os.makedirs("C:\\extract")
    	os.chmod("C:\\extract", stat.S_IRWXU)
    	os.chmod("C:\\extract", stat.S_IRWXG)
    	os.chmod("C:\\extract", stat.S_IRWXO)
	pipe = subprocess.Popen([r"C:\Program Files\7-Zip\7z.exe", "e", path, "-oC:\\extract", "-y"], stdout=subprocess.PIPE, stderr=None)
	puts("Extracted: "+path+ " to: C:\extract")
	out, err = pipe.communicate()
	process_dir("C:\\extract")
	os.system('rmdir /S /Q \"{}\"'.format("C:\\extract"))
	#shutil.rmtree("C:\extract")
	

# This is to fix windows cp65001 encoding issue.
import codecs
codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)
parser = OptionParser()
parser.add_option("-f", "--file",      dest="filenames",   action="append", metavar="FILE", help="File to be checked.")
parser.add_option("-d", "--dir",       dest="directories", action="append", metavar="DIR",  help="Directtory to be checked.")
parser.add_option("-z", "--7zip",       dest="zip", action="append", metavar="ZIP",  help="7Zip to be checked.")
parser.add_option("-t", "--teamcity",  dest="teamcity",    action="store_true",  help="Print Teamcity service messages.")

(options, args) = parser.parse_args()

if (options.directories is None) and (options.filenames is None) and (options.zip is None) and (len(args) < 1):
	parser.print_help()

if options.filenames:
	for fname in options.filenames:
		ret = process_file(fname)
		if ret==False:
			exit(1)

if options.zip:
	for zip in options.zip:
		extract_7z(zip)	

if options.directories:
	for dname in options.directories:
		process_dir(dname)

if args:
	for dname in args:
		process_dir(dname)
