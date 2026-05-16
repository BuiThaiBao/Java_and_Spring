import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * ============================================================
 * GIAI ĐOẠN 10: DATE AND TIME API - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    public static void main(String[] args) {

        // BÀI 1: Tính tuổi từ ngày sinh
        System.out.println("=== BÀI 1: Tính tuổi ===");
        LocalDate birthday = LocalDate.of(2004, 5, 20);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthday, today);
        System.out.printf("  Ngày sinh: %s%n  Tuổi: %d năm %d tháng %d ngày%n",
                birthday, age.getYears(), age.getMonths(), age.getDays());

        // BÀI 2: Số ngày giữa 2 ngày
        System.out.println("\n=== BÀI 2: Số ngày giữa 2 ngày ===");
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 12, 31);
        long days = ChronoUnit.DAYS.between(start, end);
        System.out.printf("  Từ %s đến %s: %d ngày%n", start, end, days);

        // BÀI 3: Format ngày
        System.out.println("\n=== BÀI 3: Format ngày ===");
        DateTimeFormatter vnFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        System.out.println("  VN format: " + today.format(vnFormat));
        System.out.println("  Full format: " + today.format(fullFormat));

        // Parse ngày từ String
        LocalDate parsed = LocalDate.parse("25/12/2026", vnFormat);
        System.out.println("  Parsed: " + parsed);

        // BÀI 4: Kiểm tra đơn hàng quá hạn
        System.out.println("\n=== BÀI 4: Kiểm tra quá hạn ===");
        LocalDateTime orderDate = LocalDateTime.of(2026, 5, 10, 14, 30);
        LocalDateTime deadline = orderDate.plusDays(7);
        LocalDateTime now = LocalDateTime.now();

        System.out.println("  Ngày đặt: " + orderDate);
        System.out.println("  Deadline: " + deadline);
        System.out.println("  Bây giờ: " + now);
        System.out.println("  Quá hạn? " + (now.isAfter(deadline) ? "CÓ ✗" : "CHƯA ✓"));

        // BÀI 5: Thời gian còn lại đến deadline
        System.out.println("\n=== BÀI 5: Countdown ===");
        LocalDate examDate = LocalDate.of(2026, 7, 1);
        long daysLeft = ChronoUnit.DAYS.between(today, examDate);
        System.out.printf("  Ngày thi: %s%n  Còn %d ngày%n", examDate, daysLeft);

        // BONUS: LocalTime, Duration
        System.out.println("\n=== BONUS: LocalTime & Duration ===");
        LocalTime startWork = LocalTime.of(8, 30);
        LocalTime endWork = LocalTime.of(17, 30);
        Duration workDuration = Duration.between(startWork, endWork);
        System.out.printf("  Giờ làm: %s → %s (%d giờ)%n",
                startWork, endWork, workDuration.toHours());

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("6. Tạo lịch hẹn với nhắc nhở trước 1 giờ");
        System.out.println("7. Tính số ngày làm việc (trừ thứ 7, CN) giữa 2 ngày");
        System.out.println("8. Chuyển đổi timezone (VN → US)");
    }
}
