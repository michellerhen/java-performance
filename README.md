# 🚀 Java Performance Demonstration Project

A complete Java 21 performance demonstration project, showing different aspects of optimization and performance comparisons through practical and visual benchmarks.

## 📋 About the Project

This project demonstrates important Java performance concepts through practical and measurable examples. It compares different approaches to common programming tasks, showing the real performance impact of each choice.

## ✨ Features

The project includes 5 benchmark categories:

### 1. 📦 Collections Performance
Compares the performance of different data structures:
- **ArrayList** - Fast access by index
- **LinkedList** - Efficient insertions and removals
- **HashSet** - Extremely fast searches
- **TreeSet** - Sorted data with fast searches

### 2. 📝 String vs StringBuilder
Demonstrates the impact of string concatenation:
- **String with +=** - Inefficient method
- **StringBuilder** - Optimized solution
- **StringBuffer** - Thread-safe but slower
- **String.concat()** - Alternative method

### 3. 🔄 Stream API vs Traditional Loops
Compares different ways to iterate over collections:
- **For Loop** - Classic approach
- **Stream API** - Functional programming
- **Iterator** - Manual iteration
- **For Index** - Access by index

### 4. ⚡ Parallel vs Sequential Streams
Shows when parallelism brings benefits:
- **Sequential Stream** - Sequential processing
- **Parallel Stream** - Parallel processing
- Comparison with simple vs complex operations

### 5. 💾 Cache Impact
Demonstrates the importance of caching:
- **Without Cache** - Repeated calculations
- **With Cache** - Memoization
- **Pre-computed** - Values in array

## 🎯 Visual Output

The project uses ANSI colors in the console for a visually appealing presentation:
- 🟢 Green for fastest results
- 🔵 Blue for information
- 🟡 Yellow for tips and summaries
- Interface with borders and symbols for better visualization

## 🛠️ Technologies Used

- **Java 21** - Latest Java version
- **Maven** - Dependency management
- **JVM** - Native JVM optimizations

## 📦 Project Structure

```
Performance/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── Main.java                    # Main orchestrator
│                   ├── benchmarks/                  # Individual benchmarks
│                   │   ├── CollectionBenchmark.java
│                   │   ├── StringBenchmark.java
│                   │   ├── StreamBenchmark.java
│                   │   ├── ParallelBenchmark.java
│                   │   └── CacheBenchmark.java
│                   └── utils/                       # Utilities
│                       ├── ConsoleColors.java       # ANSI colors
│                       ├── PerformanceTimer.java    # Time measurement
│                       └── PerformanceReporter.java # Report generator
├── pom.xml
└── README.md
```

## 🚀 How to Run

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher (optional)

### Running the Project

#### Option 1: Quick Script (Recommended)
```bash
./run.sh
```

#### Option 2: Manual Compilation
```bash
# Compile
mkdir -p target/classes
javac -d target/classes -sourcepath src/main/java src/main/java/org/example/Main.java

# Run
java -cp target/classes org.example.Main
```

#### Option 3: Using Maven
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 📊 Sample Output

```
╔═══════════════════════════════════════════════════════════╗
║                                                           ║
║     JAVA PERFORMANCE DEMONSTRATION PROJECT               ║
║     Performance Analysis in Java 21                       ║
║                                                           ║
╚═══════════════════════════════════════════════════════════╝

╔════════════════════════════════════════════╗
║   1. COLLECTIONS PERFORMANCE               ║
╚════════════════════════════════════════════╝

✓ ArrayList: 14.58 ms
✓ LinkedList: 46.64 ms
✓ HashSet: 9.21 ms ⚡ FASTEST
✓ TreeSet: 15.71 ms

📊 Collections
  HashSet       : 9.21 ms ⚡ FASTEST
  ArrayList     : 14.58 ms (1.58x slower)
  TreeSet       : 15.71 ms (1.71x slower)
  LinkedList    : 46.64 ms (5.06x slower)
```


## 📈 Typical Results

Results vary by hardware, but you can expect significant differences like:

- **StringBuilder vs String**: ~100x-1000x faster
- **HashSet vs ArrayList (searches)**: ~50x-100x faster
- **With Cache vs Without Cache**: ~1000x-10000x faster
- **Parallel vs Sequential (complex operations)**: ~2x-8x faster

## 🎓 Concepts Covered

- **Big O Notation** in practice
- **Memoization and Caching**
- **Parallelism and Concurrency**
- **Performance trade-offs**
- **Proper benchmarking**
- **Data structure selection**

## 🔧 Customizations

You can easily modify the benchmarks:

1. Change size constants:
```java
private static final int OPERATIONS = 100_000; // Increase or decrease
```

2. Add new benchmarks by creating classes in `benchmarks/`

3. Modify colors in `ConsoleColors.java`

## 📝 License

This project is free for educational use and demonstrations.

## 👤 Author

Michelle Henriques

## 🤝 Contributions

Suggestions and improvements are welcome! Feel free to:
- Add new benchmarks
- Improve visualizations
- Optimize existing code
- Fix bugs

## 📚 Additional Resources

- [Java Performance Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/performance/)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Java Streams API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html)

---

⭐ If this project was useful, consider giving it a star on GitHub!

