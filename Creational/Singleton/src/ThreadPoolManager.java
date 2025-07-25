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

    public void addTask(Runnable task) {
        try {
            blockingTaskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
   }
}