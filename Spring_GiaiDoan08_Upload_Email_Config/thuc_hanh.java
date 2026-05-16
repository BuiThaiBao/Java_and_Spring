package com.example.demo;

/**
 * SPRING BOOT - GIAI ĐOẠN 8: UPLOAD, EMAIL, CONFIG - THỰC HÀNH
 */

// ============================================================
// 1. FILE UPLOAD SERVICE
// ============================================================
/*
@Service
public class FileService {
    private final Path uploadDir = Path.of("uploads");
    
    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(uploadDir);
    }
    
    public String upload(MultipartFile file) throws IOException {
        // Validate
        if (file.isEmpty()) throw new RuntimeException("File is empty");
        
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Only image files allowed");
        }
        
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("File size must be < 5MB");
        }
        
        // Save
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return "/uploads/" + filename;
    }
}
*/

// ============================================================
// 2. FILE UPLOAD CONTROLLER
// ============================================================
/*
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    
    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = fileService.upload(file);
        return Map.of("url", url, "message", "Upload success");
    }
    
    @PostMapping("/upload-multiple")
    public List<String> uploadMultiple(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            urls.add(fileService.upload(file));
        }
        return urls;
    }
}
*/

// ============================================================
// 3. EMAIL SERVICE
// ============================================================
/*
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    
    public void sendVerificationEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Xác thực tài khoản");
        message.setText("Mã OTP của bạn: " + otp + "\nMã có hiệu lực trong 5 phút.");
        
        try {
            mailSender.send(message);
            log.info("Verification email sent to {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to {}", to, e);
            throw new RuntimeException("Email sending failed");
        }
    }
    
    public void sendResetPasswordEmail(String to, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reset mật khẩu");
        message.setText("Link reset: http://localhost:3000/reset-password?token=" + resetToken);
        mailSender.send(message);
    }
}
*/

// ============================================================
// 4. APPLICATION.YML
// ============================================================
/*
# application.yml
server:
  port: 8080

spring:
  profiles:
    active: dev
  servlet:
    multipart:
      max-file-size: 5MB
      max-request-size: 10MB
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

jwt:
  secret: ${JWT_SECRET:myDefaultSecretKeyThatShouldBeVeryLong}
  expiration: 3600000  # 1 hour

logging:
  level:
    root: INFO
    com.example.demo: DEBUG
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Upload avatar cho user (liên kết với user entity)
// 2. Gửi email OTP khi register
// 3. Implement forgot-password + reset-password flow
// 4. Tạo application-dev.yml và application-prod.yml riêng
// 5. Serve static files (uploads) qua ResourceHandler config

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 8: UPLOAD, EMAIL, CONFIG ===");
    }
}
