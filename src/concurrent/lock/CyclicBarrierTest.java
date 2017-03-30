package concurrent.lock;

/**
 * Created by zhaoying on 2017/3/30.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 同样是一种同步机制,能够对处理一些算法的线程实现同步
 * 也就是说,一个所有线程都必须等待的栅栏,直到所有线程都达到这里
 * 然后所有线程才能继续做其他事情
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        // 创建 CyclicBarrier 时要指定有多少线程在被释放前等待栅栏


        Barrier1Action barrier1Action = new Barrier1Action() ;
        Barrier2Action barrier2Action = new Barrier2Action() ;

        // 当等待栅栏的个数满足 2 时,则才会继续执行下去,否则,阻塞
        CyclicBarrier barrier1 = new CyclicBarrier(2,barrier1Action) ;

        CyclicBarrier barrier2 = new CyclicBarrier(3,barrier2Action) ;


        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1,barrier2);

        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1,barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();

    }
}

class Barrier1Action implements Runnable{

    @Override
    public void run() {
        System.out.println("Barrier Action 1 executed");
    }
}

class Barrier2Action implements Runnable{

    @Override
    public void run() {
        System.out.println("Barrier Action 2 executed");
    }
}


class CyclicBarrierRunnable implements Runnable{

    CyclicBarrier barrier1 ;
    CyclicBarrier barrier2 ;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "waiting at barrier 1");
            // 调用 await 表示 正在等在 栅栏
            // 当栅栏收到指定个数的 await 等待后
            // 那么就开始让所有线程继续执行下去
            this.barrier1.await() ;

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "waiting at barrier 2");
            this.barrier2.await() ;

            System.out.println(Thread.currentThread().getName() + "done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

