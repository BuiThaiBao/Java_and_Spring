import java.util.*;

/**
 * ============================================================
 * GIAI ĐOẠN 6: GENERICS - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    // ====== Generic Class ======
    static class Box<T> {
        private T value;

        public Box(T value) { this.value = value; }
        public T getValue() { return value; }
        public void setValue(T value) { this.value = value; }

        @Override
        public String toString() { return "Box[" + value + "]"; }
    }

    // ====== Generic Class với 2 type parameters ======
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() { return key + " = " + value; }
    }

    // ====== Generic Interface ======
    interface Repository<T> {
        void save(T entity);
        T findById(int id);
        List<T> findAll();
    }

    static class Product {
        int id;
        String name;
        double price;

        Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("Product{id=%d, name='%s', price=%.0f}", id, name, price);
        }
    }

    // Generic Repository Implementation
    static class ProductRepository implements Repository<Product> {
        private List<Product> products = new ArrayList<>();

        @Override
        public void save(Product entity) {
            products.add(entity);
        }

        @Override
        public Product findById(int id) {
            for (Product p : products) {
                if (p.id == id) return p;
            }
            return null;
        }

        @Override
        public List<Product> findAll() {
            return new ArrayList<>(products);
        }
    }

    // ====== Generic Methods ======
    public static <T> void printArray(T[] array) {
        System.out.print("  [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) max = item;
        }
        return max;
    }

    public static <T extends Number> double sumList(List<T> list) {
        double total = 0;
        for (T num : list) {
            total += num.doubleValue();
        }
        return total;
    }

    // Wildcard examples
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static double sumNumbers(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        return total;
    }

    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    // ====== Main ======
    public static void main(String[] args) {

        // BÀI 1: Generic Class - Box
        System.out.println("=== BÀI 1: Generic Class ===");
        Box<String> stringBox = new Box<>("Hello Java");
        Box<Integer> intBox = new Box<>(42);
        Box<Double> doubleBox = new Box<>(3.14);

        System.out.println("  " + stringBox);
        System.out.println("  " + intBox);
        System.out.println("  " + doubleBox);
        System.out.println();

        // BÀI 2: Generic Pair
        System.out.println("=== BÀI 2: Generic Pair ===");
        Pair<String, Integer> nameAge = new Pair<>("Bảo", 22);
        Pair<String, Double> nameScore = new Pair<>("An", 9.5);
        System.out.println("  " + nameAge);
        System.out.println("  " + nameScore);
        System.out.println();

        // BÀI 3: Generic Method
        System.out.println("=== BÀI 3: Generic Method ===");
        Integer[] ints = {3, 7, 1, 9, 4};
        String[] strs = {"banana", "apple", "cherry"};
        Double[] doubles = {3.14, 2.71, 1.41};

        System.out.print("  Integer array: ");
        printArray(ints);
        System.out.println("  Max Integer: " + findMax(ints));

        System.out.print("  String array: ");
        printArray(strs);
        System.out.println("  Max String: " + findMax(strs));
        System.out.println();

        // BÀI 4: Bounded Type Parameter
        System.out.println("=== BÀI 4: Bounded Type ===");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("  Sum integers: " + sumList(intList));
        System.out.println("  Sum doubles: " + sumList(doubleList));
        System.out.println();

        // BÀI 5: Wildcard
        System.out.println("=== BÀI 5: Wildcard ===");
        List<String> names = Arrays.asList("Bảo", "An", "Minh");
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        System.out.print("  List<?> String: ");
        printList(names);
        System.out.print("  List<?> Integer: ");
        printList(numbers);

        System.out.println("  Sum (? extends Number): " + sumNumbers(numbers));

        List<Number> numberList = new ArrayList<>();
        addIntegers(numberList); // ? super Integer
        System.out.println("  After addIntegers: " + numberList);
        System.out.println();

        // BÀI 6: Generic Repository
        System.out.println("=== BÀI 6: Generic Repository ===");
        ProductRepository repo = new ProductRepository();
        repo.save(new Product(1, "Laptop", 25000000));
        repo.save(new Product(2, "Phone", 15000000));
        repo.save(new Product(3, "Tablet", 10000000));

        System.out.println("  Find ID 2: " + repo.findById(2));
        System.out.println("  All products:");
        for (Product p : repo.findAll()) {
            System.out.println("    " + p);
        }

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("7. Tạo generic class Stack<T> với push/pop/peek");
        System.out.println("8. Tạo generic method swap<T>(T[] arr, int i, int j)");
        System.out.println("9. Tạo generic interface Converter<F, T> chuyển đổi kiểu");
        System.out.println("10. Tạo generic Repository<T> hoàn chỉnh với CRUD");
    }
}
