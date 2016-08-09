%~d0
cd %~p0
cd .\..\..\..\..\
SET sanityreport=sanityreport
SET regressionreport=regressionreport
SET compliancereportreport=compliancereportreport
SET debugreport=debugreport


 ant -f selenium-wd\build.xml %sanityreport%
REM ant -f selenium-wd\build.xml %regressionreport%
REM ant -f selenium-wd\build.xml %compliancereportreport%
REM ant -f selenium-wd\build.xml %debugreport%
pause