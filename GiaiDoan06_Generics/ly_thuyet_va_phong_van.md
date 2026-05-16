# Giai Đoạn 6: Generics

## 📚 Lý Thuyết

### 1. Generic là gì?
Generics cho phép viết code **an toàn kiểu dữ liệu** (type-safe) và **tái sử dụng** cho nhiều kiểu khác nhau.

```java
// KHÔNG có generics - không an toàn
List list = new ArrayList();
list.add("Hello");
list.add(123);               // Thêm bất kỳ kiểu nào
String s = (String) list.get(1); // ClassCastException lúc runtime!

// CÓ generics - an toàn
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123);            // COMPILE ERROR - phát hiện sớm!
String s = list.get(0);      // Không cần cast
```

### 2. Generic Class
```java
public class Box<T> {
    private T value;
    
    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}

// Sử dụng
Box<String> stringBox = new Box<>();
stringBox.setValue("Hello");

Box<Integer> intBox = new Box<>();
intBox.setValue(42);
```

### 3. Generic Method
```java
public static <T> void printArray(T[] array) {
    for (T item : array) {
        System.out.println(item);
    }
}

public static <T extends Comparable<T>> T findMax(T[] array) {
    T max = array[0];
    for (T item : array) {
        if (item.compareTo(max) > 0) max = item;
    }
    return max;
}
```

### 4. Bounded Type Parameters
```java
// Upper bound: T phải là Number hoặc subclass
public static <T extends Number> double sum(List<T> list) {
    double total = 0;
    for (T num : list) total += num.doubleValue();
    return total;
}
```

### 5. Wildcard (?)
| Wildcard | Ý nghĩa | Dùng khi |
|----------|---------|----------|
| `<?>` | Bất kỳ kiểu nào | Chỉ đọc, không quan tâm kiểu |
| `<? extends T>` | T hoặc subclass | **Đọc** dữ liệu (Producer) |
| `<? super T>` | T hoặc superclass | **Ghi** dữ liệu (Consumer) |

> **PECS:** Producer Extends, Consumer Super

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Generics là gì? Tại sao cần?
**Trả lời:** Generics cho phép viết code parameter hóa kiểu dữ liệu. Lợi ích: type-safety tại compile time, không cần cast, tái sử dụng code.

### Q2: Type Erasure là gì?
**Trả lời:** Tại runtime, JVM xóa thông tin generic (thay T bằng Object hoặc bound). Nên `List<String>` và `List<Integer>` cùng kiểu tại runtime. Không thể `new T()` hay `instanceof T`.

### Q3: `<? extends T>` vs `<? super T>`?
**Trả lời:** `extends` = đọc (Producer) — lấy dữ liệu kiểu T. `super` = ghi (Consumer) — thêm dữ liệu kiểu T. Nhớ: **PECS**.
