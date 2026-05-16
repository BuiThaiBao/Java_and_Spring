# Spring Boot - Giai Đoạn 7: Spring Security & JWT

## 📚 Lý Thuyết

### 1. Khái niệm
- **Authentication:** Xác thực bạn là ai (login)
- **Authorization:** Bạn có quyền gì (role/permission)

### 2. JWT Flow
```
1. Client gửi POST /api/auth/login { username, password }
2. Server kiểm tra tài khoản → tạo JWT access token + refresh token
3. Client lưu token (localStorage/cookie)
4. Client gửi request + Header: Authorization: Bearer <token>
5. Server verify JWT → cho phép/từ chối truy cập
```

### 3. Các thành phần chính
| Component | Vai trò |
|-----------|---------|
| `SecurityFilterChain` | Cấu hình URL nào cần auth, URL nào public |
| `UserDetailsService` | Load user từ DB khi login |
| `PasswordEncoder` | Mã hóa password (BCrypt) |
| `JwtFilter (OncePerRequestFilter)` | Kiểm tra JWT trên mỗi request |
| `AuthenticationManager` | Xử lý authentication |

### 4. Phân quyền
```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/users")
public List<UserResponse> getUsers() { }

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/profile")
public UserResponse getProfile() { }
```

### 5. SecurityFilterChain cơ bản
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Authentication vs Authorization?
**Trả lời:** Authentication: **xác thực danh tính** (bạn là ai? → login). Authorization: **phân quyền** (bạn được làm gì? → role/permission). Authentication luôn trước Authorization.

### Q2: JWT là gì? Cấu trúc?
**Trả lời:** JSON Web Token — chuỗi mã hóa gồm 3 phần: **Header** (algorithm, type) + **Payload** (claims: userId, role, exp) + **Signature** (verify token chưa bị sửa). Stateless — server không cần lưu session.

### Q3: Session-based vs Token-based auth?
**Trả lời:**
- **Session:** Server lưu session trên memory/DB. Cookie chứa sessionId. Stateful.
- **Token (JWT):** Server KHÔNG lưu gì. Token chứa thông tin user. **Stateless** — phù hợp REST API, microservices, scale dễ.

### Q4: Tại sao phải mã hóa password?
**Trả lời:** Nếu DB bị hack, password plain text bị lộ hết. **BCrypt** hash password với salt ngẫu nhiên → mỗi lần hash khác nhau → không thể reverse. Spring dùng `BCryptPasswordEncoder`.

### Q5: Access Token vs Refresh Token?
**Trả lời:**
- **Access Token:** Ngắn hạn (15-60 phút), dùng cho mỗi request API
- **Refresh Token:** Dài hạn (7-30 ngày), dùng để lấy access token mới khi hết hạn
- Giảm rủi ro: access token lộ → hết hạn nhanh

### Q6: CORS là gì?
**Trả lời:** Cross-Origin Resource Sharing — browser chặn request từ domain khác. Backend cần cấu hình cho phép frontend domain. Spring Boot cấu hình qua `@CrossOrigin` hoặc `CorsConfiguration`.

### Q7: OncePerRequestFilter dùng khi nào?
**Trả lời:** Đảm bảo filter chỉ chạy **1 lần** per request. Dùng cho JWT filter: extract token → verify → set authentication vào SecurityContext.
