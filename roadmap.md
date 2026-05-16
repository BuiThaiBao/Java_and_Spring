Mình sẽ chia roadmap theo từng giai đoạn từ nền tảng đến nâng cao, kèm mục tiêu, bài tập thực hành và dấu hiệu “đã học đủ” để bạn tự kiểm tra.

Thought for 6s

Dưới đây là roadmap học Java Core đầy đủ, chi tiết, phù hợp nếu bạn muốn đi theo hướng Java Backend / Spring Boot. Hiện tại có thể học bằng JDK 21 hoặc JDK 25 LTS. Oracle hiện liệt kê JDK 25 là bản LTS mới nhất, còn JDK 21 là bản LTS trước đó; nếu học để đi làm, Java 21 vẫn rất phổ biến, còn Java 25 có thể dùng nếu bạn muốn cập nhật hơn.

Roadmap Java Core từ cơ bản đến nâng cao
Giai đoạn 0: Chuẩn bị môi trường
Cần cài

Bạn nên cài:

JDK 21 hoặc JDK 25
IntelliJ IDEA Community
Git
Maven hoặc Gradle, nhưng ban đầu nên học Maven trước
MySQL hoặc PostgreSQL nếu sau này học JDBC
Cần hiểu

Trước khi code nhiều, bạn cần hiểu:

JDK là gì?
JRE là gì?
JVM là gì?
Java source code .java
Bytecode .class
Compile và run chương trình Java

Ví dụ:

javac Main.java
java Main

Mục tiêu của giai đoạn này là bạn biết Java không chạy trực tiếp file .java, mà compile thành bytecode .class, sau đó JVM chạy bytecode.

Giai đoạn 1: Java syntax cơ bản

1. Cấu trúc chương trình Java

Bạn cần hiểu:

public class Main {
public static void main(String[] args) {
System.out.println("Hello Java");
}
}

Cần nắm:

class
main method
public
static
void
String[] args
System.out.println

Chưa cần hiểu quá sâu static ngay từ đầu, nhưng cần biết đây là điểm bắt đầu chạy chương trình.

2. Biến và kiểu dữ liệu
   Kiểu nguyên thủy
   byte
   short
   int
   long
   float
   double
   char
   boolean

Ví dụ:

int age = 22;
double salary = 1500.5;
char grade = 'A';
boolean isActive = true;
Kiểu tham chiếu
String name = "Bao";
Integer number = 10;

Bạn cần phân biệt:

primitive type: int, double, boolean...
reference type: String, Object, Array, Class...

Đây là nền tảng rất quan trọng vì sau này học OOP, Collection, null, memory đều liên quan đến reference.

3. Ép kiểu dữ liệu

Cần học:

Widening casting
int a = 10;
double b = a;
Narrowing casting
double x = 10.5;
int y = (int) x;
Parse String
int age = Integer.parseInt("20");
double price = Double.parseDouble("99.5");
Convert sang String
String s = String.valueOf(123); 4. Toán tử

Cần nắm:

- - - / %
      ++ --
      == != > < >= <=
      && || !
      = += -= \*= /=

Đặc biệt phải hiểu kỹ:

int x = 10;
System.out.println(x++);
System.out.println(++x);

Vì x++ và ++x rất hay gây nhầm.

5. Câu điều kiện
   if
   else if
   else
   switch

Ví dụ:

int score = 8;

if (score >= 8) {
System.out.println("Good");
} else if (score >= 5) {
System.out.println("Average");
} else {
System.out.println("Fail");
}

Với Java hiện đại, switch có thể viết gọn hơn:

String level = switch (score) {
case 10, 9, 8 -> "Good";
case 7, 6, 5 -> "Average";
default -> "Fail";
};

Java SE có tài liệu riêng cho các cập nhật ngôn ngữ từ Java 9 trở đi, nên sau khi vững syntax cũ bạn có thể xem thêm phần language updates chính thức.

6. Vòng lặp

Cần học:

for
while
do while
break
continue

Ví dụ:

for (int i = 1; i <= 10; i++) {
System.out.println(i);
}

Ví dụ while:

int i = 1;

