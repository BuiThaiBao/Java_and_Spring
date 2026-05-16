/**
 * ============================================================
 * GIAI ĐOẠN 0: CHUẨN BỊ MÔI TRƯỜNG - THỰC HÀNH
 * ============================================================
 * 
 * Mục tiêu: Hiểu quy trình compile và chạy chương trình Java
 * 
 * Hướng dẫn:
 * 1. Mở terminal/command prompt
 * 2. cd đến thư mục chứa file này
 * 3. Compile: javac thuc_hanh.java
 * 4. Chạy:   java thuc_hanh
 */

public class thuc_hanh {

    public static void main(String[] args) {

        // ============================================================
        // BÀI 1: Hello World - Chương trình Java đầu tiên
        // ============================================================
        System.out.println("=== BÀI 1: Hello World ===");
        System.out.println("Hello Java! Đây là chương trình đầu tiên của tôi.");
        System.out.println();

        // ============================================================
        // BÀI 2: Hiểu cấu trúc chương trình Java
        // ============================================================
        System.out.println("=== BÀI 2: Cấu trúc chương trình ===");
        // Mỗi chương trình Java cần:
        // 1. Một class (public class thuc_hanh)
        // 2. Một method main (public static void main)
        // 3. main method là nơi chương trình bắt đầu chạy
        System.out.println("Class name: thuc_hanh");
        System.out.println("Method: main");
        System.out.println("Output: System.out.println()");
        System.out.println();

        // ============================================================
        // BÀI 3: Kiểm tra phiên bản Java
        // ============================================================
        System.out.println("=== BÀI 3: Thông tin hệ thống Java ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println();

        // ============================================================
        // BÀI 4: Hiểu sự khác nhau giữa print, println, printf
        // ============================================================
        System.out.println("=== BÀI 4: Các cách in output ===");
        
        // print - in ra KHÔNG xuống dòng
        System.out.print("Hello ");
        System.out.print("World ");
        System.out.println(); // xuống dòng
        
        // println - in ra VÀ xuống dòng
        System.out.println("Dòng 1");
        System.out.println("Dòng 2");
        
        // printf - in ra theo format
        String name = "Bao";
        int age = 22;
        double score = 9.5;
        System.out.printf("Tên: %s, Tuổi: %d, Điểm: %.1f%n", name, age, score);
        System.out.println();

        // ============================================================
        // BÀI 5: Comment trong Java
        // ============================================================
        System.out.println("=== BÀI 5: Comment ===");
        
        // Đây là comment 1 dòng (single-line comment)
        
        /*
         * Đây là comment nhiều dòng
         * (multi-line comment)
         */
        
        /**
         * Đây là Javadoc comment
         * Dùng để tạo documentation cho class/method
         * @param args tham số dòng lệnh
         */
        
        System.out.println("Comment giúp giải thích code, không ảnh hưởng đến chương trình chạy.");
        System.out.println();

        // ============================================================
        // BÀI TẬP TỰ LÀM
        // ============================================================
        System.out.println("=== BÀI TẬP TỰ LÀM ===");
        System.out.println("1. Thay đổi nội dung Hello World và compile lại");
        System.out.println("2. Tạo file mới MyFirstApp.java, viết class và method main");
        System.out.println("3. Dùng printf in ra thông tin cá nhân (tên, tuổi, trường)");
        System.out.println("4. Thử compile file mà không có method main, xem lỗi gì xảy ra");
        System.out.println("5. Dùng javap -c thuc_hanh để xem bytecode");
    }
}
