#! /usr/bin/env python
# -*- coding: utf-8 -*-
# Converte ficheiros texto para html estruturado, em que referência os versículos
#
import re
import logging
import sys

LOG_FILENAME = 'log_EscalasSGO.txt'
LOG_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
LOG_FILEMOD = 'w'
LOG_LEVEL = logging.DEBUG

logging.basicConfig(filename=LOG_FILENAME,filemode=LOG_FILEMOD, format=LOG_FORMAT,level=LOG_LEVEL)
_log = logging.getLogger(str(__name__))
_log.info('Inicio ')

reobj = re.compile(r"(\D+)|(\d+)", re.MULTILINE)

if len(sys.argv) > 1:
    ficheiro = sys.argv[1]
else:
    ficheiro = "c:\Dados\Dropbox\Privado\Rosario\Doloroso1.txt"

f_out = open(ficheiro+".html", 'w')

f_out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">")
f_out.write("<html>")
f_out.write("<head>")
f_out.write("</head>")
f_out.write("<body>")



with open(ficheiro,'Ur') as f:

 

    for line in f:
        subject = line

        for match in reobj.finditer(subject):

			result = match.group(1)

			if result is None: 
				result = match.group(2) 
				f_out.write ("<sup><font color=\"#AA66CC\" size=\"-1\">"+result+"</font></sup>")
			else:
				f_out.write (result) 

        f_out.write("<br>")

f_out.write("</body>")
f_out.write("</html>")

f_out.close()
_log.info('fim ')