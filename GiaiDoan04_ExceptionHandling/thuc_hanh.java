package GiaiDoan04_ExceptionHandling;

/**
 * ============================================================
 * GIAI ĐOẠN 4: EXCEPTION HANDLING - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    // ====== Custom Exceptions ======

    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    static class InsufficientBalanceException extends Exception {
        private double balance;
        private double amount;

        public InsufficientBalanceException(double balance, double amount) {
            super(String.format("Số dư %.0f không đủ để rút %.0f", balance, amount));
            this.balance = balance;
            this.amount = amount;
        }

        public double getBalance() { return balance; }
        public double getAmount() { return amount; }
    }

    static class InvalidAgeException extends RuntimeException {
        public InvalidAgeException(int age) {
            super("Tuổi không hợp lệ: " + age + ". Phải từ 0-150.");
        }
    }

    // ====== Bank Account Demo ======

    static class BankAccount {
        private String owner;
        private double balance;

        public BankAccount(String owner, double balance) {
            this.owner = owner;
            this.balance = balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Số tiền nạp phải > 0");
            }
            balance += amount;
            System.out.printf("  ✓ Nạp %,.0f → Số dư: %,.0f%n", amount, balance);
        }

        public void withdraw(double amount) throws InsufficientBalanceException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Số tiền rút phải > 0");
            }
            if (amount > balance) {
                throw new InsufficientBalanceException(balance, amount);
            }
            balance -= amount;
            System.out.printf("  ✓ Rút %,.0f → Số dư: %,.0f%n", amount, balance);
        }

        public double getBalance() { return balance; }
        public String getOwner() { return owner; }
    }

    // ====== Main ======

    public static void main(String[] args) {

        // ============================================================
        // BÀI 1: try-catch cơ bản
        // ============================================================
        System.out.println("=== BÀI 1: try-catch cơ bản ===");

        // ArithmeticException
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Lỗi chia cho 0: " + e.getMessage());
        }

        // ArrayIndexOutOfBoundsException
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Lỗi index ngoài phạm vi: " + e.getMessage());
        }

        // NumberFormatException
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi parse số: " + e.getMessage());
        }

        // NullPointerException
        try {
            String s = null;
            s.length();
        } catch (NullPointerException e) {
            System.out.println("Lỗi NullPointer: biến chưa được khởi tạo");
        }
        System.out.println();

        // ============================================================
        // BÀI 2: finally block
        // ============================================================
        System.out.println("=== BÀI 2: finally block ===");

        try {
            System.out.println("  Mở resource...");
            int result = 10 / 2;
            System.out.println("  Kết quả: " + result);
        } catch (Exception e) {
            System.out.println("  Có lỗi: " + e.getMessage());
        } finally {
            System.out.println("  Finally: Đóng resource (LUÔN chạy)");
        }
        System.out.println();

        // ============================================================
        // BÀI 3: Multi-catch (Java 7+)
        // ============================================================
        System.out.println("=== BÀI 3: Multi-catch ===");

        String[] inputs = {"123", "abc", null};
        for (String input : inputs) {
            try {
                int value = Integer.parseInt(input);
                System.out.println("  Parsed: " + value);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("  Lỗi với input '" + input + "': " + e.getClass().getSimpleName());
            }
        }
        System.out.println();

        // ============================================================
        // BÀI 4: throw - ném exception thủ công
        // ============================================================
        System.out.println("=== BÀI 4: throw ===");

        try {
            validateAge(25);
            System.out.println("  Tuổi 25: Hợp lệ ✓");
        } catch (InvalidAgeException e) {
            System.out.println("  " + e.getMessage());
        }

        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("  " + e.getMessage() + " ✗");
        }

        try {
            validateAge(200);
        } catch (InvalidAgeException e) {
            System.out.println("  " + e.getMessage() + " ✗");
        }
        System.out.println();

        // ============================================================
        // BÀI 5: Custom Exception với BankAccount
        // ============================================================
        System.out.println("=== BÀI 5: Custom Exception - Bank Account ===");

        BankAccount account = new BankAccount("Bảo", 5000000);
        System.out.println("Tài khoản: " + account.getOwner());
        System.out.printf("Số dư ban đầu: %,.0f VND%n", account.getBalance());

        try {
            account.deposit(2000000);
            account.withdraw(3000000);
            account.withdraw(10000000); // Sẽ ném InsufficientBalanceException
        } catch (InsufficientBalanceException e) {
            System.out.println("  ✗ " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Tham số không hợp lệ: " + e.getMessage());
        }

        // Test số tiền không hợp lệ
        try {
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ " + e.getMessage());
        }
        System.out.println();

        // ============================================================
        // BÀI 6: UserNotFoundException
        // ============================================================
        System.out.println("=== BÀI 6: UserNotFoundException ===");

        String[] userDatabase = {"admin", "user1", "manager"};

        String[] searchUsers = {"admin", "guest", "manager", "superadmin"};
        for (String username : searchUsers) {
            try {
                findUser(userDatabase, username);
                System.out.println("  ✓ Tìm thấy user: " + username);
            } catch (UserNotFoundException e) {
                System.out.println("  ✗ " + e.getMessage());
            }
        }
        System.out.println();

        // ============================================================
        // BÀI 7: Exception chaining
        // ============================================================
        System.out.println("=== BÀI 7: Exception Chaining ===");

        try {
            processOrder("invalid-id");
        } catch (RuntimeException e) {
            System.out.println("  Lỗi: " + e.getMessage());
            System.out.println("  Nguyên nhân gốc: " + e.getCause().getMessage());
        }
        System.out.println();

        // ============================================================
        // BÀI 8: Xử lý nhiều exception lồng nhau
        // ============================================================
        System.out.println("=== BÀI 8: Nested Exception Handling ===");

        String[] data = {"10", "0", "abc", "5"};
        for (int i = 0; i < data.length; i++) {
            try {
                int divisor = Integer.parseInt(data[i]);
                int result = 100 / divisor;
                System.out.printf("  100 / %s = %d%n", data[i], result);
            } catch (NumberFormatException e) {
                System.out.printf("  '%s' không phải số ✗%n", data[i]);
            } catch (ArithmeticException e) {
                System.out.printf("  Không thể chia cho %s ✗%n", data[i]);
            }
        }
        System.out.println();

        // ============================================================
        // BÀI TẬP TỰ LÀM
        // ============================================================
        System.out.println("=== BÀI TẬP TỰ LÀM ===");
        System.out.println("9.  Tạo DuplicateEmailException và dùng trong hệ thống đăng ký");
        System.out.println("10. Tạo InvalidPasswordException với validation (8+ ký tự, có số, có chữ hoa)");
        System.out.println("11. Viết method đọc file và xử lý FileNotFoundException");
        System.out.println("12. Tạo hệ thống đăng nhập với custom exception (WrongPasswordException, AccountLockedException)");
        System.out.println("13. Dùng try-with-resources đọc/ghi file");
    }

    // ====== Helper Methods ======

    static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException(age);
        }
    }

    static void findUser(String[] database, String username) {
        for (String user : database) {
            if (user.equals(username)) return;
        }
        throw new UserNotFoundException("Không tìm thấy user: " + username);
    }

    static void processOrder(String orderId) {
        try {
            int id = Integer.parseInt(orderId);
        } catch (NumberFormatException e) {
            // Wrap exception gốc vào exception mới
            throw new RuntimeException("Không thể xử lý đơn hàng: " + orderId, e);
        }
    }
}
