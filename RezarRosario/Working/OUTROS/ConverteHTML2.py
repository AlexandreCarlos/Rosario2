#! /usr/bin/env python
# -*- coding: utf-8 -*-
# Converte ficheiros texto para html estruturado, em que referência os versículos
#
import re
import logging
import sys
import argparse
import os
import codecs

__author__="Alexandre Carlos"
__date__ ="$11/Out/2012 14:12:55$"
__version__ ="0.2"

global args

LOG_FILENAME = 'log_ConverteHTML2.txt'
LOG_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
LOG_FILEMOD = 'a'
LOG_LEVEL = logging.DEBUG

logging.basicConfig(filename=LOG_FILENAME,filemode=LOG_FILEMOD, format=LOG_FORMAT,level=LOG_LEVEL)
_log = logging.getLogger(str(__name__))
_log.info('Inicio ')


def Trata():
    global args

    reobj = re.compile(r"(\D+)|(\d+)", re.MULTILINE)
    
    f_out = open(args.fileout, 'w')
#    f_out = codecs.open(args.fileout, encoding='utf-8', mode='w')

    f_out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">")
    f_out.write("<html>")
    f_out.write("<head>")
    f_out.write("</head>")
    f_out.write("<body>")

    with open(args.filein,'Ur') as f:

     

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

def TemArgumentos():
    global args
    
    Out_Default = 'SameDirectory'

    _log.info('A verificar os argumentos de input')
    
    parser = argparse.ArgumentParser(description='Converte os ficheiros de texto, em ficheiros HTML cpm superscript dos números (versiculos).', \
                                        fromfile_prefix_chars='@')
                                        
    parser.add_argument('filein', \
#                        type=argparse.FileType('Ur'), \
                        help='Ficheiro em formato txt')
    parser.add_argument('fileout', \
#                        type=argparse.FileType('w'), \
                        nargs='?', \
                        default=Out_Default, \
                        help='Directório onde grava o Ficheiro HTML')

    args = parser.parse_args()
    
    path_in = os.path.split(os.path.expanduser(args.filein))
    
    _log.debug("path in = " + path_in[0])
    _log.debug("file out = " + args.fileout)
    
    if args.fileout == Out_Default:
        path_out = path_in[0]
    else:
        path_out = os.path.expanduser(args.fileout)
    
    _log.debug("Novo path out = " + path_out)
    
    if os.path.isdir(path_out) or path_out == '':
        fn_in = os.path.splitext(path_in[1])
        fn_out = fn_in[0]+'.html'
        path_out = os.path.join(path_out, fn_out)
    
    args.filein = os.path.join(path_in[0], path_in[1])
    args.fileout = path_out

    _log.info('argumentos utilizados: ' + str(args))
    
    return True


if __name__ == "__main__":
    print 'Inicio do programa'
    _log.info('Inicio do programa')
    
    if TemArgumentos():    
        Trata()
        
    _log.info('Fim do programa')
    _log.info(' ')  
    print 'Fim do programa'

