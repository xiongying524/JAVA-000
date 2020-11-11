import java.util.concurrent.*;

/**
 * ˼���ж����ַ�ʽ����main��������һ�����̻߳��̳߳أ�
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
      int result = res.get(); //���ǵõ��ķ���ֵ
      System.out.println("�첽������Ϊ��"+result);
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