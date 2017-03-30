package concurrent.lock;

/**
 * Created by zhaoying on 2017/3/30.
 */

/**
 * 信号量
 * 信号量由一个指定的数量的"许可"初始化,
 * 每调用一个 acquire ,一个"许可"会被调用线程取走
 * 每调用一个 release ,一个"许可"会被返回给信号量
 * 因此,没有任何 release 调用时,最多有 N 个线程能够通过 acquire
 *
 * 用途:
 * 1、保护一个重要部分防止一次超过 N 个线程进入
 * 在两个线程之间发送信号
 */
public class SemaphoreTest {

}
