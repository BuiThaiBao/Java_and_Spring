# Giai Đoạn 13: Multithreading và Concurrency

## 📚 Lý Thuyết

### 1. Thread cơ bản
```java
// Cách 1: Lambda + Thread
Thread thread = new Thread(() -> {
    System.out.println("Running in: " + Thread.currentThread().getName());
});
thread.start();

// Cách 2: Runnable
Runnable task = () -> System.out.println("Task running");
new Thread(task).start();
```

### 2. Thread Lifecycle
```
NEW → RUNNABLE → RUNNING → BLOCKED/WAITING → TERMINATED
       (start)              (sleep/wait)      (done)
```

### 3. ExecutorService
```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Task"));
executor.shutdown();
```

### 4. synchronized
```java
public synchronized void increase() { count++; }

// Hoặc synchronized block
synchronized (this) { count++; }
```

### 5. Các khái niệm quan trọng
| Khái niệm | Mô tả |
|------------|--------|
| **Race condition** | 2+ thread cùng truy cập shared data → kết quả không dự đoán |
| **Deadlock** | 2+ thread chờ nhau mãi → treo |
| **Thread pool** | Tái sử dụng thread thay vì tạo mới |
| **volatile** | Đảm bảo biến luôn đọc từ main memory |
| **Callable/Future** | Thread trả về kết quả |
| **CompletableFuture** | Async programming nâng cao |

### 6. Virtual Threads (Java 21+)
```java
Thread.startVirtualThread(() -> {
    System.out.println("Hello from virtual thread");
});
```
Virtual thread nhẹ hơn platform thread, phù hợp cho server-side concurrency.

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Thread vs Runnable?
**Trả lời:** Implement Runnable tốt hơn vì: Java không hỗ trợ đa kế thừa (extend Thread = mất slot), Runnable linh hoạt hơn (dùng với ExecutorService), tách biệt task và thread.

### Q2: `synchronized` hoạt động thế nào?
**Trả lời:** Chỉ cho phép 1 thread truy cập block/method tại 1 thời điểm. Thread khác phải chờ (blocking). Dùng monitor lock (intrinsic lock) của object.

### Q3: Deadlock là gì? Cách phòng tránh?
**Trả lời:** 2+ thread chờ lock mà thread khác đang giữ → tất cả bị treo vĩnh viễn. Phòng tránh: lock theo thứ tự cố định, dùng tryLock() với timeout, tránh nested locks.

### Q4: `volatile` vs `synchronized`?
**Trả lời:** `volatile`: đảm bảo visibility (đọc từ main memory), KHÔNG đảm bảo atomicity. `synchronized`: đảm bảo cả visibility VÀ atomicity. `volatile` dùng cho flag đơn giản, `synchronized` cho compound operations.

### Q5: Virtual Threads (Java 21) là gì?
**Trả lời:** Thread nhẹ, managed bởi JVM thay vì OS. 1 platform thread có thể chạy hàng triệu virtual threads. Phù hợp cho I/O-bound tasks (web server, DB queries). Không phù hợp cho CPU-bound.
