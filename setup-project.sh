#!/bin/bash

# Telescope CPS Project Setup Script
# This script creates the complete directory structure for the project

echo "Setting up Telescope CPS Project..."
echo ""

# Create main directory
mkdir -p telescope-cps
cd telescope-cps

echo "Created project directory"

# Create Java source directory structure
mkdir -p src/main/java/com/cse564/telescope/api
mkdir -p src/main/java/com/cse564/telescope/config
mkdir -p src/main/java/com/cse564/telescope/cyber
mkdir -p src/main/java/com/cse564/telescope/events
mkdir -p src/main/java/com/cse564/telescope/orchestration
mkdir -p src/main/java/com/cse564/telescope/physical
mkdir -p src/main/java/com/cse564/telescope/sa
mkdir -p src/main/java/com/cse564/telescope/service

echo "Created Java package structure"

# Create resources directory
mkdir -p src/main/resources/static

echo "Created resources directory"

# Create test directory
mkdir -p src/test/java/com/cse564/telescope

echo "Created test directory"

# Initialize git
git init

echo "Initialized Git repository"

# Create .gitignore
cat > .gitignore << 'EOF'
# Compiled class files
*.class

# Log files
*.log

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# IDE - IntelliJ IDEA
.idea/
*.iws
*.iml
*.ipr
out/

# IDE - Eclipse
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

# IDE - VS Code
.vscode/
*.code-workspace

# macOS
.DS_Store
.AppleDouble
.LSOverride

# Windows
Thumbs.db
ehthumbs.db
Desktop.ini

# Package Files
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs
hs_err_pid*

# Spring Boot
spring-boot-devtools.properties
EOF

echo "Created .gitignore"

# Display structure
echo ""
echo "Project Structure Created:"
tree -L 4 -I 'target' || ls -R

echo ""
echo "Setup complete!"
echo ""
echo "Next steps:"
echo "1. Copy all Java files from Claude's artifacts to their respective packages"
echo "2. Copy configuration files (pom.xml, application.properties, etc.)"
echo "3. Run: mvn clean install"
echo "4. Run: mvn spring-boot:run"
echo "5. Open: http://localhost:8080"
echo ""
echo "For detailed instructions, see QUICKSTART.md"