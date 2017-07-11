#!/usr/bin/python
#
"""
Copyright (c) 2016 Picarro, Inc. All rights reserved
"""
from collections import deque
from Queue import Queue
import sys
import threading

from Host.autogen import interface
from Host.Common import CmdFIFO, StringPickler
from Host.Common.Listener import Listener
from Host.Common.SharedTypes import RPC_PORT_INSTR_MANAGER
from Host.Common.EventManagerProxy import EventManagerProxy_Init, Log, LogExc

APP_NAME = "DummyInstrMgr"
EventManagerProxy_Init(APP_NAME)

if hasattr(sys, "frozen"):  # we're running compiled with py2exe
    AppPath = sys.executable
else:
    AppPath = sys.argv[0]

class DummyInstrMgr(object):
    def __init__(self):
        self.queue = Queue(100)
        self.deque = deque()
        self.dequeSize = 200
        self.server = CmdFIFO.CmdFIFOServer(("", RPC_PORT_INSTR_MANAGER),
                                            ServerName = "Instmgr",
                                            ServerDescription = "Dummy instr mgr",
                                            threaded = True)
        self.server.register_function(self.INSTMGR_ShutdownRpc)
        print "PORT", RPC_PORT_INSTR_MANAGER

    def mainLoop(self, continueFunc):
        print "Listening for commands"
        while continueFunc():
            try:
                obj = self.queue.get()
                print obj
            except:
                LogExc("Unhandled exception in main loop, attempting to continue",
                       Level=interface.LOG_LEVEL_STANDARD)


    def INSTMGR_ShutdownRpc(self, shutdownType):
        print "RPC_shutdown"
        return interface.STATUS_OK


def main():
    try:
        mgr = DummyInstrMgr()
        th = threading.Thread(target=mgr.server.serve_forever)
        th.setDaemon(True)
        th.start()
        mgr.mainLoop(th.isAlive)
        Log("Exiting on terminate request")
    except:
        LogExc("Unexpected termination", Level=interface.LOG_LEVEL_CRITICAL)

if __name__ == "__main__":
    main()
