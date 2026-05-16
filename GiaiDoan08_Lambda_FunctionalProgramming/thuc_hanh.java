package GiaiDoan08_Lambda_FunctionalProgramming;

import java.util.*;
import java.util.function.*;

/**
 * ============================================================
 * GIAI ĐOẠN 8: LAMBDA & FUNCTIONAL PROGRAMMING - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }

    public static void main(String[] args) {

        // BÀI 1: Lambda cơ bản
        System.out.println("=== BÀI 1: Lambda cơ bản ===");
        Calculator sum = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("  10 + 5 = " + sum.calculate(10, 5));
        System.out.println("  10 - 5 = " + subtract.calculate(10, 5));
        System.out.println("  10 * 5 = " + multiply.calculate(10, 5));
        System.out.println();

        // BÀI 2: Predicate - kiểm tra điều kiện
        System.out.println("=== BÀI 2: Predicate ===");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<String> isLongEnough = s -> s.length() >= 8;

        System.out.println("  4 là chẵn? " + isEven.test(4));
        System.out.println("  -3 là dương? " + isPositive.test(-3));
        System.out.println("  'password123' >= 8 ký tự? " + isLongEnough.test("password123"));

        // Kết hợp Predicate
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("  4 chẵn VÀ dương? " + isEvenAndPositive.test(4));
        System.out.println("  -4 chẵn VÀ dương? " + isEvenAndPositive.test(-4));
        System.out.println();

        // BÀI 3: Function - chuyển đổi dữ liệu
        System.out.println("=== BÀI 3: Function ===");
        Function<String, Integer> toLength = String::length;
        Function<String, String> toUpper = String::toUpperCase;
        Function<Integer, String> toGrade = score -> {
            if (score >= 9) return "Xuất sắc";
            if (score >= 7) return "Khá";
            if (score >= 5) return "TB";
            return "Yếu";
        };

        System.out.println("  Length of 'Hello': " + toLength.apply("Hello"));
        System.out.println("  Upper of 'hello': " + toUpper.apply("hello"));
        System.out.println("  Grade of 8: " + toGrade.apply(8));

        // Chain functions
        Function<String, String> trimAndUpper = ((Function<String, String>) String::trim).andThen(String::toUpperCase);
        System.out.println("  trimAndUpper: '" + trimAndUpper.apply("  hello  ") + "'");
        System.out.println();

        // BÀI 4: Consumer - xử lý không trả về
        System.out.println("=== BÀI 4: Consumer ===");
        Consumer<String> printFormatted = s -> System.out.println("  → " + s);
        Consumer<String> printLength = s -> System.out.println("    (length: " + s.length() + ")");

        Consumer<String> printAll = printFormatted.andThen(printLength);
        printAll.accept("Hello Java");
        System.out.println();

        // BÀI 5: Supplier - tạo dữ liệu
        System.out.println("=== BÀI 5: Supplier ===");
        Supplier<String> randomGreeting = () -> {
            String[] greetings = {"Hello!", "Hi!", "Hey!", "Xin chào!"};
            return greetings[new Random().nextInt(greetings.length)];
        };

        for (int i = 0; i < 3; i++) {
            System.out.println("  " + randomGreeting.get());
        }
        System.out.println();

        // BÀI 6: Sắp xếp với Lambda
        System.out.println("=== BÀI 6: Sắp xếp ===");
        List<String> names = new ArrayList<>(Arrays.asList("Minh", "An", "Bảo", "Hương", "Nam"));

        // Sắp theo length
        names.sort(Comparator.comparingInt(String::length));
        System.out.println("  Theo length: " + names);

        // Sắp theo alphabet
        names.sort(Comparator.naturalOrder());
        System.out.println("  Alphabet: " + names);

        // Ngược
        names.sort(Comparator.reverseOrder());
        System.out.println("  Reverse: " + names);
        System.out.println();

        // BÀI 7: Custom Functional Interface
        System.out.println("=== BÀI 7: Custom Interface ===");
        StringProcessor trimmer = String::trim;
        StringProcessor upper = String::toUpperCase;
        StringProcessor addBrackets = s -> "[" + s + "]";

        String input = "  hello world  ";
        System.out.println("  Input: '" + input + "'");
        System.out.println("  Trim: '" + trimmer.process(input) + "'");
        System.out.println("  Upper: '" + upper.process(input) + "'");
        System.out.println("  Brackets: '" + addBrackets.process(input) + "'");

        // BÀI 8: Method Reference
        System.out.println("\n=== BÀI 8: Method Reference ===");
        List<String> items = Arrays.asList("Java", "Spring", "Boot");

        System.out.println("  forEach with method reference:");
        items.forEach(System.out::println);

        // BiFunction
        BiFunction<Integer, Integer, Integer> maxFunc = Math::max;
        System.out.println("  Max(10, 20): " + maxFunc.apply(10, 20));

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("9.  Viết Predicate kiểm tra email hợp lệ");
        System.out.println("10. Viết Function chuyển đổi List<String> → List<Integer> (lengths)");
        System.out.println("11. Tạo mini calculator dùng Map<String, Calculator>");
        System.out.println("12. Viết chain of processors cho text processing pipeline");
    }
}
