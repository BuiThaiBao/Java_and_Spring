package GiaiDoan11_Enum_Record_Optional;

import java.util.*;

/**
 * ============================================================
 * GIAI ĐOẠN 11: ENUM, RECORD, OPTIONAL - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    // ====== ENUM ======
    enum OrderStatus {
        PENDING("Chờ xử lý"),
        PAID("Đã thanh toán"),
        SHIPPED("Đang giao"),
        DELIVERED("Đã giao"),
        CANCELLED("Đã hủy");

        private final String label;

        OrderStatus(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    enum UserRole {
        ADMIN(1, "Quản trị viên"),
        MODERATOR(2, "Quản lý"),
        USER(3, "Người dùng");

        private final int level;
        private final String description;

        UserRole(int level, String description) {
            this.level = level;
            this.description = description;
        }

        public int getLevel() { return level; }
        public String getDescription() { return description; }

        public boolean hasPermission(UserRole required) {
            return this.level <= required.level;
        }
    }

    // ====== RECORD ======
    record StudentDto(int id, String name, double score) {
        // Compact constructor - validation
        StudentDto {
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("Score phải 0-10");
            }
        }

        // Custom method
        String grade() {
            if (score >= 8) return "Giỏi";
            if (score >= 5) return "TB";
            return "Yếu";
        }
    }

    // ====== CLASS cho Optional demo ======
    static class StudentService {
        private Map<Integer, String> students = new HashMap<>(Map.of(
            1, "Bảo", 2, "An", 3, "Minh"
        ));

        public Optional<String> findById(int id) {
            return Optional.ofNullable(students.get(id));
        }
    }

    // ====== MAIN ======
    public static void main(String[] args) {

        // BÀI 1-2: Enum cơ bản
        System.out.println("=== BÀI 1-2: Enum ===");
        OrderStatus status = OrderStatus.PAID;
        System.out.println("  Status: " + status + " - " + status.getLabel());

        // Switch với enum
        String action = switch (status) {
            case PENDING -> "Chờ thanh toán";
            case PAID -> "Chuẩn bị hàng";
            case SHIPPED -> "Đang vận chuyển";
            case DELIVERED -> "Hoàn thành";
            case CANCELLED -> "Đã hủy đơn";
        };
        System.out.println("  Action: " + action);

        // Duyệt tất cả enum values
        System.out.println("  Tất cả trạng thái:");
        for (OrderStatus s : OrderStatus.values()) {
            System.out.println("    " + s.name() + " → " + s.getLabel());
        }

        // Parse từ String
        OrderStatus fromString = OrderStatus.valueOf("SHIPPED");
        System.out.println("  Parse 'SHIPPED': " + fromString.getLabel());

        // BÀI 3: Enum với logic
        System.out.println("\n=== BÀI 3: Enum + Permission ===");
        UserRole currentUser = UserRole.MODERATOR;
        System.out.println("  Role: " + currentUser.getDescription());
        System.out.println("  Has ADMIN permission? " + currentUser.hasPermission(UserRole.ADMIN));
        System.out.println("  Has USER permission? " + currentUser.hasPermission(UserRole.USER));

        // BÀI 4-5: Record
        System.out.println("\n=== BÀI 4-5: Record ===");
        StudentDto s1 = new StudentDto(1, "Bảo", 9.0);
        StudentDto s2 = new StudentDto(2, "An", 7.5);
        StudentDto s3 = new StudentDto(1, "Bảo", 9.0);

        System.out.println("  s1: " + s1);
        System.out.println("  Name: " + s1.name() + ", Score: " + s1.score());
        System.out.println("  Grade: " + s1.grade());
        System.out.println("  s1.equals(s3): " + s1.equals(s3)); // true

        // BÀI 6-8: Optional
        System.out.println("\n=== BÀI 6-8: Optional ===");
        StudentService service = new StudentService();

        // ifPresent
        service.findById(1).ifPresent(name ->
                System.out.println("  Found: " + name));

        // orElse
        String name = service.findById(99).orElse("Unknown");
        System.out.println("  ID 99: " + name);

        // orElseThrow
        try {
            String found = service.findById(99)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (RuntimeException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // map + orElse
        int nameLength = service.findById(1)
                .map(String::length)
                .orElse(0);
        System.out.println("  Name length of ID 1: " + nameLength);

        // filter
        Optional<String> longName = service.findById(1)
                .filter(n -> n.length() > 5);
        System.out.println("  ID 1 name > 5 chars? " + longName.isPresent());

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("9.  Tạo PaymentMethod enum (MOMO, BANK, CASH) với phí xử lý");
        System.out.println("10. Tạo OrderDto record cho hệ thống đặt hàng");
        System.out.println("11. Dùng Optional trong method tìm kiếm product");
    }
}
