@echo off
echo Setting up Java Environment...
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.18.8-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java Version:
java -version

echo.
echo Starting Employee Management System...
.\mvnw.cmd spring-boot:run

pause
