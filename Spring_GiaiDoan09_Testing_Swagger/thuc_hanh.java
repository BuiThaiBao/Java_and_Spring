package com.example.demo;

/**
 * SPRING BOOT - GIAI ĐOẠN 9: TESTING & SWAGGER - THỰC HÀNH
 */

// ============================================================
// 1. UNIT TEST SERVICE
// ============================================================
/*
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = Product.builder()
                .id(1L).name("Laptop").price(new BigDecimal("25000000")).quantity(10)
                .build();
    }

    @Test
    @DisplayName("Tạo sản phẩm thành công")
    void shouldCreateProduct() {
        // Arrange
        CreateProductRequest request = new CreateProductRequest();
        request.setName("Laptop");
        request.setPrice(new BigDecimal("25000000"));
        request.setQuantity(10);
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // Act
        ProductResponse result = productService.create(request);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("Tìm sản phẩm theo ID - thành công")
    void shouldGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        ProductResponse result = productService.getById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getName());
    }

    @Test
    @DisplayName("Tìm sản phẩm theo ID - không tìm thấy")
    void shouldThrowWhenProductNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getById(99L));
    }

    @Test
    @DisplayName("Xóa sản phẩm thành công")
    void shouldDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        assertDoesNotThrow(() -> productService.delete(1L));
        verify(productRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Lấy tất cả sản phẩm")
    void shouldGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(sampleProduct));

        List<ProductResponse> result = productService.getAll();

        assertEquals(1, result.size());
    }
}
*/

// ============================================================
// 2. TEST CONTROLLER (MockMvc)
// ============================================================
/*
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("GET /api/products - trả 200 OK")
    void shouldReturnProducts() throws Exception {
        ProductResponse response = new ProductResponse(1L, "Laptop", 
                new BigDecimal("25000000"), 10);
        when(productService.getAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("POST /api/products - tạo mới trả 201")
    void shouldCreateProduct() throws Exception {
        String body = """
            {"name": "Mouse", "price": 500000, "quantity": 50}
            """;

        ProductResponse response = new ProductResponse(1L, "Mouse", 
                new BigDecimal("500000"), 50);
        when(productService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mouse"));
    }

    @Test
    @DisplayName("POST /api/products - validation lỗi trả 400")
    void shouldReturn400WhenInvalid() throws Exception {
        String body = """
            {"name": "", "price": -1}
            """;
        
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }
}
*/

// ============================================================
// 3. TEST REPOSITORY
// ============================================================
/*
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindByNameContaining() {
        productRepository.save(Product.builder().name("Laptop Dell").price(BigDecimal.valueOf(25000000)).build());
        productRepository.save(Product.builder().name("Laptop HP").price(BigDecimal.valueOf(20000000)).build());
        productRepository.save(Product.builder().name("Mouse").price(BigDecimal.valueOf(500000)).build());

        List<Product> results = productRepository.findByNameContainingIgnoreCase("laptop");

        assertEquals(2, results.size());
    }
}
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Viết test cho AuthService (register, login)
// 2. Viết test cho UserController với MockMvc
// 3. Test exception cases (not found, duplicate)
// 4. Thêm Swagger annotations: @Operation, @ApiResponse
// 5. Đạt test coverage >= 70% cho service layer

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 9: TESTING & SWAGGER ===");
    }
}
