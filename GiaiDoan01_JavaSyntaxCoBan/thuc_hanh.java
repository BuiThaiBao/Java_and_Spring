import java.util.Scanner;

/**
 * ============================================================
 * GIAI ĐOẠN 1: JAVA SYNTAX CƠ BẢN - THỰC HÀNH
 * ============================================================
 * 
 * Bao gồm 10 bài tập từ cơ bản đến nâng cao:
 * 1. Kiểm tra chẵn lẻ
 * 2. Xếp loại học lực
 * 3. In bảng cửu chương
 * 4. Tính tổng từ 1 đến n
 * 5. Kiểm tra số nguyên tố
 * 6. Tìm số lớn nhất trong 3 số
 * 7. Tính giai thừa
 * 8. Đảo ngược một số nguyên
 * 9. Kiểm tra số đối xứng (palindrome)
 * 10. In tam giác sao
 */

public class thuc_hanh {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ============================================================
        // BÀI 1: Kiểm tra số chẵn lẻ
        // ============================================================
        System.out.println("=== BÀI 1: Kiểm tra chẵn lẻ ===");
        System.out.print("Nhập số nguyên: ");
        int num1 = scanner.nextInt();
        if (num1 % 2 == 0) {
            System.out.println(num1 + " là số CHẴN");
        } else {
            System.out.println(num1 + " là số LẺ");
        }
        System.out.println();

        // ============================================================
        // BÀI 2: Xếp loại học lực
        // ============================================================
        System.out.println("=== BÀI 2: Xếp loại học lực ===");
        System.out.print("Nhập điểm (0-10): ");
        double diem = scanner.nextDouble();
        
        if (diem >= 9) {
            System.out.println("Xếp loại: Xuất sắc");
        } else if (diem >= 8) {
            System.out.println("Xếp loại: Giỏi");
        } else if (diem >= 6.5) {
            System.out.println("Xếp loại: Khá");
        } else if (diem >= 5) {
            System.out.println("Xếp loại: Trung bình");
        } else {
            System.out.println("Xếp loại: Yếu");
        }
        System.out.println();

