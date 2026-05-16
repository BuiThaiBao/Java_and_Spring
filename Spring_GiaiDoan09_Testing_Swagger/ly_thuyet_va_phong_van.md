# Spring Boot - Giai Đoạn 9: Testing & Swagger

## 📚 Lý Thuyết

### 1. Testing Layers
| Annotation | Test gì | Scope |
|------------|---------|-------|
| `@ExtendWith(MockitoExtension.class)` | Unit test Service | Chỉ logic, mock dependencies |
| `@WebMvcTest` | Unit test Controller | HTTP layer, mock Service |
| `@DataJpaTest` | Test Repository | DB layer, dùng H2 |
| `@SpringBootTest` | Integration test | Full context |

### 2. Unit Test Service (Mockito)
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserRepository userRepository;
    @InjectMocks private UserService userService;

    @Test
    void shouldCreateUser() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("Bao", "bao@gmail.com");
        User savedUser = User.builder().id(1L).username("Bao").email("bao@gmail.com").build();
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponse result = userService.create(request);

        // Assert
        assertEquals("Bao", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getById(1L));
    }
}
```

### 3. Test Controller (MockMvc)
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;

    @Test
    void shouldReturnUsers() throws Exception {
        when(userService.getAll()).thenReturn(List.of(...));
        
        mockMvc.perform(get("/api/users"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)));
    }
}
```

### 4. Swagger/OpenAPI
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```
Truy cập: `http://localhost:8080/swagger-ui.html`

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Unit test vs Integration test?
**Trả lời:** Unit test: test **1 class** riêng lẻ, mock dependencies → nhanh. Integration test: test **nhiều layer** cùng nhau, dùng DB thật/H2 → chậm hơn nhưng test toàn diện.

### Q2: @Mock vs @MockBean?
**Trả lời:** `@Mock` (Mockito): dùng trong unit test KHÔNG có Spring context. `@MockBean` (Spring): thay bean trong Spring context → dùng trong @WebMvcTest, @SpringBootTest.

### Q3: AAA pattern?
**Trả lời:** **Arrange** (setup data, mock) → **Act** (gọi method cần test) → **Assert** (kiểm tra kết quả). Giúp test rõ ràng, dễ đọc.

### Q4: Swagger dùng để làm gì?
**Trả lời:** Tạo API documentation tự động từ code. Frontend dev có thể xem và test API trực tiếp. Hỗ trợ JWT authentication trên Swagger UI.
