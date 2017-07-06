#!/usr/bin/python
#
"""
File Name: DummyLinearFitterAlarm.py
Purpose:
    Generate an audible alarm using the soundcard for locating a gas source

File History:
    16-10-25 sze   Created first release

Copyright (c) 2016 Picarro, Inc. All rights reserved
"""
from collections import deque
import numpy as np
from Queue import Queue
import sys
import threading

from Host.autogen import interface
from Host.Common import CmdFIFO, StringPickler
from Host.Common.Listener import Listener
from Host.Common.SharedTypes import BROADCAST_PORT_LINEARFITRESULTS, RPC_PORT_AUDIO_ALARM, RPC_PORT_DRIVER
from Host.Common.EventManagerProxy import EventManagerProxy_Init, Log, LogExc

APP_NAME = "LinearFitterAlarm"
EventManagerProxy_Init(APP_NAME)

if hasattr(sys, "frozen"):  # we're running compiled with py2exe
    AppPath = sys.executable
else:
    AppPath = sys.argv[0]

class LinearFitterAlarm(object):
    def __init__(self):
        self.queue = Queue(100)
        self.deque = deque()
        self.dequeSize = 200
        self.minFiltDuration = 5.0  # Seconds for computation of background
        self.background = None
        self.listener = Listener(self.queue, BROADCAST_PORT_LINEARFITRESULTS, StringPickler.ArbitraryObject, retry=True, logFunc=Log)
        self.mute = False
        self.volume = 20
        self.absoluteThreshold = 5.0
        self.amplitude = 0.2
        self.fs = 22050
        self.alarm = self.makeAlarm()
        self.rpcServer = CmdFIFO.CmdFIFOServer(("", RPC_PORT_AUDIO_ALARM),
                                               ServerName="AudioAlarm",
                                               ServerDescription="AudioAlarm",
                                               ServerVersion="1.0",
                                               threaded=True)
        self.rpcServer.register_function(self.RPC_getAmplitude, NameSlice=4)
        self.rpcServer.register_function(self.RPC_getThreshold, NameSlice=4)
        self.rpcServer.register_function(self.RPC_getVolume, NameSlice=4)
        self.rpcServer.register_function(self.RPC_getBackgroundDuration, NameSlice=4)
        self.rpcServer.register_function(self.RPC_getMute, NameSlice=4)
        self.rpcServer.register_function(self.RPC_setAmplitude, NameSlice=4)
        self.rpcServer.register_function(self.RPC_setThreshold, NameSlice=4)
        self.rpcServer.register_function(self.RPC_setVolume, NameSlice=4)
        self.rpcServer.register_function(self.RPC_setBackgroundDuration, NameSlice=4)
        self.rpcServer.register_function(self.RPC_setMute, NameSlice=4)

    def mainLoop(self, continueFunc):
        print "Listening to fast methane concentration"
        while continueFunc():
            try:
                obj = self.queue.get()
                ts = obj["timestamp"]
                CH4conc = obj["fitResults"][1]
                if CH4conc > 0:
                    self.deque.append((ts, CH4conc))
                # Keep getting until we empty out the queue, or if there are no data
                #   to process in the deque (which is used to find the background)
                if not self.queue.empty() or len(self.deque) == 0:
                    continue
                # Only keep the most recent data in the queue (up to self.minFiltDuration ago)
                while len(self.deque) > 0 and (len(self.deque) > self.dequeSize or self.deque[0][0] < ts - 1000*self.minFiltDuration):
                    self.deque.popleft()
                # Use a threshold which is the minimum of absoluteThreshold and (background + amplitude)
                background = min([conc for _, conc in self.deque])
                if CH4conc < min(background + self.amplitude, self.absoluteThreshold):
                    continue
                # print CH4conc, background
            except:
                LogExc("Unhandled exception in main loop, attempting to continue", Level=interface.LOG_LEVEL_STANDARD)

    def makeAlarm(self):
        t = np.arange(self.fs, dtype="float32") / float(self.fs)
        y = np.array(np.sin(2 * np.pi * 2400 * t), dtype='float32')
        y[(t > 0.13) & (t < 0.2)] = 0
        y[(t > 0.33) & (t < 0.4)] = 0
        y[(t > 0.53) & (t < 0.6)] = 0
        y[(t > 0.73)] = 0
        return y

    def RPC_getAmplitude(self):
        print "RPC_getAmplitude", self.amplitude
        return self.amplitude

    def RPC_getThreshold(self):
        return self.absoluteThreshold

    def RPC_getBackgroundDuration(self):
        return self.minFiltDuration

    def RPC_getMute(self):
        return self.mute

    def RPC_getVolume(self):
        if not self.mute:
            try:
                # self.volume = Driver.rdDasReg(interface.AUDIO_AMPLIFIER_VOLUME_REGISTER)
                pass
            except:
                pass
        return self.volume

    def RPC_setAmplitude(self, amplitude):
        print "RPC_setAmplitude", amplitude
        self.amplitude = amplitude
        return interface.STATUS_OK

    def RPC_setBackgroundDuration(self, minFiltDuration):
        self.minFiltDuration = minFiltDuration
        return interface.STATUS_OK

    def RPC_setMute(self, mute):
        self.mute = bool(mute)
        try:
            if mute:
                pass ## Driver.wrDasReg(interface.AUDIO_AMPLIFIER_VOLUME_REGISTER, 0)
            else:
                pass ## Driver.wrDasReg(interface.AUDIO_AMPLIFIER_VOLUME_REGISTER, self.volume)
        except:
            pass
        return interface.STATUS_OK

    def RPC_setThreshold(self, threshold):
        self.absoluteThreshold = threshold
        return interface.STATUS_OK

    def RPC_setVolume(self, volume):
        self.volume = volume
        try:
            if not self.mute:
                pass ## return Driver.wrDasReg(interface.AUDIO_AMPLIFIER_VOLUME_REGISTER, volume)
        except:
            pass
        return interface.STATUS_OK

def main():
    try:
        lfa = LinearFitterAlarm()
        th = threading.Thread(target=lfa.rpcServer.serve_forever)
        th.setDaemon(True)
        th.start()
        lfa.mainLoop(th.isAlive)
        Log("Exiting on terminate request")
    except:
        LogExc("Unexpected termination", Level=interface.LOG_LEVEL_CRITICAL)

if __name__ == "__main__":
    main()
