import java.util.Arrays;
import java.util.Scanner;

/**
 * ============================================================
 * GIAI ĐOẠN 2: ARRAY, STRING, METHOD - THỰC HÀNH
 * ============================================================
 * 
 * Bao gồm 10 bài tập:
 * 1. Tìm max/min trong mảng
 * 2. Tính tổng các phần tử trong mảng
 * 3. Đếm số chẵn, số lẻ
 * 4. Sắp xếp mảng tăng dần
 * 5. Tìm phần tử xuất hiện nhiều nhất
 * 6. Đảo ngược chuỗi
 * 7. Kiểm tra chuỗi palindrome
 * 8. Đếm số từ trong chuỗi
 * 9. Kiểm tra email hợp lệ đơn giản
 * 10. Quản lý danh sách sinh viên bằng mảng
 */

public class thuc_hanh {

    public static void main(String[] args) {
        // ============================================================
        // BÀI 1: Tìm max/min trong mảng
        // ============================================================
        System.out.println("=== BÀI 1: Tìm max/min trong mảng ===");
        int[] numbers = {45, 12, 78, 3, 56, 91, 23, 67};
        System.out.println("Mảng: " + Arrays.toString(numbers));
        
        int max = numbers[0];
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) max = numbers[i];
            if (numbers[i] < min) min = numbers[i];
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println();

        // ============================================================
        // BÀI 2: Tính tổng các phần tử trong mảng
        // ============================================================
        System.out.println("=== BÀI 2: Tổng các phần tử ===");
        int[] arr2 = {10, 20, 30, 40, 50};
        System.out.println("Mảng: " + Arrays.toString(arr2));
        
        int sum = 0;
        for (int num : arr2) {
            sum += num;
        }
        System.out.println("Tổng: " + sum);
        System.out.println("Trung bình: " + (double) sum / arr2.length);
        System.out.println();

        // ============================================================
        // BÀI 3: Đếm số chẵn, số lẻ
        // ============================================================
        System.out.println("=== BÀI 3: Đếm số chẵn, lẻ ===");
        int[] arr3 = {1, 4, 7, 8, 11, 14, 17, 20, 23, 26};
        System.out.println("Mảng: " + Arrays.toString(arr3));
        
        int countEven = 0, countOdd = 0;
        for (int num : arr3) {
            if (num % 2 == 0) countEven++;
            else countOdd++;
        }
        System.out.println("Số chẵn: " + countEven);
        System.out.println("Số lẻ: " + countOdd);
        System.out.println();

        // ============================================================
        // BÀI 4: Sắp xếp mảng tăng dần (Bubble Sort)
        // ============================================================
        System.out.println("=== BÀI 4: Sắp xếp mảng (Bubble Sort) ===");
        int[] arr4 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Trước: " + Arrays.toString(arr4));
        
        // Bubble Sort
        for (int i = 0; i < arr4.length - 1; i++) {
            for (int j = 0; j < arr4.length - 1 - i; j++) {
                if (arr4[j] > arr4[j + 1]) {
                    // Swap
                    int temp = arr4[j];
                    arr4[j] = arr4[j + 1];
                    arr4[j + 1] = temp;
                }
            }
        }
        System.out.println("Sau:   " + Arrays.toString(arr4));
        System.out.println();

        // ============================================================
        // BÀI 5: Tìm phần tử xuất hiện nhiều nhất
        // ============================================================
        System.out.println("=== BÀI 5: Phần tử xuất hiện nhiều nhất ===");
        int[] arr5 = {1, 3, 2, 1, 4, 1, 3, 2, 1, 3, 3, 3};
        System.out.println("Mảng: " + Arrays.toString(arr5));
        
        int maxCount = 0;
        int mostFrequent = arr5[0];
        
        for (int i = 0; i < arr5.length; i++) {
            int count = 0;
            for (int j = 0; j < arr5.length; j++) {
                if (arr5[i] == arr5[j]) count++;
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequent = arr5[i];
            }
        }
        System.out.println("Phần tử xuất hiện nhiều nhất: " + mostFrequent + " (" + maxCount + " lần)");
        System.out.println();

        // ============================================================
        // BÀI 6: Đảo ngược chuỗi
        // ============================================================
        System.out.println("=== BÀI 6: Đảo ngược chuỗi ===");
        String str6 = "Hello Java";
        System.out.println("Chuỗi gốc: " + str6);
        
        // Cách 1: StringBuilder
        String reversed1 = new StringBuilder(str6).reverse().toString();
        System.out.println("Đảo (StringBuilder): " + reversed1);
        
        // Cách 2: Vòng lặp
        char[] chars = str6.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        System.out.println("Đảo (vòng lặp): " + sb.toString());
        
        // Cách 3: Đệ quy
        System.out.println("Đảo (đệ quy): " + reverseString(str6));
        System.out.println();

        // ============================================================
        // BÀI 7: Kiểm tra chuỗi palindrome
        // ============================================================
        System.out.println("=== BÀI 7: Kiểm tra palindrome ===");
        String[] testStrings = {"madam", "racecar", "hello", "level", "java"};
        
        for (String s : testStrings) {
            boolean isPalindrome = isPalindrome(s);
            System.out.printf("  \"%s\" → %s%n", s, isPalindrome ? "Palindrome ✓" : "Không phải ✗");
        }
        System.out.println();

        // ============================================================
        // BÀI 8: Đếm số từ trong chuỗi
        // ============================================================
        System.out.println("=== BÀI 8: Đếm số từ ===");
        String str8 = "  Hello   World   Java   Programming  ";
        System.out.println("Chuỗi: \"" + str8 + "\"");
        