        // ============================================================
        // BÀI 3: In bảng cửu chương
        // ============================================================
        System.out.println("=== BÀI 3: Bảng cửu chương ===");
        System.out.print("Nhập số (2-9): ");
        int n3 = scanner.nextInt();
        
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d%n", n3, i, n3 * i);
        }
        System.out.println();

        // ============================================================
        // BÀI 4: Tính tổng từ 1 đến n
        // ============================================================
        System.out.println("=== BÀI 4: Tổng từ 1 đến n ===");
        System.out.print("Nhập n: ");
        int n4 = scanner.nextInt();
        
        // Cách 1: Dùng vòng lặp
        int sum = 0;
        for (int i = 1; i <= n4; i++) {
            sum += i;
        }
        System.out.println("Tổng (vòng lặp): " + sum);
        
        // Cách 2: Dùng công thức Gauss
        int sumFormula = n4 * (n4 + 1) / 2;
        System.out.println("Tổng (công thức): " + sumFormula);
        System.out.println();

        // ============================================================
        // BÀI 5: Kiểm tra số nguyên tố
        // ============================================================
        System.out.println("=== BÀI 5: Kiểm tra số nguyên tố ===");
        System.out.print("Nhập số: ");
        int n5 = scanner.nextInt();
        
        boolean isPrime = kiemTraNguyenTo(n5);
        if (isPrime) {
            System.out.println(n5 + " LÀ số nguyên tố");
        } else {
            System.out.println(n5 + " KHÔNG phải số nguyên tố");
        }
        System.out.println();

        // ============================================================
        // BÀI 6: Tìm số lớn nhất trong 3 số
        // ============================================================
        System.out.println("=== BÀI 6: Số lớn nhất trong 3 số ===");
        System.out.print("Nhập số thứ 1: ");
        int a = scanner.nextInt();
        System.out.print("Nhập số thứ 2: ");
        int b = scanner.nextInt();
        System.out.print("Nhập số thứ 3: ");
        int c = scanner.nextInt();
        
        // Cách 1: Dùng if-else
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        System.out.println("Số lớn nhất (if-else): " + max);
        
        // Cách 2: Dùng Math.max
        int max2 = Math.max(a, Math.max(b, c));
        System.out.println("Số lớn nhất (Math.max): " + max2);

        // Cách 3: Dùng ternary operator
        int max3 = (a > b) ? (a > c ? a : c) : (b > c ? b : c);
        System.out.println("Số lớn nhất (ternary): " + max3);
        System.out.println();

        // ============================================================
        // BÀI 7: Tính giai thừa
        // ============================================================
        System.out.println("=== BÀI 7: Tính giai thừa n! ===");
        System.out.print("Nhập n: ");
        int n7 = scanner.nextInt();
        
        // Cách 1: Vòng lặp
        long factorial = 1;
        for (int i = 1; i <= n7; i++) {
            factorial *= i;
        }
        System.out.println(n7 + "! (vòng lặp) = " + factorial);
        
        // Cách 2: Đệ quy
        System.out.println(n7 + "! (đệ quy) = " + giaiThua(n7));
        System.out.println();

        // ============================================================
        // BÀI 8: Đảo ngược một số nguyên
        // ============================================================
        System.out.println("=== BÀI 8: Đảo ngược số nguyên ===");
        System.out.print("Nhập số: ");
        int n8 = scanner.nextInt();
        
        int original = n8;
        int reversed = 0;
        while (n8 != 0) {
            int digit = n8 % 10;       // Lấy chữ số cuối
            reversed = reversed * 10 + digit; // Thêm vào số đảo
            n8 /= 10;                  // Bỏ chữ số cuối
        }
        System.out.println("Số gốc: " + original);
        System.out.println("Số đảo ngược: " + reversed);
        System.out.println();

        // ============================================================
        // BÀI 9: Kiểm tra số đối xứng (palindrome)
        // ============================================================
        System.out.println("=== BÀI 9: Kiểm tra số đối xứng ===");
        System.out.print("Nhập số: ");
        int n9 = scanner.nextInt();
        
        int original9 = n9;
        int reversed9 = 0;
        int temp = n9;
        while (temp != 0) {
            reversed9 = reversed9 * 10 + temp % 10;
            temp /= 10;
        }
        
        if (original9 == reversed9) {
            System.out.println(original9 + " LÀ số đối xứng (palindrome)");
        } else {
            System.out.println(original9 + " KHÔNG phải số đối xứng");
        }
        System.out.println();

        // ============================================================
        // BÀI 10: In tam giác sao
        // ============================================================
        System.out.println("=== BÀI 10: In tam giác sao ===");
        System.out.print("Nhập số hàng: ");
        int n10 = scanner.nextInt();
        
        // Tam giác vuông
        System.out.println("--- Tam giác vuông ---");
        for (int i = 1; i <= n10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        System.out.println();
        
        // Tam giác cân
        System.out.println("--- Tam giác cân ---");
        for (int i = 1; i <= n10; i++) {
            // In khoảng trắng
            for (int j = n10 - i; j > 0; j--) {
                System.out.print(" ");
            }
            // In sao
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println();

        // ============================================================
        // BÀI TẬP THÊM (Tự làm)
        // ============================================================
        System.out.println("=== BÀI TẬP THÊM ===");
        System.out.println("11. In dãy Fibonacci n số đầu tiên");
        System.out.println("12. Tìm ƯCLN của 2 số (Euclid)");
        System.out.println("13. Đếm số chữ số của một số nguyên");
        System.out.println("14. Kiểm tra năm nhuận");
        System.out.println("15. Chuyển số thập phân sang nhị phân");

        scanner.close();
    }

    // ============================================================
    // CÁC METHOD HỖ TRỢ
    // ============================================================

    /**
     * Kiểm tra số nguyên tố
     * Số nguyên tố: > 1 và chỉ chia hết cho 1 và chính nó
     */
    public static boolean kiemTraNguyenTo(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        // Chỉ cần kiểm tra đến sqrt(n)
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Tính giai thừa bằng đệ quy
     */
    public static long giaiThua(int n) {
        if (n <= 1) return 1;
        return n * giaiThua(n - 1);
    }
}
