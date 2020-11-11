package thread;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework_CyclicBarrierMethod {

    private volatile  Integer result = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        Homework_CyclicBarrierMethod h = new Homework_CyclicBarrierMethod();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + h.result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });

        new Thread(()->{
            try {
//                cyclicBarrier.await();
                h.result = sum();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();


    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

}


