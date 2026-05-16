# Spring Boot - Giai Đoạn 5: Spring Data JPA & Database

## 📚 Lý Thuyết

### 1. JPA Annotations
| Annotation | Mô tả |
|------------|--------|
| `@Entity` | Đánh dấu class là entity (ánh xạ bảng DB) |
| `@Table(name="...")` | Tên bảng |
| `@Id` | Primary key |
| `@GeneratedValue` | Tự tăng ID |
| `@Column` | Tùy chỉnh cột (nullable, unique, length) |
| `@Enumerated` | Map enum |
| `@CreatedDate` / `@LastModifiedDate` | Audit timestamps |

### 2. JpaRepository Methods
```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Query methods tự động tạo SQL từ tên method
    List<Product> findByNameContaining(String name);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    Optional<Product> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByStatus(String status);
    List<Product> findByStatusOrderByCreatedAtDesc(String status);

    // Custom query
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    List<Product> findExpensiveProducts(@Param("minPrice") BigDecimal minPrice);
}
```

### 3. JPA Relationships
```java
// ManyToOne: Product → Category
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;

// OneToMany: Category → Products
@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
private List<Product> products;

// ManyToMany: Student ↔ Course (dùng join table)
@ManyToMany
@JoinTable(name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
private Set<Course> courses;
```

### 4. Fetch Types
| LAZY | EAGER |
|------|-------|
| Load khi cần (truy cập field) | Load ngay khi query entity cha |
| **Mặc định** cho @OneToMany, @ManyToMany | **Mặc định** cho @ManyToOne, @OneToOne |
| Tốt cho performance | Có thể gây N+1 query |

### 5. Lỗi thường gặp
- **LazyInitializationException**: Truy cập lazy field ngoài transaction
- **N+1 Query**: Load 1 entity cha + N query cho entity con → dùng `@Query` với `JOIN FETCH`
- **Circular Reference**: Entity A ↔ B → JSON StackOverflow → dùng DTO hoặc `@JsonIgnore`

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: JPA vs Hibernate?
**Trả lời:** JPA là **specification** (interface), Hibernate là **implementation**. Spring Data JPA sử dụng JPA spec, mặc định dùng Hibernate.

### Q2: N+1 problem là gì? Cách giải quyết?
**Trả lời:** Query 1 lần lấy N entities cha → mỗi entity cha lại query 1 lần lấy entity con = **1 + N queries**. Giải quyết: (1) `JOIN FETCH` trong @Query, (2) `@EntityGraph`, (3) `@BatchSize`.

### Q3: LAZY vs EAGER loading?
**Trả lời:** LAZY chỉ load khi truy cập → tốt cho performance. EAGER load ngay → đơn giản nhưng có thể chậm. Best practice: mặc định LAZY, chỉ EAGER khi luôn cần dữ liệu liên quan.

### Q4: CascadeType có những loại nào?
**Trả lời:** `ALL`, `PERSIST` (save), `MERGE` (update), `REMOVE` (delete), `REFRESH`, `DETACH`. Cẩn thận `REMOVE` — có thể xóa nhầm dữ liệu.

### Q5: ddl-auto có những giá trị nào?
**Trả lời:** `none` (không làm gì), `validate` (kiểm tra schema), `update` (thêm cột/bảng mới), `create` (xóa tạo lại), `create-drop` (create + drop khi shutdown). Production nên dùng `validate` hoặc `none` + Flyway/Liquibase.
