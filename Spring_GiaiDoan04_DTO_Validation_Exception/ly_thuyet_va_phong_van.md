# Spring Boot - Giai Đoạn 4: DTO, Validation & Exception Handling

## 📚 Lý Thuyết

### 1. DTO Pattern
**Tại sao cần DTO?**
- KHÔNG nên trả Entity trực tiếp ra API vì:
  - Lộ field nhạy cảm (password, token)
  - Circular reference khi JPA relationship → StackOverflow JSON
  - Entity gắn với DB, DTO gắn với API — tách biệt concerns

```
Client ←→ DTO ←→ Service ←→ Entity ←→ Database
```

### 2. Validation Annotations
| Annotation | Mô tả |
|------------|--------|
| `@NotNull` | Không null |
| `@NotBlank` | Không null, không rỗng, không chỉ có khoảng trắng |
| `@NotEmpty` | Không null, không rỗng (String, Collection) |
| `@Size(min,max)` | Độ dài min-max |
| `@Min(value)` / `@Max(value)` | Giá trị số min/max |
| `@Email` | Email hợp lệ |
| `@Pattern(regexp)` | Regex pattern |
| `@Valid` | Kích hoạt validation trên @RequestBody |

### 3. Global Exception Handler
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return new ErrorResponse(400, "Validation failed", errors);
    }
}
```

### 4. HTTP Status Codes quan trọng
| Code | Tên | Khi nào dùng |
|------|-----|-------------|
| 200 | OK | GET/PUT/PATCH thành công |
| 201 | Created | POST tạo mới thành công |
| 204 | No Content | DELETE thành công |
| 400 | Bad Request | Validation lỗi, input sai |
| 401 | Unauthorized | Chưa đăng nhập |
| 403 | Forbidden | Không có quyền |
| 404 | Not Found | Resource không tồn tại |
| 409 | Conflict | Trùng lặp (email đã tồn tại) |
| 500 | Internal Server Error | Lỗi server |

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Tại sao không nên trả Entity trực tiếp ra API?
**Trả lời:** 4 lý do: (1) Lộ field nhạy cảm (password), (2) Circular reference → StackOverflow khi serialize JSON, (3) Entity thay đổi → API response thay đổi (tight coupling), (4) Khó kiểm soát format response.

### Q2: @Valid vs @Validated?
**Trả lời:** `@Valid` (javax/jakarta): dùng trên @RequestBody, @PathVariable. `@Validated` (Spring): hỗ trợ thêm **validation groups** để validate khác nhau cho create/update.

### Q3: @RestControllerAdvice là gì?
**Trả lời:** Là global exception handler — bắt exception từ **tất cả** controller, xử lý tập trung, trả response lỗi chuẩn JSON. Kết hợp `@ControllerAdvice` + `@ResponseBody`.

### Q4: @NotNull vs @NotBlank vs @NotEmpty?
**Trả lời:**
- `@NotNull`: Chỉ kiểm tra ≠ null. `""` vẫn pass.
- `@NotEmpty`: ≠ null VÀ ≠ `""`. `"   "` vẫn pass.
- `@NotBlank`: ≠ null VÀ ≠ `""` VÀ ≠ `"   "`. Nghiêm ngặt nhất.

### Q5: Làm sao chuẩn hóa error response?
**Trả lời:** Tạo class ErrorResponse thống nhất cho toàn app:
```java
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors; // validation errors
}
```
Dùng `@RestControllerAdvice` + `@ExceptionHandler` để map exception → ErrorResponse.
