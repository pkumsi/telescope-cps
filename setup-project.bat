@echo off
REM Telescope CPS Project Setup Script for Windows
REM This script creates the complete directory structure for the project

echo.
echo Setting up Telescope CPS Project...
echo.

REM Create main directory
mkdir telescope-cps
cd telescope-cps

echo Created project directory

REM Create Java source directory structure
mkdir src\main\java\com\cse564\telescope\api
mkdir src\main\java\com\cse564\telescope\config
mkdir src\main\java\com\cse564\telescope\cyber
mkdir src\main\java\com\cse564\telescope\events
mkdir src\main\java\com\cse564\telescope\orchestration
mkdir src\main\java\com\cse564\telescope\physical
mkdir src\main\java\com\cse564\telescope\sa
mkdir src\main\java\com\cse564\telescope\service

echo Created Java package structure

REM Create resources directory
mkdir src\main\resources\static

echo Created resources directory

REM Create test directory
mkdir src\test\java\com\cse564\telescope

echo Created test directory

REM Initialize git
git init

echo Initialized Git repository

echo.
echo Setup complete!
echo.
echo Next steps:
echo 1. Copy all Java files from Claude's artifacts to their respective packages
echo 2. Copy configuration files (pom.xml, application.properties, etc.)
echo 3. Run: mvn clean install
echo 4. Run: mvn spring-boot:run
echo 5. Open: http://localhost:8080
echo.
echo For detailed instructions, see QUICKSTART.md
echo.
pause