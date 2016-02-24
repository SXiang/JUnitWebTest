# Usage: check_obfuscation.py [options]
#
# Options:
#   -h, --help            show this help message and exit
#   -f FILE, --file=FILE  File to be checked.
#   -d DIR, --dir=DIR     Directtory to be checked.
#   -z ZIP, --7zip=ZIP    7Zip to be checked.
#   -e EXE, --exe=EXE     Specify the de4dot executable file with path.
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



import sys, subprocess, os, shutil, stat, tempfile
from optparse import OptionParser

############################################################

# Build Script Interaction with Teamcity:
# http://confluence.jetbrains.com/display/TCD8/Build+Script+Interaction+with+TeamCity#BuildScriptInteractionwithTeamCity-teamcity-info.xml	

# Escapes a string so that it is suitable to be embedded in Teamcity service messagse.
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
	if not options.teamcity:
		print(str)


# Returns True if decompilation failed, or False if it was succesful.
def process_file(filename):
	tc_test_started(filename)
	puts("Testing file for obfuscation: " + filename)
	pipe = subprocess.Popen([options.executable, "-f", filename], stdout=subprocess.PIPE, stderr=None)
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

# Returns True if decompilation failed, or False if it was succesful.
def process_dir(dirname):
	tc_suite_started(dirname+os.sep)
	puts("DIRECTORY: " + dirname)
	for dirpath, dirs, files in os.walk(dirname):
		for fname in files:
			fname_check = fname.lower()
			if (fname_check.endswith(".exe") or fname_check.endswith(".dll")) and fname_check.startswith("picarro"):
				if not process_file(os.path.join(dirname, fname)):
					tc_suite_finished(dirname+os.sep)
					return False
		for dname in dirs:
			if not process_dir(os.path.join(dirname, dname)):
				tc_suite_finished(dirname+os.sep)
				return False
	tc_suite_finished(dirname+os.sep)
	return True

# Decompress and then process a  7Zip file. Returns True if decompilation failed, or False if it was succesful.
def extract_7zip(path):
	tc_suite_started('Extracting '+path)
	tmpdir = tempfile.mkdtemp()
	print("TEMPORARY_DIRECTORY: "+tmpdir)
	os.chmod(tmpdir, stat.S_IRWXU)
	os.chmod(tmpdir, stat.S_IRWXG)
	os.chmod(tmpdir, stat.S_IRWXO)
	pipe = subprocess.Popen([r"C:\Program Files\7-Zip\7z.exe", "e", path, "-o"+tmpdir, "-y"], stdout=subprocess.PIPE, stderr=None)
	puts("Extracted: "+path+ " to: "+tmpdir)
	out, err = pipe.communicate()
	if (pipe.returncode!=0):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nFILE_NOT_EXTRACTED: "+path+"\n\n")
		tc_test_failed('Extracting '+path, 'File Was not extracted!', out_message)
		tc_suite_finished('Extracting '+path)
	ret = process_dir(tmpdir)
	os.chmod(tmpdir, stat.S_IWUSR)
	shutil.rmtree(tmpdir, False)
	tc_suite_finished('Extracting '+path)
	return ret


# This is to fix windows cp65001 encoding issue.
import codecs
codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)
parser = OptionParser()
parser.add_option("-f", "--file",      dest="filenames",   action="append", metavar="FILE", help="File to be checked.")
parser.add_option("-d", "--dir",       dest="directories", action="append", metavar="DIR",  help="Directtory to be checked.")
parser.add_option("-z", "--7zip",      dest="zip",         action="append", metavar="ZIP",  help="7Zip to be checked.")
parser.add_option("-e", "--exe",       dest="executable",  action="store",  metavar="EXE",  type="string", help="Specify the de4dot executable file with path.", default="de4dot.exe")
parser.add_option("-t", "--teamcity",  dest="teamcity",    action="store_true",  help="Print Teamcity service messages.")

(options, args) = parser.parse_args()

if (options.directories is None) and (options.filenames is None) and (options.zip is None) and (len(args) < 1):
	parser.print_help()


print "EXCUTABLE: "+options.executable

# Check individual files specified on teh command line.
if options.filenames:
	for fname in options.filenames:
		if not process_file(fname):
			exit(1)

# Check 7Zip files specified on teh command line.
if options.zip:
	for zip in options.zip:
		if not extract_7zip(zip):
			exit(1)

# Check directories specified on teh command line.
if options.directories:
	for dname in options.directories:
		if not process_dir(dname):
			exit(1)

# Treat any remaining command line arguments as directory names and process them.
if args:
	for dname in args:
		if not process_dir(dname):
			exit(1)

