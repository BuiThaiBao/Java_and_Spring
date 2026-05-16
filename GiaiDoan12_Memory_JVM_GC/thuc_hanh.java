/**
 * ============================================================
 * GIAI ĐOẠN 12: MEMORY, JVM, GARBAGE COLLECTION - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    static int staticVar = 100; // Method Area (Metaspace)

    public static void main(String[] args) {

        // BÀI 1: Stack vs Heap
        System.out.println("=== BÀI 1: Stack vs Heap ===");
        int x = 10;          // Stack: biến primitive
        int y = x;            // Stack: copy giá trị
        y = 20;               // x KHÔNG thay đổi
        System.out.println("  x = " + x + " (không đổi khi y thay đổi)");
        System.out.println("  y = " + y);

        int[] arr = {1, 2, 3}; // Stack: reference, Heap: object array
        int[] arr2 = arr;      // Stack: copy reference (cùng trỏ đến 1 object)
        arr2[0] = 99;          // arr[0] CŨNG thay đổi!
        System.out.println("  arr[0] = " + arr[0] + " (thay đổi vì cùng reference)");
        System.out.println();

        // BÀI 2: String Pool
        System.out.println("=== BÀI 2: String Pool ===");
        String s1 = "Hello";                   // String Pool
        String s2 = "Hello";                   // Tái sử dụng từ Pool
        String s3 = new String("Hello");       // Object mới trên Heap
        String s4 = new String("Hello").intern(); // Đưa vào Pool

        System.out.println("  s1 == s2: " + (s1 == s2));       // true
        System.out.println("  s1 == s3: " + (s1 == s3));       // false
        System.out.println("  s1 == s4: " + (s1 == s4));       // true
        System.out.println("  s1.equals(s3): " + s1.equals(s3)); // true
        System.out.println();

        // BÀI 3: Reference types
        System.out.println("=== BÀI 3: Reference ===");
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = sb1;  // Cùng reference
        sb2.append(" World");
        System.out.println("  sb1: " + sb1); // "Hello World" - bị ảnh hưởng
        System.out.println("  sb1 == sb2: " + (sb1 == sb2)); // true
        System.out.println();

        // BÀI 4: Garbage Collection demo
        System.out.println("=== BÀI 4: GC Demo ===");
        Object obj = new Object(); // Object reachable
        System.out.println("  Object created: " + obj);
        obj = null;                // Object trở thành unreachable → GC eligible
        System.out.println("  Object set to null → eligible for GC");
        System.gc();               // Gợi ý GC (không đảm bảo)
        System.out.println("  System.gc() called (hint only)");
        System.out.println();

        // BÀI 5: NullPointerException
        System.out.println("=== BÀI 5: NullPointerException ===");
        String nullStr = null;
        try {
            int len = nullStr.length(); // NPE!
        } catch (NullPointerException e) {
            System.out.println("  ✗ NPE: gọi method trên biến null");
        }
        // Cách phòng tránh
        if (nullStr != null) {
            System.out.println(nullStr.length());
        } else {
            System.out.println("  ✓ Kiểm tra null trước khi dùng");
        }
        System.out.println();

        // BÀI 6: StackOverflowError
        System.out.println("=== BÀI 6: StackOverflowError ===");
        try {
            infiniteRecursion(0);
        } catch (StackOverflowError e) {
            System.out.println("  ✗ StackOverflow: đệ quy quá sâu");
        }
        System.out.println();

        // BÀI 7: static nằm ở đâu?
        System.out.println("=== BÀI 7: Static memory ===");
        System.out.println("  staticVar = " + staticVar + " (Method Area/Metaspace)");
        System.out.println("  Thuộc về class, không thuộc về instance");
        System.out.println("  Tồn tại suốt vòng đời chương trình");
        System.out.println();

        // BÀI 8: Memory analysis
        System.out.println("=== BÀI 8: Memory Info ===");
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.printf("  Max Memory:   %d MB%n", maxMemory);
        System.out.printf("  Total Memory: %d MB%n", totalMemory);
        System.out.printf("  Used Memory:  %d MB%n", usedMemory);
        System.out.printf("  Free Memory:  %d MB%n", freeMemory);

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("9.  Tạo nhiều object và theo dõi memory usage");
        System.out.println("10. So sánh hiệu suất String concat vs StringBuilder (1 triệu lần)");
        System.out.println("11. Vẽ diagram Stack/Heap cho đoạn code cho trước");
    }

    static void infiniteRecursion(int depth) {
        infiniteRecursion(depth + 1); // Không có điều kiện dừng!
    }
}
