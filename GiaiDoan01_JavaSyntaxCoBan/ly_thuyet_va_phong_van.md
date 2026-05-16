# Giai Đoạn 1: Java Syntax Cơ Bản

## 📚 Lý Thuyết

### 1. Cấu trúc chương trình Java

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

**Giải thích từng phần:**
| Từ khóa | Ý nghĩa |
|---------|---------|
| `public` | Access modifier - cho phép truy cập từ mọi nơi |
| `class` | Khai báo một lớp |
| `Main` | Tên class (phải trùng tên file .java) |
| `static` | Method thuộc về class, không cần tạo object để gọi |
| `void` | Method không trả về giá trị |
| `main` | Tên method - điểm bắt đầu chạy chương trình |
| `String[] args` | Mảng tham số dòng lệnh |
| `System.out.println` | In ra console và xuống dòng |

### 2. Biến và Kiểu dữ liệu

#### Kiểu nguyên thủy (Primitive Types)
| Kiểu | Kích thước | Phạm vi | Ví dụ |
|------|-----------|---------|-------|
| `byte` | 1 byte | -128 → 127 | `byte b = 100;` |
| `short` | 2 bytes | -32,768 → 32,767 | `short s = 1000;` |
| `int` | 4 bytes | -2^31 → 2^31-1 | `int age = 22;` |
| `long` | 8 bytes | -2^63 → 2^63-1 | `long id = 123456789L;` |
| `float` | 4 bytes | ~6-7 chữ số thập phân | `float f = 3.14f;` |
| `double` | 8 bytes | ~15 chữ số thập phân | `double d = 3.14159;` |
| `char` | 2 bytes | 1 ký tự Unicode | `char grade = 'A';` |
| `boolean` | 1 bit | true/false | `boolean isActive = true;` |

#### Kiểu tham chiếu (Reference Types)
```java
String name = "Bao";       // Chuỗi ký tự
Integer number = 10;       // Wrapper class của int
int[] numbers = {1,2,3};   // Mảng
Student s = new Student(); // Object
```

#### Sự khác biệt Primitive vs Reference
| Primitive | Reference |
|-----------|-----------|
| Lưu **giá trị trực tiếp** trên Stack | Lưu **địa chỉ** (reference) trên Stack, object trên Heap |
| Không thể null | Có thể null |
| `int`, `double`, `boolean`... | `String`, `Object`, `Array`... |
| So sánh bằng `==` | So sánh bằng `.equals()` |

### 3. Ép kiểu dữ liệu (Type Casting)

#### Widening (tự động - nhỏ → lớn)
```java
int a = 10;
double b = a;  // 10 → 10.0 (tự động, không mất dữ liệu)
// byte → short → int → long → float → double
```

#### Narrowing (thủ công - lớn → nhỏ)
```java
double x = 10.99;
int y = (int) x;  // 10 (mất phần thập phân!)
```

#### Parse String sang số
```java
int age = Integer.parseInt("20");
double price = Double.parseDouble("99.5");
String s = String.valueOf(123); // số → String
```

### 4. Toán tử (Operators)

| Loại | Toán tử | Ví dụ |
|------|---------|-------|
| Số học | `+ - * / %` | `10 % 3 = 1` |
| Tăng/Giảm | `++ --` | `x++`, `++x` |
| So sánh | `== != > < >= <=` | `a > b` |
| Logic | `&& \|\| !` | `a && b` |
| Gán | `= += -= *= /=` | `x += 5` → `x = x + 5` |

**⚠️ Lưu ý quan trọng: `x++` vs `++x`**
```java
int x = 10;
System.out.println(x++); // In 10, SAU ĐÓ x = 11 (post-increment)
System.out.println(++x); // x = 12 TRƯỚC, rồi in 12 (pre-increment)
```

### 5. Câu điều kiện

#### if - else if - else
```java
int score = 8;
if (score >= 8) {
    System.out.println("Giỏi");
} else if (score >= 5) {
    System.out.println("Trung bình");
} else {
    System.out.println("Yếu");
}
```

#### switch (truyền thống)
```java
int day = 1;
switch (day) {
    case 1: System.out.println("Monday"); break;
    case 2: System.out.println("Tuesday"); break;
    default: System.out.println("Other");
}
```

#### switch expression (Java 14+)
```java
String level = switch (score) {
    case 10, 9, 8 -> "Giỏi";
    case 7, 6, 5  -> "Trung bình";
    default        -> "Yếu";
};
```

### 6. Vòng lặp

#### for loop
```java
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}
```

#### while loop
```java
int i = 1;
while (i <= 10) {
    System.out.println(i);
    i++;
}
```

