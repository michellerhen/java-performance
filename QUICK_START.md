# ⚡ Quick Start - Java Performance Project

## 🚀 Quick Run

### Option 1: Automatic Script (Recommended)
```bash
./run.sh
```

### Option 2: Manual Commands
```bash
# Compile
mkdir -p target/classes
javac -d target/classes -sourcepath src/main/java src/main/java/org/example/Main.java

# Run
java -cp target/classes org.example.Main
```

### Option 3: Maven (if installed)
```bash
mvn clean compile exec:java -Dexec.mainClass="org.example.Main"
```

## 📋 Requirements
- Java 21+ (run `java -version` to check)

## 🎯 What the Project Does

Runs 5 performance benchmarks:
1. **Collections** - ArrayList vs LinkedList vs HashSet vs TreeSet
2. **Strings** - String += vs StringBuilder
3. **Streams** - For loops vs Stream API
4. **Parallel** - Sequential vs Parallel processing
5. **Cache** - Impact of memoization

## ⏱️ Execution Time
- Total: ~2-5 seconds
- Each benchmark: ~1 second

## 📊 Expected Results

You'll see dramatic differences like:
- StringBuilder **93x faster** than String +=
- HashSet **5x faster** than LinkedList
- Cache **2500x faster** than without cache

## 🎥 For Video Recording

1. Increase terminal font size (16-20pt)
2. Use dark theme
3. Run: `./run.sh`
4. Read **VIDEO_GUIDE.md** for complete script

## 🐛 Troubleshooting

### "command not found: javac"
Install JDK 21: https://www.oracle.com/java/technologies/downloads/

### "Unsupported class file major version"
Update your Java to version 21 or higher

### Strange characters on Windows
Use Windows Terminal or Git Bash for ANSI color support

## 📚 More Information

- **README.md** - Complete documentation
- **VIDEO_GUIDE.md** - Video script
- **USE_CASES.md** - Practical examples

---

Ready! In 10 seconds you can be running the benchmarks! 🚀

