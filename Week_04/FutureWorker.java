import java.util.concurrent.*;

/**
 * 思考有多少种方式，在main函数启动一个新线程或线程池，
 *
 */
public class FutureWorker {

  public static void main(String[] args) {

    ExecutorService execs = Executors.newCachedThreadPool();

    Future<Integer> res = execs.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return sum();
      }
    });

    execs.shutdown();

    try {
      int result = res.get(); //这是得到的返回值
      System.out.println("异步计算结果为："+result);
    } catch (InterruptedException e){
      e.printStackTrace();
    } catch (ExecutionException e){
      e.printStackTrace();
    }

  }

  private static int sum() {
    return fibo(36);
  }

  private static int fibo(int a) {
    if ( a < 2)
      return 1;
    return fibo(a-1) + fibo(a-2);
  }
}