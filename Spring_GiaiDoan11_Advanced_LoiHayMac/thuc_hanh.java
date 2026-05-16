package com.example.demo;

/**
 * SPRING BOOT - GIAI ĐOẠN 11: ADVANCED & LỖI HAY MẮC - THỰC HÀNH
 *
 * Giai đoạn này tổng hợp các pattern nâng cao và project thực hành.
 */

// ============================================================
// 1. AOP - LOGGING ASPECT
// ============================================================
/*
@Aspect @Component @Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.demo.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        
        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            log.info("✓ {} executed in {} ms", methodName, elapsed);
            return result;
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            log.error("✗ {} failed after {} ms: {}", methodName, elapsed, e.getMessage());
            throw e;
        }
    }
}
*/

// ============================================================
// 2. MAPPER - MapStruct
// ============================================================
/*
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(CreateProductRequest request);
    
    List<ProductResponse> toResponseList(List<Product> products);
}

// Dùng trong Service:
@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;
    
    public ProductResponse create(CreateProductRequest request) {
        Product product = mapper.toEntity(request);
        return mapper.toResponse(repo.save(product));
    }
    
    public List<ProductResponse> getAll() {
        return mapper.toResponseList(repo.findAll());
    }
}
*/

// ============================================================
// 3. SCHEDULER
// ============================================================
/*
@Component @Slf4j
public class ScheduledTasks {

    @Scheduled(cron = "0 0 2 * * ?") // 2h sáng mỗi ngày
    public void cleanExpiredListings() {
        log.info("Cleaning expired listings...");
        // listingService.deleteExpired();
    }

    @Scheduled(fixedRate = 300000) // Mỗi 5 phút
    public void updateListingRanks() {
        log.info("Updating listing ranks...");
        // listingService.recalculateRanks();
    }
}
*/

// ============================================================
// 4. API RESPONSE WRAPPER
// ============================================================
/*
@Getter @Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

// Dùng trong Controller:
@GetMapping("/{id}")
public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
}
*/

// ============================================================
// 5. PROJECT THỰC HÀNH TỔNG HỢP
// ============================================================
/*
=== PROJECT: MINI E-COMMERCE API ===

Entities:
- User (id, username, email, password, role)
- Category (id, name)
- Product (id, name, price, quantity, category_id)
- Order (id, user_id, status, totalAmount, createdAt)
- OrderItem (id, order_id, product_id, quantity, price)

APIs:
Auth:
  POST /api/auth/register
  POST /api/auth/login
  GET  /api/auth/me

Products:
  GET    /api/products?page=0&size=10&keyword=laptop
  GET    /api/products/{id}
  POST   /api/products         (ADMIN)
  PUT    /api/products/{id}    (ADMIN)
  DELETE /api/products/{id}    (ADMIN)

Orders:
  POST   /api/orders
  GET    /api/orders/my-orders
  GET    /api/orders/{id}
  PATCH  /api/orders/{id}/status   (ADMIN)

Categories:
  GET    /api/categories
  POST   /api/categories    (ADMIN)

Checklist:
[  ] REST API CRUD
[  ] Layer Architecture (Controller-Service-Repository)
[  ] DTO (Request/Response)
[  ] Validation (@Valid)
[  ] Global Exception Handler
[  ] JPA Entity + Relationships
[  ] Pagination + Sorting + Filter
[  ] Spring Security + JWT
[  ] Role-based Authorization
[  ] Upload file
[  ] Unit Tests
[  ] Swagger/OpenAPI
[  ] Dockerfile + docker-compose
[  ] Redis cache (optional)
[  ] Logging (SLF4J)
*/

// ============================================================
// THỨ TỰ HỌC NGẮN GỌN
// ============================================================
/*
Spring Boot basic
→ REST API
→ Layer Architecture
→ DTO
→ Validation
→ Exception Handling
→ Spring Data JPA
→ Relationship
→ Transaction
→ Pagination
→ Security basic
→ JWT
→ Upload file
→ Email
→ Swagger
→ Testing
→ Docker
→ Deploy
*/

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 11: ADVANCED & LỖI HAY MẮC ===");
        System.out.println();
        System.out.println("PROJECT THỰC HÀNH KHUYẾN NGHỊ:");
        System.out.println("1. Product Management API (REST + JPA + DTO + Validation)");
        System.out.println("2. User Auth API (Security + JWT + Email)");
        System.out.println("3. Mini E-commerce (Tổng hợp tất cả)");
        System.out.println("4. Real Estate Listing API (Giống Propify - tốt nhất để đi PV)");
        System.out.println();
        System.out.println("Nếu làm được 70%+ checklist → Đủ apply Intern/Fresher!");
    }
}
