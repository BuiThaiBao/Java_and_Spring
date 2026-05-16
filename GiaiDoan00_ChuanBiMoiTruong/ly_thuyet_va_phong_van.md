# Giai Đoạn 0: Chuẩn Bị Môi Trường

## 📚 Lý Thuyết

### 1. Cần cài đặt
- **JDK 21 hoặc JDK 25** (LTS - Long Term Support)
- **IntelliJ IDEA Community** (IDE phổ biến nhất cho Java)
- **Git** (quản lý version code)
- **Maven** (công cụ build, quản lý dependency - học trước Gradle)
- **MySQL hoặc PostgreSQL** (database cho JDBC sau này)

### 2. JDK, JRE, JVM là gì?

```
┌─────────────────────────────────────────┐
│                  JDK                    │
│  (Java Development Kit)                 │
│  ┌───────────────────────────────────┐  │
│  │              JRE                  │  │
│  │  (Java Runtime Environment)       │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │           JVM               │  │  │
│  │  │  (Java Virtual Machine)     │  │  │
│  │  │  - Chạy bytecode .class     │  │  │
│  │  │  - Platform independent     │  │  │
│  │  └─────────────────────────────┘  │  │
│  │  + Thư viện chuẩn (rt.jar)       │  │
│  └───────────────────────────────────┘  │
│  + javac (compiler)                     │
│  + jdb (debugger)                       │
│  + jar (đóng gói)                       │
└─────────────────────────────────────────┘
```

| Thành phần | Vai trò | Bao gồm |
|------------|---------|----------|
| **JDK** | Bộ công cụ phát triển Java đầy đủ | JRE + compiler (javac) + debugger + tools |
| **JRE** | Môi trường chạy chương trình Java | JVM + thư viện chuẩn |
| **JVM** | Máy ảo chạy bytecode Java | Class Loader + Bytecode Verifier + Execution Engine |

### 3. Quá trình biên dịch và chạy Java

```
  File .java  ──→  javac  ──→  File .class  ──→  JVM  ──→  Chạy chương trình
  (source code)   (compile)   (bytecode)        (run)     (kết quả)
```

**Ví dụ cụ thể:**
```bash
# Bước 1: Viết file Main.java
# Bước 2: Compile
javac Main.java     # → Tạo ra Main.class (bytecode)
# Bước 3: Chạy
java Main           # JVM đọc Main.class và chạy
```

### 4. Tại sao Java "Write Once, Run Anywhere"?
- Java không compile thành mã máy trực tiếp (như C/C++)
- Java compile thành **bytecode** (.class) - một ngôn ngữ trung gian
- **JVM** trên mỗi hệ điều hành sẽ đọc bytecode và chuyển thành mã máy phù hợp
- Vì vậy cùng một file .class có thể chạy trên Windows, macOS, Linux

### 5. Bytecode là gì?
- Là tập lệnh trung gian, không phải mã máy, không phải mã nguồn
- Được lưu trong file `.class`
- Chỉ JVM mới hiểu và thực thi được
- Có thể xem bytecode bằng lệnh: `javap -c Main.class`

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: JDK, JRE, JVM khác nhau như thế nào?
**Trả lời:**
- **JVM (Java Virtual Machine):** Là máy ảo Java, chịu trách nhiệm chạy bytecode (.class). JVM là nền tảng giúp Java đạt được tính "Write Once, Run Anywhere".
- **JRE (Java Runtime Environment):** Bao gồm JVM + các thư viện chuẩn. JRE đủ để **chạy** chương trình Java, nhưng không thể **phát triển** (compile).
- **JDK (Java Development Kit):** Bao gồm JRE + các công cụ phát triển như javac (compiler), jdb (debugger), javadoc. JDK cần thiết để **viết và compile** chương trình Java.

### Q2: Java compile thành gì? Có chạy trực tiếp file .java được không?
**Trả lời:**
Không, Java không chạy trực tiếp file `.java`. Quy trình:
1. `javac` compile file `.java` thành bytecode `.class`
2. JVM đọc file `.class` và chạy
- Bytecode là ngôn ngữ trung gian, không phải mã máy cụ thể cho bất kỳ OS nào.

### Q3: Tại sao Java được gọi là ngôn ngữ "platform independent"?
**Trả lời:**
Java compile thành bytecode chứ không phải mã máy trực tiếp. Bytecode chạy trên JVM, và JVM có phiên bản cho từng hệ điều hành. Vì vậy cùng một bytecode có thể chạy trên Windows, macOS, Linux mà không cần compile lại. Đây là nguyên lý "Write Once, Run Anywhere".

### Q4: Java là ngôn ngữ biên dịch (compiled) hay thông dịch (interpreted)?
**Trả lời:**
Java là **cả hai**:
- **Compiled:** javac compile source code thành bytecode
- **Interpreted:** JVM thông dịch bytecode thành mã máy khi chạy
- Ngoài ra, JVM còn có **JIT (Just-In-Time) Compiler** giúp tối ưu bằng cách compile bytecode thành mã máy native cho những đoạn code được gọi nhiều lần (hot spots).

### Q5: JIT Compiler là gì?
**Trả lời:**
JIT (Just-In-Time) Compiler là một phần của JVM, hoạt động tại runtime. Khi phát hiện đoạn code (method) được gọi nhiều lần, JIT sẽ compile bytecode thành native machine code để chạy nhanh hơn. Điều này giúp Java đạt hiệu năng gần bằng C/C++ trong nhiều trường hợp.

### Q6: Sự khác nhau giữa Maven và Gradle?
**Trả lời:**
| Tiêu chí | Maven | Gradle |
|----------|-------|--------|
| File cấu hình | `pom.xml` (XML) | `build.gradle` (Groovy/Kotlin) |
| Tốc độ build | Chậm hơn | Nhanh hơn (incremental build) |
| Độ phổ biến | Rất phổ biến, nhiều project dùng | Đang tăng, Android dùng mặc định |
| Học dễ hơn | Dễ hơn cho người mới | Phức tạp hơn |

→ Nên học Maven trước vì dễ hiểu hơn và nhiều project enterprise dùng.

### Q7: Java 21 có gì đặc biệt so với các phiên bản trước?
**Trả lời:**
Java 21 là bản LTS (Long Term Support), có nhiều tính năng quan trọng:
- **Virtual Threads** (JEP 444) - thread nhẹ cho server-side
- **Pattern Matching for switch** - switch expression mạnh hơn
- **Record Patterns** - destructuring cho record
- **Sequenced Collections** - interface mới cho collection có thứ tự
- **String Templates** (preview) - nội suy chuỗi dễ hơn
