# 💼 Practical Use Cases - Quick Guide

## 🎯 When to Use Each Data Structure

### ArrayList
✅ **Use when:**
- You need fast access by index
- You do many reads
- You add elements at the end
- You know the approximate size beforehand

❌ **Avoid when:**
- You need to insert in the middle frequently
- You do many value searches
- You remove elements from the beginning/middle constantly

**Real Example:**
```java
// Product list in a shopping cart
List<Product> cart = new ArrayList<>();
cart.add(product);           // Fast O(1)
Product p = cart.get(0);     // Fast O(1)
```

### LinkedList
✅ **Use when:**
- You insert/remove from beginning/middle frequently
- You implement queue or stack
- You don't need random access

❌ **Avoid when:**
- You access elements by index frequently
- You do many searches
- You're concerned about memory usage (pointer overhead)

**Real Example:**
```java
// Task processing queue
Queue<Task> taskQueue = new LinkedList<>();
taskQueue.offer(task);      // Fast O(1)
Task next = taskQueue.poll(); // Fast O(1)
```

### HashSet
✅ **Use when:**
- You need to guarantee uniqueness
- You do many existence checks
- You don't care about order
- Search is critical

❌ **Avoid when:**
- You need to maintain insertion order
- You need sorting
- You need access by index

**Real Example:**
```java
// Unique online user IDs
Set<String> onlineUsers = new HashSet<>();
onlineUsers.add(userId);              // O(1)
boolean isOnline = onlineUsers.contains(userId); // O(1) - Fast!
```

### TreeSet
✅ **Use when:**
- You need sorted data
- You do range searches (greater/less than)
- You want unique set AND sorted

❌ **Avoid when:**
- You don't need sorting
- Insertion performance is critical
- Elements are not comparable

**Real Example:**
```java
// Player ranking (sorted by score)
TreeSet<Player> ranking = new TreeSet<>(Comparator.comparing(Player::getScore));
ranking.add(player);  // O(log n) - maintains sorted
Player top = ranking.first(); // Best player
```

### HashMap
✅ **Use when:**
- You need to associate key-value
- Key lookup is frequent
- You don't care about order

❌ **Avoid when:**
- You need sorting by key
- Keys can be null and that's a problem
- You need synchronization (use ConcurrentHashMap)

**Real Example:**
```java
// User cache by ID
Map<Long, User> userCache = new HashMap<>();
userCache.put(id, user);       // O(1)
User user = userCache.get(id); // O(1) - Very fast!
```

## 📝 String Operations

### String Concatenation (+=)
❌ **NEVER use in loops!**
```java
// ❌ TERRIBLE - O(n²)
String html = "";
for (String line : lines) {
    html += line;  // Creates new object each time!
}
```

### StringBuilder
✅ **Always use in loops**
```java
// ✅ EXCELLENT - O(n)
StringBuilder html = new StringBuilder();
for (String line : lines) {
    html.append(line);  // Reuses same buffer
}
```

**Use Cases:**
- HTML/XML generation
- SQL query building
- Concatenated logs
- Message formatting

### StringBuffer
✅ **Use only when you need thread-safety**
```java
// Thread-safe but slower
StringBuffer shared = new StringBuffer();
// Multiple threads can use safely
```

## 🔄 Streams vs Loops

### Traditional For Loop
✅ **Use when:**
- Performance is absolutely critical
- Code is simple
- You don't need complex operations

```java
// Simple and fast
for (Order order : orders) {
    total += order.getAmount();
}
```

### Stream API
✅ **Use when:**
- Code becomes more readable
- You need to chain operations
- You might parallelize in the future
- You work with complex data

```java
// Readable and expressive
double total = orders.stream()
    .filter(Order::isPaid)
    .map(Order::getAmount)
    .reduce(0.0, Double::sum);
```

### When NOT to use Streams
❌ Avoid in:
- Simple loops (unnecessary overhead)
- External state modifications
- Code that's already very slow

## ⚡ Parallel Processing

### Sequential Stream
✅ **Use when:**
- Operations are fast (< 1ms each)
- List is small (< 10,000 elements)
- Order matters
- Operations have side-effects

```java
// Simple operation - sequential is better
List<String> names = users.stream()
    .map(User::getName)
    .collect(Collectors.toList());
```

### Parallel Stream
✅ **Use when:**
- Operations are expensive (> 1ms each)
- List is large (> 100,000 elements)
- Operations are independent
- You have multiple cores available

```java
// Expensive operation - parallel helps
List<Image> processed = images.parallelStream()
    .map(img -> expensiveProcessing(img))
    .collect(Collectors.toList());
```

**Examples where Parallel shines:**
- Image processing
- Complex mathematical calculations
- Heavy data transformations
- Expensive validations

**Examples where Parallel is bad:**
- Database I/O (I/O bound, not CPU)
- Very fast operations (overhead > benefit)
- Small lists

## 💾 Caching Strategies

### When to Use Cache

✅ **Cache is essential for:**
```java
// 1. Repeated calculations
Map<Integer, Long> fibCache = new HashMap<>();

// 2. Frequent database queries
Map<Long, User> userCache = new HashMap<>();

// 3. Expensive operations
Map<String, ProcessedData> resultCache = new HashMap<>();

// 4. Rarely changed data
Map<String, Config> configCache = new HashMap<>();
```

### Cache Types

#### 1. Simple In-Memory
```java
private final Map<String, Object> cache = new HashMap<>();

public Object getData(String key) {
    if (!cache.containsKey(key)) {
        cache.put(key, expensiveOperation(key));
    }
    return cache.get(key);
}
```

