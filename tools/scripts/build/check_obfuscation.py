import sys, subprocess, os

# Examples:
#    python testDeobfuscator.py nonobf
#    python testDeobfuscator.py install
#Pre-req:
#	http://de4dot.com/ deobfuscator should be downloaded and extracted
#	Script should be copied to de4dot folder after extraction


# Returns true if decompilation failed, or false if it was succesful.
def process_file(filename):
	print "CHECKING FILE =", filename
	pipe = subprocess.Popen(["de4dot.exe", "-f", filename], stdout=subprocess.PIPE, stderr=None)
	out, err = pipe.communicate()

	# de4dot return 0 if decompile was succesful, or 1 if it fails.
	if (pipe.returncode==0):
		print "\n\nFILE_NOT_OBFUSCATED:", filename, "\n\n"
		return False
	else:
		return True


def process_dir(dirname):
	for fname in os.listdir(dirname):
		fname_check = fname.lower()
		if (fname_check.endswith(".exe") or fname_check.endswith(".dll")) and fname_check.startswith("picarro"):
			ret = process_file(os.path.join(dirname, fname))
			if ret==False:
				exit(1)


process_dir(sys.argv[1])

