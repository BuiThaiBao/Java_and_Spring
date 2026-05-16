# Giai Đoạn 2: Array, String, Method

## 📚 Lý Thuyết

### 1. Mảng (Array)

#### Khai báo và khởi tạo
```java
// Cách 1: Khai báo rồi khởi tạo
int[] numbers = new int[5]; // mảng 5 phần tử, mặc định = 0

// Cách 2: Khởi tạo trực tiếp
int[] numbers = {1, 2, 3, 4, 5};

// Cách 3: Khai báo rồi gán
int[] numbers = new int[]{1, 2, 3, 4, 5};
```

#### Truy cập và duyệt mảng
```java
// Truy cập phần tử (index bắt đầu từ 0)
System.out.println(numbers[0]); // phần tử đầu tiên

// Duyệt bằng for
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// Duyệt bằng for-each
for (int number : numbers) {
    System.out.println(number);
}
```

#### Đặc điểm quan trọng của Array
| Đặc điểm | Mô tả |
|-----------|--------|
| Index từ 0 | Phần tử đầu tiên ở index 0 |
| Kích thước cố định | Sau khi tạo, không thể thay đổi kích thước |
| `length` là thuộc tính | Không phải method: `array.length` (không có `()`) |
| Lỗi phổ biến | `ArrayIndexOutOfBoundsException` khi truy cập index ngoài phạm vi |

#### Mảng 2 chiều
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

### 2. String

#### Các method quan trọng
| Method | Mô tả | Ví dụ |
|--------|--------|-------|
| `length()` | Độ dài chuỗi | `"Hello".length()` → 5 |
| `charAt(i)` | Ký tự tại vị trí i | `"Hello".charAt(0)` → 'H' |
| `substring(a,b)` | Chuỗi con từ a đến b-1 | `"Hello".substring(0,3)` → "Hel" |
| `contains(s)` | Kiểm tra chứa chuỗi con | `"Hello".contains("ell")` → true |
| `equals(s)` | So sánh nội dung | `"abc".equals("abc")` → true |
| `equalsIgnoreCase(s)` | So sánh không phân biệt hoa thường | `"ABC".equalsIgnoreCase("abc")` → true |
| `toLowerCase()` | Chuyển thường | `"HELLO".toLowerCase()` → "hello" |
| `toUpperCase()` | Chuyển hoa | `"hello".toUpperCase()` → "HELLO" |
| `trim()` | Xóa khoảng trắng đầu cuối | `" hi ".trim()` → "hi" |
| `replace(a,b)` | Thay thế ký tự/chuỗi | `"hello".replace('l','r')` → "herro" |
| `split(regex)` | Tách chuỗi thành mảng | `"a,b,c".split(",")` → ["a","b","c"] |
| `startsWith(s)` | Kiểm tra bắt đầu bằng | `"Hello".startsWith("He")` → true |
| `endsWith(s)` | Kiểm tra kết thúc bằng | `"test.java".endsWith(".java")` → true |
| `indexOf(s)` | Vị trí xuất hiện đầu tiên | `"Hello".indexOf("l")` → 2 |
| `isEmpty()` | Kiểm tra rỗng | `"".isEmpty()` → true |

#### ⚠️ Lưu ý cực kỳ quan trọng
```java
// SAI - So sánh reference
if (name == "Bao") { }

// ĐÚNG - So sánh nội dung
if (name.equals("Bao")) { }

// AN TOÀN HƠN - Tránh NullPointerException
if ("Bao".equals(name)) { }
```

### 3. StringBuilder

```java
// String là IMMUTABLE (bất biến) - mỗi lần nối tạo object mới
String s = "Hello";
s = s + " World"; // Tạo object mới, object cũ bỏ đi → tốn bộ nhớ

// StringBuilder là MUTABLE (thay đổi được) - nối chuỗi hiệu quả
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
String result = sb.toString(); // "Hello World"
```

| String | StringBuilder |
|--------|---------------|
| Immutable (bất biến) | Mutable (thay đổi được) |
| Thread-safe | Không thread-safe |
| Nối chuỗi tạo object mới | Nối chuỗi trên cùng object |
| Dùng khi ít thao tác nối | Dùng khi nối nhiều (vòng lặp) |

> **StringBuffer** giống StringBuilder nhưng **thread-safe** (chậm hơn). Thường dùng StringBuilder.

### 4. Method (Phương thức)

#### Cấu trúc method
```java
// [access modifier] [static] [return type] methodName([parameters]) {
//     // body
//     return value; // nếu không void
// }

public static int sum(int a, int b) {
    return a + b;
}
```

#### Các khái niệm
| Khái niệm | Ý nghĩa | Ví dụ |
|------------|---------|-------|
| **Parameter** | Biến trong khai báo method | `int a, int b` |
| **Argument** | Giá trị truyền khi gọi method | `sum(3, 5)` |
| **Return type** | Kiểu dữ liệu trả về | `int`, `String`, `void` |
| **void** | Không trả về giá trị | `public void printHello()` |
| **Scope** | Phạm vi biến | Biến local chỉ tồn tại trong method |

