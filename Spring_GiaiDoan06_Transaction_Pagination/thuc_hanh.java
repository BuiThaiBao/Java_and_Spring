package com.example.demo;

/**
 * SPRING BOOT - GIAI ĐOẠN 6: TRANSACTION & PAGINATION - THỰC HÀNH
 */

// ============================================================
// 1. PAGINATION CONTROLLER
// ============================================================
/*
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status
    ) {
        return productService.getProducts(page, size, sortBy, direction, keyword, status);
    }
}
*/

// ============================================================
// 2. SERVICE VỚI PAGINATION + TRANSACTION
// ============================================================
/*
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductResponse> getProducts(int page, int size,
            String sortBy, String direction, String keyword, String status) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> products;
        if (keyword != null && status != null) {
            products = productRepository.findByNameContainingAndStatus(
                    keyword, ProductStatus.valueOf(status), pageable);
        } else if (keyword != null) {
            products = productRepository.findByNameContaining(keyword, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(this::toResponse);
    }

    @Transactional
    public void transferStock(Long fromId, Long toId, int quantity) {
        Product from = productRepository.findById(fromId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", fromId));
        Product to = productRepository.findById(toId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", toId));

        if (from.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        from.setQuantity(from.getQuantity() - quantity);
        to.setQuantity(to.getQuantity() + quantity);
        // Nếu lỗi → rollback cả 2 thay đổi
    }
}
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Thêm filter theo price range: minPrice, maxPrice
// 2. Thêm filter theo category
// 3. Tạo custom PageResponse wrapper
// 4. Viết API transfer money giữa 2 tài khoản
// 5. Test rollback: throw exception giữa chừng transaction

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 6: TRANSACTION & PAGINATION ===");
    }
}
