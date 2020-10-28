不同GC的总结：

SerialGC：串行收集器是最简单的，效率差，通过-XX:+UseSerialGC可以使用串行垃圾回收器。

ParallelGC：是JDK8默认收集器，使用多线程来扫描和压缩堆，并行收集器同样有个缺点就是在它执行 minor或者 full 垃圾回收时将会停止所有的应用程序线程。并行收集器最适合应用程序，可以容忍应用程序的暂停，并试图优化来降低收集器导致的CPU开销。

CMS：带有Concurrent的是并发执行，并不影响业务时间，Concurrent操作的时候，默认占用系统线程数的1/4。主要影响业务时间的是两个阶段 Initial Mark（初始标记） 和 Final Remark（最终标记） 这两个阶段会产生SWT，即所有线程数都在干这一件事情，没有多余的线程处理业务

G1： G1收集器利用多个后台线程来扫描堆，将其划分为多个区域，范围从1MB到32MB（取决于堆的大小）。 G1收集器首先会去扫描那些包含最多垃圾对象的区域。 G1的新生代收集跟ParNew类似，当新生代占用达到一定比例的时候，开始出发收集。和CMS类似，G1收集器收集老年代对象会有短暂停顿。


结论：
  1、内存设置的太小的时候（100M左右），不论采用哪种GC算法，老年代都会被填满，导致没有内存可用，最后都会OOM。
  2、堆内存设置比较小的时候，容易触发GC，GC频次很高，因此，多线程处理GC并不能体现出优势，反而由于线程切换提高成本。
  3、堆内存设置比较大的时候，GC次数减少，每次GC的处理的存活对象较多，并行多线程就会发挥出优势，CMS GC的吞吐量是最大的，串行GC的吞吐量是最低的。
  
~~~
import java.io.IOException;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tianhl
 */
public class OkHttpExample {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801/";
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
~~~
