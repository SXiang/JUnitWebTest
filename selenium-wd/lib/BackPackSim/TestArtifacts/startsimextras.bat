@echo off
set PYTHON_EXE="c:\python27\python.exe"
set APP_DIR="%BUILDROOT%\host\src\main\python\host\utilities\backpackserver"
start "DummyInstrMgr" /d %APP_DIR% /min %PYTHON_EXE% dummyinstrmgr.py
start "DummyLinearFitterAlarm" /d %APP_DIR% /min %PYTHON_EXE% dummylinearfitteralarm.py
