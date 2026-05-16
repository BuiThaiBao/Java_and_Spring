# Giai Đoạn 14: Annotation và Reflection

## 📚 Lý Thuyết

### 1. Annotation
Annotation là metadata đính kèm vào code, cung cấp thông tin cho compiler hoặc runtime.

#### Built-in Annotations
```java
@Override           // Báo compiler kiểm tra override đúng
@Deprecated         // Đánh dấu method/class không nên dùng
@SuppressWarnings   // Tắt warning cụ thể
@FunctionalInterface // Báo interface chỉ có 1 abstract method
```

#### Custom Annotation
```java
@Retention(RetentionPolicy.RUNTIME) // Có thể đọc tại runtime
@Target(ElementType.METHOD)          // Chỉ dùng cho method
public @interface LogExecutionTime {
    String value() default "";
}
```

### 2. Reflection
Reflection cho phép xem và thao tác với class, method, field **tại runtime**.

```java
Class<?> clazz = Student.class;

// Lấy tất cả methods
for (Method method : clazz.getDeclaredMethods()) {
    System.out.println(method.getName());
}

// Lấy fields
for (Field field : clazz.getDeclaredFields()) {
    System.out.println(field.getName() + " : " + field.getType());
}

// Tạo object
Object obj = clazz.getDeclaredConstructor().newInstance();

// Gọi method
Method method = clazz.getMethod("getName");
String name = (String) method.invoke(obj);
```

**Tại sao quan trọng?** Spring Boot dùng rất nhiều annotation + reflection:
- `@Controller`, `@Service`, `@Repository`
- `@Autowired`, `@Bean`
- `@GetMapping`, `@PostMapping`

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Annotation dùng để làm gì?
**Trả lời:** Annotation cung cấp metadata cho: compiler (kiểm tra lỗi), build tools (code generation), runtime (reflection). Spring Framework dùng annotation thay XML config.

### Q2: Reflection là gì? Ưu nhược điểm?
**Trả lời:** Reflection cho phép inspect/modify class tại runtime. **Ưu:** linh hoạt, framework dùng nhiều. **Nhược:** chậm hơn, bỏ qua access modifier, mất type-safety.

### Q3: @Retention có mấy loại?
**Trả lời:** 3 loại: `SOURCE` (chỉ compile time, VD @Override), `CLASS` (trong .class nhưng không runtime), `RUNTIME` (đọc được qua reflection tại runtime).
