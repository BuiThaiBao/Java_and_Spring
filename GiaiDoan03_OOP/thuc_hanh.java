import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * GIAI ĐOẠN 3: OOP - THỰC HÀNH
 * ============================================================
 *
 * Bao gồm 10 bài tập OOP:
 * 1-3: Class Student + getter/setter + constructor + StudentManager
 * 4-6: Abstract Employee + FullTime/PartTime + tính lương
 * 7-9: Interface Payment + MomoPayment/BankPayment/CashPayment
 * 10:  Refactor dùng polymorphism
 *
 * Cấu trúc: Tất cả class trong 1 file để dễ chạy.
 * Thực tế nên tách mỗi class 1 file riêng.
 */

public class thuc_hanh {

    // ============================================================
    // BÀI 1-3: Class Student + StudentManager
    // ============================================================

    static class Student {
        private int id;
        private String name;
        private int age;
        private double score;

        // Constructor mặc định
        public Student() {}

        // Constructor đầy đủ
        public Student(int id, String name, int age, double score) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.score = score;
        }

        // Getter / Setter
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) {
            if (age > 0 && age < 150) this.age = age;
            else throw new IllegalArgumentException("Tuổi không hợp lệ: " + age);
        }

        public double getScore() { return score; }
        public void setScore(double score) {
            if (score >= 0 && score <= 10) this.score = score;
            else throw new IllegalArgumentException("Điểm không hợp lệ: " + score);
        }

        // Xếp loại học lực
        public String getGrade() {
            if (score >= 9) return "Xuất sắc";
            if (score >= 8) return "Giỏi";
            if (score >= 6.5) return "Khá";
            if (score >= 5) return "Trung bình";
            return "Yếu";
        }

        @Override
        public String toString() {
            return String.format("ID: %d | %-10s | Tuổi: %d | Điểm: %.1f | %s",
                    id, name, age, score, getGrade());
        }
    }

    static class StudentManager {
        private List<Student> students = new ArrayList<>();

        // Thêm sinh viên
        public void add(Student student) {
            students.add(student);
            System.out.println("✓ Đã thêm: " + student.getName());
        }

        // Sửa sinh viên theo id
        public void update(int id, String newName, int newAge, double newScore) {
            Student s = findById(id);
            if (s != null) {
                s.setName(newName);
                s.setAge(newAge);
                s.setScore(newScore);
                System.out.println("✓ Đã cập nhật sinh viên ID " + id);
            } else {
                System.out.println("✗ Không tìm thấy sinh viên ID " + id);
            }
        }

        // Xóa sinh viên theo id
        public void delete(int id) {
            Student s = findById(id);
            if (s != null) {
                students.remove(s);
                System.out.println("✓ Đã xóa sinh viên ID " + id);
            } else {
                System.out.println("✗ Không tìm thấy sinh viên ID " + id);
            }
        }

        // Tìm sinh viên theo id
        public Student findById(int id) {
            for (Student s : students) {
                if (s.getId() == id) return s;
            }
            return null;
        }

        // Tìm sinh viên theo tên
        public List<Student> findByName(String name) {
            List<Student> result = new ArrayList<>();
            for (Student s : students) {
                if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }

        // Hiển thị tất cả
        public void displayAll() {
            System.out.println("-".repeat(60));
            for (Student s : students) {
                System.out.println(s);
            }
            System.out.println("-".repeat(60));
        }
    }

    // ============================================================
    // BÀI 4-6: Abstract Employee + FullTime/PartTime
    // ============================================================

    static abstract class Employee {
        protected int id;
        protected String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Method trừu tượng - class con PHẢI implement
        public abstract double calculateSalary();

        public void displayInfo() {
            System.out.printf("ID: %d | %-12s | Lương: %,.0f VND | Loại: %s%n",
                    id, name, calculateSalary(), getType());
        }

        public abstract String getType();
    }

    static class FullTimeEmployee extends Employee {
        private double baseSalary;
        private double bonus;

        public FullTimeEmployee(int id, String name, double baseSalary, double bonus) {
            super(id, name); // Gọi constructor class cha
            this.baseSalary = baseSalary;
            this.bonus = bonus;
        }

        @Override
        public double calculateSalary() {
            return baseSalary + bonus;
        }

        @Override
        public String getType() {
            return "Full-time";
        }
    }

    static class PartTimeEmployee extends Employee {
        private double hourlyRate;
        private int hoursWorked;

        public PartTimeEmployee(int id, String name, double hourlyRate, int hoursWorked) {
            super(id, name);
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        @Override
        public double calculateSalary() {
            return hourlyRate * hoursWorked;
        }

        @Override
        public String getType() {
            return "Part-time";
        }
    }

    // ============================================================
    // BÀI 7-9: Interface Payment + Implementations
    // ============================================================

    interface Payment {
        void pay(double amount);
        String getPaymentMethod();

        // Default method (Java 8+)
        default void printReceipt(double amount) {
            System.out.printf("  → Biên lai: Thanh toán %,.0f VND qua %s%n",
                    amount, getPaymentMethod());
        }
    }

    static class MomoPayment implements Payment {
        private String phoneNumber;

        public MomoPayment(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("  Thanh toán %,.0f VND qua Momo (SĐT: %s)%n", amount, phoneNumber);
            printReceipt(amount);
        }

        @Override
        public String getPaymentMethod() {
            return "Momo";
        }
    }

    static class BankPayment implements Payment {
        private String bankName;
        private String accountNumber;

        public BankPayment(String bankName, String accountNumber) {
            this.bankName = bankName;
            this.accountNumber = accountNumber;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("  Chuyển khoản %,.0f VND qua %s (TK: %s)%n",
                    amount, bankName, accountNumber);
            printReceipt(amount);
        }

        @Override
        public String getPaymentMethod() {
            return "Bank Transfer - " + bankName;
        }
    }

    static class CashPayment implements Payment {
        @Override
        public void pay(double amount) {
            System.out.printf("  Thanh toán tiền mặt %,.0f VND%n", amount);
            printReceipt(amount);
        }

        @Override
        public String getPaymentMethod() {
            return "Tiền mặt";
        }
    }

    // ============================================================
    // BÀI 10: Demo Polymorphism - Hệ thống thanh toán
    // ============================================================

    static void processPayment(Payment payment, double amount) {
        // Polymorphism: Cùng method pay() nhưng hành vi khác nhau
        // tùy thuộc vào object thực tế (Momo, Bank, Cash)
        payment.pay(amount);
    }

    // ============================================================
    // MAIN - Chạy tất cả các bài
    // ============================================================

    public static void main(String[] args) {

        // === BÀI 1-3: Student Management ===
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   BÀI 1-3: QUẢN LÝ SINH VIÊN               ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        StudentManager manager = new StudentManager();

        // Thêm sinh viên
        manager.add(new Student(1, "Bảo", 22, 9.0));
        manager.add(new Student(2, "An", 21, 7.5));
        manager.add(new Student(3, "Minh", 23, 8.5));
        manager.add(new Student(4, "Hương", 20, 6.0));
        manager.add(new Student(5, "Nam", 22, 9.5));

        System.out.println("\n--- Danh sách sinh viên ---");
        manager.displayAll();

        // Tìm kiếm
        System.out.println("Tìm theo ID 3: " + manager.findById(3));

        System.out.println("Tìm theo tên 'an':");
        for (Student s : manager.findByName("an")) {
            System.out.println("  " + s);
        }

        // Sửa
        manager.update(2, "An Updated", 21, 8.0);

        // Xóa
        manager.delete(4);

        System.out.println("\n--- Sau khi sửa/xóa ---");
        manager.displayAll();

        // === BÀI 4-6: Employee Salary ===
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   BÀI 4-6: QUẢN LÝ LƯƠNG NHÂN VIÊN         ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        List<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee(1, "Nguyễn Văn A", 15000000, 3000000));
        employees.add(new FullTimeEmployee(2, "Trần Thị B", 20000000, 5000000));
        employees.add(new PartTimeEmployee(3, "Lê Văn C", 50000, 120));
        employees.add(new PartTimeEmployee(4, "Phạm Thị D", 60000, 80));

        System.out.println("--- Bảng lương ---");
        double totalSalary = 0;
        for (Employee emp : employees) {
            emp.displayInfo();    // Polymorphism: gọi đúng method tùy loại
            totalSalary += emp.calculateSalary();
        }
        System.out.printf("\nTổng quỹ lương: %,.0f VND%n", totalSalary);

        // === BÀI 7-10: Payment System ===
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   BÀI 7-10: HỆ THỐNG THANH TOÁN             ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // Tạo các phương thức thanh toán
        Payment[] payments = {
            new MomoPayment("0901234567"),
            new BankPayment("Vietcombank", "1234567890"),
            new CashPayment()
        };

        double orderAmount = 1500000;
        System.out.printf("Đơn hàng: %,.0f VND%n%n", orderAmount);

        // BÀI 10: Dùng Polymorphism - không cần if-else
        // Thay vì:
        //   if (method == "momo") { ... }
        //   else if (method == "bank") { ... }
        //   else if (method == "cash") { ... }
        //
        // Chỉ cần:
        for (int i = 0; i < payments.length; i++) {
            System.out.printf("--- Phương thức %d ---\n", i + 1);
            processPayment(payments[i], orderAmount); // Polymorphism!
            System.out.println();
        }

        // Chọn phương thức thanh toán
        System.out.println("--- Chọn phương thức thanh toán ---");
        int choice = 1; // Giả lập user chọn 1 (Momo)
        processPayment(payments[choice - 1], orderAmount);

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("11. Tạo interface Printable với method printDocument()");
        System.out.println("12. Implement: LaserPrinter, InkjetPrinter, PDFExporter");
        System.out.println("13. Tạo abstract class Vehicle → Car, Motorcycle, Bicycle");
        System.out.println("14. Tạo interface Discountable để tính giảm giá cho Product");
        System.out.println("15. Refactor một chương trình dùng nhiều if-else thành polymorphism");
    }
}
