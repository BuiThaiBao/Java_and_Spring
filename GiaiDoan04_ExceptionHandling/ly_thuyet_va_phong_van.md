# Giai Đoạn 4: Exception Handling

## 📚 Lý Thuyết

### 1. Exception là gì?
Exception là sự kiện bất thường xảy ra trong quá trình chạy chương trình, làm gián đoạn luồng xử lý bình thường.

### 2. Phân cấp Exception trong Java
```
                    Throwable
                   /         \
               Error         Exception
              /                 \
   OutOfMemoryError    RuntimeException    IOException
   StackOverflowError   /        \         SQLException
                  NullPointer  ArrayIndex  FileNotFoundException
                  Arithmetic   ClassCast
                  IllegalArgument
```

| Loại | Mô tả | Bắt buộc xử lý? |
|------|--------|:---------------:|
| **Error** | Lỗi nghiêm trọng của JVM (OutOfMemory, StackOverflow) | Không |
| **Checked Exception** | Lỗi có thể dự đoán (IOException, SQLException) | **Có** |
| **Unchecked Exception** | RuntimeException (NullPointer, ArrayIndex) | Không |

### 3. try-catch-finally

```java
try {
    // Code có thể gây lỗi
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Xử lý lỗi cụ thể
    System.out.println("Lỗi: " + e.getMessage());
} catch (Exception e) {
    // Xử lý lỗi chung (catch con trước, cha sau)
    System.out.println("Lỗi khác: " + e.getMessage());
} finally {
    // LUÔN chạy dù có lỗi hay không
    System.out.println("Giải phóng tài nguyên");
}
```

### 4. throw và throws

```java
// throw - ném exception thủ công
public static void checkAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Phải >= 18 tuổi");
    }
}

// throws - khai báo method CÓ THỂ ném exception
public static void readFile(String path) throws IOException {
    // code đọc file... có thể ném IOException
}
```

| | throw | throws |
|-|-------|--------|
| Vị trí | Trong body method | Khai báo method |
| Mục đích | Ném exception cụ thể | Khai báo exception method có thể ném |
| Số lượng | 1 exception | Nhiều exception (dấu phẩy) |

### 5. Checked vs Unchecked Exception

| Checked | Unchecked |
|---------|-----------|
| Kế thừa `Exception` (trừ RuntimeException) | Kế thừa `RuntimeException` |
| **Bắt buộc** xử lý (try-catch hoặc throws) | Không bắt buộc |
| IOException, SQLException, FileNotFoundException | NullPointerException, ArrayIndexOutOfBoundsException |
| Lỗi do **ngoại cảnh** (file, network, DB) | Lỗi do **lập trình sai** (null, sai index) |

### 6. Custom Exception

```java
// Unchecked custom exception
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

// Checked custom exception
public class InsufficientBalanceException extends Exception {
    private double balance;
    private double amount;

    public InsufficientBalanceException(double balance, double amount) {
        super("Số dư " + balance + " không đủ để rút " + amount);
        this.balance = balance;
        this.amount = amount;
    }
}
```

### 7. try-with-resources (Java 7+)

```java
// Tự động đóng resource khi kết thúc
try (FileReader reader = new FileReader("data.txt");
     BufferedReader br = new BufferedReader(reader)) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// reader và br tự động đóng - KHÔNG cần finally
```

### 8. Best Practices

1. **Bắt exception cụ thể** trước, chung sau
2. **Không bắt Exception/Throwable** chung chung nếu không cần
3. **Không dùng exception để điều khiển luồng** (flow control)
4. **Log exception** thay vì nuốt (swallow) - đừng catch rỗng
5. **Dùng custom exception** cho business logic
6. **Dùng try-with-resources** cho AutoCloseable resources
7. **Đừng throw Exception chung** - throw loại cụ thể

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: Checked vs Unchecked Exception? Cho ví dụ.
**Trả lời:**
- **Checked:** Compiler bắt buộc xử lý. Thường là lỗi ngoại cảnh không kiểm soát được: `IOException`, `SQLException`, `FileNotFoundException`. Phải dùng try-catch hoặc throws.
- **Unchecked:** Không bắt buộc xử lý. Thường là lỗi logic lập trình: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`. Kế thừa RuntimeException.

### Q2: `finally` block có luôn chạy không?
**Trả lời:**
- **Gần như luôn chạy**, kể cả khi có exception hay return trong try/catch
- **Ngoại lệ:** Không chạy khi gọi `System.exit()` hoặc JVM crash
- Nếu cả try và finally đều có return → **return trong finally sẽ thắng**

### Q3: `throw` và `throws` khác nhau thế nào?
**Trả lời:**
- `throw`: Dùng trong **body** method để **ném** một exception cụ thể: `throw new IOException("Error")`
- `throws`: Dùng trong **khai báo** method để báo rằng method **có thể** ném exception: `void read() throws IOException`

### Q4: Khi nào dùng Custom Exception?
**Trả lời:**
Dùng custom exception khi cần diễn tả **lỗi business logic** cụ thể mà Java không có sẵn:
- `UserNotFoundException` - không tìm thấy user
- `InsufficientBalanceException` - số dư không đủ
- `DuplicateEmailException` - email đã tồn tại

Custom exception giúp code **rõ nghĩa hơn** và dễ xử lý theo từng loại lỗi.

### Q5: try-with-resources hoạt động thế nào?
**Trả lời:**
- Resource khai báo trong `try()` sẽ **tự động đóng** khi block kết thúc
- Resource phải implement interface `AutoCloseable`
- Thay thế pattern try-finally phức tạp
- Nhiều resource cách nhau bằng dấu `;`
- Đóng theo thứ tự **ngược lại** so với khai báo

### Q6: Error vs Exception?
**Trả lời:**
| Error | Exception |
|-------|-----------|
| Lỗi **nghiêm trọng** của JVM | Lỗi **có thể xử lý** |
| Thường không recover được | Có thể recover |
| `OutOfMemoryError`, `StackOverflowError` | `IOException`, `NullPointerException` |
| Không nên catch | Nên catch và xử lý |

### Q7: NullPointerException xảy ra khi nào? Cách phòng tránh?
**Trả lời:**
Xảy ra khi gọi method/truy cập field trên biến null:
```java
String s = null;
s.length(); // NullPointerException
```
Phòng tránh:
1. Kiểm tra null trước khi dùng: `if (s != null)`
2. Dùng `Optional<T>` (Java 8+)
3. Dùng `Objects.requireNonNull()`
4. Annotation `@NonNull`, `@Nullable`
5. Đặt giá trị mặc định thay vì null

### Q8: Có thể catch nhiều exception trong 1 catch block không?
**Trả lời:**
Có, từ Java 7+ dùng multi-catch:
```java
try {
    // code
} catch (IOException | SQLException e) {
    // xử lý chung cho cả 2 loại exception
    System.out.println(e.getMessage());
}
```
Lưu ý: Các exception trong multi-catch **không được có quan hệ kế thừa**.
