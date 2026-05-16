package com.example.demo;

/**
 * SPRING BOOT - GIAI ĐOẠN 10: DOCKER, DEPLOY, REDIS - THỰC HÀNH
 */

// ============================================================
// 1. DOCKERFILE
// ============================================================
/*
--- File: Dockerfile ---
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
*/

// ============================================================
// 2. DOCKER COMPOSE
// ============================================================
/*
--- File: docker-compose.yml ---
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/demo_db
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - SPRING_REDIS_HOST=redis
    depends_on:
      - mysql
      - redis

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
*/

// ============================================================
// 3. REDIS CONFIG
// ============================================================
/*
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .serializeValuesWith(
                    SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
    }
}
*/

// ============================================================
// 4. SERVICE VỚI CACHE
// ============================================================
/*
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Cacheable(value = "products", key = "#id")
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        log.info("Fetching product from DB: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return toResponse(product);
    }

    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public void delete(Long id) {
        log.info("Deleting product + cache: {}", id);
        productRepository.deleteById(id);
    }

    @CachePut(value = "products", key = "#result.id")
    @Transactional
    public ProductResponse update(Long id, CreateProductRequest request) {
        log.info("Updating product + cache: {}", id);
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(request.getName());
        return toResponse(productRepository.save(product));
    }
}
*/

// ============================================================
// 5. OTP SERVICE VỚI REDIS
// ============================================================
/*
@Service
@RequiredArgsConstructor
public class OtpService {
    private final StringRedisTemplate redisTemplate;

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        redisTemplate.opsForValue().set("otp:" + email, otp, Duration.ofMinutes(5));
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        String stored = redisTemplate.opsForValue().get("otp:" + email);
        if (stored != null && stored.equals(otp)) {
            redisTemplate.delete("otp:" + email);
            return true;
        }
        return false;
    }
}
*/

// ============================================================
// DOCKER COMMANDS
// ============================================================
/*
# Build & run
docker-compose up --build -d

# Xem logs
docker-compose logs -f app

# Dừng
docker-compose down

# Build JAR trước khi docker build
mvn clean package -DskipTests
*/

// ============================================================
// BÀI TẬP
// ============================================================
// 1. Dockerize toàn bộ app + MySQL + Redis
// 2. Thêm @Cacheable cho API get product by ID
// 3. Implement OTP verification khi register
// 4. Thêm Redis rate limiting (5 requests/phút per IP)
// 5. Deploy app lên VPS hoặc Render/Railway

public class thuc_hanh {
    public static void main(String[] args) {
        System.out.println("=== SPRING - GĐ 10: DOCKER, DEPLOY, REDIS ===");
    }
}
