# Usage: check_simulator_disabled.py [options]

# Options:
#   -h, --help         show this help message and exit
#   -u URL, --url=URL  URL of the 7Zip file to be downloaded.
#
# Examples:
#     python check_simulator_disabled.py -u https://p3prodstg.picarro.com/content/Analyzer/Install.7z
#     python check_simulator_disabled.py -u https://p3prodstg.picarro.com/content/Analyzer/Install.7z -s C:\Security\simulatorDisabledTest\Surveyor.db3
#     python check_simulator_disabled.py -u https://p3sqa.picarro.com/content/Analyzer/Install.7z     -s C:\Security\simulatorDisabledTest\Surveyor.db3
# 


import sys, subprocess, os, shutil, stat, tempfile, urllib
from optparse import OptionParser


def puts(str):
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
	if  bool(out.strip()):
		out_message = "\n\n" + ('-'*60) + "\n" + out +"\n" + ('-'*60) + "\n"
		puts(out_message)
		puts("\n\nSIMULATION_NOT_DISABLED: "+filename+"\n\n")
		return False
	else:
		puts("\n\nSIMULATION_DISABLED: "+filename+"\n\n")
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

# Download 7Zip file, extract it, and attempt to run the EXE in it.
def process_url(url):
	print("DOWNLOADING: "+url)
	filename, response = urllib.urlretrieve(url)
	print("DOWNLOADED_FILE: "+filename)

	if (response.subtype == 'x-7z-compressed'):
		return extract_7zip(filename)
	else:

		print("Download was not a 7Zip file: " + r.type)
		return False


# This is to fix windows cp65001 encoding issue.
import codecs
codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)
parser = OptionParser()
parser.add_option("-u", "--url", dest="url", action="append", metavar="URL",  help="URL of the 7Zip file to be downloaded.")
parser.add_option("-s", "--sim", dest="simfile", action="store",  type="string", metavar="SIM",  help="Location of the simulation file.", default="Surveyor.db3")

(options, args) = parser.parse_args()

if (options.url is None) and (len(args) < 1):
	parser.print_help()

# Check 7Zip files specified on the command line.
if options.url:
	for url in options.url:
		if not process_url(url):
			exit(1)
