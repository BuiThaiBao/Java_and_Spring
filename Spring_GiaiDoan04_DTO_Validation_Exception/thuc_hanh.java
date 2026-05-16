package com.example.demo;

/**
 * ============================================================
 * SPRING BOOT - GIAI ĐOẠN 4: DTO, VALIDATION, EXCEPTION - THỰC HÀNH
 * ============================================================
 */

// ============================================================
// 1. CUSTOM EXCEPTIONS
// ============================================================
// File: exception/ResourceNotFoundException.java
/*
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
    }
}
*/

// File: exception/DuplicateResourceException.java
/*
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
*/

// ============================================================
// 2. ERROR RESPONSE DTO
// ============================================================
// File: dto/ErrorResponse.java
/*
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter @Setter @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message);
        this.errors = errors;
    }
}
*/

// ============================================================
// 3. GLOBAL EXCEPTION HANDLER
// ============================================================
// File: exception/GlobalExceptionHandler.java
/*
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicate(DuplicateResourceException ex) {
        return new ErrorResponse(409, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return new ErrorResponse(400, "Validation failed", errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return new ErrorResponse(500, "Internal server error");
    }
}
*/

// ============================================================
// 4. REQUEST DTO VỚI VALIDATION
// ============================================================
// File: dto/RegisterRequest.java
/*
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
             message = "Password must contain uppercase letter and digit")
    private String password;
}
*/

// ============================================================
// 5. SERVICE VỚI EXCEPTION
// ============================================================
// File: service/UserService.java (snippet)
/*
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest request) {
        // Kiểm tra email trùng
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + request.getEmail());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // Nên encode!
                .build();

        return toResponse(userRepository.save(user));
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return toResponse(user);
    }
}
*/

// ============================================================
// 6. CONTROLLER VỚI @Valid
// ============================================================
// File: controller/UserController.java (snippet)
/*
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Tạo CreateProductRequest với validation đầy đủ
// 2. Tạo custom exception: InvalidPasswordException
// 3. Xử lý ConstraintViolationException cho @PathVariable validation
// 4. Tạo ApiResponse wrapper: { success, data, message }
// 5. Test validation errors bằng Postman

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 4: DTO, VALIDATION, EXCEPTION ===");
    }
}