while (i <= 10) {
System.out.println(i);
i++;
}
Bài tập giai đoạn 1

Bạn nên làm các bài sau:

1. Nhập vào số nguyên, kiểm tra chẵn lẻ.
2. Nhập điểm, xếp loại học lực.
3. In bảng cửu chương.
4. Tính tổng từ 1 đến n.
5. Kiểm tra số nguyên tố.
6. Tìm số lớn nhất trong 3 số.
7. Tính giai thừa.
8. Đảo ngược một số nguyên.
9. Kiểm tra số đối xứng.
10. In tam giác sao.

Khi làm được các bài này mà không cần nhìn lời giải, bạn có thể sang giai đoạn tiếp theo.

Giai đoạn 2: Array, String, Method

1. Mảng

Cần học:

int[] numbers = {1, 2, 3, 4, 5};

Truy cập phần tử:

System.out.println(numbers[0]);

Duyệt mảng:

for (int i = 0; i < numbers.length; i++) {
System.out.println(numbers[i]);
}

For-each:

for (int number : numbers) {
System.out.println(number);
}

Cần nắm:

index bắt đầu từ 0
length của mảng
ArrayIndexOutOfBoundsException
mảng có kích thước cố định 2. String

String là phần cực kỳ quan trọng.

Cần học:

String name = "Bao";

Các method quan trọng:

length()
charAt()
substring()
contains()
equals()
equalsIgnoreCase()
toLowerCase()
toUpperCase()
trim()
replace()
split()
startsWith()
endsWith()

Ví dụ:

String email = "test@gmail.com";

if (email.endsWith("@gmail.com")) {
System.out.println("Gmail account");
}
Lưu ý rất quan trọng

Không so sánh String bằng ==.

Sai:

if (name == "Bao") {
}

Đúng:

if (name.equals("Bao")) {
} 3. StringBuilder

Khi nối chuỗi nhiều lần, nên dùng:

StringBuilder sb = new StringBuilder();

sb.append("Hello");
sb.append(" ");
sb.append("Java");

System.out.println(sb.toString());

Cần hiểu:

String là immutable
StringBuilder dùng để build chuỗi hiệu quả hơn 4. Method

Cần học cách tách logic thành hàm.

Ví dụ:

public static int sum(int a, int b) {
return a + b;
}

Gọi method:

int result = sum(3, 5);

Cần nắm:

parameter
argument
return type
void
method overloading
scope của biến

Ví dụ overloading:

public static int sum(int a, int b) {
return a + b;
}

public static double sum(double a, double b) {
return a + b;
}
Bài tập giai đoạn 2

1. Tìm max/min trong mảng.
2. Tính tổng các phần tử trong mảng.
3. Đếm số chẵn, số lẻ.
4. Sắp xếp mảng tăng dần.
5. Tìm phần tử xuất hiện nhiều nhất.
6. Đảo ngược chuỗi.
7. Kiểm tra chuỗi palindrome.
8. Đếm số từ trong chuỗi.
9. Viết method kiểm tra email hợp lệ đơn giản.
10. Viết chương trình quản lý danh sách sinh viên bằng mảng.
    Giai đoạn 3: OOP - Lập trình hướng đối tượng

Đây là phần quan trọng nhất của Java Core.

1. Class và Object

Ví dụ:

public class Student {
String name;
int age;
}

Tạo object:

Student student = new Student();
student.name = "Bao";
student.age = 22;

Cần hiểu:

