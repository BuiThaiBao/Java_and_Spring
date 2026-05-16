package GiaiDoan13_Multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ============================================================
 * GIAI ĐOẠN 13: MULTITHREADING - THỰC HÀNH
 * ============================================================
 */

public class thuc_hanh {

    private static int unsafeCount = 0;
    private static final AtomicInteger safeCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        // BÀI 1: Thread cơ bản
        System.out.println("=== BÀI 1: Thread cơ bản ===");
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  Thread 1: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("  Thread 2: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        t1.start();
        t2.start();
        t1.join(); t2.join(); // Chờ cả 2 hoàn thành
        System.out.println();

        // BÀI 2: Race Condition
        System.out.println("=== BÀI 2: Race Condition ===");
        unsafeCount = 0;
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) unsafeCount++;
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("  Unsafe count (expected 10000): " + unsafeCount);
        System.out.println("  → Có thể sai do race condition!");

        // BÀI 3: AtomicInteger - giải quyết race condition
        System.out.println("\n=== BÀI 3: AtomicInteger ===");
        safeCount.set(0);
        Thread[] safeThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) safeCount.incrementAndGet();
            });
            safeThreads[i].start();
        }
        for (Thread t : safeThreads) t.join();
        System.out.println("  Safe count (expected 10000): " + safeCount.get());
        System.out.println("  → Luôn đúng nhờ AtomicInteger!");

        // BÀI 4: ExecutorService
        System.out.println("\n=== BÀI 4: ExecutorService ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.printf("  Task %d running on %s%n", taskId,
                        Thread.currentThread().getName());
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // BÀI 5: Callable + Future
        System.out.println("\n=== BÀI 5: Callable + Future ===");
        ExecutorService exec2 = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = exec2.submit(() -> {
            Thread.sleep(500);
            return 42;
        });
        Future<String> future2 = exec2.submit(() -> {
            Thread.sleep(300);
            return "Hello from Callable";
        });
        System.out.println("  Future1: " + future1.get());
        System.out.println("  Future2: " + future2.get());
        exec2.shutdown();

        // BÀI 6: CompletableFuture
        System.out.println("\n=== BÀI 6: CompletableFuture ===");
        CompletableFuture<String> cf = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);
        System.out.println("  Result: " + cf.get());

        // Chain multiple async tasks
        CompletableFuture<Void> combined = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> System.out.println("  Async task 1")),
                CompletableFuture.runAsync(() -> System.out.println("  Async task 2")),
                CompletableFuture.runAsync(() -> System.out.println("  Async task 3"))
        );
        combined.get();

        // BÀI 7: Virtual Threads (Java 21+)
        System.out.println("\n=== BÀI 7: Virtual Threads ===");
        try {
            Thread vt = Thread.startVirtualThread(() -> {
                System.out.println("  Hello from Virtual Thread: " +
                        Thread.currentThread().isVirtual());
            });
            vt.join();
        } catch (Exception e) {
            System.out.println("  Virtual Threads cần Java 21+");
        }

        System.out.println("\n=== BÀI TẬP TỰ LÀM ===");
        System.out.println("8. Mô phỏng Producer-Consumer với BlockingQueue");
        System.out.println("9. Tạo countdown timer đa luồng");
        System.out.println("10. Mô phỏng deadlock và cách giải quyết");
    }
}
