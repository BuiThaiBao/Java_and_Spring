# Giai Đoạn 8: Lambda và Functional Programming

## 📚 Lý Thuyết

### 1. Lambda Expression
Lambda giúp viết anonymous function ngắn gọn. Cú pháp: `(parameters) -> expression`

```java
// Trước Java 8 - Anonymous class
students.sort(new Comparator<Student>() {
    @Override
    public int compare(Student a, Student b) {
        return a.getScore() - b.getScore();
    }
});

// Sau Java 8 - Lambda
students.sort((a, b) -> a.getScore() - b.getScore());
```

### 2. Functional Interface
Interface chỉ có **1 abstract method**. Đánh dấu bằng `@FunctionalInterface`.

**Built-in Functional Interfaces:**
| Interface | Method | Input → Output | Ví dụ |
|-----------|--------|----------------|-------|
| `Predicate<T>` | `test(T)` | T → boolean | Lọc, kiểm tra |
| `Function<T,R>` | `apply(T)` | T → R | Chuyển đổi |
| `Consumer<T>` | `accept(T)` | T → void | In, lưu |
| `Supplier<T>` | `get()` | () → T | Tạo object |
| `BiFunction<T,U,R>` | `apply(T,U)` | (T,U) → R | 2 input |
| `Comparator<T>` | `compare(T,T)` | (T,T) → int | So sánh |

### 3. Method Reference
```java
names.forEach(System.out::println);        // Instance method
students.sort(Comparator.comparing(Student::getName)); // Getter
```

| Loại | Cú pháp | Lambda tương đương |
|------|---------|-------------------|
| Static method | `Math::abs` | `x -> Math.abs(x)` |
| Instance method | `System.out::println` | `x -> System.out.println(x)` |
| Constructor | `Student::new` | `() -> new Student()` |

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Lambda expression là gì?
**Trả lời:** Lambda là cách viết ngắn gọn cho anonymous function, dùng để implement functional interface. Cú pháp: `(params) -> body`. Giúp code ngắn hơn, đặc biệt với Collection API.

### Q2: Functional Interface là gì? Cho ví dụ.
**Trả lời:** Interface chỉ có 1 abstract method. Ví dụ: `Runnable`, `Comparator`, `Predicate`, `Function`. Đánh dấu bằng `@FunctionalInterface`. Lambda chỉ dùng được với functional interface.

### Q3: Predicate, Function, Consumer, Supplier khác nhau thế nào?
**Trả lời:** Predicate: kiểm tra (T→boolean). Function: chuyển đổi (T→R). Consumer: xử lý không trả về (T→void). Supplier: tạo/cung cấp (→T).

### Q4: Method reference vs Lambda?
**Trả lời:** Method reference (`::`) là shorthand cho lambda khi chỉ gọi 1 method. `x -> System.out.println(x)` = `System.out::println`. Dùng khi lambda chỉ delegate cho 1 method có sẵn.
