# Spring Boot - Giai Đoạn 3: Layer Architecture & Dependency Injection

## 📚 Lý Thuyết

### 1. Layer Architecture
```
Client (Postman/Frontend)
    ↓ HTTP Request
┌─────────────────────────┐
│   Controller Layer      │  ← Nhận request, trả response
│   @RestController       │  ← KHÔNG chứa business logic
├─────────────────────────┤
│   Service Layer         │  ← Business logic
│   @Service              │  ← Validate, tính toán, chuyển DTO
├─────────────────────────┤
│   Repository Layer      │  ← Giao tiếp database
│   @Repository           │  ← CRUD operations
├─────────────────────────┤
│   Database              │  ← MySQL/PostgreSQL
└─────────────────────────┘
```

**Nguyên tắc:**
- Controller → **KHÔNG** xử lý logic phức tạp
- Service → **KHÔNG** trả Entity trực tiếp (dùng DTO)
- Repository → **KHÔNG** chứa business logic

### 2. Dependency Injection (DI) & IoC

**IoC (Inversion of Control):** Spring quản lý vòng đời object (Bean) thay vì developer tự `new`.

**DI:** Spring tự "inject" (tiêm) dependency vào class cần dùng.

```java
// KHÔNG dùng DI - tự new (tight coupling)
UserService userService = new UserService();

// DI - Spring tự inject (loose coupling)
@Service
public class UserService { }

@RestController
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) { // Constructor Injection
        this.userService = userService;
    }
}
```

### 3. Stereotype Annotations
| Annotation | Layer | Vai trò |
|------------|-------|---------|
| `@Component` | General | Bean tổng quát |
| `@Controller` | Web | Controller trả view |
| `@RestController` | Web | Controller trả JSON |
| `@Service` | Business | Business logic |
| `@Repository` | Data | Database access |
| `@Configuration` | Config | Cấu hình Bean |

### 4. Constructor Injection vs Field Injection
```java
// ✓ NÊN: Constructor Injection
private final UserService userService;
public UserController(UserService userService) {
    this.userService = userService;
}

// ✗ KHÔNG NÊN: Field Injection
@Autowired
private UserService userService;
```

**Tại sao Constructor Injection tốt hơn?**
- `final` → immutable, thread-safe
- Dễ test (truyền mock qua constructor)
- Rõ ràng dependencies
- Compiler phát hiện lỗi thiếu dependency

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: IoC và DI là gì?
**Trả lời:** **IoC (Inversion of Control)** là nguyên tắc đảo ngược quyền kiểm soát — Spring Container quản lý object thay vì developer tự tạo. **DI (Dependency Injection)** là cách thực hiện IoC — Spring tự động tiêm dependency vào class thông qua constructor, setter, hoặc field.

### Q2: @Component vs @Service vs @Repository?
**Trả lời:** Về kỹ thuật, cả 3 đều đánh dấu class là Spring Bean. Khác biệt:
- `@Component`: Bean tổng quát
- `@Service`: Business logic layer — không có tính năng đặc biệt, chỉ semantic
- `@Repository`: Data access layer — Spring **tự động dịch** database exception thành Spring exception

### Q3: Bean scope trong Spring?
**Trả lời:**
- `singleton` (mặc định): 1 instance cho toàn app
- `prototype`: Tạo instance mới mỗi lần request
- `request`: 1 instance per HTTP request
- `session`: 1 instance per HTTP session

### Q4: Constructor Injection vs Field Injection?
**Trả lời:** Constructor Injection tốt hơn: immutable (final), dễ test, compiler check. Field Injection chỉ dùng reflection, khó test, không final. Spring team khuyến nghị Constructor Injection.

### Q5: @Bean vs @Component?
**Trả lời:**
- `@Component`: Đặt trên **class** → Spring tự quét và tạo bean
- `@Bean`: Đặt trên **method** trong `@Configuration` class → Dùng khi cần cấu hình chi tiết hoặc tạo bean từ third-party library (không sửa được source code)
