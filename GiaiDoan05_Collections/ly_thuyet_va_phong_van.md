# Giai Đoạn 5: Collections Framework

## 📚 Lý Thuyết

### Tổng quan Collections Framework
```
                     Collection (I)
                    /       |       \
                List (I)  Set (I)   Queue (I)
               /    \      |    \        \
         ArrayList  LinkedList  HashSet  PriorityQueue
                    LinkedHashSet  TreeSet

                     Map (I)
                    /    |    \
              HashMap  LinkedHashMap  TreeMap
```

### 1. List - Danh sách có thứ tự, cho phép trùng

#### ArrayList
```java
List<String> names = new ArrayList<>();
names.add("Bao");       // Thêm cuối
names.add(0, "An");     // Thêm tại index
names.get(0);           // Lấy phần tử
names.set(0, "Minh");   // Sửa phần tử
names.remove(0);        // Xóa theo index
names.remove("Bao");    // Xóa theo giá trị
names.contains("An");   // Kiểm tra tồn tại
names.size();           // Kích thước
names.isEmpty();        // Kiểm tra rỗng
```

| ArrayList | LinkedList |
|-----------|------------|
| Dựa trên **mảng động** | Dựa trên **doubly linked list** |
| Truy cập nhanh O(1) | Truy cập chậm O(n) |
| Thêm/xóa giữa chậm O(n) | Thêm/xóa đầu/cuối nhanh O(1) |
| Dùng nhiều hơn 95% cases | Dùng khi thêm/xóa đầu/cuối nhiều |

### 2. Set - Tập hợp, KHÔNG cho phép trùng

```java
Set<String> emails = new HashSet<>();
emails.add("a@gmail.com");
emails.add("a@gmail.com"); // Không thêm được - đã tồn tại
// size = 1
```

| HashSet | LinkedHashSet | TreeSet |
|---------|---------------|---------|
| Không giữ thứ tự | Giữ thứ tự thêm vào | Sắp xếp tự nhiên |
| Nhanh nhất O(1) | O(1) | O(log n) |
| Dùng phổ biến nhất | Cần giữ insertion order | Cần sorted |

### 3. Map - Cặp key-value

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Bao", 9);          // Thêm
scores.get("Bao");             // Lấy value theo key → 9
scores.getOrDefault("X", 0);   // Lấy hoặc giá trị mặc định
scores.containsKey("Bao");     // Kiểm tra key
scores.containsValue(9);       // Kiểm tra value
scores.remove("Bao");          // Xóa
scores.keySet();               // Set các key
scores.values();               // Collection các value
scores.entrySet();             // Set các entry (key-value)
```

| HashMap | LinkedHashMap | TreeMap |
|---------|---------------|---------|
| Không giữ thứ tự | Giữ thứ tự thêm vào | Sắp xếp theo key |
| Nhanh nhất O(1) | O(1) | O(log n) |
| Dùng phổ biến nhất | Cần insertion order | Cần sorted keys |

### 4. Queue và Stack

```java
// Queue - FIFO (First In First Out)
Queue<String> queue = new LinkedList<>();
queue.offer("A");   // Thêm cuối
queue.offer("B");
queue.poll();       // Lấy + xóa đầu → "A"
queue.peek();       // Xem đầu (không xóa) → "B"

// Stack (dùng Deque) - LIFO (Last In First Out)
Deque<String> stack = new ArrayDeque<>();
stack.push("A");    // Thêm đỉnh
stack.push("B");
stack.pop();        // Lấy + xóa đỉnh → "B"
stack.peek();       // Xem đỉnh → "A"
```

### 5. Comparable và Comparator

#### Comparable - Sắp xếp tự nhiên (trong class)
```java
public class Student implements Comparable<Student> {
    private String name;
    private int score;

    @Override
    public int compareTo(Student other) {
        return this.score - other.score; // tăng dần
    }
}
Collections.sort(students); // Dùng compareTo()
```

#### Comparator - Sắp xếp tùy chỉnh (bên ngoài)
```java
// Giảm dần theo điểm
students.sort((s1, s2) -> s2.getScore() - s1.getScore());

