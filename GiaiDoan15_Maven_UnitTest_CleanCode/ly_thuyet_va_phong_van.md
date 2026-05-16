# Giai Đoạn 15: Maven, Unit Test, Clean Code

## 📚 Lý Thuyết

### 1. Maven

#### pom.xml - file cấu hình chính
```xml
<project>
    <groupId>com.example</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0</version>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

#### Maven Commands
| Command | Mô tả |
|---------|--------|
| `mvn clean` | Xóa folder target |
| `mvn compile` | Compile source code |
| `mvn test` | Chạy unit tests |
| `mvn package` | Đóng gói thành JAR/WAR |
| `mvn install` | Install vào local repository |
| `mvn clean package` | Clean rồi đóng gói |

#### Maven Lifecycle
```
validate → compile → test → package → verify → install → deploy
```

#### Dependency Scope
| Scope | Compile | Test | Runtime |
|-------|:-------:|:----:|:-------:|
| `compile` (default) | ✅ | ✅ | ✅ |
| `test` | ❌ | ✅ | ❌ |
| `provided` | ✅ | ✅ | ❌ |
| `runtime` | ❌ | ✅ | ✅ |

### 2. Unit Test với JUnit 5

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Cộng 2 số dương")
    void shouldAddTwoPositiveNumbers() {
        assertEquals(5, calculator.sum(2, 3));
    }

    @Test
    void shouldThrowExceptionWhenDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    void shouldReturnTrueForEvenNumber() {
        assertTrue(calculator.isEven(4));
        assertFalse(calculator.isEven(3));
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }
}
```

#### JUnit Assertions
| Assertion | Mô tả |
|-----------|--------|
| `assertEquals(expected, actual)` | Bằng nhau |
| `assertNotEquals(a, b)` | Không bằng |
| `assertTrue(condition)` | Đúng |
| `assertFalse(condition)` | Sai |
| `assertNull(obj)` | Null |
| `assertNotNull(obj)` | Không null |
| `assertThrows(Exception.class, () -> {})` | Ném exception |
| `assertAll(executables...)` | Kiểm tra nhiều điều kiện |

### 3. Clean Code

#### Nguyên tắc cốt lõi
1. **Tên biến rõ nghĩa**
   ```java
   // ✗ int a = 10;
   // ✓ int userAge = 10;
   ```
2. **Method ngắn** (< 20 dòng, làm 1 việc)
3. **Class có 1 trách nhiệm** (Single Responsibility)
4. **DRY - Don't Repeat Yourself**
5. **Không viết if-else quá dài** → dùng polymorphism, strategy pattern
6. **Tách service xử lý logic** khỏi controller
7. **Không hard-code** → dùng constants, config

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Maven là gì? pom.xml chứa gì?
**Trả lời:** Maven là build tool quản lý dependency, build lifecycle, project structure. pom.xml chứa: project info (groupId, artifactId, version), dependencies, plugins, build config.

### Q2: `mvn clean package` làm gì?
**Trả lời:** `clean` xóa folder target (build cũ). `package` compile → test → đóng gói thành JAR/WAR. Chạy tuần tự: clean trước, package sau.

### Q3: Tại sao cần Unit Test?
**Trả lời:** Phát hiện bug sớm, tự tin refactor, documentation cho code, CI/CD pipeline, đảm bảo regression không xảy ra. Test nên: nhanh, độc lập, lặp lại được, tự kiểm tra.

### Q4: @BeforeEach vs @BeforeAll?
**Trả lời:** `@BeforeEach`: chạy **trước mỗi** test method → setup fresh state. `@BeforeAll`: chạy **1 lần** trước tất cả tests → setup heavy resource (phải static).

### Q5: AAA pattern trong testing?
**Trả lời:** **Arrange** (chuẩn bị dữ liệu) → **Act** (thực hiện action) → **Assert** (kiểm tra kết quả). Giúp test rõ ràng, dễ đọc.

### Q6: Clean Code quan trọng thế nào?
**Trả lời:** Code đọc nhiều hơn viết (10:1). Clean code giảm thời gian maintain, dễ onboard member mới, giảm bug. Quy tắc: "Leave the code cleaner than you found it."

### Q7: Dependency scope `test` vs `compile`?
**Trả lời:** `compile` (default): có ở mọi nơi (compile + test + runtime). `test`: chỉ dùng khi test, KHÔNG đóng gói vào production. VD: JUnit, Mockito dùng scope `test`.
