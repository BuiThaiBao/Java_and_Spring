package com.example.demo;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 * SPRING BOOT - GIAI ĐOẠN 1: GIỚI THIỆU - THỰC HÀNH
 * ============================================================
 *
 * LƯU Ý: File này là pseudo-code hướng dẫn.
 * Để chạy thực tế, cần tạo Spring Boot project từ Spring Initializr.
 *
 * HƯỚNG DẪN TẠO PROJECT:
 * 1. Truy cập https://start.spring.io
 * 2. Chọn: Maven, Java 21, Spring Boot 3.5.x
 * 3. Group: com.example | Artifact: demo
 * 4. Thêm dependencies: Spring Web, Lombok, DevTools
 * 5. Download, giải nén, mở bằng IntelliJ
 * 6. Chạy DemoApplication.java
 * 7. Mở browser: http://localhost:8080
 */

// ============================================================
// BÀI 1: File main - Entry point
// ============================================================
// @SpringBootApplication
// public class DemoApplication {
//     public static void main(String[] args) {
//         SpringApplication.run(DemoApplication.class, args);
//     }
// }

// ============================================================
// BÀI 2: Controller đầu tiên - Hello World API
// ============================================================
// @RestController
// public class HelloController {
//
//     @GetMapping("/hello")
//     public String hello() {
//         return "Hello Spring Boot!";
//     }
//
//     @GetMapping("/hello/{name}")
//     public String helloName(@PathVariable String name) {
//         return "Hello " + name + "!";
//     }
//
//     // Test: GET http://localhost:8080/hello
//     // Test: GET http://localhost:8080/hello/Bao
// }

// ============================================================
// BÀI 3: Trả về JSON
// ============================================================
// @RestController
// @RequestMapping("/api")
// public class ApiController {
//
//     @GetMapping("/info")
//     public Map<String, Object> getInfo() {
//         Map<String, Object> info = new HashMap<>();
//         info.put("app", "Demo Spring Boot");
//         info.put("version", "1.0.0");
//         info.put("author", "Bao");
//         return info; // Spring tự chuyển thành JSON
//     }
// }

// ============================================================
// BÀI 4: application.yml mẫu
// ============================================================
/*
server:
  port: 8080

spring:
  application:
    name: demo-app

  datasource:
    url: jdbc:mysql://localhost:3306/demo_db
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

logging:
  level:
    root: INFO
    com.example.demo: DEBUG
*/

// ============================================================
// BÀI 5: pom.xml - Dependencies cơ bản
// ============================================================
/*
<dependencies>
    <!-- Spring Web - REST API -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA - Database -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- DevTools - Hot reload -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
*/

// ============================================================
// BÀI TẬP TỰ LÀM
// ============================================================
// 1. Tạo project Spring Boot từ Spring Initializr
// 2. Viết API GET /api/greeting?name=Bao → trả JSON { "message": "Hello Bao" }
// 3. Thay đổi port sang 9090 trong application.yml
// 4. Tạo API trả về danh sách sản phẩm giả (fake data bằng List)
// 5. Test tất cả API bằng Postman

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING BOOT - GIAI ĐOẠN 1 ===");
        System.out.println("File này là hướng dẫn pseudo-code.");
        System.out.println("Hãy tạo project Spring Boot thực tế từ https://start.spring.io");
        System.out.println();
        System.out.println("Các bước:");
        System.out.println("1. Tạo project từ Spring Initializr");
        System.out.println("2. Mở bằng IntelliJ IDEA");
        System.out.println("3. Chạy DemoApplication.java");
        System.out.println("4. Mở http://localhost:8080");
        System.out.println("5. Tạo Controller đầu tiên");
        System.out.println("6. Test bằng Postman");
    }
}
