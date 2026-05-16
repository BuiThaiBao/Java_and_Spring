# Giai Đoạn 3: OOP - Lập Trình Hướng Đối Tượng

## 📚 Lý Thuyết

> **Đây là phần QUAN TRỌNG NHẤT của Java Core.** Hiểu OOP là nền tảng để học Spring Boot, Design Pattern, Clean Architecture.

### 1. Class và Object

**Class** = bản thiết kế (blueprint), **Object** = đối tượng cụ thể tạo từ class.

```java
// Class - bản thiết kế
public class Student {
    String name;
    int age;
}

// Object - đối tượng cụ thể
Student student = new Student();
student.name = "Bao";
student.age = 22;
```

### 2. Constructor (Hàm khởi tạo)

```java
public class Student {
    String name;
    int age;

    // Default constructor (no-args)
    public Student() {
        this.name = "Unknown";
        this.age = 0;
    }

    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Sử dụng
Student s1 = new Student();           // Gọi default constructor
Student s2 = new Student("Bao", 22);  // Gọi parameterized constructor
```

**Lưu ý quan trọng:**
- `this` đại diện cho object hiện tại
- Nếu không viết constructor, Java tự tạo **default constructor** (no-args)
- Nếu viết bất kỳ constructor nào, Java **KHÔNG** tạo default constructor nữa

### 3. Encapsulation (Đóng gói)

Dùng **private** field + **getter/setter** để bảo vệ dữ liệu.

```java
public class Student {
    private String name;  // private - không truy cập trực tiếp từ ngoài
    private int age;

    // Getter
    public String getName() { return name; }

    // Setter với validation
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age không hợp lệ");
        }
    }
}
```

**Tại sao không để field public?**
- Kiểm soát **validation** khi gán giá trị
- Có thể thay đổi logic bên trong mà không ảnh hưởng code bên ngoài
- Bảo mật dữ liệu
- Tuân theo nguyên tắc **information hiding**

### 4. Inheritance (Kế thừa)

```java
// Class cha
public class Animal {
    protected String name;
    
    public void eat() {
        System.out.println(name + " is eating...");
    }
}

// Class con - kế thừa từ Animal
public class Dog extends Animal {
    public void bark() {
        System.out.println(name + " is barking...");
    }
    
    @Override  // Ghi đè method của class cha
    public void eat() {
        System.out.println(name + " is eating dog food...");
    }
}
```

**Các khái niệm:**
| Khái niệm | Ý nghĩa |
|------------|---------|
| `extends` | Class con kế thừa class cha |
| `super` | Tham chiếu đến class cha |
| `@Override` | Đánh dấu method ghi đè |
| IS-A | Dog IS-A Animal (Dog là một Animal) |

**`super` dùng khi nào?**
```java
public class Dog extends Animal {
    public Dog(String name) {
        super(name); // Gọi constructor của class cha
    }
    
    @Override
    public void eat() {
        super.eat(); // Gọi method eat() của class cha
        System.out.println("Then eating bone...");
    }
}
```

### 5. Polymorphism (Đa hình)

Một biến kiểu cha có thể tham chiếu tới object con khác nhau.

```java
// Biến kiểu cha, object kiểu con
Animal animal = new Dog();
animal.eat(); // Gọi eat() của Dog (runtime binding)

// Ví dụ thực tế trong dự án
Payment payment = new MomoPayment();
payment.pay(); // → "Pay by Momo"

payment = new BankPayment();
payment.pay(); // → "Pay by Bank Transfer"
```

**Hai loại polymorphism:**
| Loại | Thời điểm | Cơ chế |
|------|-----------|--------|
| Compile-time (static) | Lúc compile | Method Overloading |
| Runtime (dynamic) | Lúc chạy | Method Overriding |

### 6. Abstraction (Trừu tượng)

#### Abstract Class
```java
public abstract class Shape {
    protected String color;
    
    // Abstract method - KHÔNG có body, class con BẮT BUỘC implement
    public abstract double area();
    
    // Concrete method - CÓ body, class con có thể dùng hoặc override
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

public class Circle extends Shape {
    private double radius;
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
```

#### Interface
```java
public interface Payment {
    void pay();                // abstract method (mặc định)
    
    default void log() {       // default method (Java 8+)
        System.out.println("Payment logged");
    }
    
    static void validate() {   // static method (Java 8+)
        System.out.println("Validating...");
    }
}

public class MomoPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Pay by Momo");
    }
}
```

#### So sánh Abstract Class vs Interface
| Tiêu chí | Abstract Class | Interface |
|----------|---------------|-----------|
| Từ khóa | `extends` | `implements` |
| Constructor | Có | Không |
| Field | Có (bất kỳ modifier) | Chỉ `public static final` |
| Method | abstract + concrete | abstract + default + static |
| Kế thừa | Chỉ 1 class | Nhiều interface |
| Khi nào dùng | Các class con có chung logic | Định nghĩa khả năng/hành vi |

### 7. Access Modifier

| Modifier | Trong class | Cùng package | Class con | Bên ngoài |
|----------|:-----------:|:------------:|:---------:|:---------:|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `default` (không viết gì) | ✅ | ✅ | ❌* | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

*Class con ở khác package không truy cập được default.

### 8. static và final

#### static - thuộc về class
```java
public class MathUtil {
    public static int count = 0; // biến static - dùng chung cho tất cả object
    
    public static int sum(int a, int b) { // method static
        return a + b;
    }
}

// Gọi qua class, KHÔNG cần tạo object
MathUtil.sum(1, 2);
```

