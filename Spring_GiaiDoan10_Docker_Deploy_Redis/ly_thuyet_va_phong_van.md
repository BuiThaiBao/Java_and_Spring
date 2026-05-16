# Spring Boot - Giai Đoạn 10: Docker, Deploy & Redis

## 📚 Lý Thuyết

### 1. Build & Run JAR
```bash
# Build
mvn clean package -DskipTests

# Run
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### 2. Dockerfile
```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 3. Docker Compose
```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/demo_db
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    depends_on:
      - mysql

  mysql:
    image: mysql:8
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: demo_db
    volumes:
      - mysql_data:/var/lib/mysql

  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"

volumes:
  mysql_data:
```

### 4. Redis & Spring Cache
```java
// Dependency: spring-boot-starter-data-redis, spring-boot-starter-cache

@Cacheable(value = "products", key = "#id")
public ProductResponse getById(Long id) { }

@CacheEvict(value = "products", key = "#id")
public void delete(Long id) { }

@CachePut(value = "products", key = "#id")
public ProductResponse update(Long id, ...) { }
```

### 5. Redis Use Cases
- **Cache:** Giảm load database
- **OTP storage:** Lưu OTP 5 phút → tự xóa (TTL)
- **Refresh token:** Lưu + blacklist revoked tokens
- **Rate limiting:** Đếm request per IP
- **Session:** Distributed session

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Docker là gì? Tại sao dùng?
**Trả lời:** Docker đóng gói app + dependencies vào **container**. Đảm bảo chạy giống nhau trên mọi môi trường (dev, staging, prod). Dễ deploy, scale, rollback.

### Q2: Dockerfile vs Docker Compose?
**Trả lời:** **Dockerfile**: build **1 image** (app). **Docker Compose**: orchestrate **nhiều container** cùng lúc (app + DB + Redis).

### Q3: Redis dùng trong Spring Boot thế nào?
**Trả lời:** (1) **Cache** — `@Cacheable` giảm query DB, (2) **Session** — distributed session, (3) **OTP** — set TTL 5 phút tự xóa, (4) **Rate limit** — đếm request. Cần `spring-boot-starter-data-redis`.

### Q4: @Cacheable vs @CachePut vs @CacheEvict?
**Trả lời:**
- `@Cacheable`: Kiểm tra cache trước → có thì trả luôn, không thì query DB + lưu cache
- `@CachePut`: Luôn chạy method + **cập nhật** cache
- `@CacheEvict`: **Xóa** cache (khi delete/update entity)

### Q5: Deploy Spring Boot lên VPS thế nào?
**Trả lời:** (1) Build JAR: `mvn clean package`, (2) Copy lên server, (3) `java -jar app.jar` hoặc dùng Docker, (4) Nginx reverse proxy, (5) Systemd service để auto-restart.
