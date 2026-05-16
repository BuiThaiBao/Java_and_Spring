package GiaiDoan15_Maven_UnitTest_CleanCode;

/**
 * ============================================================
 * GIAI ĐOẠN 15: MAVEN, UNIT TEST, CLEAN CODE - THỰC HÀNH
 * ============================================================
 *
 * Lưu ý: Để chạy JUnit test, cần Maven project với dependency JUnit.
 * File này demo cấu trúc code và test patterns.
 * Để chạy thực tế, tạo Maven project và thêm JUnit dependency.
 */

public class thuc_hanh {

    // ============================================================
    // PHẦN 1: CODE CẦN TEST (Production Code)
    // ============================================================

    /** Calculator - class cần test */
    static class Calculator {
        public int sum(int a, int b) { return a + b; }
        public int subtract(int a, int b) { return a - b; }
        public int multiply(int a, int b) { return a * b; }

        public int divide(int a, int b) {
            if (b == 0) throw new ArithmeticException("Cannot divide by zero");
            return a / b;
        }

        public boolean isEven(int n) { return n % 2 == 0; }
        public boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    /** StudentService - class với business logic */
    static class StudentService {
        public String getGrade(double score) {
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("Score must be 0-10");
            }
            if (score >= 9) return "Xuất sắc";
            if (score >= 8) return "Giỏi";
            if (score >= 6.5) return "Khá";
            if (score >= 5) return "Trung bình";
            return "Yếu";
        }

        public boolean isValidEmail(String email) {
            if (email == null || email.isEmpty()) return false;
            return email.contains("@") && email.contains(".")
                    && email.indexOf("@") > 0
                    && email.indexOf("@") < email.lastIndexOf(".");
        }

        public String formatName(String firstName, String lastName) {
            if (firstName == null || lastName == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
            return lastName.trim() + " " + firstName.trim();
        }
    }

    // ============================================================
    // PHẦN 2: UNIT TEST PATTERNS (Mô phỏng JUnit)
    // ============================================================

    /** Simple test runner - mô phỏng JUnit */
    static int passed = 0, failed = 0;

    static void assertEquals(Object expected, Object actual, String testName) {
        if (expected.equals(actual)) {
            System.out.println("  ✓ " + testName);
            passed++;
        } else {
            System.out.println("  ✗ " + testName + " | Expected: " + expected + ", Got: " + actual);
            failed++;
        }
    }

    static void assertTrue(boolean condition, String testName) {
        assertEquals(true, condition, testName);
    }

    static void assertFalse(boolean condition, String testName) {
        assertEquals(false, condition, testName);
    }

    static void assertThrows(Class<? extends Exception> exClass, Runnable action, String testName) {
        try {
            action.run();
            System.out.println("  ✗ " + testName + " | Expected exception but none thrown");
            failed++;
        } catch (Exception e) {
            if (exClass.isInstance(e)) {
                System.out.println("  ✓ " + testName);
                passed++;
            } else {
                System.out.println("  ✗ " + testName + " | Wrong exception: " + e.getClass().getSimpleName());
                failed++;
            }
        }
    }

    // ============================================================
    // MAIN - Chạy tất cả test
    // ============================================================

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        StudentService service = new StudentService();

        // TEST: Calculator
        System.out.println("=== TEST: Calculator ===");
        assertEquals(5, calc.sum(2, 3), "sum(2,3) should be 5");
        assertEquals(0, calc.sum(-1, 1), "sum(-1,1) should be 0");
        assertEquals(7, calc.subtract(10, 3), "subtract(10,3) should be 7");
        assertEquals(20, calc.multiply(4, 5), "multiply(4,5) should be 20");
        assertEquals(3, calc.divide(10, 3), "divide(10,3) should be 3");
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0),
                "divide by zero should throw ArithmeticException");
        assertTrue(calc.isEven(4), "4 should be even");
        assertFalse(calc.isEven(3), "3 should not be even");
        assertTrue(calc.isPrime(7), "7 should be prime");
        assertFalse(calc.isPrime(4), "4 should not be prime");
        assertFalse(calc.isPrime(1), "1 should not be prime");

        // TEST: StudentService - getGrade
        System.out.println("\n=== TEST: StudentService.getGrade ===");
        assertEquals("Xuất sắc", service.getGrade(9.5), "9.5 → Xuất sắc");
        assertEquals("Giỏi", service.getGrade(8.0), "8.0 → Giỏi");
        assertEquals("Khá", service.getGrade(7.0), "7.0 → Khá");
        assertEquals("Trung bình", service.getGrade(5.5), "5.5 → Trung bình");
        assertEquals("Yếu", service.getGrade(3.0), "3.0 → Yếu");
        assertThrows(IllegalArgumentException.class, () -> service.getGrade(-1),
                "Negative score should throw exception");
        assertThrows(IllegalArgumentException.class, () -> service.getGrade(11),
                "Score > 10 should throw exception");

        // TEST: StudentService - isValidEmail
        System.out.println("\n=== TEST: isValidEmail ===");
        assertTrue(service.isValidEmail("test@gmail.com"), "test@gmail.com → valid");
        assertTrue(service.isValidEmail("user@domain.co"), "user@domain.co → valid");
        assertFalse(service.isValidEmail("invalid"), "invalid → invalid");
        assertFalse(service.isValidEmail("@gmail.com"), "@gmail.com → invalid");
        assertFalse(service.isValidEmail(""), "empty → invalid");
        assertFalse(service.isValidEmail(null), "null → invalid");

        // TEST: formatName
        System.out.println("\n=== TEST: formatName ===");
        assertEquals("Nguyen Bao", service.formatName("Bao", "Nguyen"), "formatName basic");
        assertEquals("Tran An", service.formatName(" An ", " Tran "), "formatName with spaces");
        assertThrows(IllegalArgumentException.class, () -> service.formatName(null, "Test"),
                "null firstName should throw");

        // CLEAN CODE EXAMPLES
        System.out.println("\n=== CLEAN CODE EXAMPLES ===");

        // ✗ Bad naming
        int a = 10;
        // ✓ Good naming
        int userAge = 10;
        System.out.println("  Good: userAge = " + userAge + " (thay vì a = " + a + ")");

        // ✗ Magic numbers
        // if (score > 8) ...
        // ✓ Named constants
        final int GOOD_SCORE_THRESHOLD = 8;
        System.out.println("  Good: dùng GOOD_SCORE_THRESHOLD = " + GOOD_SCORE_THRESHOLD);

        // Summary
        System.out.println("\n" + "=".repeat(40));
        System.out.printf("TỔNG KẾT: %d passed, %d failed, %d total%n",
                passed, failed, passed + failed);
        System.out.println("=".repeat(40));

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("1. Tạo Maven project thực tế với JUnit 5");
        System.out.println("2. Viết test cho class BankAccount (deposit, withdraw)");
        System.out.println("3. Viết test coverage >= 80% cho StudentService");
        System.out.println("4. Refactor code cũ theo Clean Code principles");
        System.out.println("5. Thêm Mockito để mock dependencies");
    }
}
