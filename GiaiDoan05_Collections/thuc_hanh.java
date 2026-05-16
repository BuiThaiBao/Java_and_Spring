import java.util.*;

/**
 * ============================================================
 * GIAI ĐOẠN 5: COLLECTIONS FRAMEWORK - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    static class Student implements Comparable<Student> {
        private int id;
        private String name;
        private double score;

        public Student(int id, String name, double score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getScore() { return score; }
        public void setName(String name) { this.name = name; }
        public void setScore(double score) { this.score = score; }

        @Override
        public int compareTo(Student other) {
            return Double.compare(this.score, other.score);
        }

        @Override
        public String toString() {
            return String.format("ID:%d %-8s %.1f", id, name, score);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {

        // ============================================================
        // BÀI 1: Quản lý sinh viên bằng ArrayList
        // ============================================================
        System.out.println("=== BÀI 1: ArrayList - Quản lý sinh viên ===");

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Bảo", 9.0));
        students.add(new Student(2, "An", 7.5));
        students.add(new Student(3, "Minh", 8.5));
        students.add(new Student(4, "Hương", 6.0));
        students.add(new Student(5, "Nam", 9.5));

        System.out.println("Danh sách: " + students);
        System.out.println("Số lượng: " + students.size());
        System.out.println();

        // ============================================================
        // BÀI 2: Tìm sinh viên theo ID
        // ============================================================
        System.out.println("=== BÀI 2: Tìm sinh viên theo ID ===");

        int searchId = 3;
        Student found = null;
        for (Student s : students) {
            if (s.getId() == searchId) {
                found = s;
                break;
            }
        }
        System.out.println("Tìm ID " + searchId + ": " + (found != null ? found : "Không tìm thấy"));
        System.out.println();

        // ============================================================
        // BÀI 3: Xóa sinh viên theo ID
        // ============================================================
        System.out.println("=== BÀI 3: Xóa sinh viên theo ID ===");

        int deleteId = 4;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == deleteId) {
                it.remove();
                System.out.println("✓ Đã xóa sinh viên ID " + deleteId);
            }
        }
        System.out.println("Sau xóa: " + students);
        System.out.println();

        // ============================================================
        // BÀI 4: Sắp xếp theo điểm giảm dần
        // ============================================================
        System.out.println("=== BÀI 4: Sắp xếp theo điểm giảm dần ===");

        students.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
        System.out.println("Theo điểm giảm dần:");
        for (Student s : students) {
            System.out.println("  " + s);
        }
        System.out.println();

        // ============================================================
        // BÀI 5: Sắp xếp theo tên A-Z
        // ============================================================
        System.out.println("=== BÀI 5: Sắp xếp theo tên ===");

        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Theo tên A-Z:");
        for (Student s : students) {
            System.out.println("  " + s);
        }
        System.out.println();

        // ============================================================
        // BÀI 6: HashMap - username/password
        // ============================================================
        System.out.println("=== BÀI 6: HashMap - Đăng nhập ===");

        Map<String, String> userPasswords = new HashMap<>();
        userPasswords.put("admin", "admin123");
        userPasswords.put("user1", "pass456");
        userPasswords.put("manager", "mgr789");

        // Giả lập đăng nhập
        String loginUser = "admin";
        String loginPass = "admin123";

        if (userPasswords.containsKey(loginUser)) {
            if (userPasswords.get(loginUser).equals(loginPass)) {
                System.out.println("  ✓ Đăng nhập thành công: " + loginUser);
            } else {
                System.out.println("  ✗ Sai mật khẩu!");
            }
        } else {
            System.out.println("  ✗ Username không tồn tại!");
        }

        // Hiển thị tất cả users
        System.out.println("  Danh sách users:");
        for (Map.Entry<String, String> entry : userPasswords.entrySet()) {
            System.out.println("    " + entry.getKey() + " → " + entry.getValue());
        }
        System.out.println();

        // ============================================================
        // BÀI 7: Đếm tần suất xuất hiện từng từ
        // ============================================================
        System.out.println("=== BÀI 7: Đếm tần suất từ ===");

        String text = "java is great java is fun python is also great";
        String[] words = text.split(" ");

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("  Text: \"" + text + "\"");
        System.out.println("  Tần suất:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.printf("    %-10s → %d lần%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        // ============================================================
        // BÀI 8: Set - Loại bỏ email trùng
        // ============================================================
        System.out.println("=== BÀI 8: Set - Loại bỏ email trùng ===");

        List<String> emailList = Arrays.asList(
            "a@gmail.com", "b@yahoo.com", "a@gmail.com",
            "c@hotmail.com", "b@yahoo.com", "d@gmail.com"
        );
        System.out.println("  Trước (có trùng): " + emailList);

        Set<String> uniqueEmails = new LinkedHashSet<>(emailList);
        System.out.println("  Sau (unique): " + uniqueEmails);
        System.out.println("  Đã loại bỏ " + (emailList.size() - uniqueEmails.size()) + " email trùng");
        System.out.println();

        // ============================================================
        // BÀI 9: Queue - Mô phỏng hàng đợi mua vé
        // ============================================================
        System.out.println("=== BÀI 9: Queue - Hàng đợi mua vé ===");

        Queue<String> ticketQueue = new LinkedList<>();
        ticketQueue.offer("Khách 1 - Nguyễn Văn A");
        ticketQueue.offer("Khách 2 - Trần Thị B");
        ticketQueue.offer("Khách 3 - Lê Văn C");
        ticketQueue.offer("Khách 4 - Phạm Thị D");
        ticketQueue.offer("Khách 5 - Hoàng Văn E");

        System.out.println("  Hàng đợi ban đầu: " + ticketQueue);
        System.out.println("  Số người chờ: " + ticketQueue.size());

        System.out.println("\n  --- Bắt đầu bán vé ---");
        int ticketNumber = 1;
        while (!ticketQueue.isEmpty()) {
            String customer = ticketQueue.poll();
            System.out.printf("  Vé #%d → %s%n", ticketNumber++, customer);
        }
        System.out.println("  Hàng đợi rỗng: " + ticketQueue.isEmpty());
        System.out.println();

        // ============================================================
        // BÀI 10: Stack - Kiểm tra dấu ngoặc hợp lệ
        // ============================================================
        System.out.println("=== BÀI 10: Stack - Kiểm tra dấu ngoặc ===");

        String[] expressions = {
            "((a + b) * (c - d))",
            "{[a + b] * (c - d)}",
            "((a + b)",
            "([a + b)]",
            "{()}[]",
            ""
        };

        for (String expr : expressions) {
            boolean valid = isValidBrackets(expr);
            System.out.printf("  %-25s → %s%n", "\"" + expr + "\"",
                    valid ? "Hợp lệ ✓" : "Không hợp lệ ✗");
        }

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("11. Dùng TreeMap sắp xếp từ điển Anh-Việt theo alphabet");
        System.out.println("12. Implement LRU Cache đơn giản bằng LinkedHashMap");
        System.out.println("13. Dùng PriorityQueue mô phỏng phòng khám (ưu tiên người cao tuổi)");
        System.out.println("14. Tìm 2 phần tử trong mảng có tổng bằng target (dùng HashMap)");
        System.out.println("15. Dùng Stack đánh giá biểu thức Postfix");
    }

    /**
     * Kiểm tra dấu ngoặc hợp lệ dùng Stack
     */
    static boolean isValidBrackets(String expression) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
