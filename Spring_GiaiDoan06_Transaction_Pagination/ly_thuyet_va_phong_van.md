# Spring Boot - Giai Đoạn 6: Transaction & Pagination

## 📚 Lý Thuyết

### 1. @Transactional
```java
@Transactional
public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
    Account from = accountRepo.findById(fromId).orElseThrow();
    Account to = accountRepo.findById(toId).orElseThrow();
    from.withdraw(amount);  // Nếu lỗi ở đây...
    to.deposit(amount);     // ...cả block sẽ rollback
}

@Transactional(readOnly = true) // Tối ưu cho read-only queries
public List<OrderResponse> getOrders() { }
```

**Đặt @Transactional ở đâu?** → Service layer (KHÔNG đặt ở Controller hay Repository).

### 2. Pagination & Sorting
```java
// Controller
@GetMapping
public Page<ProductResponse> getProducts(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "createdAt") String sortBy,
    @RequestParam(defaultValue = "desc") String direction
) { return productService.getProducts(page, size, sortBy, direction); }

// Service
Pageable pageable = PageRequest.of(page, size,
    Sort.by(Sort.Direction.fromString(direction), sortBy));
Page<Product> productPage = productRepository.findAll(pageable);
```

### 3. Filtering
```java
// Repository
Page<Product> findByNameContainingAndStatus(
    String keyword, ProductStatus status, Pageable pageable);
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: @Transactional hoạt động thế nào?
**Trả lời:** Spring tạo **proxy** quanh method. Khi method bắt đầu → mở transaction. Kết thúc OK → commit. Throw **unchecked** exception → rollback. Dùng AOP (Aspect-Oriented Programming) bên dưới.

### Q2: readOnly = true có tác dụng gì?
**Trả lời:** Gợi ý cho Hibernate tối ưu: không cần dirty checking, không flush. Database có thể dùng read-only connection → nhanh hơn.

### Q3: Page vs Slice vs List?
**Trả lời:** `Page<T>`: có totalElements, totalPages — cần thêm COUNT query. `Slice<T>`: chỉ biết có trang tiếp không — nhanh hơn. `List<T>`: không có thông tin phân trang.

### Q4: Transaction propagation là gì?
**Trả lời:** Quyết định behavior khi method A (có transaction) gọi method B:
- `REQUIRED` (default): Dùng chung transaction nếu có, tạo mới nếu không
- `REQUIRES_NEW`: Luôn tạo transaction mới
- `MANDATORY`: Phải có transaction sẵn, nếu không → exception
