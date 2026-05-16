# Spring Boot - Giai Đoạn 2: REST API với Spring Web

## 📚 Lý Thuyết

### Annotations quan trọng
| Annotation | Mô tả | Ví dụ |
|------------|--------|-------|
| `@RestController` | Đánh dấu class là REST controller (= @Controller + @ResponseBody) | Class level |
| `@RequestMapping` | Base URL cho controller | `@RequestMapping("/api/users")` |
| `@GetMapping` | HTTP GET | Lấy dữ liệu |
| `@PostMapping` | HTTP POST | Tạo mới |
| `@PutMapping` | HTTP PUT | Cập nhật toàn bộ |
| `@PatchMapping` | HTTP PATCH | Cập nhật một phần |
| `@DeleteMapping` | HTTP DELETE | Xóa |
| `@PathVariable` | Lấy giá trị từ URL path | `/users/{id}` |
| `@RequestParam` | Lấy query parameter | `/users?name=bao` |
| `@RequestBody` | Lấy JSON body | POST/PUT body |
| `@ResponseStatus` | Set HTTP status code | `@ResponseStatus(HttpStatus.CREATED)` |

### HTTP Methods & Status Codes
| Method | Dùng khi | Status thường trả |
|--------|----------|-------------------|
| GET | Lấy dữ liệu | 200 OK |
| POST | Tạo mới | 201 Created |
| PUT | Cập nhật toàn bộ | 200 OK |
| PATCH | Cập nhật một phần | 200 OK |
| DELETE | Xóa | 204 No Content |

### Ví dụ CRUD Controller
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<UserResponse> getAll() { }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) { }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody CreateUserRequest request) { }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UpdateUserRequest request) { }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { }
}
```

### Các loại Request Data
```java
// 1. PathVariable - từ URL: GET /api/users/1
@GetMapping("/{id}")
public UserDto getUser(@PathVariable Long id) { }

// 2. RequestParam - query string: GET /api/users?name=bao&page=0
@GetMapping
public List<UserDto> search(@RequestParam String name,
                            @RequestParam(defaultValue = "0") int page) { }

// 3. RequestBody - JSON body: POST /api/users
@PostMapping
public UserDto create(@RequestBody CreateUserRequest request) { }

// 4. RequestHeader
@GetMapping("/me")
public UserDto me(@RequestHeader("Authorization") String token) { }
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: @Controller vs @RestController?
**Trả lời:**
- `@Controller`: Trả về **view** (HTML template). Cần `@ResponseBody` trên mỗi method để trả JSON.
- `@RestController`: = `@Controller` + `@ResponseBody`. Mọi method tự động trả JSON. Dùng cho **REST API**.

### Q2: @PathVariable vs @RequestParam?
**Trả lời:**
- `@PathVariable`: Lấy từ **URL path** → `/users/{id}` → `@PathVariable Long id`. Dùng cho resource identifier.
- `@RequestParam`: Lấy từ **query string** → `/users?name=bao` → `@RequestParam String name`. Dùng cho filter, search, pagination.

### Q3: PUT vs PATCH?
**Trả lời:**
- **PUT**: Cập nhật **toàn bộ** resource. Client gửi full object. Nếu thiếu field → set null.
- **PATCH**: Cập nhật **một phần**. Client chỉ gửi field cần sửa. Linh hoạt hơn.

### Q4: REST API design best practices?
**Trả lời:**
1. Dùng **danh từ số nhiều**: `/api/users`, `/api/products`
2. Dùng **HTTP methods** đúng: GET đọc, POST tạo, PUT/PATCH sửa, DELETE xóa
3. Versioning: `/api/v1/users`
4. Phân trang: `?page=0&size=10`
5. Filter: `?status=ACTIVE&sort=createdAt,desc`
6. Trả **status code** chính xác
7. Trả **error response** chuẩn JSON

### Q5: ResponseEntity là gì?
**Trả lời:**
`ResponseEntity` cho phép kiểm soát **toàn bộ** HTTP response: status code, headers, body.
```java
return ResponseEntity.status(HttpStatus.CREATED)
        .header("Location", "/api/users/" + user.getId())
        .body(userResponse);
```