#### do-while loop
```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 10);
```

**Sự khác biệt:**
| for | while | do-while |
|-----|-------|----------|
| Biết trước số lần lặp | Không biết trước | Không biết trước |
| Kiểm tra điều kiện đầu | Kiểm tra điều kiện đầu | Kiểm tra điều kiện cuối |
| Chạy 0 → n lần | Chạy 0 → n lần | Chạy **ít nhất 1 lần** |

#### break và continue
```java
// break: thoát vòng lặp
for (int i = 0; i < 10; i++) {
    if (i == 5) break; // dừng khi i = 5
}

// continue: bỏ qua lần lặp hiện tại
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue; // bỏ qua số chẵn
    System.out.println(i); // chỉ in số lẻ
}
```

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: Sự khác nhau giữa `==` và `.equals()` trong Java?
**Trả lời:**
- `==` so sánh **địa chỉ bộ nhớ** (reference) với kiểu tham chiếu, so sánh **giá trị** với kiểu nguyên thủy
- `.equals()` so sánh **nội dung/giá trị** của object
```java
String a = new String("Hello");
String b = new String("Hello");
System.out.println(a == b);      // false (khác reference)
System.out.println(a.equals(b)); // true (cùng nội dung)
```

### Q2: Sự khác nhau giữa primitive type và reference type?
**Trả lời:**
- **Primitive:** Lưu giá trị trực tiếp trên stack, có 8 kiểu (byte, short, int, long, float, double, char, boolean), không thể null, chiếm bộ nhớ cố định.
- **Reference:** Lưu địa chỉ (con trỏ) tới object trên heap, có thể null, bao gồm String, Array, Object, và tất cả các class.

### Q3: Autoboxing và Unboxing là gì?
**Trả lời:**
- **Autoboxing:** Tự động chuyển primitive → Wrapper class. Ví dụ: `Integer x = 5;` (int → Integer)
- **Unboxing:** Tự động chuyển Wrapper class → primitive. Ví dụ: `int y = x;` (Integer → int)
- Java tự động thực hiện từ Java 5+

### Q4: Giải thích sự khác nhau giữa `x++` và `++x`?
**Trả lời:**
- `x++` (post-increment): Trả về giá trị hiện tại, SAU ĐÓ mới tăng x
- `++x` (pre-increment): Tăng x TRƯỚC, rồi mới trả về giá trị
```java
int x = 5;
int a = x++; // a = 5, x = 6
int b = ++x; // x = 7, b = 7
```

### Q5: Khi nào dùng `switch` thay vì `if-else`?
**Trả lời:**
- Dùng `switch` khi so sánh **một biến** với **nhiều giá trị cố định** (constants)
- Dùng `if-else` khi cần so sánh **phức tạp** (range, nhiều điều kiện kết hợp)
- `switch` thường dễ đọc hơn khi có nhiều case cụ thể
- Java 14+ hỗ trợ switch expression ngắn gọn hơn

### Q6: `break` và `continue` khác nhau thế nào?
**Trả lời:**
- `break`: **Thoát hoàn toàn** khỏi vòng lặp, không thực hiện các lần lặp còn lại
- `continue`: **Bỏ qua** lần lặp hiện tại, nhưng vẫn tiếp tục các lần lặp tiếp theo

### Q7: Java có hỗ trợ multiple inheritance không?
**Trả lời:**
- Java **không** hỗ trợ multiple inheritance cho class (chỉ extends 1 class)
- Java hỗ trợ multiple inheritance cho **interface** (có thể implements nhiều interface)
- Điều này tránh vấn đề "Diamond Problem"

### Q8: Tại sao kiểu `float` phải thêm `f` ở cuối?
**Trả lời:**
Mặc định Java coi các số thập phân là `double`. Nếu muốn gán cho biến `float`, phải thêm `f` hoặc `F` để compiler biết:
```java
float f = 3.14f;  // Đúng
float f = 3.14;   // Lỗi compile - mất dữ liệu từ double → float
```

### Q9: Ternary operator là gì?
**Trả lời:**
Là cách viết if-else ngắn gọn trên 1 dòng:
```java
// Cú pháp: điều_kiện ? giá_trị_true : giá_trị_false
String result = (age >= 18) ? "Adult" : "Minor";
```

### Q10: Sự khác biệt giữa `while` và `do-while`?
**Trả lời:**
- `while`: Kiểm tra điều kiện **trước** khi thực thi body → có thể **không** chạy lần nào
- `do-while`: Thực thi body **trước**, kiểm tra điều kiện **sau** → **luôn chạy ít nhất 1 lần**
