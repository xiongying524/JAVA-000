import java.util.concurrent.*;

/**
 * ˼���ж����ַ�ʽ����main��������һ�����̻߳��̳߳أ�
 *
 */
public class CountDownLatchWorker {
    private  int res = 0;
    private CountDownLatch countDownLatch;

    public CountDownLatchWork(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    private void sum(){
        res = fibo(36);
        countDownLatch.countDown();
    }

    private int getRes() throws InterruptedException{
        countDownLatch.await();
        return res;
    }

    
    public static void main(String[] args) throws InterruptedException{
        
        
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatchWork countDownLatchWork = new CountDownLatchWork(countDownLatch);

        new Thread(()-> {
            countDownLatchWork.sum();
        }).start();

        int result = countDownLatchWork.getRes();
        System.out.println("�첽������Ϊ��"+result);

    }
    

}