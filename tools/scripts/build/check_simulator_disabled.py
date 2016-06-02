# Usage: check_simulator_disabled.py [options]

# Options:
#   -h, --help         show this help message and exit
#   -z, --7zip  path to the 7Zip file to be downloaded.
#	-s, --sim path to db3 file
#	-t, --teamcity        Print Teamcity service messages.
#
# Examples:
#     python check_simulator_disabled.py -z path/to/Install.7z
#     python check_simulator_disabled.py -z path/to/Install.7z -s C:\Security\simulatorDisabledTest\Surveyor.db3
#     python check_simulator_disabled.py -z path/to/Install.7z -s C:\Security\simulatorDisabledTest\Surveyor.db3
# 

import sys, subprocess, os, shutil, stat, tempfile, urllib
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

# Returns True if EXE did NOT execute, or False if it was succesful.
def check_disabled_exe(dirname):
	puts("DIRECTORY: " + dirname)
	filename = dirname + os.sep + 'Picarro.Surveyor.Analyzer.exe'
	puts("Check simluator disabled: " + filename)
	print("SIMFILE: " + options.simfile)
	cmd = [filename, "-simulation:"+options.simfile]
	print("CMD: " + ' '.join(cmd))
	pipe = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=None)
	out, err = pipe.communicate()
	print("OUT: " + out)
	invalidArg="An unknown command-line option was found: DEFINE: simulation ="
	if  (bool(out.strip()) and (invalidArg not in out.strip())):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nSIMULATION_NOT_DISABLED: "+filename+"\n\n")
		tc_test_failed(filename, 'Simulator not disabled!', filename)
		tc_test_finished(filename)
		return False
	else:
		puts("\n\nSIMULATION_DISABLED: "+filename+"\n\n")
		tc_suite_finished(filename+os.sep)
		return True

# Decompress and then process a 7Zip file. Returns True if EXE did NOT execute, or False if it was succesful.
def extract_7zip(path):
	tmpdir = tempfile.mkdtemp()
	print("TEMPORARY_DIRECTORY: "+tmpdir)
	os.chmod(tmpdir, stat.S_IRWXU)
	os.chmod(tmpdir, stat.S_IRWXG)
	os.chmod(tmpdir, stat.S_IRWXO)
	pipe = subprocess.Popen([r"C:\Program Files\7-Zip\7z.exe", "e", path, "-o"+tmpdir, "-y"], stdout=subprocess.PIPE, stderr=None)
	puts("EXTRACTED: "+path+ " TO: "+tmpdir)
	out, err = pipe.communicate()
	if (pipe.returncode!=0):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nFILE_NOT_EXTRACTED: "+path+"\n\n")
	ret = check_disabled_exe(tmpdir)
	os.chmod(tmpdir, stat.S_IWUSR)
	# shutil.rmtree(tmpdir, False)
	return ret

# This is to fix windows cp65001 encoding issue.
import codecs
codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)
parser = OptionParser()
parser.add_option("-z", "--7zip", dest="zip", action="append", metavar="ZIP",  help="7zip file to be validated")
parser.add_option("-s", "--sim", dest="simfile", action="store",  type="string", metavar="SIM",  help="Location of the simulation file.", default="Surveyor.db3")
parser.add_option("-t", "--teamcity",  dest="teamcity",    action="store_true",  help="Print Teamcity service messages.")

(options, args) = parser.parse_args()

if (options.zip is None) and (len(args) < 1):
	parser.print_help()

# Check 7Zip files specified on the command line.
if options.zip:
	for zip in options.zip:
		if not extract_7zip(zip):
			exit(1)
