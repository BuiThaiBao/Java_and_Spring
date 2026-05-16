package com.example.demo;

/**
 * ============================================================
 * SPRING BOOT - GIAI ĐOẠN 5: JPA & DATABASE - THỰC HÀNH
 * ============================================================
 */

// ============================================================
// 1. ENTITY với Relationships
// ============================================================

// File: entity/Category.java
/*
@Entity @Table(name = "categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore // Tránh circular reference
    private List<Product> products = new ArrayList<>();
}
*/

// File: entity/Product.java
/*
@Entity @Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}

enum ProductStatus { ACTIVE, INACTIVE, OUT_OF_STOCK }
*/

// ============================================================
// 2. REPOSITORY với Query Methods
// ============================================================

// File: repository/ProductRepository.java
/*
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Auto-generated queries from method name
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByStatus(ProductStatus status);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    boolean existsByName(String name);
    long countByStatus(ProductStatus status);

    // Custom JPQL query
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice ORDER BY p.price DESC")
    List<Product> findExpensiveProducts(@Param("minPrice") BigDecimal minPrice);

    // JOIN FETCH (tránh N+1)
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByIdWithCategory(@Param("id") Long id);

    // Native SQL query
    @Query(value = "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery = true)
    List<Product> searchByName(String keyword);

    // Pagination
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
}
*/

// ============================================================
// 3. SERVICE với Transaction
// ============================================================

// File: service/ProductService.java
/*
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(category)
                .build();

        return toResponse(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return productRepository.findAll(pageable).map(this::toResponse);
    }
}
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Tạo Category CRUD đầy đủ
// 2. Tạo query method: findByPriceGreaterThanAndStatus
// 3. Thêm pagination cho GET /api/products?page=0&size=10
// 4. Viết @Query custom: tìm top 5 sản phẩm đắt nhất
// 5. Xử lý N+1 với JOIN FETCH

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 5: JPA & DATABASE ===");
    }
}