#### Method Overloading (Nạp chồng)
Cùng tên method nhưng **khác parameter** (số lượng, kiểu, thứ tự):
```java
public static int sum(int a, int b) {
    return a + b;
}

public static double sum(double a, double b) {
    return a + b;
}

public static int sum(int a, int b, int c) {
    return a + b + c;
}
```

#### Pass by Value trong Java
Java luôn **pass by value**:
- Primitive: copy **giá trị** → thay đổi trong method KHÔNG ảnh hưởng ngoài
- Reference: copy **địa chỉ** → thay đổi **nội dung** object ảnh hưởng, nhưng gán lại reference KHÔNG ảnh hưởng

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: String trong Java là mutable hay immutable? Tại sao?
**Trả lời:**
String là **immutable** (bất biến). Khi thay đổi giá trị String, Java tạo object mới chứ không thay đổi object cũ.

Lý do immutable:
1. **String Pool:** Java lưu String literals trong pool để tái sử dụng. Nếu mutable, thay đổi 1 sẽ ảnh hưởng tất cả.
2. **Thread-safe:** Immutable object tự động thread-safe, không cần synchronization.
3. **Security:** String dùng làm key trong HashMap, password, connection URL. Nếu mutable sẽ không an toàn.
4. **Caching hashCode:** Vì immutable nên hashCode chỉ tính 1 lần.

### Q2: String Pool là gì?
**Trả lời:**
String Pool (hay String Intern Pool) là vùng bộ nhớ đặc biệt trong Heap, nơi Java lưu trữ các String literal.
```java
String s1 = "Hello";  // Tạo trong pool
String s2 = "Hello";  // Tái sử dụng từ pool
String s3 = new String("Hello"); // Tạo object mới trên heap

System.out.println(s1 == s2);  // true (cùng reference trong pool)
System.out.println(s1 == s3);  // false (khác reference)
System.out.println(s1.equals(s3)); // true (cùng nội dung)
```

### Q3: Khi nào dùng StringBuilder thay vì String?
**Trả lời:**
Dùng StringBuilder khi cần **nối chuỗi nhiều lần**, đặc biệt trong vòng lặp:
```java
// CHẬM - mỗi lần += tạo object mới
String s = "";
for (int i = 0; i < 10000; i++) {
    s += i; // O(n²) vì tạo n object
}

// NHANH - thao tác trên cùng 1 object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i); // O(n)
}
```

### Q4: Method Overloading vs Method Overriding?
**Trả lời:**
| | Overloading | Overriding |
|-|-------------|------------|
| Ở đâu | Cùng class | Class con kế thừa class cha |
| Tên method | Giống nhau | Giống nhau |
| Parameters | **Khác nhau** | **Giống nhau** |
| Return type | Có thể khác | Giống hoặc covariant |
| Thời điểm quyết định | Compile time | Runtime |
| Từ khóa | Không cần | `@Override` |

### Q5: Java là pass by value hay pass by reference?
**Trả lời:**
Java **luôn pass by value**. Nhưng cần phân biệt:
- **Primitive:** Copy giá trị → thay đổi trong method KHÔNG ảnh hưởng biến gốc
- **Object:** Copy **reference** (địa chỉ) → có thể thay đổi **nội dung** object, nhưng gán reference mới KHÔNG ảnh hưởng biến gốc

```java
void changeValue(int x) { x = 10; }
// x ngoài KHÔNG đổi

void changeObject(Student s) { s.setName("New"); }
// Name thay đổi vì cùng trỏ đến 1 object

void reassign(Student s) { s = new Student(); }
// s ngoài KHÔNG đổi vì chỉ copy reference
```

### Q6: Array và ArrayList khác nhau thế nào?
**Trả lời:**
| Array | ArrayList |
|-------|-----------|
| Kích thước **cố định** | Kích thước **tự động tăng** |
| Chứa primitive và object | Chỉ chứa object (wrapper class) |
| `array.length` (property) | `list.size()` (method) |
| Truy cập: `array[i]` | Truy cập: `list.get(i)` |
| Hiệu suất cao hơn | Linh hoạt hơn |

### Q7: `length` vs `length()` vs `size()`?
**Trả lời:**
- `length` (không ngoặc): Dùng cho **Array** → `int[] arr; arr.length`
- `length()` (có ngoặc): Dùng cho **String** → `"Hello".length()`
- `size()`: Dùng cho **Collection** (List, Set, Map) → `list.size()`

### Q8: Varargs là gì?
**Trả lời:**
Varargs (Variable Arguments) cho phép method nhận số lượng argument không cố định:
```java
public static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}
sum(1, 2);        // OK
sum(1, 2, 3, 4);  // OK
```
- Viết bằng `type...` 
- Bên trong method nó là **array**
- Phải đặt **cuối cùng** trong parameter list
- Chỉ được 1 varargs trong 1 method