Class là bản thiết kế
Object là đối tượng cụ thể được tạo từ class 2. Constructor
public class Student {
String name;
int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

Tạo object:

Student student = new Student("Bao", 22);

Cần hiểu:

constructor dùng để khởi tạo object
this đại diện cho object hiện tại 3. Encapsulation

Dùng private field và getter/setter.

public class Student {
private String name;
private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

Cần hiểu tại sao không nên để field public.

4. Inheritance
   public class Animal {
   public void eat() {
   System.out.println("Eating...");
   }
   }

public class Dog extends Animal {
public void bark() {
System.out.println("Barking...");
}
}

Cần hiểu:

extends
super
method overriding
IS-A relationship 5. Polymorphism
Animal animal = new Dog();
animal.eat();

Ví dụ thực tế:

Payment payment = new MomoPayment();
payment.pay();

Cần hiểu:

Một interface/class cha có thể tham chiếu tới nhiều object con khác nhau

Đây là nền tảng để hiểu Spring, Design Pattern, Clean Architecture.

6. Abstraction
   Abstract class
   public abstract class Shape {
   public abstract double area();
   }
   Interface
   public interface Payment {
   void pay();
   }

Implement:

public class MomoPayment implements Payment {
@Override
public void pay() {
System.out.println("Pay by Momo");
}
}

Cần hiểu khác nhau giữa:

abstract class
interface
extends
implements 7. Access modifier

Cần nắm:

public
private
protected
default

Bảng đơn giản:

Modifier Trong class Cùng package Class con Bên ngoài
public Có Có Có Có
protected Có Có Có Không trực tiếp
default Có Có Không nếu khác package Không
private Có Không Không Không 8. static và final
static
public class MathUtil {
public static int sum(int a, int b) {
return a + b;
}
}

Gọi:

MathUtil.sum(1, 2);
final
final int MAX_SIZE = 100;

Cần hiểu:

static thuộc về class
non-static thuộc về object
final biến: không đổi giá trị
final method: không override được
final class: không kế thừa được
Bài tập giai đoạn 3

1. Tạo class Student gồm id, name, age, score.
2. Viết getter/setter, constructor.
3. Tạo class StudentManager để thêm, sửa, xóa, tìm kiếm sinh viên.
4. Tạo abstract class Employee.
5. Tạo FullTimeEmployee và PartTimeEmployee kế thừa Employee.
6. Tính lương khác nhau theo từng loại nhân viên.
7. Tạo interface Payment.
8. Tạo MomoPayment, BankPayment, CashPayment.
9. Viết chương trình chọn phương thức thanh toán.
10. Refactor code để dùng polymorphism thay vì if-else quá nhiều.
    Giai đoạn 4: Exception Handling
11. Exception là gì?

Exception là lỗi xảy ra trong lúc chương trình chạy.

Ví dụ:

int result = 10 / 0;

Sẽ gây lỗi:

ArithmeticException 2. try-catch
try {
int result = 10 / 0;
} catch (ArithmeticException e) {
System.out.println("Cannot divide by zero");
} 3. finally
try {
System.out.println("Open resource");
} catch (Exception e) {
System.out.println("Error");
} finally {
System.out.println("Close resource");
} 4. throw và throws
public static void checkAge(int age) {
if (age < 18) {
throw new IllegalArgumentException("Age must be >= 18");
}
}

Ví dụ throws:

public static void readFile() throws IOException {
} 5. Checked và unchecked exception

Cần phân biệt:

Checked exception: bắt buộc xử lý hoặc throws
Unchecked exception: RuntimeException, không bắt buộc xử lý

Ví dụ:

IOException: checked
SQLException: checked
NullPointerException: unchecked
IllegalArgumentException: unchecked 6. Custom exception
public class UserNotFoundException extends RuntimeException {
public UserNotFoundException(String message) {
super(message);
}
}

Dùng:

throw new UserNotFoundException("User not found");

Đây là phần rất quan trọng khi học Spring Boot API.

Giai đoạn 5: Collections Framework

Đây là phần cực kỳ quan trọng khi đi làm.

1. List
   List<String> names = new ArrayList<>();

names.add("Bao");
names.add("An");
names.add("Minh");

Duyệt:

for (String name : names) {
System.out.println(name);
}

Cần học:

ArrayList
LinkedList
add
remove
get
set
contains
size
isEmpty 2. Set
Set<String> emails = new HashSet<>();

emails.add("a@gmail.com");
emails.add("a@gmail.com");

Set không lưu phần tử trùng.

Cần học:

HashSet
LinkedHashSet
TreeSet 3. Map
Map<String, Integer> scores = new HashMap<>();

scores.put("Bao", 9);
scores.put("An", 8);

Lấy dữ liệu:

System.out.println(scores.get("Bao"));

Duyệt Map:

for (Map.Entry<String, Integer> entry : scores.entrySet()) {
System.out.println(entry.getKey() + ": " + entry.getValue());
}

Cần học:

HashMap
LinkedHashMap
TreeMap
put
get
remove
containsKey
containsValue
keySet
values
entrySet 4. Queue và Stack
Queue<String> queue = new LinkedList<>();
queue.offer("A");
queue.offer("B");
System.out.println(queue.poll());

Stack hiện đại thường dùng Deque:

Deque<String> stack = new ArrayDeque<>();
stack.push("A");
stack.push("B");
System.out.println(stack.pop()); 5. Comparable và Comparator
Comparable
public class Student implements Comparable<Student> {
private String name;
private int score;

