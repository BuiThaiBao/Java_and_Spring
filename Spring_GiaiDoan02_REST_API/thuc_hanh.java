package Spring_GiaiDoan02_REST_API;

/**
 * ============================================================
 * SPRING BOOT - GIAI ĐOẠN 2: REST API - THỰC HÀNH
 * ============================================================
 *
 * Copy các class bên dưới vào Spring Boot project thực tế.
 * Tách mỗi class thành 1 file riêng trong package tương ứng.
 */

// ============================================================
// 1. ENTITY (giả lập bằng class thường, chưa dùng JPA)
// ============================================================

// File: model/Product.java
/*
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    public Product() {}
    public Product(Long id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
*/

// ============================================================
// 2. DTO - Request & Response
// ============================================================

// File: dto/CreateProductRequest.java
/*
public class CreateProductRequest {
    private String name;
    private double price;
    private int quantity;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
*/

// File: dto/ProductResponse.java
/*
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    public ProductResponse(Long id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
*/

// ============================================================
// 3. SERVICE
// ============================================================

// File: service/ProductService.java
/*
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ProductService() {
        // Fake data
        products.add(new Product(idCounter.getAndIncrement(), "Laptop", 25000000, 10));
        products.add(new Product(idCounter.getAndIncrement(), "Phone", 15000000, 20));
        products.add(new Product(idCounter.getAndIncrement(), "Tablet", 10000000, 15));
    }

    public List<ProductResponse> getAll() {
        return products.stream()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getQuantity()))
                .toList();
    }

    public ProductResponse getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getQuantity()))
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public ProductResponse create(CreateProductRequest request) {
        Product product = new Product(
            idCounter.getAndIncrement(),
            request.getName(),
            request.getPrice(),
            request.getQuantity()
        );
        products.add(product);
        return new ProductResponse(product.getId(), product.getName(),
                                   product.getPrice(), product.getQuantity());
    }

    public ProductResponse update(Long id, CreateProductRequest request) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return new ProductResponse(product.getId(), product.getName(),
                                   product.getPrice(), product.getQuantity());
    }

    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
*/

// ============================================================
// 4. CONTROLLER - REST API CRUD
// ============================================================

// File: controller/ProductController.java
/*
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    // GET /api/products/1
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    // POST /api/products (body: JSON)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    // PUT /api/products/1 (body: JSON)
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @RequestBody CreateProductRequest request) {
        return productService.update(id, request);
    }

    // DELETE /api/products/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    // GET /api/products/search?name=laptop
    @GetMapping("/search")
    public List<ProductResponse> search(@RequestParam String name) {
        return productService.getAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
*/

// ============================================================
// TEST BẰNG POSTMAN
// ============================================================
/*
1. GET    http://localhost:8080/api/products         → List all
2. GET    http://localhost:8080/api/products/1       → Get by ID
3. POST   http://localhost:8080/api/products         → Create
   Body: { "name": "Mouse", "price": 500000, "quantity": 50 }
4. PUT    http://localhost:8080/api/products/1       → Update
   Body: { "name": "Laptop Pro", "price": 30000000, "quantity": 5 }
5. DELETE http://localhost:8080/api/products/1       → Delete
6. GET    http://localhost:8080/api/products/search?name=phone → Search
*/

// ============================================================
// BÀI TẬP TỰ LÀM
// ============================================================
// 1. Thêm API GET /api/products/expensive?minPrice=15000000
// 2. Thêm API PATCH /api/products/{id}/price (chỉ update price)
// 3. Tạo UserController với CRUD tương tự
// 4. Thêm API GET /api/products/stats → trả tổng số, giá trung bình
// 5. Dùng ResponseEntity thay vì @ResponseStatus

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING BOOT - GIAI ĐOẠN 2: REST API ===");
        System.out.println("Copy code vào Spring Boot project thực tế để chạy.");
        System.out.println("Test bằng Postman hoặc curl.");
    }
}
