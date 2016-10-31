import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhaoying on 16/10/9.
 */
public class FutureTest {
    public static  class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("ah");
        }
    }

    public static class CallableTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("ah");
            return "something";
        }
    }

//    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool();
//        for (int i= 0; i < 10 ; i ++){
//            es.submit(new Task());
//        }
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0 ;i < 10 ;i ++){
            results.add(es.submit(new CallableTask()));
        }

        for (Future<String> result : results){
            System.out.println(result.get());
        }
    }
}
