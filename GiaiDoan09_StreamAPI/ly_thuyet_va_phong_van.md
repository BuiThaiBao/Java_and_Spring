# Giai Đoạn 9: Stream API

## 📚 Lý Thuyết

### Tổng quan Stream API
Stream API xử lý collections theo kiểu **functional/declarative** thay vì imperative (vòng lặp).

```
Source → Intermediate Operations → Terminal Operation → Result
(List)     (filter, map, sorted)     (collect, forEach)    (List, value)
```

### Các Intermediate Operations (trả về Stream - lazy)
| Operation | Mô tả | Ví dụ |
|-----------|--------|-------|
| `filter(Predicate)` | Lọc phần tử | `.filter(s -> s.getScore() >= 8)` |
| `map(Function)` | Chuyển đổi phần tử | `.map(Student::getName)` |
| `flatMap(Function)` | Chuyển đổi + flatten | `.flatMap(list -> list.stream())` |
| `sorted()` | Sắp xếp | `.sorted(Comparator.comparing(S::getName))` |
| `distinct()` | Loại bỏ trùng | `.distinct()` |
| `limit(n)` | Lấy n phần tử đầu | `.limit(5)` |
| `skip(n)` | Bỏ qua n phần tử đầu | `.skip(2)` |
| `peek(Consumer)` | Debug (xem phần tử) | `.peek(System.out::println)` |

### Các Terminal Operations (kết thúc stream)
| Operation | Mô tả | Trả về |
|-----------|--------|--------|
| `collect()` | Thu thập kết quả | List, Set, Map |
| `forEach()` | Duyệt | void |
| `count()` | Đếm | long |
| `reduce()` | Gộp thành 1 giá trị | Optional / value |
| `anyMatch()` | Có phần tử nào thỏa? | boolean |
| `allMatch()` | Tất cả thỏa? | boolean |
| `noneMatch()` | Không có ai thỏa? | boolean |
| `findFirst()` | Phần tử đầu tiên | Optional |
| `findAny()` | Bất kỳ phần tử nào | Optional |
| `min() / max()` | Nhỏ nhất / lớn nhất | Optional |

### Collectors phổ biến
```java
.collect(Collectors.toList())
.collect(Collectors.toSet())
.collect(Collectors.toMap(S::getId, Function.identity()))
.collect(Collectors.groupingBy(S::getClassName))
.collect(Collectors.partitioningBy(s -> s.getScore() >= 5))
.collect(Collectors.counting())
.collect(Collectors.joining(", "))
.collect(Collectors.averagingDouble(S::getScore))
.collect(Collectors.summarizingDouble(S::getScore))
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Stream là gì? Lazy evaluation là gì?
**Trả lời:** Stream là pipeline xử lý dữ liệu từ collection. Intermediate operations là **lazy** - chỉ thực thi khi gặp terminal operation. Giúp tối ưu vì không tạo collection trung gian.

### Q2: Stream vs Collection?
**Trả lời:** Collection lưu trữ dữ liệu (data structure). Stream xử lý dữ liệu (computation). Stream **không lưu** dữ liệu, **không thay đổi** source, chỉ dùng **1 lần**.

### Q3: `map()` vs `flatMap()`?
**Trả lời:** `map()`: 1 phần tử → 1 phần tử (1:1). `flatMap()`: 1 phần tử → nhiều phần tử, rồi flatten thành 1 stream (1:N).
```java
// map: List<String> → List<String[]>
// flatMap: List<String> → Stream<String> (flatten)
```

### Q4: Parallel stream khi nào dùng?
**Trả lời:** Khi xử lý **lượng lớn** dữ liệu, **không có side effect**, các phần tử **độc lập**. Không dùng khi: dữ liệu nhỏ (overhead lớn hơn lợi ích), có shared state, cần thứ tự.
