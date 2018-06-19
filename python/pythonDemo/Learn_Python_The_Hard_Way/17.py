from sys import argv
from os.path import exists

script, from_file, to_file = argv

input_file = open(from_file)
output_file = open(to_file,'w')

indata = input_file.read()
output_file.write(indata)

input_file.close()
output_file.close()
