#!/bin/bash

# Script to compile and run the Performance project

echo "🚀 Compiling Java Performance project..."

# Create target directory if it doesn't exist
mkdir -p target/classes

# Compile the project
javac -d target/classes -sourcepath src/main/java src/main/java/org/example/Main.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✅ Compilation completed successfully!"
    echo ""
    echo "▶️  Running benchmarks..."
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo ""

    # Run the program
    java -cp target/classes org.example.Main

    echo ""
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "✅ Execution completed!"
else
    echo "❌ Compilation error!"
    exit 1
fi