#### final - không thay đổi
```java
final int MAX_SIZE = 100;      // final biến: không đổi giá trị
// MAX_SIZE = 200; → COMPILE ERROR

// final method: không override được
public final void doSomething() { }

// final class: không kế thừa được (ví dụ: String, Integer)
public final class MyClass { }
```

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: 4 tính chất của OOP là gì? Giải thích từng tính chất.
**Trả lời:**
1. **Encapsulation (Đóng gói):** Ẩn chi tiết bên trong, chỉ expose interface cần thiết. Dùng private field + getter/setter. Bảo vệ dữ liệu khỏi truy cập/sửa đổi trái phép.
2. **Inheritance (Kế thừa):** Class con kế thừa thuộc tính và phương thức của class cha. Tái sử dụng code, tạo hierarchy. Dùng `extends` hoặc `implements`.
3. **Polymorphism (Đa hình):** Một hành vi có nhiều hình thức. Compile-time (overloading) và Runtime (overriding). Biến kiểu cha có thể chứa object con.
4. **Abstraction (Trừu tượng):** Ẩn chi tiết phức tạp, chỉ hiển thị chức năng cần thiết. Dùng abstract class hoặc interface. Ví dụ: người dùng chỉ cần biết `payment.pay()`, không cần biết logic bên trong.

### Q2: Abstract class và Interface khác nhau thế nào? Khi nào dùng cái nào?
**Trả lời:**
- **Abstract class:** Dùng khi các class con **có chung bản chất** (IS-A) và chia sẻ code chung. Ví dụ: `Animal` → `Dog`, `Cat` cùng có `eat()`.
- **Interface:** Dùng khi muốn định nghĩa **khả năng/hành vi** mà nhiều class không liên quan có thể thực hiện. Ví dụ: `Serializable`, `Comparable`, `Payment`.
- **Từ Java 8:** Interface có thể có default method, ranh giới bớt rõ ràng hơn. Quy tắc: ưu tiên interface, chỉ dùng abstract class khi cần constructor hoặc state.

### Q3: Overloading vs Overriding?
**Trả lời:**
| | Overloading | Overriding |
|-|-------------|------------|
| Nơi xảy ra | Cùng class | Class con → class cha |
| Tên method | Giống | Giống |
| Parameters | **Khác** | **Giống** |
| Return type | Có thể khác | Giống hoặc covariant |
| Access modifier | Bất kỳ | Không được **hẹp hơn** cha |
| Binding | Compile-time | Runtime |

### Q4: `this` và `super` khác nhau thế nào?
**Trả lời:**
- `this`: Tham chiếu đến **object hiện tại** → dùng để phân biệt field và parameter cùng tên, gọi constructor khác trong cùng class
- `super`: Tham chiếu đến **class cha** → dùng để gọi constructor cha, gọi method cha khi đã override

### Q5: Java có hỗ trợ đa kế thừa (multiple inheritance) không?
**Trả lời:**
- **Class:** KHÔNG hỗ trợ đa kế thừa (chỉ extends 1 class) → tránh Diamond Problem
- **Interface:** CÓ hỗ trợ (implements nhiều interface)
- Nếu 2 interface có cùng default method, class implement phải **override** method đó

### Q6: Tại sao nên dùng composition thay vì inheritance?
**Trả lời:**
- **Inheritance** tạo coupling chặt giữa cha-con, khó thay đổi
- **Composition** (HAS-A) linh hoạt hơn: thay đổi behavior bằng cách thay đổi component
- Nguyên tắc: "Favor composition over inheritance" (GoF Design Patterns)
```java
// Inheritance: Engine IS-A Vehicle → sai về logic
// Composition: Vehicle HAS-A Engine → đúng
class Car {
    private Engine engine; // composition
}
```

### Q7: Constructor có được kế thừa không?
**Trả lời:**
- Constructor **KHÔNG** được kế thừa
- Class con phải tự định nghĩa constructor
- Constructor class con **phải gọi** constructor class cha (tường minh hoặc ngầm định)
- `super()` phải là **dòng đầu tiên** trong constructor class con

### Q8: Có thể tạo object từ abstract class hoặc interface không?
**Trả lời:**
- **KHÔNG** thể tạo object trực tiếp từ abstract class hoặc interface
- Phải tạo class con **implement/extend** rồi mới tạo object
- Ngoại lệ: Anonymous class
```java
Payment p = new Payment() {
    @Override
    public void pay() { System.out.println("Anonymous payment"); }
};
```

### Q9: `static` method có thể bị override không?
**Trả lời:**
- **KHÔNG.** Static method thuộc về **class**, không thuộc về object
- Static method có thể bị **ẩn** (method hiding) trong class con, nhưng không phải override
- Vì vậy, static method không có polymorphism

### Q10: SOLID principles là gì? (Bonus - quan trọng cho interview)
**Trả lời:**
1. **S - Single Responsibility:** Mỗi class chỉ có 1 lý do để thay đổi
2. **O - Open/Closed:** Open for extension, closed for modification
3. **L - Liskov Substitution:** Object con có thể thay thế object cha
4. **I - Interface Segregation:** Interface nhỏ, cụ thể tốt hơn interface lớn
5. **D - Dependency Inversion:** Phụ thuộc vào abstraction, không phụ thuộc vào implementation
