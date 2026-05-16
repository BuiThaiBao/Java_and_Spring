# Spring Boot - Giai Đoạn 11: Advanced Topics & Lỗi Hay Mắc

## 📚 Lý Thuyết Nâng Cao

### 1. AOP (Aspect-Oriented Programming)
```java
@Aspect @Component
public class LoggingAspect {
    @Around("execution(* com.example.demo.service.*.*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;
        log.info("{} executed in {} ms", joinPoint.getSignature().getName(), time);
        return result;
    }
}
```

### 2. Scheduler & Async
```java
@EnableScheduling
@EnableAsync
@Configuration
public class AsyncConfig { }

// Scheduler
@Scheduled(cron = "0 0 2 * * ?") // 2h sáng mỗi ngày
public void cleanExpiredTokens() { }

// Async
@Async
public CompletableFuture<Void> sendEmailAsync(String to) {
    emailService.send(to);
    return CompletableFuture.completedFuture(null);
}
```

### 3. MapStruct (Mapper)
```java
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(CreateProductRequest request);
}
```

### 4. Flyway (Database Migration)
```sql
-- V1__Create_users_table.sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

---

## ⚠️ Các Lỗi Người Mới Hay Mắc

### 1. Trả Entity trực tiếp ra API
```java
// ✗ SAI
@GetMapping
public List<User> getUsers() { return userRepository.findAll(); }

// ✓ ĐÚNG - dùng DTO
@GetMapping
public List<UserResponse> getUsers() { return userService.getUsers(); }
```

### 2. Viết logic trong Controller
```java
// ✗ SAI - logic trong controller
@PostMapping
public Product create(@RequestBody Product product) {
    if (product.getPrice().compareTo(BigDecimal.ZERO) < 0)
        throw new RuntimeException("Invalid price");
    return productRepository.save(product);
}

// ✓ ĐÚNG - logic trong service
@PostMapping
public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
    return productService.create(request);
}
```

### 3. Không validate request
```java
// ✗ THIẾU @Valid
public UserResponse register(@RequestBody RegisterRequest request) { }

// ✓ CÓ @Valid
public UserResponse register(@Valid @RequestBody RegisterRequest request) { }
```

### 4. Lạm dụng @Autowired field injection
```java
// ✗ KHÔNG NÊN
@Autowired private UserService userService;

// ✓ NÊN
private final UserService userService;
public UserController(UserService userService) { this.userService = userService; }
```

### 5. Dùng ddl-auto: update ở production
```yaml
# ✗ PRODUCTION
spring.jpa.hibernate.ddl-auto: update  # Nguy hiểm!

# ✓ PRODUCTION
spring.jpa.hibernate.ddl-auto: validate  # Chỉ kiểm tra
# + dùng Flyway/Liquibase cho migration
```

### 6. Không mã hóa password
```java
// ✗ SAI
user.setPassword(request.getPassword());

// ✓ ĐÚNG
user.setPassword(passwordEncoder.encode(request.getPassword()));
```

### 7. Log thông tin nhạy cảm
```java
// ✗ TUYỆT ĐỐI KHÔNG LOG
log.info("Password: {}", password);
log.info("Token: {}", jwtToken);
log.info("OTP: {}", otp);

// ✓ CHỈ LOG thông tin cần thiết
log.info("User registered: {}", email);
```

---

## 🎯 Câu Hỏi Phỏng Vấn Tổng Hợp

### Q1: Luồng xử lý 1 request trong Spring Boot?
**Trả lời:**
```
Client → DispatcherServlet → Filter (Security/JWT) → Controller
→ Service → Repository → Database
→ Response ← Controller ← Service ← Repository
```

### Q2: AOP dùng khi nào?
**Trả lời:** Cross-cutting concerns: Logging, Security, Transaction, Performance monitoring, Exception handling. Spring Security và @Transactional đều dùng AOP.

### Q3: Flyway vs ddl-auto?
**Trả lời:** `ddl-auto` tự động thay đổi schema → nguy hiểm ở production. Flyway: **versioned migration scripts** → kiểm soát chặt chẽ, rollback được, team collaboration.

### Q4: Kiến trúc project Spring Boot chuẩn?
**Trả lời:**
```
controller/ → Nhận request, trả response
service/    → Business logic
repository/ → Database access
entity/     → JPA entities (map DB table)
dto/        → Request/Response objects
exception/  → Custom exceptions + GlobalHandler
config/     → Security, CORS, Redis, Swagger
mapper/     → Entity ↔ DTO conversion
```

---

## 📋 Checklist Tự Kiểm Tra

| # | Câu hỏi | ✓/✗ |
|---|---------|-----|
| 1 | Tạo REST API CRUD được không? | |
| 2 | Tách Controller-Service-Repository được không? | |
| 3 | Biết tại sao không trả Entity trực tiếp? | |
| 4 | Validate request bằng @Valid được không? | |
| 5 | Xử lý lỗi bằng @RestControllerAdvice? | |
| 6 | Kết nối MySQL/PostgreSQL được không? | |
| 7 | Dùng JpaRepository + query method được không? | |
| 8 | Tạo quan hệ OneToMany, ManyToOne? | |
| 9 | Hiểu Lazy loading? | |
| 10 | Phân trang API được không? | |
| 11 | Viết API login JWT được không? | |
| 12 | Phân quyền USER/ADMIN? | |
| 13 | Upload file được không? | |
| 14 | Viết unit test service cơ bản? | |
| 15 | Build thành file jar? | |
| 16 | Chạy app bằng Docker? | |

> **70%+ checklist = đủ apply Intern/Fresher Java Spring Boot!**
