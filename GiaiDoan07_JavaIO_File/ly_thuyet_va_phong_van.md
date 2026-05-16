# Giai Đoạn 7: Java IO và File

## 📚 Lý Thuyết

### 1. Đọc/ghi file text (NIO.2 - Java 7+)
```java
// Ghi file
Files.writeString(Path.of("data.txt"), "Hello Java");

// Đọc toàn bộ file
String content = Files.readString(Path.of("data.txt"));

// Đọc nhiều dòng
List<String> lines = Files.readAllLines(Path.of("data.txt"));

// Stream file lớn (lazy loading)
try (Stream<String> lines = Files.lines(Path.of("data.txt"))) {
    lines.forEach(System.out::println);
}
```

### 2. Serialization
- **Serialization:** Object → byte stream (lưu trữ/truyền đi)
- **Deserialization:** byte stream → Object
- Class phải implement `Serializable`
- Field không muốn serialize: dùng `transient`

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Sự khác nhau giữa IO truyền thống và NIO?
**Trả lời:** IO truyền thống (java.io) là stream-based, blocking. NIO (java.nio) là buffer-based, non-blocking, có Channel và Selector. NIO.2 (Java 7) thêm Files, Path API dễ dùng hơn.

### Q2: BufferedReader vs FileReader?
**Trả lời:** FileReader đọc từng ký tự. BufferedReader wrap FileReader, đọc theo buffer (chunk) → **nhanh hơn nhiều**. Luôn dùng BufferedReader.

### Q3: Serializable là gì? transient dùng khi nào?
**Trả lời:** Serializable là marker interface cho phép object chuyển thành byte stream. `transient` đánh dấu field **không** được serialize (password, sensitive data).

### Q4: try-with-resources liên quan gì đến IO?
**Trả lời:** Các IO resource (Reader, Writer, Stream) implement `AutoCloseable`. Dùng try-with-resources đảm bảo resource **luôn được đóng**, tránh resource leak.
