import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolManager {
    private static volatile ThreadPoolManager instance;
    private int threadCount;
    private final ExecutorService threadPool;
    private BlockingQueue<Runnable> blockingTaskQueue;


    private ThreadPoolManager(int threadCount) {
        this.threadCount = threadCount;
        this.threadPool = Executors.newFixedThreadPool(threadCount);
        this.blockingTaskQueue = new LinkedBlockingQueue<>();
    }

    // default constructor
    private ThreadPoolManager() {
        this(10);
    }

    public static ThreadPoolManager getInstance(int threadCount) {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolManager(threadCount);
                }
            }
        }
        return instance;
    }

    public static ThreadPoolManager getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    public void addTask(Runnable task) {
        try {
            blockingTaskQueue.put(task);
            // Process the task from the queue
            processNextTask();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted while adding task");
        }
    }

    private void processNextTask() {
        if (!blockingTaskQueue.isEmpty()) {
            Runnable task = blockingTaskQueue.poll();
            if (task != null) {
                threadPool.submit(task);
            }
        }
    }

    public void executeTask(Runnable task) {
        threadPool.submit(task);
    }

    public int getThreadCount() {
        return threadCount;
    }

    public int getQueueSize() {
        return blockingTaskQueue.size();
    }

    public boolean isShutdown() {
        return threadPool.isShutdown();
    }

    public void shutdown() {
        threadPool.shutdown();
        System.out.println("ThreadPoolManager shutdown initiated");
    }

    public void shutdownNow() {
        threadPool.shutdownNow();
        blockingTaskQueue.clear();
        System.out.println("ThreadPoolManager force shutdown completed");
    }

    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton instance cannot be cloned");
    }

    // Information method
    public void printStatus() {
        System.out.println("ThreadPoolManager Status:");
        System.out.println("  Thread Count: " + threadCount);
        System.out.println("  Queue Size: " + blockingTaskQueue.size());
        System.out.println("  Is Shutdown: " + threadPool.isShutdown());
        System.out.println("  Instance Hash: " + this.hashCode());
    }
}