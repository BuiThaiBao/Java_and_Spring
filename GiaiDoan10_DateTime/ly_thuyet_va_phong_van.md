# Giai Đoạn 10: Date and Time API

## 📚 Lý Thuyết

### Các class chính (java.time - Java 8+)
| Class | Mô tả | Ví dụ |
|-------|--------|-------|
| `LocalDate` | Chỉ ngày | 2026-05-16 |
| `LocalTime` | Chỉ giờ | 15:30:00 |
| `LocalDateTime` | Ngày + giờ | 2026-05-16T15:30:00 |
| `ZonedDateTime` | Ngày giờ + timezone | 2026-05-16T15:30+07:00 |
| `Instant` | Timestamp (epoch) | Dùng cho máy |
| `Duration` | Khoảng thời gian (giờ, phút, giây) | 2 hours |
| `Period` | Khoảng thời gian (năm, tháng, ngày) | 1 year 3 months |
| `DateTimeFormatter` | Format/Parse ngày giờ | dd/MM/yyyy |

### Tạo và xử lý ngày
```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(2004, 5, 20);
LocalDate parsed = LocalDate.parse("16/05/2026", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

// Tính toán
LocalDate nextWeek = today.plusWeeks(1);
Period age = Period.between(birthday, today);
long daysBetween = ChronoUnit.DAYS.between(date1, date2);
```

---

## 🎯 Câu Hỏi Phỏng Vấn

### Q1: Tại sao nên dùng java.time thay vì Date/Calendar?
**Trả lời:** `Date`/`Calendar` cũ có nhiều vấn đề: mutable (không thread-safe), API khó dùng, month bắt đầu từ 0. `java.time` immutable, fluent API, thread-safe, rõ ràng hơn.

### Q2: LocalDateTime vs ZonedDateTime?
**Trả lời:** `LocalDateTime` không có timezone - dùng cho logic local. `ZonedDateTime` có timezone - dùng khi cần xử lý multiple timezone (API quốc tế, scheduling).

### Q3: Instant dùng khi nào?
**Trả lời:** Instant đại diện cho 1 điểm thời gian trên timeline (epoch milliseconds). Dùng cho: timestamp database, logging, so sánh thời gian giữa các timezone, audit trail.
