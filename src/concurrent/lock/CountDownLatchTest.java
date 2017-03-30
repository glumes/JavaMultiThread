package concurrent.lock;

/**
 * Created by zhaoying on 2017/3/30.
 */

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLock 锁允许一个或多个线程等待一系列指定操作的完成
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        // 以指定数量初始化
        CountDownLatch latch = new CountDownLatch(3);

        Waiter waiter = new Waiter(latch) ;
        Decrementer decrementer = new Decrementer(latch) ;

        new Thread(waiter).start();
        new Thread(decrementer).start();
    }
}

/**
 * 等到 CountDownLatch 数量达到 0 之后,再打印内容
 */
class Waiter implements Runnable{

    CountDownLatch latch = null ;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // 调用 await 方法,线程可以阻塞等待 CountDownLatch 数量达到 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Waiter Released");
    }
}

class Decrementer implements Runnable{

    CountDownLatch latch = null  ;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            // 每调用一次 countDown ,CountDownLatch 的数量就会减一
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}