#### 2. LRU Cache (Limited Size)
```java
private final Map<String, Object> cache = 
    new LinkedHashMap<>(100, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 100;
        }
    };
```

#### 3. Time-Based Cache
```java
class CacheEntry<T> {
    T value;
    long timestamp;
    long ttl; // time to live
    
    boolean isExpired() {
        return System.currentTimeMillis() - timestamp > ttl;
    }
}
```

#### 4. Guava Cache (Library)
```java
// Using Guava
Cache<String, User> cache = CacheBuilder.newBuilder()
    .maximumSize(1000)
    .expireAfterWrite(10, TimeUnit.MINUTES)
    .build();
```

## 🎯 Complete Real Scenarios

### 1. E-commerce: Shopping Cart
```java
public class ShoppingCart {
    // ArrayList - fast access, order matters
    private List<CartItem> items = new ArrayList<>();
    
    // HashMap - fast product lookup
    private Map<Long, CartItem> itemsByProduct = new HashMap<>();
    
    public void addItem(Product product, int quantity) {
        CartItem item = itemsByProduct.get(product.getId());
        if (item != null) {
            item.increaseQuantity(quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity);
            items.add(newItem);
            itemsByProduct.put(product.getId(), newItem);
        }
    }
    
    public BigDecimal getTotal() {
        // Stream - clean code
        return items.stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
```

### 2. Logging System
```java
public class LogAggregator {
    // StringBuilder - efficient concatenation
    private final StringBuilder buffer = new StringBuilder();
    
    // HashSet - unique error IDs
    private final Set<String> uniqueErrors = new HashSet<>();
    
    public void addLog(String message, String errorCode) {
        // Efficient concatenation
        buffer.append("[")
              .append(LocalDateTime.now())
              .append("] ")
              .append(message)
              .append("\n");
        
        // Track unique errors
        if (errorCode != null) {
            uniqueErrors.add(errorCode);
        }
    }
    
    public int getUniqueErrorCount() {
        return uniqueErrors.size(); // O(1)
    }
}
```

### 3. Image Processing
```java
public class ImageProcessor {
    // Processing cache
    private final Map<String, ProcessedImage> cache = new HashMap<>();
    
    public List<ProcessedImage> processImages(List<Image> images) {
        // Parallel stream - expensive operation
        return images.parallelStream()
            .map(this::processWithCache)
            .collect(Collectors.toList());
    }
    
    private ProcessedImage processWithCache(Image img) {
        String hash = img.getHash();
        
        // Check cache first
        if (cache.containsKey(hash)) {
            return cache.get(hash);
        }
        
        // Expensive processing
        ProcessedImage processed = expensiveProcessing(img);
        cache.put(hash, processed);
        
        return processed;
    }
}
```

### 4. Ranking System
```java
public class LeaderboardService {
    // TreeSet - maintains automatic sorting
    private final TreeSet<Score> topScores = new TreeSet<>(
        Comparator.comparing(Score::getPoints).reversed()
            .thenComparing(Score::getTimestamp)
    );
    
    // HashMap - fast user lookup
    private final Map<Long, Score> scoresByUser = new HashMap<>();
    
    public void submitScore(Long userId, int points) {
        Score oldScore = scoresByUser.get(userId);
        if (oldScore != null) {
            topScores.remove(oldScore);
        }
        
        Score newScore = new Score(userId, points, LocalDateTime.now());
        topScores.add(newScore);
        scoresByUser.put(userId, newScore);
        
        // Keep only top 100
        while (topScores.size() > 100) {
            Score lowest = topScores.last();
            topScores.remove(lowest);
            scoresByUser.remove(lowest.getUserId());
        }
    }
    
    public List<Score> getTop10() {
        return topScores.stream()
            .limit(10)
            .collect(Collectors.toList());
    }
}
```

## 📊 Quick Decision: Flowchart

```
Need to store data?
│
├─ Single element
│  └─ Use simple variable
│
├─ Key-value pair?
│  ├─ Need sorting? → TreeMap
│  ├─ Thread-safe? → ConcurrentHashMap
│  └─ Default → HashMap
│
└─ Collection of elements?
   │
   ├─ Need uniqueness?
   │  ├─ Need sorting? → TreeSet
   │  └─ No → HashSet
   │
   └─ Allow duplicates?
      ├─ Frequent index access? → ArrayList
      ├─ Insertions/removals at beginning? → LinkedList
      └─ Don't know? → ArrayList (default)

Need to concatenate strings?
│
├─ Once? → String + or String.format()
├─ Loop? → StringBuilder
└─ Thread-safe? → StringBuffer

Need to iterate?
│
├─ Simple operation? → For loop
├─ Complex operation?
│  ├─ Readability matters? → Stream
│  └─ Performance critical? → For loop
│
└─ Very expensive operation?
   ├─ Large list? → Parallel stream
   └─ Small list? → Sequential stream

Expensive/repeated operation?
│
└─ ALWAYS use cache!
```

## 🚀 Performance Checklist

Before deploying, verify:

- [ ] **No String += in loops**
- [ ] **HashMap for frequent lookups**
- [ ] **ArrayList as default collection**
- [ ] **HashSet for unique elements**
- [ ] **Cache for expensive operations**
- [ ] **Parallel stream only for heavy operations**
- [ ] **StringBuilder for concatenations**
- [ ] **Correct data structure for use case**
- [ ] **No unnecessary O(n²) operations**
- [ ] **Measured with profiler before optimizing**

---

💡 **Remember**: Premature optimization is the root of all evil. Measure first, optimize later!