// Theo tên A-Z
students.sort(Comparator.comparing(Student::getName));

// Theo điểm giảm, nếu bằng thì theo tên tăng
students.sort(Comparator.comparing(Student::getScore).reversed()
                        .thenComparing(Student::getName));
```

### 6. equals() và hashCode()

Khi dùng `HashSet` hoặc `HashMap`, cần override `equals()` và `hashCode()`:
```java
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
```

**Quy tắc:** Nếu `a.equals(b)` thì `a.hashCode() == b.hashCode()` (nhưng ngược lại không bắt buộc).

---

## 🎯 Câu Hỏi Phỏng Vấn Thường Gặp

### Q1: ArrayList vs LinkedList?
**Trả lời:**
- **ArrayList:** Dựa trên mảng động. Truy cập index O(1), thêm/xóa giữa O(n). Dùng trong hầu hết trường hợp.
- **LinkedList:** Dựa trên doubly linked list. Truy cập O(n), thêm/xóa đầu/cuối O(1). Dùng khi thao tác nhiều ở đầu/cuối.

### Q2: HashSet hoạt động thế nào bên trong?
**Trả lời:**
HashSet bên trong dùng **HashMap**. Mỗi phần tử được lưu như **key** của HashMap (value là dummy object). Khi add:
1. Tính `hashCode()` → xác định bucket
2. Kiểm tra `equals()` với các phần tử trong bucket
3. Nếu bằng → không thêm (đảm bảo unique)

### Q3: HashMap hoạt động thế nào?
**Trả lời:**
HashMap dùng **array of buckets**. Mỗi bucket là linked list (hoặc tree khi quá dài):
1. Tính `hashCode()` của key → xác định index bucket
2. Nếu bucket rỗng → thêm entry mới
3. Nếu có collision → duyệt linked list, so sánh `equals()`
4. Nếu key bằng → cập nhật value, ngược lại → thêm vào cuối

Từ Java 8: Khi bucket có > 8 entries → chuyển thành **balanced tree** (O(log n)).

### Q4: Tại sao phải override cả equals() và hashCode()?
**Trả lời:**
- Nếu chỉ override `equals()` mà không `hashCode()`: 2 object bằng nhau theo equals nhưng hashCode khác → HashMap/HashSet coi là khác nhau → **bug**
- **Contract:** Nếu `a.equals(b)` thì `a.hashCode() == b.hashCode()`
- Khi dùng object làm key trong HashMap hoặc phần tử trong HashSet, **bắt buộc** phải override cả hai

### Q5: Comparable vs Comparator?
**Trả lời:**
| Comparable | Comparator |
|------------|------------|
| Interface trong class | Tạo ở bên ngoài |
| Method: `compareTo()` | Method: `compare()` |
| 1 cách sắp xếp (natural ordering) | Nhiều cách sắp xếp |
| `Collections.sort(list)` | `list.sort(comparator)` |

### Q6: Iterator và for-each khác nhau thế nào?
**Trả lời:**
- `for-each`: Đơn giản, dễ đọc. Nhưng **không thể** xóa phần tử khi đang duyệt.
- `Iterator`: Có thể **xóa an toàn** bằng `iterator.remove()` khi đang duyệt.
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("remove")) {
        it.remove(); // An toàn
    }
}
```

### Q7: ConcurrentModificationException xảy ra khi nào?
**Trả lời:**
Xảy ra khi **sửa đổi** collection trong khi đang duyệt bằng for-each:
```java
for (String s : list) {
    list.remove(s); // ConcurrentModificationException!
}
```
Cách giải quyết: Dùng `Iterator.remove()`, `removeIf()`, hoặc CopyOnWriteArrayList.

### Q8: Khi nào dùng List, Set, Map?
**Trả lời:**
- **List:** Cần thứ tự, cho phép trùng. VD: danh sách đơn hàng.
- **Set:** Không cho trùng. VD: danh sách email unique.
- **Map:** Cặp key-value. VD: username → User object, config settings.
