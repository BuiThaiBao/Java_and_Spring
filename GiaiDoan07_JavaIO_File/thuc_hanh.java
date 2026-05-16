package GiaiDoan07_JavaIO_File;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * ============================================================
 * GIAI ĐOẠN 7: JAVA IO VÀ FILE - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    public static void main(String[] args) throws Exception {
        String dir = "io_practice";
        Files.createDirectories(Path.of(dir));

        // BÀI 1: Ghi file text
        System.out.println("=== BÀI 1: Ghi file ===");
        Path filePath = Path.of(dir, "data.txt");
        Files.writeString(filePath, "Dòng 1: Hello Java\nDòng 2: IO Practice\nDòng 3: NIO.2 API");
        System.out.println("  ✓ Đã ghi file: " + filePath);

        // BÀI 2: Đọc toàn bộ file
        System.out.println("\n=== BÀI 2: Đọc file ===");
        String content = Files.readString(filePath);
        System.out.println("  Nội dung:\n  " + content.replace("\n", "\n  "));

        // BÀI 3: Đọc từng dòng
        System.out.println("\n=== BÀI 3: Đọc từng dòng ===");
        List<String> lines = Files.readAllLines(filePath);
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("  Dòng %d: %s%n", i + 1, lines.get(i));
        }

        // BÀI 4: Ghi danh sách sinh viên ra file
        System.out.println("\n=== BÀI 4: Ghi danh sách ra file ===");
        Path studentFile = Path.of(dir, "students.txt");
        List<String> studentData = Arrays.asList(
            "1,Bảo,9.0",
            "2,An,7.5",
            "3,Minh,8.5",
            "4,Hương,6.0",
            "5,Nam,9.5"
        );
        Files.write(studentFile, studentData);
        System.out.println("  ✓ Đã ghi " + studentData.size() + " sinh viên vào " + studentFile);

        // BÀI 5: Đọc và parse CSV
        System.out.println("\n=== BÀI 5: Đọc và parse CSV ===");
        List<String> readLines = Files.readAllLines(studentFile);
        System.out.printf("  %-5s %-10s %-8s%n", "ID", "Tên", "Điểm");
        System.out.println("  " + "-".repeat(25));
        for (String line : readLines) {
            String[] parts = line.split(",");
            System.out.printf("  %-5s %-10s %-8s%n", parts[0], parts[1], parts[2]);
        }

        // BÀI 6: Append thêm dữ liệu
        System.out.println("\n=== BÀI 6: Append dữ liệu ===");
        Files.writeString(studentFile, "\n6,Lan,8.0", StandardOpenOption.APPEND);
        System.out.println("  ✓ Đã append thêm sinh viên");
        System.out.println("  Tổng dòng: " + Files.readAllLines(studentFile).size());

        // BÀI 7: BufferedReader/Writer truyền thống
        System.out.println("\n=== BÀI 7: BufferedWriter/Reader ===");
        Path logFile = Path.of(dir, "log.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile.toString()))) {
            writer.write("[INFO] Application started");
            writer.newLine();
            writer.write("[DEBUG] Loading config...");
            writer.newLine();
            writer.write("[INFO] Ready to serve");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(logFile.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        }

        // BÀI 8: Kiểm tra file/directory
        System.out.println("\n=== BÀI 8: File operations ===");
        System.out.println("  Exists: " + Files.exists(filePath));
        System.out.println("  Is file: " + Files.isRegularFile(filePath));
        System.out.println("  Is directory: " + Files.isDirectory(Path.of(dir)));
        System.out.println("  Size: " + Files.size(filePath) + " bytes");

        // Liệt kê files trong directory
        System.out.println("  Files trong " + dir + ":");
        try (var stream = Files.list(Path.of(dir))) {
            stream.forEach(p -> System.out.println("    " + p.getFileName()));
        }

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("9.  Tạo Todo App lưu dữ liệu vào file");
        System.out.println("10. Copy nội dung từ file này sang file khác");
        System.out.println("11. Đếm số từ, số dòng trong file");
        System.out.println("12. Tìm và thay thế text trong file");
    }
}
