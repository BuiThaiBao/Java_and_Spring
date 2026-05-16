import java.util.*;
import java.util.stream.*;

/**
 * ============================================================
 * GIAI ĐOẠN 9: STREAM API - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    static class Student {
        private int id;
        private String name;
        private double score;
        private String className;

        public Student(int id, String name, double score, String className) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.className = className;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getScore() { return score; }
        public String getClassName() { return className; }

        @Override
        public String toString() {
            return String.format("%-8s (%.1f) [%s]", name, score, className);
        }
    }

    static class Order {
        private int id;
        private String product;
        private double price;
        private int quantity;
        private String status;

        public Order(int id, String product, double price, int quantity, String status) {
            this.id = id;
            this.product = product;
            this.price = price;
            this.quantity = quantity;
            this.status = status;
        }

        public int getId() { return id; }
        public String getProduct() { return product; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public String getStatus() { return status; }
        public double getTotal() { return price * quantity; }
    }

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student(1, "Bảo", 9.0, "12A1"),
            new Student(2, "An", 7.5, "12A2"),
            new Student(3, "Minh", 8.5, "12A1"),
            new Student(4, "Hương", 6.0, "12A2"),
            new Student(5, "Nam", 9.5, "12A1"),
            new Student(6, "Lan", 5.0, "12A2"),
            new Student(7, "Tuấn", 8.0, "12A1"),
            new Student(8, "Mai", 7.0, "12A2")
        );

        // BÀI 1: filter - Lọc sinh viên điểm >= 8
        System.out.println("=== BÀI 1: filter - Điểm >= 8 ===");
        List<Student> goodStudents = students.stream()
                .filter(s -> s.getScore() >= 8)
                .toList();
        goodStudents.forEach(s -> System.out.println("  " + s));

        // BÀI 2: map - Lấy danh sách tên
        System.out.println("\n=== BÀI 2: map - Tên sinh viên ===");
        List<String> names = students.stream()
                .map(Student::getName)
                .toList();
        System.out.println("  " + names);

        // BÀI 3: sorted - Sắp xếp theo điểm giảm dần
        System.out.println("\n=== BÀI 3: sorted - Điểm giảm dần ===");
        students.stream()
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                .forEach(s -> System.out.println("  " + s));

        // BÀI 4: Tính điểm trung bình
        System.out.println("\n=== BÀI 4: Điểm trung bình ===");
        double avg = students.stream()
                .mapToDouble(Student::getScore)
                .average()
                .orElse(0);
        System.out.printf("  Điểm trung bình: %.2f%n", avg);

        // BÀI 5: Sinh viên điểm cao nhất
        System.out.println("\n=== BÀI 5: Điểm cao nhất ===");
        students.stream()
                .max(Comparator.comparingDouble(Student::getScore))
                .ifPresent(s -> System.out.println("  Top 1: " + s));

        // BÀI 6: groupingBy - Nhóm theo lớp
        System.out.println("\n=== BÀI 6: groupingBy - Theo lớp ===");
        Map<String, List<Student>> grouped = students.stream()
                .collect(Collectors.groupingBy(Student::getClassName));
        grouped.forEach((className, list) -> {
            System.out.println("  " + className + ":");
            list.forEach(s -> System.out.println("    " + s));
        });

        // BÀI 7: Đếm sinh viên theo lớp
        System.out.println("\n=== BÀI 7: Đếm theo lớp ===");
        Map<String, Long> countByClass = students.stream()
                .collect(Collectors.groupingBy(Student::getClassName, Collectors.counting()));
        countByClass.forEach((k, v) -> System.out.println("  " + k + ": " + v + " sinh viên"));

        // BÀI 8: distinct - Email không trùng
        System.out.println("\n=== BÀI 8: distinct ===");
        List<String> emails = Arrays.asList("a@g.com", "b@g.com", "a@g.com", "c@g.com", "b@g.com");
        List<String> unique = emails.stream().distinct().toList();
        System.out.println("  Trước: " + emails);
        System.out.println("  Sau: " + unique);

        // BÀI 9: toMap - List → Map
        System.out.println("\n=== BÀI 9: toMap - ID → Student ===");
        Map<Integer, Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getId, s -> s));
        studentMap.forEach((id, s) -> System.out.println("  " + id + " → " + s));

        // BÀI 10: reduce - Tổng doanh thu
        System.out.println("\n=== BÀI 10: reduce - Doanh thu ===");
        List<Order> orders = Arrays.asList(
            new Order(1, "Laptop", 25000000, 2, "PAID"),
            new Order(2, "Phone", 15000000, 3, "PAID"),
            new Order(3, "Tablet", 10000000, 1, "CANCELLED"),
            new Order(4, "Mouse", 500000, 10, "PAID"),
            new Order(5, "Keyboard", 1000000, 5, "PENDING")
        );

        double totalRevenue = orders.stream()
                .filter(o -> "PAID".equals(o.getStatus()))
                .mapToDouble(Order::getTotal)
                .sum();
        System.out.printf("  Tổng doanh thu (PAID): %,.0f VND%n", totalRevenue);

        // Thống kê theo trạng thái
        Map<String, Double> revenueByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.summingDouble(Order::getTotal)));
        System.out.println("  Doanh thu theo trạng thái:");
        revenueByStatus.forEach((status, revenue) ->
                System.out.printf("    %s: %,.0f VND%n", status, revenue));

        // BONUS: Tổng hợp thống kê
        System.out.println("\n=== BONUS: summarizingDouble ===");
        DoubleSummaryStatistics stats = students.stream()
                .mapToDouble(Student::getScore)
                .summaryStatistics();
        System.out.println("  Count: " + stats.getCount());
        System.out.printf("  Min: %.1f%n", stats.getMin());
        System.out.printf("  Max: %.1f%n", stats.getMax());
        System.out.printf("  Avg: %.2f%n", stats.getAverage());
        System.out.printf("  Sum: %.1f%n", stats.getSum());

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("11. Tìm top 3 sinh viên điểm cao nhất");
        System.out.println("12. Partition sinh viên đạt/không đạt (>= 5)");
        System.out.println("13. flatMap: từ List<List<String>> → List<String>");
        System.out.println("14. Tính điểm trung bình theo từng lớp");
        System.out.println("15. Tìm sản phẩm bán chạy nhất (theo quantity)");
    }
}
