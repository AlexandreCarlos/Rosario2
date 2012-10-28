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
__date__ ="$11/Out/2012 15:34:25$"
__version__ ="0.2"

global args

LOG_FILENAME = 'log_ConverteJAVA1.txt'
LOG_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
LOG_FILEMOD = 'a'
LOG_LEVEL = logging.DEBUG

logging.basicConfig(filename=LOG_FILENAME,filemode=LOG_FILEMOD, format=LOG_FORMAT,level=LOG_LEVEL)
_log = logging.getLogger(str(__name__))
_log.info('Inicio ')


def Trata():
    global args

    reobj = re.compile('(")|([^"\r\n]+)', re.MULTILINE)

    f_out = open(args.fileout, 'a')
    #f_out = codecs.open(args.fileout, encoding='utf-8', mode='w')

    _misterio = "private static final String " + args.misterio.upper() + " = \""

    with open(args.filein,'Ur') as f:
    #f = codecs.open(args.filein, encoding='utf-8')

        for line in f:
            subject = line.strip("\n")
            _log.debug("subject: "+subject)
            for match in reobj.finditer(subject):
                result = match.group(1)
                if result is None: 
                    result = match.group(2) 
                    _log.debug("Match2: "+result)
                    _misterio += result
                else:
                    _misterio += "\\\"" 
                    _log.debug("Match1: "+result)
            _log.debug("linha: "+_misterio)

    _misterio += "\";\n"
    f_out.write(_misterio)
    f_out.close()
    _log.info('fim ')

def TemArgumentos():
    global args
    
    Out_Default = 'SameDirectory'

    _log.info('A verificar os argumentos de input')
    
    parser = argparse.ArgumentParser(description='Converte os ficheiros HTML, em constantes JAVA.', \
                                        fromfile_prefix_chars='@')
                                        
    parser.add_argument('filein', \
#                        type=argparse.FileType('Ur'), \
                        help='Ficheiro em formato HTML')
    parser.add_argument('fileout', \
#                        type=argparse.FileType('w'), \
                        nargs='?', \
                        default=Out_Default, \
                        help='Directório onde grava o Ficheiro JAVA')

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
        fn_out = fn_in[0]+'.java'
        args.misterio = fn_in[0]
        path_out = os.path.join(path_out, fn_out)
    
    fn_in = os.path.splitext(path_in[1])
    args.misterio = fn_in[0]
    
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

