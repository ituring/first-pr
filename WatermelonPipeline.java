package session03;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//能力有限   借鉴了 iMLe0n大佬的代码 谢谢啦 

public class WatermelonPipeline {

    public static class BananaWatermelon {

        int bananaQuantity;
        public BananaWatermelon(int bananaQuantity) {
        this.bananaQuantity = bananaQuantity;


 
        }


 
    }

public static class AppleWatermelon {

        int appleQuantity;
        public AppleWatermelon(int appleQuantity) {

            this.appleQuantity = appleQuantity;

 
        }


 
    }


    public static class CommonWatermelon {

        int quantity;
        
        public CommonWatermelon(int quantity) {


            this.quantity = quantity;
 
        }
    }
    
    
    
    public static void main(String[] args) {
    	
        int[] bananaWatermelonArray = {-1, 0, 5, 60,50,10,-5,9,10};

        List<BananaWatermelon> bananaWatermelons = new ArrayList<>();


        for (int i = 0; i < bananaWatermelonArray.length; i++) {

            bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));

        }



 
        int[] appleWatermelonArray ={-1, 0, 5, 60,50,10,-5,9,10};

        List<AppleWatermelon> appleWatermelons = new ArrayList<>();

        for (int i = 0; i < appleWatermelonArray.length; i++) {

            appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));

        }


        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);


 
        commonWatermelons.stream().forEach(System.out::println);


 
        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);
        sendoutWatermelons(filteredWatermenlon);
        countingWatermelons(filteredWatermenlon);


    }


    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {

        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。

        return Stream.of(bananaWatermelons, appleWatermelons).flatMap(t -> t.stream())

                .map(t -> {

                    if (t instanceof BananaWatermelon) {
 
                        return new CommonWatermelon(((BananaWatermelon) t).bananaQuantity);
 
                    } else if (t instanceof AppleWatermelon) {

                        return new CommonWatermelon(((AppleWatermelon) t).appleQuantity);

                    }
                    return null;

                }).collect(Collectors.toList());

    }


    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {

        //2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。

        return filterWatermelons.stream().filter(melon -> melon.quantity > 0 && melon.quantity <= 50).collect(Collectors.toList());

    }


    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {


 
        //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
    	//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

        AtomicInteger index = new AtomicInteger(1);

        AtomicInteger inspectorsIdx = new AtomicInteger(1);

        CountDownLatch countDownLatch = new CountDownLatch(filterWatermelons.size() * 5);

        ObjIntConsumer<CommonWatermelon> commonWatermelonConsumer = (melon, id) -> {
 
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    System.out.println(inspectorsIdx.getAndIncrement() + "号检察员检查第" + id + "个西瓜，质量为" + melon.quantity +   "，完毕。");
                    countDownLatch.countDown();
                }).start();

            }

        };
 
        for (CommonWatermelon commonWatermelon : filterWatermelons) {


 
            commonWatermelonConsumer.accept(commonWatermelon, index.getAndIncrement());


 
        }
        try {


 
            countDownLatch.await();


 
        } catch (InterruptedException e) {


 
            e.printStackTrace();


 
        }


    }

 
    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {


 
        System.out.println("---");


 
        System.out.println(commonWatermelons.stream().map(t -> t.quantity + "").sorted().collect(Collectors.joining(",")));


 
        System.out.println("---");


 
    }


    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {

        Integer count = commonWatermelons.stream().map(e -> 1).reduce(0, Integer::sum);


 
        System.out.println("这批西瓜总计有"+count+"个");


 
    }

}