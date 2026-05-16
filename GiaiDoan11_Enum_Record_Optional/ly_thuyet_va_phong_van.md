# Giai Đoạn 11: Enum, Record, Optional

## 📚 Lý Thuyết

### 1. Enum
Enum đại diện cho một tập hợp hằng số cố định.

```java
public enum OrderStatus {
    PENDING, PAID, SHIPPED, DELIVERED, CANCELLED;
}

// Enum nâng cao - có field và method
public enum PackageType {
    BASIC("Cơ bản", 100000),
    PREMIUM("Cao cấp", 500000),
    VIP("VIP", 1000000);

    private final String label;
    private final double price;

    PackageType(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() { return label; }
    public double getPrice() { return price; }
}
```

**Dùng nhiều trong backend:** `UserRole`, `OrderStatus`, `PaymentStatus`, `ListingStatus`.

### 2. Record (Java 14+)
Record tự động tạo constructor, getter, equals, hashCode, toString.

```java
public record StudentDto(String name, int age, double score) {}

// Tương đương class đầy đủ với:
// - Constructor
// - Getter: name(), age(), score()
// - equals(), hashCode(), toString()
```

### 3. Optional
Dùng để tránh `NullPointerException`. Chứa giá trị hoặc rỗng.

```java
Optional<Student> opt = findById(1);
opt.ifPresent(s -> System.out.println(s.getName()));
String name = opt.map(Student::getName).orElse("Unknown");
Student s = opt.orElseThrow(() -> new UserNotFoundException("Not found"));
```

**⚠️ Không lạm dụng:** Không dùng Optional làm field, parameter, hay collection element.

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Enum vs static final constants?
**Trả lời:** Enum type-safe (compiler kiểm tra), có thể có method/field, dùng trong switch, iterable. Constants chỉ là giá trị số/chuỗi, không type-safe.

### Q2: Record là gì? Khi nào dùng?
**Trả lời:** Record là immutable data class ngắn gọn. Dùng cho DTO, value objects, response/request objects. Không dùng khi cần mutable state hoặc kế thừa.

### Q3: Optional dùng khi nào? Không dùng khi nào?
**Trả lời:** Dùng: return type của method có thể trả null. Không dùng: field, parameter, collection (dùng empty collection), primitive (dùng OptionalInt...).
