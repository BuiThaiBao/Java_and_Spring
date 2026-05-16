package GiaiDoan14_Annotation_Reflection;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * ============================================================
 * GIAI ĐOẠN 14: ANNOTATION & REFLECTION - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    // ====== Custom Annotations ======
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface LogExecutionTime {
        String description() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface NotNull {
        String message() default "Field không được null";
    }

    // ====== Sample Class ======
    static class Student {
        @NotNull(message = "Tên không được null")
        private String name;

        @NotNull(message = "Email không được null")
        private String email;

        private int age;

        public Student() {}
        public Student(String name, String email, int age) {
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public int getAge() { return age; }

        @LogExecutionTime(description = "Display student info")
        public void display() {
            System.out.printf("    Student: %s, %s, age %d%n", name, email, age);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', email='" + email + "', age=" + age + '}';
        }
    }

    // ====== Main ======
    public static void main(String[] args) throws Exception {

        // BÀI 1: Reflection - xem thông tin class
        System.out.println("=== BÀI 1: Class Info ===");
        Class<?> clazz = Student.class;
        System.out.println("  Class: " + clazz.getName());
        System.out.println("  Simple name: " + clazz.getSimpleName());
        System.out.println("  Superclass: " + clazz.getSuperclass().getSimpleName());

        // BÀI 2: Lấy tất cả fields
        System.out.println("\n=== BÀI 2: Fields ===");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("  %-10s : %-10s (modifier: %s)%n",
                    field.getName(), field.getType().getSimpleName(),
                    Modifier.toString(field.getModifiers()));
        }

        // BÀI 3: Lấy tất cả methods
        System.out.println("\n=== BÀI 3: Methods ===");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.printf("  %-15s → returns %-10s (params: %d)%n",
                    method.getName(), method.getReturnType().getSimpleName(),
                    method.getParameterCount());
        }

        // BÀI 4: Tạo object qua Reflection
        System.out.println("\n=== BÀI 4: Tạo object ===");
        Constructor<?> constructor = clazz.getDeclaredConstructor(
                String.class, String.class, int.class);
        Object student = constructor.newInstance("Bảo", "bao@gmail.com", 22);
        System.out.println("  Created: " + student);

        // BÀI 5: Gọi method qua Reflection
        System.out.println("\n=== BÀI 5: Gọi method ===");
        Method displayMethod = clazz.getDeclaredMethod("display");
        displayMethod.invoke(student);

        // Set private field
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // Bỏ qua private!
        nameField.set(student, "Bảo (Modified)");
        System.out.println("  After modify: " + student);

        // BÀI 6: Đọc Annotation
        System.out.println("\n=== BÀI 6: Đọc Annotation ===");

        // Kiểm tra @LogExecutionTime
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                LogExecutionTime ann = method.getAnnotation(LogExecutionTime.class);
                System.out.println("  Method '" + method.getName() +
                        "' has @LogExecutionTime: " + ann.description());
            }
        }

        // Kiểm tra @NotNull
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                NotNull ann = field.getAnnotation(NotNull.class);
                System.out.println("  Field '" + field.getName() +
                        "' has @NotNull: " + ann.message());
            }
        }

        // BÀI 7: Simple Validator dùng Reflection
        System.out.println("\n=== BÀI 7: Validator ===");
        Student validStudent = new Student("Bảo", "bao@gmail.com", 22);
        Student invalidStudent = new Student(null, "test@gmail.com", 20);
        Student invalidStudent2 = new Student("An", null, 21);

        validate(validStudent);
        validate(invalidStudent);
        validate(invalidStudent2);

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("8. Tạo @MaxLength annotation và validator");
        System.out.println("9. Tạo simple DI container dùng reflection");
        System.out.println("10. Tạo @Cacheable annotation mô phỏng Spring Cache");
    }

    // Simple validator dùng Reflection
    static void validate(Object obj) {
        Class<?> clazz = obj.getClass();
        boolean valid = true;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        NotNull ann = field.getAnnotation(NotNull.class);
                        System.out.println("  ✗ Validation failed: " + ann.message());
                        valid = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (valid) {
            System.out.println("  ✓ Validation passed: " + obj);
        }
    }
}