    @Override
    public int compareTo(Student other) {
        return this.score - other.score;
    }

}
Comparator
students.sort((s1, s2) -> s2.getScore() - s1.getScore());

Cần hiểu để sort object theo:

điểm tăng dần
điểm giảm dần
tên A-Z
tuổi
ngày tạo
Bài tập giai đoạn 5

1. Quản lý danh sách sinh viên bằng ArrayList.
2. Tìm sinh viên theo id.
3. Xóa sinh viên theo id.
4. Sắp xếp sinh viên theo điểm giảm dần.
5. Sắp xếp sinh viên theo tên.
6. Dùng HashMap lưu username/password.
7. Đếm tần suất xuất hiện của từng từ trong chuỗi.
8. Dùng Set để loại bỏ email trùng.
9. Dùng Queue mô phỏng hàng đợi mua vé.
10. Dùng Stack kiểm tra dấu ngoặc hợp lệ.
    Giai đoạn 6: Generics

Generics giúp code an toàn kiểu dữ liệu hơn.

1.  Generic class
    public class Box<T> {
    private T value;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

    }

Dùng:

Box<String> stringBox = new Box<>();
stringBox.setValue("Hello"); 2. Generic method
public static <T> void printArray(T[] array) {
for (T item : array) {
System.out.println(item);
}
} 3. Wildcard
List<? extends Number>
List<? super Integer>

Ban đầu chỉ cần hiểu cơ bản:

? extends T: đọc dữ liệu kiểu T hoặc con của T
? super T: ghi dữ liệu kiểu T hoặc cha của T

Phần này hơi khó, học sau khi đã vững Collection.

Giai đoạn 7: Java IO và File

1. Đọc ghi file text
   Files.writeString(Path.of("data.txt"), "Hello Java");

String content = Files.readString(Path.of("data.txt"));
System.out.println(content); 2. Đọc nhiều dòng
List<String> lines = Files.readAllLines(Path.of("data.txt"));

for (String line : lines) {
System.out.println(line);
} 3. Stream file lớn
try (Stream<String> lines = Files.lines(Path.of("data.txt"))) {
lines.forEach(System.out::println);
} 4. Serialization

Bạn nên biết khái niệm, nhưng không cần học quá sâu lúc đầu.

Serialization là biến object thành dạng có thể lưu trữ/truyền đi
Deserialization là khôi phục lại object
Giai đoạn 8: Lambda và Functional Programming

1. Lambda expression

Trước Java 8:

students.sort(new Comparator<Student>() {
@Override
public int compare(Student a, Student b) {
return a.getScore() - b.getScore();
}
});

Sau Java 8:

students.sort((a, b) -> a.getScore() - b.getScore()); 2. Functional Interface
@FunctionalInterface
public interface Calculator {
int calculate(int a, int b);
}

Dùng:

Calculator sum = (a, b) -> a + b; 3. Method reference
names.forEach(System.out::println);
Giai đoạn 9: Stream API

Stream API dùng rất nhiều trong Java Backend.

1. filter
   List<Student> goodStudents = students.stream()
   .filter(s -> s.getScore() >= 8)
   .toList();
2. map
   List<String> names = students.stream()
   .map(Student::getName)
   .toList();
3. sorted
   List<Student> sortedStudents = students.stream()
   .sorted((a, b) -> b.getScore() - a.getScore())
   .toList();
4. collect
   Map<String, List<Student>> grouped = students.stream()
   .collect(Collectors.groupingBy(Student::getClassName));
5. reduce
   int total = numbers.stream()
   .reduce(0, Integer::sum);

Cần học kỹ:

filter
map
flatMap
sorted
distinct
limit
skip
anyMatch
allMatch
noneMatch
findFirst
findAny
collect
groupingBy
partitioningBy
reduce
Bài tập Stream API

1. Lọc sinh viên có điểm >= 8.
2. Lấy danh sách tên sinh viên.
3. Sắp xếp sinh viên theo điểm giảm dần.
4. Tính điểm trung bình.
5. Tìm sinh viên điểm cao nhất.
6. Nhóm sinh viên theo lớp.
7. Đếm số sinh viên theo từng lớp.
8. Tìm danh sách email không trùng.
9. Chuyển List<Student> sang Map<id, Student>.
10. Tính tổng doanh thu từ List<Order>.
    Giai đoạn 10: Date and Time API

Không nên dùng Date và Calendar cũ nếu không bắt buộc. Nên học java.time.

Cần học:

LocalDate
LocalTime
LocalDateTime
ZonedDateTime
Instant
Duration
Period
DateTimeFormatter

Ví dụ:

LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(2004, 5, 20);

Format ngày:

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

String result = today.format(formatter);

Parse ngày:

LocalDate date = LocalDate.parse("16/05/2026", formatter);

Bài tập:

1. Tính tuổi từ ngày sinh.
2. Tính số ngày giữa 2 ngày.
3. Format ngày theo dd/MM/yyyy.
4. Kiểm tra đơn hàng có quá hạn chưa.
5. Tính thời gian còn lại đến deadline.
   Giai đoạn 11: Enum, Record, Optional
6. Enum
   public enum OrderStatus {
   PENDING,
   PAID,
   CANCELLED
   }

Dùng:

OrderStatus status = OrderStatus.PAID;

Rất hay dùng trong backend:

UserRole
OrderStatus
PaymentStatus
ListingStatus
PackageType 2. Record

Record giúp tạo class chứa dữ liệu ngắn gọn.

public record StudentDto(String name, int age) {
}

Thay vì phải viết nhiều getter, constructor, equals, hashCode.

Java 21 đã có nhiều cải tiến hiện đại như record patterns và pattern matching for switch; bạn không cần học quá sớm, nhưng nên biết sau khi vững OOP.

3. Optional
   Optional<Student> student = findStudentById(1);

student.ifPresent(System.out::println);

Dùng để giảm lỗi NullPointerException, nhưng không nên lạm dụng.

Giai đoạn 12: Memory, JVM, Garbage Collection

Phần này không cần học quá sâu lúc đầu, nhưng muốn lên level khá thì phải biết.

Cần hiểu
Stack memory
Heap memory
Object được lưu ở đâu
Reference là gì
Garbage Collection là gì
String Pool là gì
static nằm ở đâu

Ví dụ:

Student s1 = new Student("Bao");
Student s2 = s1;

Cần hiểu s1 và s2 cùng trỏ tới một object.

Những lỗi cần hiểu
NullPointerException
StackOverflowError
OutOfMemoryError
Memory leak

Oracle có tài liệu riêng về JVM và Garbage Collection trong JDK docs, phù hợp để học sâu sau khi bạn đã code Java Core ổn.

Giai đoạn 13: Multithreading và Concurrency

Đây là phần khó, học sau cùng.

1. Thread cơ bản
   Thread thread = new Thread(() -> {
   System.out.println("Running in thread");
   });

thread.start(); 2. Runnable
Runnable task = () -> System.out.println("Task running");
new Thread(task).start(); 3. ExecutorService
ExecutorService executor = Executors.newFixedThreadPool(3);

executor.submit(() -> {
System.out.println("Task");
});

executor.shutdown(); 4. synchronized
public synchronized void increase() {
count++;
} 5. Các khái niệm cần nắm
Thread
Runnable
Callable
Future
ExecutorService
synchronized
volatile
race condition
deadlock
thread pool
CompletableFuture 6. Virtual Threads

Từ Java 21, Virtual Threads là một tính năng quan trọng, giúp xử lý concurrency nhẹ hơn so với platform threads trong nhiều trường hợp server-side. OpenJDK liệt kê Virtual Threads trong JDK 21 thông qua JEP 444.

Ví dụ:

Thread.startVirtualThread(() -> {
System.out.println("Hello from virtual thread");
});

Bạn không cần học Virtual Threads ngay từ đầu. Học sau khi đã hiểu thread truyền thống.

Giai đoạn 14: Annotation và Reflection

Phần này rất quan trọng nếu sau này học Spring Boot.

1. Annotation

Ví dụ:

@Override
@Deprecated
@SuppressWarnings("unchecked")

Custom annotation:

public @interface MyAnnotation {
} 2. Reflection

Reflection cho phép đọc thông tin class, method, field khi chương trình đang chạy.

Ví dụ:

Class<?> clazz = Student.class;

for (Method method : clazz.getDeclaredMethods()) {
System.out.println(method.getName());
}

Spring Boot dùng rất nhiều annotation và reflection, nên phần này giúp bạn hiểu bên trong Spring hoạt động như thế nào.

Giai đoạn 15: Maven, Unit Test, Clean Code

1. Maven

Cần học:

pom.xml
dependency
plugin
build lifecycle
mvn clean
mvn compile
mvn test
mvn package

Ví dụ dependency:

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
2. Unit Test với JUnit
@Test
void shouldReturnSum() {
    int result = Calculator.sum(2, 3);
    assertEquals(5, result);
}

Cần học:

@Test
assertEquals
assertTrue
assertFalse
assertThrows
@BeforeEach
@AfterEach 3. Clean Code cơ bản

Cần luyện:

Tên biến rõ nghĩa
Method ngắn
Class có một trách nhiệm
Không lặp code
Không viết if-else quá dài
Tách service xử lý logic
Không hard-code lung tung

Ví dụ tên biến chưa tốt:

int a = 10;

Tốt hơn:

int userAge = 10;
Thứ tự học đề xuất trong 8 tuần
Tuần 1: Syntax cơ bản

Học:

biến
kiểu dữ liệu
toán tử
if-else
switch
for
while
method cơ bản

Làm ít nhất 30 bài nhỏ.

Tuần 2: Array, String, Method

Học:

array
String
StringBuilder
method
overloading
scope

Làm project nhỏ:

Ứng dụng xử lý chuỗi
Ứng dụng quản lý điểm sinh viên bằng mảng
Tuần 3: OOP cơ bản

Học:

class
object
constructor
this
getter/setter
encapsulation

Làm project:

Student Management Console App
Tuần 4: OOP nâng cao

Học:

inheritance
polymorphism
abstract class
interface
static
final
access modifier

Làm project:

Employee Salary Management
Payment Method Demo
Tuần 5: Exception + Collections

Học:

try-catch
custom exception
List
Set
Map
Queue
Deque
Comparable
Comparator

Làm project:

Library Management Console App
Tuần 6: Generics + IO + Date Time

Học:

generic class
generic method
wildcard cơ bản
đọc ghi file
LocalDate
LocalDateTime
DateTimeFormatter

Làm project:

Todo App lưu dữ liệu vào file
Tuần 7: Lambda + Stream API

Học:

lambda
functional interface
method reference
stream
filter
map
sorted
collect
groupingBy
reduce

Làm project:

Order Analytics Console App

Ví dụ tính:

tổng doanh thu
đơn hàng theo trạng thái
top sản phẩm bán chạy
lọc đơn hàng theo ngày
Tuần 8: JVM + Thread + Maven + Test

Học:

JVM cơ bản
heap/stack
garbage collection
thread cơ bản
ExecutorService
Maven
JUnit
clean code

Làm project tổng kết:

Mini Banking System Console App
Project Java Core nên làm
Project 1: Student Management

Chức năng:

Thêm sinh viên
Sửa sinh viên
Xóa sinh viên
Tìm theo id
Tìm theo tên
Sắp xếp theo điểm
Xếp loại học lực
Lưu dữ liệu ra file
Đọc dữ liệu từ file

Kiến thức dùng:

OOP
ArrayList
Comparator
Exception
File IO
Project 2: Library Management

Chức năng:

Thêm sách
Tìm sách
Mượn sách
Trả sách
Quản lý người mượn
Kiểm tra sách đang được mượn
Lưu lịch sử mượn trả

Kiến thức dùng:

OOP
Map
List
Enum
LocalDate
Custom Exception
File IO
Project 3: Mini Banking System

Chức năng:

Tạo tài khoản
Đăng nhập
Nạp tiền
Rút tiền
Chuyển khoản
Xem lịch sử giao dịch
Kiểm tra số dư
Lưu dữ liệu vào file

Kiến thức dùng:

OOP
Encapsulation
Exception
List
Map
Enum
Date Time
File IO
Clean Code
Project 4: Order Management

Chức năng:

Tạo sản phẩm
Tạo đơn hàng
Thêm sản phẩm vào đơn
Tính tổng tiền
Áp mã giảm giá
Lọc đơn theo trạng thái
Thống kê doanh thu
Top sản phẩm bán chạy

Kiến thức dùng:

OOP
Stream API
Map
Enum
BigDecimal
LocalDateTime
Những phần Java Core quan trọng nhất khi đi làm Backend

Nếu mục tiêu của bạn là Java Spring Boot Intern/Fresher, hãy ưu tiên theo thứ tự này:

1. OOP
2. Collection
3. Exception
4. Stream API
5. Lambda
6. Date Time API
7. Enum
8. Generics
9. Maven
10. Unit Test
11. JVM basic
12. Thread basic
13. Annotation + Reflection basic

Đặc biệt, phải rất chắc:

interface
abstract class
polymorphism
List/Map
custom exception
stream filter/map/collect
Optional
LocalDateTime
Maven dependency
Checklist tự kiểm tra Java Core

Bạn có thể tự hỏi:

Tôi có phân biệt được class và object không?
Tôi có hiểu this dùng làm gì không?
Tôi có biết khi nào dùng interface không?
Tôi có biết List, Set, Map khác nhau thế nào không?
Tôi có sort được List object không?
Tôi có viết được custom exception không?
Tôi có đọc ghi file đơn giản được không?
Tôi có dùng Stream để filter/map/collect được không?
Tôi có biết Optional dùng để làm gì không?
Tôi có biết LocalDateTime format thế nào không?
Tôi có biết Maven dependency là gì không?
Tôi có viết được unit test đơn giản không?

Nếu trả lời được hầu hết, Java Core của bạn đã khá ổn để học Spring Boot.

Lộ trình ngắn gọn nhất

Nếu bạn muốn học nhanh để đi Spring Boot, hãy đi theo thứ tự này:

Java syntax
→ OOP
→ Collection
→ Exception
→ Generics
→ Lambda
→ Stream API
→ Date Time
→ Enum
→ File IO
→ Maven
→ JUnit
→ Annotation
→ JVM basic
→ Thread basic
→ Spring Boot
Tài liệu nên dùng

Nguồn chính thống nhất là tài liệu Oracle JDK. Oracle có đầy đủ API documentation, guides, migration guide, JVM guide, GC tuning guide và language updates cho từng phiên bản JDK.

Ngoài ra, khi học thực chiến, bạn nên kết hợp:

1. Java documentation chính thức
2. Baeldung Java tutorials
3. CodingBat Java
4. LeetCode easy bằng Java
5. Một project console tự làm
6. Sau đó chuyển sang Spring Boot
   Gợi ý kế hoạch học mỗi ngày

Nếu bạn học 2 tiếng/ngày, có thể chia như sau:

30 phút: học lý thuyết
60 phút: code theo ví dụ
30 phút: làm bài tập tự nghĩ hoặc refactor lại code cũ

Nếu học 4 tiếng/ngày:

60 phút: học lý thuyết
120 phút: code bài tập
60 phút: làm project nhỏ

Nguyên tắc là:

Đừng chỉ xem video.
Mỗi kiến thức phải có code.
Mỗi tuần phải có một project nhỏ.
Học Java Core xong phải làm được app console tương đối hoàn chỉnh.

Roadmap này mà học nghiêm túc khoảng 6-8 tuần là đủ nền tảng tốt để chuyển sang Spring Boot.
