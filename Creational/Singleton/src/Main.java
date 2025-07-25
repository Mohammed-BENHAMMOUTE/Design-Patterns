/**
 * Demonstration of the Singleton Design Pattern
 *
 * This example showcases the ThreadPoolManager as a Singleton with:
 * 1. Thread-safe double-checked locking implementation
 * 2. Lazy initialization
 * 3. Prevention of cloning
 * 4. Practical use case with thread pool management
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Singleton Design Pattern Demo ===\n");

        // 1. Demonstrate singleton instance creation
        System.out.println("1. Creating Singleton Instances:");
        demonstrateSingletonCreation();

        // 2. Demonstrate thread safety
        System.out.println("2. Thread Safety Test:");
        demonstrateThreadSafety();

        // 3. Demonstrate practical usage
        System.out.println("3. Practical Usage - Task Execution:");
        demonstratePracticalUsage();

        // 4. Demonstrate singleton benefits and constraints
        System.out.println("4. Singleton Constraints:");
        demonstrateSingletonConstraints();

        // Clean up
        ThreadPoolManager.getInstance().shutdown();
        System.out.println("\n=== Singleton Demo Complete ===");
    }

    private static void demonstrateSingletonCreation() {
        // Get instances using different methods
        ThreadPoolManager manager1 = ThreadPoolManager.getInstance();
        ThreadPoolManager manager2 = ThreadPoolManager.getInstance(15); // Thread count ignored if already created
        ThreadPoolManager manager3 = ThreadPoolManager.getInstance();

        System.out.println("Manager1 hash: " + manager1.hashCode());
        System.out.println("Manager2 hash: " + manager2.hashCode());
        System.out.println("Manager3 hash: " + manager3.hashCode());
        System.out.println("All instances are the same: " + (manager1 == manager2 && manager2 == manager3));
        System.out.println("Thread count: " + manager1.getThreadCount());
        System.out.println();
    }

    private static void demonstrateThreadSafety() {
        System.out.println("Creating singleton instances from multiple threads...");
        
        // Create multiple threads that try to get the singleton instance
        Thread[] threads = new Thread[5];
        ThreadPoolManager[] instances = new ThreadPoolManager[5];
        
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = ThreadPoolManager.getInstance();
                System.out.println("Thread " + index + " got instance: " + instances[index].hashCode());
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify all instances are the same
        boolean allSame = true;
        for (int i = 1; i < instances.length; i++) {
            if (instances[i] != instances[0]) {
                allSame = false;
                break;
            }
        }
        System.out.println("All thread instances are identical: " + allSame);
        System.out.println();
    }

    private static void demonstratePracticalUsage() {
        ThreadPoolManager manager = ThreadPoolManager.getInstance();
        
        System.out.println("Executing tasks using singleton ThreadPoolManager:");
        manager.printStatus();

        // Create and execute some sample tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            manager.executeTask(() -> {
                System.out.println("Executing Task " + taskId + " on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }

        // Add tasks to queue
        System.out.println("\nAdding tasks to queue:");
        for (int i = 6; i <= 8; i++) {
            final int taskId = i;
            manager.addTask(() -> {
                System.out.println("Queued Task " + taskId + " executing on: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Wait a bit for tasks to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        manager.printStatus();
        System.out.println();
    }

    private static void demonstrateSingletonConstraints() {
        ThreadPoolManager manager = ThreadPoolManager.getInstance();
        
        System.out.println("Testing singleton constraints:");
        
        // 1. Test cloning prevention
        try {
            @SuppressWarnings("unused")
            ThreadPoolManager clonedManager = (ThreadPoolManager) manager.clone();
            System.out.println("❌ Cloning succeeded (this shouldn't happen)");
        } catch (CloneNotSupportedException e) {
            System.out.println("✓ Cloning prevented: " + e.getMessage());
        }

        // 2. Show that constructor is private (cannot be demonstrated at runtime)
        System.out.println("✓ Constructor is private - cannot create instances directly");
        
        // 3. Show thread count cannot be changed after creation
        System.out.println("✓ Thread count is fixed after first instantiation");
        System.out.println("  Current thread count: " + manager.getThreadCount());
        
        ThreadPoolManager anotherAttempt = ThreadPoolManager.getInstance(20);
        System.out.println("  Thread count after getInstance(20): " + anotherAttempt.getThreadCount());
        System.out.println("  Same instance: " + (manager == anotherAttempt));
        
        System.out.println("\nSingleton Pattern Benefits:");
        System.out.println("✓ Controlled access to sole instance");
        System.out.println("✓ Reduced memory footprint");
        System.out.println("✓ Global point of access");
        System.out.println("✓ Thread-safe lazy initialization");
        System.out.println("✓ Prevention of multiple instances");
        System.out.println();
    }
}