        // Cách 1: split
        String[] words = str8.trim().split("\\s+");
        System.out.println("Số từ: " + words.length);
        System.out.print("Các từ: ");
        for (String word : words) {
            System.out.print("[" + word + "] ");
        }
        System.out.println("\n");

        // ============================================================
        // BÀI 9: Kiểm tra email hợp lệ đơn giản
        // ============================================================
        System.out.println("=== BÀI 9: Kiểm tra email ===");
        String[] emails = {"test@gmail.com", "invalid@", "user@domain.co", "@gmail.com", "hello.world@email.org", "no space@email.com"};
        
        for (String email : emails) {
            boolean valid = isValidEmail(email);
            System.out.printf("  %-25s → %s%n", email, valid ? "Hợp lệ ✓" : "Không hợp lệ ✗");
        }
        System.out.println();

        // ============================================================
        // BÀI 10: Quản lý sinh viên bằng mảng
        // ============================================================
        System.out.println("=== BÀI 10: Quản lý sinh viên ===");
        
        // Khai báo mảng lưu thông tin sinh viên
        String[] studentNames = new String[5];
        double[] studentScores = new double[5];
        int studentCount = 0;
        
        // Thêm sinh viên
        studentCount = addStudent(studentNames, studentScores, studentCount, "Bảo", 9.0);
        studentCount = addStudent(studentNames, studentScores, studentCount, "An", 7.5);
        studentCount = addStudent(studentNames, studentScores, studentCount, "Minh", 8.5);
        studentCount = addStudent(studentNames, studentScores, studentCount, "Hương", 6.0);
        studentCount = addStudent(studentNames, studentScores, studentCount, "Nam", 9.5);
        
        // Hiển thị danh sách
        System.out.println("--- Danh sách sinh viên ---");
        displayStudents(studentNames, studentScores, studentCount);
        
        // Tìm sinh viên điểm cao nhất
        int topIndex = findTopStudent(studentScores, studentCount);
        System.out.println("\nSinh viên điểm cao nhất: " + studentNames[topIndex] + " (" + studentScores[topIndex] + ")");
        
        // Tính điểm trung bình
        double avg = calculateAverage(studentScores, studentCount);
        System.out.printf("Điểm trung bình: %.2f%n", avg);
        
        System.out.println();

        // ============================================================
        // BÀI TẬP THÊM
        // ============================================================
        System.out.println("=== BÀI TẬP TỰ LÀM ===");
        System.out.println("11. Xóa phần tử trùng lặp trong mảng");
        System.out.println("12. Gộp 2 mảng đã sắp xếp thành 1 mảng sắp xếp");
        System.out.println("13. Đếm số lần xuất hiện của mỗi ký tự trong chuỗi");
        System.out.println("14. Chuyển đổi camelCase ↔ snake_case");
        System.out.println("15. Viết method tách họ và tên từ chuỗi họ tên đầy đủ");
    }

    // ============================================================
    // CÁC METHOD HỖ TRỢ
    // ============================================================

    /** Đảo ngược chuỗi bằng đệ quy */
    public static String reverseString(String str) {
        if (str.isEmpty()) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    /** Kiểm tra chuỗi palindrome */
    public static boolean isPalindrome(String str) {
        str = str.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** Kiểm tra email hợp lệ đơn giản */
    public static boolean isValidEmail(String email) {
        // Phải chứa đúng 1 ký tự @
        int atIndex = email.indexOf("@");
        if (atIndex <= 0 || atIndex != email.lastIndexOf("@")) return false;
        
        // Phải có dấu . sau @
        String domain = email.substring(atIndex + 1);
        if (!domain.contains(".")) return false;
        
        // Không có khoảng trắng
        if (email.contains(" ")) return false;
        
        // Phần sau dấu . cuối phải >= 2 ký tự
        int lastDot = domain.lastIndexOf(".");
        if (lastDot == domain.length() - 1 || domain.substring(lastDot + 1).length() < 2) return false;
        
        return true;
    }

    /** Thêm sinh viên vào mảng */
    public static int addStudent(String[] names, double[] scores, int count, String name, double score) {
        if (count >= names.length) {
            System.out.println("Mảng đã đầy!");
            return count;
        }
        names[count] = name;
        scores[count] = score;
        return count + 1;
    }

    /** Hiển thị danh sách sinh viên */
    public static void displayStudents(String[] names, double[] scores, int count) {
        System.out.printf("%-5s %-15s %-10s %-10s%n", "STT", "Tên", "Điểm", "Xếp loại");
        System.out.println("-".repeat(40));
        for (int i = 0; i < count; i++) {
            String rank = getGrade(scores[i]);
            System.out.printf("%-5d %-15s %-10.1f %-10s%n", i + 1, names[i], scores[i], rank);
        }
    }

    /** Xếp loại học lực */
    public static String getGrade(double score) {
        if (score >= 9) return "Xuất sắc";
        if (score >= 8) return "Giỏi";
        if (score >= 6.5) return "Khá";
        if (score >= 5) return "TB";
        return "Yếu";
    }

    /** Tìm sinh viên điểm cao nhất */
    public static int findTopStudent(double[] scores, int count) {
        int topIndex = 0;
        for (int i = 1; i < count; i++) {
            if (scores[i] > scores[topIndex]) {
                topIndex = i;
            }
        }
        return topIndex;
    }

    /** Tính điểm trung bình */
    public static double calculateAverage(double[] scores, int count) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += scores[i];
        }
        return total / count;
    }
}
