# Spring Boot - Giai Đoạn 1: Giới Thiệu & Làm Quen Spring Boot

## 📚 Lý Thuyết

### 1. Spring Framework vs Spring Boot

| | Spring Framework | Spring Boot |
|-|-----------------|-------------|
| Vai trò | Nền tảng chính, full-featured | Wrapper giúp cấu hình nhanh |
| Cấu hình | Nhiều XML/Java config thủ công | Auto-configuration |
| Server | Cần deploy lên server riêng | **Embedded server** (Tomcat) |
| Khởi tạo | Phức tạp | Spring Initializr |

### 2. Hệ sinh thái Spring
```
Spring Framework (nền tảng)
├── Spring Boot      → Tạo app nhanh, auto-config
├── Spring MVC       → Web/REST API
├── Spring Data JPA  → Database ORM
├── Spring Security  → Authentication/Authorization
├── Spring Cloud     → Microservices
├── Spring Batch     → Batch processing
└── Spring WebFlux   → Reactive programming
```

### 3. Tạo Project với Spring Initializr
- Truy cập: https://start.spring.io
- Chọn: Maven, Java 21, Spring Boot 3.5.x
- Dependencies cơ bản ban đầu:
  - **Spring Web** — REST API
  - **Lombok** — Giảm boilerplate code
  - **Spring Boot DevTools** — Hot reload
  - **Validation** — Input validation
  - **Spring Data JPA** — Database
  - **MySQL Driver** — Kết nối MySQL

### 4. Cấu trúc project chuẩn
```
src/main/java/com/example/demo
├── DemoApplication.java     ← Entry point
├── controller/              ← Nhận request, trả response
├── service/                 ← Business logic
├── repository/              ← Giao tiếp database
├── entity/                  ← Ánh xạ bảng DB
├── dto/                     ← Request/Response objects
├── exception/               ← Custom exceptions
└── config/                  ← Cấu hình (Security, CORS...)

src/main/resources
├── application.yml          ← Cấu hình app
└── static/                  ← Tài nguyên tĩnh
```

### 5. @SpringBootApplication
```java
@SpringBootApplication  // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### 6. application.yml cơ bản
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/demo_db
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Spring Framework và Spring Boot khác nhau thế nào?
**Trả lời:**
- **Spring Framework** là nền tảng lớn cho Java enterprise, cung cấp DI/IoC, AOP, MVC... nhưng cần **cấu hình nhiều** (XML hoặc Java config).
- **Spring Boot** xây dựng trên Spring Framework, thêm **auto-configuration**, **embedded server** (Tomcat), **Spring Initializr** để tạo project nhanh. Giảm boilerplate, convention over configuration.
- Spring Boot KHÔNG thay thế Spring Framework, mà là **layer bọc ngoài** giúp dùng Spring dễ hơn.

### Q2: @SpringBootApplication gồm những annotation gì?
**Trả lời:**
`@SpringBootApplication` = 3 annotation:
1. `@Configuration` — Class này là nguồn cấu hình Bean
2. `@EnableAutoConfiguration` — Tự động cấu hình dựa trên dependency trong classpath
3. `@ComponentScan` — Quét các @Component, @Service, @Repository, @Controller trong package hiện tại và sub-packages

### Q3: Embedded server là gì? Tại sao quan trọng?
**Trả lời:**
Spring Boot đóng gói server (mặc định **Tomcat**) vào trong app. Khi chạy `java -jar app.jar`, Tomcat khởi động cùng app. Không cần cài Tomcat riêng, deploy WAR file. Giúp app chạy **độc lập**, dễ Docker hóa, dễ deploy.

### Q4: application.properties vs application.yml?
**Trả lời:**
Cùng chức năng cấu hình app:
- `.properties`: `server.port=8080` — dạng key=value, đơn giản
- `.yml`: Dạng cây phân cấp, dễ đọc khi config phức tạp
- Nên dùng **yml** cho dự án thực tế vì dễ đọc cấu trúc

### Q5: Spring Boot auto-configuration hoạt động thế nào?
**Trả lời:**
Spring Boot kiểm tra classpath (dependencies trong pom.xml) và tự cấu hình. Ví dụ:
- Có `spring-boot-starter-web` → tự cấu hình DispatcherServlet, Tomcat
- Có `spring-boot-starter-data-jpa` + MySQL driver → tự cấu hình DataSource, EntityManager
- Có thể override bằng `application.yml` hoặc `@Configuration` class

### Q6: Starter dependency là gì?
**Trả lời:**
Starter là **nhóm dependency** được đóng gói sẵn. Thay vì thêm 10+ dependency riêng lẻ, chỉ cần 1 starter:
- `spring-boot-starter-web` = Spring MVC + Tomcat + Jackson
- `spring-boot-starter-data-jpa` = Hibernate + Spring Data JPA
- `spring-boot-starter-security` = Spring Security core
