# Spring Boot - Giai Đoạn 8: Upload File, Email & Configuration

## 📚 Lý Thuyết

### 1. Upload File
```java
@PostMapping("/upload")
public String upload(@RequestParam("file") MultipartFile file) {
    // Validate
    if (file.isEmpty()) throw new RuntimeException("File is empty");
    if (file.getSize() > 5 * 1024 * 1024) throw new RuntimeException("File too large");
    
    // Lưu file
    String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
    Path path = Path.of("uploads", filename);
    Files.copy(file.getInputStream(), path);
    return "/uploads/" + filename;
}
```

**Config:**
```yaml
spring:
  servlet:
    multipart:
      max-file-size: 5MB
      max-request-size: 10MB
```

### 2. Email (JavaMailSender)
```java
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
```

### 3. Profile & Environment
```yaml
# application.yml
spring:
  profiles:
    active: dev

# application-dev.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dev_db

# application-prod.yml  
spring:
  datasource:
    url: jdbc:mysql://prod-server:3306/prod_db
```

### 4. Logging
```java
private static final Logger log = LoggerFactory.getLogger(UserService.class);
// Hoặc dùng Lombok: @Slf4j

log.info("Creating user: {}", email);
log.warn("User not found: {}", id);
log.error("Failed to process", exception);
// KHÔNG log: password, token, OTP
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: MultipartFile xử lý thế nào?
**Trả lời:** Spring tự parse multipart request. Validate: type (image/pdf), size. Lưu: local disk, S3, hoặc cloud storage. Trả URL cho client.

### Q2: Spring Profiles dùng khi nào?
**Trả lời:** Tách cấu hình theo môi trường: dev (DB local), test (H2), prod (DB server). Activate: `spring.profiles.active=prod` hoặc env variable.

### Q3: Tại sao không dùng System.out.println?
**Trả lời:** Không có log level, không format chuẩn, không output ra file, không filter được. SLF4J + Logback: có level (INFO/WARN/ERROR), timestamp, class name, output file, rotation.

### Q4: Environment variable vs hardcode?
**Trả lời:** KHÔNG hardcode password, secret key trong code. Dùng env variable: `${DB_PASSWORD}` hoặc `.env` file. Bảo mật + linh hoạt khi deploy.
