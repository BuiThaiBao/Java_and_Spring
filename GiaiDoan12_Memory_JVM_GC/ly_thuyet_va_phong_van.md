# Giai Đoạn 12: Memory, JVM, Garbage Collection

## 📚 Lý Thuyết

### 1. JVM Memory Structure
```
┌──────────────────────────────────────────┐
│                JVM Memory                │
├───────────────┬──────────────────────────┤
│  Stack Memory │     Heap Memory          │
│  (per thread) │     (shared)             │
│               │                          │
│  - Local vars │  - Objects               │
│  - Method     │  - Instance variables    │
│    frames     │  - Arrays                │
│  - References │  ┌─────────────────────┐ │
│               │  │   String Pool       │ │
│  LIFO order   │  └─────────────────────┘ │
│  Size: nhỏ    │  Size: lớn               │
├───────────────┴──────────────────────────┤
│  Method Area (Metaspace)                 │
│  - Class metadata, static vars, methods  │
└──────────────────────────────────────────┘
```

### 2. Stack vs Heap
| Stack | Heap |
|-------|------|
| Biến local, reference | Objects, instance vars |
| Mỗi thread 1 stack | Shared giữa các thread |
| LIFO, tự động giải phóng | GC quản lý |
| Nhanh | Chậm hơn |
| `StackOverflowError` khi đầy | `OutOfMemoryError` khi đầy |

### 3. Reference là gì?
```java
Student s1 = new Student("Bao");  // s1 là reference trên Stack
Student s2 = s1;                   // s2 cùng trỏ đến 1 object trên Heap
s2.setName("An");                  // s1.getName() cũng = "An"
```

### 4. Garbage Collection
GC tự động thu hồi bộ nhớ của object **không còn reference** nào trỏ tới.
- **Mark and Sweep:** Đánh dấu object reachable → xóa unreachable
- **Generational GC:** Young (Eden, Survivor) → Old → Metaspace
- Không thể bắt buộc GC chạy (`System.gc()` chỉ gợi ý)

### 5. String Pool
```java
String s1 = "Hello";     // Tạo trong Pool
String s2 = "Hello";     // Tái sử dụng từ Pool
String s3 = new String("Hello"); // Tạo object mới trên Heap
s1 == s2;    // true  (cùng reference)
s1 == s3;    // false (khác reference)
```

### 6. Các lỗi phổ biến
| Lỗi | Nguyên nhân |
|------|-------------|
| `NullPointerException` | Gọi method trên biến null |
| `StackOverflowError` | Đệ quy quá sâu / vô hạn |
| `OutOfMemoryError` | Tạo quá nhiều object, memory leak |
| Memory Leak | Object không dùng nhưng vẫn bị reference giữ |

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Stack và Heap khác nhau thế nào?
**Trả lời:** Stack lưu biến local và reference, mỗi thread riêng, LIFO, tự giải phóng. Heap lưu objects, shared, GC quản lý. Primitive trên Stack, Object trên Heap (reference trên Stack trỏ tới).

### Q2: Garbage Collection hoạt động thế nào?
**Trả lời:** GC tự động thu hồi bộ nhớ khi object không còn reachable. JVM dùng Generational GC: Young Gen (minor GC thường xuyên) → Old Gen (major/full GC). Không thể bắt buộc GC, `System.gc()` chỉ gợi ý.

### Q3: Memory leak trong Java là gì?
**Trả lời:** Object không còn dùng nhưng vẫn có reference → GC không thu hồi. Ví dụ: static collection chỉ add không remove, listener không unregister, connection không close.

### Q4: `==` vs `equals()` liên quan gì đến memory?
**Trả lời:** `==` so sánh **reference** (địa chỉ trên Stack), `equals()` so sánh **nội dung** (object trên Heap). Với String Pool: `"abc" == "abc"` → true (cùng reference trong pool).
