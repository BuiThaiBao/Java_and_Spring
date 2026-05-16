package Spring_GiaiDoan03_LayerArchitecture_DI;

/**
 * ============================================================
 * SPRING BOOT - GIAI ĐOẠN 3: LAYER ARCHITECTURE & DI - THỰC HÀNH
 * ============================================================
 * Copy vào Spring Boot project. Tách mỗi class 1 file.
 */

// ============================================================
// ENTITY
// ============================================================
// File: entity/Product.java
/*
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
*/

// ============================================================
// DTO - Request
// ============================================================
// File: dto/CreateProductRequest.java
/*
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class CreateProductRequest {
    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    private String description;

    @NotNull(message = "Giá không được null")
    @Min(value = 0, message = "Giá phải >= 0")
    private BigDecimal price;

    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer quantity;
}
*/

// ============================================================
// DTO - Response
// ============================================================
// File: dto/ProductResponse.java
/*
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createdAt;
}
*/

// ============================================================
// REPOSITORY
// ============================================================
// File: repository/ProductRepository.java
/*
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
*/

// ============================================================
// SERVICE - Business Logic
// ============================================================
// File: service/ProductService.java
/*
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Lombok tạo Constructor Injection
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        return toResponse(product);
    }

    public ProductResponse create(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        return toResponse(productRepository.save(product));
    }

    public ProductResponse update(Long id, CreateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return toResponse(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }

    // Mapper: Entity → Response DTO
    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
*/

// ============================================================
// CONTROLLER
// ============================================================
// File: controller/ProductController.java
/*
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @Valid @RequestBody CreateProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
*/

// ============================================================
// BÀI TẬP TỰ LÀM
// ============================================================
// 1. Tạo UserEntity, UserService, UserController tương tự
// 2. Thêm CategoryEntity và quan hệ Product → Category
// 3. Dùng @Configuration + @Bean tạo custom bean
// 4. Viết Mapper class riêng (không viết trong Service)

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 3: LAYER ARCHITECTURE & DI ===");
        System.out.println("Copy code vào project Spring Boot thực tế.");
    }
}
